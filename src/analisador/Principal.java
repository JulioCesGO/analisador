package analisador;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Principal {
    
    public static void main(final String[] args) {
        final Map<Integer, Set<String>> listaDeIdentificadores = new HashMap<>();
        final Map<Integer, Map<String, String>> listaDeLinhas = new HashMap<>();
        
        try {
            final LeituraDeArquivo p = new LeituraDeArquivo(Files.readAllBytes(Paths.get("file4.txt")));
            
            final List<String> linhas = p.lerConteudoDeANSIParaUTF8();
            int numeroDaInicializacao = 0;
            for (int i = 0; i < linhas.size(); i++) {
                
                if (isLinhaInicioAplicacao(linhas.get(i))) {
                    listaDeIdentificadores.put(++numeroDaInicializacao, new HashSet<>());
                    listaDeLinhas.put(numeroDaInicializacao, new HashMap<>());
                }
                
                if (isLinhaDeLog(linhas.get(i))) {
                    
                    // System.out.println(linhas.get(i));
                    final String identificadorDoProcesso = extrairIdentificadorDoProcsso(linhas.get(i));
                    
                    if (listaDeIdentificadores.get(numeroDaInicializacao).contains(identificadorDoProcesso)) {
                        listaDeIdentificadores.get(numeroDaInicializacao).remove(identificadorDoProcesso);
                        listaDeLinhas.get(numeroDaInicializacao).remove(identificadorDoProcesso);
                    } else {
                        listaDeIdentificadores.get(numeroDaInicializacao).add(identificadorDoProcesso);
                        listaDeLinhas.get(numeroDaInicializacao).put(identificadorDoProcesso, linhas.get(i));
                    }
                }
            }
            
            System.out.println(listaDeIdentificadores);
            
            listaDeLinhas.entrySet().stream().forEach((e) -> {
                System.out.println("Execução de numero: " + e.getKey());
                e.getValue().entrySet().stream().forEach(m -> {
                    System.out.println(m);
                });
                System.out.println("---------------------------------");
            });
            
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    private static boolean isLinhaInicioAplicacao(final String string) {
        return string.indexOf("Starting FullApplication on") > 0;
    }
    
    private static String extrairIdentificadorDoProcsso(final String string) {
        return string.split("\\|\\|")[3];
    }
    
    private static boolean isLinhaDeLog(final String string) {
        return Objects.nonNull(string) && string.length() > 0 && string.split("\\|\\|").length > 5;
    }
}

package analisador;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LeituraDeArquivo {
    
    private final byte[] arquivo;
    
    public LeituraDeArquivo(final byte[] arquivo) {
        this.arquivo = arquivo;
    }
    
    public String lerLinha(final int posicao) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(this.arquivo)))) {
            return reader.lines().skip(posicao - 1).findFirst().orElse("");
        }
    }
    
    public List<String> lerAposLinha(final int posicao) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(this.arquivo)))) {
            return reader.lines().skip(posicao - 1).collect(Collectors.toList());
        }
    }
    
    public List<String> lerConteudoDeANSIParaUTF8() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(this.arquivo), "Cp1252"))) {
            final List<String> linhas = new ArrayList<>();
            reader.lines().skip(1).forEach(linha -> {
                
                final byte[] bytes = linha.getBytes(StandardCharsets.UTF_8);
                
                final String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
                
                linhas.add(utf8EncodedString);
                
            });
            return linhas;
        }
    }
    
}

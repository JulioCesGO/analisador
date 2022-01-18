package analisador;

public class LinhaLog {
    
    private Integer numero;
    
    private String identificador;
    
    private String descricao;
    
    public LinhaLog(final Integer numero, final String identificador, final String descricao) {
        super();
        this.numero = numero;
        this.identificador = identificador;
        this.descricao = descricao;
    }
    
    public LinhaLog(final String identificador) {
        this.identificador = identificador;
    }
    
    public Integer getNumero() {
        return this.numero;
    }
    
    public void setNumero(final Integer numero) {
        this.numero = numero;
    }
    
    public String getIdentificador() {
        return this.identificador;
    }
    
    public void setIdentificador(final String identificador) {
        this.identificador = identificador;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }
    
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        try {
            return Integer.parseInt(this.identificador.replace(" ", ""));
        } catch (final NumberFormatException e) {
            return 11;
        }
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof LinhaLog) {
            final LinhaLog l = (LinhaLog) obj;
            return l.identificador == this.identificador;
        }
        return false;
    }
}

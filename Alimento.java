public class Alimento {
    private String nome;
    private String tipo; // "SALADA", "PRINCIPAL", "ACOMPANHAMENTO"
    
    public Alimento(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getTipo() {
        return tipo;
    }
    
    @Override
    public String toString() {
        return nome;
    }
}

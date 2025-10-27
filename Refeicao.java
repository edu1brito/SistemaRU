public class Refeicao {
    private String nome;
    private Alimento salada;
    private Alimento pratoPrincipal;
    private Alimento acompanhamento;
    
    public Refeicao(String nome) {
        this.nome = nome;
    }
    
    public void setSalada(Alimento salada) {
        if (!salada.getTipo().equals("SALADA")) {
            System.out.println("Erro: precisa ser uma salada!");
            return;
        }
        this.salada = salada;
    }
    
    public void setPratoPrincipal(Alimento prato) {
        if (!prato.getTipo().equals("PRINCIPAL")) {
            System.out.println("Erro: precisa ser um prato principal!");
            return;
        }
        this.pratoPrincipal = prato;
    }
    
    public void setAcompanhamento(Alimento acomp) {
        if (!acomp.getTipo().equals("ACOMPANHAMENTO")) {
            System.out.println("Erro: precisa ser um acompanhamento!");
            return;
        }
        this.acompanhamento = acomp;
    }
    
    public boolean isCompleta() {
        return salada != null && pratoPrincipal != null && acompanhamento != null;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void mostrarRefeicao() {
        System.out.println("  Salada: " + (salada != null ? salada.getNome() : "---"));
        System.out.println("  Principal: " + (pratoPrincipal != null ? pratoPrincipal.getNome() : "---"));
        System.out.println("  Acompanhamento: " + (acompanhamento != null ? acompanhamento.getNome() : "---"));
    }
}

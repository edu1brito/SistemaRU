public class Menu {
    private String diaSemana;
    private String turno; // "ALMOÇO" ou "JANTAR"
    private Refeicao refeicao;
    
    public Menu(String dia, String turno, Refeicao refeicao) {
        this.diaSemana = dia;
        this.turno = turno;
        this.refeicao = refeicao;
    }
    
    public String getDia() {
        return diaSemana;
    }
    
    public String getTurno() {
        return turno;
    }
    
    public Refeicao getRefeicao() {
        return refeicao;
    }
    
    public void exibirMenu() {
        System.out.println("\n" + turno + ":");
        if (refeicao != null) {
            refeicao.mostrarRefeicao();
        } else {
            System.out.println("  Sem refeição cadastrada");
        }
    }
}

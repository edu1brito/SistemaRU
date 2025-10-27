import java.util.*;

public class SistemaRU {
    private List<Alimento> alimentosCadastrados;
    private List<Refeicao> refeicoesCadastradas;
    private Map<String, List<Menu>> cardapioSemanal;
    private Scanner scanner;
    
    public SistemaRU() {
        alimentosCadastrados = new ArrayList<>();
        refeicoesCadastradas = new ArrayList<>();
        cardapioSemanal = new LinkedHashMap<>();
        scanner = new Scanner(System.in);
        
        // Inicializa os dias da semana
        String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};
        for (String dia : dias) {
            cardapioSemanal.put(dia, new ArrayList<>());
        }
    }
    
    public void executar() {
        int opcao;
        
        System.out.println("===== SISTEMA DE CARDÁPIO DO RU =====");
        
        do {
            System.out.println("\n----- MENU PRINCIPAL -----");
            System.out.println("1 - Cadastrar alimento");
            System.out.println("2 - Montar refeição");
            System.out.println("3 - Criar menu do dia");
            System.out.println("4 - Ver cardápio semanal");
            System.out.println("5 - Listar alimentos");
            System.out.println("6 - Listar refeições");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer
            
            switch (opcao) {
                case 1:
                    cadastrarAlimento();
                    break;
                case 2:
                    montarRefeicao();
                    break;
                case 3:
                    criarMenu();
                    break;
                case 4:
                    mostrarCardapioSemanal();
                    break;
                case 5:
                    listarAlimentos();
                    break;
                case 6:
                    listarRefeicoes();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        
        scanner.close();
    }
    
    private void cadastrarAlimento() {
        System.out.println("\n--- CADASTRO DE ALIMENTO ---");
        System.out.print("Nome do alimento: ");
        String nome = scanner.nextLine();
        
        System.out.println("Tipo do alimento:");
        System.out.println("1 - Salada");
        System.out.println("2 - Prato principal");
        System.out.println("3 - Acompanhamento");
        System.out.print("Escolha: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        String tipoStr;
        switch (tipo) {
            case 1:
                tipoStr = "SALADA";
                break;
            case 2:
                tipoStr = "PRINCIPAL";
                break;
            case 3:
                tipoStr = "ACOMPANHAMENTO";
                break;
            default:
                System.out.println("Tipo inválido!");
                return;
        }
        
        Alimento alimento = new Alimento(nome, tipoStr);
        alimentosCadastrados.add(alimento);
        System.out.println("Alimento cadastrado com sucesso!");
    }
    
    private void montarRefeicao() {
        System.out.println("\n--- MONTAR REFEIÇÃO ---");
        
        if (alimentosCadastrados.isEmpty()) {
            System.out.println("Nenhum alimento cadastrado ainda!");
            return;
        }
        
        System.out.print("Nome da refeição: ");
        String nome = scanner.nextLine();
        Refeicao refeicao = new Refeicao(nome);
        
        // Escolher salada
        System.out.println("\nSaladas disponíveis:");
        List<Alimento> saladas = filtrarPorTipo("SALADA");
        if (saladas.isEmpty()) {
            System.out.println("Nenhuma salada cadastrada!");
        } else {
            for (int i = 0; i < saladas.size(); i++) {
                System.out.println((i + 1) + " - " + saladas.get(i).getNome());
            }
            System.out.print("Escolha a salada (0 para pular): ");
            int escolha = scanner.nextInt();
            if (escolha > 0 && escolha <= saladas.size()) {
                refeicao.setSalada(saladas.get(escolha - 1));
            }
        }
        
        // Escolher prato principal
        System.out.println("\nPratos principais disponíveis:");
        List<Alimento> principais = filtrarPorTipo("PRINCIPAL");
        if (principais.isEmpty()) {
            System.out.println("Nenhum prato principal cadastrado!");
        } else {
            for (int i = 0; i < principais.size(); i++) {
                System.out.println((i + 1) + " - " + principais.get(i).getNome());
            }
            System.out.print("Escolha o prato principal (0 para pular): ");
            int escolha = scanner.nextInt();
            if (escolha > 0 && escolha <= principais.size()) {
                refeicao.setPratoPrincipal(principais.get(escolha - 1));
            }
        }
        
        // Escolher acompanhamento
        System.out.println("\nAcompanhamentos disponíveis:");
        List<Alimento> acompanhamentos = filtrarPorTipo("ACOMPANHAMENTO");
        if (acompanhamentos.isEmpty()) {
            System.out.println("Nenhum acompanhamento cadastrado!");
        } else {
            for (int i = 0; i < acompanhamentos.size(); i++) {
                System.out.println((i + 1) + " - " + acompanhamentos.get(i).getNome());
            }
            System.out.print("Escolha o acompanhamento (0 para pular): ");
            int escolha = scanner.nextInt();
            if (escolha > 0 && escolha <= acompanhamentos.size()) {
                refeicao.setAcompanhamento(acompanhamentos.get(escolha - 1));
            }
        }
        
        scanner.nextLine(); // limpa buffer
        
        if (refeicao.isCompleta()) {
            refeicoesCadastradas.add(refeicao);
            System.out.println("\nRefeição montada com sucesso!");
            refeicao.mostrarRefeicao();
        } else {
            System.out.println("\nAtenção: Refeição incompleta! Salve mesmo assim? (s/n)");
            String resposta = scanner.nextLine();
            if (resposta.equalsIgnoreCase("s")) {
                refeicoesCadastradas.add(refeicao);
                System.out.println("Refeição salva!");
            }
        }
    }
    
    private void criarMenu() {
        System.out.println("\n--- CRIAR MENU DO DIA ---");
        
        if (refeicoesCadastradas.isEmpty()) {
            System.out.println("Nenhuma refeição montada ainda!");
            return;
        }
        
        System.out.println("Dia da semana:");
        String[] dias = {"Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado", "Domingo"};
        for (int i = 0; i < dias.length; i++) {
            System.out.println((i + 1) + " - " + dias[i]);
        }
        System.out.print("Escolha: ");
        int diaEscolhido = scanner.nextInt();
        
        if (diaEscolhido < 1 || diaEscolhido > 7) {
            System.out.println("Dia inválido!");
            return;
        }
        
        String dia = dias[diaEscolhido - 1];
        
        System.out.println("Turno:");
        System.out.println("1 - Almoço");
        System.out.println("2 - Jantar");
        System.out.print("Escolha: ");
        int turnoEscolhido = scanner.nextInt();
        
        String turno;
        if (turnoEscolhido == 1) {
            turno = "ALMOÇO";
        } else if (turnoEscolhido == 2) {
            turno = "JANTAR";
        } else {
            System.out.println("Turno inválido!");
            return;
        }
        
        System.out.println("\nRefeições disponíveis:");
        for (int i = 0; i < refeicoesCadastradas.size(); i++) {
            System.out.println((i + 1) + " - " + refeicoesCadastradas.get(i).getNome());
        }
        System.out.print("Escolha a refeição: ");
        int refeicaoEscolhida = scanner.nextInt();
        scanner.nextLine();
        
        if (refeicaoEscolhida < 1 || refeicaoEscolhida > refeicoesCadastradas.size()) {
            System.out.println("Refeição inválida!");
            return;
        }
        
        Menu menu = new Menu(dia, turno, refeicoesCadastradas.get(refeicaoEscolhida - 1));
        cardapioSemanal.get(dia).add(menu);
        System.out.println("Menu criado com sucesso!");
    }
    
    private void mostrarCardapioSemanal() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println(" ".repeat(15) + "CARDÁPIO SEMANAL DO RU");
        System.out.println("=".repeat(60));
        
        for (Map.Entry<String, List<Menu>> entry : cardapioSemanal.entrySet()) {
            String dia = entry.getKey();
            List<Menu> menus = entry.getValue();
            
            System.out.println("\n" + "-".repeat(60));
            System.out.println(">>> " + dia.toUpperCase());
            System.out.println("-".repeat(60));
            
            if (menus.isEmpty()) {
                System.out.println("  [Sem cardápio cadastrado]");
            } else {
                // Ordena por turno (almoço primeiro)
                menus.sort((m1, m2) -> m1.getTurno().compareTo(m2.getTurno()));
                
                for (Menu menu : menus) {
                    menu.exibirMenu();
                }
            }
        }
        System.out.println("\n" + "=".repeat(60));
    }
    
    private void listarAlimentos() {
        System.out.println("\n--- ALIMENTOS CADASTRADOS ---");
        
        if (alimentosCadastrados.isEmpty()) {
            System.out.println("Nenhum alimento cadastrado!");
            return;
        }
        
        System.out.println("\nSALADAS:");
        filtrarPorTipo("SALADA").forEach(a -> System.out.println("  - " + a.getNome()));
        
        System.out.println("\nPRATOS PRINCIPAIS:");
        filtrarPorTipo("PRINCIPAL").forEach(a -> System.out.println("  - " + a.getNome()));
        
        System.out.println("\nACOMPANHAMENTOS:");
        filtrarPorTipo("ACOMPANHAMENTO").forEach(a -> System.out.println("  - " + a.getNome()));
    }
    
    private void listarRefeicoes() {
        System.out.println("\n--- REFEIÇÕES MONTADAS ---");
        
        if (refeicoesCadastradas.isEmpty()) {
            System.out.println("Nenhuma refeição montada!");
            return;
        }
        
        for (Refeicao r : refeicoesCadastradas) {
            System.out.println("\n" + r.getNome() + ":");
            r.mostrarRefeicao();
        }
    }
    
    private List<Alimento> filtrarPorTipo(String tipo) {
        List<Alimento> filtrados = new ArrayList<>();
        for (Alimento a : alimentosCadastrados) {
            if (a.getTipo().equals(tipo)) {
                filtrados.add(a);
            }
        }
        return filtrados;
    }
    
    public static void main(String[] args) {
        SistemaRU sistema = new SistemaRU();
        sistema.executar();
    }
}

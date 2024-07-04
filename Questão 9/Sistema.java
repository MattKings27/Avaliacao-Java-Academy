import Aliens.*;
import Controle.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Sistema {
    private List<Alien> alienigenas;
    private List<EspecieAlien> especies;
    private Quarentena quarentena;
    private Scanner scanner;

    public Sistema() {
        this.alienigenas = new ArrayList<>();
        this.especies = new ArrayList<>();
        this.quarentena = new Quarentena();
        this.scanner = new Scanner(System.in);
        inicializarEspecies();
    }

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 0:
                    registrarAlien();
                    break;
                case 1:
                    exibirAlienigenas();
                    break;
                case 2:
                    rankingPericulosidade();
                    break;
                case 3:
                    visualizarPlanetas();
                    break;
                case 4:
                    verificarQuarentena();
                    break;
                case 5:
                    adicionarAlienQuarentena();
                    break;
                case 6:
                    verificarAliensPorEspecie();
                    break;
                case 7:
                    verificarAliensPorPlaneta();
                    break;
                case 8:
                    mostrarUltimosAliens();
                    break;
                case 9:
                    continuar = false;
                    break;
                default:
                    System.out.println("\n Talvez você seja o Alien!! Opção inválida.");
            }
        }
    }

    private void inicializarEspecies() {
        especies.add(new EspecieAlien("Saiyajin", "Planeta Saiyajin", 9));
        especies.add(new EspecieAlien("Namekuseijin", "Namekusei", 6));
        especies.add(new EspecieAlien("Marciano", "Marte", 5));
    }

    private void mostrarMenu() {
        System.out.println("\nMenu:");
        System.out.println("0. Novo Alien");
        System.out.println("1. Exibir Todos os Aliens Registrados");
        System.out.println("2. Ranking de Periculosidade");
        System.out.println("3. Visualizar Planetas dos Aliens");
        System.out.println("4. Verificar Quarentena");
        System.out.println("5. Adicionar Alien à Quarentena");
        System.out.println("6. Aliens por Espécie");
        System.out.println("7. Aliens por Planeta");
        System.out.println("8. Ultimos Aliens Registrados");
        System.out.println("9. Sair");
        System.out.print("Escolha uma opção: ");
        System.out.println();
    }

    private void registrarAlien() {
        System.out.println("Informe o nome do alienígena:");
        String nome = scanner.nextLine();

        System.out.println("Escolha a espécie do alienígena:");
        for (int i = 0; i < especies.size(); i++) {
            System.out.println(i + ". " + especies.get(i).getNome());
        }
        int escolhaEspecie = scanner.nextInt();
        scanner.nextLine();

        if (escolhaEspecie < 0 || escolhaEspecie >= especies.size()) {
            System.out.println("Espécie desconhecida! Pulando registro (Em breve traremos a opção de novos registros).");
            return;
        }

        EspecieAlien especie = especies.get(escolhaEspecie);

        int periculosidadeBase = especie.getNivelPericulosidadeBase();
        System.out.println("Digite o nível de poder específico do alienígena: (Fuja caso maior que 8000) ");
        int periculosidadeAtributos = scanner.nextInt();
        scanner.nextLine();

        int periculosidadeTotal = periculosidadeBase + periculosidadeAtributos;

        Alien alien = new Perigo(alienigenas.size() + 1, nome, especie.getNome(), LocalDateTime.now(), periculosidadeTotal);
        alienigenas.add(alien);

        if (periculosidadeTotal >= 7) {
            quarentena.adicionarAlienQuarentena((Perigo) alien);
            System.out.println("\nAlienígena adicionado à quarentena.");
        }

        System.out.println("\nAlienígena registrado com sucesso.");
    }

    private void exibirAlienigenas() {
        RelatorioEntradas relatorio = new RelatorioEntradas();
        relatorio.gerarRelatorio(alienigenas, quarentena);

        if (alienigenas.isEmpty()) {
            System.out.println("Nada aqui por enquanto.\n");
        }

    }

    private void rankingPericulosidade() {
        System.out.println("Ranking de Periculosidade:");
        System.out.println();

        if (alienigenas.isEmpty()) {
            System.out.println("Nada aqui por enquanto.\n");
            return;
        }

        alienigenas.stream()
                .sorted((a1, a2) -> Integer.compare(a2.avaliarPericulosidade(), a1.avaliarPericulosidade()))
                .forEach(alien -> System.out.println(alien.getNome() + " - " + alien.getEspecie() + " - Periculosidade: " + alien.avaliarPericulosidade()));
    }

    private void visualizarPlanetas() {
        System.out.println("Planetas dos Alienígenas:");
        System.out.println();

        if (especies.isEmpty()) {
            System.out.println("Nada aqui por enquanto.\n");
            return;
        }

        especies.forEach(especie -> {
            System.out.println("Planeta: " + especie.getPlanetaOrigem());
            System.out.println("Periculosidade Base: " + especie.getNivelPericulosidadeBase());
            System.out.println();
        });
    }

    private void verificarQuarentena() {
        System.out.println("Alienígenas em Quarentena:");

        if (quarentena.getAlienigenasQuarentena().isEmpty()) {
            System.out.println("Nada aqui por enquanto.\n");
            return;
        }

        for (Perigo alien : quarentena.getAlienigenasQuarentena()) {
            System.out.println(alien);
        }
    }

    private void adicionarAlienQuarentena() {
        System.out.println("Digite o ID do alien a ser adicionado à quarentena:");
        int id = scanner.nextInt();
        scanner.nextLine();

        Alien alien = null;
        for (Alien a : alienigenas) {
            if (a.getId() == id) {
                alien = a;
                break;
            }
        }

        if (alien != null && alien instanceof Perigo) {
            quarentena.adicionarAlienQuarentena((Perigo) alien);
            System.out.println("Alien adicionado à quarentena.");
        } else {
            System.out.println("Alien não encontrado ou não é perigoso.");
        }
    }

    private void verificarAliensPorEspecie() {
        System.out.println("Escolha a espécie para listar os alienígenas:");
        especies.forEach(especie -> System.out.println(especies.indexOf(especie) + ". " + especie.getNome()));
        int escolhaEspecie = scanner.nextInt();
        scanner.nextLine();

        if (escolhaEspecie < 0 || escolhaEspecie >= especies.size()) {
            System.out.println("Espécie desconhecida! Voltando ao menu.");
            return;
        }

        String nomeEspecie = especies.get(escolhaEspecie).getNome();
        List<Alien> aliensPorEspecie = alienigenas.stream()
                .filter(alien -> alien.getEspecie().equals(nomeEspecie))
                .collect(Collectors.toList());

        if (aliensPorEspecie.isEmpty()) {
            System.out.println("Nenhum alienígena registrado para a espécie '" + nomeEspecie + "'.\n");
        } else {
            System.out.println("\nAlienígenas da espécie '" + nomeEspecie + "':");
            aliensPorEspecie.forEach(alien -> System.out.println(alien));
        }
    }

    private void verificarAliensPorPlaneta() {
        System.out.println("Escolha o planeta para listar os alienígenas:");
        especies.forEach(especie -> System.out.println(especies.indexOf(especie) + ". " + especie.getPlanetaOrigem()));
        int escolhaPlaneta = scanner.nextInt();
        scanner.nextLine();

        if (escolhaPlaneta < 0 || escolhaPlaneta >= especies.size()) {
            System.out.println("Planeta desconhecido! Voltando ao menu.");
            return;
        }

        EspecieAlien especieSelecionada = especies.get(escolhaPlaneta);
        List<Alien> aliensPorPlaneta = alienigenas.stream()
                .filter(alien -> alien.getEspecie().equals(especieSelecionada))
                .collect(Collectors.toList());

        if (aliensPorPlaneta.isEmpty()) {
            System.out.println("Nenhum alienígena registrado para o planeta '" + especieSelecionada.getPlanetaOrigem() + "'.\n");
        } else {
            System.out.println("\nAlienígenas do planeta '" + especieSelecionada.getPlanetaOrigem() + "':");
            aliensPorPlaneta.forEach(alien -> System.out.println(alien));
        }
    }

    private void mostrarUltimosAliens() {
        LocalDateTime seisMesesAtras = LocalDateTime.now().minus(6, ChronoUnit.MONTHS);

        List<Alien> ultimosAliens = alienigenas.stream()
                .filter(alien -> alien.getDataEntrada().isAfter(seisMesesAtras))
                .collect(Collectors.toList());

        if (ultimosAliens.isEmpty()) {
            System.out.println("Nenhum alienígena registrado nos últimos 6 meses.\n");
        } else {
            System.out.println("\nAlienígenas registrados nos últimos 6 meses:");
            ultimosAliens.forEach(alien -> System.out.println(alien));
        }
    }
}
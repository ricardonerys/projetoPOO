package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaIntegrado {
    private Restaurante restaurante;
    private List<Evento> eventos;
    private Clinica clinica;

    public SistemaIntegrado() {
        this.restaurante = new Restaurante();
        this.eventos = new ArrayList<>();
        this.clinica = new Clinica();
    }


    public void exibirMenuPrincipal(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMENU INTEGRADO:");
            System.out.println("1. Acessar Restaurante");
            System.out.println("2. Gerenciar Eventos");
            System.out.println("3. Acessar Clínica");
            System.out.println("4. Perguntas de Tomada de Decisão");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    gerenciarRestaurante(scanner);
                    break;
                case 2:
                    gerenciarEventos(scanner);
                    break;
                case 3:
                    gerenciarClinica(scanner);
                    break;
                case 4:
                    responderPerguntas(scanner);
                    break;
                case 5:
                    System.out.println("\nAgradecemos pela preferência!");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void gerenciarRestaurante(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMENU DO RESTAURANTE:");
            System.out.println("1. Exibir Menu");
            System.out.println("2. Fazer Pedido");
            System.out.println("3. Fechar Conta");
            System.out.println("4. Buscar Pedido");
            System.out.println("5. Liberar Mesa");
            System.out.println("6. Cancelar Pedido");
            System.out.println("7. Gerar Relatório");
            System.out.println("8. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    restaurante.exibirMenu();
                    break;
                case 2:
                    System.out.print("\nNúmero da Mesa: ");
                    int numeroMesa = scanner.nextInt();
                    restaurante.fazerPedido(numeroMesa, scanner);
                    break;
                case 3:
                    System.out.print("\nNúmero da Mesa: ");
                    int mesa = scanner.nextInt();
                    restaurante.fecharConta(mesa, scanner);
                    break;
                case 4:
                    System.out.print("\nNúmero da Mesa: ");
                    int mesaVerificar = scanner.nextInt();
                    restaurante.verificarPedido(mesaVerificar);
                    break;
                case 5:
                    System.out.print("\nNúmero da Mesa a liberar: ");
                    int mesaLiberar = scanner.nextInt();
                    restaurante.liberarMesa(mesaLiberar);
                    break;
                case 6:
                    System.out.print("\nNúmero da Mesa para cancelar o pedido: ");
                    int mesaCancelar = scanner.nextInt();
                    restaurante.cancelarPedido(mesaCancelar, scanner);
                    break;
                case 7:
                    restaurante.gerarRelatorio();
                    break;
                case 8:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void gerenciarEventos(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMENU DE EVENTOS:");
            System.out.println("1. Criar Evento");
            System.out.println("2. Registrar Participante");
            System.out.println("3. Gerar Relatório do Evento");
            System.out.println("4. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    criarEvento(scanner);
                    break;
                case 2:
                    registrarParticipante(scanner);
                    break;
                case 3:
                    gerarRelatorioEventos(scanner);
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void criarEvento(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Nome do Evento: ");
        String nome = scanner.nextLine();
        System.out.print("Data do Evento: ");
        String data = scanner.nextLine();
        System.out.print("Local do Evento: ");
        String localNome = scanner.nextLine();
        System.out.print("Endereço do Evento: ");
        String endereco = scanner.nextLine();
        System.out.print("Capacidade Máxima: ");
        int capacidadeMaxima = scanner.nextInt();

        Local local = new Local(localNome, endereco);
        Evento evento = new Evento(nome, data, local, capacidadeMaxima);
        eventos.add(evento);
        System.out.println("Evento criado com sucesso!");
    }

    private void registrarParticipante(Scanner scanner) {
        System.out.print("Digite o nome do evento para registrar o participante: ");
        scanner.nextLine();
        String nomeEvento = scanner.nextLine();
        Evento evento = eventos.stream()
                .filter(e -> e.getNome().equalsIgnoreCase(nomeEvento))
                .findFirst()
                .orElse(null);

        if (evento != null) {
            System.out.print("Nome do Participante: ");
            String nomeParticipante = scanner.nextLine();
            System.out.print("Email do Participante: ");
            String email = scanner.nextLine();
            Participante participante = new Participante(nomeParticipante, email);
            evento.registrarParticipante(participante);
        } else {
            System.out.println("Evento não encontrado.");
        }
    }

    private void gerarRelatorioEventos(Scanner scanner) {
        System.out.print("Digite o nome do evento para gerar o relatório: ");
        scanner.nextLine();
        String nomeEvento = scanner.nextLine();
        Evento evento = eventos.stream()
                .filter(e -> e.getNome().equalsIgnoreCase(nomeEvento))
                .findFirst()
                .orElse(null);

        if (evento != null) {
            evento.gerarRelatorio();
        } else {
            System.out.println("Evento não encontrado.");
        }
    }

    private void gerenciarClinica(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMENU DA CLÍNICA:");
            System.out.println("1. Cadastrar Médico");
            System.out.println("2. Cadastrar Paciente");
            System.out.println("3. Agendar Consulta");
            System.out.println("4. Exibir Consultas");
            System.out.println("5. Exibir Pacientes");
            System.out.println("6. Exibir Médicos");
            System.out.println("7. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    clinica.criarMedico(scanner);
                    break;
                case 2:
                    clinica.criarPaciente(scanner);
                    break;
                case 3:
                    clinica.criarConsulta(scanner);
                    break;
                case 4:
                    exibirConsultas();
                    break;
                case 5:
                    exibirPacientes();
                    break;
                case 6:
                    exibirMedicos();
                    break;
                case 7:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    
    private void exibirConsultas() {
        if (clinica.getConsultas().isEmpty()) {
            System.out.println("Nenhuma consulta agendada.");
            return;
        }
        for (Consulta consulta : clinica.getConsultas()) {
            clinica.exibirDadosConsulta(consulta);
        }
    }
    
    private void exibirPacientes() {
        if (clinica.pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
        } else {
            for (Paciente p : clinica.pacientes) {
                clinica.exibirDadosPaciente(p);
                System.out.println();
            }
        }
    }
    
    private void exibirMedicos() {
        if (clinica.medicos.isEmpty()) {
            System.out.println("Nenhum médico cadastrado.");
        } else {
            for (Medico m : clinica.medicos) {
                clinica.exibirDadosMedico(m);
                System.out.println();
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaIntegrado sistema = new SistemaIntegrado();
        sistema.exibirMenuPrincipal(scanner);
        scanner.close();
    }    

    //PARA TERMINAR AS 6 TOMADAS DE DECISÕES
    private void eventoComMaisParticipantes() {
        
    }

    private void eventoMaisRentavel() {
        
    }

    private void medicoMaisSolicitado() {
        
    }

    private void datasHorariosDeUmMedico() {
        
    }

    private void pratoMaisPedido() {
        
    }
    
    private void mesaMaisRentavel() {
        
    }
    

    private void responderPerguntas(Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMENU DE PERGUNTAS:");
            System.out.println("1. Qual é o evento com maior número de participantes?");
            System.out.println("2. Qual é o evento mais rentável?");
            System.out.println("3. Qual médico foi mais solicitado?");
            System.out.println("4. Quais sao as datas e horários ocupados por um medico especifico?");
            System.out.println("5. Qual é o prato mais pedido?");
            System.out.println("6. Qual é a mesa que mais gera receita?");
            System.out.println("7. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    eventoComMaisParticipantes();
                    break;
                case 2:
                    eventoMaisRentavel();
                    break;
                case 3:
                    medicoMaisSolicitado();
                    break;
                case 4:
                    datasHorariosDeUmMedico();
                    break;
                case 5:
                    pratoMaisPedido();
                    break;
                case 6:
                    mesaMaisRentavel();
                    break;
                case 7:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}

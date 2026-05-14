import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.PrintWriter;

class Funcionario {

    String nome;
    int matricula;
    String tipoContrato;

    public Funcionario(String nome, int matricula, String tipoContrato) {
        this.nome = nome;
        this.matricula = matricula;
        this.tipoContrato = tipoContrato;
    }
}

class Talhao {

    int codigo;
    String nome;
    String variedadeCafe;
    double estimativaProducao;

    public Talhao(int codigo, String nome, String variedadeCafe, double estimativaProducao) {
        this.codigo = codigo;
        this.nome = nome;
        this.variedadeCafe = variedadeCafe;
        this.estimativaProducao = estimativaProducao;
    }
}

class Trator {

    String placa;
    double capacidadeMaxima;

    public Trator(String placa, double capacidadeMaxima) {
        this.placa = placa;
        this.capacidadeMaxima = capacidadeMaxima;
    }
}

class Lancamento {

    String data;
    int matriculaFuncionario;
    int codigoTalhao;
    String placaTrator;
    double litros;
    String destino;

    public Lancamento(String data, int matriculaFuncionario,
                      int codigoTalhao, String placaTrator,
                      double litros, String destino) {

        this.data = data;
        this.matriculaFuncionario = matriculaFuncionario;
        this.codigoTalhao = codigoTalhao;
        this.placaTrator = placaTrator;
        this.litros = litros;
        this.destino = destino;
    }
}

public class Main {

    static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    static ArrayList<Talhao> talhoes = new ArrayList<>();
    static ArrayList<Trator> tratores = new ArrayList<>();
    static ArrayList<Lancamento> lancamentos = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int opcao;

        do {

            System.out.println("\n=== FAZENDA ESPERANÇA ===");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Cadastrar talhão");
            System.out.println("3 - Cadastrar trator");
            System.out.println("4 - Registrar lançamento");
            System.out.println("5 - Relatório funcionários");
            System.out.println("6 - Relatório talhões");
            System.out.println("7 - Relatório secagem");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    cadastrarFuncionario();
                    break;

                case 2:
                    cadastrarTalhao();
                    break;

                case 3:
                    cadastrarTrator();
                    break;

                case 4:
                    registrarLancamento();
                    break;

                case 5:
                    Relatorio.relatorioFuncionarios(funcionarios, lancamentos);
                    break;

                case 6:
                    Relatorio.relatorioTalhoes(talhoes, lancamentos);
                    break;

                case 7:
                    Relatorio.relatorioSecagem(lancamentos);
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    public static void cadastrarFuncionario() {

        System.out.println("Nome:");
        String nome = sc.nextLine();

        System.out.println("Matrícula:");
        int matricula = sc.nextInt();
        sc.nextLine();

        System.out.println("Tipo de contrato:");
        String tipo = sc.nextLine();

        funcionarios.add(new Funcionario(nome, matricula, tipo));

        salvarFuncionarios();

        System.out.println("Funcionário cadastrado.");
    }

    public static void cadastrarTalhao() {

        System.out.println("Código:");
        int codigo = sc.nextInt();
        sc.nextLine();

        System.out.println("Nome:");
        String nome = sc.nextLine();

        System.out.println("Variedade do café:");
        String variedade = sc.nextLine();

        System.out.println("Estimativa de produção:");
        double estimativa = sc.nextDouble();
        sc.nextLine();

        talhoes.add(new Talhao(codigo, nome, variedade, estimativa));

        System.out.println("Talhão cadastrado.");
    }

    public static void cadastrarTrator() {

        System.out.println("Placa:");
        String placa = sc.nextLine();

        System.out.println("Capacidade máxima:");
        double capacidade = sc.nextDouble();
        sc.nextLine();

        tratores.add(new Trator(placa, capacidade));

        System.out.println("Trator cadastrado.");
    }

    public static void registrarLancamento() {

        System.out.println("Data:");
        String data = sc.nextLine();

        System.out.println("Matrícula do funcionário:");
        int matricula = sc.nextInt();
        sc.nextLine();

        boolean funcionarioExiste = false;

        for (Funcionario f : funcionarios) {

            if (f.matricula == matricula) {
                funcionarioExiste = true;
            }
        }

        if (!funcionarioExiste) {

            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.println("Código do talhão:");
        int codigoTalhao = sc.nextInt();
        sc.nextLine();

        System.out.println("Placa do trator:");
        String placa = sc.nextLine();

        Trator tratorEncontrado = null;

        for (Trator t : tratores) {

            if (t.placa.equals(placa)) {
                tratorEncontrado = t;
            }
        }

        if (tratorEncontrado == null) {

            System.out.println("Trator não encontrado.");
            return;
        }

        System.out.println("Quantidade de litros:");
        double litros = sc.nextDouble();
        sc.nextLine();

        if (litros > tratorEncontrado.capacidadeMaxima) {

            System.out.println("Carga maior que a capacidade do trator.");
            return;
        }

        System.out.println("Destino:");
        String destino = sc.nextLine();

        lancamentos.add(new Lancamento(
                data,
                matricula,
                codigoTalhao,
                placa,
                litros,
                destino
        ));

        System.out.println("Lançamento registrado.");
    }

    public static void salvarFuncionarios() {

        try {

            FileWriter fw = new FileWriter("funcionarios.txt");
            PrintWriter pw = new PrintWriter(fw);

            for (Funcionario f : funcionarios) {

                pw.println(
                        f.nome + ";" +
                        f.matricula + ";" +
                        f.tipoContrato
                );
            }

            pw.close();
            fw.close();

        } catch (Exception e) {

            System.out.println("Erro ao salvar funcionários.");
        }
    }
}
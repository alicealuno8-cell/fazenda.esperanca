import java.util.ArrayList;

public class Relatorio {

    public static void relatorioFuncionarios(
            ArrayList<Funcionario> funcionarios,
            ArrayList<Lancamento> lancamentos) {

        for (Funcionario f : funcionarios) {

            double total = 0;

            for (Lancamento l : lancamentos) {

                if (l.matriculaFuncionario == f.matricula) {
                    total += l.litros;
                }
            }

            System.out.println("Funcionário: " + f.nome);
            System.out.println("Total colhido: " + total + " litros");
            System.out.println();
        }
    }

    public static void relatorioTalhoes(
            ArrayList<Talhao> talhoes,
            ArrayList<Lancamento> lancamentos) {

        for (Talhao t : talhoes) {

            double total = 0;

            for (Lancamento l : lancamentos) {

                if (l.codigoTalhao == t.codigo) {
                    total += l.litros;
                }
            }

            System.out.println("Talhão: " + t.nome);
            System.out.println("Produção atual: " + total);
            System.out.println("Estimativa inicial: " + t.estimativaProducao);

            if (total >= t.estimativaProducao) {
                System.out.println("Meta atingida");
            }

            System.out.println();
        }
    }

    public static void relatorioSecagem(
            ArrayList<Lancamento> lancamentos) {

        double terreiro = 0;
        double secador = 0;

        for (Lancamento l : lancamentos) {

            if (l.destino.equalsIgnoreCase("Terreiro")) {
                terreiro += l.litros;
            }

            if (l.destino.equalsIgnoreCase("Secador")) {
                secador += l.litros;
            }
        }

        System.out.println("Total Terreiro: " + terreiro);
        System.out.println("Total Secador: " + secador);
    }
}
public class Lancamento {

    String data;
    int matriculaFuncionario;
    int codigoTalhao;
    String placaTrator;
    double litros;
    String destino;

    public Lancamento(String data, int matriculaFuncionario, int codigoTalhao,
                      String placaTrator, double litros, String destino) {

        this.data = data;
        this.matriculaFuncionario = matriculaFuncionario;
        this.codigoTalhao = codigoTalhao;
        this.placaTrator = placaTrator;
        this.litros = litros;
        this.destino = destino;
    }
}
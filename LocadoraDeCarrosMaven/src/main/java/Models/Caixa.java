package Models;

public class Caixa {

    private double ultimoValor;
    private double total;
    private double gastos;
    private double ganhos;

    public Caixa() {
    }

    public Caixa(double ganhos, double gastos, double total, double ultimoValor) {

        this.ganhos = ganhos;
        this.gastos = gastos;
        this.total = total;
        this.ultimoValor = ultimoValor;

    }

    public double getUltimoValor() {
        return ultimoValor;
    }

    public void setUltimoValor(double ultimoValor) {
        this.ultimoValor = ultimoValor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    public double getGanhos() {
        return ganhos;
    }

    public void setGanhos(double ganhos) {
        this.ganhos = ganhos;
    }

}

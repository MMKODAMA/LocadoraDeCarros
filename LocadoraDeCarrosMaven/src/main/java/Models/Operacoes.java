package Models;

public class Operacoes {

    private int id;
    private String tipo;
    private int carro;
    private double valor;
    private float kilometragem;
    private int tanque;
    

    public Operacoes() {
    }

    public Operacoes(int id, String tipo, int carro, double valor, float kilometragem, int tanque) {
        this.id = id;
        this.tipo = tipo;
        this.carro = carro;
        this.valor = valor;
        this.kilometragem = kilometragem;
        this.tanque = tanque;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCarro() {
        return carro;
    }

    public void setCarro(int carro) {
        this.carro = carro;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public float getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(float kilometragem) {
        this.kilometragem = kilometragem;
    }

    public int getTanque() {
        return tanque;
    }

    public void setTanque(int tanque) {
        this.tanque = tanque;
    }

    public void add(Operacoes operacoes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

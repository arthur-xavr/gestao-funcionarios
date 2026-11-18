package br.com.empresa.model;

public class Caixa extends Funcionario {
    private int numeroGuiche;

    public Caixa(String nome, double salarioBase, int numeroGuiche) {
        super(nome, salarioBase);
        this.numeroGuiche = numeroGuiche;
    }

    public Caixa(int id, String nome, double salarioBase, int numeroGuiche) {
        super(id, nome, salarioBase);
        this.numeroGuiche = numeroGuiche;
    }

    @Override
    public double calcularSalarioFinal() {
        return salarioBase * 1.10;
    }

    @Override
    public String getCargo() { return "Caixa"; }

    public int getNumeroGuiche() { return numeroGuiche; }
}
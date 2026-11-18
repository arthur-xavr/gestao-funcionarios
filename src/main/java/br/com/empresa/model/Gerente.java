package br.com.empresa.model;

public class Gerente extends Funcionario {
    private double bonusExtra;

    public Gerente(String nome, double salarioBase, double bonusExtra) {
        super(nome, salarioBase);
        this.bonusExtra = bonusExtra;
    }

    public Gerente(int id, String nome, double salarioBase, double bonusExtra) {
        super(id, nome, salarioBase);
        this.bonusExtra = bonusExtra;
    }

    @Override
    public double calcularSalarioFinal() {
        return salarioBase * 1.20 + bonusExtra;
    }

    @Override
    public String getCargo() { return "Gerente"; }

    public double getBonusExtra() { return bonusExtra; }
}
package br.com.empresa.model;

public abstract class Funcionario {
    protected int id;
    protected String nome;
    protected double salarioBase;

    public Funcionario(String nome, double salarioBase) {
        this.nome = nome;
        this.salarioBase = salarioBase;
    }

    public Funcionario(int id, String nome, double salarioBase) {
        this(nome, salarioBase);
        this.id = id;
    }

    public abstract double calcularSalarioFinal();
    public abstract String getCargo();

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getSalarioBase() { return salarioBase; }

    @Override
    public String toString() {
        return String.format("[%d] %s | Cargo: %s | Salário Final: R$ %.2f",
                id, nome, getCargo(), calcularSalarioFinal());
    }
}
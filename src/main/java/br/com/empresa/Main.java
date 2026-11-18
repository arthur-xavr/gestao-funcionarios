package br.com.empresa;

import java.util.Scanner;

import br.com.empresa.dao.FuncionarioDAO;
import br.com.empresa.model.Caixa;
import br.com.empresa.model.Gerente;

public class Main {
    public static void main(String[] args) {
        FuncionarioDAO dao = new FuncionarioDAO();
        dao.inicializarBanco();
        Scanner sc = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\n===== GESTÃO DE FUNCIONÁRIOS =====");
            System.out.println("1 - Cadastrar Gerente");
            System.out.println("2 - Cadastrar Caixa");
            System.out.println("3 - Listar Funcionários");
            System.out.println("4 - Excluir Funcionário");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Salário base: ");
                    double sal = Double.parseDouble(sc.nextLine());
                    System.out.print("Bônus extra: ");
                    double extra = Double.parseDouble(sc.nextLine());
                    dao.inserir(new Gerente(nome, sal, extra));
                    System.out.println("Gerente cadastrado!");
                }
                case 2 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Salário base: ");
                    double sal = Double.parseDouble(sc.nextLine());
                    System.out.print("Nº do guichê: ");
                    int g = Integer.parseInt(sc.nextLine());
                    dao.inserir(new Caixa(nome, sal, g));
                    System.out.println("Caixa cadastrado!");
                }
                case 3 -> {
                    var lista = dao.listarTodos();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado.");
                    } else {
                        lista.forEach(System.out::println);
                    }
                }
                case 4 -> {
                    System.out.print("ID a excluir: ");
                    int id = Integer.parseInt(sc.nextLine());
                    dao.excluir(id);
                    System.out.println("Excluído.");
                }
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        sc.close();
    }
}

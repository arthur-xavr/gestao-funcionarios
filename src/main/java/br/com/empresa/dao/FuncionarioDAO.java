package br.com.empresa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.empresa.model.Caixa;
import br.com.empresa.model.Funcionario;
import br.com.empresa.model.Gerente;

public class FuncionarioDAO {

    public void inicializarBanco() {
        String sql = """
            CREATE TABLE IF NOT EXISTS funcionarios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                salario_base REAL NOT NULL,
                cargo TEXT NOT NULL,
                bonus_extra REAL DEFAULT 0,
                numero_guiche INTEGER DEFAULT 0
            );
        """;
        try (Connection conn = ConnectionFactory.getConnection();
             Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela: " + e.getMessage(), e);
        }
    }

    public void inserir(Funcionario f) {
        String sql = "INSERT INTO funcionarios (nome, salario_base, cargo, bonus_extra, numero_guiche) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getNome());
            ps.setDouble(2, f.getSalarioBase());
            ps.setString(3, f.getCargo().toUpperCase());

            if (f instanceof Gerente g) {
                ps.setDouble(4, g.getBonusExtra());
                ps.setInt(5, 0);
            } else if (f instanceof Caixa c) {
                ps.setDouble(4, 0);
                ps.setInt(5, c.getNumeroGuiche());
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir: " + e.getMessage(), e);
        }
    }

    public List<Funcionario> listarTodos() {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios ORDER BY id";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double salario = rs.getDouble("salario_base");
                String cargo = rs.getString("cargo");

                if ("GERENTE".equalsIgnoreCase(cargo)) {
                    lista.add(new Gerente(id, nome, salario, rs.getDouble("bonus_extra")));
                } else {
                    lista.add(new Caixa(id, nome, salario, rs.getInt("numero_guiche")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar: " + e.getMessage(), e);
        }
        return lista;
    }

    public void excluir(int id) {
        String sql = "DELETE FROM funcionarios WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir: " + e.getMessage(), e);
        }
    }
}

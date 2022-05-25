/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Usuario;

/**
 *
 * @author Enrico
 */
public class UsuarioDAO {

    public void create(Usuario u) {
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement st = null;

        try {

            st = c.prepareStatement("INSERT INTO usuario (nome,senha,pontuacao) VALUES (?,?,?)");
            st.setString(1, u.getNome());
            st.setString(2, u.getSenha());
            st.setInt(3, u.getPontuacao());

            st.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuário criado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e);
        } finally {
            ConnectionFactory.closeConnection(c);
        }
    }

    public List<Usuario> read() {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario u = new Usuario();

                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setPontuacao(rs.getInt("pontuacao"));
                u.setStatusAdmin(rs.getInt("statusAdmin") == 1);

                usuarios.add(u);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

        return usuarios;
    }

    public List<Usuario> readWithoutAdmin() {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE statusAdmin = 0");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario u = new Usuario();

                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setPontuacao(rs.getInt("pontuacao"));
                u.setStatusAdmin(rs.getInt("statusAdmin") == 1);

                usuarios.add(u);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

        return usuarios;
    }

    public void update(Usuario u) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE usuario SET nome = ? ,senha = ?,pontuacao = ? WHERE id = ?");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getSenha());
            stmt.setInt(3, u.getPontuacao());
            stmt.setInt(4, u.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

    }

    public void delete(Usuario u) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM usuario WHERE id = ?");
            stmt.setInt(1, u.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

    }

    public boolean loginCheck(String nome, String senha) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE nome = ? and senha = ?");
            stmt.setString(1, nome);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fazer login: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return check;
    }

    public boolean adminLoginCheck(String nome, String senha) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {
            stmt = con.prepareStatement("SELECT statusAdmin FROM usuario WHERE nome = ? and senha = ?");
            stmt.setString(1, nome);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            if (rs.next()) {
                if (rs.getBoolean("statusAdmin")) {
                    check = true;
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fazer login: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return check;
    }

    public int readPontuacao(String nome) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        int pontuacao = 0;

        try {
            stmt = con.prepareStatement("SELECT pontuacao FROM usuario WHERE nome = ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();

            if (rs.next()) {

                pontuacao = rs.getInt("pontuacao");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return pontuacao;
    }

    public void updatePontuacao(String nome, int pontuacao) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE usuario SET pontuacao = ? WHERE nome = ?");
            stmt.setInt(1, pontuacao);
            stmt.setString(2, nome);

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

    }
}

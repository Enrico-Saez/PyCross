/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            
            st = c.prepareStatement("INSERT INTO usuario (nome,senha) VALUES (?,?)");
            st.setString(1, u.getNome());
            st.setString(2, u.getSenha());
            
            st.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuário criado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e);
        } finally {
            ConnectionFactory.closeConnection(c);
        }
    }
}

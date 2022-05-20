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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public List<Usuario> listar_ranking(){
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        
        List<Usuario> usuarios = new ArrayList<>();
        try{
            st = c.prepareStatement("SELECT * FROM usuario");
            rs = st.executeQuery();
            
            while (rs.next()){
                
                Usuario usuario = new Usuario();
                
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPontuacao(rs.getInt("pontuacao"));
                usuario.setStatusAdmin(rs.getBoolean("statusAdmin"));
                usuarios.add(usuario);
                
            }
        }catch(SQLException ex){
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{    
            ConnectionFactory.closeConnection(c);
        }
        return usuarios;
    }
}

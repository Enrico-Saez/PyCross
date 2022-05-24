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
import model.bean.Palavra;

/**
 *
 * @author Enrico
 */
public class PalavraDAO {

    public List<Palavra> readPalavrasFase1() {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Palavra> palavras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM palavra WHERE fase = 1");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Palavra p = new Palavra();

                p.setId(rs.getInt("id"));
                p.setResposta(rs.getString("resposta"));
                p.setDica(rs.getString("dica"));
                p.setFase(rs.getInt("fase"));
                palavras.add(p);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

        return palavras;
    }

    public List<Palavra> readPalavrasFase2() {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Palavra> palavras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM palavra WHERE fase = 2");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Palavra p = new Palavra();

                p.setId(rs.getInt("id"));
                p.setResposta(rs.getString("resposta"));
                p.setDica(rs.getString("dica"));
                p.setFase(rs.getInt("fase"));
                palavras.add(p);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

        return palavras;
    }

    public List<Palavra> readPalavrasFase3() {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Palavra> palavras = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM palavra WHERE fase = 3");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Palavra p = new Palavra();

                p.setId(rs.getInt("id"));
                p.setResposta(rs.getString("resposta"));
                p.setDica(rs.getString("dica"));
                p.setFase(rs.getInt("fase"));
                palavras.add(p);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

        return palavras;
    }

    public boolean verificarRespostaFase1(String respostaFinal) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean check = false;

        try {
            stmt = con.prepareStatement("SELECT resposta FROM palavra WHERE fase = 1");
            rs = stmt.executeQuery();

            while (rs.next()) {
                    if (respostaFinal.equals(rs.getString("resposta"))) {
                        check = true;
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

        return check;
    }
    
    public boolean verificarRespostaFase2(String respostaFinal) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean check = false;

        try {
            stmt = con.prepareStatement("SELECT resposta FROM palavra WHERE fase = 2");
            rs = stmt.executeQuery();

            while (rs.next()) {
                    if (respostaFinal.equals(rs.getString("resposta"))) {
                        check = true;
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

        return check;
    }
    
    public boolean verificarRespostaFase3(String respostaFinal) {

        Connection con = ConnectionFactory.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        boolean check = false;

        try {
            stmt = con.prepareStatement("SELECT resposta FROM palavra WHERE fase = 3");
            rs = stmt.executeQuery();

            while (rs.next()) {
                    if (respostaFinal.equals(rs.getString("resposta"))) {
                        check = true;
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

        return check;
    }
}

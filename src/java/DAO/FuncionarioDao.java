/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FuncionarioDao {
    private Connection connection;
    FuncionarioDao(){
        this.connection = new ConnectionFactory().getConnection();
    }
    public void adiciona(Funcionario funcionario)
    {
        String sql = "INSERT INTO funcionario VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stat = connection.prepareStatement(sql);
            stat.setInt(1, funcionario.getNummat());
            stat.setString(2, funcionario.getNome());
            stat.setDouble(3, funcionario.getSalario());
            String sexo = funcionario.getSexo() + "";
            stat.setString(4, sexo);
            stat.setInt(5, funcionario.getNdepto());
            stat.setInt(6, funcionario.getNsuper());
            stat.executeUpdate();
            stat.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public ArrayList getLista()
    {
        ArrayList<Funcionario> funcs = new ArrayList<>();
        String sql = "SELECT * FROM funcionario";
        try {
            PreparedStatement stat = connection.prepareStatement(sql);
            ResultSet res = stat.executeQuery();
            while(res.next()){
                Funcionario f = new Funcionario();
                f.setNummat(res.getInt("NUMMAT"));
                f.setNome(res.getString("NOME"));
                f.setSalario(res.getDouble("SALARIO"));
                String sexo = res.getString("SEXO") + "";
                f.setSexo(sexo.charAt(0));
                f.setNdepto(res.getInt("NDEPTO"));
                f.setNsuper(res.getInt("NSUPER"));
                
                funcs.add(f);
            }
            res.close();
            stat.close();
            return funcs;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Funcionario consulta(int numat){
  String sql = "SELECT * from funcionario numat_funcionario = ?";
  Funcionario funcionario = null;

 try{
     PreparedStatement stmt = connection.prepareStatement(sql);
     stmt.setInt(1, numat);
     ResultSet rs = stmt.executeQuery();

    if(rs.next()){
                Funcionario f = new Funcionario();
                f.setNummat(rs.getInt("NUMMAT"));
                f.setNome(rs.getString("NOME"));
                f.setSalario(rs.getDouble("SALARIO"));
                String sexo = rs.getString("SEXO") + "";
                f.setSexo(sexo.charAt(0));
                f.setNdepto(rs.getInt("NDEPTO"));
                f.setNsuper(rs.getInt("NSUPER"));
    
    }
    rs.close();
    stmt.close();
    } catch (SQLException e) {
       throw new RuntimeException(e);
    }
    return funcionario;
 }
}


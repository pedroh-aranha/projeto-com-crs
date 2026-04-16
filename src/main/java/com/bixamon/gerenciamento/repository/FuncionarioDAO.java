/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bixamon.gerenciamento.repository;

import com.bixamon.gerenciamento.model.FuncionarioBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public class FuncionarioDAO {
    public List<FuncionarioBean> lerTodos(){
        List<FuncionarioBean> dados = new ArrayList();
            try{
                Connection conn = Conexao.conectar();
                PreparedStatement stmt = null;
                ResultSet rs = null;
                
                stmt = conn.prepareStatement("select * from funcionario");
                rs = stmt.executeQuery();
                
                while(rs.next()){
                    FuncionarioBean funcionario = new FuncionarioBean();
                    funcionario.setId(rs.getInt("id"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setCargo(rs.getString("cargo"));
                    funcionario.setDepartamento(rs.getString("departamento"));
                    funcionario.setEmail(rs.getString("email"));
                    funcionario.setDataContratacao(rs.getDate("data_contratacao"));
                    
                    dados.add(funcionario);
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        return dados;
    }
    public FuncionarioBean buscaPorId(int id) {
        FuncionarioBean funcionario = new FuncionarioBean();
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;

            stmt = conn.prepareStatement("select * from funcionario where id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                funcionario.setId(rs.getInt("id"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setDepartamento(rs.getString("departamento"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setDataContratacao(rs.getDate("data_contratacao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return funcionario;
    }
    public void editar(FuncionarioBean funcionario) {
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;

            stmt = conn.prepareStatement("update funcionario set nome = ?, cargo = ?, departamento = ?, email = ?, data_contratacao = ? where id = ?");

            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCargo());
            stmt.setString(3, funcionario.getDepartamento());
            stmt.setString(4, funcionario.getEmail());
            stmt.setDate(5, funcionario.getDataContratacao());
            stmt.setInt(6, funcionario.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public void addFuncionario(FuncionarioBean funcionario) {
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO funcionario (nome, cargo, departamento, email, dataContratacao) VALUES (?, ?, ?, ?, ?)"
            );
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getCargo());
            stmt.setString(3, funcionario.getDepartamento());
            stmt.setString(4, funcionario.getEmail());
            stmt.setDate(5, funcionario.getDataContratacao());
            stmt.executeUpdate();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

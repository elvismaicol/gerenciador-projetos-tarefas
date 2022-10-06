/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author Elvis
 */
public class ProjectController {

    public void save(Project project) {

        String sql = "INSERT INTO projects(name, description, createdAt, updateAt) VALUES(?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Estabelecendo a conexão com o Banco de Dados            
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query            
            statement = (PreparedStatement) connection.prepareStatement(sql);
            
            //Setando os valores do statement            
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdateAt().getTime()));
            
            //Executando a query            
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar o projeto");
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Project project) {

        String sql = "UPDATE projects SET "
                + "name = ?, "
                + "description = ?, "
                + "createdAt = ?, "
                + "updateAt = ? "
                + "WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Estabelecendo a conexão com o Banco de Dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = (PreparedStatement) connection.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdateAt().getTime()));
            statement.setInt(5, project.getId());
            
            //Executando a query
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o projeto" + e.getMessage(), e);
        }

    }

    public void removeById(int idProject) {

        String sql = "DELETE FROM projects WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Estabelecendo a conexão com o Banco de Dados
            connection = ConnectionFactory.getConnection();

            //Preparando a query
            statement = (PreparedStatement) connection.prepareStatement(sql);

            //Setando os valores do statement
            statement.setInt(1, idProject);
                        
            //Executando a query            
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar o projeto" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Project> getAll() {

        String sql = "SELECT * FROM projects";

        Connection connection = null;
        PreparedStatement statement = null;
        
        //Recuperar os dados do banco de dados
        ResultSet resultSet = null;
        
        List<Project> projects = new ArrayList<>();
        
        try {
            //Estabelecendo a conexão com o Banco de Dados            
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query            
            statement = (PreparedStatement) connection.prepareStatement(sql);
            
            //Valor retornado pela execução da query
            resultSet = statement.executeQuery();
            
            //Percorre a lista enquanto houver valores
            while(resultSet.next()){
                
                Project project = new Project();
                
                project.setId(resultSet.getInt("id"));
                project.setName(resultSet.getString("name"));                
                project.setDescription(resultSet.getString("description"));
                project.setCreatedAt(resultSet.getDate("createdAt"));
                project.setUpdateAt(resultSet.getDate("updateAt"));
                projects.add(project);
            }    
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar os projetos" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        //Lista de tarefas que foi criada e carregada do BD
        return projects;
    }
}

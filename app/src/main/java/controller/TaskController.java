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
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author Elvis
 */
public class TaskController {

    public void save(Task task) {

        String sql = "INSERT INTO tasks(idProject, name, description, notes, deadline, completed, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Estabelecendo a conexão com o Banco de Dados            
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query            
            statement = (PreparedStatement) connection.prepareStatement(sql);
            
            //Setando os valores do statement            
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setDate(5, new java.sql.Date(task.getDeadline().getTime()));
            statement.setBoolean(6, task.getIsCompleted());
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            
            //Executando a query            
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a tarefa" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Task task) {

        String sql = "UPDATE tasks SET idProject = ?, name = ?, description = ?, notes = ?, deadline = ?, completed = ?, createdAt = ?, updatedAt = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Estabelecendo a conexão com o Banco de Dados
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query
            statement = (PreparedStatement) connection.prepareStatement(sql);
            
            //Setando os valores do statement
            statement.setInt    (1, task.getIdProject());
            statement.setString (2, task.getName());
            statement.setString (3, task.getDescription());
            statement.setString (4, task.getNotes());
            statement.setDate   (5, new java.sql.Date(task.getDeadline().getTime()));
            statement.setBoolean(6, task.getIsCompleted());
            statement.setDate   (7, new Date(task.getCreatedAt().getTime()));
            statement.setDate   (8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt    (9, task.getId());
            
            //Executando a query
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a tarefa" + e.getMessage(), e);
        }

    }

    public void removeById(int taskId) {

        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //Estabelecendo a conexão com o Banco de Dados
            connection = ConnectionFactory.getConnection();

            //Preparando a query
            statement = (PreparedStatement) connection.prepareStatement(sql);

            //Setando os valores do statement
            statement.setInt(1, taskId);
            
            //Executando a query            
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar a tarefa" + e.getMessage(), e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<Task> getAll(int idProject) {

        String sql = "SELECT * FROM tasks WHERE idProject = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        List<Task> tasks = new ArrayList<>();
        
        try {
            //Estabelecendo a conexão com o Banco de Dados            
            connection = ConnectionFactory.getConnection();
            
            //Preparando a query            
            statement = (PreparedStatement) connection.prepareStatement(sql);
            
            //Setando o valor que corresponde ao filtro de busca
            statement.setInt(1, idProject);
            
            //Valor retornado pela execução da query
            resultSet = statement.executeQuery();
            
            //Percorre a lista enquanto houver valores
            while(resultSet.next()){
                
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));
                tasks.add(task);
            }    
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir a tarefa", e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        //Lista de tarefas que foi criada e carregada do BD
        return tasks;
    }
}

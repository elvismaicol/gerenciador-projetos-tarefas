/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controller.ProjectController;
import controller.TaskController;
import java.util.Date;
import java.util.List;
import model.Project;
import model.Task;

/**
 *
 * @author Elvis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//    ProjectController projectController = new ProjectController();
//    
//    Project project = new Project();
//    project.setName("Projeto teste");
//    project.setDescription("description");
//    projectController.save(project);

    //Testando Update
//    ProjectController projectController = new ProjectController();   
//    Project project = new Project(); 
//    project.setId(1);
//    project.setName("Novo nome projeto");
//    project.setDescription("description do projeto auterado");    
//    projectController.update(project);
//    
//    List<Project> projects = projectController.getAll();
//    System.out.println("Total de Projetos: " + projects.size());
    
//    projectController.removeById(1);
      
    TaskController  taskController = new TaskController();
    
    Task task = new Task();
    
//    task.setIdProject(10);
//    task.setName("Projeto 3 task");
//    task.setDescription("Testando a inserção de uma task 3");
//    task.setNotes("Nenhuma");
//    task.setIsCompleted(false);
//    task.setDeadline(new Date());
//    
    taskController.save(task);
//        
//    task.setName("Alterar telas da aplicação");
//    taskController.update(task);
//    List<Task> tasks = taskController.getAll(2);
//    System.out.println("Total de tarefas = " + tasks.size());     
    }
}

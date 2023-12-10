/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.todoList.controllers;


import org.springframework.ui.Model;
import com.example.todoList.models.Tarefa;
import com.example.todoList.services.TarefaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author juanc
 */
@Controller
public class TarefaFormController {
    @Autowired
    private TarefaService tarefaService;
    
    
    @GetMapping("/criarTarefa")
    public String mostrarCriadorForm(Tarefa tarefa){
        return "novaTarefa";
    }
    
    @PostMapping("/tarefa")
    public String criarTarefa(@Valid Tarefa tarefa, BindingResult result, Model model){
        Tarefa item = new Tarefa();
        item.setTitulo(tarefa.getTitulo());
        item.setIsCompleto(tarefa.getIsCompleto());
        
        tarefaService.save(tarefa);
        return "redirect:/";
    }
    
    @GetMapping("/deletar/{id}")
    public String deletarTarefa(@PathVariable("id")Long id, Model model){
        Tarefa tarefa = tarefaService.getPorId(id).orElseThrow(()-> new IllegalArgumentException("Tarefa id: "+ id + " não encontrado"));
        tarefaService.delete(tarefa);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{id}")
    public String editarTarefa(@PathVariable("id") Long id,Model model){
        Tarefa tarefa = tarefaService.getPorId(id).orElseThrow(()-> new IllegalArgumentException("Tarefa id: "+ id + " não encontrado"));
        model.addAttribute("tarefa",tarefa);
        return "editarTarefa";
    }
    
    @PostMapping("/tarefa/{id}")
    public String atualizarTarefa(@PathVariable("id")Long id,@Valid Tarefa tarefa,BindingResult result,Model model){
        Tarefa item = tarefaService.getPorId(id).orElseThrow(()-> new IllegalArgumentException("Tarefa id: "+ id + " não encontrado"));
        item.setIsCompleto(tarefa.getIsCompleto());
        item.setTitulo(tarefa.getTitulo());
        
        tarefaService.save(item);
        
        return "redirect:/";
    }
}

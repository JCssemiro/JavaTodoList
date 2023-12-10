/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.todoList.services;

import com.example.todoList.models.Tarefa;
import com.example.todoList.repositories.TarefaRepository;
import java.time.Instant;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author juanc
 */

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;
    
    public Iterable<Tarefa> getAll(){
        return tarefaRepository.findAll();
    }
    
    public Optional<Tarefa> getPorId(Long id){
        return tarefaRepository.findById(id);
    }
    
    public Tarefa save(Tarefa tarefa){
        if(tarefa.getId() == null){
            tarefa.setCriadoEm(Instant.now());
        }
        tarefa.setAtualizadoEm(Instant.now());
        return tarefaRepository.save(tarefa);
    }

    public void delete(Tarefa tarefa){
        tarefaRepository.delete(tarefa);
    }

}
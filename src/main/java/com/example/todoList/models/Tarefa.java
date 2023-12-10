/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.todoList.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Getter
@Setter
@Entity
@Table(name="tarefas")
public class Tarefa implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long Id;
    
    private String titulo;
    
    private Boolean isCompleto;
    
    private Instant criadoEm;
    
    private Instant atualizadoEm;

    @Override
    public String toString(){
        return String.format("Tarefa{id=%d, titulo='%s', isCompleto='%s', criadoEm='%s', atualizadoEm='%s'}",id,titulo,isCompleto,criadoEm,atualizadoEm);
    }
}

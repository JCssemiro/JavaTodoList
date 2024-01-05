package com.example.todoList.controllers;

import com.example.todoList.models.Tarefa;
import com.example.todoList.services.TarefaService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarefaFormControllerTest {

    @InjectMocks
    private TarefaFormController tarefaFormController;

    @Mock
    private TarefaService tarefaService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;


    @Test
    void DeveriaMostrarCriadorForm() {
        Tarefa tarefa = new Tarefa();
        String viewName = tarefaFormController.mostrarCriadorForm(tarefa);
        assertEquals("novaTarefa", viewName);
    }

    @Test
    void DeveriaCriarTarefaERedirecionarHome() {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Tarefa de teste");
        when(tarefaService.save(tarefa)).thenReturn(tarefa);

        String viewName = tarefaFormController.criarTarefa(tarefa, bindingResult, model);

        verify(tarefaService, times(1)).save(tarefa);
        assertEquals("redirect:/", viewName);
    }

    @Test
    void DeveriaDeletarTarefaERedirecionarHome() {
        Long id = 1L;
        Tarefa tarefa = new Tarefa();
        when(tarefaService.getPorId(id)).thenReturn(Optional.of(tarefa));

        String viewName = tarefaFormController.deletarTarefa(id, model);

        verify(tarefaService, times(1)).delete(tarefa);
        assertEquals("redirect:/", viewName);
    }

    @Test
    void DeveriaEditarTarefaERedirecionarHome() {
        Long id = 1L;
        Tarefa tarefa = new Tarefa();
        when(tarefaService.getPorId(id)).thenReturn(Optional.of(tarefa));

        String viewName = tarefaFormController.editarTarefa(id, model);

        verify(model, times(1)).addAttribute("tarefa", tarefa);
        assertEquals("editarTarefa", viewName);
    }

    @Test
    void DeveriaAtualizarTarefaERedirecionarHome() {
        Long id = 1L;
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo("Tarefa de teste");
        when(tarefaService.getPorId(id)).thenReturn(Optional.of(tarefa));

        String viewName = tarefaFormController.atualizarTarefa(id, tarefa, bindingResult, model);

        verify(tarefaService, times(1)).save(tarefa);
        assertEquals("redirect:/", viewName);
    }
}
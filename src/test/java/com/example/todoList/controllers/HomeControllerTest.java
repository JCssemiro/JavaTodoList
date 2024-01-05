package com.example.todoList.controllers;

import com.example.todoList.models.Tarefa;
import com.example.todoList.services.TarefaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Mock
    private TarefaService tarefaService;

    @Test
    void DeveriaRetornarViewIndexETarefas() {
        //ARRANGE
        Tarefa tarefa1 = new Tarefa("Tarefa 1");
        Tarefa tarefa2 = new Tarefa("Tarefa 2");
        List<Tarefa> tarefas = Arrays.asList(tarefa1, tarefa2);
        when(tarefaService.getAll()).thenReturn(tarefas);

        //ACT
        ModelAndView modelAndView = homeController.index();

        //ASSERT
        assertEquals("index", modelAndView.getViewName());
        List<Tarefa> tarefasNoModel = (List<Tarefa>) modelAndView.getModel().get("tarefas");
        assertEquals(tarefas, tarefasNoModel);
    }

}
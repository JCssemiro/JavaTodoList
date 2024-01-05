package com.example.todoList.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import com.example.todoList.models.Tarefa;
import com.example.todoList.repositories.TarefaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    @Test
    void getAll_DeveRetornarListaDeTarefas() {
        // Arrange
        Tarefa tarefa1 = new Tarefa(1L, "Tarefa 1");
        Tarefa tarefa2 = new Tarefa(2L, "Tarefa 2");
        when(tarefaRepository.findAll()).thenReturn(List.of(tarefa1, tarefa2));

        // Act
        Iterable<Tarefa> result = tarefaService.getAll();
        long qtdElementos = StreamSupport.stream(result.spliterator(), false)
                .count();
        // Assert
        assertEquals(2,qtdElementos);
        assertNotNull(result);
    }

    @Test
    void getPorIdExistenteDeveRetornarTarefa() {
        // Arrange
        Tarefa tarefa = new Tarefa(1L, "Tarefa teste");
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));

        // Act
        Optional<Tarefa> result = tarefaService.getPorId(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(tarefa, result.get());
    }

    @Test
    void getPorIdNaoExistenteDeveRetornarVazio() {
        // Arrange
        when(tarefaRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Tarefa> result = tarefaService.getPorId(1L);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void saveNovaTarefaDeveConfigurarDatasEChamarSaveRepository() {
        // Arrange
        Tarefa novaTarefa = new Tarefa("Nova Tarefa");
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(novaTarefa);

        // Act
        Tarefa result = tarefaService.save(novaTarefa);

        // Assert

        assertNotNull(result.getCriadoEm());
        assertNotNull(result.getTitulo());
        assertNotNull(result.getAtualizadoEm());
        assertNull(result.getIsCompleto());
        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void save_TarefaExistente_DeveAtualizarDataEChamarSaveRepository() {
        // Arrange
        LocalDateTime localDataTime = LocalDateTime.of(2024, 1, 1, 12, 0);
        Instant instant = localDataTime.atZone(ZoneId.systemDefault()).toInstant();
        Tarefa tarefaExistente = new Tarefa(1l,"Tarefa Existente");
        tarefaExistente.setAtualizadoEm(instant);
        tarefaExistente.setCriadoEm(instant);
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefaExistente);

        // Act
        Tarefa result = tarefaService.save(tarefaExistente);

        // Assert
        assertNotNull(result.getId());
        assertNotNull(result.getCriadoEm());
        assertNotNull(result.getAtualizadoEm());
        assertNotEquals(instant,result.getAtualizadoEm());
        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void delete_DeveChamarDeleteRepository() {
        // Arrange
        Tarefa tarefa = new Tarefa(1L, "Tarefa 1");

        // Act
        tarefaService.delete(tarefa);

        // Assert
        verify(tarefaRepository, times(1)).delete(eq(tarefa));
    }
}
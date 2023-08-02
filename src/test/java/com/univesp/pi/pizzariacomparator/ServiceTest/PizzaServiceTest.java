package com.univesp.pi.pizzariacomparator.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.univesp.pi.pizzariacomparator.Exception.ResourceNotFoundException;
import com.univesp.pi.pizzariacomparator.Model.Pizza;
import com.univesp.pi.pizzariacomparator.Repository.PizzaRepository;
import com.univesp.pi.pizzariacomparator.Service.PizzaService;

@ExtendWith(MockitoExtension.class)
public class PizzaServiceTest {

    @InjectMocks
    PizzaService service;

    @Mock
    PizzaRepository repository;

    Pizza pizza;

    private UUID uuid;

    @BeforeEach
    public void setUp() {
        UUID uuid = UUID.randomUUID();
        pizza = new Pizza(uuid, "pizza 1", "pizza salgada", "primeira pizza", "aaa");
    }

    @Test
    public void buscarUmaPizzaPorId_QuandoEncontrada_DeveRetornarPizza() {
        // Configurando o mock para retornar a pizza quando findById for chamado
        when(repository.findById(uuid)).thenReturn(Optional.of(pizza));

        // Chamando o método a ser testado
        Pizza pizzaEncontrada = service.buscarPizzaPorId(pizza.getId());

        // Verificando se o resultado é o esperado
        assertEquals(pizza, pizzaEncontrada);

        // Verificando se o método findById foi chamado com o ID correto da pizza
        verify(repository).findById(pizza.getId());

        // Verificando se não há mais interações com o repositório
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void buscarUmaPizzaPorId_QuandoNaoEncontrada_DeveLancarExcecao() {
        // Configurando o mock para retornar Optional vazio quando findById for chamado
        when(repository.findById(pizza.getId())).thenReturn(Optional.empty());

        // Verificando se o método findById foi chamado com o ID correto da pizza
        assertThrows(ResourceNotFoundException.class, () -> service.buscarPizzaPorId(pizza.getId()));

        // Verificando se o método findById foi chamado com o ID correto da pizza
        verify(repository).findById(pizza.getId());

        // Verificando se não há mais interações com o repositório
        verifyNoMoreInteractions(repository);
    }
}

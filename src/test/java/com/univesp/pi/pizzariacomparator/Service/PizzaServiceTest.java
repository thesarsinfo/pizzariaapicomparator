package com.univesp.pi.pizzariacomparator.Service;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import com.univesp.pi.pizzariacomparator.DTO.Pizza.PizzaDTO;
import com.univesp.pi.pizzariacomparator.DTO.Pizza.PizzaDTOCriar;
import com.univesp.pi.pizzariacomparator.Model.Pizza;
import com.univesp.pi.pizzariacomparator.Repository.PizzaRepository;

public class PizzaServiceTest {
    private static final UUID randomUUID = UUID.randomUUID();
    private static final String nome = "calabresa";
    private static final String categoria = "salgado";
    private static final String descricao = "É uma pizza salgada";
    private static final String urlimagem = "www.java.com";
    @InjectMocks
    private PizzaService servicePizza;
    @Mock
    private PizzaRepository pizzaRepository;
    @Mock
    private ModelMapper pizzaMapper;

    private Pizza pizzaModel;

    private PizzaDTO pizzaDTO;

    private PizzaDTOCriar pizzaDTOcriar;

    private Optional<Pizza> optionalPizza;
    
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        startPizza();
    }
    @Test
    void testBuscarPizzaPorId() {
        Mockito.when(pizzaRepository.findById(randomUUID)).thenReturn(optionalPizza);

        Pizza response = servicePizza.buscarPizzaPorId(randomUUID);
        Assertions.assertNotNull(response);        
        Assertions.assertEquals(Pizza.class, response.getClass());
        Assertions.assertEquals(randomUUID, response.getId());
    }

    @Test
    void testAtualizarParcialmentePizza() {
        
    }

    @Test
    void testAtualizarPizza() {

    }

    

    @Test
    void testBuscarPizzaPorNome() {

    }

    @Test
    void testBuscarPizzaPorNomesParecidos() {

    }

    @Test
    void testBuscarTodos() {

    }

    @Test
    void testBuscarTodosPaginados() {

    }

    @Test
    void testExcluirPizza() {

    }

    @Test
    void testSalvarPizza() {

    }

    private void startPizza() {
        
        pizzaModel = new Pizza ( randomUUID, nome, categoria, descricao, urlimagem);
        pizzaDTOcriar = new PizzaDTOCriar (  nome, categoria, descricao, urlimagem);
        optionalPizza =  Optional.of(new Pizza(randomUUID, nome, categoria, descricao, urlimagem));

    }
}

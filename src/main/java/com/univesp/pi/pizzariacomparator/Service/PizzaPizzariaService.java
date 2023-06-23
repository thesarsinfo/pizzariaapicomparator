package com.univesp.pi.pizzariacomparator.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.univesp.pi.pizzariacomparator.DTO.Pizza.PizzaDTO;
import com.univesp.pi.pizzariacomparator.DTO.PizzaPizzaria.PizzaPizzariaDTOCriar;
import com.univesp.pi.pizzariacomparator.DTO.PizzaPizzaria.PizzaPizzariaDTOPut;
import com.univesp.pi.pizzariacomparator.Exception.ResourceNotFoundException;
import com.univesp.pi.pizzariacomparator.Model.Pizza;
import com.univesp.pi.pizzariacomparator.Model.PizzaPizzaria;
import com.univesp.pi.pizzariacomparator.Model.Pizzaria;
import com.univesp.pi.pizzariacomparator.Repository.PizzaPizzariaRepository;



@Service
public class PizzaPizzariaService {
    @Autowired
    private PizzaPizzariaRepository pizzaPizzariaRepository; 
    @Autowired
    private PizzaService pizzaService; 
    @Autowired
    private PizzariaService pizzariaService; 
    @Autowired
    private ModelMapper pizzaPizzariaMapper;
    

    public List<PizzaPizzaria> salvarPizzaPizzaria(PizzaPizzariaDTOCriar pizzaPizzariaDTO) { 
        Pizzaria pizzaria = pizzariaService.buscarPizzariaPorId(pizzaPizzariaDTO.getPizzaria().getId());
        List<PizzaPizzaria> pizzasSalvas = new ArrayList<>(); 
        List<PizzaDTO> pizzas = pizzaPizzariaDTO.getPizza();
        for (PizzaDTO pizza : pizzas) {
            Pizza novaPizza = pizzaService.buscarPizzaPorId(pizza.getId());
            if (novaPizza != null) {
                pizzasSalvas.add(new PizzaPizzaria( pizzaria, novaPizza, pizza.getPreco()));
            }                
        }                  
           
        return pizzaPizzariaRepository.saveAll(pizzasSalvas);        
    }
    public PizzaPizzaria buscarPizzaPizzariaPorId(UUID id){
        Optional<PizzaPizzaria> pizzaPizzaria = pizzaPizzariaRepository.findById(id);
        return pizzaPizzaria.orElseThrow(() -> new ResourceNotFoundException("PizzaPizzaria não encontrada - id: " + id));
    }

    public List<PizzaPizzaria> buscarTodos(){       
        return pizzaPizzariaRepository.findAll();
    }
    public Page<PizzaPizzaria> buscarTodosPaginados(Integer paginaPassada){      
        PageRequest pagina = PageRequest.of(paginaPassada,10); 
        return pizzaPizzariaRepository.findAll(pagina);
    }
    public List<PizzaPizzaria> compararPrecosPizza(String nomePizza){
        Pizza pizza = pizzaService.buscarPizzaPorNome(nomePizza);
        
        List<PizzaPizzaria> pizzarias = pizzaPizzariaRepository.findByPizzariasParecidas(pizza.getId());
        Collections.sort(pizzarias, Comparator.comparing(PizzaPizzaria::getPreco)); 
        return pizzarias; // retorna a lista ordenada
    }




    public PizzaPizzaria atualizarPizzaPizzaria(UUID id, PizzaPizzariaDTOPut pizzaPizzariaAtualizada) {
        pizzaService.buscarPizzaPorId(pizzaPizzariaAtualizada.getPizza().getId());
        pizzariaService.buscarPizzariaPorId(pizzaPizzariaAtualizada.getPizzaria().getId()); 
        PizzaPizzaria pizzaPizzariaAtual = buscarPizzaPizzariaPorId(id);
        pizzaPizzariaMapper.map(pizzaPizzariaAtualizada,pizzaPizzariaAtual);
        return pizzaPizzariaRepository.save(pizzaPizzariaAtual);       
    }
    public PizzaPizzaria atualizarParcialmentePizzaPizzaria(UUID id, PizzaPizzariaDTOCriar pizzaPizzariaAtualizada) {
        PizzaPizzaria pizzaPizzariaAtual = buscarPizzaPizzariaPorId(id);
        
        List<Field> fields = Arrays.asList(PizzaPizzariaDTOCriar.class.getDeclaredFields());
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                
                Object pizzaPizzariaParcialAtualizada = field.get(pizzaPizzariaAtualizada);
                if (Objects.nonNull(pizzaPizzariaParcialAtualizada)) {
                    Field pizzaPizzariaField = PizzaPizzaria.class.getDeclaredField(field.getName());
                    pizzaPizzariaField.setAccessible(true);
                    pizzaPizzariaField.set(pizzaPizzariaAtual, pizzaPizzariaParcialAtualizada);
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                // Trate a exceção de acordo com a necessidade
            }
        }
        return pizzaPizzariaRepository.save(pizzaPizzariaAtual);           
    }
    public void excluirPizzaPizzaria(UUID id) {
        PizzaPizzaria pizzaPizzariaAtual = buscarPizzaPizzariaPorId(id);
        pizzaPizzariaRepository.delete(pizzaPizzariaAtual);
    }
}

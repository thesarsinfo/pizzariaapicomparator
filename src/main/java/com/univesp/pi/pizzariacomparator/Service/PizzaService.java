package com.univesp.pi.pizzariacomparator.Service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.univesp.pi.pizzariacomparator.DTO.Pizza.PizzaDTOCriar;
import com.univesp.pi.pizzariacomparator.Exception.ResourceNotFoundException;
import com.univesp.pi.pizzariacomparator.Model.Pizza;
import com.univesp.pi.pizzariacomparator.Repository.PizzaRepository;

@Service()
public class PizzaService {
    
    @Autowired
    private PizzaRepository pizzaRepository;  
    @Autowired
    private ModelMapper pizzaMapper;

    public Pizza salvarPizza(PizzaDTOCriar pizzaDTOCriar) {  
        Pizza pizza = new Pizza();  
        pizzaMapper.map(pizzaDTOCriar, pizza);
        return pizzaRepository.save(pizza);

    }
    public Pizza buscarPizzaPorId(UUID id){
        Optional<Pizza> pizza = pizzaRepository.findById(id);
        return pizza.orElseThrow(() -> new ResourceNotFoundException("Pizza não encontrada - id: " + id));
    }

    public List<Pizza> buscarTodos(){       
        return pizzaRepository.findAll();
    }
    public List<Pizza> buscarPizzaPorNomesParecidos(String nome) {
        return pizzaRepository.findByNomesParecidos(nome);
      }

      public Pizza buscarPizzaPorNome(String nome) {
        return pizzaRepository.findByNome(nome);
      }
    public Page<Pizza> buscarTodosPaginados(Integer paginaPassada){      
        PageRequest pagina = PageRequest.of(paginaPassada,10); 
        return pizzaRepository.findAll(pagina);
    }
    public Pizza atualizarPizza(UUID id, PizzaDTOCriar pizzaAtualizada) {
        Pizza pizzaAtual = buscarPizzaPorId(id);
        pizzaMapper.map(pizzaAtualizada,pizzaAtual);
        return pizzaRepository.save(pizzaAtual);       
    }
    public Pizza atualizarParcialmentePizza(UUID id, PizzaDTOCriar pizzaAtualizada) {
        Pizza pizzaAtual = buscarPizzaPorId(id);
        
        List<Field> fields = Arrays.asList(PizzaDTOCriar.class.getDeclaredFields());
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object pizzaParcialAtualizada = field.get(pizzaAtualizada);
                if (Objects.nonNull(pizzaParcialAtualizada)) {
                    Field pizzaField = Pizza.class.getDeclaredField(field.getName());
                    pizzaField.setAccessible(true);
                    pizzaField.set(pizzaAtual, pizzaParcialAtualizada);
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                // Trate a exceção de acordo com a necessidade
            }
        }
        return pizzaRepository.save(pizzaAtual);           
    }
    public void excluirPizza(UUID id) {
        Pizza pizzaAtual = buscarPizzaPorId(id);
        pizzaRepository.delete(pizzaAtual);
    }
}

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

import com.univesp.pi.pizzariacomparator.DTO.Pizzaria.PizzariaDTOCriar;
import com.univesp.pi.pizzariacomparator.Exception.ResourceNotFoundException;
import com.univesp.pi.pizzariacomparator.Model.Pizzaria;
import com.univesp.pi.pizzariacomparator.Repository.PizzariaRepository;

@Service()
public class PizzariaService {
    
    @Autowired
    private PizzariaRepository pizzariaRepository;  
    @Autowired
    private ModelMapper pizzariaMapper;

    public Pizzaria salvarPizzaria(PizzariaDTOCriar pizzariaDTOCriar) {   
        Pizzaria pizzaria = new Pizzaria();   
        pizzariaMapper.map(pizzariaDTOCriar, pizzaria);
        return pizzariaRepository.save(pizzaria);        
    }
    public Pizzaria buscarPizzariaPorId(UUID id){
        Optional<Pizzaria> pizzaria = pizzariaRepository.findById(id);
        return pizzaria.orElseThrow(() -> new ResourceNotFoundException("Pizzaria não encontrada - id: " + id));
    }

    public List<Pizzaria> buscarTodos(){       
        return pizzariaRepository.findAll();
    }
    public Page<Pizzaria> buscarTodosPaginados(Integer paginaPassada){      
        PageRequest pagina = PageRequest.of(paginaPassada,10); 
        return pizzariaRepository.findAll(pagina);
    }
    public Pizzaria atualizarPizzaria(UUID id, PizzariaDTOCriar pizzariaAtualizada) {
        Pizzaria pizzariaAtual = buscarPizzariaPorId(id);
        pizzariaMapper.map(pizzariaAtualizada,pizzariaAtual);
        return pizzariaRepository.save(pizzariaAtual);       
    }
    public Pizzaria atualizarParcialmentePizzaria(UUID id, PizzariaDTOCriar pizzariaAtualizada) {
        Pizzaria pizzariaAtual = buscarPizzariaPorId(id);
        
        List<Field> fields = Arrays.asList(PizzariaDTOCriar.class.getDeclaredFields());
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object pizzariaParcialAtualizada = field.get(pizzariaAtualizada);
                if (Objects.nonNull(pizzariaParcialAtualizada)) {
                    Field pizzariaField = Pizzaria.class.getDeclaredField(field.getName());
                    pizzariaField.setAccessible(true);
                    pizzariaField.set(pizzariaAtual, pizzariaParcialAtualizada);
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                // Trate a exceção de acordo com a necessidade
            }
        }
        return pizzariaRepository.save(pizzariaAtual);           
    }
    public void excluirPizzaria(UUID id) {
        Pizzaria pizzariaAtual = buscarPizzariaPorId(id);
        pizzariaRepository.delete(pizzariaAtual);
    }
}



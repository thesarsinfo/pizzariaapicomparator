package com.univesp.pi.pizzariacomparator.Controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.univesp.pi.pizzariacomparator.DTO.Pizza.PizzaDTOCriar;
import com.univesp.pi.pizzariacomparator.Model.Pizza;
import com.univesp.pi.pizzariacomparator.Service.PizzaService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(value = "/v1/api/pizza")
@SecurityRequirement(name = "bearerAuth")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    
    @GetMapping
    public ResponseEntity<List<Pizza>> buscarTodasPizzas(){
        List<Pizza> listaPizzas = pizzaService.buscarTodos();
        return ResponseEntity.status(200).body(listaPizzas);        
    }

    @GetMapping("/pagina")
    public ResponseEntity<Page<Pizza>> buscarTodosPaginados(@RequestParam(defaultValue = "0") Integer pagina){

        Page<Pizza> pizzaPaginada = pizzaService.buscarTodosPaginados(pagina);
        return ResponseEntity.ok(pizzaPaginada);        
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pizza> buscarPizzaPorId(@PathVariable UUID id){
        Pizza pizza = pizzaService.buscarPizzaPorId(id);
        return ResponseEntity.ok(pizza);
    }
    @GetMapping("/buscarPorNomesParecidos")
    public ResponseEntity<List<Pizza>> buscarPizzaPorNomesParecidos(@RequestParam("nome") String nome) {
        List<Pizza> buscarNome = pizzaService.buscarPizzaPorNomesParecidos(nome);
        return ResponseEntity.ok(buscarNome);
    }

    @GetMapping("/buscarPorNome")
    public ResponseEntity<Pizza> buscarPizzaPorNome(@RequestParam("nome") String nome) {
        Pizza buscarNome = pizzaService.buscarPizzaPorNome(nome);
        return ResponseEntity.ok(buscarNome);
    }
    
    @PostMapping
    public ResponseEntity<Pizza> cadastrarPizza(@RequestBody @Valid PizzaDTOCriar pizzaDTOCriar) {
        Pizza pizza = pizzaService.salvarPizza(pizzaDTOCriar);
        return ResponseEntity.status(201).body(pizza);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> atualizarPizza(@PathVariable UUID id, @RequestBody @Valid PizzaDTOCriar pizzaDTOAtualizar) {
        Pizza pizza = pizzaService.atualizarPizza(id,pizzaDTOAtualizar);
        return ResponseEntity.status(200).body(pizza);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Pizza> atualizarParcialmentePizza(@PathVariable UUID id, @RequestBody @Valid PizzaDTOCriar pizzaDTOAtualizar) {
        
            Pizza pizza = pizzaService.atualizarParcialmentePizza(id,pizzaDTOAtualizar);
            return ResponseEntity.status(200).body(pizza);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPizza(@PathVariable UUID id) {
        
        pizzaService.excluirPizza(id);
        return ResponseEntity.ok("Pizza deletada com sucesso");
    }
}

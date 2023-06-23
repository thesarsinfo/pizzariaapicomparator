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

import com.univesp.pi.pizzariacomparator.DTO.Pizzaria.PizzariaDTOCriar;
import com.univesp.pi.pizzariacomparator.Model.Pizzaria;
import com.univesp.pi.pizzariacomparator.Service.PizzariaService;

@RestController
@RequestMapping(value = "/v1/api/pizzaria")
public class PizzariaController {
    @Autowired
    private PizzariaService pizzariaService;

    
    @GetMapping
    public ResponseEntity<List<Pizzaria>> buscarTodasPizzarias(){
        List<Pizzaria> listaPizzarias = pizzariaService.buscarTodos();
        return ResponseEntity.status(200).body(listaPizzarias);        
    }

    @GetMapping("/pagina")
    public ResponseEntity<Page<Pizzaria>> buscarTodosPaginados(@RequestParam(defaultValue = "0") Integer pagina){

        Page<Pizzaria> pizzariaPaginada = pizzariaService.buscarTodosPaginados(pagina);
        return ResponseEntity.ok(pizzariaPaginada);        
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pizzaria> buscarPizzariaPorId(@PathVariable UUID id){
        Pizzaria pizzaria = pizzariaService.buscarPizzariaPorId(id);
        return ResponseEntity.ok(pizzaria);
    }
    
    @PostMapping
    public ResponseEntity<Pizzaria> cadastrarPizzaria(@RequestBody @Valid PizzariaDTOCriar pizzariaDTOCriar) {
        Pizzaria pizzaria = pizzariaService.salvarPizzaria(pizzariaDTOCriar);
        return ResponseEntity.status(201).body(pizzaria);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Pizzaria> atualizarPizzaria(@PathVariable UUID id, @RequestBody @Valid PizzariaDTOCriar pizzariaDTOAtualizar) {
        Pizzaria pizzaria = pizzariaService.atualizarPizzaria(id,pizzariaDTOAtualizar);
        return ResponseEntity.status(200).body(pizzaria);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Pizzaria> atualizarParcialmentePizzaria(@PathVariable UUID id, @RequestBody @Valid PizzariaDTOCriar pizzariaDTOAtualizar) {
        
            Pizzaria pizzaria = pizzariaService.atualizarParcialmentePizzaria(id,pizzariaDTOAtualizar);
            return ResponseEntity.status(200).body(pizzaria);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirPizzaria(@PathVariable UUID id) {
        
        pizzariaService.excluirPizzaria(id);
        return ResponseEntity.ok("Pizzaria deletada com sucesso");
    }
}

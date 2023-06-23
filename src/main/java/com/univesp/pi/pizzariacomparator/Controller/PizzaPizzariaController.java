package com.univesp.pi.pizzariacomparator.Controller;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

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

import com.univesp.pi.pizzariacomparator.DTO.PizzaPizzaria.PizzaPizzariaDTOCriar;
import com.univesp.pi.pizzariacomparator.DTO.PizzaPizzaria.PizzaPizzariaDTOPut;
import com.univesp.pi.pizzariacomparator.Model.PizzaPizzaria;
import com.univesp.pi.pizzariacomparator.Service.PizzaPizzariaService;


@RestController
@RequestMapping(value = "/v1/api/pizzaPizzaria")
public class PizzaPizzariaController {

    @Autowired
    private PizzaPizzariaService pizzaPizzariariaService;
    

    @GetMapping
    public ResponseEntity<List<PizzaPizzaria>> buscarTodasPizzaPizzarias() {
        List<PizzaPizzaria> listaPizzaPizzarias = pizzaPizzariariaService.buscarTodos();
        return ResponseEntity.ok(listaPizzaPizzarias);
    }

    @GetMapping("/buscarPorPaginas")
    public ResponseEntity<Page<PizzaPizzaria>> buscarPizzaPizzariaPaginado (@RequestParam(defaultValue = "0") Integer numeroPaginas) {
        Page<PizzaPizzaria> paginas = pizzaPizzariariaService.buscarTodosPaginados(numeroPaginas);
        return ResponseEntity.ok(paginas);
        }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaPizzaria> buscarPizzaPizzariaPorId(@PathVariable UUID id) {
        PizzaPizzaria pizzaPizzaPizzariaria = pizzaPizzariariaService.buscarPizzaPizzariaPorId(id);
            return ResponseEntity.ok(pizzaPizzaPizzariaria);
        }
    @GetMapping("/compararprecos")
    public ResponseEntity<List<PizzaPizzaria>> compararPizzarias(@RequestParam("nome") String nome) {
        List<PizzaPizzaria> buscarPizzarias =  pizzaPizzariariaService.compararPrecosPizza(nome);
        return ResponseEntity.ok(buscarPizzarias);
    }

    @PostMapping
    public ResponseEntity<List<PizzaPizzaria>> salvarPizzaPizzaria (@RequestBody @Valid PizzaPizzariaDTOCriar pizzaPizzaPizzariariaDTO) {

        return ResponseEntity.status(201).body(pizzaPizzariariaService.salvarPizzaPizzaria(pizzaPizzaPizzariariaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atulizarPizzaPizzaria(@PathVariable UUID id, @RequestBody PizzaPizzariaDTOPut atualizar) {
        pizzaPizzariariaService.atualizarPizzaPizzaria(id, atualizar);
        return ResponseEntity.ok("Dados atualizados com sucesso");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atulizarPizzaPizzariaPatch(@PathVariable UUID id, @RequestBody PizzaPizzariaDTOCriar atualizarPatch) {
        pizzaPizzariariaService.atualizarParcialmentePizzaPizzaria(id, atualizarPatch);
        return ResponseEntity.ok("Dados atualizados com sucesso");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPizzaPizzaria(@PathVariable UUID id) {
        pizzaPizzariariaService.excluirPizzaPizzaria(id);
        return ResponseEntity.ok("PizzaPizzaria excluída com sucesso");
    }
}
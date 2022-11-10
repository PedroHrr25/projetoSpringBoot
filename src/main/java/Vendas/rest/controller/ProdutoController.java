package Vendas.rest.controller;


import Vendas.domain.entity.Produto;
import Vendas.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    private Produtos repository;

    public ProdutoController(Produtos repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto save(@RequestBody Produto produto) {
        return repository.save(produto);
    }

    @PutMapping("{id}")
    @ResponseStatus(CREATED)
    public void update(@PathVariable Integer id, @RequestBody Produto produto) {
        repository
                .findById(id)
                .map(p -> {
                    produto.setId(p.getId());
                    repository.save(produto);
                    return produto;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "produto não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete (@PathVariable Integer id, @RequestBody Produto produto) {
        repository
                .findById(id)
                .map(p -> {
                    repository.delete(p);
                    return Void.TYPE;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "produto não encontrado"));
    }

    @GetMapping("{id}")
    @ResponseStatus
    public Produto getById(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(NOT_FOUND,"Produto não encontrado"));
    }

    @GetMapping
    public List<Produto> find(Produto filtro ){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return repository.findAll(example);
    }

}

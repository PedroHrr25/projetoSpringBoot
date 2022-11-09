package Vendas.domain.controller;


import Vendas.domain.entity.Cliente;
import Vendas.domain.repository.Clientes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    //    @RequestMapping(
//            value = {"/api/clientes/hello/{nome}", "/api/hello"},
//            method = RequestMethod.POST,
//            consumes = { "application/json", "application/xml" },
//            produces = { "application/json", "application/xml" }
//
    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity delete (@PathVariable Integer id){
        Optional<Cliente> cliente = clientes.findById(id);

        if (cliente.isPresent()) {
            clientes.delete( cliente.get());

        }
// nocontent não precisa retornar nada
        return ResponseEntity.noContent().build();

    }
// responsebody = saida
    // request = entrada
    @PutMapping("api/clientes")
    @ResponseBody
    public ResponseEntity update (@RequestBody Cliente cliente,
                                   @PathVariable  Integer id) {

        return     clientes
                  .findById(id)
                  .map(clientesExistente -> {
                      cliente.setId(clientesExistente.getId());
                      clientes.save(cliente);
                      return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());


    }

}

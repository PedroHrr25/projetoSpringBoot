package Vendas.rest.controller;


import Vendas.domain.entity.ItemPedido;
import Vendas.domain.entity.Pedido;
import Vendas.rest.dto.InformacaoItemPedidoDTO;
import Vendas.rest.dto.InformacoesPedidoDTO;
import Vendas.rest.dto.PedidoDTO;
import Vendas.service.PedidoService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save (@RequestBody PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById (@PathVariable Integer id){
        return (InformacoesPedidoDTO) service
                .obterPedidoCompleto(id)
                .map(p -> converter((List<ItemPedido>) p))
                .orElseThrow(()-> new ResponseStatusException(NOT_FOUND,"sem pedido"));
    }

//falta atualizar pedido

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacaoItemPedidoDTO
                        .builder().descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }
}

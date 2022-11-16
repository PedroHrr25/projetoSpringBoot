package Vendas.service;

import Vendas.domain.entity.Pedido;
import Vendas.domain.enums.StatusPedido;
import Vendas.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar( PedidoDTO dto );
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}

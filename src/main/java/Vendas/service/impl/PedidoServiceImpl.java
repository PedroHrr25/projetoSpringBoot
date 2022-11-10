package Vendas.service.impl;

import Vendas.domain.repository.Pedidos;
import Vendas.service.PedidoService;
import org.springframework.stereotype.Service;


@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}

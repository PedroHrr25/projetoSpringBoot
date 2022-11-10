package Vendas.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidosDTO {

    private Integer Cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;
}

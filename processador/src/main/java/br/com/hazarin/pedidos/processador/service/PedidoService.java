package br.com.hazarin.pedidos.processador.service;


import br.com.hazarin.pedidos.processador.entity.ItemPedido;
import br.com.hazarin.pedidos.processador.entity.Pedido;
import br.com.hazarin.pedidos.processador.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final Logger logger = LoggerFactory.getLogger(PedidoService.class);
    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;
    private final ItemPedidoService itemPedidoService;


    public PedidoService(PedidoRepository pedidoRepository, ProdutoService produtoService, ItemPedidoService itemPedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoService = produtoService;
        this.itemPedidoService = itemPedidoService;
    }

    public void salvar(Pedido pedido){
        //salvamos os produtos
        produtoService.save(pedido.getItens());

        //Salvamos os itens do pedido
        List<ItemPedido> itensPedido =  itemPedidoService.save(pedido.getItens());


        //salvamos o pedido
        pedidoRepository.save(pedido);

        //Atualiza o item pedido definindo o pedido ao qual ele faz parte
        itemPedidoService.updatedItemPedido(itensPedido, pedido);
        logger.info("Pedido salvo: {}", pedido.toString());


    }
}

package br.com.hazarin.pedidos.processador.service;


import br.com.hazarin.pedidos.processador.entity.ItemPedido;
import br.com.hazarin.pedidos.processador.entity.Pedido;
import br.com.hazarin.pedidos.processador.repository.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public List<ItemPedido> save(List<ItemPedido> itens) {
        return itemPedidoRepository.saveAll(itens);

    }

    public void save(ItemPedido item){
        itemPedidoRepository.save(item);
    }

    public void updatedItemPedido(List<ItemPedido> itensPedido, Pedido pedido) {
        itensPedido.forEach(item -> {
            item.setPedido(pedido); // Informando ao item, o seu pedido
            this.save(item);
        });
    }
}

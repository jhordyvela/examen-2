package pe.edu.upeu.api_orders.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upeu.api_orders.model.Order;
import pe.edu.upeu.api_orders.repository.OrderRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService service;

    @Test
    void crearPedidoConDescuento() {
        Order order = new Order(null, "Jhan Arly", 1200.0);

        when(repository.save(any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Order result = service.createOrder(order);

        assertEquals(1080.0, result.getAmount());
        verify(repository).save(order);
    }

    @Test
    void crearPedidoSinDescuento() {
        Order order = new Order(null, "Linda", 800.0);

        when(repository.save(any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Order result = service.createOrder(order);

        assertEquals(800.0, result.getAmount());
        verify(repository).save(order);
    }

    @Test
    void listarPedidos() {
        List<Order> orders = List.of(
                new Order(1L, "Jhan Arly", 1200.0),
                new Order(2L, "Linda", 800.0)
        );

        when(repository.findAll()).thenReturn(orders);

        List<Order> result = service.getAllOrders();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }
}
package ru.productstar.mockito.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.productstar.mockito.ProductNotFoundException;
import ru.productstar.mockito.model.*;
import ru.productstar.mockito.repository.OrderRepository;
import ru.productstar.mockito.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    /**
     * Покрыть тестами методы create и addProduct.
     * Можно использовать вызовы реальных методов.
     *
     * Должны быть проверены следующие сценарии:
     * - создание ордера для существующего и нового клиента
     * - добавление существующего и несуществующего товара
     * - добавление товара в достаточном и не достаточном количестве
     * - заказ товара с быстрой доставкой
     *
     * Проверки:
     * - общая сумма заказа соответствует ожидаемой
     * - корректная работа для несуществующего товара
     * - порядок и количество вызовов зависимых сервисов
     * - факт выбрасывания ProductNotFoundException
     */

    private CustomerService customerService;
    private WarehouseService warehouseService;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderService orderService;

    @BeforeEach
    void Search(){
        customerService = mock(CustomerService.class);
        warehouseService = mock(WarehouseService.class);
        orderRepository = mock(OrderRepository.class);
        productRepository = mock(ProductRepository.class);

        orderService = new OrderService(
                customerService,
                warehouseService,
                orderRepository,
                productRepository
        );
    }

    /**
     * Проверяем создание заказа для разных клиенов
     * CustomService должен вернуть клиента,
     * после чего OrderRepository должен создать заказ.
     */

    @ParameterizedTest
    @ValueSource(strings = {"Ivan","Oleg", "Petr"})
    void createOrder(String customerName){
        Customer customer = new Customer(customerName);
        Order order = new Order(customer);

        when(customerService.getOrCreate(customerName))
                .thenReturn(customer);

        when(orderRepository.create(customer))
                .thenReturn(order);

        Order result = orderService.create(customerName);

        assertEquals(customerName, result.getCustomer().getName());

        verify(customerService, times(1))
                .getOrCreate(customerName);

        verify(orderRepository, times(1))
                .create(customer);

    }

    /**
     * Проверяем успешное добавление существующего товара.
     * товар имеется на складе в достаточном количестве
     * итоговая сумма заказа должно соответствовать цене * количество
     */

    @Test
    void addExistingProduct() throws ProductNotFoundException{
        Customer customer = new Customer("Ivan");

        Order order = new Order(customer);
        order.setId(1);

        Product product = new Product("phone");

        Warehouse warehouse = new Warehouse("Warehouse", 20);

        Stock stock = new Stock(product, 400, 10);
        warehouse.addStock(stock);

        when(warehouseService.findWarehouse("phone",2))
                .thenReturn(warehouse);

        when(productRepository.getByName("phone"))
                .thenReturn(product);

        when(warehouseService.getStock(warehouse, "phone"))
                .thenReturn(stock);

        when(orderRepository.addDelivery(eq(1), any()))
                .thenAnswer(invocation -> {
                    order.addDelivery(invocation.getArgument(1));
                    return order;
                });

        Order result = orderService.addProduct(
                order,
                "phone",
                2,
                false
        );

        assertEquals(800, result.getTotal());

        verify(warehouseService, times(1))
                .findWarehouse("phone", 2);

        verify(productRepository, times(1))
                .getByName("phone");

        verify(warehouseService, times(1))
                .getStock(warehouse, "phone");

        verify(orderRepository, times(1))
                .addDelivery(eq(1),any());
    }

    /**
     * Проверяем ситуацию, когда товара недостаточно.
     * WarehouseService не находит подходящий склад,
     * пожтому OrderService должен выбросить ProductNotFoundException.
     */

    @Test
    void throwExceptionWhenNotEnoughProduct(){
        Order order = new Order(new Customer("Ivan"));

        when(warehouseService.findWarehouse("phone", 100))
                .thenReturn(null);

        assertThrows(
                ProductNotFoundException.class,
                () -> orderService.addProduct(
                        order,
                        "phone",
                        100,
                        false
                )
        );

        verify(warehouseService, times(1))
                .findWarehouse("phone", 100);

        verify(productRepository,never())
                .getByName(any());

        verify(orderRepository, never())
                .addDelivery(anyInt(), any());
    }

    /**
     * Проверяем ситуацию, когда товар вообще не найден.
     * WarehouseService возвращает null,
     * после чего выбрасывается ProductNotFoundException.
     */
    @Test
    void  throwExceptionWhenProductNotFound(){
        Order order = new Order(new Customer("Ivan"));

        when(warehouseService.findWarehouse("unknown", 1))
                .thenReturn(null);

        ProductNotFoundException exception = assertThrows(
                ProductNotFoundException.class,
                () -> orderService.addProduct(
                        order,
                        "unknown",
                        1,
                        false

                )
        );

        assertEquals("unknown not found", exception.getMessage());

        verify(warehouseService).findWarehouse("unknown",1);

        verifyNoInteractions(productRepository);
    }

    /**
     * Проверяем заказ с бытрой доставкой
     * При fastestDelivery = true должен использоваться
     * метод findClosestWarehouse(), а не findWarehouse().
     */
    @Test
    void addProductWithFastestDelivery() throws ProductNotFoundException{
        Order order = new Order(new Customer("Ivan"));
        order.setId(2);

        Product product = new Product("phone");

        Warehouse warehouse = new Warehouse("ClosestWarehouse", 5);

        Stock stock= new Stock(product,450, 10);
        warehouse.addStock(stock);

        when(warehouseService.findClosestWarehouse("phone", 3))
                .thenReturn(warehouse);

        when(productRepository.getByName("phone"))
                .thenReturn(product);

        when(warehouseService.getStock(warehouse, "phone"))
                .thenReturn(stock);

        when(orderRepository.addDelivery(eq(2), any()))
                .thenAnswer(invocation ->{
                    order.addDelivery(invocation.getArgument(1));
                    return order;
                });

        Order result = orderService.addProduct(
                order,
                "phone",
                3,
                true
        );

        assertEquals(1350, result.getTotal());

        verify(warehouseService, times(1))
                .findClosestWarehouse("phone", 3);

        verify(warehouseService, never())
                .findWarehouse(anyString(),anyInt());
    }

    /**
     * Проверяем работу spy
     * Создаем spy поверх реального OrderService и убеждаемся
     * что метод create был действительно вызван
     */
    @Test
    void createOrderUsingSpy(){
        Customer customer = new Customer("Ivan");
        Order order = new Order(customer);

        when(customerService.getOrCreate("Ivan"))
                .thenReturn(customer);

        when(orderRepository.create(customer))
                .thenReturn(order);

        OrderService spyOrderService = spy(orderService);

        Order result = spyOrderService.create("Ivan");

        assertEquals("Ivan", result.getCustomer().getName());

        verify(spyOrderService,times(1))
                .create("Ivan");
    }
}

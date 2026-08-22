package ru.productstar.mockito.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.productstar.mockito.model.Product;
import ru.productstar.mockito.model.Stock;
import ru.productstar.mockito.model.Warehouse;
import ru.productstar.mockito.repository.WarehouseRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WarehouseServiceTest {

    /**
     * Покрыть тестами методы findWarehouse и findClosestWarehouse.
     * Вызывать реальные методы зависимых сервисов и репозиториев нельзя.
     * Поиск должен осуществляться как минимум на трех складах.
     *
     * Должны быть проверены следующие сценарии:
     * - поиск несуществующего товара
     * - поиск существующего товара с достаточным количеством
     * - поиск существующего товара с недостаточным количеством
     *
     * Проверки:
     * - товар находится на нужном складе, учитывается количество и расстояние до него
     * - корректная работа для несуществующего товара
     * - порядок и количество вызовов зависимых сервисов
     */

    private WarehouseRepository warehouseRepository;
    private WarehouseService warehouseService;


    private Warehouse warehouse1;
    private Warehouse warehouse2;
    private Warehouse warehouse3;

    @BeforeEach
    void setUp(){
        warehouseRepository = mock(WarehouseRepository.class);
        warehouseService = new WarehouseService(warehouseRepository);

        Product phone = new Product("phone");
        Product laptop = new Product("laptop");

        warehouse1 = new Warehouse("Warehouse1", 30);
        warehouse1.addStock(new Stock(phone, 400, 5));

        warehouse2 = new Warehouse("Warehouse2",20);
        warehouse2.addStock(new Stock(phone, 300, 2));
        warehouse2.addStock(new Stock(laptop, 850, 1));

        warehouse3 = new Warehouse("Warehouse3", 5);
        warehouse3.addStock(new Stock(phone, 450, 3));

    }

    /**
     * Проверяем поиск существующего товара в достаточном количестве
     * Метод findWarehouse должен вернуть первый подходящий склад
     */
    @Test
    void findWarehouseWithEnoughProduct(){

        when(warehouseRepository.all())
                .thenReturn(Arrays.asList(
                        warehouse1,
                        warehouse2,
                        warehouse3
                ));

        Warehouse result = warehouseService.findWarehouse("phone", 4);

        assertEquals("Warehouse1", result.getName());

        verify(warehouseRepository, times(1))
                .all();
    }

    /**
     * Проверяем поиск существующего товара в недостаточном количестве.
     * Если нужного количества нет ни на одном складе,
     * метод должен вернуть null.
     */
    @Test
    void findWarehouseWithNotEnoughProduct(){
        when(warehouseRepository.all())
                .thenReturn(Arrays.asList(
                        warehouse1,
                        warehouse2,
                        warehouse3
                ));

        Warehouse result = warehouseService.findWarehouse("phone", 100);

        assertNull(result);

        verify(warehouseRepository, times(1)).all();
    }

    /**
     * Проверяем поиск ближайшего склада
     * При наличии товара на нескольких складах должен быть
     * выбран склад с минимальным расстоянием
     */
    @Test
    void findClosestWarehouse(){
        when(warehouseRepository.all())
                .thenReturn(Arrays.asList(
                        warehouse1,
                        warehouse2,
                        warehouse3
                ));

        Warehouse result =
                warehouseService.findClosestWarehouse("phone", 2);

        assertEquals("Warehouse3", result.getName());
        assertEquals(5, result.getDistance());

        verify(warehouseRepository, times(1)).all();
    }

    /**
     * Проверяем граничный случай с нулевым остатком
     * Склад с нулевым количеством товара не должен подходить
     * для заказа хотя бы одной единицы
     */
    @Test
    void findWarehouseWithZeroStock(){
        Product monitor = new Product("monitor");

        Warehouse emptyWarehouse =
                new Warehouse("EmptyWarehouse", 1);

        emptyWarehouse.addStock(
                new Stock(monitor, 300, 0)
        );

        when(warehouseRepository.all())
                .thenReturn(Arrays.asList(
                        emptyWarehouse,
                        warehouse1,
                        warehouse2
                ));

        Warehouse result = warehouseService.findWarehouse("monitor", 1);

        assertNull(result);

        verify(warehouseRepository, times(1)).all();
    }

    /**
     * Проверяем использование spy
     * с помощью doReturn подменяем результат реального метода
     * и проверяем факт его вызова
     */
    @Test
    void findWarehouseUsingSpy(){
        WarehouseService spyWarehouseService =
                spy(warehouseService);

        doReturn(warehouse2)
                .when(spyWarehouseService)
                .findWarehouse("phone",2);

        Warehouse result =
                spyWarehouseService.findWarehouse("phone", 2);

        assertEquals("Warehouse2", result.getName());

        verify(spyWarehouseService, times(1))
                .findWarehouse("phone", 2);
    }

    /**
     * Проверяем поиск несуществующего товара.
     * Если товара нет ни на одном складе,
     * метод должен вернуть null.
     */
    @Test
    void findWarehouseWithUnknownProduct() {
        when(warehouseRepository.all())
                .thenReturn(Arrays.asList(
                        warehouse1,
                        warehouse2,
                        warehouse3
                ));

        Warehouse result =
                warehouseService.findWarehouse("printer", 1);

        assertNull(result);

        verify(warehouseRepository, times(1))
                .all();
    }
}


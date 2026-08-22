package ru.productstar.mockito.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.productstar.mockito.model.Customer;
import ru.productstar.mockito.repository.CustomerRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private CustomerService customerService;

    @BeforeEach
    void setUp(){
        customerRepository = mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepository);
    }

    /**
     * Проверяем получение уже существующего покупателя Ivan.
     * Репозиторий должен выполнить только поиск клиента,
     * добавление нового клиента происходить не должно.
     */
    @Test
    void getExistingCustomerIvan(){
        Customer ivan = new Customer("Ivan");

        when(customerRepository.getByName("Ivan"))
                .thenReturn(ivan);

        Customer result = customerService.getOrCreate("Ivan");

        assertEquals("Ivan", result.getName());

        verify(customerRepository, never())
                .add(any(Customer.class));
    }

    /**
     * Проверяем создание Oleg, если его нет в репозитории.
     * Сначала должен произойти поиск, затем добавление нового клиента
     * Также проверяем имя клиента, переданного в метод add().
     */
    @Test
    void createCustomerOlegIfNotExist(){
        when(customerRepository.getByName("Oleg"))
                .thenReturn(null);

        when(customerRepository.add(any(Customer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Customer result = customerService.getOrCreate("Oleg");

        ArgumentCaptor<Customer> customerCaptor =
                ArgumentCaptor.forClass(Customer.class);

        var inOrder = inOrder(customerRepository);

        inOrder.verify(customerRepository, times(1))
                .getByName("Oleg");

        inOrder.verify(customerRepository, times(1))
                .add(customerCaptor.capture());

        assertEquals("Oleg", customerCaptor.getValue().getName());
        assertEquals("Oleg", result.getName());

        verifyNoMoreInteractions(customerRepository);
    }

    /**
     * Проверяем создание покупателя с пустым именем
     * Это граничный случай для выходного значения
     */

    @Test
    void createCustomerWithEmptyName(){
        when(customerRepository.getByName(""))
                .thenReturn(null);

        when(customerRepository.add(any(Customer.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Customer result = customerService.getOrCreate("");

        assertEquals("", result.getName());

        verify(customerRepository).getByName("");
        verify(customerRepository).add(any(Customer.class));
    }

}

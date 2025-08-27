package com.example.library.controller;

import com.example.library.dto.OrderDto;
import com.example.library.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibraryOrderController.class)
class LibraryOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService; // Исправлено: OrderService вместо LibraryOrderService

    @Test
    void getAllOrders_ShouldReturnOrders() throws Exception {
        // Создаем тестовые данные
        OrderDto order1 = new OrderDto(1L, 1L, 1L,
                LocalDateTime.now(), LocalDateTime.now().plusDays(7), "ACTIVE");
        OrderDto order2 = new OrderDto(2L, 2L, 2L,
                LocalDateTime.now(), LocalDateTime.now().plusDays(14), "COMPLETED");

        when(orderService.getAllOrders()).thenReturn(List.of(order1, order2));

        // Выполняем запрос и проверяем результат
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk());
    }
}
package com.example.library.controller;

import com.example.library.dto.OrderDto;
import com.example.library.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibraryOrderController.class)
@WithMockUser // Добавляем для обхода Security
class LibraryOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    @Test
    void getAllOrders_ShouldReturnOrders() throws Exception {
        OrderDto order1 = new OrderDto();
        order1.setId(1L);
        order1.setBookId(1L);
        order1.setUserId(1L);
        order1.setOrderDate(LocalDateTime.now());
        order1.setDueDate(LocalDateTime.now().plusDays(7));
        order1.setStatus("ACTIVE");

        OrderDto order2 = new OrderDto();
        order2.setId(2L);
        order2.setBookId(2L);
        order2.setUserId(2L);
        order2.setOrderDate(LocalDateTime.now());
        order2.setDueDate(LocalDateTime.now().plusDays(14));
        order2.setStatus("COMPLETED");

        when(orderService.getAllOrders()).thenReturn(List.of(order1, order2));

        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderById_WhenOrderExists_ShouldReturnOrder() throws Exception {
        // Arrange
        Long orderId = 1L;
        OrderDto orderDto = new OrderDto();
        orderDto.setId(orderId);
        orderDto.setBookId(1L);
        orderDto.setUserId(1L);
        orderDto.setOrderDate(LocalDateTime.now());
        orderDto.setDueDate(LocalDateTime.now().plusDays(7));
        orderDto.setStatus("ACTIVE");

        when(orderService.getOrderById(orderId)).thenReturn(orderDto);

        mockMvc.perform(get("/api/orders/{id}", orderId))
                .andExpect(status().isOk());
    }

    @Test
    void createOrder_ShouldReturnCreatedOrder() throws Exception {
        // Arrange
        OrderDto inputDto = new OrderDto();
        inputDto.setBookId(1L);
        inputDto.setUserId(1L);
        inputDto.setStatus("ACTIVE");

        OrderDto createdOrder = new OrderDto();
        createdOrder.setId(1L);
        createdOrder.setBookId(1L);
        createdOrder.setUserId(1L);
        createdOrder.setOrderDate(LocalDateTime.now());
        createdOrder.setDueDate(LocalDateTime.now().plusDays(7));
        createdOrder.setStatus("ACTIVE");

        when(orderService.createOrder(any(OrderDto.class))).thenReturn(createdOrder);

        String orderJson = objectMapper.writeValueAsString(inputDto);

        mockMvc.perform(post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk()); // или .isCreated() если контроллер возвращает 201
    }

    @Test
    void updateOrder_ShouldReturnUpdatedOrder() throws Exception {
        Long orderId = 1L;
        OrderDto updateDto = new OrderDto();
        updateDto.setStatus("COMPLETED");

        OrderDto updatedOrder = new OrderDto();
        updatedOrder.setId(orderId);
        updatedOrder.setBookId(1L);
        updatedOrder.setUserId(1L);
        updatedOrder.setOrderDate(LocalDateTime.now());
        updatedOrder.setDueDate(LocalDateTime.now().plusDays(7));
        updatedOrder.setStatus("COMPLETED");

        when(orderService.updateOrder(eq(orderId), any(OrderDto.class))).thenReturn(updatedOrder);

        String orderJson = objectMapper.writeValueAsString(updateDto);

        mockMvc.perform(put("/api/orders/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson))
                .andExpect(status().isOk());
    }

    @Test
    void deleteOrder_ShouldReturnNoContent() throws Exception {
        Long orderId = 1L;
        doNothing().when(orderService).deleteOrder(orderId);

        mockMvc.perform(delete("/api/orders/{id}", orderId))
                .andExpect(status().isOk()); // или .isNoContent() если контроллер возвращает 204
    }
}
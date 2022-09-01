package com.company.controllers;


import com.company.API.model.ProductDto;
import com.company.domain.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductControllerImpl.class)
class ProductControllerImplTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    void setUp() {
    }

    void tearDown() {

    }

    @Test
    @SneakyThrows
    void add() {
        mvc.perform(post("/api/product/add")
                .content(om.writeValueAsString(new ProductDto("Гайка", "22-11A", "17-99", "Деталь",null))))
                .andDo(print())
                .andExpect(status().isOk());
    }

    void addList() {
    }

    @Test
    @SneakyThrows
    void getAll() {
        mvc.perform(get("/api/product/add"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    void getByCipher() {
    }

    void delete() {
    }
}
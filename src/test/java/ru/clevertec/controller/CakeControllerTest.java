package ru.clevertec.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.clevertec.common.CakeType;
import ru.clevertec.domain.Cake;
import ru.clevertec.exception.CakeNotFoundException;
import ru.clevertec.service.CakeService;
import ru.clevertec.util.TestData;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CakeController.class)
class CakeControllerTest {
    @MockBean
    private CakeService cakeService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFindAll() throws Exception {
        //given
        when(cakeService.getCakes()).thenReturn(List.of(TestData.generateCake(), TestData.generateCake()));

        //when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cakes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldFindCakeById() throws Exception {
        //given
        UUID cakeId = TestData.generateCake().getId();
        when(cakeService.getCakeById(cakeId)).thenReturn(TestData.generateCake());

        //when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cakes/{cakeId}", cakeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cakeId.toString()));
    }

    @Test
    void shouldReturnStatusNotFound_whenCakeNotFoundById() throws Exception {
        //given
        UUID cakeId = TestData.generateCake().getId();
        when(cakeService.getCakeById(cakeId)).thenThrow(CakeNotFoundException.byCakeId(cakeId));

        //when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cakes/{cakeId}", cakeId))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldFindCakeByType() throws Exception {
        //given
        CakeType cakeType = TestData.generateCake().getCakeType();
        when(cakeService.getCakeByType(cakeType)).thenReturn(TestData.generateCake());

        //when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cakes/byType/{type}", cakeType))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnStatusNotFound_whenCakeNotFoundByType() throws Exception {
        //given
        CakeType cakeType = TestData.generateCake().getCakeType();
        when(cakeService.getCakeByType(cakeType)).thenThrow(CakeNotFoundException.byCakeType(cakeType));

        //when, then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/cakes/byType/{type}", cakeType))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldCreateNewCake() throws Exception {
        //given
        Cake cake = TestData.generateCake();
        when(cakeService.create(cake)).thenReturn(cake);

        //when,then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/cakes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cake)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateCake() throws Exception {
        //given
        Cake cake = TestData.generateCake();
        UUID cakeId = cake.getId();
        when(cakeService.update(cakeId, cake)).thenReturn(cake);

        //when, then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/cakes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cake)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteCakeById() throws Exception {
        //given
        Cake cake = TestData.generateCake();
        UUID cakeId = cake.getId();
        doNothing().when(cakeService).deleteById(cakeId);

        //when, then
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/cakes/{cakeId}", cakeId))
                .andExpect(status().isOk());
    }
}
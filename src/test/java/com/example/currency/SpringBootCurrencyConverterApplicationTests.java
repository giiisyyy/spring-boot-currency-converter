package com.example.currency;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean; 
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import org.mockito.Mockito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootCurrencyConverterApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void indexPageLoads() throws Exception {
        mockMvc.perform(get("/"))
               .andExpect(status().isOk())
               .andExpect(view().name("index"))
               .andExpect(model().attributeExists("currencies"));
    }

    @Test
    void convertReturnsResult() throws Exception {
        Map<String, Object> mockRates = Map.of("EUR", 1.0, "USD", 1.08);
        Map<String, Object> mockResponse = Map.of("rates", mockRates);

        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Map.class)))
               .thenReturn(mockResponse);

        mockMvc.perform(get("/convert")
               .param("amount", "100")
               .param("from", "EUR")
               .param("to", "USD")
               .param("lang", "en"))
               .andExpect(status().isOk())
               .andExpect(view().name("result"))
               .andExpect(model().attributeExists("result"));
    }
}
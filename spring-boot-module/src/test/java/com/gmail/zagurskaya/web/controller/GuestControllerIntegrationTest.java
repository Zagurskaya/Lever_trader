package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.TraderService;
import com.gmail.zagurskaya.service.model.TraderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GuestControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private TraderService traderService;

    @Value("${local.server.port}")
    private int port;

    @Test
    public void getTradersTest_OK() {
        createTrader("testTrader");
        createTrader("testTrader1");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        String url = "http://localhost:" + port + "/api/traders";
        ResponseEntity<List<TraderDTO>> response = testRestTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<TraderDTO>>() {
                });
        List<TraderDTO> traderList = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertNotNull(traderList);
        assertEquals(traderList.size(), 2);
        assertEquals(traderList.get(0).getName(), "testTrader");
        assertEquals(traderList.get(1).getName(), "testTrader1");
    }

    @Test
    public void saveTraderTest_OK() {
        TraderDTO traderDTO = new TraderDTO();
        traderDTO.setName("testTrader");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<TraderDTO> entity = new HttpEntity<>(traderDTO, headers);

        String url = "http://localhost:" + port + "/api/traders";
        ResponseEntity<TraderDTO> response = testRestTemplate.exchange(url, HttpMethod.POST, entity, TraderDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getTraderByIdTest_OK() {
        long id = createTrader("testTrader");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        String url = "http://localhost:" + port + "/api/traders/{id}";
        ResponseEntity<TraderDTO> response = testRestTemplate.exchange(url, HttpMethod.GET, entity, TraderDTO.class, id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON_UTF8, response.getHeaders().getContentType());
        assertNotNull(response.getBody().getId());
        assertEquals(response.getBody().getName(), "testTrader");
    }

    Long createTrader(String name) {
        TraderDTO traderDTO = new TraderDTO();
        traderDTO.setName(name);
        traderDTO.setApproved(true);
        return traderService.add(traderDTO);

    }
}
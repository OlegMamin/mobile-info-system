package ru.levelup.junior.web.controllers;

import configuration.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Tariff;
import ru.levelup.junior.repositories.ClientsRepository;
import ru.levelup.junior.repositories.ContractsRepository;
import ru.levelup.junior.repositories.TariffsRepository;
import ru.levelup.junior.web.configuration.WebConfig;
import ru.levelup.junior.web.security.SecurityConfig;

import javax.servlet.Filter;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by otherz on 06.12.2019.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class, SecurityConfig.class, WebConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@WebAppConfiguration
public class AddEntityControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    @Qualifier("springSecurityFilterChain")
    private Filter securityFilter;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ContractsRepository contractsRepository;

    @Autowired
    private TariffsRepository tariffsRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(securityFilter)
                .apply(springSecurity())
                .build();

        Client client = new Client("John", "Terry"
                , "1234564145", "test", "1234");
        Tariff tariff = new Tariff("tariff", 123);
        Contract contract = new Contract("7557755", client, tariff);
        clientsRepository.save(client);
        tariffsRepository.save(tariff);
        contractsRepository.save(contract);
    }

    @Test
    public void redirectingToDashboard() throws Exception {
        // unauthorized user should be redirected to /
        mockMvc.perform(MockMvcRequestBuilders.get("/addContract"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/"))
                .andReturn();
    }

}
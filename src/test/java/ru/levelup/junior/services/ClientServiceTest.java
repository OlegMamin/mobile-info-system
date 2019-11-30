package ru.levelup.junior.services;

import configuration.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Tariff;
import ru.levelup.junior.repositories.ClientsRepository;
import ru.levelup.junior.repositories.ContractsRepository;
import ru.levelup.junior.repositories.TariffsRepository;

import static org.junit.Assert.*;

/**
 * Created by otherz on 30.11.2019.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ClientServiceTest {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ContractsRepository contractsRepository;

    @Autowired
    private TariffsRepository tariffsRepository;

    private Client client;

    private Contract contract;

    @Before
    public void setup() {
        Client client1 = new Client("John", "Terry"
                , "1234564145", "test", "1234");
        clientsRepository.save(client1);

        Tariff tariffLow = new Tariff("tariffLow", 100);
        tariffsRepository.save(tariffLow);

        Contract contract1 = new Contract("7557755", client1, tariffLow);
        contractsRepository.save(contract1);

        this.client = client1;
        this.contract = contract1;
    }

    @Test
    public void findByPhoneNumber() throws Exception {
        Client found = clientService.findByPhoneNumber(contract.getPhoneNumber());

        Assert.assertNotNull(found);
        Assert.assertEquals(client.getId(), found.getId());
    }
}
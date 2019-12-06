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
import ru.levelup.junior.entities.Option;
import ru.levelup.junior.entities.Tariff;
import ru.levelup.junior.repositories.ClientsRepository;
import ru.levelup.junior.repositories.ContractsRepository;
import ru.levelup.junior.repositories.OptionsRepository;
import ru.levelup.junior.repositories.TariffsRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

/**
 * Created by otherz on 30.11.2019.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ContractServiceTest {
    @Autowired
    private ContractService contractService;

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ContractsRepository contractsRepository;

    @Autowired
    private OptionsRepository optionsRepository;

    @Autowired
    private TariffsRepository tariffsRepository;

    @PersistenceContext
    private EntityManager manager;

    private Client client;

    private Contract contract, contractWithoutClientAndTariff;

    private Option option;

    private Tariff tariff;

    @Before
    public void setup() {
        Client client1 = new Client("John", "Terry"
                , "1234564145", "test", "12345");
        Client client2 = new Client("Frank", "Lampard"
                , "1005323232", "frog", "4567");
        clientsRepository.save(client1);
        clientsRepository.save(client2);

        Tariff tariffLow = new Tariff("tariffLow", 100);
        Tariff tariffMedium = new Tariff("tariffMedium", 200);
        Tariff tariffHigh = new Tariff("tariffHigh", 300);
        tariffsRepository.save(tariffLow);
        tariffsRepository.save(tariffMedium);
        tariffsRepository.save(tariffHigh);

        Option option1 = new Option("testOption1", 10, 3);
        Option option2 = new Option("testOption2", 12, 4);
        optionsRepository.save(option1);
        optionsRepository.save(option2);

        Contract contract1 = new Contract("7557755");
        Contract contract2 = new Contract("1112233");
        Contract contract3 = new Contract("9994466");
        Contract contract4 = new Contract("0000000");

        contract1.setClient(client1);
        contract2.setClient(client1);
        contract3.setClient(client2);

        contract1.setTariff(tariffHigh);
        contract2.setTariff(tariffLow);
        contract3.setTariff(tariffLow);

        contract1.getOptions().add(option2);
        contract1.getOptions().add(option1);
        contract2.getOptions().add(option2);
        contract2.getOptions().add(option1);
        contract3.getOptions().add(option1);

        contractsRepository.save(contract1);
        contractsRepository.save(contract2);
        contractsRepository.save(contract3);
        contractsRepository.save(contract4);

        this.contract = contract1;
        this.contractWithoutClientAndTariff = contract4;
        this.client = client1;
        this.option = option1;
        this.tariff = tariffMedium;
    }


    @Test
    public void removeOption() throws Exception {
        contractService.removeOption(contract.getId(), option.getId());
        Contract found = contractsRepository.findById(contract.getId()).get();

        Assert.assertEquals(1, found.getOptions().size());
        Assert.assertEquals("testOption2", found.getOptions().get(0).getName());
    }

    @Test
    public void connectOption() throws Exception {
        contractService.connectOption(contractWithoutClientAndTariff.getId(), option.getId());
        Contract found = contractsRepository.findById(contractWithoutClientAndTariff.getId()).get();

        Assert.assertEquals(1, found.getOptions().size());
        Assert.assertEquals(option.getId(), found.getOptions().get(0).getId());
        Assert.assertEquals("testOption1", found.getOptions().get(0).getName());
    }

    @Test
    public void blockTariff() throws Exception {
        contractService.blockTariff(contract.getId());
        Contract found = contractsRepository.findById(contract.getId()).get();

        Assert.assertNull(found.getTariff());
    }

    @Test
    public void terminateContract() throws Exception {
        contractService.terminateContract(contract.getId());
        Contract found = contractsRepository.findById(contract.getId()).get();

        Assert.assertNull(found.getClient());
    }

    @Test
    public void setClientToContract() throws Exception {
        contractService.setClientToContract(contractWithoutClientAndTariff.getPhoneNumber(), client.getLogin());

        Contract found = contractsRepository.findById(contractWithoutClientAndTariff.getId()).get();

        Assert.assertEquals(contractWithoutClientAndTariff.getPhoneNumber(), found.getPhoneNumber());
        Assert.assertEquals(client.getId(), found.getClient().getId());
    }

    @Test
    public void setTariffToContract() throws Exception {
        contractService.setTariffToContract(contractWithoutClientAndTariff.getPhoneNumber(), tariff.getId());

        Contract found = contractsRepository.findById(contractWithoutClientAndTariff.getId()).get();

        Assert.assertEquals(contractWithoutClientAndTariff.getPhoneNumber(), found.getPhoneNumber());
        Assert.assertEquals(tariff.getId(), found.getTariff().getId());
        Assert.assertEquals("tariffMedium", found.getTariff().getName());
    }

}
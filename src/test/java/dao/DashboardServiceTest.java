package dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelup.junior.dao.ClientsDAO;
import ru.levelup.junior.dao.ContractsDAO;
import ru.levelup.junior.dao.OptionsDAO;
import ru.levelup.junior.dao.TariffsDAO;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Option;
import ru.levelup.junior.entities.Tariff;
import ru.levelup.junior.web.DashboardService;
import configuration.TestConfig;

import java.util.List;

/**
 * Created by otherz on 14.11.2019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DashboardServiceTest {
    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private ClientsDAO clientsDAO;

    @Autowired
    private ContractsDAO contractsDAO;

    @Autowired
    private TariffsDAO tariffsDAO;

    @Autowired
    private OptionsDAO optionsDAO;

    private int clientId;

    @Before
    public void setup() {
        Client client1 = new Client("John", "Terry"
                , "1234564145", "test", "12345");
        clientsDAO.create(client1);
        Client client2 = new Client("Frank", "Lampard"
                , "1005323232", "frog", "4567");
        clientsDAO.create(client2);

        Tariff tariffLow = new Tariff("tariffLow", 100);
        tariffsDAO.create(tariffLow);
        Tariff tariffHigh = new Tariff("tariffHigh", 300);
        tariffsDAO.create(tariffHigh);
        Tariff tariffMedium = new Tariff("tariffMedium", 200);
        tariffsDAO.create(tariffMedium);

        Contract contract1 = new Contract("7557755", client1, tariffLow);
        Contract contract2 = new Contract("1112233", client1, tariffHigh);
        Contract contract3 = new Contract("2322212", client1, tariffMedium);
        Contract contract4 = new Contract("4666666", client2, tariffHigh);

        Option option1 = new Option("testDao1", 10, 3);
        optionsDAO.create(option1);
        Option option2 = new Option("testDao2", 12, 4);
        optionsDAO.create(option2);

        contract1.getOptions().add(option1);
        contract1.getOptions().add(option2);
        contract2.getOptions().add(option1);
        contract3.getOptions().add(option1);
        contract3.getOptions().add(option2);
        contract4.getOptions().add(option2);

        contractsDAO.create(contract1);
        contractsDAO.create(contract2);
        contractsDAO.create(contract3);
        contractsDAO.create(contract4);
        clientId = client1.getId();
    }

    @Test
    public void getContracts() {
        List<Contract> contracts = dashboardService.getContracts(clientId);

        Assert.assertEquals(3, contracts.size());
    }
}

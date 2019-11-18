package dao;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelup.junior.dao.ClientsDAO;
import ru.levelup.junior.dao.ContractsDAO;
import ru.levelup.junior.dao.TariffsDAO;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Option;
import ru.levelup.junior.entities.Tariff;
import ru.levelup.junior.dao.OptionsDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.levelup.junior.web.AppConfig;
import ru.levelup.junior.web.DashboardService;
import tests.TestConfig;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by otherz on 06.11.2019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OptionsDAOTest {
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

    @Autowired
    private EntityManager manager;

    private Option option;

    @Before
    public void setup() {
        manager.getTransaction().begin();

        Option option1 = new Option("testDao1", 10, 3);
        optionsDAO.create(option1);
        Option option2 = new Option("testDao2", 12, 4);
        optionsDAO.create(option2);


        this.option = option1;
    }
    @Test
    public void create() throws Exception {

        Assert.assertNotNull(manager.find(Option.class, option.getId()));

    }

    @Test
    public void findByName() throws Exception {

        Option found = optionsDAO.findByName(option.getName());

        Assert.assertNotNull(found);
        Assert.assertEquals(option.getId(), found.getId());

        try {
            optionsDAO.findByName("fakeName");
            fail("Option fakeName shouldn't be found");
        } catch (NoResultException expected) {

        }
    }

    @Test
    public void findByMonthlyPaymentInterval() throws Exception {

        List<Option> found = optionsDAO.findByMonthlyPaymentInterval(1, 10);

        Assert.assertEquals(2, found.size());
        Assert.assertEquals(option.getId(), found.get(0).getId());

    }

    @Test
    public void findByCostOfConnectionInterval() throws Exception {

        List<Option> found = optionsDAO.findByCostOfConnectionInterval(2, 11);

        Assert.assertEquals(1, found.size());
        Assert.assertEquals(option.getId(), found.get(0).getId());
    }
}
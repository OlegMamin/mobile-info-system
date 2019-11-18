package dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelup.junior.dao.TariffsDAO;
import ru.levelup.junior.entities.Tariff;
import ru.levelup.junior.web.configuration.TestConfig;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by otherz on 06.11.2019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TariffsDAOTest {

    @Autowired
    private TariffsDAO tariffsDAO;

    @PersistenceContext
    private EntityManager manager;

    private Tariff tariff;

    @Before
    public void setup() {
        Tariff tariffLow = new Tariff("tariffLow", 100);
        tariffsDAO.create(tariffLow);
        Tariff tariffHigh = new Tariff("tariffHigh", 300);
        tariffsDAO.create(tariffHigh);
        Tariff tariffMedium = new Tariff("tariffMedium", 200);
        tariffsDAO.create(tariffMedium);

        this.tariff = tariffLow;
    }
    @Test
    public void create() throws Exception {
        Assert.assertNotNull(manager.find(Tariff.class, tariff.getId()));
    }

    @Test
    public void findByName() throws Exception {
        Tariff found = tariffsDAO.findByName(tariff.getName());

        Assert.assertNotNull(found);
        Assert.assertEquals(tariff.getId(), found.getId());

        try {
            tariffsDAO.findByName("fakeName");
            fail("Option fakeName shouldn't be found");
        } catch (NoResultException expected) {

        }
    }

    @Test
    public void findByPriceInterval() throws Exception {
        List<Tariff> found = tariffsDAO.findByPriceInterval(90, 210);

        Assert.assertEquals(2, found.size());
        Assert.assertEquals(tariff.getId(), found.get(0).getId());
    }
}
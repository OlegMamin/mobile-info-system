package ru.levelup.junior.repositories;

import configuration.TestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.levelup.junior.entities.Tariff;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by otherz on 30.11.2019.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TariffsRepositoryTest {
    @Autowired
    private TariffsRepository tariffsRepository;

    @PersistenceContext
    private EntityManager manager;

    private Tariff tariff;

    @Before
    public void setup() {
        Tariff tariffLow = new Tariff("tariffLow", 100);
        tariffsRepository.save(tariffLow);
        Tariff tariffHigh = new Tariff("tariffHigh", 300);
        tariffsRepository.save(tariffHigh);
        Tariff tariffMedium = new Tariff("tariffMedium", 200);
        tariffsRepository.save(tariffMedium);

        this.tariff = tariffLow;
    }

    @Test
    public void save() throws Exception {
        Assert.assertNotNull(manager.find(Tariff.class, tariff.getId()));
    }

    @Test
    public void findByName() throws Exception {
        Tariff found = tariffsRepository.findByName(tariff.getName());

        Assert.assertNotNull(found);
        Assert.assertEquals(tariff.getId(), found.getId());
    }

    @Test
    public void findByPriceBetween() throws Exception {
        List<Tariff> found = tariffsRepository.findByPriceBetween(90, 210);

        Assert.assertEquals(2, found.size());
        Assert.assertEquals(tariff.getId(), found.get(0).getId());
    }

    @Test
    public void findAll() throws Exception {
        List<Tariff> found = (List<Tariff>) tariffsRepository.findAll();

        Assert.assertEquals(3, found.size());
        Assert.assertEquals(tariff.getId(), found.get(0).getId());
        Assert.assertEquals("tariffLow", found.get(0).getName());
    }

}
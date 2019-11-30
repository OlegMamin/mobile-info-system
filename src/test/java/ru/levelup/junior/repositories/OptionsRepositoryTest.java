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
import ru.levelup.junior.entities.Option;

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
public class OptionsRepositoryTest {

    @Autowired
    private OptionsRepository optionsRepository;

    @PersistenceContext
    private EntityManager manager;

    private Option option;

    @Before
    public void setup() {
        Option option1 = new Option("testDao1", 10, 3);
        optionsRepository.save(option1);
        Option option2 = new Option("testDao2", 12, 4);
        optionsRepository.save(option2);

        this.option = option1;
    }

    @Test
    public void save() throws Exception {
        Assert.assertNotNull(manager.find(Option.class, option.getId()));
    }

    @Test
    public void findById() throws Exception {
        Option found = optionsRepository.findById(option.getId()).get();
        Assert.assertNotNull(found);
    }

    @Test
    public void findByName() throws Exception {
        Option found = optionsRepository.findByName(option.getName());

        Assert.assertNotNull(found);
        Assert.assertEquals(option.getId(), found.getId());
    }

    @Test
    public void findByMonthlyPaymentBetween() throws Exception {
        List<Option> found = optionsRepository.findByMonthlyPaymentBetween(1, 10);

        Assert.assertEquals(2, found.size());
        Assert.assertEquals(option.getId(), found.get(0).getId());
    }

    @Test
    public void findByCostOfConnectionBetween() throws Exception {
        List<Option> found = optionsRepository.findByCostOfConnectionBetween(2, 11);

        Assert.assertEquals(1, found.size());
        Assert.assertEquals(option.getId(), found.get(0).getId());
    }

    @Test
    public void findAll() throws Exception {
        List<Option> found = (List<Option>) optionsRepository.findAll();

        Assert.assertEquals(2, found.size());
    }

}
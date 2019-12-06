package ru.levelup.junior.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.junior.entities.Client;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.entities.Option;
import ru.levelup.junior.entities.Tariff;
import ru.levelup.junior.repositories.ClientsRepository;
import ru.levelup.junior.repositories.ContractsRepository;
import ru.levelup.junior.repositories.OptionsRepository;
import ru.levelup.junior.repositories.TariffsRepository;

import java.util.Random;


/**
 * Created by otherz on 11.11.2019.
 */
@Component
public class StartupListener {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private ContractsRepository contractsRepository;


    @Autowired
    private TariffsRepository tariffsRepository;


    @Autowired
    private OptionsRepository optionsRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    @EventListener
    public void handleContextRefreshEvent(ContextRefreshedEvent ctxStartEvt) {
        Client firstClient;
        Client secondClient;
        Client withoutContractClient;
        Client admin;
        Tariff tariffLow;
        Tariff tariffHigh;
        Tariff tariffMedium;
        Contract contract1;
        Contract contract2;
        Contract contract3;
        Contract contract4;
        Option option1;
        Option option2;
        Option option3;

        firstClient = new Client("John", "Terry"
                    , "1234564145", "test", encoder.encode("1234"));
        secondClient = new Client("Frank", "Lampard"
                    , "1005323232", "second", encoder.encode("4567"));
        withoutContractClient = new Client("Homer", "Simpson"
                    , "123456", "hommy", encoder.encode("1234"));

        admin = new Client("Admin", "Smith"
                    , "123098", "admin", encoder.encode("1111"));
        admin.setAdmin(true);

        tariffLow = new Tariff("tariffLow", 100);
        tariffHigh = new Tariff("tariffHigh", 300);
        tariffMedium = new Tariff("tariffMedium", 200);
        contract1 = new Contract("7557755", firstClient, tariffLow);
        contract2 = new Contract("1112233", firstClient, tariffHigh);
        contract3 = new Contract("2322212", firstClient, tariffMedium);
        contract4 = new Contract("4666666", secondClient, tariffHigh);

        for (int i = 0; i < 100; i++) {
            String number = "" + (new Random().nextInt(8999999) + 1000000);
            contractsRepository.save(new Contract(number));
        }

        option1 = new Option("option1", 10, 3);
        option2 = new Option("option2", 12, 4);
        option3 = new Option("option3", 5, 2);

        contract1.getOptions().add(option1);
        contract1.getOptions().add(option2);
        contract2.getOptions().add(option1);
        contract3.getOptions().add(option1);
        contract3.getOptions().add(option2);
        contract4.getOptions().add(option2);

        clientsRepository.save(firstClient);
        clientsRepository.save(secondClient);
        clientsRepository.save(withoutContractClient);
        clientsRepository.save(admin);
        tariffsRepository.save(tariffHigh);
        tariffsRepository.save(tariffLow);
        tariffsRepository.save(tariffMedium);
        contractsRepository.save(contract1);
        contractsRepository.save(contract2);
        contractsRepository.save(contract3);
        contractsRepository.save(contract4);
        optionsRepository.save(option1);
        optionsRepository.save(option2);
        optionsRepository.save(option3);
    }
}
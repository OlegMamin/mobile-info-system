package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.levelup.junior.entities.Contract;
import ru.levelup.junior.repositories.ContractsRepository;
import ru.levelup.junior.services.DashboardService;
import ru.levelup.junior.web.MyRestResponse;

import java.util.Arrays;
import java.util.List;

/**
 * Created by otherz on 03.12.2019.
 */
@RestController
public class FirstRestController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private ContractsRepository contractsRepository;

    @GetMapping(path = "/api/contracts/find")
    public List<Contract> getContractsRest(
            @RequestParam int clientId
    ){
        return dashboardService.getContracts(clientId);
    }

    @GetMapping(path = "/api/contracts/findFree")
    public List<Contract> getFreeContractsRest() {

        return contractsRepository.findContractsToChose();
    }

    @GetMapping(path = "/api/example")
    public MyRestResponse getExample() {
        MyRestResponse restResponse = new MyRestResponse();

        restResponse.setName("zzz");
        restResponse.setList(Arrays.asList("1", "2", "3"));

        return restResponse;
    }
}

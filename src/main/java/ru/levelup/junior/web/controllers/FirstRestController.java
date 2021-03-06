package ru.levelup.junior.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        return dashboardService.getContractsByClientId(clientId);
    }

    @GetMapping(path = "/api/contracts/findFree")
    public Page<Contract> getFreeContractsRest(
            @RequestParam int size,
            @RequestParam int page
    ) {
       return contractsRepository.findContractsToChosePagination(
//                Sort.by("phoneNumber"),
//                Sort.unsorted(),
//                Sort.by("phoneNumber").descending(),
//                Sort.by(Sort.Order.desc("phoneNumber"), Sort.Order.asc("clientName"))
                PageRequest.of(page - 1, size, Sort.by("phoneNumber"))
                );
    }

    @GetMapping(path = "/api/example")
    public MyRestResponse getExample() {
        MyRestResponse restResponse = new MyRestResponse();

        restResponse.setName("zzz");
        restResponse.setList(Arrays.asList("1", "2", "3"));

        return restResponse;
    }
}

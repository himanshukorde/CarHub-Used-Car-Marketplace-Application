package com.car_evaluation.controller;


import com.car_evaluation.entity.Agent;
import com.car_evaluation.entity.Area;
import com.car_evaluation.entity.CustomerVisit;
import com.car_evaluation.payload.APIResponse;
import com.car_evaluation.repository.AgentRepository;
import com.car_evaluation.repository.AreaRepository;
import com.car_evaluation.repository.CustomerVisitRepository;
import com.car_evaluation.service.AllocateAgent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.*;

@RestController
@RequestMapping("/api/v1/crm")
public class CRMController {

    private AreaRepository areaRepository;
    private final AgentRepository agentRepository;
    private CustomerVisitRepository customerVisitRepository;
    private AllocateAgent allocateAgent;


    public CRMController(AreaRepository areaRepository,
                         AgentRepository agentRepository, CustomerVisitRepository customerVisitRepository, AllocateAgent allocateAgent) {
        this.areaRepository = areaRepository;
        this.agentRepository = agentRepository;
        this.customerVisitRepository = customerVisitRepository;

        this.allocateAgent = allocateAgent;
    }

    @GetMapping("/getAgent/{pinCode}")
    public ResponseEntity<List<Area>> getAgentDetails(@PathVariable String pinCode){
        List<Area> agentDetails = areaRepository.findByPinCode(pinCode);
        return new ResponseEntity<>(agentDetails, HttpStatus.OK);

    }

    @PutMapping("/allocate-agent/{customerId}/{agentId}")
    public ResponseEntity<APIResponse<String>> allocateAgent(@PathVariable long customerId, @PathVariable long agentId){
        APIResponse<String> response = allocateAgent.agentAllocation(customerId, agentId);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}

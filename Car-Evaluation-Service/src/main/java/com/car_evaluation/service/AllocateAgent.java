package com.car_evaluation.service;

import com.car_evaluation.constants.AppConstants;
import com.car_evaluation.entity.Agent;
import com.car_evaluation.entity.CustomerVisit;
import com.car_evaluation.payload.APIResponse;
import com.car_evaluation.payload.EmailRequest;
import com.car_evaluation.repository.AgentRepository;
import com.car_evaluation.repository.CustomerVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AllocateAgent {
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    private CustomerVisitRepository customerVisitRepository;
    @Autowired
    private KafkaTemplate<String, EmailRequest> kafkaTemplate;

    public APIResponse<String> agentAllocation(long customerId,long agentId){
        APIResponse<String> response = new APIResponse<>();
        Optional<Agent> opAgent = agentRepository.findById(agentId);
        Agent agent = null;
        if(opAgent.isPresent()){
            agent = opAgent.get();
        }
        CustomerVisit customerVisit = customerVisitRepository.findById(customerId).get();
        customerVisit.setAgent(agent);
        customerVisitRepository.save(customerVisit);
        response.setData("Success");
        response.setStatus(201);
        response.setMessage("Agent Allocated");
        EmailRequest request = new EmailRequest("himanshukorde2002@gmail.com",
                "Agent Allocation","Agent Allocated Successfully");
        kafkaTemplate.send(AppConstants.TOPIC, request);
        return response;


    }
}

package com.safety.car.utils.mappers;

import com.safety.car.models.dto.rest.PolicyApprovalDto;
import com.safety.car.models.entity.PolicyDetails;
import com.safety.car.models.entity.PolicyRequest;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.services.interfaces.PolicyDetailsService;
import com.safety.car.services.interfaces.UserService;
import com.safety.car.utils.mappers.interfaces.PolicyRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PolicyRequestMapperImpl implements PolicyRequestMapper {

    private final PolicyDetailsService policyDetailsService;
    private final UserService userService;

    @Autowired
    public PolicyRequestMapperImpl(PolicyDetailsService policyDetailsService, UserService userService) {
        this.policyDetailsService = policyDetailsService;
        this.userService = userService;
    }

    @Override
    public PolicyRequest fromDto(PolicyApprovalDto policyApprovalDto) {
        var policyToUpdate = new PolicyRequest();

        PolicyDetails policyDetails = policyDetailsService.getById(policyApprovalDto.getPolicyId());
        policyToUpdate.setPolicyDetails(policyDetails);

        UserDetails userDetails = userService.getById(policyDetails.getUser().getId());
        policyToUpdate.setUserDetails(userDetails);

        policyToUpdate.setApproved(Boolean.parseBoolean(policyApprovalDto.getIsApproved()));

        return policyToUpdate;
    }
}
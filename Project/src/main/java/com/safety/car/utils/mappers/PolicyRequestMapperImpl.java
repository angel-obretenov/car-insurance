package com.safety.car.utils.mappers;

import com.safety.car.models.dto.rest.PolicyRequestApprovalDto;
import com.safety.car.models.entity.PolicyDetails;
import com.safety.car.models.entity.PolicyRequest;
import com.safety.car.models.entity.UserDetails;
import com.safety.car.services.interfaces.PolicyRequestService;
import com.safety.car.services.interfaces.UserService;
import com.safety.car.utils.mappers.interfaces.PolicyRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PolicyRequestMapperImpl implements PolicyRequestMapper {

    private final UserService userService;
    private final PolicyRequestService policyRequestService;

    @Autowired
    public PolicyRequestMapperImpl(UserService userService,
                                   PolicyRequestService policyRequestService) {
        this.policyRequestService = policyRequestService;
        this.userService = userService;
    }

    @Override
    public PolicyRequest from(PolicyDetails policyDetails) {
        var policyToCreate = new PolicyRequest();

        policyToCreate.setPolicyDetails(policyDetails);

        UserDetails userDetails = userService.getById(policyDetails.getUser().getId());
        policyToCreate.setUserDetails(userDetails);

        return policyToCreate;
    }

    @Override
    public PolicyRequest fromDto(PolicyRequestApprovalDto policyApprovalDto) {
        PolicyRequest policyToUpdate = policyRequestService.getById(policyApprovalDto.getId());

        policyToUpdate.setApproved(policyApprovalDto.getApproved());

        return policyToUpdate;
    }

    @Override
    public PolicyRequest getUpdateFrom(String action) {
        String[] val = action.split(" ");

        PolicyRequest policyToUpdate = policyRequestService.getById(Integer.parseInt(val[1]));

        if (val[0].equals("accept")) {
            policyToUpdate.setApproved(true);
        }

        if (val[0].equals("reject")) {
            policyToUpdate.setApproved(false);
        }

        return policyToUpdate;
    }
}
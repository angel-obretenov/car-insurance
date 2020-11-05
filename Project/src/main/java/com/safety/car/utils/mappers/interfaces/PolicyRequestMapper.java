package com.safety.car.utils.mappers.interfaces;

import com.safety.car.models.dto.rest.PolicyRequestApprovalDto;
import com.safety.car.models.entity.PolicyDetails;
import com.safety.car.models.entity.PolicyRequest;

public interface PolicyRequestMapper {

    PolicyRequest from(PolicyDetails policyDetails);

    PolicyRequest fromDto(PolicyRequestApprovalDto policyRequestApprovalDto);

    /**
     *
     * @param action A string in the format "accept 7", where action[0] is the new status and action[1] is the id of the policy to update
     * @return
     */
    PolicyRequest getUpdateFrom(String action);
}

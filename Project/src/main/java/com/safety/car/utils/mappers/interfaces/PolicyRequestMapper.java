package com.safety.car.utils.mappers.interfaces;

import com.safety.car.models.dto.rest.PolicyRequestCreateDto;
import com.safety.car.models.dto.rest.PolicyRequestApprovalDto;
import com.safety.car.models.entity.PolicyRequest;

public interface PolicyRequestMapper {

    PolicyRequest fromDto(PolicyRequestCreateDto policyRequestCreateDto);

    PolicyRequest fromDto(PolicyRequestApprovalDto policyRequestApprovalDto);

    PolicyRequest getUpdateFrom(String action);
}

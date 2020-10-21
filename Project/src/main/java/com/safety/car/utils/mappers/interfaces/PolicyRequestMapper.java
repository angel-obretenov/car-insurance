package com.safety.car.utils.mappers.interfaces;

import com.safety.car.models.dto.rest.PolicyApprovalDto;
import com.safety.car.models.entity.PolicyRequest;

public interface PolicyRequestMapper {

    PolicyRequest fromDto(PolicyApprovalDto policyApprovalDto);
}

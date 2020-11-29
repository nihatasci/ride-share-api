package com.adesso.challenge.request;

import com.adesso.challenge.enums.PlanStatus;

public class PublishPlanRequest {

	private Long planId;
	private PlanStatus status;

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public PlanStatus getStatus() {
		return status;
	}

	public void setStatus(PlanStatus status) {
		this.status = status;
	}

}

package com.adesso.challenge.response;

public class AddPlanResponse extends BaseResponse {
	private Long planId;

	public AddPlanResponse() {
		
	}
	
	public AddPlanResponse(Long planId, BaseResponse response) {
		this.setDescription(response.getDescription());
		this.setStatus(response.getStatus());
		this.planId = planId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}
}

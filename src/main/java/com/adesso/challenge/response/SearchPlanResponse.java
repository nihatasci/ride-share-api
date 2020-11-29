package com.adesso.challenge.response;

import java.util.List;

import com.adesso.challenge.entity.PlanEntity;

public class SearchPlanResponse extends BaseResponse {
	private List<PlanEntity> plans;

	public List<PlanEntity> getPlans() {
		return plans;
	}

	public void setPlans(List<PlanEntity> plans) {
		this.plans = plans;
	}
}

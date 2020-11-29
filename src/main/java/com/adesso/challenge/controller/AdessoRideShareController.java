package com.adesso.challenge.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adesso.challenge.entity.PlanEntity;
import com.adesso.challenge.entity.UserEntity;
import com.adesso.challenge.enums.PlanStatus;
import com.adesso.challenge.enums.ResponseStatus;
import com.adesso.challenge.repository.PlanRepository;
import com.adesso.challenge.repository.UserRepository;
import com.adesso.challenge.request.AddPlanRequest;
import com.adesso.challenge.request.JoinPlanRequest;
import com.adesso.challenge.request.PublishPlanRequest;
import com.adesso.challenge.request.SearchPlanRequest;
import com.adesso.challenge.response.AddPlanResponse;
import com.adesso.challenge.response.BaseResponse;
import com.adesso.challenge.response.SearchPlanResponse;
import com.adesso.challenge.util.MapUtil;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/plan")
public class AdessoRideShareController {

	@Autowired
	PlanRepository planRepository;

	@Autowired
	UserRepository userRepository;

	@PostMapping("/add")
	public AddPlanResponse addPlan(@RequestBody AddPlanRequest request) {
		if (!MapUtil.isCityExists(request.getStartCity()) || !MapUtil.isCityExists(request.getEndCity())) {
			return new AddPlanResponse(null, new BaseResponse("Şehir bulunamadı", ResponseStatus.WARNING));
		}

		PlanEntity plan = new PlanEntity();
		plan.setCreatedBy(request.getCreatedBy());
		plan.setDate(request.getDate());
		plan.setDescription(request.getDescription());
		plan.setEndCity(request.getEndCity());
		plan.setStartCity(request.getStartCity());
		plan.setSeatCount(request.getSeatCount());
		plan.setStatus(PlanStatus.AC.getText());

		plan = planRepository.save(plan);

		return new AddPlanResponse(plan.getId(),
				new BaseResponse("Plan başarı ile kaydedildi.", ResponseStatus.SUCCESS));
	}

	@PostMapping("/publish")
	public BaseResponse publishPlan(@RequestBody PublishPlanRequest request) {
		try {
			PlanEntity plan = planRepository.findById(request.getPlanId()).orElseThrow();
			plan.setStatus(request.getStatus().getText());
			planRepository.save(plan);
		} catch (Exception e) {
			return new BaseResponse(e.getLocalizedMessage(), ResponseStatus.ERROR);
		}
		return new BaseResponse("Isleminiz başarı ile gerçekleştirildi.", ResponseStatus.SUCCESS);
	}

	@PostMapping("/search")
	public SearchPlanResponse searchPlan(@RequestBody SearchPlanRequest request) {
		SearchPlanResponse response = new SearchPlanResponse();
		if (!MapUtil.isCityExists(request.getStartCity()) || !MapUtil.isCityExists(request.getEndCity())) {
			response.setDescription("Şehir bulunamadı.");
			response.setStatus(ResponseStatus.WARNING);
			return response;
		}

		List<PlanEntity> plans = planRepository.findAllByStartCityAndEndCityAndStatus(request.getStartCity(),
				request.getEndCity(), PlanStatus.AC.getText());

		response.setPlans(plans);
		response.setStatus(ResponseStatus.SUCCESS);

		return response;
	}

	@PostMapping("/join")
	public BaseResponse joinPlan(@RequestBody JoinPlanRequest request) {
		try {
			PlanEntity plan = planRepository.findById(request.getPlanId()).orElse(null);
			if (plan == null) {
				return new BaseResponse("Seçili plan bulunamadı.", ResponseStatus.WARNING);
			}
			if (plan.getSeatCount() <= plan.getUsers().size()) {
				return new BaseResponse("Seçili araçta tüm koltuklar doludur.", ResponseStatus.WARNING);
			}
			UserEntity user = getValidUser(request.getUserName());

			if (plan.getUsers().contains(user)) {
				return new BaseResponse("Seçili araçta zaten kaydınız bulunmaktadır.", ResponseStatus.WARNING);
			}
			plan.getUsers().add(user);
			planRepository.save(plan);
		} catch (Exception e) {
			return new BaseResponse(e.getLocalizedMessage(), ResponseStatus.ERROR);
		}

		return new BaseResponse("Kaydınız başarı ile gerçekleştirildi.", ResponseStatus.SUCCESS);
	}

	@GetMapping("/getCityMap")
	public String[][] getCityMap() {
		return MapUtil.cities;
	}

	private UserEntity getValidUser(String userName) throws NoSuchElementException {
		return userRepository.findByUserName(userName).orElseThrow();
	}

}

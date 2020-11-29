package com.adesso.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adesso.challenge.entity.PlanEntity;
import com.adesso.challenge.enums.PlanStatus;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Long> {
	List<PlanEntity> findAllByStartCityAndEndCityAndStatus(String startCity, String endCity, String status);

}

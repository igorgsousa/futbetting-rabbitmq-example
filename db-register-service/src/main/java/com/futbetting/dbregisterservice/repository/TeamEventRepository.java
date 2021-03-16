package com.futbetting.dbregisterservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.futbetting.dbregisterservice.model.TeamEvent;

@Repository
public interface TeamEventRepository extends CrudRepository<TeamEvent, Long> {

}

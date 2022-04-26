package com.roboApocalypse.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.roboApocalypse.entities.Survivor;

@Repository
public interface SurvivorRepository extends MongoRepository<Survivor, Integer> {

	List<Survivor> findByInfectedStatus(boolean b);

	long countByInfectedStatus(boolean b);

}

package com.roboApocalypse.serviceImplementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.roboApocalypse.constant.MessageConstant;
import com.roboApocalypse.entities.Location;
import com.roboApocalypse.entities.Survivor;
import com.roboApocalypse.exception.RobotApocalypse;
import com.roboApocalypse.repositories.SurvivorRepository;
import com.roboApocalypse.service.SequenceGeneratorService;
import com.roboApocalypse.service.SurvivorService;

@Service
public class SurvivorServiceImplementation implements SurvivorService {

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	@Autowired
	private SurvivorRepository survivorRepository;
	@Autowired
	RestTemplate restTemplate;

	
	@Override
	public Survivor addNewSurvivor(Survivor survivor) throws RobotApocalypse {

		try {

			survivor.setId(sequenceGeneratorService.generateSequence(Survivor.SEQUENCE_NAME));
			survivor.setCreatedDate(new Date());
			survivor.setUpdatedDate(new Date());
			survivorRepository.save(survivor);
			return survivor;
		} catch (Exception e) {
			throw new RobotApocalypse(RobotApocalypse.BAD_REQUEST, e.getLocalizedMessage());
		}
	}

	public Survivor getSurvivorById(int survivorId) throws RobotApocalypse {
		return survivorRepository.findById(survivorId)
				.orElseThrow(() -> new RobotApocalypse(RobotApocalypse.NOT_FOUND, MessageConstant.RECORD_NOT_FOUND));
	}

	
	@Override
	public String updateLastLocation(int survivorId, Location updatedLocation) throws RobotApocalypse {

		Survivor survivor = getSurvivorById(survivorId);

		try {

			survivor.setLastLocation(updatedLocation);
			survivor.setUpdatedDate(new Date());
			survivorRepository.save(survivor);
			return MessageConstant.RECORD_UPDATED_SUCCESSFULLY;

		} catch (Exception e) {
			throw new RobotApocalypse(RobotApocalypse.BAD_REQUEST, e.getLocalizedMessage());
		}
	}

	
	@Override
	public String reportContamination(int survivorId, int reportedBySurvivorId) throws RobotApocalypse {

		Survivor survivor = getSurvivorById(survivorId);

		try {

			survivor.getReportedBy().add(reportedBySurvivorId);

			if (survivor.getReportedBy().size() >= 3) {
				survivor.setInfectedStatus(true);
				survivorRepository.save(survivor);
				return MessageConstant.SURVIVOR_IS_INFECTED;

			} else {
				survivorRepository.save(survivor);
				return MessageConstant.REPORT_ADDED_SUCCESSFULLY;
			}
		} catch (Exception e) {
			throw new RobotApocalypse(RobotApocalypse.BAD_REQUEST, e.getLocalizedMessage());
		}
	}

	
	@Override
	public List<Survivor> getListofInfectedSurvivors() {
		return survivorRepository.findByInfectedStatus(true);
	}

	
	@Override
	public List<Survivor> getListofNonInfectedSurvivors() {
		return survivorRepository.findByInfectedStatus(false);
	}

	
	
	@Override
	public long getPercentageOfInfectedSurvivors() {
		long countOfAllSurvivors = survivorRepository.count();
		long countOfInfectedSurvivors = survivorRepository.countByInfectedStatus(true);
		long percentage = (countOfInfectedSurvivors / countOfAllSurvivors) * 100;
		return percentage;
	}

	
	@Override
	public long getPercentageOfNonInfectedSurvivors() {
		long countOfAllSurvivors = survivorRepository.count();
		long countOfInfectedSurvivors = survivorRepository.countByInfectedStatus(false);
		long percentage = (countOfInfectedSurvivors / countOfAllSurvivors) * 100;
		return percentage;
	}

	@Override
	public Object getgetAllRobots() {
		return restTemplate.getForEntity("https://robotstakeover20210903110417.azurewebsites.net/robotcpu",
				Object.class);

	}

}

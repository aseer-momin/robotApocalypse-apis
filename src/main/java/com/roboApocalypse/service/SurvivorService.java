package com.roboApocalypse.service;

import java.util.List;

import com.roboApocalypse.entities.Location;
import com.roboApocalypse.entities.Survivor;
import com.roboApocalypse.exception.RobotApocalypse;

public interface SurvivorService {

	Object getgetAllRobots();


	long getPercentageOfNonInfectedSurvivors();


	long getPercentageOfInfectedSurvivors();


	List<Survivor> getListofNonInfectedSurvivors();


	List<Survivor> getListofInfectedSurvivors();


	String reportContamination(int survivorId, int reportedBySurvivorId) throws RobotApocalypse;


	String updateLastLocation(int survivorId, Location updatedLocation) throws RobotApocalypse;


	Survivor addNewSurvivor(Survivor survivor) throws RobotApocalypse;

}

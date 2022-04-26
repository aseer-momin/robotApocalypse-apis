package com.roboApocalypse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roboApocalypse.constant.MessageConstant;
import com.roboApocalypse.entities.Location;
import com.roboApocalypse.entities.Survivor;
import com.roboApocalypse.exception.RobotApocalypse;
import com.roboApocalypse.service.SurvivorService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/survivors")
@CrossOrigin
public class SurvivorController {

	@Autowired
	private SurvivorService survivorService;

	@ApiOperation(value = "add new survivor", response = Object.class)
	@ApiResponses(value = { @ApiResponse(code = 200, response = Object.class, message = "OK"),
			@ApiResponse(code = 400, message = "system generated") })
	@PostMapping
	public Survivor addNewSurvivor(@RequestBody Survivor survivor) throws RobotApocalypse {
		return survivorService.addNewSurvivor(survivor);
	}

	@ApiOperation(value = "report Contamination", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = MessageConstant.SURVIVOR_IS_INFECTED),
			@ApiResponse(code = 200, message = MessageConstant.REPORT_ADDED_SUCCESSFULLY),
			@ApiResponse(code = 400, message = "system generated") })
	@PostMapping("/report/survivorId/{survivorId}/reportedBySurvivorId/{reportedBySurvivorId}")
	public String reportContamination(@PathVariable int survivorId, @PathVariable int reportedBySurvivorId)
			throws RobotApocalypse {
		return survivorService.reportContamination(survivorId, reportedBySurvivorId);
	}

	@ApiOperation(value = "update Last Location", response = String.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = MessageConstant.RECORD_UPDATED_SUCCESSFULLY),
			@ApiResponse(code = 400, message = "system generated") })
	@PutMapping("/updateLocation/{survivorId}/survivorId")
	public String updateLastLocation(@PathVariable int survivorId, @RequestBody Location updatedLocation)
			throws RobotApocalypse {
		return survivorService.updateLastLocation(survivorId, updatedLocation);
	}

	@ApiOperation(value = "get list Of Infected Survivors", response = List.class)
	@ApiResponse(code = 200, message = "OK")
	@GetMapping("/listOfInfectedSurvivors")
	public List<Survivor> getListofInfectedSurvivors() {
		return survivorService.getListofInfectedSurvivors();
	}

	@ApiOperation(value = "get list Of Non Infected Survivors", response = List.class)
	@ApiResponse(code = 200, message = "OK")
	@GetMapping("/listOfNonInfectedSurvivors")
	public List<Survivor> getListofNonInfectedSurvivors() {
		return survivorService.getListofNonInfectedSurvivors();
	}

	@ApiOperation(value = "get Percentage Of Infected Survivors", response = Long.class)
	@ApiResponse(code = 200, message = "OK")
	@GetMapping("/percentageOfInfectedSurvivors")
	public long getPercentageOfInfectedSurvivors() {
		return survivorService.getPercentageOfInfectedSurvivors();
	}

	@ApiOperation(value = "get Percentage Of Non Infected Survivors", response = Long.class)
	@ApiResponse(code = 200, message = "OK")
	@GetMapping("/percentageOfNonInfectedSurvivors")
	public long getPercentageOfNonInfectedSurvivors() {
		return survivorService.getPercentageOfNonInfectedSurvivors();
	}

	@ApiOperation(value = "get list of all robots", response = Object.class)
	@ApiResponse(code = 200, message = "OK")
	@GetMapping("/allRobots")
	public Object getAllRobots() {
		return survivorService.getgetAllRobots();
	}

}

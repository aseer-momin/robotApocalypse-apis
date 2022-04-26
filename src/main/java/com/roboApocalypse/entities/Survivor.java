package com.roboApocalypse.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.roboApocalypse.enums.Gender;

@Document
public class Survivor {

	public static final String SEQUENCE_NAME = "survivor_sequence";

	@Id
	private int id;
	private String name;
	private Gender gender;
	private int age;
	private Location lastLocation;
	private List<String> resources = new ArrayList<>();
	private List<Integer> reportedBy = new ArrayList<>(); // list of ids of survivors reported.
	private boolean infectedStatus; // status shows the survivor is infected or not, true is infected and false is
									// not infected.
	private Date createdDate;
	private Date updatedDate; // stores the date when details are updated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<String> getResources() {
		return resources;
	}

	public void setResources(List<String> resources) {
		this.resources = resources;
	}

	public List<Integer> getReportedBy() {
		return reportedBy;
	}

	public void setReportedBy(List<Integer> reportedBy) {
		this.reportedBy = reportedBy;
	}

	public boolean isInfectedStatus() {
		return infectedStatus;
	}

	public void setInfectedStatus(boolean infectedStatus) {
		this.infectedStatus = infectedStatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Location getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(Location lastLocation) {
		this.lastLocation = lastLocation;
	}

}

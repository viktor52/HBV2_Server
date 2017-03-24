package net.viralpatel.spring.persistence.entities;

import java.util.Date;

//Stats object that has the average weight for each day
public class Stats{
	private String date;
	private Double average;
	
	public Stats (String date, Double average){
		this.date = date;
		this.average = average;
	}

	public String getDate(){
		return date;
	}

	public Double getAverage(){
		return average;
	}


}

package net.viralpatel.spring.persistence.entities;

import java.util.Date;
import java.util.ArrayList;

//One day that includes exercises
public class Day{
  private Double weight;
	private String date;
	private String wenttogym;
	private ArrayList<Exercises> exercises;


	public Day (Double weight, String date, String wenttogym, ArrayList<Exercises> exercises){
	    this.weight=weight;
	    this.date=date;
	    this.wenttogym=wenttogym;
	    this.exercises=exercises;

	}

	public Double getWeight(){
		return weight;
	}

	public String getDate(){
		return date;
	}

	public String getWentToGym(){
		return wenttogym;
	}

	public ArrayList<Exercises> getExercises(){
		return exercises;
	}
}

package net.viralpatel.spring.persistence.entities;

import java.util.Date;

//Set that has number of reps in them
public class Set{
  private int rep;
	private Double weight;
	private int number;


	public Set (int rep, Double weight, int number){
	    this.rep=rep;
	    this.weight=weight;
	    this.number=number;

	}

	public int getRep(){
		return rep;
	}

	public Double getWeight(){
		return weight;
	}

	public int getNumber(){
		return number;
	}
	public void setWeight(Double weight){
		this.weight = weight;
	}
}

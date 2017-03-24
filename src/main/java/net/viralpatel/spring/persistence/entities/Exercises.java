package net.viralpatel.spring.persistence.entities;

import java.util.Date;
import java.util.ArrayList;

//One Exercise that includes sets.
public class Exercises{
  private String name;
	private ArrayList<Set> set;
	private int id;

	public Exercises (String name, ArrayList<Set> set, int id){
	    this.name=name;
	    this.set=set;
	    this.id=id;
	}

	public String getName(){
		return name;
	}

	public ArrayList<Set> getSet(){
		return set;
	}
	public int getId(){
		return id;
	}
}

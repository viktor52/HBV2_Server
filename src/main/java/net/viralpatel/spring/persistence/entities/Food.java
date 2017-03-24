package net.viralpatel.spring.persistence.entities;

import java.util.Date;

//Food object
public class Food{
  	private String name;
	private String category;
	private String typeofmeal;


	public Food (String name, String category, String typeofmeal){
    this.name=name;
    this.category=category;
    this.typeofmeal=typeofmeal;

	}

	public String getName(){
		return name;
	}

	public String getCategory(){
		return category;
	}

	public String getTypeOfMeal(){
		return typeofmeal;
	}
}

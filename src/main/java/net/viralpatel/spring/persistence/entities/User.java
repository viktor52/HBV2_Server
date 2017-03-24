package net.viralpatel.spring.persistence.entities;

import java.util.Date;

//User that included info about user.
public class User{
	private String name;
	private String password;
	private String email;
	private int age;
	private String username;
	private String goal;
	private String gender;
	private Double userWeight;
	private String nextUpdate;//SKOÐA

	public User (String name, String password, String email, int age, String username, String goal, String gender, Double userWeight, String nextUpdate){
		this.name = name;
		this.password = password;
		this.email = email;
		this.age = age;
		this.username = username;
		this.goal = goal;
		this.gender = gender;
		this.userWeight = userWeight;
		this.nextUpdate=nextUpdate;
	}

	public String getName(){
		return name;
	}

	public String getPassword(){
		return password;
	}

	public String getEmail(){
		return email;
	}

	public int getAge(){
		return age;
	}

	public String getUsername(){
		return username;
	}

	public String getGoal(){
		return goal;
	}

	public String getGender(){
		return gender;
	}

	public Double getUserWeight(){
		return userWeight;
	}

	public String getNextUpdate(){
		return nextUpdate;
	}



}

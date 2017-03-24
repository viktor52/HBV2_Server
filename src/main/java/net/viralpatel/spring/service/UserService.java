package net.viralpatel.spring.service;

import net.viralpatel.spring.persistence.entities.User;
import java.util.*;

import net.viralpatel.spring.persistence.repositories.UserRepository;
import net.viralpatel.spring.persistence.repositories.WorkoutRepository;

public class UserService{

	public UserRepository userRepository = new UserRepository();
	public Password pass = new Password();
	public WorkoutRepository workoutRepository = new WorkoutRepository();
	
	//Creates new user
	public void createNewUser(String name, String password, String email, String username,
	 						String age,String goal, String gender, String weight, String nextUpdate){
		try{
			String hashandsalt = pass.getSaltedHash(password);

			User user = new User(name,hashandsalt,email,Integer.parseInt(age),username,goal,gender,Double.parseDouble(weight),nextUpdate);
			userRepository.createNewUser(user);
			workoutRepository.createCycle(username,nextUpdate);
		}catch(Exception e){

		}


	}

	//Authenticates user trying to login
	public boolean authUser(String username, String password){

		Object user = userRepository.findUser(username);

		if(user instanceof User){
			User userToAuth = (User) user;
			try{
				if(pass.check(password,userToAuth.getPassword())){
					return true;
				}
			}catch(Exception e){
			
			}

		}
		return false;
	}

	//Finds user from the dateabase
	public ArrayList findUser(String username){

		Object userToFind = userRepository.findUser(username);

		if(userToFind instanceof User){
			User userFound = (User) userToFind;

			ArrayList user = new ArrayList();
			String name = userFound.getName();
			String email = userFound.getEmail();
			String goal = userFound.getGoal();
			int age = userFound.getAge();
			String gender = userFound.getGender();
			Double weight = userFound.getUserWeight();
			String update = userFound.getNextUpdate();

			user.add(name);
			user.add(goal);
			user.add(email);
			user.add(age);
			user.add(gender);
			user.add(weight);
			user.add(update);
			return user;
		}

		return null;

	}
	
	//Updates user info
	public void updateUser(User user){

		userRepository.updateUser(user);
	}

}

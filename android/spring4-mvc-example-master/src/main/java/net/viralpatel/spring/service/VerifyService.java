package net.viralpatel.spring.service;

import net.viralpatel.spring.persistence.entities.User;

import net.viralpatel.spring.persistence.repositories.UserRepository;

// Used for athuntication when registering a new user
public class VerifyService{

	public UserRepository userRepository = new UserRepository();

	public Boolean verifyName(String name){
		if(name.length() <= 2 || name.length() > 30)return false;

		if(name.matches(".*\\d.*"))return false;

		return true;
	}

	public Boolean verifyUsername(String username){

		if(username.length() < 1)return false;

		Object user = userRepository.findUser(username);
		if(user instanceof User)return false;

		return true;
	}

	public Boolean verifyPass(String password){
		if(password.length() < 6)return false;
		return true;
	}

	public Boolean verifyEmail(String email){
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		if(!email.matches(EMAIL_PATTERN))return false;
		return true;
	}

	public Boolean verifyWeight(String weight){
		if(!weight.matches("[0-9]+"))return false;
		return true;
	}

}

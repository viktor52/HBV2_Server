package net.viralpatel.spring.service;

import java.util.ArrayList;
import net.viralpatel.spring.persistence.entities.Food;
import net.viralpatel.spring.persistence.repositories.FoodRepository;

public class FoodService{

	public FoodRepository foodRepository = new FoodRepository();

	//Gets diet plan from repository for specific user
	public ArrayList<Food> getDietPlan(String username){

		Object dietPlanObject = foodRepository.getDietPlan(username);

		if(dietPlanObject instanceof ArrayList<?>){
			ArrayList<Food> dietPlan = (ArrayList<Food>) dietPlanObject;
			return dietPlan;
		}
		return null;
	}

}

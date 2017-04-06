package net.viralpatel.spring.service;

import net.viralpatel.spring.persistence.entities.User;
import net.viralpatel.spring.persistence.entities.Stats;
import net.viralpatel.spring.persistence.entities.Day;
import net.viralpatel.spring.persistence.entities.Exercises;
import net.viralpatel.spring.persistence.entities.Set;
import java.util.*;

import net.viralpatel.spring.persistence.repositories.UserRepository;
import net.viralpatel.spring.persistence.repositories.WorkoutRepository;


public class StatsService{

	public UserRepository userRepository = new UserRepository();
	public WorkoutRepository workoutRepository = new WorkoutRepository();

	//Gets stats about a specific type of workout 
	public ArrayList getAveragePerDay(String username, int id, String goal ){
		Object daysWithID = workoutRepository.getDaysByID(username, id, goal);

		ArrayList<Stats> stats = new ArrayList<Stats>();
		

		if(daysWithID instanceof ArrayList<?>){
			ArrayList<Day> days = (ArrayList<Day>) daysWithID;
			for(int i = 0 ; i < days.size(); i ++){
				double sum =0;
				int count =0;
				ArrayList<Exercises> exer = days.get(i).getExercises();
				for(int j = 0 ; j < exer.size(); j ++){
					ArrayList<Set> sets = exer.get(j).getSet();
					for(int k = 0; k < sets.size(); k++){
						if(sets.get(k).getWeight() != null){
							sum += sets.get(k).getWeight();
							count++;
						}
						else return null;
					}
				}
				stats.add(new Stats(days.get(i).getDate(), sum/count));


			}
		}
		return stats;
	}

}

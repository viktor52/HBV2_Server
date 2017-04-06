package net.viralpatel.spring.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.ArrayList;
import net.viralpatel.spring.persistence.entities.Day;
import net.viralpatel.spring.persistence.entities.Exercises;
import net.viralpatel.spring.persistence.entities.Set;
import net.viralpatel.spring.persistence.entities.Stats;
import net.viralpatel.spring.service.LineChartService;
import net.viralpatel.spring.service.WorkoutService;
import net.viralpatel.spring.service.StatsService;
import net.viralpatel.spring.service.UserService;


@Controller
public class MobileWorkoutController extends HttpServlet{

	private static String VIEW_INDEX = "homepage";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(WorkoutController.class);
	private static WorkoutService workoutService = new WorkoutService();
	private static StatsService statsService = new StatsService();
	private static UserService userService = new UserService();




	//Show user the whole workout for a specific day.
	@RequestMapping(value = "mobile_currentCycle", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Day> getSpecificDayGet(HttpSession session, @RequestParam("username") String username){
		//Checks if user is logged in
		
			
			//Day day = workoutService.getSpecificDay(username, date);

			//Input information from day into view.
			//ArrayList<Exercises> exercises = day.getExercises();
		ArrayList<Day> cycle = workoutService.getCurrentCycle(username);

		return cycle;
		

	}

	//Adds to database the weights that user lifted on that specific day
	@RequestMapping(value = "mobile_workoutOfToday", method= RequestMethod.POST)
	public @ResponseBody Boolean getSpecificDayPost(HttpSession session, @RequestParam("username") String username, @RequestParam("date") String date
		,@RequestBody ArrayList<Double> weights){

		Day day = workoutService.getSpecificDay(username,date);
		ArrayList<Exercises> exercises = day.getExercises();
		ArrayList<Double> inputs = new ArrayList<Double>();



		//Adds input into day object.
		int index = 0;
		for(int i=0; i<exercises.size(); i++){
			Exercises exercise = exercises.get(i);
			ArrayList<Set> sets = exercise.getSet();
			for(int j=0; j<sets.size();j++){
				Set set = sets.get(j);
				Double input = weights.get(index);
				set.setWeight(input);
				index++;
			}
		}
		//Add input into databse
		workoutService.updateDay(day, username);


		return true;
	}
	//Allows the user to see stats about a specific type of workouts. 
	//Can only be accessed from specific day. 
	//Shows you workout stats for that type of day.
	@RequestMapping(value = "/mobile_stats", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Stats> publishStatsPost(HttpSession session, @RequestParam("username") String username, 
		@RequestParam("id") int id, @RequestParam("goal") String goal){

			
			ArrayList<Stats> stats = (ArrayList<Stats>) statsService.getAveragePerDay(username, id, goal);
			return stats;

	}
}

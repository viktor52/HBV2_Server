package net.viralpatel.spring.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import net.viralpatel.spring.persistence.entities.Food;
import net.viralpatel.spring.persistence.entities.Exercises;
import net.viralpatel.spring.persistence.entities.Stats;
import net.viralpatel.spring.service.FoodService;
import net.viralpatel.spring.service.UserService;
import net.viralpatel.spring.service.StatsService;
import net.viralpatel.spring.persistence.repositories.WorkoutRepository;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;





@Controller
public class MobileFoodController extends HttpServlet{

	private static String VIEW_INDEX = "index";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(FoodController.class);
	private static FoodService foodService = new FoodService();
	private static UserService userService = new UserService();
	private static WorkoutRepository workoutRepository = new WorkoutRepository();
	private static StatsService statsService = new StatsService();

	//Gets diet plan recomended specific for each user
	@RequestMapping(value = "/mobile_foodPlan", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Food> publishSuggestedFoodsPost(HttpSession session, ModelMap model){
		
		//Checks if user is logged in
		/*if(session.getAttribute("username") == null){
			VIEW_INDEX = "index";
			return "redirect:/"+VIEW_INDEX;
		}*/

		String username = (String)session.getAttribute("username");

		//Get foodPlan and add it to view
		ArrayList<Food> foodPlan = foodService.getDietPlan("sol");

		//String food = new Gson().toJson(foodPlan);

		return foodPlan;
	}
	@RequestMapping(value = "/mobile_allExercises", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Exercises> publishAllExercisesPost(HttpSession session, ModelMap model){
		
		//Object cc = workoutRepository.getAllExercises("stronger");
		//ArrayList<Exercises> exercises = workoutRepository.getAllExercises("stronger");
		ArrayList<Exercises> exer = (ArrayList<Exercises>) workoutRepository.getAllExercises("stronger");
		return exer;
		//return exercises;
/*
			if(cc instanceof ArrayList<?>){
				System.out.println("eg er inni if bby");
				ArrayList<Exercises> exercises = (ArrayList<Exercises>) cc;
				
				//return exercises;	
			}
*/
			//return exercises;

	}
	/*@RequestMapping(value = "/mobile_stats", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Stats> publishStatsPost(HttpSession session, ModelMap model){
		
		ArrayList<Stats> stats = (ArrayList<Stats>) statsService.getAveragePerDay("sol", 1, "stronger");
		return stats;
		

	}*/

	@RequestMapping(value = "/mobile_info", method = RequestMethod.POST)
	public  String publishInfoPost(@RequestBody String  request_json) {
		
		//ArrayList<Food> test = new ArrayList<Food>(params.values());
		System.out.println(request_json);
		System.out.println("hvað er að frétta herna ???");
		return request_json;

	}				

}
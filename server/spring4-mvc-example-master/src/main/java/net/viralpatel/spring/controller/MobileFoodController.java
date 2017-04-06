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
	public @ResponseBody ArrayList<Food> publishSuggestedFoodsPost(HttpSession session, @RequestParam("username") String username){
		
		

		//Get foodPlan and add it to view
		ArrayList<Food> foodPlan = foodService.getDietPlan(username);


		return foodPlan;
	}
				

}
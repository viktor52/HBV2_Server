package net.viralpatel.spring.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import net.viralpatel.spring.persistence.entities.Food;

import net.viralpatel.spring.service.FoodService;
import net.viralpatel.spring.service.UserService;


@Controller
public class FoodController extends HttpServlet{

	private static String VIEW_INDEX = "index";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(FoodController.class);
	private static FoodService foodService = new FoodService();
	private static UserService userService = new UserService();

	//Gets diet plan recomended specific for each user
	@RequestMapping(value = "foodPlan", method = RequestMethod.GET)
	public String publishSuggestedFoodsPost(HttpSession session, ModelMap model){
		
		//Checks if user is logged in
		if(session.getAttribute("username") == null){
			VIEW_INDEX = "index";
			return "redirect:/"+VIEW_INDEX;
		}

		String username = (String)session.getAttribute("username");

		//Get foodPlan and add it to view
		ArrayList<Food> foodPlan = foodService.getDietPlan(username);

		model.addAttribute("breakfast",foodPlan.get(0).getName());
		model.addAttribute("lunch",foodPlan.get(1).getName());
		model.addAttribute("dinner",foodPlan.get(2).getName());

		VIEW_INDEX = "foodPlan";
		return VIEW_INDEX;
	}

}

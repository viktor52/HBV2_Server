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
import java.util.Date;
import net.viralpatel.spring.persistence.entities.User;
import net.viralpatel.spring.persistence.entities.Day;
import net.viralpatel.spring.persistence.entities.Exercises;
import net.viralpatel.spring.persistence.entities.Food;
import net.viralpatel.spring.persistence.entities.Set;
import java.text.SimpleDateFormat;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import net.viralpatel.spring.service.LineChartService;
import net.viralpatel.spring.service.StatsService;
import net.viralpatel.spring.service.WorkoutService;
import net.viralpatel.spring.persistence.repositories.UserRepository;
import net.viralpatel.spring.persistence.repositories.WorkoutRepository;
import net.viralpatel.spring.persistence.repositories.FoodRepository;


@Controller
public class RepositoryTesterController extends HttpServlet{

	
	private static String VIEW_INDEX = "index";
	//private final static org.slf4j.Logger logger = LoggerFactory.getLogger(BaseController.class);
	private static UserRepository groovyXml=new UserRepository();
	private static WorkoutRepository groovyWorkout = new WorkoutRepository();
	private static FoodRepository groovyFood = new FoodRepository();
	private static WorkoutService workoutService = new WorkoutService();
	private static StatsService statsService = new StatsService();
	

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String login() {
			VIEW_INDEX = "createNewUserTester";
		return VIEW_INDEX;
	}

	@RequestMapping(value = "test", method = RequestMethod.POST)
	public String loginPost(HttpServletRequest request) {
			//SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			User user = new User(request.getParameter("name"), request.getParameter("password"), request.getParameter("email"), Integer.parseInt(request.getParameter("age")), request.getParameter("username"), request.getParameter("goal"), request.getParameter("gender"), Double.parseDouble(request.getParameter("weight")), request.getParameter("nextUpdate"));
			groovyXml.createNewUser(user);

			VIEW_INDEX = "createNewUserTester";
		return VIEW_INDEX;
	}

	@RequestMapping(value = "FindUser", method = RequestMethod.GET)
	public String getUsername(){
		VIEW_INDEX = "testinputFindUser";
		return VIEW_INDEX;
	}

	@RequestMapping(value = "FindUser", method = RequestMethod.POST)
	public String getUsernamePost(HttpServletRequest request, ModelMap model) throws IOException{

		
		Object user = groovyXml.findUser(request.getParameter("username"));
		
		
		if(user instanceof User){
			System.out.println("eg er inni if bby");
			User u = (User) user;
			System.out.println(u.getName());
		}


		VIEW_INDEX = "testFindUser";
				
			

			return VIEW_INDEX;
	}

	@RequestMapping(value = "testUpdate", method = RequestMethod.GET)
	public String update() {
			VIEW_INDEX = "testUpdateUser";
		return VIEW_INDEX;
	}

	@RequestMapping(value = "testUpdate", method = RequestMethod.POST)
	public String updatepost(HttpServletRequest request) {
			User user = new User(null, null, null, Integer.parseInt(request.getParameter("age")), request.getParameter("username"), request.getParameter("goal"), null, Double.parseDouble(request.getParameter("weight")), null);
			groovyXml.updateUser(user);

			VIEW_INDEX = "testUpdateUser";
		return VIEW_INDEX;
	}


	@RequestMapping(value = "testworkout", method = RequestMethod.GET)
	public String getworkout() {
			VIEW_INDEX = "testgetworkout";
		return VIEW_INDEX;
	}

	@RequestMapping(value = "testworkout", method = RequestMethod.POST)
	public String getworkoutPost(HttpServletRequest request) {
			groovyWorkout.createCycle(request.getParameter("username"), request.getParameter("date"));


			VIEW_INDEX = "testgetworkout";
		return VIEW_INDEX;
	}

	@RequestMapping(value = "testgetcycle", method = RequestMethod.GET)
	public String getworkoutCycle() {
			VIEW_INDEX = "testgetCycle";
		return VIEW_INDEX;
	}

	@RequestMapping(value = "testgetcycle", method = RequestMethod.POST)
	public String getworkoutCyclePost(HttpServletRequest request) {
			Object cc = groovyWorkout.getCurrentCycle(request.getParameter("username"));

			if(cc instanceof ArrayList<?>){
			System.out.println("eg er inni if bby");
			ArrayList<Day> days = (ArrayList<Day>) cc;
			ArrayList<Exercises> exer = days.get(0).getExercises();
			System.out.println(days.get(0).getWentToGym());
			System.out.println(exer.get(0).getName());
			}


			VIEW_INDEX = "testgetCycle";
		return VIEW_INDEX;
	}

	@RequestMapping(value = "testgetday", method = RequestMethod.GET)
	public String getSpecificDay() {
			VIEW_INDEX = "testgetday";
		return VIEW_INDEX;
	}

	@RequestMapping(value = "testgetday", method = RequestMethod.POST)
	public String getSpecificDay(HttpServletRequest request) {
			Object cc = groovyWorkout.getSpecificDay(request.getParameter("username"), request.getParameter("date"));

			if(cc instanceof Day){
			System.out.println("eg er inni if bby");
			Day days = (Day) cc;
			System.out.println(days.getWentToGym());
			}


			VIEW_INDEX = "testgetday";
		return VIEW_INDEX;
	}

	@RequestMapping(value = "testUpdateSet", method = RequestMethod.GET)
	public String updateSet() {
			VIEW_INDEX = "testUpdateSet";
		return VIEW_INDEX;
	}

	@RequestMapping(value = "testUpdateSet", method = RequestMethod.POST)
	public String updateSetPost(HttpServletRequest request) {
			
			groovyWorkout.updateSet(request.getParameter("username"), Double.parseDouble(request.getParameter("dbWeight")),Integer.parseInt(request.getParameter("noOfSet")),Integer.parseInt(request.getParameter("exerciseID")), request.getParameter("date"));

		

			VIEW_INDEX = "testUpdateSet";
		return VIEW_INDEX;
	}

/*
	@RequestMapping(value = "testgetdietplan", method = RequestMethod.GET)
	public String getdietplan() {
			VIEW_INDEX = "testgetdietplan";
		return VIEW_INDEX;
	}*/
/*
	@RequestMapping(value = "testgetdietplan", method = RequestMethod.POST)
	public String getdietplanpost(HttpServletRequest request) {
			Object cc = groovyFood.getDietPlan(request.getParameter("username"));

			if(cc instanceof ArrayList<?>){
				System.out.println("eg er inni if bby");
				ArrayList<Food> foods = (ArrayList<Food>) cc;
				System.out.println(foods.get(0).getName()+"   NAME BABY");
			}


			VIEW_INDEX = "testgetdietplan";
		return VIEW_INDEX;
	}

	@RequestMapping(value = "testcreatenewcycleservice", method = RequestMethod.GET)
	public String getnewcycleservice() {
			WorkoutService ws = new WorkoutService();
			Object cc = groovyXml.findUser("smm");
			if(cc instanceof User){
				User user = (User) cc;
				workoutService.createNewCycle(user);
				System.out.println("-------------------------------");
			}
			

			VIEW_INDEX = "testgetdietplan";
		return VIEW_INDEX;
	}
*/
	@RequestMapping(value = "testwtg", method = RequestMethod.GET)
	public String getwtg() {
			VIEW_INDEX = "testwtg";
		return VIEW_INDEX;
	}

	@RequestMapping(value = "testwtg", method = RequestMethod.POST)
	public String getwtgpost(HttpServletRequest request) {
			groovyWorkout.wentToGym(request.getParameter("username"), request.getParameter("date"));



			VIEW_INDEX = "testwtg";
		return VIEW_INDEX;
	}



/*
	@RequestMapping(value = "testUpdateSetDay", method = RequestMethod.GET)
	public String updatetestshit(HttpServletRequest request) {
			
			//groovyWorkout.updateSet(request.getParameter("username"), Double.parseDouble(request.getParameter("dbWeight")),Integer.parseInt(request.getParameter("noOfSet")),Integer.parseInt(request.getParameter("exerciseID")), request.getParameter("date"));
			//(Double weight, String date, String wenttogym, ArrayList<Exercises> exercises

			WorkoutService ws = new WorkoutService();
			Object cc = groovyXml.findUser("sun");
			//Object bb = groovyWorkout.getSpecificDay();
			ArrayList<Exercises> exercises = new ArrayList<Exercises>();
			ArrayList<Set> sets = new ArrayList<Set>();
			sets.add(new Set(6, 12.5, 1));
			exercises.add(new Exercises("name", sets, 1));
			Day day = new Day(70.0,"07/11/2016", null, exercises);
			if(cc instanceof User ){
				User user = (User) cc;
				workoutService.updateDay(day, user);
			}
			
		

			VIEW_INDEX = "testgetdietplan";
		return VIEW_INDEX;
	}
*/
	@RequestMapping(value = "testsum", method = RequestMethod.GET)
	public String getsum() {

			//groovyWorkout.getDaysByID("okok", 1, "stronger");
			LineChartService lcs = new LineChartService();
			lcs.getLineChart("sol", 1, "stronger");
			//statsService.getAveragePerDay("sol", 1, "stronger");

			VIEW_INDEX = "testwtg";
		return VIEW_INDEX;
	}
/*
Her fyrir neðan er nýja testið mitt
*/

	@RequestMapping(value = "testgetdietplan", method = RequestMethod.GET)
	public String getdietplan() {
			VIEW_INDEX = "testgetdietplan";
		return VIEW_INDEX;
	}

	@RequestMapping(value = "testgetdietplan", method = RequestMethod.POST)
	public String getdietplanpost(HttpServletRequest request) {
			//Object cc = groovyFood.getDietPlan(request.getParameter("username"));
			Object cc = groovyWorkout.getAllExercises(request.getParameter("username"));

			if(cc instanceof ArrayList<?>){
				System.out.println("eg er inni if bby");
				ArrayList<Exercises> exercises = (ArrayList<Exercises>) cc;
				Exercises exer = (Exercises) exercises.get(0);
				if(exer instanceof Exercises){
					System.out.println(exer.getName()+ " her er eg inni í controller!!!!!!!");
				}
				
				//Exercises test = (Exercises) exercises.get(0);
				//System.out.println(foods.get(0).getName()+"   NAME BABY");
//				System.out.println(exercises.get(0) + "----Veit ekki hvad a ad vera her");
				//System.out.println(test.getName() + "---------------------");
				/*
				Set test = (Set) exercises.get(0);

				if(test instanceof Set){
					System.out.println(test.getRep());
				}
*/
				
			}


			VIEW_INDEX = "testgetdietplan";
		return VIEW_INDEX;
	}




}

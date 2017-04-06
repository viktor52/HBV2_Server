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
import org.json.JSONObject;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import net.viralpatel.spring.persistence.entities.User;
import java.util.HashMap;
import net.viralpatel.spring.service.UserService;
import net.viralpatel.spring.service.VerifyService;
import net.viralpatel.spring.service.WorkoutService;


@Controller
public class MobileUserController extends HttpServlet{

	private static String VIEW_INDEX = "index";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
	private static UserService userService = new UserService();
	private static VerifyService verifyService = new VerifyService();
	private static WorkoutService workoutService = new WorkoutService();
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


	@RequestMapping(value = "mobile_checkIfValid", method = RequestMethod.GET)

	public @ResponseBody ArrayList registerPost(HttpServletRequest request, HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password
		, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("weight") String weight, @RequestParam("age") String age) {
		System.out.print("------CHECKS__IF__VALID");
		ArrayList ob = new ArrayList();
		ArrayList error = new ArrayList();

		//User user = new User(name,password,email,Integer.parseInt(age),username,goal,gender,Double.parseDouble(weight),nextUpdate);

		//Verifies if parameters are in the correct format
		if(!verifyService.verifyName(name)||!verifyService.verifyUsername(username)||
			!verifyService.verifyPass(password)||!verifyService.verifyEmail(email)||!verifyService.verifyWeight(weight)){
			System.out.print("------CHECKS__IF__ERROR");
			if(!verifyService.verifyName(name)){
				error.add("Invalid name");
				System.out.print("name");

			}
			if(!verifyService.verifyUsername(username)){
				error.add("Invalid username");
				System.out.print("username");
			}
			if(!verifyService.verifyPass(password)){
				error.add("Password must be at least six characters");
				System.out.print("pass");
			}
			if(!verifyService.verifyEmail(email)){
				error.add("Invalid email");
				System.out.print("email");
			}
			if(!verifyService.verifyWeight(weight)){
				error.add("Invalid weight");
				System.out.print("weight");
			}
		}
		//Redirects to homepage
		else{
			ob.add("Succesfull");
			return ob;
		}
		
		return error;
	}


	//Registers new user, redirects to homepage when succesfull
	@RequestMapping(value = "mobile_register", method = RequestMethod.POST)

	public @ResponseBody String registerPost(HttpServletRequest request, HttpSession session, @RequestBody HashMap<String,String> register) {
		
		//Gets parameters from form
		String name = register.get("name");
		String password	= register.get("password");
		String email = register.get("email");
		String username = register.get("username");
		String age = register.get("age");
		String goal = register.get("goal");
		String gender = register.get("gender");
		String weight = register.get("weight");

		ArrayList<String> ob = new ArrayList<String>();
		
		Date date = new Date();
		String nextUpdate = (String)dateFormat.format(date);
		ArrayList error = new ArrayList();

		//User user = new User(name,password,email,Integer.parseInt(age),username,goal,gender,Double.parseDouble(weight),nextUpdate);

		//Verifies if parameters are in the correct format
		if(!verifyService.verifyName(name)||!verifyService.verifyUsername(username)||
			!verifyService.verifyPass(password)||!verifyService.verifyEmail(email)||!verifyService.verifyWeight(weight)){
			
			if(!verifyService.verifyName(name)){
				error.add("Invalid name");
				System.out.print("name");

			}
			if(!verifyService.verifyUsername(username)){
				error.add("Invalid username");
				System.out.print("username");
			}
			if(!verifyService.verifyPass(password)){
				error.add("Password must be at least six characters");
				System.out.print("pass");
			}
			if(!verifyService.verifyEmail(email)){
				error.add("Invalid email");
				System.out.print("email");
			}
			if(!verifyService.verifyWeight(weight)){
				error.add("Invalid weight");
				System.out.print("weight");
			}
		}
		//Redirects to homepage
		else{
			System.out.print("gera");
			userService.createNewUser(name,password,email,username,age,goal,gender,weight,nextUpdate);
			ob.add("true");
			//workoutService.createNewCycle(user);
			return "true";
		}
		System.out.print("endir");
		ob.add("False");
		return null;
	}

	//Gets login page
	@RequestMapping(value = "mobile_login", method = RequestMethod.GET)
	public @ResponseBody String loginGet(HttpSession session, @RequestParam("un") String username, @RequestParam("pw") String password){
	
		if(userService.authUser(username,password)){
			session.setAttribute("username", username);
			return "true";
		}
		else {
			return "false";
		}


	}


	//Gets the user profile page
	@RequestMapping(value = "mobile_myProfile", method = RequestMethod.GET)
	public @ResponseBody ArrayList myProfileGet(HttpSession session, @RequestParam("username") String username){

		ArrayList user = userService.findUser(username);
			
		return user;

	}

	//Gets update user page
	@RequestMapping(value = "mobile_updateUser", method = RequestMethod.GET)
	public @ResponseBody ArrayList updateUserGet(HttpSession session, ModelMap model, @RequestParam("username") String username){
		
		//Checks if users is logged in
	

		ArrayList user = userService.findUser(username);

		//Inputs current user info into view
		return user;
	
	}
	//Updates user informaition
	@RequestMapping(value = "mobile_updateUser", method = RequestMethod.POST)
	public @ResponseBody String updateUserPost(HttpServletRequest request, HttpSession session, @RequestBody HashMap<String,String> updateUser){

		System.out.println("-----UPDATE----------");
		String username = updateUser.get("username");
		String goal = updateUser.get("goal");
		int age	 = Integer.parseInt(updateUser.get("age"));
		String weight = updateUser.get("weight");

		System.out.println(username);
		System.out.println(goal);
		System.out.println(age);
		System.out.println(weight);

		//Changes user info in database
		User user = new User(null,null,null,age,username,goal,null,Double.parseDouble(weight),null);

		userService.updateUser(user);

		return "true";
	}
	//Logs out user
	@RequestMapping(value = "mobile_logout", method = RequestMethod.GET)
	public @ResponseBody boolean logOutGet(HttpSession session) {
		
		session.invalidate();
		return true;
	}
}















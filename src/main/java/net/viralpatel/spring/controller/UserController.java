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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import net.viralpatel.spring.persistence.entities.User;

import net.viralpatel.spring.service.UserService;
import net.viralpatel.spring.service.VerifyService;


@Controller
public class UserController extends HttpServlet{

	private static String VIEW_INDEX = "index";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
	private static UserService userService = new UserService();
	private static VerifyService verifyService = new VerifyService();
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	//Gets starting page
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String indexGet() {
			VIEW_INDEX = "index";
		return VIEW_INDEX;
	}

	//Redirects to login page or register page
	@RequestMapping(value = "index", method = RequestMethod.POST)
	public String indexPost(HttpServletRequest request) {
		if(request.getParameter("login")!=null){
			VIEW_INDEX = "login";
		}
		else if(request.getParameter("register")!=null){
			VIEW_INDEX = "register";
		}
		return "redirect:/"+VIEW_INDEX;
	}

	//Gets register page
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String registerGet() {
			VIEW_INDEX = "register";
		return VIEW_INDEX;
	}

	//Registers new user, redirects to homepage when succesfull
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerPost(HttpServletRequest request, ModelMap model, HttpSession session) {

		//Gets parameters from form
		String name = request.getParameter("name");
		String password	= request.getParameter("password");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String age = request.getParameter("age");
		String goal = request.getParameter("goal");
		String gender = request.getParameter("gender");
		String weight = request.getParameter("weight");
		
		Date date = new Date();
		String nextUpdate = (String)dateFormat.format(date);
		ArrayList error = new ArrayList();

		//Verifies if parameters are in the correct format
		if(!verifyService.verifyName(name)||!verifyService.verifyUsername(username)||
			!verifyService.verifyPass(password)||!verifyService.verifyEmail(email)||!verifyService.verifyWeight(weight)){
			
			if(!verifyService.verifyName(name)){
				error.add("Invalid name");
			}
			if(!verifyService.verifyUsername(username)){
				error.add("Invalid username");
			}
			if(!verifyService.verifyPass(password)){
				error.add("Password must be at least six characters");
			}
			if(!verifyService.verifyEmail(email)){
				error.add("Invalid email");
			}
			if(!verifyService.verifyWeight(weight)){
				error.add("Invalid weight");
			}
		}
		//Redirects to homepage
		else{
			session.setAttribute("username", username);
			userService.createNewUser(name,password,email,username,age,goal,gender,weight,nextUpdate);
			VIEW_INDEX = "homepage";
			return "redirect:/"+VIEW_INDEX;
		}

		//Keeps input if not succesful
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		model.addAttribute("age", age);
		model.addAttribute("goal", goal );
		model.addAttribute("gender", gender);
		model.addAttribute("weight", weight);
		model.addAttribute("error", error);

		return null;
	}

	//Gets login page
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginGet(){
		VIEW_INDEX = "login";
		return VIEW_INDEX;
	}

	//Authenticates the login, redirects to homepage if succesful
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginPost(HttpServletRequest request, ModelMap model, HttpSession session){
		
		//Gets parameters
		String password	= request.getParameter("pw");
		String username = request.getParameter("person_id");

		if(userService.authUser(username, password)){
			session.setAttribute("username", username);
			VIEW_INDEX = "homepage";	
			return  "redirect:/"+VIEW_INDEX;
		}
		else{
			model.addAttribute("error", "Invalid Username or Password");
			return null;
		}

	}
	//Gets the user profile page
	@RequestMapping(value = "myProfile", method = RequestMethod.GET)
	public String myProfileGet(HttpSession session, ModelMap model){
		
		//Checks if user is logged in
		if(session.getAttribute("username") == null){
			VIEW_INDEX = "index";
			return "redirect:/"+VIEW_INDEX;
		}

		String username = (String)session.getAttribute("username");

		ArrayList user = userService.findUser(username);

		//Adds user info into view
		model.addAttribute("name",user.get(0));
		model.addAttribute("goal",user.get(1));
		model.addAttribute("email",user.get(2));
		model.addAttribute("age",user.get(3));
		model.addAttribute("gender",user.get(4));
		model.addAttribute("weight",user.get(5));

		VIEW_INDEX = "myProfile";
		return VIEW_INDEX;
	}
	//Redirects to update user page
	@RequestMapping(value = "myProfile", method = RequestMethod.POST)
	public String myProfilePost(){
		

		VIEW_INDEX = "updateUser";
		return "redirect:/"+VIEW_INDEX;
	}
	//Gets update user page
	@RequestMapping(value = "updateUser", method = RequestMethod.GET)
	public String updateUserGet(HttpSession session, ModelMap model){
		
		//Checks if users is logged in
		if(session.getAttribute("username") == null){
			VIEW_INDEX = "index";
			return "redirect:/"+VIEW_INDEX;
		}
		String username = (String)session.getAttribute("username");
		ArrayList user = userService.findUser(username);

		//Inputs current user info into view
		model.addAttribute("goal",user.get(1));
		model.addAttribute("email",user.get(2));
		model.addAttribute("age",user.get(3));
		model.addAttribute("gender",user.get(4));
		model.addAttribute("weight",user.get(5));
		model.addAttribute("update",user.get(6));

		return VIEW_INDEX;
	}	
	//Updates user informaition
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public String updateUserPost(HttpServletRequest request, HttpSession session, ModelMap model){

		//Checks if users is logged in
		if(session.getAttribute("username") == null){
			VIEW_INDEX = "index";
			return "redirect:/"+VIEW_INDEX;
		}
		
		String username = (String)session.getAttribute("username");
		String goal = request.getParameter("goal");
		int age	 = Integer.parseInt(request.getParameter("age"));
		String weight = request.getParameter("weight");

		//Changes user info in database
		User user = new User(null,null,null,age,username,goal,null,Double.parseDouble(weight),null);

		userService.updateUser(user);

		VIEW_INDEX = "myProfile";
		return "redirect:/"+ VIEW_INDEX;
	}
	//Logs out user
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logOutGet(HttpSession session) {
		
		session.invalidate();
		VIEW_INDEX = "index";
		return "redirect:/"+VIEW_INDEX;
	}
}















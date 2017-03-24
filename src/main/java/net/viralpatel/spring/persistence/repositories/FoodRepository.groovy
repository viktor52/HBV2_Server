package net.viralpatel.spring.persistence.repositories

import groovy.xml.XmlUtil
import java.security.SecureRandom
import net.viralpatel.spring.persistence.entities.User
import net.viralpatel.spring.persistence.entities.Food

//Databaseconnector for diet plan related stuff
public class FoodRepository {

	//Gets diet plan for user
	def getDietPlan(String username){

		def personFile=new File("${new File(new File(".").getCanonicalPath())}//src//main//resources//persons.xml")
		def personXML= new XmlParser().parse(personFile)
		def foodFile=new File("${new File(new File(".").getCanonicalPath())}//src//main//resources//dietplan.xml")
		def foodXML= new XmlParser().parse(foodFile)

		def userNode = personXML.person.find{it ->
			it.@username == username}

		ArrayList<Food> dietplan = new ArrayList<Food>()
		if(userNode != null){
			def goal = userNode.goal.text()
			def dietCategory=foodXML.category.find{it ->
				it.@id == goal}
			dietCategory.name.each{it ->

			Food foods = new Food(it.text(), goal, it.@typeOfMeal )
			dietplan.add(foods)
			}


		}

		return dietplan
	}

}

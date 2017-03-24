package net.viralpatel.spring.persistence.repositories

import groovy.xml.XmlUtil
import java.security.SecureRandom
import net.viralpatel.spring.persistence.entities.User
import net.viralpatel.spring.persistence.entities.Day
import net.viralpatel.spring.persistence.entities.Exercises
import net.viralpatel.spring.persistence.entities.Set
import java.util.ArrayList

//Databaseconnector for workout releates stuff
public class WorkoutRepository {

	//Creates new workout cycle for user
	def createCycle(String username, String date){
		def personFile=new File("${new File(new File(".").getCanonicalPath())}//src//main//resources//persons.xml")
		def personXML= new XmlParser().parse(personFile)
		def workoutFile=new File("${new File(new File(".").getCanonicalPath())}//src//main//resources//workoutplan.xml")
		def workoutXML= new XmlParser().parse(workoutFile)

		def userNode = personXML.person.find{it ->
			it.@username == username}
		//def df1 = new SimpleDateFormat("dd/MM/yyyy")
		//date = df1.parse(date)
		Date d = Date.parse("dd/MM/yyyy", date)

		if(userNode != null){
			def goal = userNode.goal.text()
			def workoutNode = workoutXML.plan.find{it ->
				it.@id == goal}

			if(workoutNode != null){
				workoutNode.day.each{it ->
					def node = userNode.workoutPlan[0]
					it.@Date = d.format("dd/MM/yyyy")
					it.@Goal = userNode.goal.text()
					//new Node (node,"", d.toString())
					node.append(it)
					d=d.next()
				}
				userNode.nextUpdate[0].value = 	d.format("dd/MM/yyyy")

				personFile.withWriter ("utf-8") {writer ->
					writer.writeLine(new XmlUtil().serialize(personXML))}
			}
		}

	}

	//Gets current cycle for specific user
	def getCurrentCycle(String username){

		def personFile=new File("${new File(new File(".").getCanonicalPath())}//src//main//resources//persons.xml")
		def personXML= new XmlParser().parse(personFile)
		def workoutFile=new File("${new File(new File(".").getCanonicalPath())}//src//main//resources//workoutplan.xml")
		def workoutXML= new XmlParser().parse(workoutFile)

		def userNode = personXML.person.find{it ->
			it.@username == username}

		def nextUpdate = userNode.nextUpdate.text()
		Date d = Date.parse("dd/MM/yyyy", nextUpdate)
		ArrayList<Day> currCycle = new ArrayList<Day>()
		for(int i = 0; i<5; i++){
			d = d.previous()
			def prevDate= d.format("dd/MM/yyyy")
			def workoutDay=userNode.workoutPlan[0].day.find{it ->
				it.@Date == prevDate}

			ArrayList<Exercises> exercises = new ArrayList<Exercises>()

			workoutDay.exercise.each{it ->
				ArrayList<Set> sets = new ArrayList<Set>()
				it.set.each{iterator ->
					Set set = new Set(iterator.reps.text().toInteger(), null, iterator.@id.toInteger() )
					sets.add(set)
				}
				Exercises exercise = new Exercises( it.name.text(), sets, it.@id.toInteger() )
				exercises.add(exercise)
			}

			Day day = new Day(userNode.userWeight.text().toDouble(), prevDate, userNode.wentToGym.text(), exercises)
			currCycle.add(day)
		}

		return currCycle

	}

	//Gets specific day in workout cycle for user
	def getSpecificDay(String username, String date){

		def personFile=new File("${new File(new File(".").getCanonicalPath())}//src//main//resources//persons.xml")
		def personXML= new XmlParser().parse(personFile)
		def workoutFile=new File("${new File(new File(".").getCanonicalPath())}//src//main//resources//workoutplan.xml")
		def workoutXML= new XmlParser().parse(workoutFile)
		def userNode = personXML.person.find{it ->
			it.@username == username}

		def workoutDay = userNode.workoutPlan[0].day.find{it ->
			it.@Date == date}

			ArrayList<Exercises> exercises = new ArrayList<Exercises>()

			workoutDay.exercise.each{it ->
				ArrayList<Set> sets = new ArrayList<Set>()

				it.set.each{iterator ->
					//def check=iterator.dbWeight.findAll{}
					if(iterator.dbWeight[0] != null){
						Set set = new Set(iterator.reps.text().toInteger(), iterator.dbWeight.text().toDouble(), iterator.@id.toInteger() )
					sets.add(set)
					}
					else{
						Set set = new Set(iterator.reps.text().toInteger(), null, iterator.@id.toInteger() )
					sets.add(set)
					}

				}
				Exercises exercise = new Exercises( it.name.text(), sets, it.@id.toInteger() )
				exercises.add(exercise)
			}


			Day day = new Day(userNode.userWeight.text().toDouble(), date, workoutDay.wentToGym.text(), exercises)

			return day

	}

	//Inputs weights that user lifted on specific day into database
	def updateSet(String username, Double dbWeight, int noOfSet, int exerciseID, String date){

		def personFile=new File("${new File(new File(".").getCanonicalPath())}//src//main//resources//persons.xml")
		def personXML= new XmlParser().parse(personFile)
		def workoutFile=new File("${new File(new File(".").getCanonicalPath())}//src//main//resources//workoutplan.xml")
		def workoutXML= new XmlParser().parse(workoutFile)
		def userNode = personXML.person.find{it ->
			it.@username == username}
		if(userNode != null){
		def workoutDay = userNode.workoutPlan[0].day.find{it ->
			it.@Date == date}
			if(workoutDay != null){
				def exerciseNode = workoutDay.exercise.find{it ->
					it.@id == exerciseID.toString()}
				if(exerciseNode != null){
					def setNode = exerciseNode.set.find{it ->
						it.@id == noOfSet.toString()}
					if(setNode != null){
						//def check = setNode.dbWeight.find{}
						if(setNode.dbWeight[0] != null){
							setNode.dbWeight[0].value = dbWeight
						}
						else{
							new Node (setNode, "dbWeight", dbWeight)
						}
						//new Node (setNode, "dbWeight", dbWeight)
						personFile.withWriter ("utf-8") {writer ->
							writer.writeLine(new XmlUtil().serialize(personXML))}
						return true

					}


				}

			}

		}


	}

	//Changes wentToGym attribute in day to true when user inputs weights for that day
	def wentToGym(String username, String date){
		def personFile=new File("${new File(new File(".").getCanonicalPath())}//src//main//resources//persons.xml")
		def personXML= new XmlParser().parse(personFile)
		def userNode = personXML.person.find{it ->
			it.@username == username}
		if(userNode != null){
			def dayNode = userNode.workoutPlan[0].day.find{it ->
				it.@Date == date}
				if(dayNode!=null){
					Date d = Date.parse("dd/MM/yyyy", date)
					if(dayNode.wentToGym[0] != null){
						dayNode.wentToGym[0].value = true
					}
					else{
						new Node (dayNode, "wentToGym", "true")
					}

					def loop = true
					while(loop){
						d=d.previous()
						def preDayNode = userNode.workoutPlan[0].day.find{it ->
							it.@Date == d.format("dd/MM/yyyy")}
						if(preDayNode!=null){

							if(preDayNode.wentToGym[0] != null){
								loop = false
							}
							else{
								new Node (preDayNode, "wentToGym", "false")

							}
						}
						else loop = false
					}
					personFile.withWriter ("utf-8") {writer ->
							writer.writeLine(new XmlUtil().serialize(personXML))}
				}


		}

	}
	//Gets list of days with the same id == same type of workout
	def getDaysByID(String username, int id, String goal ){
		def personFile=new File("${new File(new File(".").getCanonicalPath())}//src//main//resources//persons.xml")
		def personXML= new XmlParser().parse(personFile)
		def userNode = personXML.person.find{it ->
			it.@username == username}


		if(userNode != null){
			ArrayList<Day> daysByID = new ArrayList<Day>();
			userNode.workoutPlan[0].day.each{it ->
				if(it.@id.toInteger() == id && goal.compareTo(it.@Goal ) == 0){
						//herna var arrylist
						daysByID.addAll(getSpecificDay(username, it.@Date ))
					}
				}
				return daysByID
		}


	}

	//Gets the id of a day from its date. Used to find which type of workout a day is.
	def getIdfromDate(String username,String date){
		def personFile=new File("${new File(new File(".").getCanonicalPath())}//src//main//resources//persons.xml")
		def personXML= new XmlParser().parse(personFile)

		def userNode = personXML.person.find{it ->
			it.@username == username}

		if(userNode != null){
			def dayNode = userNode.workoutPlan[0].day.find{it ->
				it.@Date == date}

			if(dayNode != null){
				def id = dayNode.@id

				return id
			}
		}
		return

	}

	def getAllExercises(String goal){
		def workoutFile=new File("${new File(new File(".").getCanonicalPath())}//src//main//resources//workoutplan.xml")
		def workoutXML= new XmlParser().parse(workoutFile)

		def planNode = workoutXML.plan.find{it ->
			it.@id == goal}
		ArrayList<Exercises> exercises = new ArrayList<Exercises>()
		if(planNode != null){
			
			planNode.day.each{dayIt ->
				
				//ArrayList<Exercises> exercises = new ArrayList<Exercises>()
				dayIt.exercise.each{exerIt ->
					
						ArrayList<Set> sets = new ArrayList<Set>()
						exerIt.set.each{setIt ->

							Set set = new Set(setIt.reps.text().toInteger(), 1.0, setIt.@id.toInteger() )
							sets.add(set)


						}
						//print sets.get(0).getRep()
						//return sets
					Exercises exercise = new Exercises(exerIt.name.text(), sets, exerIt.@id.toInteger())
					exercises.add(exercise)
					//print exercises
				}
				//return exercises
			}

		}
		Exercises test = (Exercises) exercises[0]
		println test.getName()
		println test.getSet()
		println test.getId()
		return exercises
		
	}



}

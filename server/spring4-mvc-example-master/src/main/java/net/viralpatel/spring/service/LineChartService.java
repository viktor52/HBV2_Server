package net.viralpatel.spring.service;

import net.viralpatel.spring.persistence.entities.User;
import net.viralpatel.spring.persistence.entities.Stats;
import net.viralpatel.spring.persistence.entities.Day;
import net.viralpatel.spring.persistence.entities.Exercises;
import net.viralpatel.spring.persistence.entities.Set;
import java.util.*;

import net.viralpatel.spring.persistence.repositories.UserRepository;
import net.viralpatel.spring.persistence.repositories.WorkoutRepository;

import java.io.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartService{

   //Gets progress chart for user for a specifci type of workout
   public void getLineChart(String username, int id, String goal) {

      StatsService ss = new StatsService();

      ArrayList<Stats> stats = ss.getAveragePerDay(username, id, goal);

      try{
       DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
      for(int i = 0; i<stats.size(); i++){
         line_chart_dataset.addValue(stats.get(i).getAverage(), "Average Weight", stats.get(i).getDate() );
      }

      JFreeChart lineChartObject = ChartFactory.createLineChart(
         "Progress","Date",
         "Average Weight",
         line_chart_dataset,PlotOrientation.VERTICAL,
         true,true,false);

      int width = 640; /* Width of the image */
      int height = 480; /* Height of the image */
      File lineChart = new File( "src/main/webapp/resources/img/"+username+".jpeg" );
      ChartUtilities.saveChartAsJPEG(lineChart ,lineChartObject, width ,height);
      }
      catch(Exception e){

      }

   }

}

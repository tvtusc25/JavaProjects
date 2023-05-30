//Name: Trey Tuscai
//Date: Sept. 27 2021
//File: LifeSimulation.java
//Section: A
//Project: 2
//Course: CS231

package gameOfLife;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class LifeSimulation {

	public static void main(String[] args) throws InterruptedException {
		//Landscape height is 1st command line arg
		//Landscape width is 2nd command line arg
		//density of alive cells is 3rd command line arg
		//number of update iterations is 4th command line arg
		int scapeHeight = Integer.parseInt(args[0]);
	     int scapeWidth = Integer.parseInt(args[1]);
	     double density = Double.parseDouble(args[2]);
	     int iterations = Integer.parseInt(args[3]);
		Landscape scape = new Landscape(scapeHeight,scapeWidth);
	     Random gen = new Random();

	      // initialize the grid to be 30% full
	      for (int i = 0; i < scape.getRows(); i++) {
	          for (int j = 0; j < scape.getCols(); j++ ) { 
	              scape.getCell( i, j ).setAlive( gen.nextDouble() <= density );
	          }
	      }
	      
	      //display landscape
	      //update and repaint every 250 ms based on num of iterations
	      LandscapeDisplay display = new LandscapeDisplay(scape, 8);
	      for (int i = 0; i <= iterations; i++)
 	      {
	     	 scape.advance();
	     	 display.repaint();
	     	 Thread.sleep(250);
 	      }
	      
	      display.getNext().addActionListener(new ActionListener() 
	      {
	     	 public void actionPerformed(ActionEvent event) 
	     	 {
	     	//do whatever should happen when the button is clicked...
	     		 scape.advance();
	     	      display.repaint();
	     	 }
	      });
	
	   }

}


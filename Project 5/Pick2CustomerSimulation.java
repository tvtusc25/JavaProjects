//Name: Trey Tuscai
//Date: Oct. 27 2021
//File: Pick2CustomerSimulation.java
//Section: A
//Project: 5
//Course: CS231

package checkoutLines;

import java.util.ArrayList;
import java.util.Random;

public class Pick2CustomerSimulation 
{
	//sets number of checkout lines and customers
	public static void main(String[] args) throws InterruptedException {
		    Random gen = new Random();
		    ArrayList<CheckoutAgent> checkouts = new ArrayList<CheckoutAgent>(5);

		    for(int i=0;i<5;i++) {
		        CheckoutAgent checkout = new CheckoutAgent( i*100+50, 480 );
		        checkouts.add( checkout );
		    }
		    Landscape scape = new Landscape(600,600, checkouts);
		    LandscapeDisplay display = new LandscapeDisplay(scape);
		    
		    for (int j = 0; j < 1000; j++) {
		        Customer cust = new Pick2Customer(1+gen.nextInt(6));
		        int choice = cust.chooseLine( checkouts );
		        checkouts.get(choice).addCustomerToQueue( cust );
		        if (j%100 == 0)
		        {
		     	   scape.printFinishedCustomerStatistics();
		        }
		      //updates every iteration
		        scape.updateCheckouts();
		        display.repaint();
		        Thread.sleep(200);
		    }
		  //prints average time steps and standard deviation
		    //scape.printFinishedCustomerStatistics();

		}
}

//Name: Trey Tuscai
//Date: Oct. 27 2021
//File: LandscapeDisplay.java
//Section: A
//Project: 5
//Course: CS231

package checkoutLines;

/*
Originally written by Bruce A. Maxwell a long time ago.
Updated by Brian Eastwood and Stephanie Taylor more recently

Creates a window using the JFrame class.

Creates a drawable area in the window using the JPanel class.

The JPanel calls the Landscape's draw method to fill in content, so the
Landscape class needs a draw method.
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
* Displays a Landscape graphically using Swing. In this version, we do not assume
* the Landscape is a grid. The only difference between this code and the code for project 4 
* is the testing code in the main method.
*/
public class LandscapeDisplay 
{
  protected JFrame win;
  protected Landscape scape; 
  private LandscapePanel canvas;

  /**
   * Initializes a display window for a Landscape.
   * @param scape the Landscape to display
   */
  public LandscapeDisplay(Landscape scape)
  {
      // setup the window
      this.win = new JFrame("Checkout Queues");
      this.win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      this.scape = scape;

      // create a panel in which to display the Landscape
      this.canvas = new LandscapePanel( this.scape.getWidth(),
					  this.scape.getHeight() );

      // add the panel to the window, layout, and display
      this.win.add(this.canvas, BorderLayout.CENTER);
      this.win.pack();
      this.win.setVisible(true);
  }

  /**
   * Saves an image of the display contents to a file.  The supplied
   * filename should have an extension supported by javax.imageio, e.g.
   * "png" or "jpg".
   *
   * @param filename  the name of the file to save
   */
  public void saveImage(String filename)
  {
      // get the file extension from the filename
      String ext = filename.substring(filename.lastIndexOf('.') + 1, filename.length());

      // create an image buffer to save this component
      Component tosave = this.win.getRootPane();
      BufferedImage image = new BufferedImage(tosave.getWidth(), tosave.getHeight(), 
                                              BufferedImage.TYPE_INT_RGB);

      // paint the component to the image buffer
      Graphics g = image.createGraphics();
      tosave.paint(g);
      g.dispose();

      // save the image
      try
	    {
		ImageIO.write(image, ext, new File(filename));
	    }
      catch (IOException ioe)
	    {
		System.out.println(ioe.getMessage());
	    }
  }

  /**
   * This inner class provides the panel on which Landscape elements
   * are drawn.
   */
  private class LandscapePanel extends JPanel
  {
      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
       * Creates the panel.
       * @param width     the width of the panel in pixels
       * @param height        the height of the panel in pixels
       */
      public LandscapePanel(int width, int height) {
          super();
          this.setPreferredSize(new Dimension(width, height));
          this.setBackground(Color.white);
      }

      /**
       * Method overridden from JComponent that is responsible for
       * drawing components on the screen.  The supplied Graphics
       * object is used to draw.
       * 
       * @param g     the Graphics object used for drawing
       */
      public void paintComponent(Graphics g) {
          super.paintComponent(g);
          scape.draw( g );
      } // end paintComponent
      
  } // end LandscapePanel

  public void repaint() {
	    this.win.repaint();
  }

  // test function that creates a new LandscapeDisplay and populates it with 200 agents.
  public static void main(String[] args) throws InterruptedException {
      Random gen = new Random();
      ArrayList<CheckoutAgent> checkouts = new ArrayList<CheckoutAgent>();
      int windowWidth = 500;
      int windowHeight = 500;

      for(int i=0;i<5;i++) {
          CheckoutAgent checkout = new CheckoutAgent( i*windowWidth/5+50, windowHeight-20 );
          checkouts.add( checkout );
          int numCust = gen.nextInt( 10 );
          for (int j = 0; j < numCust; j++) {
              checkout.addCustomerToQueue( new RandomCustomer(1+gen.nextInt(5)) );
          }
      }
      Landscape scape = new Landscape(windowWidth,windowHeight,checkouts);
      LandscapeDisplay display = new LandscapeDisplay(scape);

      display.repaint();
  }
}
//Name: Trey Tuscai
//Date: Dec. 13 2021
//File: HuntTheWumpus.java
//Section: A
//Project: 9
//Course: CS231

package huntTheWumpus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseEvent;
//import java.awt.Point;

//import javax.imageio.ImageIO;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import java.util.*;

public class HuntTheWumpus {
	/*
	Template created by Bruce A. Maxwell and Stephanie R Taylor

	Your name and header goes here
	*/

	/**
	* Creates a window with two text fields, one buttons, and a large
	* display area. The app then tracks the mouse over the display area.
	**/

	  // These four fields are as in the LandscapeDisplay class (though 
	  // they are now all private)
	  private JFrame win;
	  private LandscapePanel canvas;    
	  private Landscape scape; 
	  private int scale;
	  Hunter hunter;
	  Wumpus wumpus;

	  /** fields related to demo of mouse interaction **/
	  // Unless you have a good reason to report the mouse position in
	  // HuntTheWumpus, you should remove these fields and all the
	  // code that affects them.
	  // There here to demonstrate how you would add a message to the bottom
	  // of the window. For HuntTheWumpus, you may want to use it to indicate
	  // that the Hunter is armed or close to the Wumpus, or dead.
	  JLabel fieldX; // Label field 1, displays the X location of the mouse 
	  JLabel fieldY; // Label field 2, displays the Y location of the mouse 

	  // controls whether the game is playing or exiting
	  private enum PlayState { PLAY, STOP }
	  private PlayState state;

	  /**
	   * Creates the main window
	   * @param scape the Landscape that will hold the objects we display
	   * @param scale the size of each grid element
	   **/		 
	  public HuntTheWumpus() {
	      // The game should begin in the play state.
	      this.state = PlayState.PLAY; 
	      
	      // Create the elements of the Landscape and the game.
	      this.scale = 64; // determines the size of the grid
	      this.scape = new Landscape(scale*10,scale*7);
	      // This is test code that adds a few vertices.
	      // You will need to update this to make a Graph first, then
	      // add the vertices to the Landscape.
	      Graph g = new Graph();
	      
	      Vertex[][] vertArray = new Vertex[7][10];
	      
	      //fill graph with vertices
	      for(int x = 0; x < 7; x++)
	      {
	     	 for(int y = 0; y < 9; y++)
	     	 {
	     		 vertArray[x][y] = new Vertex(x, y);
	     		 g.addVertices(vertArray[x][y]);
	     	 }
	      }
	      
	      Random rand = new Random();	
		 int x_1 = rand.nextInt(7);
		 int y_1 = rand.nextInt(9);
		 int x_2 = rand.nextInt(7);
		 int y_2 = rand.nextInt(9);
	      
	      //add hunter and set it to a vertex
	      hunter = new Hunter(vertArray[x_1][y_1]);
	      this.scape.setHunter(hunter);
	      System.out.println(hunter.getLocation());
	      
	      wumpus = new Wumpus(vertArray[x_2][y_2]);
	      this.scape.setWumpus(wumpus);
	      System.out.println(wumpus.getHome());
	      
	      //add vertices to landscape
	      ArrayList<Vertex> vertices = g.getVertices(); 
	      for(int i = 0; i < vertices.size(); i ++)
	      {
	     	 //vertices.get(i).setVisible(true);
	     	 vertices.get(i).setCost(vertices.get(i).distance(wumpus.getHome()));
	     	 this.scape.addBackgroundAgent(vertices.get(i));
	      }
	      //connect along y axis
	      for(int i = 0; i < vertices.size()-1; i ++)
	      {
	     	 g.addBiEdge(vertices.get(i), vertices.get(i+1));
	      }
	      //connect along x axis
	      for(int i = 0; i < vertices.size()-9; i++)
	      {
	     	 g.addBiEdge(vertices.get(i), vertices.get(i+9));
	      }
	      
	      // Make the main window
	      this.win = new JFrame("Basic Interactive Display");
	      win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

	      // make the main drawing canvas (this is the usual
	      // LandscapePanel we have been using). and put it in the window
	      this.canvas = new LandscapePanel( this.scape.getWidth(),this.scape.getHeight() );
	      this.win.add( this.canvas, BorderLayout.CENTER );
	      this.win.pack();

	      // make the labels and a button and put them into the frame
	      // below the canvas.
	      this.fieldX = new JLabel("X");
	      this.fieldY = new JLabel("Y");
	      JButton quit = new JButton("Quit");
	      JPanel panel = new JPanel( new FlowLayout(FlowLayout.RIGHT));
	      panel.add( this.fieldX );
	      panel.add( this.fieldY );
	      panel.add( quit );
	      this.win.add( panel, BorderLayout.SOUTH);
	      this.win.pack();

	      // Add key and button controls.
	      // We are binding the key control object to the entire window.
	      // That means that if a key is pressed while the window is
	      // in focus, then control.keyTyped will be executed.
	      // Because we are binding quit (the button) to control, control.actionPerformed will
	      // be called if the quit button is pressed. If you make more than one button,
	      // then the same function will be called. Use an if-statement in the function
	      // to determine which button was pressed (check out Control.actionPerformed and
	      // this advice should make sense)
	      Control control = new Control();
	      this.win.addKeyListener(control);
	      this.win.setFocusable(true);
	      this.win.requestFocus();
	      quit.addActionListener( control );
	     

	      // for mouse control
	      // Make a MouseControl object and then bind it to the canvas
	      // (the part that displays the Landscape). When the mouse
	      // enters, exits, moves, or clicks in the canvas, the appropriate
	      // method will be called.
	      MouseControl mc = new MouseControl();
	      this.canvas.addMouseListener( mc );
	      this.canvas.addMouseMotionListener( mc );

	      // The last thing to do is make it all visible.
	      this.win.setVisible( true );

	  }
	  //returns hunter 
		public Hunter getHunter(){
			return this.hunter;
		}
		
		//returns wumpus
		public Wumpus getWumpus(){
			return this.wumpus;
		}

	  private class LandscapePanel extends JPanel {
			
	      /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
	       * Creates the drawing canvas
	       * @param height the height of the panel in pixels
	       * @param width the width of the panel in pixels
	       **/
	      public LandscapePanel(int height, int width) {
	          super();
	          this.setPreferredSize( new Dimension( width, height ) );
	          this.setBackground(Color.white);
	      }

	      /**
	       * Method overridden from JComponent that is responsible for
	       * drawing components on the screen.  The supplied Graphics
	       * object is used to draw.
	       * 
	       * @param g		the Graphics object used for drawing
	       */
	      public void paintComponent(Graphics g) {
	          super.paintComponent(g);
	          scape.draw( g, scale );
	      }
	  } // end class LandscapePanel
	  
	  
	  

	  // This is the class where you define functions that are 
	  // executed when certain mouse events take place.
	  private class MouseControl extends MouseInputAdapter {
	      public void mouseMoved(MouseEvent e) {
	          fieldX.setText( "" + e.getPoint().x );
	          fieldY.setText( "" + e.getPoint().y );
	      }

	      public void mouseDragged(MouseEvent e) {
	          fieldX.setText( "" + e.getPoint().x );
	          fieldY.setText( "" + e.getPoint().y );
	      }
	      
	      public void mousePressed(MouseEvent e) {
	          System.out.println( "Pressed: " + e.getClickCount());
	      }

	      public void mouseReleased(MouseEvent e) {
	          System.out.println( "Released: " + e.getClickCount());
	      }

	      public void mouseEntered(MouseEvent e) {
	          System.out.println( "Entered: " + e.getPoint() );
	      }

	      public void mouseExited(MouseEvent e) {
	          System.out.println( "Exited: " + e.getPoint() );
	      }

	      public void mouseClicked(MouseEvent e) {
	  	    System.out.println( "Clicked: " + e.getClickCount() );
	      }
	  } // end class MouseControl

	  private class Control extends KeyAdapter implements ActionListener {
		 
	      public void keyTyped(KeyEvent e) {
	          System.out.println( "Key Pressed: " + e.getKeyChar() );
	          if( ("" + e.getKeyChar()).equalsIgnoreCase("q") ) {
	              state = PlayState.STOP;
	          }
	          
	          if( ("" + e.getKeyChar()).equalsIgnoreCase(" ") ) {
				if( getHunter().getHunterState() == 1 ){
					getHunter().setHunterState(0);
					System.out.println("Hunter is armed and ready.");
				}
				else if (getHunter().getHunterState() == 0 ){
					getHunter().setHunterState(1);
					System.out.println("Hunter is disarmed.");
				}
			}
	          
	          if( ("" + e.getKeyChar()).equalsIgnoreCase("w") ) 
			{
	          	 if( getHunter().getHunterState() == 1)
				 {
					 scape.getHunter().move(Vertex.Direction.NORTH);
					 if(getHunter().getLocation() == wumpus.getHome())
				      {
						 getHunter().setHunterState(-1);
						 getWumpus().getHome().setVisible(true);
							wumpus.setLifeStatus(1);
							System.out.println("Hunter was killed");
				      }
				 }
					//hunter is armed
	          	 else if( getHunter().getHunterState() == 0)
						{
							System.out.println("Hunter shoots north");
							Vertex neighbor = getHunter().getLocation().getNeighbor(getHunter().getLocation().getX(), getHunter().getLocation().getY()-1);
							while( neighbor != null)
							{
								if( getWumpus().getHome() == neighbor )
								{
									getWumpus().getHome().setVisible(true);
									getWumpus().setLifeStatus(-1);
									getHunter().setHunterState(2);
									System.out.println("Wumpus dies!");
									break;
								}
								neighbor = neighbor.getNeighbor(neighbor.getX(), neighbor.getY()-1);
							}
							
							if( neighbor == null )
							{
								getHunter().setHunterState(-2);
								System.out.println("Hunter shoots and misses");
								getWumpus().getHome().setVisible(true);
								getWumpus().setLifeStatus(1);
							}
						}
			}
	          
	          if( ("" + e.getKeyChar()).equalsIgnoreCase("s") ) 
			{
	          	if( getHunter().getHunterState() == 1)
				 {
					 scape.getHunter().move(Vertex.Direction.SOUTH);
					 if(getHunter().getLocation() == wumpus.getHome())
				      {
						 getHunter().setHunterState(-1);
						 getWumpus().getHome().setVisible(true);
							wumpus.setLifeStatus(1);
							System.out.println("Hunter was killed");
				      }
				 }
				//hunter is armed
	          	else if( getHunter().getHunterState() == 0)
						{
							System.out.println("Hunter shoots south");
							Vertex neighbor = getHunter().getLocation().getNeighbor(getHunter().getLocation().getX(), getHunter().getLocation().getY()+1);
							System.out.println(neighbor);
							while( neighbor != null)
							{
								System.out.println("here");
								if( getWumpus().getHome() == neighbor )
								{
									getWumpus().getHome().setVisible(true);
									getWumpus().setLifeStatus(-1);
									getHunter().setHunterState(2);
									System.out.println("Wumpus dies!");
									break;
								}
								neighbor = neighbor.getNeighbor(neighbor.getX(), neighbor.getY()+1);
								System.out.println(neighbor);
							}
							
							if( neighbor == null )
							{
								getHunter().setHunterState(-2);
								System.out.println("Hunter shoots and misses");
								getWumpus().getHome().setVisible(true);
								getWumpus().setLifeStatus(1);
							}
						}
			}
	          
	          if( ("" + e.getKeyChar()).equalsIgnoreCase("a") ) 
			{
	          	if( getHunter().getHunterState() == 1)
				 {
					 scape.getHunter().move(Vertex.Direction.WEST);
					 if(getHunter().getLocation() == wumpus.getHome())
				      {
						 getHunter().setHunterState(-1);
						 getWumpus().getHome().setVisible(true);
							wumpus.setLifeStatus(1);
							System.out.println("Hunter was killed");
				      }
				 }
				 
				//hunter is armed
	          	else if( getHunter().getHunterState() == 0)
				{
							System.out.println("Hunter shoots west");
							Vertex neighbor = getHunter().getLocation().getNeighbor(getHunter().getLocation().getX()-1, getHunter().getLocation().getY());
							while( neighbor != null)
							{
								if( getWumpus().getHome() == neighbor )
								{
									getWumpus().getHome().setVisible(true);
									getWumpus().setLifeStatus(-1);
									getHunter().setHunterState(2);
									System.out.println("Wumpus dies!");
									break;
								}
								neighbor = neighbor.getNeighbor(neighbor.getX()-1, neighbor.getY());
							}
							
							if( neighbor == null )
							{
								getHunter().setHunterState(-2);
								System.out.println("Hunter shoots and misses");
								getWumpus().getHome().setVisible(true);
								getWumpus().setLifeStatus(1);
							}
				}
			}
	          
	          if( ("" + e.getKeyChar()).equalsIgnoreCase("d") ) 
			{
	          	if( getHunter().getHunterState() == 1)
				{
	          		scape.getHunter().move(Vertex.Direction.EAST);
					 if(getHunter().getLocation() == wumpus.getHome())
				      {
						 getHunter().setHunterState(-1);
						 getWumpus().getHome().setVisible(true);
							wumpus.setLifeStatus(1);
							System.out.println("Hunter was killed");
				      }
				}
				//hunter is armed
				 else if( getHunter().getHunterState() == 0)
				 {
							System.out.println("Hunter shoots east");
							Vertex neighbor = getHunter().getLocation().getNeighbor(getHunter().getLocation().getX()+1, getHunter().getLocation().getY());
							while( neighbor != null)
							{
								if( getWumpus().getHome() == neighbor )
								{
									getWumpus().getHome().setVisible(true);
									getWumpus().setLifeStatus(-1);
									getHunter().setHunterState(2);
									System.out.println("Wumpus dies!");
									break;
								}
								neighbor = neighbor.getNeighbor(neighbor.getX()+1, neighbor.getY());
							}
							
							if( neighbor == null )
							{
								getHunter().setHunterState(-2);
								System.out.println("Hunter shoots and misses");
								getWumpus().getHome().setVisible(true);
								getWumpus().setLifeStatus(1);
							}
			       }
				 
			}
	      }
	      

	      public void actionPerformed(ActionEvent event) {
	          // If the Quit button was pressed
	          if( event.getActionCommand().equalsIgnoreCase("Quit") ) {
			        System.out.println("Quit button clicked");
	              state = PlayState.STOP;
	          }
	      }
	       
	  } // end class Control

	  public void repaint() {
	  	this.win.repaint();
	  }

	  public void dispose() {
		    this.win.dispose();
	  }


	  public static void main(String[] argv) throws InterruptedException {
	      HuntTheWumpus w = new HuntTheWumpus();
	      while (w.state == PlayState.PLAY) {
	          w.repaint();
	          Thread.sleep(33);
	      }
	      System.out.println("Disposing window");
	      w.dispose();
	  }
}

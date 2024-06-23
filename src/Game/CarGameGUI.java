package Game;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Font;

public class CarGameGUI {
	boolean lost = false;
	JFrame frame;
	JPanel CarPanel;
	JPanel EnemyPanel;
	JPanel Background;
	JPanel LostPanel;
	JLayeredPane layers;
	JLabel Score;
	JLabel lblS;
	List<Component> Components = new ArrayList<>();
	//JLayerd
	CarHandler h;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double width = screenSize.getWidth();
	double height = screenSize.getHeight();
	Timer t,e,m;
	int counter = 0;
	int HighestScore;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public CarGameGUI() {
		h = new CarHandler(this);
		h.t.start();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		t = new Timer(1000, UpdateScoreAction);
		e = new Timer(1000, UpdateEnemyAction);
		m = new Timer(25, UpdateEnemyMoveAction);
		t.start();
		e.start();
		frame = new JFrame("Car Game");
		//frame.getContentPane().setLayout(null);
		layers = new JLayeredPane();
		Background = new JPanel();
		//CarPanel = new JPanel(new BorderLayout());
		CarPanel = new JPanel(new BorderLayout());
		CarPanel.setSize((int)width/15, (int)(height/3.8));
		CarPanel.setOpaque(false);
		CarPanel.setLocation((int)(width/2)-((CarPanel.getSize().width)/2),(int)(height/1.6));
		
		Background.setSize((int)width, (int)(height));
		Background.setLocation(0,0);
		Background.setOpaque(false);
		//JLabel car = new JLabel(new ImageIcon("C:/Users/anaal/eclipse-workspace/CarGame/src/Game/RedCar2.png"));
		JLabel car = new JLabel(new ImageIcon("src/Game/RedCar2.png"));
//		JLabel Back = new JLabel(new ImageIcon("C:/Users/anaal/eclipse-workspace/CarGame/src/Game/Road_Top_View (2).gif"));
		JLabel Back = new JLabel(new ImageIcon("src/Game/Road_Top_View (2).gif"));
		Score = new JLabel();
		Score.setFont(new Font("Arial", Font.BOLD, 20));
		Score.setSize(100, 100);
		Score.setOpaque(false);
		Score.setLocation(40,0);
		Background.add(Back);
		CarPanel.add(car, BorderLayout.CENTER);

		layers.add(Background,new Integer(0));
		layers.add(CarPanel,new Integer(1));
		layers.add(Score,new Integer(2));

		frame.setVisible(true);
		frame.setSize((int)width,(int)height);
		//frame.getContentPane().add(Background);
		//frame.getContentPane().add(CarPanel);
		frame.setContentPane(layers);
		
	//	frame.getContentPane().setBackground(new Color(0, 255, 64));
		
		
		//panel.setBounds(706, 380, 10, 10);
		LostPanel = new JPanel();
		LostPanel.setSize(220,100);
		LostPanel.setLocation((int)(width/2)-(LostPanel.getSize().width/2),(int)(height/2)-(LostPanel.getSize().height));
		LostPanel.setVisible(false);
		layers.add(LostPanel,new Integer(3));
		
		lblS = new JLabel("<html>Your Score: "+HighestScore+"<br/>Better luck next time.</html>");
		lblS.setFont(new Font("Arial", Font.BOLD, 20));
		LostPanel.add(lblS);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent e)
	      {
	        System.exit(0);
	      }
	    });
		LostPanel.add(btnFinish);
		//frame.setLocationRelativeTo(null);
		frame.addKeyListener(h);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	ActionListener UpdateScoreAction = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		      if(!lost) {
		    	  Score.setText("Score: "+Integer.toString(counter++)); 
		    	  //System.out.println(counter);
		    	  }
		    }
		};
		ActionListener UpdateEnemyAction = new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  	if(!lost) {
					JLabel enemy = new JLabel(new ImageIcon("src/Game/SilverCar2.png"));
				    EnemyPanel = new JPanel(new BorderLayout());
					EnemyPanel.setSize((int)width/15, (int)(height/4));
					EnemyPanel.setOpaque(false);
					//EnemyPanel.setLocation((int)(width/2.5),(int)(height/40));
					EnemyPanel.setLocation(ThreadLocalRandom.current().nextInt((int)width/8, (int)(width-width/5 + 1)),(int)(height/50));
					
					EnemyPanel.add(enemy, BorderLayout.CENTER);
					layers.add(EnemyPanel,new Integer(1));
					
					Components.add(EnemyPanel);
					
					for(Component i : Components) {
						m.start();
						if (i.getLocation().y >height) {
							layers.remove(i);
							layers.revalidate();
							layers.repaint();
							//System.out.println("Removing Enemy");
						}
					}
			    }
				  	}
			};
			ActionListener UpdateEnemyMoveAction = new ActionListener() {
				  public void actionPerformed(ActionEvent e) {
					  for(Component i : Components) {
						  if(!lost) {
							i.setLocation(i.getLocation().x, i.getLocation().y+10);}
							Area areae = new Area(i.getBounds());
							Area areac = new Area(CarPanel.getBounds());

							if(areae.intersects(areac.getBounds2D())) {
								//System.out.println("You Lost Loser");
								lost = true;
								HighestScore = counter;
								lblS.setText("<html>Your Score: "+(HighestScore-1)+"<br/>Better luck next time.</html>");
								LostPanel.setVisible(lost);
							}
					  }
				    }
				};
}

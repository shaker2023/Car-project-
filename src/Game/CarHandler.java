package Game;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;

import javax.swing.Timer;

public class CarHandler implements KeyListener{
	CarGameGUI GUI;
	Timer t;
	int CarSpeed = 15;
	boolean LPressed;
	boolean RPressed;
	public CarHandler(CarGameGUI g) {
		t = new Timer(25, UpdateCarAction);
		this.GUI = g;
	}
	
	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		int code = k.getKeyCode();
		int XPos = GUI.CarPanel.getLocation().x;
		int YPos = GUI.CarPanel.getLocation().y;
		if(code == 65) {
			LPressed=true;
			//if(!GUI.lost) {
			//if(XPos-CarSpeed>200) {
				//GUI.CarPanel.setLocation((XPos-CarSpeed), YPos);
			//}
			//}
		}
		else if(code == 68) {
			RPressed=true;
			//if(!GUI.lost) {
			//if(XPos+CarSpeed<GUI.width-320) {
				//GUI.CarPanel.setLocation((XPos+CarSpeed), YPos);
			//}
			//}
		}
	}
	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		
		
			LPressed=false;
		
		
			RPressed=false;
	
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	ActionListener UpdateCarAction = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  if(!GUI.lost) {
				  if(LPressed) {
					  if(GUI.CarPanel.getLocation().x-CarSpeed>200) {
					  GUI.CarPanel.setLocation(GUI.CarPanel.getLocation().x-CarSpeed, GUI.CarPanel.getLocation().y);
					  }
				}
				  else if(RPressed) {
					  if(GUI.CarPanel.getLocation().x+CarSpeed<GUI.width-320) {
					  GUI.CarPanel.setLocation(GUI.CarPanel.getLocation().x+CarSpeed, GUI.CarPanel.getLocation().y);
					  }
					  }
			  }
		  }
		};
}

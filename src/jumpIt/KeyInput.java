package jumpIt;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for( int i=0; i<handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player){
				//key events for player 1
				if (key == KeyEvent.VK_SPACE) {
					tempObject.setVelY(-5);
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for( int i=0; i<handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player){
				//key events for player 1
				tempObject.setVelY(+5);
				//CHECK FOR NO BUTTONS
			}
			
			if(tempObject.getId()==ID.Enemy){
				tempObject.setVelX(-5);
			}
		}
	}
}

package jumpIt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends GameObject{

	public Enemy(int x, int y, int height, ID id) {
		super(x, y, height, id);
		
	}

	public Rectangle getBounds(){
		return new Rectangle(x, y, 60, height);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 60, height);
		
	}

}

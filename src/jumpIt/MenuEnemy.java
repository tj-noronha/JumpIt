package jumpIt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuEnemy extends GameObject{
private Handler handler;

Random r = new Random();
private Color col;
	public MenuEnemy(int x, int y, int height, ID id) {
		super(x, y, height, id);
		this.handler = handler;
		
		velX = -5;
		
		col = new Color(r.nextInt(255), r.nextInt(255),r.nextInt(255));
	}

	public Rectangle getBounds(){
		return new Rectangle(x, y, 60, height);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
	}

	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect(x, y, 60, height);
		
	}

}

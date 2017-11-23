package jumpIt;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jumpIt.jumpIt.STATE;

public class Menu extends MouseAdapter {
	private jumpIt game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	public Menu(jumpIt game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
   		int my = e.getY();
   		
   
		// play button
		if (mouseOver(mx, my, 210, 110, 200, 64)) {
			game.gameState = STATE.Game;
			//AudioPlayer.getMusic("music").stop();
			handler.clearEnemys();
			handler.addObject(new Player(jumpIt.Width / 2 - 32, jumpIt.Height / 2 - 32, 50, ID.Player, handler));
			int add = 580;
			int sub = 1060;
			for (int i = 0; i < 20; i++) {
				int randomN = r.nextInt(246) + 100;
				handler.addObject(new Enemy(add, jumpIt.Height - randomN, randomN, ID.Enemy));
				handler.addObject(new Enemy(sub, 1, randomN, ID.Enemy)); //x, y, height, ID
				add = add + 960;
				sub = sub + 960;
				System.out.println("Enemy Number: " + i + " Distance Apart: " + add + " Height: " + randomN);
			} 
			
		}
		// go to help
		if (mouseOver(mx, my, 210, 210, 200, 64)) {
			game.gameState = STATE.Help;
		}

		// return to menu in help
		if (game.gameState == STATE.Help) {
			if (mouseOver(mx, my, 210, 310, 200, 64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		//retry
		if(game.gameState == STATE.End){
			if(mouseOver(mx, my, 205, 310, 200, 64)){
				game.gameState = STATE.Game;
				handler.clearEnemys();
				hud.setScore(0);
				hud.setLevel(0);
				handler.addObject(new Player(jumpIt.Width / 2 - 32, jumpIt.Height / 2 - 32, 50, ID.Player, handler));
				int add = 580;
				int sub = 1060;
				for (int i = 0; i < 20; i++) {
					int randomN = r.nextInt(246) + 100;
					handler.addObject(new Enemy(add, jumpIt.Height - randomN, randomN, ID.Enemy));
					handler.addObject(new Enemy(sub, 1, randomN, ID.Enemy)); //x, y, height, ID
					add = add + 960;
					sub = sub + 960;
					System.out.println("Enemy Number: " + i + " Distance Apart: " + add + " Height: " + randomN);
				} 
			}
		}

		// quit
		if (game.gameState == STATE.Menu) {
			if (mouseOver(mx, my, 210, 310, 200, 64)) {
				System.exit(0);
			}
		}
	}

	public void MouseReleased(MouseEvent e) {

	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 50);

			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Menu", 246, 60);

			g.setFont(fnt2);
			g.drawRect(210, 110, 200, 64);
			g.drawString("Play", 262, 158);

			g.drawRect(210, 210, 200, 64);
			g.drawString("Help", 262, 258);

			g.drawRect(210, 310, 200, 64);
			g.drawString("Quit", 262, 358);
		} else if (game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 246, 60);

			g.setFont(fnt2);
			g.drawString("Press space to jump", 100, 150);
			g.drawString("If you hit the top or bottom you die", 100, 200);
			g.drawString("If you hit the blocks you die", 100, 250);

			g.setFont(fnt);
			g.drawRect(205, 310, 200, 64);
			g.drawString("Back", 246, 360);
		} else if (game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 165, 60);

			g.setFont(fnt2);
			g.drawString("You lost with a score of: "+hud.getScore(), 120, 195);

			g.drawRect(205, 310, 200, 64);
			g.drawString("Try Again", 235, 355);
		}

	}
}

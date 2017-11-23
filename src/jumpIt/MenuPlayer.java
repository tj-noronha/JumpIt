package jumpIt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.Timer;

public class MenuPlayer extends GameObject {

	Handler handler;
	private GameObject Enemy;

	public MenuPlayer(int x, int y, int height, ID id, Handler handler) {
		super(x, y, height, id);
		this.handler = handler;

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				handler.object.get(i);
			}
		}
		velY = 5;
	}

	public Rectangle getBounds() {
		return new Rectangle(x - 200, y, 32, 32);
	}

	public void tick() {
		x += velX;
		y += velY;

		y = jumpIt.clamp(y, 0, jumpIt.Height - 55);
		collision();
	}

	private void collision() {
		HUD scoreBoard = new HUD();
		if (y == jumpIt.Height - 55) {
			velY = -5;
		}
		if (y == 0) {
			velY = 5;
		}
	}


	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x - 200, y, 32, 32);

	}

	Rectangle playerRect = new Rectangle(this.x, this.y);

	public Rectangle getPlayerBounds() {
		return playerRect.getBounds();
	}

}

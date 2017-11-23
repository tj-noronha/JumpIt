package jumpIt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;

public class Player extends GameObject {
    Handler handler;
    private GameObject Enemy;
    private jumpIt game;

    public Player(int x, int y, int height, ID id, Handler handler) {
        super(x, y, height, id);
        this.handler = handler;

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Player) {
                handler.object.get(i);
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x - 60, y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;

        //float diffX = x - Enemy.getX();
        //float distance = (float) Math.sqrt((x-Enemy.getX()*(x-Enemy.getX() )));
        y = jumpIt.clamp(y, 0, jumpIt.Height - 55);
        collision();
    }

    private void collision() {
        HUD scoreBoard = new HUD();
/*		for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Enemy) {
				if(playerRect.getX() > tempObject.getX()) {
					scoreBoard.addScore();
				}
				if(getBounds().intersects(tempObject.getBounds())){
					velY = 0;
					if(tempObject.getId() == ID.Enemy){
						tempObject.velX = 0;
						handler.clearEnemys();
						jumpIt.gameState = jumpIt.STATE.End;
					}
				}
				if (y == jumpIt.Height - 55 || y == 0){
					handler.clearEnemys();
					jumpIt.gameState = jumpIt.STATE.End;
				}
			}
		}*/

        for (Iterator<GameObject> iterator = handler.object.iterator(); iterator.hasNext(); ) {
            //GameObject tempObject = handler.object.get(i);
            GameObject obj = iterator.next();
            if (obj.getId() == ID.Enemy) {
                if (playerRect.getX() > obj.getX()) {
                    scoreBoard.addScore();
                    iterator.remove();
                }
                if (getBounds().intersects(obj.getBounds())) {
                    velY = 0;
                    if (obj.getId() == ID.Enemy) {
                        obj.velX = 0;
                        handler.clearEnemys();
                        jumpIt.gameState = jumpIt.STATE.End;
                    }
                }
                if (y == jumpIt.Height - 55 || y == 0) {
                    handler.clearEnemys();
                    jumpIt.gameState = jumpIt.STATE.End;
                }
            }
        }
    }


    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x - 60, y, 32, 32);

    }

    Rectangle playerRect = new Rectangle(this.x, this.y);

    public Rectangle getPlayerBounds() {
        return playerRect.getBounds();
    }

}

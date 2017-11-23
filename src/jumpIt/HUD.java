package jumpIt;

import java.awt.Graphics;

public class HUD {
	
	public static int score = 0;
	private int level = 1;
	
	public void tick(){
		//score++;
	}
	
	public void addScore() {
		score++;
	}
	
	public void render(Graphics g){
		g.drawString("Score: = " + score, 15, 14);
		g.drawString("Level: = " + level, 15,34);
	}
	
	public void score(int score){
		this.score = score;
	}
	
	public int getScore(){
		return score;
	}
	
	public int setScore(int score){
		return this.score = score;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
}


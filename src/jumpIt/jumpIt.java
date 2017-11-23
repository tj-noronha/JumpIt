package jumpIt;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class jumpIt extends Canvas implements Runnable {
	public static final int Width = 600, Height = Width / 12 * 9;
	private Thread thread;
	private boolean running = false;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Random r = new Random();

	public enum STATE {
		Menu, Game, Help, End
	};

	public static STATE gameState = STATE.Menu;

	private Handler handler;

	public jumpIt() {
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		AudioPlayer test = new AudioPlayer();
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
//		if(gameState == STATE.Menu || gameState == STATE.Help ) {
//		test.Background();}
//		else test.Background();
		
		new Window(Width, Height, "Lets build a game", this);
		
		spawner = new Spawn(handler, hud);
		
		if(gameState == STATE.Game){
			handler.addObject(new Player (jumpIt.Width / 2 - 32, jumpIt.Height / 2 - 32, 50, ID.Player, handler));
			handler.addObject(new Enemy(540, jumpIt.Height - 200, 450, ID.Enemy));
		} else {
			handler.addObject(new MenuPlayer(jumpIt.Width / 2 - 32, jumpIt.Height / 2 - 32, 50, ID.Player, handler));
			int add = 580;
			int sub = 1060;
			for (int i = 0; i < 20; i++) {
				int randomN = r.nextInt(246) + 100;
				handler.addObject(new MenuEnemy(add, jumpIt.Height - randomN, randomN, ID.MenuParticle));
				handler.addObject(new MenuEnemy(sub, 1, randomN, ID.MenuParticle)); //x, y, height, ID
				add = add + 920;
				sub = sub + 920;
			} 
		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join(); // stops thread
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		new jumpIt();
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				// System.out.println("PLAYER X: " + player1.x + " PLAYER Y: " +
				// player1.y);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		if (gameState == STATE.Game) {
			hud.tick();
			spawner.tick();
		} else if (gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Width, Height);
		handler.render(g);

		if (gameState == STATE.Game) {
			hud.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);;
		}
		g.dispose();
		bs.show();
	}

	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}

	
}

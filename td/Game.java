/*
 * File Name: Game.java
 * Contributors:	Jonathan Bradley - 7/17/2013
 * 					Ryan Meier
 * 					Ben Emrick
 * 
 * Purpose: This file handles all of the main parts of the game
 */
package td;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import td.map.Map;
import td.graphics.Screen;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String NAME = "TD - Thursday Build";
	public static final int HEIGHT = 720;
	public static final int WIDTH = 1280;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	private Map testMap;
	private Screen screen;
	
	private void init() {
		
		Map testMap = new Map(10, 10);
	}
		
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int ticks = 0;
		int frames = 0;
		init();
		long lastTimer1 = System.currentTimeMillis();
		
		while(true) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	private void tick() {
		
	}
	
	private void render() {
//		BufferStrategy bs = getBufferStrategy();
//		if (bs == null) {
//			createBufferStrategy(3)	;
//			return;
//		}
//		
//		testMap.render(screen);
//		
//		for (int y = 0; y < screen.getHeight(); y++) {
//			for (int x = 0; x < screen.getWidth(); x++) {
//				pixels[x + y * WIDTH] = screen.getPixels(x + y * screen.getWidth());
//			}
//		}
//		
//		Graphics g = bs.getDrawGraphics();
//		g.fillRect(0, 0, getWidth(), getHeight());
//		
//		g.drawImage(image, (getWidth() * -1), (getHeight() * -1), WIDTH, HEIGHT, null);
//		g.dispose();
//		bs.show();
		
		
	}
	

	public static void main(String[] args) {
		Game game = new Game();
		
		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		//frame.setSize(WIDTH, HEIGHT);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.run();
	}

}

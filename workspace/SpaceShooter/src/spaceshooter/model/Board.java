/**
 * 
 */
package spaceshooter.model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import spaceshooter.model.Alien.ALIEN_DIR;
import spaceshooter.model.Missile.MISSILE_DIR;

/**
 * @author dey
 *
 */
public class Board extends JPanel implements ActionListener {
	private final static int DELAY_MS = 5;
	private static final int SPACER = 15; //in px;
	private final  int DEFAULT_MAX_X;
	private final  int DEFAULT_MAX_Y;
	
	/**
	 * @author dey
	 *
	 */
	public class CraftKeyAdapter implements KeyListener {

		/* (non-Javadoc)
		 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyPressed(KeyEvent e) {
            craft.keyPress(e);
		}

		/* (non-Javadoc)
		 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			craft.keyRelease(e);
		}

		/* (non-Javadoc)
		 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyTyped(KeyEvent e) {

		}

	}
	
	
	private Timer timer;
	private Craft craft;
	private AlienArmy alienArmy;
	private boolean isRunning;
	private int aliensKilled;
	
	public Board(int maxX, int maxY) {
		addKeyListener(new CraftKeyAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		DEFAULT_MAX_X = maxX;
		DEFAULT_MAX_Y = maxY;
		
		craft = new Craft(DEFAULT_MAX_X, DEFAULT_MAX_Y);
		alienArmy = new AlienArmy(DEFAULT_MAX_X, DEFAULT_MAX_Y);
		
		isRunning = true;
		aliensKilled = 0;
		timer = new javax.swing.Timer(DELAY_MS, this);
		timer.start();
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if (isRunning) {
			Graphics2D g2D = (Graphics2D)g;
			g2D.drawImage(craft.getImage(), craft.getX(), craft.getY(),this);
			
			for (Missile missile : craft.getMissiles()) {
				g2D.drawImage(missile.getMissileImage(), missile.getX(), missile.getY(), this);
			}
			
			for (Alien alien : alienArmy.getAlienArmy()) {
				g2D.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
			}
			showKills(g2D);
		} else {
			String gameOver = String.format("Game Over");
			Font font = new Font("Helvetica", Font.BOLD, 15);
			FontMetrics metrics =  this.getFontMetrics(font); 
			
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString(gameOver, DEFAULT_MAX_X / 2 , DEFAULT_MAX_Y / 2);
			g.drawString("Aliens Killed: " + aliensKilled, DEFAULT_MAX_X / 2, DEFAULT_MAX_Y / 2 - SPACER);
		}
		alienArmy.addAlien();
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	public void showKills(Graphics2D g2D) {
		g2D.setColor(Color.WHITE);
		String toDisplay = String.format("Kills: %d", aliensKilled);
		//top right corner
		g2D.drawString(toDisplay, DEFAULT_MAX_X - toDisplay.length() - 5 * SPACER, SPACER);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (isRunning) {
			for (Iterator<Missile> it = craft.getMissiles().iterator(); it.hasNext(); ) {
				Missile missile = it.next();
				if (!missile.isVisible()) {
					it.remove();
				} else {
					missile.move(MISSILE_DIR.UP);
				}
			}

			for (Iterator<Alien> it = alienArmy.getAlienArmy().iterator(); it.hasNext(); ) {
				Alien alien = it.next();
				if (!alien.isVisible()) {
					it.remove();
				} else {
					alien.move(ALIEN_DIR.DOWN);
				}
			}
			craft.move();
			alienArmy.move();
			checkCollsions();

			repaint();
		}
	}
	
	
	public void checkCollsions() {
		//Craft collision
		Rectangle craftRectangle = craft.getBounds();
		outer:
			for (Alien alien : alienArmy.getAlienArmy()) {
				Rectangle alienRectangle = alien.getBounds();

				if (alienRectangle.intersects(craftRectangle)) {
					alien.setVisible(false);
					craft.setVisible(false);
					isRunning = false;
					break outer;
				}

				for (Missile missile : craft.getMissiles()) {
					if (missile.isVisible()) {
						Rectangle missileRectangle = missile.getBounds();
						if (alien.isVisible()) {
							if (missileRectangle.intersects(alienRectangle)) {
								alien.setVisible(false);
								missile.setVisible(false);
								++aliensKilled;
							}
						}
					}
				}
			}
	}

}

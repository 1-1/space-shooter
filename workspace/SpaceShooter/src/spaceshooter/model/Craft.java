/**
 * 
 */
package spaceshooter.model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * @author dey
 *
 */
public class Craft {
	private final static String craftLocation = "spaceship.png";
	
	private final int IMAGE_WIDTH;
	private final int IMAGE_HEIGHT;
	private final  int DEFAULT_MAX_X;
	private final  int DEFAULT_MAX_Y;
	
	private int x, y, dx, dy;
	private Image image;
	private List<Missile> missiles;
	private boolean visible;
	
	
	public Craft(int maxX, int maxY) {
		DEFAULT_MAX_X = maxX;
		DEFAULT_MAX_Y = maxY;
		
		x = maxX / 2;
		y = maxY;
		image = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(craftLocation)).getImage();
		dx = 0;
		dy = 0;
		missiles = new LinkedList<Missile>();
		IMAGE_WIDTH = image.getWidth(null);
		IMAGE_HEIGHT = image.getHeight(null);
		visible = true;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public List<Missile> getMissiles() {
		return missiles;
	}

	public void move() {
		if (visible) {
			x += dx;
			y += dy;
			if (x < 1) {
				x = 1;
			}
			if (y < 1) {
				y = 1;
			}

			if (x + IMAGE_WIDTH >= DEFAULT_MAX_X) {
				x = DEFAULT_MAX_X - IMAGE_WIDTH;
			}

			if (y + IMAGE_HEIGHT >= DEFAULT_MAX_Y) {
				y = DEFAULT_MAX_Y - IMAGE_HEIGHT;
			}
		}
	}
	
	public void keyPress(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_UP :
			dy -= 1;
			break;
		case KeyEvent.VK_DOWN :
			dy += 1;
			break;
		case KeyEvent.VK_LEFT :
			dx -= 1;
			break;
		case KeyEvent.VK_RIGHT :
			dx += 1;
			break;
		case KeyEvent.VK_SPACE :
			fire();
			break;
		}
	}
	
	
	public void fire() {
		missiles.add(new Missile(x + IMAGE_WIDTH / 4, y - IMAGE_HEIGHT / 4, DEFAULT_MAX_X, DEFAULT_MAX_Y));
		
	}

	public void keyRelease(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key) {
		case KeyEvent.VK_UP :
		case KeyEvent.VK_DOWN :
			dy = 0;
			break;
		case KeyEvent.VK_LEFT :
		case KeyEvent.VK_RIGHT :
			dx = 0;
			break;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
	}
	
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

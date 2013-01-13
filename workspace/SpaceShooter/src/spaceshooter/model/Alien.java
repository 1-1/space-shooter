/**
 * 
 */
package spaceshooter.model;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * @author dey
 *
 */
public class Alien {
	private final static String ALIEN_IMAGE_LOCATION[] = { "alien6.png", "aliens.png", "alien4.png", "alien2.png", "alien3.png" };
	private final static int BUFFER = 5; //5px;
	private final static Random random = new Random();
	
	private int x, y;
	private Image image;

	private final int MAX_X, MAX_Y;
	private final int IMAGE_WIDTH, IMAGE_HEIGHT;
	
	private boolean visible;
	private int speed;
	
	public static enum ALIEN_DIR {UP, DOWN, LEFT, RIGHT}
	enum ALIEN_SPEED {
		SLOW(1),
		MEDIUM(2),
		FAST(2);
		
		private final int speed;
		
		public int getSpeed() {
			return speed;
		}

		ALIEN_SPEED(int val) {
			this.speed = val;
		}
		
		
	};
	
	public Alien(int x, int y, int MAX_X, int MAX_Y, ALIEN_SPEED speed) {
		this.x = x;
		this.y = y;
		
		this.MAX_X = MAX_X;
		this.MAX_Y = MAX_Y;
		
		this.image = new ImageIcon(Thread.currentThread().getContextClassLoader()
				.getResource(ALIEN_IMAGE_LOCATION[random.nextInt(ALIEN_IMAGE_LOCATION.length)])
				).getImage();
		this.speed = speed.getSpeed();
		this.visible = true;
		
		IMAGE_WIDTH = image.getWidth(null);
		IMAGE_HEIGHT = image.getHeight(null);
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
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void move(ALIEN_DIR dir) {
		if (visible) {
			switch(dir) {
			case LEFT : x -= speed;
			            break;
			case RIGHT : x += speed;
			            break;
			case UP : y -= speed;
                        break;
            case DOWN : y += speed;
                        break;
			}
			
			if (x < 1 || y < 1 || x >= MAX_X || y >= MAX_Y) {
				visible = false;
			}
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, IMAGE_WIDTH - BUFFER, IMAGE_HEIGHT - BUFFER);
	}
	
	public static void main(String[] args) {
		Alien alien = new Alien(0, 0, 0, 0, ALIEN_SPEED.SLOW);
	}
}

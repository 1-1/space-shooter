/**
 * 
 */
package spaceshooter.model;


import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * @author dey
 *
 */
public class Missile {
	private final static String MISSILE_IMAGE_LOCATION = "missile2.png";
	private final static String EXPLOSION_IMAGE_LOCATION = "explosion.png";
	
	private final static int MISSILE_SPEED = 2;
	private final int BOARD_MAX_X, BOARD_MAX_Y;
	private final int IMAGE_WIDTH, IMAGE_HEIGHT;
	
	public static enum MISSILE_DIR {UP, DOWN, LEFT, RIGHT};
	
	private int x, y;
	private Image missileImage;
	private Image explosionImage;
	private boolean visible;
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getMissileImage() {
		return missileImage;
	}

	public Image getExplosionImage() {
		return explosionImage;
	}

	public Missile(int x, int y, int width, int height) {
		missileImage = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(MISSILE_IMAGE_LOCATION)).getImage();
		this.x = x;
		this.y = y;
		visible = true;
		BOARD_MAX_X = width;
		BOARD_MAX_Y = height;
		
//		explosionImage = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(EXPLOSION_IMAGE_LOCATION)).getImage();
	
		IMAGE_WIDTH = missileImage.getWidth(null);
		IMAGE_HEIGHT = missileImage.getHeight(null);
	}
	
	public void move(MISSILE_DIR dir) {
		switch(dir) {
		case UP:
			y -= MISSILE_SPEED;
			break;
		case DOWN:
			y += MISSILE_SPEED;
	        break;
		case LEFT:
			x -= MISSILE_SPEED;
			break;
		case RIGHT:
			x += MISSILE_SPEED;
			break;
		}
		if (x > BOARD_MAX_X || y > BOARD_MAX_Y || x < 0 || y < 0) {
			visible = false;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, IMAGE_WIDTH, IMAGE_HEIGHT);
	}
}

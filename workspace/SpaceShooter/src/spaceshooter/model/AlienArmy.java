/**
 * 
 */
package spaceshooter.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import spaceshooter.model.Alien.ALIEN_DIR;
import spaceshooter.model.Alien.ALIEN_SPEED;

/**
 * @author dey
 *
 */
public class AlienArmy {
	private final static int MAX_ALIENS_PER_ITERATION = 4;
	private final static int RIGHT_BUFFER = 50; //in px
	private int it = 0;
	
	private List<Alien> alienArmy;
	private final int MAX_X, MAX_Y;
	private final Random random;
	
	public AlienArmy(int MAX_X, int MAX_Y) {
		this.MAX_X = MAX_X;
		this.MAX_Y = MAX_Y;
		this.random = new Random();
		alienArmy = new LinkedList<Alien>();
		addKAliens(random.nextInt(MAX_ALIENS_PER_ITERATION) + 1);
	}

	public List<Alien> getAlienArmy() {
		return alienArmy;
	}
	
	public void addAlien() {
		addKAliens(1);
	}
	
	private void addKAliens(int k) {
		for (int i = 0; i < k && alienArmy.size() < MAX_ALIENS_PER_ITERATION; ++i) {
//			ALIEN_SPEED speed = ALIEN_SPEED.values()[random.nextInt(ALIEN_SPEED.values().length)];
			ALIEN_SPEED speed = ALIEN_SPEED.SLOW;
			alienArmy.add(new Alien(random.nextInt(MAX_X - RIGHT_BUFFER), 1, MAX_X, MAX_Y, speed));
		}
	}
	
	public void addAliens() {
		if (alienArmy.size() < MAX_ALIENS_PER_ITERATION) {
			addKAliens(random.nextInt(MAX_ALIENS_PER_ITERATION));
		}
	}
	
	public void move() {
		++it;
		for (Alien alien : alienArmy) {
			if (it % 5 == 0) {
				alien.move(ALIEN_DIR.DOWN);
			}
		}
	}
}

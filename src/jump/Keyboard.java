package jump;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private static Keyboard instance;

	public boolean[] keys,keys2;
	
	private Keyboard() {
		keys = new boolean[256];
		keys2 = new boolean[256];
		
	}

	public static Keyboard getInstance() {

		if (instance == null) {
			instance = new Keyboard();
		}
		
		return instance;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length) {
			keys[e.getKeyCode()] = true;
			keys2[e.getKeyCode()] = false;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() >= 0 && e.getKeyCode() < keys.length) {
			keys[e.getKeyCode()] = false;
			keys2[e.getKeyCode()] = true;
		}
	}

	public void keyTyped(KeyEvent e) {}

	public boolean isDown(int key) {

		if (key >= 0 && key < keys.length) {
			return keys[key];
		}
		
		return false;
	}
	public boolean isReleased(int key2) {

		if (key2 >= 0 && key2 < keys2.length) {
			
			return keys2[key2];
		}
		
		return false;
	}
}

package jump;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

public class Frame extends JFrame{

	private static Dimension screen;
	private GamePanel gamePanel;
    public static int WIDTH = 500;
    public static int HEIGHT = 800;
    
    public static void main(String[] args) {
    	screen = Toolkit.getDefaultToolkit().getScreenSize();
        Frame frame = new Frame();
        frame.start();
        
    }
    public Frame() {
    	setVisible(true);
    	setTitle("No Jump No Gain!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation((screen.width-WIDTH)/2,0);
        Keyboard keyboard = Keyboard.getInstance();
        addKeyListener(keyboard);
        
        gamePanel = new GamePanel();
        add(gamePanel);
        setResizable(false);
        setSize(WIDTH,HEIGHT);
    }
    public void start() {
        JOptionPane.showOptionDialog(null, "Ready...?","Start", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"GO!"}, 0);
        gamePanel.start();
    }
    
}

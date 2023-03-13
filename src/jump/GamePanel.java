package jump;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
	
	private static final int STAGE_SCROLL_LIMIT = 300;

    private int pauseDelay;
    private int restartDelay;

    private Me me;
    private ArrayList<Platform> platforms;
    private Keyboard keyboard;
    private Sound sound;

    private int offset,score;
    private boolean gameover;
    private boolean firstJump;
    private boolean paused;


    public GamePanel() {
    	
        newGame();
        
    }
    
    public void start() {
    	new Thread(this).start();
    }
    
    public void newGame() {
    	
        keyboard = Keyboard.getInstance();
        restart();
    }

    
    public void restart() {
    	 paused = false;
         gameover = false;
         firstJump=true;

         score = 0;
         offset=0;
         pauseDelay = 0;
         restartDelay = 0;
         
         
        me = new Me();
        
        platforms = new ArrayList<Platform>();
        
        for (int i = 0; i < 10; i++) {
            Platform p = new Platform();
            p.x=(int)(Math.random() * (Frame.WIDTH - p.width));
            p.y=i*((int)(Math.random() * 50) + 50);
            
            this.platforms.add(p);
        }
        
        sound = new Sound();
       
    }

    public void update() {

        watchForPause();
        watchForReset();

        if (paused)
            return;

        if(firstJump) {
    		me.yVelocity=-17.5;
    		sound.getSound("jump.wav");
    		firstJump=false;
    	}
        
        me.update();
        

        if (gameover)
            return;

        checkForGame();
        moveStageUp();
        
        repaint();
    }
    
    public ArrayList<Render> getRenders() {
    	
        ArrayList<Render> renders = new ArrayList<Render>();
        
        renders.add(new Render(0, 0, "images/bg.png"));
        
        for (Platform platform : platforms)
            renders.add(platform.getRender());
        
        renders.add(me.getRender());
        
        return renders;
    }
    

    private void watchForPause() {
    	
        if (pauseDelay > 0)
            pauseDelay--;

        if (keyboard.isDown(KeyEvent.VK_P) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = 10;
        }
    }

    private void watchForReset() {
    	
        if (restartDelay > 0)
            restartDelay--;

        if (keyboard.isDown(KeyEvent.VK_R) && restartDelay <= 0) {
            restart();
            restartDelay = 10;
            return;
        }
    }
    
    
    
    private void moveStageUp() {
    	
        if (this.me.y < STAGE_SCROLL_LIMIT) {
        	
            offset = STAGE_SCROLL_LIMIT - this.me.y;
            this.me.y = STAGE_SCROLL_LIMIT;
            
            for (Platform p : platforms) {
                p.y = p.y + offset;
                if (p.y > Frame.HEIGHT) {
                    p.x=(int) (Math.random() * (Frame.WIDTH - p.width));
                    p.y=((int)(Math.random() * 50) - 50);
                }
            }
            
            this.score += offset / 2;
            
        }
        
        
    }
    

    

    private void checkForGame() {
    	if (me.y > Frame.HEIGHT) {
            gameover=true;
            sound.getSound("fail.wav");
        }
        else {
            int halfMe = me.width / 2;
            if (me.x < -halfMe) {
                me.x = Frame.WIDTH - halfMe;
            }
            else if (me.x > Frame.WIDTH - halfMe) {
                me.x = -halfMe;
            }
            for (Platform p : platforms) {
                if (me.yVelocity > 0 && me.getFeet().intersects(p.getBase())) {
                    me.yVelocity = -17.5;
                    sound.getSound("jump.wav");
                }
            }
        }
    	

    }

    protected void paintComponent(Graphics g) {
    	
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        
        for (Render r : this.getRenders()) {
        	if (r.transform != null)
                g2D.drawImage(r.image, r.transform, null);
            else
                g.drawImage(r.image, r.x, r.y, null);
        }
        
        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font("Courier", Font.PLAIN, 24));
        g2D.drawString(Integer.toString(score), 10, 30);
        
        if (gameover) {
        	JOptionPane.showMessageDialog(null, "Your score : " + score ,"Game Over", JOptionPane.WARNING_MESSAGE);
        	sound.stopSound();
        	removeAll();
        	newGame();
        }
    }

    public void run() {
        try {
            while (true) {
                update();
                Thread.sleep(25);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

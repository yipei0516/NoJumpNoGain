package jump;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


public class Me {
    public int x;
    public int y;
    public int width;
    public int height;

    public boolean dead;
    private boolean right;

    public double gravity;
    public double yVelocity;

    private Image image;
    private Keyboard keyboard;

    public Me() {
    	width = 120;
        height = 170;
    	x = (Frame.WIDTH - width) / 2;
    	y = Frame.HEIGHT - height;
        gravity=0.5;
        yVelocity=0;
        
        dead = false;
        
        keyboard = Keyboard.getInstance();
    }
    
    public void update() {
    	
    	yVelocity += gravity;
    	y += (int)yVelocity;
    	
    	if (!dead && keyboard.isDown(KeyEvent.VK_RIGHT) ) {
    		this.x+=10;
		}
		else if(!dead && keyboard.isDown(KeyEvent.VK_LEFT) ) {
			this.x-=10;
			
		}
    	if (!dead && keyboard.isReleased(KeyEvent.VK_RIGHT) ) {
    		this.x+=15;
    		keyboard.keys2[KeyEvent.VK_RIGHT]=false;
		}
		else if(!dead && keyboard.isReleased(KeyEvent.VK_LEFT) ) {
			this.x-=15;
			keyboard.keys2[KeyEvent.VK_LEFT]=false;
		}
    	
    }
    
    public Rectangle getFeet() {
    	if(right==true) {
    		return new Rectangle(x+60, y+150, 15, 20);
    	}
    	else {
    		return new Rectangle(x+45, y+150, 15, 20);
    	}
    	
    }
    

    public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Resource.loadImage("images/meL.png");     
        }
		else {
			if (keyboard.isDown(KeyEvent.VK_RIGHT) ) {
				image = Resource.loadImage("images/meR.png");
				right=true;
			}
				
			else if (keyboard.isDown(KeyEvent.VK_LEFT) ) {
				image = Resource.loadImage("images/meL.png");
				right=false;
			}
				
		}
        r.image = image;

        return r;
    }
    
    
}
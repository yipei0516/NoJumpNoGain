package jump;

import java.awt.Image;
import java.awt.Rectangle;


public class Platform {

    public int x;
    public int y;
    public int width;
    public int height;

    private Image image;

    public Platform() {
    	width = 35;
        height = 23;
    	
    }
    

    public Rectangle getBase(){
        return new Rectangle(x, y, width, 1);
    }

    public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;

        if (image == null) {
            image = Resource.loadImage("images/platform.jpg");
        }
        r.image = image;

        return r;
    }
}

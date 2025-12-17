import java.awt.Color;
import java.awt.Graphics;

public class Obstacle extends GameObject {
    
    
    public Obstacle(int x, int y, int width, int height, int speed) {
        super(x, y, width, height);
        this.speed = speed;
    }
    
    @Override
    public void update() {
        // Move downwards
        this.y += speed;
        // Update the hitbox position
        this.hitbox.setLocation(this.x, this.y); 
        
    }
    
    @Override
    public void draw(Graphics g) {
        // Simple red box for an obstacle
       
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, width, height);
        
    }
}

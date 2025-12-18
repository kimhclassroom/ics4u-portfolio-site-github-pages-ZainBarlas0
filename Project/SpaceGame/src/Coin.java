import java.awt.Color;
import java.awt.Graphics;

public class Coin extends GameObject {
    
    public Coin(int x, int y, int size, int speed) {
        super(x, y, size, size);
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
        // Simple yellow circle for a coin
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, width, height);
    }
}

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
    
    // Protected fields for access by subclasses
    protected int x, y;
    protected int width, height;
    protected int speed;
    protected Rectangle hitbox;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hitbox = new Rectangle(x, y, width, height);
        this.speed = 0; // Default speed
    }
    
    // Abstract methods must be implemented by subclasses
    public abstract void update();
    public abstract void draw(Graphics g);
    
    // Utility getter for collision checks
    public Rectangle getHitbox() {
        return hitbox;
    }
    
    // Getters for position (useful for drawing logic)
    public int getX() { return x; }
    public int getY() { return y; }
}

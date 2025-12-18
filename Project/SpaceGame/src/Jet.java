import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;


public class Jet extends GameObject {
    
    // Movement state flags
    private boolean movingUp, movingDown, movingLeft, movingRight;
    private int score;
    private int health;
    private BufferedImage jetImage;

    public Jet(int x, int y, int size, int speed) {
        super(x, y, size, size);
        this.speed = speed; // Typically 5-8 pixels per update    
        this.score = 0;
        this.health = 3;

        // Load the image from a file
       try {
            // 1. Get the URL for the resource using the correct filename.
            //    If your file is in a subfolder like 'assets', change the path to "/assets/6657598.png"
            URL imageUrl = getClass().getResource("6657598.png");

            

            // 2. Read the image from the found URL stream.
            jetImage = ImageIO.read(imageUrl);
            
        } catch (IOException e) {
            System.err.println("Failed to load jet image. Using fallback graphics.");
            e.printStackTrace();
            jetImage = null; // Ensure jetImage is null so the fallback is drawn
        }
 
        
    }

    @Override
    public void update() {
        // Adjust position based on movement flags
        if (movingUp) y -= speed;
        if (movingDown) y += speed;
        if (movingLeft) x -= speed;
        if (movingRight) x += speed;
        
        // Keep the jet within the screen boundaries
        if (x < 0) x = 0;
        if (x > Main.WIDTH - width) x = Main.WIDTH - width;
        if (y < 0) y = 0;
        if (y > Main.HEIGHT - height) y = Main.HEIGHT - height;

        // Update the hitbox position
        this.hitbox.setLocation(x, y);
    }

    @Override
    public void draw(Graphics g) {
       if (jetImage != null) {
            // Draw the loaded image, scaling it to the size defined by the GameObject
            g.drawImage(jetImage, x, y, width, height, null);
        } else {
            // Fallback drawing if the image failed to load
            g.setColor(Color.BLUE);
            g.fillRect(x, y, width, height);
            g.setColor(Color.CYAN);
            g.fillRect(x - 5, y + 15, width + 10, 5);
        }
    }

    // --- State Management Methods ---

    public void takeDamage() {
        if (health > 0) {
            health--;
        }
    }
    
    public void incrementScore(int points) {
        score += points;
    }
    
    // --- Getters ---
    public int getScore() { return score; }
    public int getHealth() { return health; }

    // --- Setters (Used by GamePanel KeyListener) ---
    public void setMovingUp(boolean movingUp) { this.movingUp = movingUp; }
    public void setMovingDown(boolean movingDown) { this.movingDown = movingDown; }
    public void setMovingLeft(boolean movingLeft) { this.movingLeft = movingLeft; }
    public void setMovingRight(boolean movingRight) { this.movingRight = movingRight; }
}
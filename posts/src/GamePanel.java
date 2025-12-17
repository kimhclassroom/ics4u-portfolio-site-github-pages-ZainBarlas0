import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {


    // Game loop control
    private Thread gameThread;
    private boolean running = true;
    private final int FPS = 60;
    private final long TARGET_TIME = 1000 / FPS;
    private  int speed = 7;
    private int score =0;

    // Game Objects
    private Jet playerJet;
    private List<GameObject> obstacles = new CopyOnWriteArrayList<>();
    private List<GameObject> coins = new CopyOnWriteArrayList<>();
    private final Random random = new Random();
    private OverPanel gameOverPanel;
    
    // Game State
    private int screenWidth;
    private int screenHeight;
    private boolean gameOver = false;

    //restart and menu button
    private BufferedImage spaceImage;



    public void stopGame() {
    running = false;
    }

    public GamePanel(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
        this.setLayout(new BorderLayout());
        
        this.gameOverPanel = new OverPanel();
        
        gameOverPanel.setVisible(false);
        this.add(gameOverPanel,BorderLayout.CENTER);
        
        
        setPreferredSize(new Dimension(width, height));
    // setBackground(Color.BLACK);
        
        // Initialize game objects and lists
        initializeGame();
        
        // Setup input listener
        addKeyListener(this);
        setFocusable(true);
        

        try {
            // 1. Get the URL for the resource using the correct filename.
            //    If your file is in a subfolder like 'assets', change the path to "/assets/6657598.png"
            URL imageUrl = getClass().getResource("Space.jpg");

            

            // 2. Read the image from the found URL stream.
            spaceImage = ImageIO.read(imageUrl);
            
        } catch (IOException e) {
            System.err.println("Failed to load jet image. Using fallback graphics.");
            e.printStackTrace();
            spaceImage = null; // Ensure jetImage is null so the fallback is drawn
        }

    

        
        // Start the game loop thread
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void startGameLoop() {
    // 1. Reset state variables
    running = true;
    gameOver = false;
    
    // 2. Create a NEW thread instance
    gameThread = new Thread(this);
    
    // 3. Start the loop
    gameThread.start();
}

    public Thread getThread(){
        return gameThread;
    }

    public void setGameOver(boolean b){
        gameOver = b;
    }
    
    public  void initializeGame() {
        // Create the player jet near the bottom center
        final int JET_SIZE =45;
        final int JET_SPEED = 4;
        int startX = screenWidth / 2 - JET_SIZE / 2;
        int startY = screenHeight - JET_SIZE - 50;
        playerJet = new Jet(startX, startY, JET_SIZE, JET_SPEED);
        
        obstacles = new CopyOnWriteArrayList<>();
        coins = new CopyOnWriteArrayList<>();
    }

    

    // --- Game Loop Implementation ---
public void setSpeed(int x){
    speed = x;
}
    @Override
    public void run() {
        double drawInterval = 1000000000.0 / FPS; // Time in nanoseconds per frame
    double delta = 0;
    long lastTime = System.nanoTime();
    long currentTime;

    while (running) {
        currentTime = System.nanoTime();
        delta += (currentTime - lastTime) / drawInterval;
        lastTime = currentTime;

        if (delta >= 1) {
            if (!gameOver) {
                update();
                repaint();
            }
            delta--;
        }

        // Optional: slight sleep to save CPU
        try {
            Thread.sleep(1); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }
    
    // --- Update Logic ---

    private void update() {
        playerJet.update();
        
        // Update and spawn game elements
        updateAndSpawnObjects(obstacles, 15); // Obstacles spawn roughly 1 in 15 frames
        updateAndSpawnObjects(coins, 40);    // Coins spawn roughly 1 in 40 frames
        
        checkCollisions();

        if (playerJet.getHealth() <= 0) {
            gameOver = true;
            addToProfile();
        }
    }
    
    // --- Object Management ---
    
    private void updateAndSpawnObjects(List<GameObject> objectList, int spawnChance) {
        // 1. Update all objects first
        for (GameObject obj : objectList) {
            obj.update();
        }

        // 2. Remove off-screen objects using removeIf
        objectList.removeIf(obj -> obj.getY() > screenHeight);

        // 3. Random Spawning
        if (random.nextInt(spawnChance) == 0) { 
            GameObject newObj = createNewObject(objectList);
            if (newObj != null) {
                objectList.add(newObj);
            }
        }
    }
    
    private GameObject createNewObject(List<GameObject> objectList) {
        // Determines the type of object to spawn
        if (objectList == obstacles) {
            int size = 20 + random.nextInt(40); // Random size
            int x = random.nextInt(screenWidth - size);
            int speed = 3 ;// Speed between 3 a nd 5

            return new Obstacle(x, -size, size, size, speed); 
            
        } else if (objectList == coins) {
            final int COIN_SIZE = 20;
            final int COIN_SPEED = 4;
            int x = random.nextInt(screenWidth - COIN_SIZE);

            return new Coin(x, -COIN_SIZE, COIN_SIZE, COIN_SPEED);
        }
        return null;
    }

    // --- Collision Detection ---

    private void checkCollisions() {
    Rectangle jetHitbox = playerJet.getHitbox();
    
    // Remove obstacles that hit the player
    obstacles.removeIf(obstacle -> {
        if (jetHitbox.intersects(obstacle.getHitbox())) {
            playerJet.takeDamage();
            return true; // This tells removeIf to remove this item
        }
        return false;
    });

    // Remove coins that the player collects
    coins.removeIf(coin -> {
        if (jetHitbox.intersects(coin.getHitbox())) {
            playerJet.incrementScore(10);
            return true; // This tells removeIf to remove this item
        }
        return false;
    });
    }

    // --- Drawing / Rendering ---

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(spaceImage, 0,0, this.getWidth(), this.getHeight(), null);

        // Draw all objects
        playerJet.draw(g);
        for (GameObject obj : obstacles) obj.draw(g);
        for (GameObject obj : coins) obj.draw(g);
        
        // Draw UI (Score and Health)
        drawUI(g);

        if(gameOver == true){
            drawGameOver();
        }
        
    
        // Toolkit.getDefaultToolkit().sync(); // Ensure OS finishes drawing (Good practice for Swing games)
    }

    private void drawUI(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Draw Score
        g.drawString("Score: " + playerJet.getScore(), 10, 25);
        
        // Draw Health
        String heart = "❤️"; // Unicode heart symbol
        String healthString = "";
        for (int i = 0; i < playerJet.getHealth(); i++) {
            healthString += heart + " ";
        }
        g.drawString("Health: " + healthString, screenWidth - 200, 25);
    }
    
    public void drawGameOver(){
        gameOverPanel.setOpaque(true);
        gameOverPanel.setVisible(true);
        gameOverPanel.scoreText("Score: "+ playerJet.getScore());
        addToProfile();
        this.revalidate();
        this.repaint();
    }

    public void addToProfile() {
    String filePro = "Profile.txt";
    File profile = new File(filePro);
    String username = ""; 
    int highScore = 0;

    
    try(Scanner input = new Scanner(profile)){
        
        if (input.hasNextLine()) {
            String info = input.nextLine();
            String[] data = info.split(",");
            if (data.length >= 2) {
                username = data[0];
                highScore = Integer.parseInt(data[1].trim());
            }
        }
    } catch (FileNotFoundException e) {
    
        username = "Player"; 
        highScore = 0;
    }


    int currentScore = playerJet.getScore();
    if (currentScore > highScore) {
        try( PrintWriter writer = new PrintWriter(profile)){
            writer.println(username + "," + currentScore);
            System.out.println("New high score saved: " + currentScore);
        } catch (IOException e) {
            System.err.println("Error writing to profile: " + e.getMessage());
        }
    }
}

    public OverPanel getGameOverPanel(){
        return gameOverPanel;
    }

    public Jet getJet(){
        return playerJet;
    }


//--- KeyListener Implementation ---

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used, but required by interface
    }

    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) playerJet.setMovingUp(true);
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) playerJet.setMovingDown(true);
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) playerJet.setMovingLeft(true);
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) playerJet.setMovingRight(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) playerJet.setMovingUp(false);
        if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) playerJet.setMovingDown(false);
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) playerJet.setMovingLeft(false);
        if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) playerJet.setMovingRight(false);
    }

    


}
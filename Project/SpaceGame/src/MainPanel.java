
import java.awt.CardLayout;
import java.io.FileNotFoundException;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
    private  CardLayout card = new CardLayout();//using card layout to switch between panels
    private  HolderPanel holder = new HolderPanel();// holder panel class object stores sub panel which allows to swtich between login and signup
    private  MenuPanel menu = new MenuPanel();// menu panel class object stores the main menu panel
    private  GamePanel gamePanel; // stores the game screen where the game is played
    private GameOverPanel overPanel = new GameOverPanel();// stores the game over screen
    private ControllerClass controller; // controller class object where the buttons funcitonality occurs

    //constructor
     public MainPanel() throws FileNotFoundException{
        this.gamePanel =  new GamePanel(Main.WIDTH,Main.HEIGHT);
        this.controller = new ControllerClass(holder.getSubPanel(),holder.getSign(),holder.getLog(),holder.getSubPanel().getLogin(), this, menu,gamePanel);
        this.setLayout(card);// setting the layout of Main Panel to card layout in order to switch between screens
        //adding panels to card deck
        this.add(holder, "Holder");
        this.add(menu, "Menu");
        this.add(gamePanel,"GamePanel");
    }

    //funciton to shows the menu card
    public  void showMenu(){
        card.show(this, "Menu");
    }

  
    // funciton to show the holder panel card
    public  void showHolder(){
        card.show(this, "Holder");
    }
    //funciton to show the game panel card
    public void showGamePanel(){
    // checks if there is a gamePanel existing already and if so stops and removes the existing game panel
     if (gamePanel != null) {
         gamePanel.stopGame();
         this.remove(gamePanel); 
     }

    // creates a fresh game panel
     gamePanel = new GamePanel(Main.WIDTH, Main.HEIGHT);

     //updates the gamepanel
     controller.updateGamePanel(gamePanel);
     //adds the new Game Panel to the card deck 
     this.add(gamePanel, "GamePanel"); 
     //shows it on the screen
     card.show(this,"GamePanel");
     // starts the game loop
     gamePanel.startGameLoop();
     // requests focus in the window
     gamePanel.requestFocusInWindow();
         
    }


}

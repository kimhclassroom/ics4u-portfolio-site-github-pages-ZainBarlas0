
import java.awt.CardLayout;
import java.io.FileNotFoundException;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
    private  CardLayout card = new CardLayout();
    private  HolderPanel holder = new HolderPanel();
    private  MenuPanel menu = new MenuPanel();
    private  GamePanel gamePanel; 
    private OverPanel overPanel = new OverPanel();
    private ControllerClass controller; // Declare controller here
    
    

 
     public MainPanel() throws FileNotFoundException{
        
        this.gamePanel =  new GamePanel(Main.WIDTH,Main.HEIGHT);
        this.controller = new ControllerClass(holder.getSubPanel(),holder.getSign(),holder.getLog(),holder.getSubPanel().getLogin(), this, menu,gamePanel);
        this.setLayout(card);
        this.add(holder, "Holder");
        this.add(menu, "Menu");
        this.add(gamePanel,"GamePanel");
        
        
        
        
       

        
    }

    public  void showMenu(){
        card.show(this, "Menu");
    }

  

    public  void showHolder(){
        card.show(this, "Holder");
    }

    public void showGamePanel(){
         // We need to stop the thread of the currently running game *before* replacing it
     if (gamePanel != null) {
         gamePanel.stopGame();
         this.remove(gamePanel); // Remove the old instance from the CardLayout
     }

     // 2. Create a NEW GamePanel instance for a fresh start
     gamePanel = new GamePanel(Main.WIDTH, Main.HEIGHT);

     // 3. Update the controller with the new GamePanel's button
     // You MUST update the action listener for the menu button on the new panel
     controller.updateGamePanel(gamePanel);

     // 4. Add the NEW GamePanel to the CardLayout
     this.add(gamePanel, "GamePanel"); 

     // 5. Switch to the new panel view
     card.show(this,"GamePanel");

     // 6. Tell the GamePanel to create and START its new thread
     gamePanel.startGameLoop();
     
     // 7. Request focus for keyboard input
     gamePanel.requestFocusInWindow();
         
    }


}

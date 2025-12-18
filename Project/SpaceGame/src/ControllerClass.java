
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import javax.print.attribute.standard.Media;
import javax.swing.JButton;

public class ControllerClass {

    private  HashMap userID = new HashMap<>();
    private   HashMap emailaddress = new HashMap<>();

    private MainPanel mainPanel; 
    private GamePanel currentGamePanel;
    private  MenuPanel menu;
    

   public ControllerClass(SubPanel sub, JButton signup, JButton login, LoginPage lPage, MainPanel mainPanel, MenuPanel menu, GamePanel game){
    this.mainPanel = mainPanel;
    this.currentGamePanel = game;
    this.menu = menu;
    
    // checks if signup button pressed if so switches to signup page
    signup.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
            sub.signupshow();
        }
    });

    //checks if login button is pressed if so switches to login page
    login.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
            sub.loginshow();
        }
    });

    //checks if the submit button on the login page is pressed
    lPage.getSubmit().addActionListener(new ActionListener(){
         @Override
            public void actionPerformed(ActionEvent e){
            //reads text from content.txt
         try{
        //accessing file and creating scanner object
        String content = "Content.txt";
        File file = new File(content);
        Scanner input = new Scanner(file);
      

        while(input.hasNextLine()){
            //adds al the accounts to two different hashmaps
            String info = input.nextLine();
            //System.out.println(info);
            String[] list = info.split(",");
            userID.put(list[1], list[2]);// hashmap stores the user id and password
            emailaddress.put(list[0], list[2]);// hashmap stores the email address and password
            list = null;// setting the array to null
        }
        input.close();// closing input
        } catch(FileNotFoundException a){
            System.out.println("FILE NOT FOUND");
        }
                //obtiaining neccessary information to run checks
                String namefield = lPage.getName();
                String password = lPage.getpassword();

                //checks if user id  hashmap has the name field
                if(userID.containsKey(namefield)){
                    System.out.println("USING USER ID");
                    String p = (String) userID.get(namefield);// gets the password key
                    p = p.trim();
                    
                    //cjecks if passwrods equal
                    if(password.equals(p)){
                        System.out.println("Logged in successfully");
                        mainPanel.showMenu();// showing the menu screen after successfully logging in
                        try{
                        //creates a profile to output the highscre
                        String filePro = "Profile.txt";
                        File profile = new File(filePro);
                        PrintWriter writer = new PrintWriter(profile);
                        writer.println(lPage.getName()+",0");
                        writer.close();
                        System.out.println("added to profile");
                        } catch(IOException c){
                            System.out.println("FILE NOT MADE");
                        }
                      
                    }else{
                        lPage.setMessage("Wrong Password");
                        System.out.println("Password does not match");
                    }

                    //checks if emailaddresses hashmap has the specified namefield
                }else if(emailaddress.containsKey(namefield)){
                    System.out.println("USING USER email");
                    String p = (String) emailaddress.get(namefield);//gets the password keu
                        p = p.trim();
                    //checks if hte entered password matches wiht teh one found with the key 
                    if(password.equals(p)){
                        System.out.println("Logged in successfully");
                        mainPanel.showMenu();// shows menu once logged in
                        try{
                        //creates profile to store highscore
                        String filePro = "Profile.txt";
                        File profile = new File(filePro);
                        PrintWriter writer = new PrintWriter(profile);
                        writer.println(lPage.getName()+",0");
                        writer.close();
                        System.out.println("added to profile");
                        } catch(IOException d){
                            System.out.println("FILE NOT MADE");
                        }
                       
                    }else{// in the case that the entered password does not match
                        lPage.setMessage("Wrong Password");
                        System.out.println("Password does not match");
                    }

                } else{//in the case that user id does not exist
                    lPage.setMessage("Email or user id does not exist");
                    System.out.println("email does not exist");
                }

    
            }

    });

    
    menu.getButton().addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mainPanel.showGamePanel(); // Calls the revised MainPanel method
                game.setGameOver(false);
                game.getGameOverPanel().setVisible(false);
            }
        });

        //creates a listner for the new game panel
        setupMenuButtonListener(game);
    }


    //attaching lsitner to the newley created game panel
    public void updateGamePanel(GamePanel newGamePanel) {
        this.currentGamePanel = newGamePanel;
        setupMenuButtonListener(newGamePanel);
    }



    private void setupMenuButtonListener(GamePanel game) {
        // Remove old listeners to prevent multiple actions on a single press
        for (ActionListener listener :game.getGameOverPanel().getMenuButton().getActionListeners()) {
            game.getGameOverPanel().getMenuButton().removeActionListener(listener);
        }
        
     
            game.getGameOverPanel().getMenuButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                
                mainPanel.showMenu();
                menu.updateScreen();
                
            }
        });

    } 


}

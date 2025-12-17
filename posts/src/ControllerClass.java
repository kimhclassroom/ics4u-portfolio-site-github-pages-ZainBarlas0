
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
    

    signup.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
            sub.signupshow();
        }
    });

    login.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
            sub.loginshow();
        }
    });

    lPage.getSubmit().addActionListener(new ActionListener(){
         @Override
            public void actionPerformed(ActionEvent e){
         try{
        String content = "Content.txt";
        File file = new File(content);
        Scanner input = new Scanner(file);
      

        while(input.hasNextLine()){
            String info = input.nextLine();
            //System.out.println(info);
            String[] list = info.split(",");
            userID.put(list[1], list[2]);
            emailaddress.put(list[0], list[2]);
            list = null;
        }
        input.close();
        } catch(FileNotFoundException a){
            System.out.println("FILE NOT FOUND");
        }

                String namefield = lPage.getName();
                String password = lPage.getpassword();

                if(userID.containsKey(namefield)){
                    System.out.println("USING USER ID");
                    String p = (String) userID.get(namefield);
                    p = p.trim();
                    
                    if(password.equals(p)){
                        System.out.println("Logged in successfully");
                        mainPanel.showMenu();
                        try{
                        String filePro = "Profile.txt";
                        File profile = new File(filePro);
                        PrintWriter writer = new PrintWriter(profile);
                        writer.println(namefield+",0");
                        writer.close();
                        System.out.println("added to profile");
                        } catch(IOException c){
                            System.out.println("FILE NOT MADE");
                        }
                      
                    }else{
                        System.out.println("Password does not match");
                    }


                }else if(emailaddress.containsKey(namefield)){
                    System.out.println("USING USER email");
                    String p = (String) emailaddress.get(namefield);
                        p = p.trim();
                    if(password.equals(p)){
                        System.out.println("Logged in successfully");
                        mainPanel.showMenu();
                        try{
                        String filePro = "Profile.txt";
                        File profile = new File(filePro);
                        PrintWriter writer = new PrintWriter(profile);
                        writer.println(namefield+",0");
                        writer.close();
                        System.out.println("added to profile");
                        } catch(IOException d){
                            System.out.println("FILE NOT MADE");
                        }
                       
                    }else{
                        System.out.println("Password does not match");
                    }

                } else{
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

        // CRITICAL: Initialize the listener for the initial gamePanel
        setupMenuButtonListener(game);
    }


    // NEW METHOD: Used to attach the listener to the newly created GamePanel
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

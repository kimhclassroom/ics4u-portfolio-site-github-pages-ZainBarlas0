
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class MenuPanel extends JPanel{
    
    private SpringLayout layout = new SpringLayout();// menu panel has a spring layout
    private  JButton playButton = new JButton("PLAY");// creates a play button 
    private  JLabel title = new JLabel("Space Crusader");// title of the game 
 
    private JLabel playerscore = new JLabel("Score: ");// stores the players highscore
    private JLabel playerID= new JLabel("Username: ");// stores the players id
    
    

    public MenuPanel(){
        this.setLayout(layout);// setting the layout

        //formatting everry element using the spring layout contraints

        layout.putConstraint(SpringLayout.WEST, title,200 , SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, playerscore,200 , SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, playerscore, 200, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, playerID,200 , SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, playerID, 175, SpringLayout.NORTH, this);


        layout.putConstraint(SpringLayout.WEST, playButton,300, SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, playButton, 250, SpringLayout.NORTH, this);

        //setting a custom font for the title
        Font customFont = new Font("Comic Sans", Font.BOLD, 50);
        title.setFont(customFont);
       
        //changing size of the play button
        playButton.setPreferredSize(new Dimension(200,75));

        //adding every element to the panel
        this.add(title);
        this.add(playButton);
        this.add(playerscore);
        this.add(playerID);
       

    }

    //retrieves play button
    public JButton getButton(){
        return playButton;
    }

    // sets the player score text lable
    public void setScore(String s){
        playerscore.setText(s);
    }
    //sets the plyer id text label
    public void setID(String s){
        playerID.setText(s);
    }

    // updates the screen if a new highscore is acheived
    public void updateScreen(){
         try{
            String filePro = "Profile.txt";
            File profile = new File(filePro);
            Scanner input = new Scanner(profile);
        if (input.hasNextLine()) {
            String info = input.nextLine();
            String[] data = info.split(",");
            
            this.setScore("Score: "+ data[1]);
            this.setID("Username: "+ data[0]);
                }
                input.close();
        }catch(IOException e){

        }
    }
}

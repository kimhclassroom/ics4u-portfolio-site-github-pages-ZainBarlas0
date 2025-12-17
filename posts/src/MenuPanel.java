
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
    
    private SpringLayout layout = new SpringLayout();
    private  JButton playButton = new JButton("PLAY");
    private  JLabel title = new JLabel("Space Crusader");
 
    private JLabel playerscore = new JLabel("Score: ");
    private JLabel playerID= new JLabel("Username: ");
    
    

    public MenuPanel(){
        this.setLayout(layout);

        layout.putConstraint(SpringLayout.WEST, title,200 , SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.WEST, playerscore,200 , SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, playerscore, 200, SpringLayout.NORTH, this);

         layout.putConstraint(SpringLayout.WEST, playerID,200 , SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, playerID, 175, SpringLayout.NORTH, this);


        layout.putConstraint(SpringLayout.WEST, playButton,300, SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, playButton, 250, SpringLayout.NORTH, this);

        Font customFont = new Font("Comic Sans", Font.BOLD, 50);
        title.setFont(customFont);

       
        playButton.setPreferredSize(new Dimension(200,75));
        this.add(title);
        this.add(playButton);
        this.add(playerscore);
        this.add(playerID);
       

    }

    public JButton getButton(){
        return playButton;
    }

    public void setScore(String s){
        playerscore.setText(s);
    }
    public void setID(String s){
        playerID.setText(s);
    }

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

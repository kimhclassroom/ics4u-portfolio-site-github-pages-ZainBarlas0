
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HolderPanel extends  JPanel {
    private  BorderLayout border = new BorderLayout();// border layout
    private SubPanel  sub = new SubPanel();// object of the sub class that contains the login and sign up pages
    private  JPanel buttonPanel = new JPanel(new FlowLayout());// Jpanel that stores buttons
    //buttons used to swtich between the login and signup pages
    private  JButton signup = new JButton("SignUp");
    private JButton login = new JButton("Login");
    
    public HolderPanel() throws FileNotFoundException{
        this.setLayout(border);// setting this panles layout
        this.add(sub, BorderLayout.CENTER);// adding the subpanel with a layout contraint
        
        //adding the two buttons to the button panel
        buttonPanel.add(signup);
        buttonPanel.add(login);
        
    
        //adding the button panel to the Holder panel
        this.add(buttonPanel, BorderLayout.SOUTH);
    }


    //gets the the signup page button
    public JButton getSign(){
        return signup;
    }

    //gets the login page button
    public JButton getLog(){
        return login;
    }

    // gets the subpanel
    public SubPanel getSubPanel(){
        return sub;
    }
    
}

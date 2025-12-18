
import java.awt.CardLayout;
import java.io.FileNotFoundException;
import javax.swing.JPanel;

public class SubPanel extends JPanel{
    private  CardLayout  card =  new CardLayout();// card layout
    //sign up and login page objects
    private SignupPage signup = new SignupPage();
    private  LoginPage login = new LoginPage();
        
    public SubPanel() throws FileNotFoundException{
       
        this.setLayout(card);// setting the panels layout as card layout
        // adding both login and sign up page to the deck
        this.add(signup, "Signup");
        this.add(login, "Login");

        
    }
    //fucntion to show login page
    public void loginshow(){
        card.show(this, "Login");
    }

    //fucntion to  show sign up page
     public void signupshow(){
        card.show(this, "Signup");
    }


    //gets login page object
    public LoginPage getLogin(){
        return login;
    }
    //gets signup page object
    public SignupPage getSignup(){
        return signup;
    }

   
}

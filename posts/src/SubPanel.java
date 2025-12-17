
import java.awt.CardLayout;
import java.io.FileNotFoundException;
import javax.swing.JPanel;

public class SubPanel extends JPanel{
    private  CardLayout  card =  new CardLayout();
    private SignupPage signup = new SignupPage();
    private  LoginPage login = new LoginPage();
        
    public SubPanel() throws FileNotFoundException{
       
        this.setLayout(card);
        this.add(signup, "Signup");
        this.add(login, "Login");

        
    }
    public void loginshow(){
        card.show(this, "Login");
    }
     public void signupshow(){
        card.show(this, "Signup");
    }

    public LoginPage getLogin(){
        return login;
    }

    public SignupPage getSignup(){
        return signup;
    }

   
}

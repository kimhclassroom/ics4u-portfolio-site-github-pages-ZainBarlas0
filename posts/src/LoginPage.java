
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LoginPage extends JPanel {
      
     //labels and text fields
      private   JButton submit = new JButton("Submit");
      private  JLabel name  = new JLabel("UserID: ");
      private    JLabel password = new JLabel("Password: ");
      private   JTextField enterName = new JTextField();
      private    JTextField enterPassword = new JTextField();
      private JLabel title = new JLabel("SPACE CRUSADER: LOGIN ");
   
       
       public LoginPage() throws FileNotFoundException{
       // this.setLayout(card);
        this.setPreferredSize(new Dimension(300,300));
        //defining panel
        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);

        enterName.setColumns(15);
        enterPassword.setColumns(15);
        
       
        

        //Adding contraints
        springLayout.putConstraint(SpringLayout.WEST, name,100 , SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH, name,100 , SpringLayout.NORTH,this);
        springLayout.putConstraint(SpringLayout.WEST, enterName,150, SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH, enterName,100 , SpringLayout.NORTH,this);

        springLayout.putConstraint(SpringLayout.WEST, password,100 , SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH, password,150 , SpringLayout.NORTH,this);
        springLayout.putConstraint(SpringLayout.WEST, enterPassword,170, SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH, enterPassword,150 , SpringLayout.NORTH,this);

        springLayout.putConstraint(SpringLayout.WEST, title,100, SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH, title,40 , SpringLayout.NORTH,this);

       

        springLayout.putConstraint(SpringLayout.WEST, submit,100, SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH, submit,200 , SpringLayout.NORTH,this);

        Font customFont = new Font("Comic Sans", Font.BOLD, 40);
        title.setFont(customFont);

        //adding to make them appear
        this.add(submit);
        this.add(name);
        this.add(enterName);
        this.add(password);
        this.add(enterPassword);
        this.add(title);
     
     
        //this.add(this);
        
    }

    public JButton getSubmit(){
        return submit;
    }

    public String getName(){
        return enterName.getText().trim();
    }

     public String getpassword(){
        return enterPassword.getText().trim();
    }

    

}

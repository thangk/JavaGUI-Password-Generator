import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Random;


public class PasswordGenerator extends JFrame {


    private Toolkit toolkit = getToolkit();
    private Dimension size = toolkit.getScreenSize();

    public PasswordGenerator() {
        new JFrame();
        this.setTitle("Simple Password Generator v1.0");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 200);
        this.setLayout(null);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);

        ContainerArea ca = new ContainerArea();

        ca.setBounds(0, 0, 500, 200);

        this.add(ca);
    }



    public class ContainerArea extends JPanel {

        Font font = new Font("SansSerif", Font.BOLD, 20);
        String lengthlist[] = {"8", "12", "14", "16", "18", "20"};

        private JTextField generatedPassword = new JTextField();
        private JButton generateButton = new JButton("Generate");

        private JLabel passLengthLabel = new JLabel("Length");
        private JCheckBox specialCharsCheckBox = new JCheckBox("Special Characters");
        private JComboBox<String> lengthDropDown = new JComboBox<String>(lengthlist);

        private int passwordLen = 8;        // default is 8

        private int SpecialChars = 0;


        public ContainerArea() {

            this.setLayout(null);

            // specialChars.setBounds(55, 45, 250, 30);
            specialCharsCheckBox.setBounds(170, 45, 250, 30);
            specialCharsCheckBox.setFocusable(false);
            specialCharsCheckBox.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    // TODO Auto-generated method stub
                    SpecialChars = (e.getStateChange() == 1) ? 1 : 0;
                }
                
            });

            lengthDropDown.setBounds(55, 50, 50, 20);
            passLengthLabel.setBounds(110, 50, 50, 20);
            
            lengthDropDown.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    String ss = "" + lengthDropDown.getItemAt(lengthDropDown.getSelectedIndex());
                    passwordLen = Integer.parseInt(ss);
                }

            });
            

            generatedPassword.setBounds(55, 85, 250, 30);
            generatedPassword.setEditable(false);
            generatedPassword.setFont(font);

            generateButton.setBounds(315, 85, 100, 30);
            generateButton.setFocusable(false);

            generateButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    if (e.getSource() == generateButton) {
                        generatePass();
                    }
                }
                
            });
            
            this.add(specialCharsCheckBox);
            this.add(lengthDropDown);
            this.add(passLengthLabel);
            this.add(generatedPassword);
            this.add(generateButton);
        }

        
        public void generatePass() {


        Random random = new Random();
        int chartype;

        String resultString = "";

        for (int i = 0; i < passwordLen; i++) {

            if (SpecialChars == 1) {
                chartype = random.nextInt(4);
            } else {
                chartype = random.nextInt(3) + 1;
            }

            char resultChar = '0';
    
            switch (chartype) {
                // special chars
                case 0 : resultChar = (char) (random.nextInt(6) + 33); break;
    
                // lowercase letters
                case 1 : resultChar = (char) (random.nextInt(26) + 97); break;
    
                // uppsercase letters
                case 2 : resultChar = (char) (random.nextInt(26) + 65); break;
    
                // numbers
                case 3 : resultChar = (char) (random.nextInt(10) + 48); break;
                default : break;
            }
    
            resultString += Character.toString(resultChar);

        }

        generatedPassword.setText(resultString);

    }


    }

    public static void main(String[] args) {
        new PasswordGenerator();
    }
    
}


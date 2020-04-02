package app;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegisterForm extends JFrame {

    private JPanel jpan;
    private JPasswordField elsojelszo;
    private JTextField szulhely;
    private JTextField neptun;
    private JTextField oktazon;
    private JPasswordField masodikjelszo;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JButton regbutton;
    private JLabel hiba;
    private JTextField nev;


    public RegisterForm()
    {
        regbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LoginController.userpass.containsKey(neptun.getText()))
                {
                    hiba.setText("Van ilyen regisztralt neptunkod");
                }else{
                    {
                        String password1 = new String(elsojelszo.getPassword());
                        String password2 = new String(masodikjelszo.getPassword());
                        if (!password1.equals(password2))
                            hiba.setText("A ket jelszo nem egyezik");
                        else
                        {
                            LoginController.userpass.put(neptun.getText(),password1);
                            JOptionPane.showMessageDialog(null,"Sikeres regisztracio!");
                            dispose();
                        }
                    }
                }
            }
        });
        szulhely.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });
        nev.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });
        neptun.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });
        elsojelszo.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });
        masodikjelszo.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });
        oktazon.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });

        setBounds(300,300,1000,600);
        setContentPane(jpan);
        setVisible(true);
        setDefaultCloseOperation(RegisterForm.EXIT_ON_CLOSE);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author dev
 */
public class Login implements GUI_Interface{
    
    private JPanel loginform;
    
    public JPanel show(JPanel loginform) {
        this.loginform = loginform;
        JTextField login = new JTextField(10);
        JPasswordField password = new JPasswordField(10);
        loginform.add(login, BorderLayout.CENTER);
        loginform.add(password, BorderLayout.CENTER);
        
        JButton button = new JButton("login");
        ActionListener actionListener = new LoginActionListener();
        button.addActionListener(actionListener);
        loginform.add(button, BorderLayout.CENTER);
        return loginform;
    }
    
    public void hide(){
        
    }
    
    public class LoginActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            loginform.removeAll(); 
            loginform.updateUI();
            Messager msg = Messager.getInstance();
            msg.show(loginform);
        }
    }

    
}

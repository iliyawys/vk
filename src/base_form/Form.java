/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base_form;

import API.API;
import GUI.Login;
import GUI.Messager;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author insane
 */
public class Form extends JDialog {

    private JPanel contentPanel;
    private TrayIcon trayIcon;
    private SystemTray systemTray = SystemTray.getSystemTray();

    Form() throws IOException, AWTException {
        setTitle("vk messager");
        getContentPane().setLayout(new BorderLayout());
        seeIcon();
        initComponents();
//        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(100, 100, 800, 300);
    }

    private void initComponents() {
        contentPanel = new JPanel();
        contentPanel.setOpaque(true);
        contentPanel.setBackground(Color.WHITE);
        if(API.getInstance().checkAuth()){
            /*Login loginform = new Login();  
            loginform.show(contentPanel);*/
            Messager msg = Messager.getInstance();
            contentPanel = msg.show(contentPanel);
        }
//        contentPanel.setPreferredSize(new Dimension (300,300));
//        getContentPane().setPreferredSize();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        getContentPane().add(contentPanel);
    }

    private void seeIcon() throws IOException, AWTException {
        //popup for icon
        PopupMenu popupMenu = new PopupMenu();
        //PopupMenu popupNewMenu = new PopupMenu();
        MenuItem popupExit = new MenuItem("Exit");
        //popupMenu.add(popupExit);
        popupMenu.add(popupExit);
        //Icon
        trayIcon = new TrayIcon(ImageIO.read(getClass().getResource("ico.gif")), "vk messager");
        trayIcon.setImageAutoSize(true);
        trayIcon.setPopupMenu(popupMenu);
        systemTray.add(trayIcon);
        //Icon view listers
        trayIcon.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                setVisible(true);
            }
        });
        trayIcon.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!isVisible()) {
                    setVisible(true);
                }
            }
        });
        //Listers Icon popup
        popupExit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    public static void main(String args[]) throws IOException, AWTException {
        Form vf = new Form();
//        vf.setResizable(false);
//        vf.setDefaultCloseOperation(JDialog.); 
        vf.setVisible(true);
    }
}

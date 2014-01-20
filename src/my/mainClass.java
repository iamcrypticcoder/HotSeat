/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my;

import javax.swing.JWindow;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.UIManager;


/**
 *
 * @author RPM
 */
public class mainClass {

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JWindow mainWindow = new JWindow();
        JPanel panelWindow = ( JPanel ) mainWindow.getContentPane();
        mainPanel panel  = new mainPanel();

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();


        panel.RunCrore();
     
        panel.setContentSizeAndPosition(800, 600);


        //panelWindow.add(panel, BorderLayout.EAST);

        mainWindow.setBounds(0, 0, 800, 600);
        panelWindow.add(panel, BorderLayout.CENTER);

        mainWindow.setVisible( true );
    
    }
}

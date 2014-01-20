/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my;

import java.awt.BorderLayout;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


import javax.swing.JFrame;

/**
 *
 * @author MAHBUB
 */
public class SplashScreen extends JFrame {

    public backGrndPanel bckPanel;
    public JButton okB;

    public SplashScreen()
    {
         setLayout(new BorderLayout());
         bckPanel=new backGrndPanel();
         add(bckPanel, BorderLayout.CENTER);
         
         okB = new JButton("Enter");
         add(okB, BorderLayout.WEST);
         

         this.setSize(1024, 768);
         setComponentZOrder(bckPanel, 0);
         setComponentZOrder(okB, 1);
     //    this.setVisible( true );

    }

     public class backGrndPanel extends JPanel
     {
        public ImageIcon backImage;
        
        public backGrndPanel()
        {
            setLayout(new BorderLayout());
        }


         public void paintComponent(Graphics g)              
        {
             super.paintComponent(g);
              backImage=new ImageIcon(getClass().getResource("GG.jpg"));

              backImage.paintIcon(this, g, 0, 0);
        }

    }


}

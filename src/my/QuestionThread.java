/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package my;

import java.awt.Color;
import javax.swing.JTextArea;

/**
 *
 * @author MAHBUB
 */
public class QuestionThread implements Runnable {

    Thread thread;

    public JTextArea textArea;
    String questionTitle;
    
    public QuestionThread(JTextArea txtArea, String str)
    {
        textArea = txtArea;
        questionTitle = str;

        thread = new Thread();
        thread.start();

        textArea.setText(questionTitle);
                textArea.setForeground(Color.MAGENTA);
    }

    public void run()
    {
       

       

        
    }
    
}

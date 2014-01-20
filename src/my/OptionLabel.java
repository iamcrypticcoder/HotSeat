/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * OptionLabel.java
 *
 * Created on Jul 1, 2010, 12:40:04 AM
 */


package my;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author RPM
 */
public class OptionLabel extends javax.swing.JPanel {

    /** Creates new form OptionLabel */
    public OptionLabel() {
        initComponents();

        this.addMouseListener(new MouseHandler());
    }


    public void setOptionText(String title, int index)
    {
        indexLabel.setText("" + (char)(64 + index));
        titleLabel.setText(title);
    }

    public void setOptionFont(Font font)
    {
        indexLabel.setFont(font);
        titleLabel.setFont(font);
    }

    public void hideText()
    {
        indexLabel.setText("");
        titleLabel.setText("");
    }


    private class MouseHandler extends MouseAdapter
    {
        public void mouseEntered( MouseEvent evt)
        {
            setBackground(Color.PINK);

            indexLabel.setFont(new Font("Calibri", Font.BOLD, 30));
            titleLabel.setFont(new Font("Calibri", Font.BOLD, 30));
            
            
            indexLabel.setForeground(Color.BLACK);
            titleLabel.setForeground(Color.BLACK);
        }
        public void mouseExited( MouseEvent evt)
        {
            setBackground(Color.BLACK);

            indexLabel.setFont(new Font("Calibri", Font.BOLD, 22));
            titleLabel.setFont(new Font("Calibri", Font.BOLD, 22));

            indexLabel.setForeground(new Color(153, 255, 204));
            titleLabel.setForeground(new Color(153, 255, 204));
        }
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        indexLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        indexLabel.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        indexLabel.setForeground(new java.awt.Color(153, 255, 204));
        indexLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        indexLabel.setText("A");

        titleLabel.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(153, 255, 204));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("OptionTitle");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(indexLabel)
                .addGap(28, 28, 28)
                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(indexLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel indexLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

}

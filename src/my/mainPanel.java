/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mainPanel.java
 *
 * Created on Jul 4, 2010, 8:12:43 PM
 */

package my;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author RPM
 */
public class mainPanel extends javax.swing.JPanel implements KeyListener {

    private
        String questionFolderPath;
        Question question;
        int clientAns;
        String clientName;
        int questionNumber;

        enum PHASE {EASY, MEDIUM, HARD};
        final String[] face = {"easy", "medium", "hard"};
        PHASE currentPhase;
    //    Suffle suffleEasy, suffleMedium, suffleHard, suffleSuperHard;
//        Suffle suffle;
        Suffle suffleEasy;
        Suffle suffleMedium;
        Suffle suffleHard;


        int currentRand;
        int fiftyFiftyClick=0;

        int valueOfQuestion[] = { 1, 2, 3, 5, 10,
                                    20, 40, 80, 160, 320,
                                        625, 1250, 2500, 5000, 10000 };

        int earnOfEASY = 10;
        int earnOfMEDIUM = 320;
        int earnOfHARD = 10000;

        int totalEarned = 0;

        RightAnswerThread rightT;

    /** Creates new form mainPanel */
    public mainPanel() {
        initComponents();

      //  questionFolderPath = "E:\\Documents and Settings\\MAHBUB\\My Documents\\WhoWantsToBeAMillioner\\QuestionDatabase\\";
        questionFolderPath = "QuestionDatabase/";


        this.setBackground(Color.BLACK);


     //   SplashScreen scrn = new SplashScreen();
     //   scrn.setVisible(true);
     

      //  scrn.setVisible(false);

        question = new Question();

//        suffle = new Suffle(15);
 //       suffle.suffle();
        //suffle.Print();
 
    //    layerPanel.setVisible(false);
        questionNumber = 1;

        backImageJLabel.setIcon(new ImageIcon("StartImage.jpg"));
        HideAllComponent();
        backImageJLabel.setVisible(true);
        startGameJButton.setVisible(true);
        adminJButton.setVisible(true);
        rightJLabel.setVisible(false);
        CountQuestion();
    }

    private void CountQuestion()
    {
        int count;
        File f = new File("");
        for(count = 1; count <100; count++) {
            f = new File(questionFolderPath + "easy/" + "easy" + String.valueOf(count) + ".ques");
            if(! f.exists())
                break;
        }
        System.out.println("Easy Total : " + String.valueOf(count-1));
        suffleEasy = new Suffle(count-1);


        for(count = 1; count <100; count++) {
            f = new File(questionFolderPath + "medium/" + "medium" + String.valueOf(count) + ".ques");
            if(! f.exists())
                break;
        }
        System.out.println("Medium Total : " + String.valueOf(count-1));
        suffleMedium = new Suffle(count-1);

        for(count = 1; count <100; count++) {
            f = new File(questionFolderPath + "hard/" + "hard" + String.valueOf(count) + ".ques");
            if(! f.exists())
                break;
        }
        System.out.println("Hard Total : "  + String.valueOf(count-1));
        suffleHard = new Suffle(count-1);



    }


    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        
    }

    public void keyReleased(KeyEvent e) {

    }
  

    public void HideAllComponent()
    {
            totalAmountJLabel.setVisible(false);
            backImageJLabel.setVisible(false);
            earningsTagJLabel.setVisible(false);
            exitJButton.setVisible(false);
            fiftyFiftyJButton.setVisible(false);
            mainForfeitJButton.setVisible(false);
            jScrollPane1.setVisible(false);
            op1OptionLabel.setVisible(false);
            op2OptionLabel.setVisible(false);
            op3OptionLabel.setVisible(false);
            op4OptionLabel.setVisible(false);
            playerNameJLabel.setVisible(false);
            playerNameTagJLabel.setVisible(false);
            questionNoJLabel.setVisible(false);
            questionjTextArea.setVisible(false);
            startGameJButton.setVisible(false);
            adminJButton.setVisible(false);


            gameOverMessageJLabel.setVisible(false);
            playAgainJButton.setVisible(false);
            forfeitJButton.setVisible(false);
            proceedJButton.setVisible(false);
    }

    public void ShowGameComponent()
    {
            totalAmountJLabel.setVisible(true);
            backImageJLabel.setVisible(true);
            earningsTagJLabel.setVisible(true);
            exitJButton.setVisible(true);
            fiftyFiftyJButton.setVisible(true);
            mainForfeitJButton.setVisible(true);
            jScrollPane1.setVisible(true);
            op1OptionLabel.setVisible(true);
            op2OptionLabel.setVisible(true);
            op3OptionLabel.setVisible(true);
            op4OptionLabel.setVisible(true);
            playerNameJLabel.setVisible(true);
            playerNameTagJLabel.setVisible(true);
            questionNoJLabel.setVisible(true);
            questionjTextArea.setVisible(true);


    }

    private String InputName()
    {
        String str;
        str = JOptionPane.showInputDialog(this, "Enter Name:");
        if(str != null)
            return str;
        else {
            System.exit(0);
        }
        return null;
    }

    private void calculatePhase()
    {
        if(questionNumber <= 5)
            currentPhase = PHASE.EASY;
        else if(questionNumber > 5 && questionNumber <=10)
            currentPhase = PHASE.MEDIUM;
        else if(questionNumber > 10 && questionNumber <= 15)
            currentPhase = PHASE.HARD;
    }

    private String calculateFace()
    {
        if(currentPhase == PHASE.EASY)
            return "easy";
        else if(currentPhase == PHASE.MEDIUM)
            return "medium";
        else if(currentPhase == PHASE.HARD)
            return "hard";
        return "";
    }

    private void RetrieveAndShowQuestion(String fileName)
    {   
        try {
            
        	FileInputStream inputFile = new FileInputStream(questionFolderPath + calculateFace() + "/" + fileName + ".ques");
            ObjectInputStream inputObject = new ObjectInputStream(inputFile);
            question = ( Question )inputObject.readObject();
            question.printQuestion();
            //	int a = inputFile.read();
        //	System.out.println(a);
        }
        catch (IOException ioException) {
            System.out.println("IOException occurred");
        }
        catch ( ClassNotFoundException cassNotFoundException) {
			System.out.println("ClassNotFoundException OCCURED");
		}

        questionjTextArea.setText(question.questionTitle);

        op1OptionLabel.setOptionText(question.options[0], 1);
        op2OptionLabel.setOptionText(question.options[1], 2);
        op3OptionLabel.setOptionText(question.options[2], 3);
        op4OptionLabel.setOptionText(question.options[3], 4);

        questionNoJLabel.setText("Question " + questionNumber);
        System.out.println("Question Number : " + questionNumber);
    }

    public void RunCrore()
    {
/*
        calculatePhase();
        RetrieveAndShowQuestion(calculateFace() + Integer.toString(currentRand = suffle.nextInt()));
        System.out.println(question.Answer());
        System.out.println("Current Random :" + currentRand);
 */
//        GameStartThread  t = new GameStartThread("GameStartThread started...");
//        ResetGame();
 }

    public void RightAnswer()
    {
        totalEarned = valueOfQuestion[questionNumber-1] * 1000;
        totalAmountJLabel.setText(Integer.toString(totalEarned));
        questionNumber++;
        calculatePhase();

        if(currentPhase == PHASE.EASY)
            currentRand = suffleEasy.nextInt();
        else if(currentPhase == PHASE.MEDIUM)
            currentRand = suffleMedium.nextInt();
        else if(currentPhase == PHASE.HARD)
            currentRand = suffleHard.nextInt();

        RetrieveAndShowQuestion(calculateFace() + Integer.toString(currentRand));
        System.out.println(question.Answer());
        System.out.println("Current Random :" + currentRand);
    }

    


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layerPanel = new javax.swing.JLayeredPane();
        totalAmountJLabel = new javax.swing.JLabel();
        earningsTagJLabel = new javax.swing.JLabel();
        playerNameTagJLabel = new javax.swing.JLabel();
        playerNameJLabel = new javax.swing.JLabel();
        questionNoJLabel = new javax.swing.JLabel();
        exitJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        questionjTextArea = new javax.swing.JTextArea();
        op4OptionLabel = new my.OptionLabel();
        op1OptionLabel = new my.OptionLabel();
        op3OptionLabel = new my.OptionLabel();
        op2OptionLabel = new my.OptionLabel();
        adminJButton = new javax.swing.JButton();
        fiftyFiftyJButton = new javax.swing.JButton();
        mainForfeitJButton = new javax.swing.JButton();
        startGameJButton = new javax.swing.JButton();
        proceedJButton = new javax.swing.JButton();
        forfeitJButton = new javax.swing.JButton();
        rightJLabel = new javax.swing.JLabel();
        backImageJLabel = new javax.swing.JLabel();
        gameOverMessageJLabel = new javax.swing.JLabel();
        playAgainJButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(0, 0, 0));

        totalAmountJLabel.setFont(new java.awt.Font("Calibri", 1, 18));
        totalAmountJLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        totalAmountJLabel.setText("00");
        totalAmountJLabel.setBounds(170, 50, 50, 20);
        layerPanel.add(totalAmountJLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        earningsTagJLabel.setFont(new java.awt.Font("Calibri", 1, 18));
        earningsTagJLabel.setForeground(new java.awt.Color(204, 255, 204));
        earningsTagJLabel.setText("Total Earnings :");
        earningsTagJLabel.setBounds(10, 50, 140, 20);
        layerPanel.add(earningsTagJLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        playerNameTagJLabel.setFont(new java.awt.Font("Calibri", 1, 18));
        playerNameTagJLabel.setText("Player Name :");
        playerNameTagJLabel.setBounds(10, 20, 110, 25);
        layerPanel.add(playerNameTagJLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        playerNameJLabel.setFont(new java.awt.Font("Calibri", 1, 18));
        playerNameJLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        playerNameJLabel.setText("Rupom");
        playerNameJLabel.setBounds(170, 20, 110, 25);
        layerPanel.add(playerNameJLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        questionNoJLabel.setFont(new java.awt.Font("Calibri", 1, 24));
        questionNoJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        questionNoJLabel.setText("Question 1");
        questionNoJLabel.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                questionNoJLabelHierarchyChanged(evt);
            }
        });
        questionNoJLabel.setBounds(0, 190, 1080, 30);
        layerPanel.add(questionNoJLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        exitJButton.setText("Exit");
        exitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitJButtonActionPerformed(evt);
            }
        });
        exitJButton.setBounds(30, 660, 120, 23);
        layerPanel.add(exitJButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        questionjTextArea.setBackground(new java.awt.Color(153, 153, 255));
        questionjTextArea.setColumns(20);
        questionjTextArea.setEditable(false);
        questionjTextArea.setFont(new java.awt.Font("Calibri", 1, 24));
        questionjTextArea.setForeground(new java.awt.Color(0, 0, 153));
        questionjTextArea.setLineWrap(true);
        questionjTextArea.setRows(4);
        questionjTextArea.setText("What is your name ?");
        questionjTextArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane1.setViewportView(questionjTextArea);

        jScrollPane1.setBounds(10, 230, 1000, 127);
        layerPanel.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        op4OptionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                op4OptionLabelMouseClicked(evt);
            }
        });
        op4OptionLabel.setBounds(560, 460, 450, 48);
        layerPanel.add(op4OptionLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        op1OptionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                op1OptionLabelMouseClicked(evt);
            }
        });
        op1OptionLabel.setBounds(10, 400, 450, 48);
        layerPanel.add(op1OptionLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        op3OptionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                op3OptionLabelMouseClicked(evt);
            }
        });
        op3OptionLabel.setBounds(10, 460, 450, 48);
        layerPanel.add(op3OptionLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        op2OptionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                op2OptionLabelMouseClicked(evt);
            }
        });
        op2OptionLabel.setBounds(560, 400, 450, 48);
        layerPanel.add(op2OptionLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        adminJButton.setText("Admin");
        adminJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminJButtonActionPerformed(evt);
            }
        });
        adminJButton.setBounds(440, 530, 130, 70);
        layerPanel.add(adminJButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fiftyFiftyJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/50-50 coin.png"))); // NOI18N
        fiftyFiftyJButton.setOpaque(false);
        fiftyFiftyJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fiftyFiftyJButtonActionPerformed(evt);
            }
        });
        fiftyFiftyJButton.setBounds(20, 120, 100, 100);
        layerPanel.add(fiftyFiftyJButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        mainForfeitJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/forfeit.png"))); // NOI18N
        mainForfeitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainForfeitJButtonActionPerformed(evt);
            }
        });
        mainForfeitJButton.setBounds(130, 120, 100, 100);
        layerPanel.add(mainForfeitJButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        startGameJButton.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        startGameJButton.setText("Start Game");
        startGameJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameJButtonActionPerformed(evt);
            }
        });
        startGameJButton.setBounds(430, 380, 170, 90);
        layerPanel.add(startGameJButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        proceedJButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        proceedJButton.setText("Proceed");
        proceedJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proceedJButtonActionPerformed(evt);
            }
        });
        proceedJButton.setBounds(380, 480, 110, 40);
        layerPanel.add(proceedJButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        forfeitJButton.setFont(new java.awt.Font("Tahoma", 1, 18));
        forfeitJButton.setText("Forfeit");
        forfeitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forfeitJButtonActionPerformed(evt);
            }
        });
        forfeitJButton.setBounds(580, 480, 100, 40);
        layerPanel.add(forfeitJButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        rightJLabel.setFont(new java.awt.Font("Tahoma", 1, 24));
        rightJLabel.setForeground(new java.awt.Color(0, 102, 51));
        rightJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rightJLabel.setText("Correct !!!");
        rightJLabel.setBounds(320, 570, 410, 110);
        layerPanel.add(rightJLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        backImageJLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my/backImage.jpg"))); // NOI18N
        backImageJLabel.setBounds(0, 0, 1080, 710);
        layerPanel.add(backImageJLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        gameOverMessageJLabel.setFont(new java.awt.Font("Calibri", 1, 24));
        gameOverMessageJLabel.setForeground(new java.awt.Color(102, 255, 102));
        gameOverMessageJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameOverMessageJLabel.setText("Hi I am Mahbub");
        gameOverMessageJLabel.setBounds(0, 110, 1084, 50);
        layerPanel.add(gameOverMessageJLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        playAgainJButton.setText("Play Again");
        playAgainJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playAgainJButtonActionPerformed(evt);
            }
        });
        playAgainJButton.setBounds(490, 160, 130, 23);
        layerPanel.add(playAgainJButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1104, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(layerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void op4OptionLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_op4OptionLabelMouseClicked
        clientAns = 4;


                rightT = new RightAnswerThread("Correct !!!");

 //           RightAnswer();
/*            if(questionNumber == 6 ) {
                if(questionNumber == 6)
                    gameOverMessageJLabel.setText("You have completed the First Round ! Do you want to proceed for the Second Round ");
                else
                    gameOverMessageJLabel.setText("You have completed the Second Round ! Do you want to proceed for the Third Round ");
                HideAllComponent();
                gameOverMessageJLabel.setVisible(true);
                proceedJButton.setVisible(true);
                forfeitJButton.setVisible(true);
            }

            if(questionNumber>=15)
            {
                HideAllComponent();

                gameOverMessageJLabel.setVisible(true);
                playAgainJButton.setVisible(true);
                gameOverMessageJLabel.setText("Congrats ! \n You Have Completed The Game!!! \nYou Have Own The Crown Of Millioneer!!!");

            }

        }
        else
        {
            HideAllComponent();

            gameOverMessageJLabel.setVisible(true);
            playAgainJButton.setVisible(true);
            //gameOverMessageJLabel.setText("SORRY ! Wrong Answer!!!\n ");
            //You Win Taka 0 BDT
            if(questionNumber > 5 && questionNumber <10)
            {
                gameOverMessageJLabel.setText("SORRY ! Wrong Answer!!!\nBut You Have Completed The 1st Round!!!\nYou Win Taka 10000 BDT" );
            }
            else if(questionNumber > 10 && questionNumber <15)
            {
                 gameOverMessageJLabel.setText("SORRY ! Wrong Answer!!!\nBut You Have Completed The 2nd Round!!!\nYou Win Taka 320000 BDT" );
            }
            else
                gameOverMessageJLabel.setText("SORRY ! Wrong Answer!!!\nYou Have Failed to Complete 1st Round!!!\nYou Got Taka 0 BDT");
        }
 */
}//GEN-LAST:event_op4OptionLabelMouseClicked

    private void op1OptionLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_op1OptionLabelMouseClicked
        clientAns = 1;


                rightT = new RightAnswerThread("Correct !!!");

}//GEN-LAST:event_op1OptionLabelMouseClicked

    private void op3OptionLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_op3OptionLabelMouseClicked
        clientAns = 3;

                rightT = new RightAnswerThread("Correct !!!");

}//GEN-LAST:event_op3OptionLabelMouseClicked

    private void op2OptionLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_op2OptionLabelMouseClicked
        clientAns = 2;

                rightT = new RightAnswerThread("Correct !!!");


//            RightAnswer();
/*
            if(questionNumber == 6 || questionNumber == 11) {
                if(questionNumber == 6)
                    gameOverMessageJLabel.setText("You have completed the First Round ! Do you want to proceed for the Second Round ");
                else
                    gameOverMessageJLabel.setText("You have completed the Second Round ! Do you want to proceed for the Third Round ");

                HideAllComponent();
                gameOverMessageJLabel.setVisible(true);
                proceedJButton.setVisible(true);
                forfeitJButton.setVisible(true);
            }

            if(questionNumber>=15)
        {
            HideAllComponent();

            gameOverMessageJLabel.setVisible(true);
            playAgainJButton.setVisible(true);
            gameOverMessageJLabel.setText("Congrats ! \n You Have Completed The Game!!! \nYou Have Own The Crown Of Millioneer!!!");

        }

        }
        else
        {
            HideAllComponent();

            gameOverMessageJLabel.setVisible(true);
            playAgainJButton.setVisible(true);
            //gameOverMessageJLabel.setText("SORRY ! Wrong Answer!!!\n");

            if(questionNumber > 5 && questionNumber <10)
            {
                gameOverMessageJLabel.setText("SORRY ! Wrong Answer!!!\nBut You Have Completed The 1st Round!!!\nYou Win Taka 10000 BDT" );
            }
            else if(questionNumber > 10 && questionNumber <15)
            {
                 gameOverMessageJLabel.setText("SORRY ! Wrong Answer!!!\nBut You Have Completed The 2nd Round!!!\nYou Win Taka 320000 BDT" );
            }
            else
                gameOverMessageJLabel.setText("SORRY ! Wrong Answer!!!\nYou Have Failed Complete The 1st Round!!!\nYou Got Taka 0 BDT");
        }
  */
}//GEN-LAST:event_op2OptionLabelMouseClicked

    private void exitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitJButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitJButtonActionPerformed

    private void performFiftyFifty(int a, int b)
    {
        if(a == 1 || b == 1)
            op1OptionLabel.hideText();
        if(a == 2 || b == 2)
            op2OptionLabel.hideText();
        if(a == 3 || b == 3)
            op3OptionLabel.hideText();
        if(a == 4 || b == 4)
            op4OptionLabel.hideText();
    }

    public void ResetGame()
    {
        backImageJLabel.setIcon(new ImageIcon("backImage.jpg"));
        backImageJLabel.setVisible(true);
        playerNameJLabel.setText(InputName());

        GameStartThread t = new GameStartThread("GameStartThread");

        startGameJButton.setVisible(false);
        gameOverMessageJLabel.setVisible(false);
        playAgainJButton.setVisible(false);
        
//        suffle.suffle();
        suffleEasy.suffle();
        suffleMedium.suffle();
        suffleHard.suffle();

        totalEarned = 0;
        questionNumber = 1;

        totalAmountJLabel.setText("0");

        layerPanel.setVisible(true);

        calculatePhase();

        if(currentPhase == PHASE.EASY)
            currentRand = suffleEasy.nextInt();
        else if(currentPhase == PHASE.MEDIUM)
            currentRand = suffleMedium.nextInt();
        else if(currentPhase == PHASE.HARD)
            currentRand = suffleHard.nextInt();

        RetrieveAndShowQuestion(calculateFace() + Integer.toString(currentRand));  // currentRand = suffle.nextInt()

        playAgainJButton.setVisible(false);
        gameOverMessageJLabel.setVisible(false);
        
    }


    private void playAgainJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playAgainJButtonActionPerformed
        ResetGame();
    }//GEN-LAST:event_playAgainJButtonActionPerformed

    private void questionNoJLabelHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_questionNoJLabelHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_questionNoJLabelHierarchyChanged

    private void startGameJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameJButtonActionPerformed
        startGameJButton.setVisible(false);
        adminJButton.setVisible(false);
        ResetGame();
    }//GEN-LAST:event_startGameJButtonActionPerformed

    private void proceedJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proceedJButtonActionPerformed
        ShowGameComponent();

        proceedJButton.setVisible(false);
        forfeitJButton.setVisible(false);
        if(fiftyFiftyClick==1)
            fiftyFiftyJButton.setVisible(false);

    }//GEN-LAST:event_proceedJButtonActionPerformed

    private void forfeitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forfeitJButtonActionPerformed
        HideAllComponent();

        gameOverMessageJLabel.setVisible(true);
        playAgainJButton.setVisible(true);

        gameOverMessageJLabel.setText("Congrats ! \n You Win Taka " + Integer.toString(totalEarned) + " BDT");

    }//GEN-LAST:event_forfeitJButtonActionPerformed

    private void fiftyFiftyJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fiftyFiftyJButtonActionPerformed
        Random random = new Random();
        int a = question.Answer(), b = question.Answer();

        while(a == question.Answer())
            a = random.nextInt(4) + 1;
        while(b == question.Answer() || b == a)
            b = random.nextInt(4) + 1;

        performFiftyFifty(a, b);
        System.out.print(a);
        System.out.println(b);
        fiftyFiftyJButton.setVisible(false);
        fiftyFiftyClick++;
}//GEN-LAST:event_fiftyFiftyJButtonActionPerformed

    private void mainForfeitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainForfeitJButtonActionPerformed
/*
        int solvedQuestion = questionNumber-1;
        if(questionNumber >= 5  && questionNumber <= 9)
            totalEarned = earnOfEASY*1000;
        else if(questionNumber >= 10  && questionNumber <= 14)
            totalEarned = earnOfMEDIUM*1000;
        else if(questionNumber == 15)
            totalEarned = earnOfHARD*1000;
*/
        HideAllComponent();

        gameOverMessageJLabel.setVisible(true);
        playAgainJButton.setVisible(true);

        gameOverMessageJLabel.setText("Congrats ! \n You Win Taka " + Integer.toString(totalEarned) + " BDT");
     
}//GEN-LAST:event_mainForfeitJButtonActionPerformed

    private void adminJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminJButtonActionPerformed
        new QuestionBuilder().setVisible(true);
    }//GEN-LAST:event_adminJButtonActionPerformed


   private int percent(int rate, int amount)
    {
        return (amount*rate)/100;

    }

    public void setContentSizeAndPosition(int w, int h)
    {
        int x, y, width, height;

        this.setSize(w, h);
        backImageJLabel.setSize(w, h);

        x = percent(3, w);
        y = percent(3, h);
        width = 200;
        height = 20;
/*
        playerNameTagJLabel.setBounds(x, y, playerNameTagJLabel.getWidth(), playerNameTagJLabel.getHeight());
        playerNameJLabel.setBounds(x+150, y, playerNameJLabel.getWidth(), playerNameJLabel.getHeight());
        earningsTagJLabel.setBounds(x, y + 30, earningsTagJLabel.getWidth(), earningsTagJLabel.getHeight());
        totalAmountJLabel.setBounds(x+150, y+30, totalAmountJLabel.getWidth(), totalAmountJLabel.getHeight());
*/
        playerNameTagJLabel.setBounds(x, y, width, height);
        playerNameJLabel.setBounds(x+150, y, width, height);
        earningsTagJLabel.setBounds(x, y + 30, width, height);
        totalAmountJLabel.setBounds(x+150, y+30, width, height);

       // backImageJLabel.setBounds(x, y, width+1100, height+800);


        width = percent(95, w);
        height = percent(18, h);
        x = (w - width) / 2;
        y = percent(35, h);
        jScrollPane1.setBounds(x, y, width, height);
        gameOverMessageJLabel.setBounds(x, y,  width, height);

        proceedJButton.setBounds(percent(40, w), y+200, 150, 80);
        forfeitJButton.setBounds(percent(40, w) + 200, y+200, 150, 80);
        rightJLabel.setBounds(percent(40, w) + 50, y+300, 150, 80);

        width = percent(20, w);
        height = percent(10, h);
        x = (w - width) / 2;
        y = (h - height) / 2;
        playAgainJButton.setBounds(x, y + 20, width, height);
        startGameJButton.setBounds(x, y-100, width, height);
        adminJButton.setBounds(x, y, width, height);

        width = percent(95, w);
        height = percent(18, h);
        x = (w - width) / 2;
        y = percent(35, h) - 90;
        questionNoJLabel.setBounds(x, y, width, height);


        width = percent(40, w);
        height = percent(8, h);
        x = percent(3, w);
        y = percent(55, h);
        op1OptionLabel.setBounds(x, y, width, height);

        width = percent(40, w);
        height = percent(8, h);
        x = w - percent(3, w) - width ;
        y = percent(55, h);
        op2OptionLabel.setBounds(x, y, width, height);

        width = percent(40, w);
        height = percent(8, h);
        x = percent(3, w);
        y = percent(55, h) + height + 10;
        op3OptionLabel.setBounds(x, y, width, height);


        width = percent(40, w);
        height = percent(8, h);
        x = w - percent(3, w) - width ;
        y = percent(55, h) + height + 10;
        op4OptionLabel.setBounds(x, y, width, height);
    }

    class RightAnswerThread implements Runnable
    {
        String threadName;
        Thread t;
        boolean suspendFlag;


        public RightAnswerThread(String name)
        {
            threadName = name;
            t = new Thread(this, name);
            suspendFlag = false;
            t.start();
        }

        public void run()
        {
            try {
                if(clientAns == question.Answer()) {

                  //  questionNumber++;

                    rightJLabel.setVisible(true);
                    Thread.sleep(1000);
                    rightJLabel.setVisible(false);
//                    RightAnswer();

                    if(questionNumber == 5 || questionNumber == 10) {
                        if(questionNumber == 5)
                            gameOverMessageJLabel.setText("You have completed the First Round ! Do you want to proceed for the Second Round ");
                        else
                            gameOverMessageJLabel.setText("You have completed the Second Round ! Do you want to proceed for the Third Round ");
                        HideAllComponent();
                        gameOverMessageJLabel.setVisible(true);
                        proceedJButton.setVisible(true);
                        forfeitJButton.setVisible(true);
                    }

                    if(questionNumber>=15)
                    {
                        HideAllComponent();

                        gameOverMessageJLabel.setVisible(true);
                        playAgainJButton.setVisible(true);
                        gameOverMessageJLabel.setText("Congrats ! \n You Have Completed The Game!!! \nYou Have Own The Crown Of Millioneer!!!");

                    }
                    RightAnswer();
                }


                else
                {
                    HideAllComponent();
                    gameOverMessageJLabel.setVisible(true);
                    gameOverMessageJLabel.setText("SORRY ! Wrong Answer!!!");
                    Thread.sleep(1000);
                    playAgainJButton.setVisible(true);
                    //gameOverMessageJLabel.setText("SORRY ! Wrong Answer!!!\n ");
                    //You Win Taka 0 BDT
                    if(questionNumber > 5 && questionNumber <10)
                        gameOverMessageJLabel.setText("But You Have Completed The 1st Round!!!\nYou Win Taka 10000 BDT" );
                    
                    else if(questionNumber > 10 && questionNumber <15)
                         gameOverMessageJLabel.setText("But You Have Completed The 2nd Round!!!\nYou Win Taka 320000 BDT" );
                    
                    else
                        gameOverMessageJLabel.setText("You Have Failed to Complete 1st Round!!!\nYou Got Taka 0 BDT");                    
                }
            }
            catch( InterruptedException e)
            {

            }


        }

        void mysuspend()
        {
            suspendFlag = true;
        }

        synchronized void myresume()
        {
            suspendFlag = false;
            notify();
        }
    }


    class GameStartThread implements Runnable
    {
        String threadName;
        Thread t;
        boolean suspendFlag;


        public GameStartThread(String name)
        {
            threadName = name;
            t = new Thread(this, name);
            suspendFlag = false;
            t.start();
        }

        public void run()
        {
            try {
                Thread.sleep(200);
                playerNameJLabel.setVisible(true);
                playerNameTagJLabel.setVisible(true);
                Thread.sleep(200);
                totalAmountJLabel.setVisible(true);
                earningsTagJLabel.setVisible(true);
                Thread.sleep(200);
                questionNoJLabel.setVisible(true);
                Thread.sleep(200);
                jScrollPane1.setVisible(true);
                questionjTextArea.setVisible(true);
                Thread.sleep(200);
                op1OptionLabel.setVisible(true);
                op2OptionLabel.setVisible(true);
                Thread.sleep(200);
                op3OptionLabel.setVisible(true);
                op4OptionLabel.setVisible(true);
                Thread.sleep(200);
                fiftyFiftyJButton.setVisible(true);
                mainForfeitJButton.setVisible(true);

                exitJButton.setVisible(true);
            }
            catch( InterruptedException e)
            {
                
            }

        }

        void mysuspend()
        {
            suspendFlag = true;
        }

        synchronized void myresume()
        {
            suspendFlag = false;
            notify();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adminJButton;
    private javax.swing.JLabel backImageJLabel;
    private javax.swing.JLabel earningsTagJLabel;
    private javax.swing.JButton exitJButton;
    private javax.swing.JButton fiftyFiftyJButton;
    private javax.swing.JButton forfeitJButton;
    private javax.swing.JLabel gameOverMessageJLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLayeredPane layerPanel;
    private javax.swing.JButton mainForfeitJButton;
    private my.OptionLabel op1OptionLabel;
    private my.OptionLabel op2OptionLabel;
    private my.OptionLabel op3OptionLabel;
    private my.OptionLabel op4OptionLabel;
    private javax.swing.JButton playAgainJButton;
    private javax.swing.JLabel playerNameJLabel;
    private javax.swing.JLabel playerNameTagJLabel;
    private javax.swing.JButton proceedJButton;
    private javax.swing.JLabel questionNoJLabel;
    private javax.swing.JTextArea questionjTextArea;
    private javax.swing.JLabel rightJLabel;
    private javax.swing.JButton startGameJButton;
    private javax.swing.JLabel totalAmountJLabel;
    // End of variables declaration//GEN-END:variables

}

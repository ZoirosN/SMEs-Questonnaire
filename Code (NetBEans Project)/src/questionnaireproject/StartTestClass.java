/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionnaireproject;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import java.util.Random;



public class StartTestClass {
    
    private int index = 0;
    private int size = 30;
    Random rand = new Random(); //used sto rotate the positive answers cogratulations 
    String delimiter = "~";
    private int tempIndex;
    private int yesSum = 0;
    private int blankQuestions = 0;
    
    
    
    public void testFunction() {
        JFrame testFrame = new JFrame();  //φτιαχνω το κυριο πλαισιο του 4ου παραθύρου με default BorderLayout
        testFrame.setSize(850, 700);
        testFrame.setTitle("Questionnaire");
        testFrame.setLayout(null);
      
        JPanel questPanel = new JPanel();
        JPanel btnPanel = new JPanel();
        JPanel answerPanel = new JPanel();     
        
        QuestionnaireProject.score =0; //make the score zero as a new test has started
       
        String[] questionsArray = new String[size];//Questions Array--------------------------------------------------------
        // Assign values to the array
        tempIndex = 0;
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/inputQuest.txt")))) {
            String line;
            StringBuilder sb = new StringBuilder();   
       
            // Read the entire file content
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            // Split the content based on the delimiter
            String[] parts = sb.toString().split(delimiter);
            for (String part : parts) {
                if (tempIndex < size) {
                    questionsArray[tempIndex] = part.trim();  // Remove leading/trailing whitespace
                    tempIndex++;
                } else {
                    System.out.println("Array is full.");
                    break;
                }
            }            
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
         
        /*for (int i = 0; i < size; i++) {
            System.out.println("Array[" + i + "]: " + questionsArray[i]);
        }*/
        
        String[] negAnswersArray = new String[size];//Suggestions Array----------------------------------------------------------------------
        // Assign values to the array
        tempIndex = 0;
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/inputSuggest.txt")))) {
            String line;
            StringBuilder sb = new StringBuilder();   
       
            // Read the entire file content
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            // Split the content based on the delimiter
            String[] parts = sb.toString().split(delimiter);
            for (String part : parts) {
                if (tempIndex < size) {
                    negAnswersArray[tempIndex] = part.trim();  // Remove leading/trailing whitespace
                    tempIndex++;
                } else {
                    System.out.println("Array is full.");
                    break;
                }
            }            
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
         
        /*for (int i = 0; i < size; i++) {
            System.out.println("Array[" + i + "]: " + questionsArray[i]);
        }*/
        
        String[] posAnswersArray = new String[5];
        // Assign values to the array
        posAnswersArray[0] = "Very well!";
        posAnswersArray[1] = "Congratulations!";
        posAnswersArray[2] = "Excellent!";
        posAnswersArray[3] = "That was welcomly expected!";
        posAnswersArray[4] = "Keep up the good work!";
        
        int[] resultArray = new int[size]; //if 1 is yes, if 2 is no. 0 means not answered
        // Assign zeroes to the array
        for(int j=0; j<size; j++ ){
            resultArray[j]=0;
        }
        
        
        JLabel questLabel = new JLabel("<html>" + questionsArray[index].replaceAll("\n", "<br>") + "</html>", SwingConstants.CENTER);
        //questLabel.setVerticalAlignment(SwingConstants.TOP);
        questLabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "")); //περιγραμμα με τιτλο
        JLabel answerLabel = new JLabel("", SwingConstants.CENTER);
        answerLabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "")); //περιγραμμα με τιτλο
        
        JButton btnPrev = new JButton("Prev"); //δημιουργω buttons
        btnPrev.setBackground(Color.LIGHT_GRAY);
        JButton btnNext = new JButton("Next"); //δημιουργω buttons
        btnNext.setBackground(Color.LIGHT_GRAY);
        JButton btnYES = new JButton("YES"); //δημιουργω buttons
        btnYES.setBackground(Color.GREEN);
        JButton btnNO = new JButton("NO"); //δημιουργω buttons
        btnNO.setBackground(Color.RED);
        
       
        //NewBtn-------------------------------------------------------------------------
        btnPrev.addActionListener(new ActionListener() { //listener που όταν πατιεται το κουμπί ...
        //...φτιάχνει στιγμότυπο της κλασής NewTicketClass και καλεί μια μέθοδό της(2ο παραθυρο)
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (index == questionsArray.length - 1) {   //αλλαγή απο Finish σε Next σε περιπτωση
                    btnNext.setText("Next");                //επιστροφής απο την τελευταία ερώτηση
                    btnNext.setBackground(Color.LIGHT_GRAY);
                } 
                if (index != 0) {
                    index--;
                    questLabel.setText("<html>" + questionsArray[index].replaceAll("\n", "<br>") + "</html>"); //εμφανισε την επόμενη ερώτηση
                    if(resultArray[index]==0){          //αλλαγή χρωμάτων για μη απαντημένη ερώτηση
                            btnYES.setBackground(Color.GREEN);
                            btnNO.setBackground(Color.RED);
                            answerLabel.setText("");       //κανε κενο το πλαίσιο απόκρισης 
                        }
                        if(resultArray[index]==1){          //αλλαγή χρωμάτων για θετικά απαντημένη ερώωτηση
                            btnYES.setBackground(Color.BLUE);
                            btnNO.setBackground(Color.WHITE);
                            answerLabel.setText(posAnswersArray[rand.nextInt(4) + 1]);  //εμφάνισε θετική απόκριση
                        }
                        if(resultArray[index]==2){          //αλλαγή χρωμάτων για αρνητικα απαντημένη ερώτηση
                            btnYES.setBackground(Color.WHITE);
                            btnNO.setBackground(Color.BLUE);
                            answerLabel.setText("<html>" + negAnswersArray[index].replaceAll("\n", "<br>") + "</html>"); //εμφάνισε την προτεινόμενη λυση ως απόκριση
                        }
                } 
            }
        });
        
        //NewBtn-------------------------------------------------------------------------
        btnNext.addActionListener(new ActionListener() { //listener που όταν πατιεται το κουμπί ...
        //...φτιάχνει στιγμότυπο της κλασής NewTicketClass και καλεί μια μέθοδό της(2ο παραθυρο)
            @Override
            public void actionPerformed(ActionEvent ae) {
               if (index < questionsArray.length - 1) {
                    index++;
                    questLabel.setText("<html>" + questionsArray[index].replaceAll("\n", "<br>") + "</html>"); //εμφανισε την επόμενη ερώτηση 
                        if(resultArray[index]==0){          //αλλαγή χρωμάτων και απόκρισης σε  μη απαντημένη ερώτηση
                            btnYES.setBackground(Color.GREEN);
                            btnNO.setBackground(Color.RED);
                            answerLabel.setText("");       //κανε κενο το πλαίσιο απόκρισης 
                        }
                        if(resultArray[index]==1){          //αλλαγή χρωμάτων για θετικά απαντημένη ερώωτηση
                            btnYES.setBackground(Color.BLUE);
                            btnNO.setBackground(Color.WHITE);
                            answerLabel.setText(posAnswersArray[rand.nextInt(4) + 1]);  //εμφάνισε θετική απόκριση
                        }
                        if(resultArray[index]==2){          //αλλαγή χρωμάτων για αρνητικα απαντημένη ερώτηση
                            btnYES.setBackground(Color.WHITE);
                            btnNO.setBackground(Color.BLUE);
                            answerLabel.setText("<html>" + negAnswersArray[index].replaceAll("\n", "<br>") + "</html>"); //εμφάνισε την προτεινόμενη λυση ως απόκριση
                        }                      
                                        
                    if (index == questionsArray.length - 1) { //αλλαγη χρωματος και κειμενου στο κουμπι 
                        btnNext.setText("Finish");            //όταν πρόκειται για την τελευταία ερώτηση
                        btnNext.setBackground(Color.ORANGE);
                    }                    
                } 
                else {
                    
                    for (int k = 0; k < size; k++) {
                        if(resultArray[k]==1){
                            yesSum++;
                        }
                        if(resultArray[k]==0){
                            blankQuestions++;
                        }
                    }
                    if(blankQuestions !=0){
                        int i = JOptionPane.showConfirmDialog(null, "You have left some questions unanswered! "
                                + "Reasure that in every question there is a selected (blue) answer. Do you want to abandon test?");
                        if (i == JOptionPane.YES_OPTION) { //αν πατησει ο χρηστης yes κλεισε το προγραμμα
                        testFrame.dispose();
                        }
                        blankQuestions=0;
                    }
                    else{
                    QuestionnaireProject.score = yesSum*10/size; //υπολογισμός αποτελεσμάτων θετικα δια πλήθος * 10 για να παει απο ποσοστο σε κλιμα 1-10
                    System.out.println("Possitive=" + yesSum);
                    GuiHandlerQuest.showResult();
                    testFrame.dispose(); // Close the frame
                    }
                } 
            }
        });        
        //NewBtn-------------------------------------------------------------------------
        btnYES.addActionListener(new ActionListener() { //listener που όταν πατιεται το κουμπί ...
        //...φτιάχνει στιγμότυπο της κλασής NewTicketClass και καλεί μια μέθοδό της(2ο παραθυρο)
            @Override
            public void actionPerformed(ActionEvent ae) {
                resultArray[index]=1;
                btnYES.setBackground(Color.BLUE);
                btnNO.setBackground(Color.WHITE);
                answerLabel.setText(posAnswersArray[rand.nextInt(4) + 1]);
            }
        });
        
        //NewBtn-------------------------------------------------------------------------
        btnNO.addActionListener(new ActionListener() { //listener που όταν πατιεται το κουμπί ...
        //...φτιάχνει στιγμότυπο της κλασής NewTicketClass και καλεί μια μέθοδό της(2ο παραθυρο)
            @Override
            public void actionPerformed(ActionEvent ae) {
                resultArray[index]=2;
                btnYES.setBackground(Color.WHITE);
                btnNO.setBackground(Color.BLUE);
                answerLabel.setText("<html>" + negAnswersArray[index].replaceAll("\n", "<br>") + "</html>");
                
            }
        });  
        questPanel.add(questLabel); //προσθέτω τα panels με τα labels στο frame
        answerPanel.add(answerLabel);
        
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(btnPrev);
        btnPanel.add(btnYES);
        btnPanel.add(btnNO);
        btnPanel.add(btnNext);
                
        testFrame.setLayout(new GridLayout(3, 1));
        testFrame.add(questPanel); 
        testFrame.add(btnPanel); //προσθετω το panel στο frame
        testFrame.add(answerPanel); //προσθετω το panel στο frame

                
        testFrame.setVisible(true);
        
        testFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//αν δεν βαλουμε αυτό το παραθυρο κλεινει ακομα
        //και αν πατησουμε cancel ή no για το κλείσιμο.
        testFrame.setVisible(true);
        
               
        testFrame.addWindowListener(new WindowAdapter() {//κλεισιμο απο κουμπι παραθύρου
        //μεσα στα αγκιστρα δεξι κλικ-insert code-override methods ωστε να διαλεξω μια μεθοδο
            @Override //υλοποιηση για να κλεινει το παραθυρο με ερωτημα καλωντας την παλι την exitApp
            public void windowClosing(WindowEvent we) {
                int i = JOptionPane.showConfirmDialog(null, "Please answer all the questions and press \"Finish\"! If you close the window now, your answers will not be saved! Procceed anyway?");
                if (i == JOptionPane.YES_OPTION) { //αν πατησει ο χρηστης yes κλεισε το προγραμμα
                testFrame.dispose();
        }
            }

        });        
            
    }
    
    
}
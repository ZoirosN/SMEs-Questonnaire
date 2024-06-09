
package questionnaireproject;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class GuiHandlerQuest {

    private static JLabel resultLabel = new JLabel("");
    private static JPanel centralPanel = new JPanel();   //κανω πανελ που θα μπει στο center του main Frame 
    
    public void prepareUI() {
        
    JFrame mainFrame = new JFrame();  //φτιαχνω το κεντρικό παράθυρο εφαρμογής με default BorderLayout
    mainFrame.setSize(850, 700); //θέτω αρχικό μέγεθος
    mainFrame.setTitle("Cybersecurity Readyness and Compliance Questionnaire");//και τίτλο παραθύρου

    JButton btnStartTest = new JButton("Start the test"); //δημιουργω buttons
    JButton btnHelp = new JButton("Help");
    JButton btnExit = new JButton("Exit");
    
    
    JPanel mainPanel = new JPanel();   //καν ω πανελ που θα μπει στο  north του main Frame 
    mainPanel.add(btnStartTest); //και του προσθετω τα buttons
    mainPanel.add(btnHelp);
    mainPanel.add(btnExit);
         
    centralPanel.add(resultLabel);
    
    
    mainFrame.add(mainPanel,BorderLayout.NORTH); //προσθετω το panel στο frame  
    mainFrame.add(centralPanel,BorderLayout.CENTER); //προσθετω το panel στο frame 
    
   
        
     //StartTest-------------------------------------------------------------------------
        btnStartTest.addActionListener(new ActionListener() { //listener που όταν πατιεται το κουμπί ...
        //...φτιάχνει στιγμότυπο της κλασής StartTour και καλεί μια μέθοδό της(2ο παραθυρο)
            @Override
            public void actionPerformed(ActionEvent ae) {
                StartTestClass test = new StartTestClass(); //εδω εχω φτιαξει κλαση που δεν κληρονομει αλλα φτιάχνει το Frame
                test.testFunction();
            }
        });
        
     //Help-------------------------------------------------------------------------
        btnHelp.addActionListener(new ActionListener() {//listener που όταν πατιεται το κουμπί ...
        //...φτιάχνει στιγμότυπο της κλασής HelpClass και καλεί μια μέθοδό της(4ο παράθυρο)
            @Override
            public void actionPerformed(ActionEvent ae) {
                HelpClassQuest help = new HelpClassQuest(); //εδω εχω φτιαξει κλαση που δεν κληρονομει αλλα φτιάχνει το Frame
                help.helpFunction();
            }
        });
        //Exit-------------------------------------------------------------------------
        btnExit.addActionListener(new ActionListener() { //κλεισιμο με κουμπί εφαρμογής
            @Override //οταν πατηθει το κουμπι exit καλεσε την συναρτηση exitApp 
            public void actionPerformed(ActionEvent ae) {
                exitApp();
            }
        });
        mainFrame.addWindowListener(new WindowAdapter() {//κλεισιμο απο κουμπι παραθύρου
        //μεσα στα αγκιστρα δεξι κλικ-insert code-override methods ωστε να διαλεξω μια μεθοδο
            @Override //υλοποιηση για να κλεινει το παραθυρο με ερωτημα καλωντας την παλι την exitApp
            public void windowClosing(WindowEvent we) {
                exitApp();
            }

        });
        
        
        //Image panel 
         try (InputStream imageStream = getClass().getResourceAsStream("/cyber.png")) {
            if (imageStream == null) {
                throw new IOException("Resource not found: /cyber.png");
            }
            BufferedImage myCartBcrnd = ImageIO.read(imageStream);
            resultLabel.setIcon(new ImageIcon(myCartBcrnd));
            centralPanel.add(resultLabel);
        } catch (IOException ex) { //έλεγχος αν υπάρχει το screenshot
            System.out.println(ex.getMessage()); //τυπώνει το μήνυμα σφάλματος
        }
        //-----------------------------------------------------------------------------------------
        
        
        
    mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);//αν δεν βαλουμε αυτό το παραθυρο κλεινει ακομα
    //και αν πατησουμε cancel ή no για το κλείσιμο.
    mainFrame.setVisible(true);//κάνε το παράθυρο εμφανές
        
    } 

    private void exitApp() {
        //εφαρμογή για προειδοποίηση κλεισίματος με ανοιγμα διαλόγου
        int i = JOptionPane.showConfirmDialog(null, "Do you realy want to exit?");
        if (i == JOptionPane.YES_OPTION) { //αν πατησει ο χρηστης yes κλεισε το προγραμμα
            System.exit(0);
        }
    }
    
    public static void showResult() {
        //εφαρμογή για εμφάνιση εικόνας αποτελέσματος
        BufferedImage myCartBcrnd;  //πρόσθεση screenshot 
        try {
             if (QuestionnaireProject.score >= 0 && QuestionnaireProject.score <= 2) {
                try (InputStream imageStream = GuiHandlerQuest.class.getResourceAsStream("/image0.png")) {
                    if (imageStream == null) {
                        throw new IOException("Resource not found: /image0.png");
                    }
                    myCartBcrnd = ImageIO.read(imageStream);
                }
                resultLabel.setIcon(new ImageIcon(myCartBcrnd));
            }
            if (QuestionnaireProject.score > 2 && QuestionnaireProject.score <= 4) {
                try (InputStream imageStream = GuiHandlerQuest.class.getResourceAsStream("/image1.png")) {
                    if (imageStream == null) {
                        throw new IOException("Resource not found: /image1.png");
                    }
                    myCartBcrnd = ImageIO.read(imageStream);
                }
                resultLabel.setIcon(new ImageIcon(myCartBcrnd));
            }
            if (QuestionnaireProject.score > 4 && QuestionnaireProject.score <= 6) {
                try (InputStream imageStream = GuiHandlerQuest.class.getResourceAsStream("/image2.png")) {
                    if (imageStream == null) {
                        throw new IOException("Resource not found: /image2.png");
                    }
                    myCartBcrnd = ImageIO.read(imageStream);
                }
                resultLabel.setIcon(new ImageIcon(myCartBcrnd));
            }
            if (QuestionnaireProject.score > 6 && QuestionnaireProject.score <= 8) {
                try (InputStream imageStream = GuiHandlerQuest.class.getResourceAsStream("/image3.png")) {
                    if (imageStream == null) {
                        throw new IOException("Resource not found: /image3.png");
                    }
                    myCartBcrnd = ImageIO.read(imageStream);
                }
                resultLabel.setIcon(new ImageIcon(myCartBcrnd));
            }
            if (QuestionnaireProject.score > 8 && QuestionnaireProject.score <= 10) {
                try (InputStream imageStream = GuiHandlerQuest.class.getResourceAsStream("/image4.png")) {
                    if (imageStream == null) {
                        throw new IOException("Resource not found: /image4.png");
                    }
                    myCartBcrnd = ImageIO.read(imageStream);
                }
                resultLabel.setIcon(new ImageIcon(myCartBcrnd));
            }

                        
            centralPanel.add(resultLabel);
        } catch (IOException ex) { //έλεγχος αν υπάρχει το screenshot
            System.out.println(ex.getMessage()); //τυπώνει το μήνυμα σφαλματος
        }
        System.out.println("RESULT=" + QuestionnaireProject.score);
    }
    
}

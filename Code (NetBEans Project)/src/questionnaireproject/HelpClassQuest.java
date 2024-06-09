
package questionnaireproject;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HelpClassQuest {

    public void helpFunction() {
        JFrame helpFrame = new JFrame();  //φτιαχνω το κυριο πλαισιο του παραθύρου με default BorderLayout
        helpFrame.setSize(765, 300);
        helpFrame.setTitle("Help");

        JPanel helpPanel = new JPanel();
        JLabel helpLabel = new JLabel("<html> The questionnaire is very simple. Just answer the questions by clicking<br/>"
                + "\"YES\" or \"NO\" buttons. If those buttons are GREEN and RED the question "
                + "<br/>needs to be answered. If one button is BLUE and the other is WHITE, then "
                + "<br/>the BLUE one is the selected answer. "
                + "<br/> You can go back and forth as many times as you like. Though you should "
                + "<br/>answer all 30 questions. If you exit the test prior to it's completion your "
                + "<br/>answers will be lost!</html>", SwingConstants.CENTER);
        
        helpLabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "")); //περιγραμμα με τιτλο
       
        helpPanel.setLayout(new BorderLayout());
        helpPanel.add(helpLabel, BorderLayout.CENTER);
        helpFrame.add(helpPanel); 
        helpFrame.setVisible(true);
    }
}



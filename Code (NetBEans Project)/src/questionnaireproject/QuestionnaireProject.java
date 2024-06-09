/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package questionnaireproject;

/**
 *
 * @author spect
 */
public class QuestionnaireProject {
    
    
    public static int score = 0;

 public static void main(String[] args) { //μόνη της η main ώστε να είναι "ελαφριά"
        GuiHandlerQuest handler = new GuiHandlerQuest(); //δημιουργει ένα στιγμιότυπο της κλασης 
        handler.prepareUI(); //καλεί μια μέθοδό τη
 }
    
}

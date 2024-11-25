package CSP;
//this one has comments

import javax.swing.*;
import java.util.*;

public class MarvelCharacterQuiz {

    private static int[] scores;

    private static void processQuiz(int numberOfQuestions, List<String> questions, String[] characters, int[] scores) {
        Random random = new Random();
        Collections.shuffle(questions, random); // Shuffle the list of questions (Sequencing)

        for (int i = 0; i < numberOfQuestions; i++) { // Iteration
            int answer = JOptionPane.showOptionDialog(null, questions.get(i),
                    "Question " + (i + 1),
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    new Object[]{"4", "3", "2", "1"}, 1);

            if (answer >= 0 && answer < 4) { // Selection
                scores[answer]++;
            } else {
                i--; // repeat the question if the user closes the dialog
            }
        }

        int maxIndex = 0;
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > scores[maxIndex]) {
                maxIndex = i;
            }
        }

        JOptionPane.showMessageDialog(null, "Based on your responses, you are most like: " + characters[maxIndex]);
    }

    public static void main(String[] args) {
        
        String[] characters = {"Iron Man", "Hulk", "Thor", "Black Widow", "Captain America", "Scarlet Witch", "Doctor Strange"};

        // Increase the size of the scores array to match the number of characters
        scores = new int[characters.length];

        List<String> questions = Arrays.asList(
                "What is your preferred method of travel? (1) Flying (2) Running (3) Teleporting (4) Driving \n",
                "Choose a color that resonates with you the most: (1) Red (2) Green (3) Blue (4) Black\n",
                "Which trait best describes you? (1) Leadership (2) Intelligence (3) Strength (4) Stealth\n",
                "Pick an item: (1) Shield (2) Hammer (3) Suit (4) Bow and Arrow\n",
                "What is your ideal weekend activity? (1) Inventing something (2) Hitting the gym (3) Reading (4) Practicing archery\n",
                "Who would be your archenemy? (1) Ultron, (2) Abomination, (3) Loki, (4) Taskmaster\n",
                "Who would be your partner? (1) War Machine, (2) She-Hulk, (3) Falcon, (4) Hawkeye\n",
                "What is your favorite Infinity Stone? (1) Power Stone, (2) Time Stone, (3) Space Stone, (4) Mind Stone\n"
        );

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                int result = JOptionPane.showConfirmDialog(null, "Welcome to the Marvel Character Quiz! Would you like to start the quiz?",
                        "Marvel Character Quiz", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    do {
                        scores = new int[characters.length]; // Reset scores
                        processQuiz(4, questions, characters, scores); // Function call

                        result = JOptionPane.showConfirmDialog(null, "Would you like to retake the quiz?",
                                "Marvel Character Quiz", JOptionPane.YES_NO_OPTION);
                    } while (result == JOptionPane.YES_OPTION);
                } else {

                    System.exit(0);

                }

            }
        });
    }
}

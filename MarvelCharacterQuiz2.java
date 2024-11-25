package CSP; //since I used VCS and put the code in a folder, this line is needed. 

/* Citations

1. Collections class
Original Source: GeeksForGeeks
URLs to Original: 
    https://www.geeksforgeeks.org/collections-class-in-java/
First Uses:  Line 47

2. Java Swing References
Original Source: GeeksForGeeks, Orcale
URLs to Original: 
    https://www.geeksforgeeks.org/introduction-to-java-swing/
    https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/package-summary.html
First Uses:  Line 32

*/

import javax.swing.*; //fully import Java swing
import java.util.*; //import all java utilities like ArrayList and Collections

public class MarvelCharacterQuiz2 {

    public static void main(String[] args) {
        runQuiz(); //call to method to run quiz which calls upon the student developed function
    }

    private static void runQuiz() { 
        String[] characters = {"Iron Man", "Hulk", "Thor", "Black Widow", "Captain America", "Scarlet Witch", "Doctor Strange"}; 
        List<CharacterInfo> characterInfoList = createCharacterInfoList(characters); 

        int result = JOptionPane.showConfirmDialog(null, "Welcome to the Marvel Character Quiz! Would you like to play?",
                "Marvel Character Quiz", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            do {
                processQuiz(5, characterInfoList);
                result = JOptionPane.showConfirmDialog(null, "Would you like to play again?",
                        "Marvel Character Quiz", JOptionPane.YES_NO_OPTION);
            } while (result == JOptionPane.YES_OPTION);
        } else {
            System.exit(0);
        }
    }

    private static void processQuiz(int numberOfQuestions, List<CharacterInfo> characterInfoList) { 
        Random random = new Random();
        Collections.shuffle(characterInfoList, random); 
        
      
        for (CharacterInfo characterInfo : characterInfoList) { 
            characterInfo.resetScore();
        }
    
        for (int i = 0; i < numberOfQuestions; i++) { 
            CharacterInfo characterInfo = characterInfoList.get(i);
            int answer = JOptionPane.showOptionDialog(null, characterInfo.getQuestion(),
                    "Question " + (i + 1), 
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    characterInfo.getAnswers().toArray(new String[0]), 1);
    
            if (answer >= 0 && answer < characterInfo.getAnswers().size()) { 
                characterInfoList.get(answer).increaseScore();
            } else { 
                i--;
            }
        }
    
        CharacterInfo winner = getCharacterWithMaxScore(characterInfoList);
        JOptionPane.showMessageDialog(null, "Based on your responses, you are most like: " + winner.getName());
    }
    
    

    private static CharacterInfo getCharacterWithMaxScore(List<CharacterInfo> characterInfoList) { //method to determine the character than the quiz will output
        int maxScore = Integer.MIN_VALUE;
        CharacterInfo winner = null;
        for (CharacterInfo characterInfo : characterInfoList) {
            if (characterInfo.getScore() > maxScore) {
                maxScore = characterInfo.getScore();
                winner = characterInfo;
            }
        }
        return winner;
    }

    private static List<CharacterInfo> createCharacterInfoList(String[] characters) { //make a character info list so the student developed function can use it
        //method takes in a character array and uses the CharacterInfo class to have all the arrays in one Array List
        List<CharacterInfo> characterInfoList = new ArrayList<>(); //Array List
        for (int i = 0; i < characters.length; i++) {
            
            List<String> questions = Arrays.asList( //array list
                "What is your preferred method of travel? (1) Flying (2) Running (3) Teleporting (4) Driving \n",
                "Choose a color that resonates with you the most: (1) Red (2) Green (3) Blue (4) Black\n",
                "Which trait best describes you? (1) Leadership (2) Intelligence (3) Strength (4) Stealth\n",
                "What is your ideal weekend activity? (1) Inventing something (2) Hitting the gym (3) Reading (4) Practicing archery\n",
                "Who would be your archenemy? (1) Ultron, (2) Abomination, (3) Loki, (4) Taskmaster\n",
                "Who would be your partner? (1) War Machine, (2) She-Hulk, (3) Falcon, (4) Hawkeye\n",
                "What is your favorite Infinity Stone? (1) Power Stone, (2) Time Stone, (3) Space Stone, (4) Mind Stone\n",
                "Which weapon would you take? (1) Stormbreaker, (2) The Ten Rings, (3) Captain America's Shield, (4) Widow's Bite\n"
        );
            List<String> answers = Arrays.asList("4", "3", "2", "1"); //list
            characterInfoList.add(new CharacterInfo(characters[i], questions.get(i), answers)); //add the characters, questions, and answer choices to the CharacterInfo list
        }
        return characterInfoList;
    }
}

class CharacterInfo {  //class to make a list so questions answers and characters can be added to it 
    private String name;
    private String question;
    private List<String> answers;
    private int score;

    public CharacterInfo(String name, String question, List<String> answers) {
        this.name = name;
        this.question = question;
        this.answers = answers;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }
    
    public void resetScore() {
        this.score = 0;
    }
}







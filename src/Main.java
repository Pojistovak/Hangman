import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String wordToGuess;
        String hiddenWord;

        final String[] words = {"java", "skillman", "python", "test"};
        final int MAX_INC0RRECT_GUESSES = 7;
        final Scanner scanner = new Scanner(System.in);
        int incorrectGuesses = 0;

        wordToGuess = selectWord(words);
        hiddenWord = hiddentoWord(wordToGuess);

        System.out.println("Welcom to Hangman!");
        System.out.println(hiddenWord);

        while (incorrectGuesses < MAX_INC0RRECT_GUESSES && hiddenWord.contains("_")) {
            System.out.println("Enter a letter: ");
            char guess = scanLetter(scanner);

            if (wordToGuess.contains(String.valueOf(guess))){
                hiddenWord = revealLetters(wordToGuess, hiddenWord, guess);
                System.out.println("spravny znak, aktualizovane slovo " + hiddenWord);
            } else {
                incorrectGuesses++;
                System.out.println("Spatne pismenko mate " + (MAX_INC0RRECT_GUESSES - incorrectGuesses) + " zbývajících pokusů");
            }
        }

        if (!hiddenWord.contains("_")){
            System.out.println("Super, vyhral jsi");
        } else {
            System.out.println("Bohužel nepovedlo se");
        }
    }


    public static String revealLetters(String wordToGuess, String hiddenWord, char letter){
        char[] hiddenWordChars = hiddenWord.toCharArray();
        for (int i = 0; i < wordToGuess.length(); i++){
            if (wordToGuess.charAt(i) == letter) {
                hiddenWordChars[i] = letter;
            }
        }
        return String.valueOf(hiddenWordChars);
    }
    public static char scanLetter(Scanner scanner){
        //return scanner.next().charAt(0);
        char guess;
        while (true) {
            try {
                String line = scanner.nextLine();
                if (line.length() != 1){
                    throw new Exception("Invalid input: Zadali jste více než jeden znak");
                }

                guess = line.charAt(0);
                if (!Character.isLetter(guess)){
                    throw new Exception("Invalid input: nezadali jste pismenko");
                }
                break;
            } catch (Exception e){
                System.out.println("Invalid input: " + e.getMessage());
            }
        }

        return guess;
    }

    public static String selectWord(String[] words){
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    public static String hiddentoWord(String randomWord){
        Random random = new Random();
        return "_".repeat(randomWord.length());
    }
}
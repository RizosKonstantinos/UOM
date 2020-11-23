import javax.swing.*;

import static sun.util.calendar.CalendarUtils.mod;

public class VigenereCipherCryptanalysis {

    public static void main(String[] args) {

        // The alphabet that is goining to be used
        int[] alphabet = new int[26];
        String alphabet_string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Encoding of the alphabet based on Caesar algorithm
        encoding(alphabet_string, alphabet);


        // Pop up message that prompts the user to type the original plaintext
        String plainText_string;
        do {
            plainText_string = JOptionPane.showInputDialog("\n* Note: You can only use characters in range a - zA - Z     " +
                    "\n              Do NOT use space between words! " +
                    "\n\nEnter the plain text:").toUpperCase();
            if(plainText_string.matches("^[a-zA-Z]*$") && !plainText_string.isEmpty()){
                JOptionPane.showMessageDialog(null,"Plain text matches criteria.");
            }
            else{
                JOptionPane.showMessageDialog(null,"Please enter a valid input containing characters in range a - zA - Z ");
            }
        }while(!(plainText_string.matches("^[a-zA-Z]*$") && !plainText_string.isEmpty()));

        // Pop up message that prompts the user to type the ciphertext
        String cipherText_string;
        do {
            cipherText_string=JOptionPane.showInputDialog("\n* Note: You can only use characters in range a - zA - Z     " +
                    "\n              Do NOT use space between words! " +
                    "\n\nEnter the cipher text:").toUpperCase();
            if(cipherText_string.matches("^[a-zA-Z]*$") && !cipherText_string.isEmpty()){
                JOptionPane.showMessageDialog(null,"Cipher text matches criteria.");
            }
            else{
                JOptionPane.showMessageDialog(null,"Please enter a valid input containing characters in range a - zA - Z ");
            }
        }while(!(cipherText_string.matches("^[a-zA-Z]*$")&& !cipherText_string.isEmpty()));

        // Pop up message that prompts the user to type the key length
        String keyLength_string;
        do {
            keyLength_string =JOptionPane.showInputDialog("\n* Note: You can only use digits      " +
                    "\n\nEnter the key length: ");
            if(keyLength_string.matches("^[0-9]*$") && !keyLength_string.isEmpty()){
                JOptionPane.showMessageDialog(null,"Key length matches criteria.");
            }
            else{
                JOptionPane.showMessageDialog(null,"Please enter a valid input containing only digits ");
            }
        }while(!(keyLength_string.matches("^[0-9]*$") && !keyLength_string.isEmpty()));

        int keyLength = Integer.parseInt(keyLength_string);

        int[] cipherText = new int[keyLength];
        int[] plainText = new int[keyLength];

        // Encoding of the original plaintext
        encoding(plainText_string, plainText);

        // Encoding of the ciphertext
        encoding(cipherText_string, cipherText);

        // Key creation based on user's input
        int[] key = new int[keyLength];

        // Key initialization
        for(int i=0; i<keyLength; i++){
            key[i]=0;
        }

        int tries = 0; // Variable for the number of tries until key is located

        // Begins to count the execution time of the program
        long startTime = System.nanoTime();

         /*   Here is the process of finding the key.
              The method returns the number of tries until the key is located
         */
        tries = Vigenere_analysis(key, alphabet, plainText, cipherText, tries);

        // Convert the key to a String for its display to the user
        StringBuilder builder = new StringBuilder();
        for (int j : key) {
            builder.append((char) (j + 65));
        }
        String keyString = builder.toString();

        // It stops counting the execution time of the program
        long endTime = System.nanoTime();

        // Calculation of the total execution time of the program in milliseconds
        long duration = (endTime - startTime) / 1000000;

        // Display a message to the user with the results of the program
        JOptionPane.showMessageDialog(null, "The key is: " + keyString + "\n\nDuration: " + duration + " milliseconds" + "\nNumber of tries: " + tries);

        System.exit(0);
    }

    // Method for encoding according to Caesar algorithm
    public static void encoding(String text, int[] table) {

        for (int i = 0; i < table.length; i++) {
            table[i] = text.charAt(i) - 65;
        }
    }

    // Method for the analysis of the Vigenere algorithm using brute-force
    public static int Vigenere_analysis(int[] keyArray, int[] alphabetArray, int[] plainTextArray, int[] cipherTextArray, int counter) {
        for (int i = 0; i < keyArray.length; i++) {
            for (int k : alphabetArray) {
                counter++;
                if (cipherTextArray[i] == mod(plainTextArray[i] + k, alphabetArray.length)) {
                    keyArray[i]= k;
                    break;
                }
            }
        }
        return counter;
    }
}

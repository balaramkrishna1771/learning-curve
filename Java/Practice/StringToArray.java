import java.util.*;

class StringMethods {

    /**
     * Splits the given input string into individual words, considering whitespace
     * characters as delimiters.
     * 
     * @param inputString The string to be divided into words.
     */
    void divideString(String inputString) {
        // Split the input string into words using regular expression for whitespace
        // (\s+)
        String[] wordsOfString = inputString.split("\\s+");

        System.out.println("Words in the input string: " + Arrays.toString(wordsOfString));

        // Create a HashMap to store words as keys and their occurrences as lists of
        // strings
        HashMap<String, List<String>> mappedWords = new HashMap<>();

        // Iterate through each word in the split string
        for (String word : wordsOfString) {
            // Check if the word already exists as a key in the map
            if (mappedWords.containsKey(word)) {
                // If it does, retrieve the corresponding list and add the current word
                // (occurrence)
                mappedWords.get(word).add(word);
            } else {
                // If it doesn't exist, create a new list for that word and add the current word
                // (first occurrence)
                mappedWords.put(word, new ArrayList<>(Collections.singletonList(word))); // Use
                                                                                         // Collections.singletonList
                                                                                         // for efficiency
            }
        }

        // Print the final map, showing each word and its occurrences
        System.out.println("Occurrences of words in the string:");
        for (Map.Entry<String, List<String>> entry : mappedWords.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

public class StringToArray {

    public static void main(String[] args) {
        // Uncomment the following line to take input from the user
        // Scanner scan = new Scanner(System.in);
        // String inpString = scan.nextLine();

        // Hardcoded example string for demonstration
        String inpString = "the door is too far too close door";

        StringMethods stringMethods = new StringMethods();
        stringMethods.divideString(inpString);

        // scan.close(); // Close the scanner if user input is used
    }
}

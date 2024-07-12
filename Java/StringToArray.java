import java.util.*;

class StringMethods{
    void devideString(String inputString){
        String[] wordsOfString = inputString.split("\\s+");
        System.out.println(Arrays.toString(wordsOfString));
        HashMap<String , List<String>> mappedWords = new HashMap<>();
        for(String word : wordsOfString){
            if(mappedWords.containsKey(word)){
                mappedWords.get(word).add(word);
            }
            else {
                mappedWords.put(word, new ArrayList<>());
            }
        }
        for(String eachWord : mappedWords.keySet()){
            mappedWords.get(eachWord).add(eachWord);
        }
        for(List<String> eachMap: mappedWords.values()){
            System.out.println(eachMap);
        }
    }
}


public class StringToArray {
    public static void main(String[] args){
        // Scanner scan = new Scanner(System.in);
        // String inpString = scan.nextLine();
        // String inpString = "the door is too far too close door";
        StringMethods stringMethods = new StringMethods();
        stringMethods.devideString(inpString);
        // scan.close();
    }
}


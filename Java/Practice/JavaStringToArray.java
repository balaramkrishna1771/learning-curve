import java.util.*;

public class JavaStringToArray {
    public static void main(String[] args) {
        String inpString = "the name of the name is name itself";
        Map<String, List<String>> result = new HashMap<>();

        List<String> splitString = Arrays.asList(inpString.split("\\s+"));

        for (String eachString : splitString) {
            // System.out.println(result.get(eachString).add(eachString));
            if (result.containsKey(eachString)) {
                result.get(eachString).add(eachString);
            } else {
                result.put(eachString, new ArrayList<>(Arrays.asList(eachString)));
            }
        }
        System.out.println(result.values());
    }
}

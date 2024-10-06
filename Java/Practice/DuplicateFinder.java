import java.util.HashMap;
import java.util.Map;

public class DuplicateFinder {
    public boolean duplicateCatcher(int[] inpArray) {
        Map<Integer, Integer> helperMap = new HashMap<>();

        for (int i = 0; i < inpArray.length; i++) {
            if (helperMap.containsKey(inpArray[i])) {
                return true;
            }
            helperMap.put(inpArray[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] inpArray = { 1, 2, 4, 6 };

        DuplicateFinder duplicateFinder = new DuplicateFinder();
        System.out.println(duplicateFinder.duplicateCatcher(inpArray));
    }

}

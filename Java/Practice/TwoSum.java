import java.util.HashMap;
import java.util.Map;

class FindTwoIndices {
    public int[] findIndices(int[] inpArray, int target) {
        Map<Integer, Integer> helperMap = new HashMap<>();

        for (int i = 0; i < inpArray.length; i++) {
            int carry = target - inpArray[i];
            if (helperMap.containsKey(carry)) {
                return new int[] { helperMap.get(carry), i };
            }
            helperMap.put(inpArray[i], i);
        }
        return new int[] { -1, -1 };
    }
}

public class TwoSum {
    public static void main(String[] args) {
        int[] inpArray = { 2, 3, 4, 2, 1, 4, 5, 6 };
        int target = 6;
        FindTwoIndices classObj = new FindTwoIndices();

        for (int i : classObj.findIndices(inpArray, target)) {
            System.out.println(inpArray[i]);
        }

    }
}

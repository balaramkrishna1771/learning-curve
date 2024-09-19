import java.util.*;

class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> elementCountMapping = new HashMap<>();
        int temp = 0, result = 0;
        for (int num : nums) {
            if (elementCountMapping.containsKey(num)) {
                temp = elementCountMapping.get(num) + 1;
                elementCountMapping.put(num, temp);
                // System.out.println(elementCountMapping.get(num));
            } else {
                elementCountMapping.put(num, 1);
            }
        }
        temp = 0;
        for (int key : elementCountMapping.keySet()) {
            if (elementCountMapping.get(key) > temp) {
                temp = elementCountMapping.get(key);
                result = key;
            }
        }
        return (result);
    }
}

public class MaxOccuredElementInArray {
    public static void main(String[] args) {
        int[] nums = { 2, 3, 4, 5, 2, 2, 2, 3, 1, 1 };
        Solution s = new Solution();
        System.out.println(s.majorityElement(nums));
    }
}

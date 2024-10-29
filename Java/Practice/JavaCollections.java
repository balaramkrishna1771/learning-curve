import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class JavaCollections {
    public static void main(String[] args) {
        List<Integer> intArrayList = new ArrayList<>();
        intArrayList.add(21);
        intArrayList.add(16);
        intArrayList.add(15);
        intArrayList.add(8);

        System.out.println("Before Removing element at index 2: " + intArrayList);

        // remove operation takes O(n) time complexity as it shifts entire array to the
        // removed position
        intArrayList.remove(2);

        intArrayList.remove(Integer.valueOf(8));

        System.out.println("After Removing element at index 2: " + intArrayList);

        if (intArrayList.contains(21))
            System.out.println("The item is available");

        List<String> stringLinkedList = new LinkedList<>();

        stringLinkedList.add("Bala");
        stringLinkedList.add("Ram");
        stringLinkedList.add("Krishna");

        System.out.println(stringLinkedList);
        stringLinkedList.remove("Krishna");
        System.out.println(stringLinkedList);

        System.out.println(stringLinkedList.indexOf("Ram"));

        Set<Integer> intSet = new HashSet<>();

        intSet.add(1);
        intSet.add(2);
        intSet.add(1); // Only adds 1 one time as a Set dosen't hold the duplicates

        System.out.println(intSet);

        Map<Integer, String> rollNumberMap = new HashMap<>();
        rollNumberMap.put(21, "Steve");
        rollNumberMap.put(90, "Mary");
        rollNumberMap.put(1, "John");

        System.out.println(rollNumberMap);
        System.out.println(rollNumberMap.get(21));
        System.out.println("Size of the map: " + rollNumberMap.size());

        for (Map.Entry<Integer, String> eachPair : rollNumberMap.entrySet()) {
            System.out.println(eachPair.getKey() + "-" + eachPair.getValue());
        }

        Queue<Integer> intQ = new PriorityQueue<Integer>();

        intQ.offer(2121);
        intQ.offer(32);
        intQ.offer(43);
        // intQ.offer(43);

        System.out.println(intQ);

        System.out.println(intQ.peek());
        System.out.println(intQ.poll());
        System.out.println(intQ);

    }
}
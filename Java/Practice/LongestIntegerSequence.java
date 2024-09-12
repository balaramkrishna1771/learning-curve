import java.util.*;

public class LongestIntegerSequence {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Integer> inpList = new ArrayList<>();
        System.out.print("Enter the length of the array: ");
        int n = scan.nextInt(), sequenceCounter = 0;
        for (int i = 0; i < n; i++) {
            System.out.print("Please enter " + i + " element: ");
            inpList.add(scan.nextInt());

            if (!(inpList.contains(inpList.get(i) - 1)))
                sequenceCounter++;
        }
        scan.close();

        System.out.println("Number of Sequences are: " + sequenceCounter);

    }

}

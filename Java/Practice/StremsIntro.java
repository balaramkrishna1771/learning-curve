import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StremsIntro {
    public static void main(String[] args) {
        List<Integer> simpleList = Arrays.asList(1, 90, 3, 4, -90, 6);
        Stream<Integer> dataStream = simpleList.stream().map(eachNumber -> eachNumber + eachNumber);
        System.out.println(dataStream);

        dataStream.forEach(eachNumber -> System.out.println(eachNumber));

    }
}

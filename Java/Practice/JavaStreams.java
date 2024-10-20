import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class JavaStreams {
        public static void main(String[] args) {
                List<Person> persons = new ArrayList<>();

                Person p1 = new Person("Ram", 21, 120);
                Person p2 = new Person("Krishna", 71, 800);

                persons.add(p1);
                persons.add(p2);



                List<Person> filteredPersons = persons.stream().filter(person -> person.getAge() >= 50)
                                .toList();
                 filteredPersons.forEach(System.out::println);
                 System.out.println(p1.toString());

                List<Integer> randomNumbers = Arrays.asList(2, 3, 12, 32, 1, 7, 5, 90, 34, 76);

                List<Integer> squaredNumbers = randomNumbers.stream()
                                .map(eachNumber -> (eachNumber * eachNumber))
                                .filter(eachNumber -> eachNumber >= 100)
                                .toList();
                 squaredNumbers.forEach(System.out::println);

                Integer sumOfRandomNumbers = randomNumbers.stream().map(eachNumber -> (eachNumber * eachNumber))
                                .filter(eachNumber -> eachNumber >= 100)
                                .skip(2)
                                .reduce(0, Integer::sum);

                Long countOFNumbers = randomNumbers.stream()
                                .filter(num -> num > 3)
                                .count();

                Predicate<Long> checkValue = (n -> n % 2 == 0);
                System.out.println(checkValue.test(countOFNumbers));

                System.out.println(sumOfRandomNumbers);
                System.out.println(countOFNumbers);


        }
}

import java.util.*;
import java.util.stream.Collectors;

class Person {
    private String personName;
    private int personAge;
    private int personIncome;

    public Person(String personName, int personAge, int personIncome) {
        this.personAge = personAge;
        this.personIncome = personIncome;
        this.personName = personName;
    }

    public void setName(String personName) {
        this.personName = personName;
    }

    public String getName() {
        return this.personName;
    }

    public void setAge(int personAge) {
        this.personAge = personAge;
    }

    public int getAge() {
        return this.personAge;
    }

    public void setIncome(int personIncome) {
        this.personIncome = personIncome;
    }

    public int getIncome() {
        return this.personIncome;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Name='" + personName + '\'' +
                ", Age=" + personAge +
                ", Income=" + personIncome +
                '}';
    }
}

public class JavaStreams {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        Person p1 = new Person("Ram", 21, 120);
        Person p2 = new Person("Krishna", 71, 800);

        persons.add(p1);
        persons.add(p2);

        List<Person> filteredPersons = persons.stream().filter(person -> person.getAge() >= 50)
                .collect(Collectors.toList());
        // filteredPersons.forEach(person -> System.out.println(person));
        // System.out.println(p1.toString());

        List<Integer> randomNumbers = Arrays.asList(2, 3, 12, 32, 1, 7, 5, 90, 34, 76);

        List<Integer> squaredNumbers = randomNumbers.stream()
                .map(eachNumber -> (eachNumber * eachNumber))
                .filter(eachNumber -> eachNumber >= 100)
                .collect(Collectors.toList());
        // squaredNumbers.forEach(System.out::println);

        Integer sumOfRandomNumbers = randomNumbers.stream().map(eachNumber -> (eachNumber * eachNumber))
                .filter(eachNumber -> eachNumber >= 100)
                .skip(2)
                .reduce(0, Integer::sum);

        System.out.println(sumOfRandomNumbers);
    }
}

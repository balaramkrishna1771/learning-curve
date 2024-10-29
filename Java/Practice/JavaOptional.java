import java.util.Optional;

public class JavaOptional {

    public static void main(String[] args) {
        Optional<Person> randomPerson = findPersonByName("John");

        System.out.println(randomPerson.map(Person::getAge)
                .orElse(0));

        // Using Optional as a stream allows us to add extra checks to unpresent values
        // if any
        System.out.println(randomPerson.stream()
                .map(Person::getAge)
                .reduce(0, (age, acc) -> age + acc));
        System.out.println(randomPerson.orElse(new Person("UNKNOWN", 0, 0)));

        if (randomPerson.isPresent()) {
            // orElseTrow is same as get
            System.out.println(randomPerson.get().getAge());
        } else {
            System.out.println("NOT FOUND");
        }

        System.out.println(randomPerson.get());
    }

    private static Optional<Person> findPersonByName(String name) {
        // mocking the database call
        Person randomPerson = new Person(name, 28, 12000);

        // return Optional.ofNullable(null);
        return Optional.ofNullable(randomPerson);
    }

}

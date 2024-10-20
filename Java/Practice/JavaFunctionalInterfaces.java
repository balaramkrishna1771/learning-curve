import java.lang.Math;
import java.util.function.*;
import java.util.*;

public class JavaFunctionalInterfaces {
    public static void main(String[] args) {
        Person randomPerson = new Person("Ravi", 25, 150);

        Function<Integer, String> testFunction = age -> {
            return (age > 18) ? "adult" : "minor";
        };

        Predicate<String> tesPredicate = s -> s.length() >= 4;

        Consumer<Integer> tConsumer = age -> System.out.println(age);

        Supplier<Double> tSupplier = () -> Math.random();

        System.out.println(tesPredicate.test(randomPerson.getName()));
        tConsumer.accept(randomPerson.getAge());

        System.out.println(tSupplier.get());

        System.out.println(testFunction.apply(randomPerson.getAge()));
        Printable printThing = () -> {
            int i = 1000;
        };
        printThing(printThing);
    }

    private static void printThing(Printable thing) {
        thing.print();
    }
}

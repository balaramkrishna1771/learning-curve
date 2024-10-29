import java.util.ArrayList;
import java.util.List;

public class Generics {
    public static <T extends List<?>> void genericPrintMethod(T value) {
        System.out.println(value);
    }

    public static void main(String[] args) {
        GenericPrinter<Integer> intObj = new GenericPrinter<>(234);
        intObj.print();

        GenericPrinter<String> stringObj = new GenericPrinter<String>("Hello");
        stringObj.print();

        List<Person> persons = new ArrayList<>();
        Person randomGuy = new Person("Peter", 23, 54422);

        persons.add(new Person("Ram", 78, 12000));

        GenericClassWithMultiplePrams<Integer, List<Person>> multiParamObj = new GenericClassWithMultiplePrams<>(100,
                persons);
        multiParamObj.print();

        // wild card with bounded generics
        genericPrintMethod(persons);
    }
}

import java.lang.Math;

public class JavaFunctionalInterfaces {
    public static void main(String[] args) {
        Person randomPerson = new Person("Ravi", 25, 150);
        Printable printThing = () -> 1000;
        printThing(printThing);
    }

    private static void printThing(Printable thing) {
        thing.print();
    }
}

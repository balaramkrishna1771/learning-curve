public class JavaFunctionalInterfaces {
    public static void main(String[] args) {
        Person randomPerson = new Person("Ravi", 25, 150);
        Printable printThing = () -> System.out.println("This is the Person Class Print Method");
        printThing(printThing);
    }

    private static void printThing(Printable thing) {
        thing.print();
    }
}

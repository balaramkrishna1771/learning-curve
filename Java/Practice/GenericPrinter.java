public class GenericPrinter<T> {

    private T printValue;

    public GenericPrinter(T printValue) {
        this.printValue = printValue;
    }

    public void print() {
        System.out.println(this.printValue);
    }
}

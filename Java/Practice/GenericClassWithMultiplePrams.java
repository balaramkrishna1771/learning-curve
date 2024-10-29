public class GenericClassWithMultiplePrams<T, V> {

    private T firstParam;
    private V secondParam;

    public GenericClassWithMultiplePrams(T firstParam, V secondPram) {
        this.firstParam = firstParam;
        this.secondParam = secondPram;
    }

    public void print() {
        System.out.println("This is the first prameter" + this.firstParam);
        System.out.println("This is the second parameter" + this.secondParam);
    }

}

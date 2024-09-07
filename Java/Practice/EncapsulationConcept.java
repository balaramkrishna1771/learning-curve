import java.util.Scanner;

class SettersAndGetters {
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }
}

public class EncapsulationConcept {
    public static void main(String args[]) {
        Scanner scannerObj = new Scanner(System.in);
        SettersAndGetters testObj = new SettersAndGetters();
        testObj.setName(scannerObj.nextLine());
        System.out.println(testObj.getName());
    }
}

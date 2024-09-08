public class Person implements Printable {
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

    public void print() {
        System.out.println("This is the Person Class Print Method" + this.personAge + " "
                + this.personName + " " + this.personIncome);
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
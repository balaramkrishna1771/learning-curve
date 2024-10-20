interface Eatable{
    void eat();
}

interface Workable{
    void work();
}

class Robot implements Workable{

    @Override
    public void work(){
        System.out.println("Task for a Robot");
    }
}

class Human implements Eatable, Workable{

    @Override
    public void eat() {
        System.out.println("Person eating");
    }

    @Override
    public void work(){
        System.out.println("Task for a Human");
    }
}


public class MoreOnJavaStreams {
    public static void main(String[] args) {
        Human person1 = new Human();
        Robot robot1 = new Robot();
        
        person1.eat();
        person1.work();

        robot1.work();
        
        
    }
}

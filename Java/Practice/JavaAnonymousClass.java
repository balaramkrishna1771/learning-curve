class Animal {
    public void makeNoise() {
        System.out.println("yap yap yap");
    }
}

public class JavaAnonymousClass {

    public static void main(String[] args) {
        Animal animal = new Animal();

        animal.makeNoise();

        Animal anonymousAnimal = new Animal() {

            @Override
            public void makeNoise() {
                System.out.println("Meaw");
            }

        };

        anonymousAnimal.makeNoise();

    }
}

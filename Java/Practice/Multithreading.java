import java.time.Duration;

class WorkingWithThreads extends Thread {

    private Integer threadNumInteger;

    public WorkingWithThreads(Integer threadNumInteger) {
        this.threadNumInteger = threadNumInteger;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 5; i++) {
                System.out.println(threadNumInteger + " is printing - " + i);
                Thread.sleep(Duration.ofSeconds(i));
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception Occured" + e);
        }

    }
}

public class Multithreading {
    public static void main(String[] args) {
        WorkingWithThreads myThreads = new WorkingWithThreads(1);
        WorkingWithThreads myThreads2 = new WorkingWithThreads(2);
        myThreads.start();
        myThreads2.start();
        // myThreads.run();
        // myThreads2.run();

    }
}

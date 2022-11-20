import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

public class Main {
    private static final int MIN = 10;
    private static final int MAX = 100;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        LongAdder stat = new LongAdder();

        IntStream.range(MIN,MAX).forEach(i->executorService.submit(()->stat.add(i)));
        IntStream.range(MIN,MAX).forEach(i->executorService.submit(()->stat.add(i)));
        IntStream.range(MIN,MAX).forEach(i->executorService.submit(()->stat.add(i)));

        executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("\nРезультат: " + stat.sum());
        executorService.shutdown();
    }
}
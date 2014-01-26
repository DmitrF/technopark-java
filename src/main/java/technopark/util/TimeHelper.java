package technopark.util;

public class TimeHelper {

    public static void sleep(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            //TODO: log it
            //e.printStackTrace();
        }
    }
}

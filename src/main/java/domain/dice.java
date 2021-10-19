package domain;
import java.util.concurrent.ThreadLocalRandom;
public class dice {
    public int result(){
       int d1 = ThreadLocalRandom.current().nextInt(1,7);
       int d2 = ThreadLocalRandom.current().nextInt(1,7);
       int ran = d1 + d2;
       return ran;
    }
}

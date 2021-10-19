package domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.Callable;

public class Player implements Callable<Integer> {
    public static Logger logger = LoggerFactory.getLogger(Player.class);
    private String name;
    private static dice die = new dice();
    public Player(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
    public Integer call() throws Exception {
        logger.info(name+"'s turn: ");
        int val = 0;
        val = val % 10;
        if (val == 0){
            val = 1;
        }
        for (int idx = 0; idx < val; idx++){
            die.result();
        }
        int roll = 0;
        roll = die.result();
        logger.info(name + " rolled " + roll + ".");
        return roll;
    }
}
package domain;

import java.util.Random;

public class dice {

    private Random dice1;
    private Random dice2;
    public dice(){
         dice1 = new Random();
         dice2 = new Random();
    }
    public int result(){
       int d1 = dice1.nextInt(6)+1;
       int d2 = dice2.nextInt(6)+1;
       int ran = d1 + d2;
       return ran;
    }
}

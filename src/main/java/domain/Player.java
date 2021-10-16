package domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
public class Player {

    public static Logger logger = LoggerFactory.getLogger(Player.class);
    private String name;
    private static dice die = new dice();

    public Player(String name){
        this.name = name;
    }

    public int takeTurn(){
        Scanner scan = new Scanner(System.in);
        System.out.print(name+"'s turn: ");
        logger.info(name+"'s turn: ");
        System.out.println("\nPlease press Enter");
        String input = scan.nextLine();
        int val = 0;
        for (int idx = 0; idx < input.length(); idx++){
            val+= input.charAt(idx);
        }
        val = val % 10;
        if (val == 0){
            val = 1;
        }
        for (int idx = 0; idx < val; idx++){
            die.result();
        }
        int roll = 0;
        roll = die.result();
        System.out.println(name + " rolled " + roll + ".");
        logger.info(name + " rolled " + roll + ".");
        return roll;
        }

        @Override
        public String toString(){
            return name;
        }
    }


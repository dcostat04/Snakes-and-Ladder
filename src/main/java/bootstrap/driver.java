package bootstrap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import domain.Player;
import domain.board;
import domain.dice;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class driver {

    public static Logger logger = LoggerFactory.getLogger(driver.class);
    private static dice die = new dice();
    public static void main(String[] args)throws IOException{
        configureLogging("game.log", "INFO");
        System.out.println("*********************************Welcome to Snakes & Ladders by Trevor D'Costa******************************");
        logger.info("*********************************Welcome to Snakes & Ladders by Trevor D'Costa******************************");
        Scanner scan = new Scanner (System.in);

        int noOfPlayer = 4;
        System.out.print(noOfPlayer+" players are playing \n" );
        List<Player> players = new ArrayList();
        Player player1 = new Player("Trevor" );
        players.add(player1);
        Player player2 = new Player("Edwin" );
        players.add(player2);
        Player player3 = new Player("Pratik" );
        players.add(player3);
        Player player4 = new Player("Christopher" );
        players.add(player4);
        board board = new board(players);
        boolean Finish = false;
        int playerId = 0;
        while (!Finish){
            Player currPlayer = players.get(playerId);
            int roll = currPlayer.takeTurn();
            Finish = board.movePlayer(currPlayer, roll);
            System.out.println(board);
            System.out.println("-----------------------\n");
            if (Finish){
                System.out.println(currPlayer + " wins");
                logger.info(currPlayer + " wins");
            }
            playerId++;
            if (playerId == noOfPlayer){
                playerId = 0;
            }
        }
    }
    public static String configureLogging(String logFile, String logLevel) {
        DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender();

        switch (logLevel) {
            case "DEBUG": {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.DEBUG_INT));
            }
            case "WARN": {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.WARN_INT));
            }
            case "ERROR": {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.ERROR_INT));
            }
            default: {
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.INFO_INT));
            }
            break;
        }

        System.out.println("Log files written out at " + logFile);
        dailyRollingFileAppender.setFile(logFile);
        dailyRollingFileAppender.setLayout(new EnhancedPatternLayout("%d [%t] %-5p %c - %m%n"));

        dailyRollingFileAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(dailyRollingFileAppender);
        return dailyRollingFileAppender.getFile();
    }
}

package bootstrap;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import domain.*;
import org.apache.log4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class driver {
    public static Logger logger = LoggerFactory.getLogger(driver.class);
    private static dice die = new dice();
    public static void main(String[] args) throws InterruptedException {
        configureLogging("game.log", "INFO");
        logger.info("*********************************Welcome to Snakes & Ladders by Trevor D'Costa******************************");
        int noOfPlayer = 4;
        logger.info(noOfPlayer+" players are playing " );

        List<Player> players = new ArrayList();
        Player player1 = new Player(("Trevor"));
        players.add(player1);
        Player player2 = new Player(("Pratik"));
        players.add(player2);
        Player player3 = new Player(("Christopher"));
        players.add(player3);
        Player player4 = new Player(("Edwin"));
        players.add(player4);
        board board = new board(players);

        boolean Finish = false;
        int playerId = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        while (!Finish){
            Player currPlayer = players.get(playerId);
            List<Future<Integer>> roll = executorService.invokeAll(players);
            Finish = board.movePlayer(currPlayer, roll);
            if (Finish){
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

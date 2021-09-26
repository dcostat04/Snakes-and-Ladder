package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class board {

    private final int ROWS = 10;
    private final int COLS = 10;
    private final int NoSnakes = 8;
    private final int NoLadders = 8;

    private int[][] gameBoard;
    private int[][] snakes;
    private int[][] ladders;


    private void setSnakes(){
        snakes = new int[NoSnakes][2];
        snakes[0][0] = 17;
        snakes[0][1] = 7;
        snakes[1][0] = 54;
        snakes[1][1] = 34;
        snakes[2][0] = 62;
        snakes[2][1] = 19;
        snakes[3][0] = 64;
        snakes[3][1] = 60;
        snakes[4][0] = 87;
        snakes[4][1] = 24;
        snakes[5][0] = 93;
        snakes[5][1] = 73;
        snakes[6][0] = 95;
        snakes[6][1] = 75;
        snakes[7][0] = 99;
        snakes[7][1] = 78;
    }


    private void setLadders(){
        ladders = new int[NoLadders][2];
        ladders[0][0] = 4;
        ladders[0][1] = 14;
        ladders[1][0] = 9;
        ladders[1][1] = 31;
        ladders[2][0] = 20;
        ladders[2][1] = 38;
        ladders[3][0] = 28;
        ladders[3][1] = 84;
        ladders[4][0] = 40;
        ladders[4][1] = 59;
        ladders[5][0] = 51;
        ladders[5][1] = 67;
        ladders[6][0] = 63;
        ladders[6][1] = 81;
        ladders[7][0] = 71;
        ladders[7][1] = 91;
    }


    Map<Player, Integer> playerPositions;

        public board(List<Player> players){

            this.playerPositions = new HashMap<Player, Integer>();
            for (Player player : players){
                this.playerPositions.put(player, 0);
            }
            gameBoard = new int[ROWS][COLS];
            for (int row = 0; row < ROWS; row++){
                for (int col = 0; col < COLS; col++){
                    gameBoard[row][col] = row*ROWS + col + 1;
                }
            }
            setSnakes();
            setLadders();
        }

        public boolean movePlayer(Player player, int value){

            int position = playerPositions.get(player);
            position += value;
            if (position >= 100){

                playerPositions.put(player, 100);
                return true;
            }
            else {
                for (int id = 0; id < NoSnakes; id++){
                    if (snakes[id][0] == position){

                        position = snakes[id][1];
                        playerPositions.put(player, position);

                        System.out.println("Snake Bites " + player + " from " + snakes[id][0] + " to " + snakes[id][1]);

                        return false;
                    }
                }
                for (int id = 0; id < NoLadders; id++){
                    if (ladders[id][0] == position){
                        position = ladders[id][1];
                        playerPositions.put(player, position);

                        System.out.println( player + " climbs ladder from " + ladders[id][0] + " to " + ladders[id][1]);

                        return false;
                    }
                }
                playerPositions.put(player, position);
                return false;
            }
        }



        @Override
        public String toString(){

            StringBuilder sb = new StringBuilder();
            boolean oddRow = true;

            for (int row = ROWS-1; row >= 0; row--){
                for (int col = 0; col < COLS; col++){
                    if (oddRow){
                        String pl = "";
                        boolean occupied = false;
                        for (Player temp : playerPositions.keySet()){
                            if (playerPositions.get(temp) == gameBoard[row][COLS-1-col]){
                                occupied = true;
                                pl += temp + " ";
                            }
                        }

                        if (occupied){
                            pl += "\t";
                            sb.append(pl);
                        } else {
                            sb.append(gameBoard[row][COLS-1-col] + "\t");
                        }
                    } else {
                        boolean occupied = false;
                        String pl = "";
                        for (Player temp : playerPositions.keySet()){
                            if (playerPositions.get(temp) == gameBoard[row][col]){
                                occupied = true;
                                pl += (temp + " ");
                            }
                        }

                        if (occupied){
                            pl += "\t";
                            sb.append(pl);
                        } else {
                            sb.append(gameBoard[row][col] + "\t");
                        }
                    }
                }
                oddRow = !oddRow;
                sb.append("\n");
            }
            sb.append("\n");
            return sb.toString();
        }
    }


//package SnakeLadderGame;
//
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//
//public class GameAppMain {
//
//    public static void main(String[] args) throws Exception {
//
//        Config config = new Config(2, 4, 6, 1, 100, 6);
//        List<Jump> snakes = Arrays.asList(new Jump(10,5), new Jump(6,3));
//        List<Jump> ladders = Arrays.asList(new Jump(7, 11), new Jump(4,8), new Jump(90,99), new Jump(44,88));
//        Queue<Player> players = new LinkedList<>();
//        players.add(new Player("tom"));
//        players.add(new Player("om"));
//        SnakeLadderApp snakeLadderApp = new SnakeLadderApp(config, players, snakes, ladders);
//
//        snakeLadderApp.startGame();
//
//        System.out.println(snakeLadderApp.getWinner());
//
//    }
//}

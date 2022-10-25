//package SnakeLadderGame;
//
//import lombok.extern.log4j.Log4j;
//
//import java.util.*;
//
//@Log4j
//public class SnakeLadderApp {
//    private Config config;
//    private Queue<Player> players;
//    private List<Jump> snakes;
//    private List<Jump> ladders;
//    private Player winner;
//    private Dice dice;
//    static int defaultPosition;
//
//    public SnakeLadderApp(Config config, Queue<Player> players, List<Jump> snakes, List<Jump> ladders) {
//        this.config = config;
//        this.players = players;
//        this.snakes = snakes;
//        this.ladders = ladders;
//        this.dice = new NormalDice(config.getDiceMax());
//        this.defaultPosition = 0;
//    }
//
//    public String getWinner() {
//        return winner.getName();
//    }
//
//    public void startGame() throws Exception {
//        if (!validation()) {
//            throw new Exception("Game config not valid");
//        }
//
//        while (Objects.isNull(winner)) {
//            Player player = players.poll();
//            int random = dice.getRandom();
//            int currentPos = player.getPosition();
//            if (currentPos == defaultPosition) {
//                System.out.println("Dice random : " + random);
//                if (random == config.getFirstRollVal()) {
//                    player.updatePosition(config.getStartPosition());
//                }
//            } else {
//                int newPosition = checkSnakeAndLadder(currentPos + random);
//                if(newPosition <= config.getWinningPosition()){
//                    player.updatePosition(newPosition);
//                }
//            }
//            if (player.getPosition() == config.getWinningPosition()) {
//                winner = player;
//                System.out.println("Winner : " + player.getName());
//            }
//            log.info("Player " + player.getName() + " rolled a " + random + " and moved to " + player.getPosition());
//            players.add(player);
//        }
//
//    }
//
//    private int checkSnakeAndLadder(int currentPos) {
//        for (Jump jump : snakes) {
//            if (jump.getStart() == currentPos) {
//                System.out.println("snake found at : " + jump.getStart());
//                return jump.getEnd();
//            }
//        }
//        for (Jump jump : ladders) {
//            if (jump.getStart() == currentPos) {
//                System.out.println("ladder found at : " + jump.getStart());
//                return jump.getEnd();
//            }
//        }
//        return currentPos;
//    }
//
//    private boolean validation() {
//        int totalPlayers = players.size();
//        Set<Integer> jumpSet = new HashSet<>();
//
//        if (updateJumpSet(jumpSet, snakes, 0)) return false;
//        if (updateJumpSet(jumpSet, ladders, 1)) return false;
//
//        if (totalPlayers >= config.getMinPlayer() && totalPlayers <= config.getMaxPlayer()) {
//            return true;
//        }
//        return false;
//    }
//
//    private boolean updateJumpSet(Set<Integer> jumpSet, List<Jump> jumps, int type) {
//        for (Jump jump : jumps) {
//            if (type == 0) {
//                if (jump.getEnd() >= jump.getStart()) {
//                    return true;
//                }
//            } else {
//                if (jump.getEnd() <= jump.getStart()) {
//                    return true;
//                }
//            }
//            if (jumpSet.contains(jump.getStart())) {
//                return true;
//            }
//            jumpSet.add(jump.getStart());
//            if (jumpSet.contains(jump.getEnd())) {
//                return true;
//            }
//            jumpSet.add(jump.getEnd());
//        }
//        return false;
//    }
//
//}

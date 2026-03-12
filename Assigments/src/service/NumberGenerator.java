package service;

import java.util.Random;
import config.GameConfig;

public class NumberGenerator {

    public int generateNumber() {
        Random random = new Random();
        return random.nextInt(GameConfig.MAX_NUMBER - GameConfig.MIN_NUMBER + 1)
                + GameConfig.MIN_NUMBER;
    }
}
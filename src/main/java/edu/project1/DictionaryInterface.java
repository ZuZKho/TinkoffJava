package edu.project1;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public interface DictionaryInterface {

    String[] DICT = {"potato", "ball", "night", "mouse", "zebra"};

    @NotNull static String randomWord() {
        int dictionaryLen = DICT.length;
        Random rnd = new Random();
        int idx = rnd.nextInt(dictionaryLen);

        return DICT[idx];
    }
}


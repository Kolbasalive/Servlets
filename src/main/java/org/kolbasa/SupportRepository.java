package org.kolbasa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SupportRepository {
    private final List<String> phrases = new ArrayList<>();

    public SupportRepository() {
        builderPhrases();
    }

    public String getRandomPhrases() {
        return phrases.get(getRandomNumber());
    }

    public void setPhrases(String phrase) {
        phrases.add(phrase);
    }

    private void builderPhrases(){
        phrases.add("Встал упай, упай вставай, не буди бадубай");
        phrases.add("Вставай ержан!");
        phrases.add("Всё получится, верь мне)");
    }

    private Integer getRandomNumber() {
        Random random = new Random();
        return random.nextInt(phrases.size());
    }
}
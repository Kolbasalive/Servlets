package org.kolbasa;

public class SupportService {
    private final SupportRepository supportRepository;

    public SupportService(SupportRepository supportRepository) {
        this.supportRepository = supportRepository;
    }

    public String getSupportPhrases() {
        if (true) {
            throw new RuntimeException();
        }
        return supportRepository.getRandomPhrases();
    }

    public void setSupportPhrases(String phrases) {
        supportRepository.setPhrases(phrases);
    }
}
package org.kolbasa;

public class SupportServiceImpl implements SupportService {
    private final SupportRepository supportRepository;

    public SupportServiceImpl(SupportRepository supportRepository) {
        this.supportRepository = supportRepository;
    }

    public String getSupportPhrase() {
        return supportRepository.getRandomPhrases();
    }

    public void setSupportPhrase(String phrases) {
        supportRepository.setPhrases(phrases);
    }
}
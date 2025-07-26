package org.kolbasa;

public interface SupportService {
    @Logged
    String getSupportPhrase();
    @Logged
    void setSupportPhrase(String phrases);
}
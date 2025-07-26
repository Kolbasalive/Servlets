package org.kolbasa;

@Configuration
public class SupportFabrics {
    @Bean
    public SupportRepository supportRepository() {
        return new SupportRepository();
    }

    @Bean
    public SupportService supportService() {
        return new SupportServiceImpl(supportRepository());
    }
}

package model;

import java.time.LocalDateTime;

public class Daily implements Repeatability {

    @Override
    public LocalDateTime nextDateTime(LocalDateTime dateTime) {
        return dateTime.plusDays(1);
    }


    @Override
    public String toString() {
        return "ежедневная";
    }
}

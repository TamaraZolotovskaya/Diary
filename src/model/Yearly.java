package model;

import java.time.LocalDateTime;

public class Yearly implements Repeatability {

    @Override
    public LocalDateTime nextDateTime(LocalDateTime dateTime) {
        return dateTime.plusYears(1);
    }

    @Override
    public String toString() {
        return "ежегодная";
    }
}

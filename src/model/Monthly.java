package model;

import java.time.LocalDateTime;

public class Monthly implements Repeatability{
    @Override
    public LocalDateTime nextDateTime(LocalDateTime dateTime) {
        return dateTime.plusMonths(1);
    }

    @Override
    public String toString() {
        return "ежемесячная";
    }
}

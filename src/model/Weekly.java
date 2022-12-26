package model;

import java.time.LocalDateTime;

public class Weekly implements Repeatability {
    @Override
    public LocalDateTime nextDateTime(LocalDateTime dateTime) {
        return dateTime.plusWeeks(1);
    }

    @Override
    public String toString() {
        return "еженедельная";
    }
}

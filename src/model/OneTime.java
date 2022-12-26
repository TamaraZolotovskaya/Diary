package model;

import java.time.LocalDateTime;

public class OneTime implements Repeatability{

    @Override
    public LocalDateTime nextDateTime(LocalDateTime dateTime) {
        return null;
    }

    @Override
    public String toString() {
        return "однократная";
    }
}

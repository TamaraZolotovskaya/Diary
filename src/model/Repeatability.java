package model;

import java.time.LocalDateTime;

public interface Repeatability {

    LocalDateTime nextDateTime(LocalDateTime dateTime);
}

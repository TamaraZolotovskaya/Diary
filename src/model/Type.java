package model;

public enum Type {
    WORK,
    PERSONAL;

    @Override
    public String toString() {
        if (this.equals(WORK)) {
            return "рабочая";
        } else return "личная";
    }
}

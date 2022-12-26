package model;

import exeption.IncorrectParameterException;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    public static int idGenerator = 1;
    private final int id;
    private String name;
    private String description;
    private Type type;
    private LocalDateTime dateTime;
    private Repeatability repeatability;

    public Task(String name,
                String description,
                Type type,
                LocalDateTime dateTime,
                Repeatability repeatability) throws IncorrectParameterException
    {
        setName(name);
        setDescription(description);
        setType(type);
        setDateTime(dateTime);
        setRepeatability(repeatability);
        this.id = idGenerator;
        idGenerator++;
    }

    public void setName(String name) throws IncorrectParameterException {
        if (name == null || name.isBlank()) {
            throw new IncorrectParameterException("название задачи");
        }
        this.name = name;
    }

    public void setDescription(String description)  throws IncorrectParameterException{
        if (description == null || description.isBlank()) {
            throw new IncorrectParameterException("описание");
        }
        this.description = description;
    }

    public void setType(Type type) {
        if (type==null){
            type=Type.WORK;
        }
        this.type = type;
    }

    public void setDateTime(LocalDateTime dateTime) throws IncorrectParameterException {
        if (dateTime==null){
            throw new IncorrectParameterException("дата задачи");

        }
        this.dateTime = dateTime;
    }

    public void setRepeatability(Repeatability repeatability) {
        if(repeatability==null){
            repeatability= new OneTime();
        }
        this.repeatability = repeatability;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Repeatability getRepeatability() {
        return repeatability;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", название задачи: " + name +
                ", описание: " + description + "\n"+
                "тип " + type +
                ", время выполнения: " + dateTime +
                ", повторяемость " + repeatability+ "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package exeption;

public class TaskNotFoundExeption extends Exception{
    private int id;

    public TaskNotFoundExeption(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Задачи c таким id "+id+" не существует";
    }
}

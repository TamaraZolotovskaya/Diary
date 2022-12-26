package service;

import exeption.IncorrectParameterException;
import exeption.TaskNotFoundExeption;
import model.OneTime;
import model.Repeatability;
import model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskList {
    private static final Map<Integer, Task> TASKS = new HashMap<>();

    private TaskList() {
    }

    public static void addTask(Task task) {
        TASKS.put(task.getId(), task);
    }

    public static void remove(int id) throws TaskNotFoundExeption {
        if (TASKS.containsKey(id)) {
            System.out.println("Удалена задача " + TASKS.get(id));
            TASKS.remove(id);
        } else throw new TaskNotFoundExeption(id);
    }

    public static ArrayList<Task> getForDay(LocalDate date)  {
        System.out.println(date);
        Collection<Task> allTasks = TASKS.values();
        ArrayList<Task> TasksForDay = new ArrayList<>();
        for (Task task : allTasks) {
            LocalDateTime taskDateTime = task.getDateTime();


            if (taskDateTime.toLocalDate().isEqual(date)) {
                TasksForDay.add(task);
                continue;
            }
            if (task.getRepeatability().getClass() != OneTime.class) {

                while (taskDateTime.toLocalDate().isBefore(date)) {
                    taskDateTime = task.getRepeatability().nextDateTime(taskDateTime);
                    System.out.println(taskDateTime);
                    if (taskDateTime.toLocalDate().isEqual(date)) {
                        try{
                        task.setDateTime(taskDateTime);}
                        catch (IncorrectParameterException e) {
                            System.out.println(e.getMessage());
                        }
                        TasksForDay.add(task);
                    }
                }
            }

        }
        return TasksForDay;
    }


}

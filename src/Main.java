import exeption.IncorrectParameterException;
import exeption.TaskNotFoundExeption;
import model.*;
import service.TaskList;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {
    private static final Pattern DATE_TIME_PATTERN =
            Pattern.compile("\\d{1,2}\\.\\d{1,2}\\.\\d{4} \\d{2}:\\d{2}");
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    private static final Pattern DATE_PATTERN =
            Pattern.compile("\\d{1,2}\\.\\d{1,2}\\.\\d{4}");
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        label:
        while (true) {
            printMenu();
            System.out.print("Выберите пункт меню: ");
            if (scanner.hasNextInt()) {
                int menu = scanner.nextInt();
                switch (menu) {
                    case 1:
                        addTask(scanner);
                        break;
                    case 2:
                        removeTask(scanner);
                        break;
                    case 3:
                        ArrayList<Task> TasksForDay = getTasksForDay(scanner);
                        if (TasksForDay.isEmpty()){
                            System.out.println("Задач на день нет");
                        }
                        else {System.out.println(TasksForDay);}
                        break;
                    case 0:
                        break label;
                }
            } else {
                scanner.next();
                System.out.println("Выберите пункт меню из списка!");
            }
        }

    }

    private static void removeTask(Scanner scanner) {
        while (true) {
            System.out.print("Введите id задачи, которую нужно удалить: ");
            if (scanner.hasNextInt()) {
                int id = scanner.nextInt();
                try {
                    TaskList.remove(id);

                } catch (TaskNotFoundExeption e) {
                    System.out.println(e.getMessage());
                }
                break;
            } else {
                scanner.next();
                System.out.println("Id введен некорректно!");
            }
        }
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String name = scanner.next();
        System.out.print("Введите описание задачи: ");
        String description = scanner.next();
        int menu = 0;
        Type type = Type.WORK;
        while (menu != 1 && menu != 2) {
            System.out.print
                    ("1. Рабочая задача \n" + "2. Личная задача \nВыберите тип задачи: ");
            if (scanner.hasNextInt()) {
                menu = scanner.nextInt();
                if (menu == 2) {
                    type = Type.PERSONAL;
                }
            } else {
                scanner.next();
                System.out.println("Выберите пункт меню из списка!");
            }
        }
        menu = 0;
        Repeatability repeatability = new OneTime();
        while (menu < 1 || menu > 5) {
            System.out.print(
                    "1. Задача однократная \n" +
                            "2. Задача ежедневная \n" +
                            "3. Задача еженедельная \n" +
                            "4. Задача ежемесячная \n" +
                            "5. Задача ежегодная \n" +
                            "Выберите периодичность выполнения задачи: ");
            if (scanner.hasNextInt()) {
                menu = scanner.nextInt();
                switch (menu) {
                    case 1:
                        break;
                    case 2:
                        repeatability = new Daily();
                        break;
                    case 3:
                        repeatability = new Weekly();
                        break;
                    case 4:
                        repeatability = new Monthly();
                        break;
                    case 5:
                        repeatability = new Yearly();
                        break;
                }
            } else {
                scanner.next();
                System.out.println("Выберите пункт меню из списка!");
            }
        }
        LocalDateTime dateTime = inputDayTame(scanner);
        try {
            Task task = new Task(name, description, type, dateTime, repeatability);
            TaskList.addTask(task);
            System.out.println("Добавлена задача " + task);
        } catch (IncorrectParameterException e) {
            System.out.println(e.getMessage());
        }


    }

    private static ArrayList<Task> getTasksForDay(Scanner scanner) {
        LocalDate date = inputDay(scanner);
        ArrayList<Task> TasksForDay = TaskList.getForDay(date);
        return TasksForDay;
    }

    private static LocalDateTime inputDayTame(Scanner scanner) {
        LocalDateTime dateTime = null;
        while (dateTime == null) {
            System.out.print("Введите дату и время в формате dd.MM.yyyy HH:mm ");
            if (scanner.hasNext(DATE_TIME_PATTERN)) {
                String dateTimeString = scanner.next();
                try {
                    dateTime = LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
                } catch (DateTimeParseException e) {
                    System.out.println("Некорректный формат даты и времени!");
                }
            } else {
                scanner.next();
                System.out.println("Некорректный формат даты и времени!");
            }
        }
        return dateTime;
    }

    private static LocalDate inputDay(Scanner scanner) {
        LocalDate date = null;
        while (date == null) {
            System.out.print("Введите дату и время в формате dd.MM.yyyy ");
            if (scanner.hasNext(DATE_PATTERN)) {
                String dateString = scanner.next();
                try {
                    date = LocalDate.parse(dateString, DATE_FORMATTER);
                } catch (DateTimeParseException e) {
                    System.out.println("Некорректный формат даты!");
                }
            } else {
                scanner.next();
                System.out.println("Некорректный формат даты!");
            }
        }
        return date;
    }

    private static void printMenu() {
        System.out.println(
                " 1. Добавить задачу \n" +
                        " 2. Удалить задачу \n" +
                        " 3. Получить задачи на указанный день \n" +
                        " 0. Выход"
        );
    }

}
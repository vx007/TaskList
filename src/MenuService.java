import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;

public class MenuService {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskService taskService = new TaskService();

    public static void print() {
        try {
            label:
            while (true) {
                printMenu();
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask();
                            break;
                        case 2:
                            removeTask();
                            break;
                        case 3:
                            findDay();
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printMenu() {
        System.out.println("1. Добавить задачу");
        System.out.println("2. Удалить задачу");
        System.out.println("3. Получить задачу на указанный день");
        System.out.println("0. Выход");
        System.out.print("Выберите пункт меню: ");
    }

    public static void inputTask() {
        taskService.addTask(new Task(inputName(),
                inputDesk(),
                inputType(),
                inputTime(),
                inputRepeat()));
    }

    public static String inputName() {
        System.out.print("Введите название задачи: ");
        return scanner.next();
    }

    public static String inputDesk() {
        System.out.print("Введите описание задачи: ");
        return scanner.next();
    }

    public static boolean inputType() {
        System.out.print("Введите тип задачи (1 - личная, 0 - рабочая): ");
        if (scanner.hasNextInt())
            return switch (scanner.nextInt()) {
                case 1 -> true;
                case 0 -> false;
                default -> true;
            };
        return true;
    }

    public static LocalDateTime inputTime() {
        LocalDateTime time = null;
        do {
            System.out.print("Введите время в формате ДД.ММ.ГГГГ чч:мм : ");
            String dateTime = scanner.next() + " " + scanner.next();
            if (dateTime.matches("\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}")) {
                time = parseDateTime(dateTime);
            }
        } while (time == null);
        return time;
    }

    public static LocalDateTime parseDateTime(String s) {
        String[] dateAndTime = s.split(" ");
        String[] dateParts = dateAndTime[0].split("\\.");
        String[] timeParts = dateAndTime[1].split(":");
        LocalDateTime time;
        try {
            time = LocalDateTime.of(Integer.parseInt(dateParts[2]),
                    Integer.parseInt(dateParts[1]),
                    Integer.parseInt(dateParts[0]),
                    Integer.parseInt(timeParts[0]),
                    Integer.parseInt(timeParts[1]));
        } catch (DateTimeException e) {
            return null;
        }
        if (time.isBefore(LocalDateTime.now())) {
            return null;
        }
        return time;
    }

    public static Repeat inputRepeat() {
        int choose;
        do {
            System.out.println("Выберите повторяемость:");
            System.out.println("1. " + Repeat.SINGLE);
            System.out.println("2. " + Repeat.DAILY);
            System.out.println("3. " + Repeat.WEEKLY);
            System.out.println("4. " + Repeat.MONTHLY);
            System.out.println("5. " + Repeat.YEARLY);
            choose = scanner.nextInt();
        } while ((choose < 1) || (choose > 5));
        return switch (choose) {
            case 1 -> Repeat.SINGLE;
            case 2 -> Repeat.DAILY;
            case 3 -> Repeat.WEEKLY;
            case 4 -> Repeat.MONTHLY;
            case 5 -> Repeat.YEARLY;
            default -> null;
        };
    }

    public static void printTasks() {
        System.out.println(taskService.getTasks());
    }

    public static void removeTask() {
        printTasks();
        System.out.print("Введите id задачи: ");
        taskService.removeTask(scanner.nextInt());
    }

    public static void findDay() {
        for (Map.Entry<Integer, Task> entry : taskService.getTasks()) {
            if (matchDay(entry.getValue(), inputTime().toLocalDate())) {
                System.out.println(entry);
            }
        }
    }

    public static boolean matchDay(Task task, LocalDate day) {
        boolean result = false;
        switch (task.getRepeat()) {
            case SINGLE:
                if (day == task.getTime().toLocalDate()) {
                    result = true;
                    break;
                }
            case DAILY:
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    if (day == task.getTime().plusDays(i).toLocalDate()) {
                        result = true;
                        break;
                    }
                }
            case WEEKLY:
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    if (day == task.getTime().plusWeeks(i).toLocalDate()) {
                        result = true;
                        break;
                    }
                }
            case MONTHLY:
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    if (day == task.getTime().plusMonths(i).toLocalDate()) {
                        result = true;
                        break;
                    }
                }
            case YEARLY:
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    if (day == task.getTime().plusYears(i).toLocalDate()) {
                        result = true;
                        break;
                    }
                }
        }
        return result;
    }
}

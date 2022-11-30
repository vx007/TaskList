import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TaskService {

    private final Map<Integer, Task> tasks = new HashMap<>();

    public int addTask(Task task){
        tasks.put(task.getId(), task);
        return task.getId();
    }

    public int removeTask(int id){
        tasks.remove(id);
        return id;
    }

    public Set<Map.Entry<Integer,Task>> getTasks() {
        return tasks.entrySet();
    }
}

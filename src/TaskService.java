import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TaskService {

    private final Map<Integer, Task> tasks = new HashMap<>();

    public void addTask(Task task){
        tasks.put(task.getId(), task);
    }

    public void removeTask(int id){
        tasks.remove(id);
    }

    public Set<Map.Entry<Integer,Task>> getTasks() {
        return tasks.entrySet();
    }
}

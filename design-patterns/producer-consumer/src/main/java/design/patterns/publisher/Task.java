package design.patterns.publisher;

import java.util.Comparator;

/**
 * Created by Feng on 2/9/14.
 */
public class Task{

    static enum TaskType {
        CANCEL(10), BUY(5), SELL(0);

        private int priority;

        TaskType(int priority) {
            this.priority = priority;
        }

        public int getPriority(){
            return this.priority;
        }


    }

    static class TaskComparator implements Comparator<Task>{

        @Override
        public int compare(Task o1, Task o2) {
            if(o1.getTaskType().getPriority() == o2.getTaskType().getPriority()){
                return 0;
            }else{
                return o1.getTaskType().getPriority() > o2.getTaskType().getPriority() ? -1 : 1;
            }
        }
    }

    private TaskType taskType;
    private String taskId;
    private String taskDescription;

    public Task(TaskType taskType, String taskId, String taskDescription) {
        this.taskType = taskType;
        this.taskId = taskId;
        this.taskDescription = taskDescription;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskType=" + taskType +
                ", taskId='" + taskId + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                '}';
    }
}

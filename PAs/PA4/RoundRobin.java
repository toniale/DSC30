/*
 * NAME: Tonia Le
 * PID: A15662706
 */

/**
 * Class RoundRobin works as a scheduler
 *
 * @author Tonia Le
 * @since 02-01-21
 */
public class RoundRobin {

    /* constants */
    private static final String TASK_HANDLED = "All tasks are already handled.";
    private static final Integer DEFAULT_QUANTUM = 4;

    /* instance variables */
    private DoublyLinkedList<Task> waitlist, finished;
    private int quantum, burstTime, waitTime;

    /**
     * Constructor of RoundRobin with given task list
     *
     * @param toHandle is the list of tasks to handle
     */
    public RoundRobin(Task[] toHandle) {
        this(DEFAULT_QUANTUM, toHandle);
    }

    /**
     * Constructor of RoundRobin given a quantum number and task list
     *
     * @param quantum the quantum time unit
     * @param toHandle tasks to handle in form of an array
     */
    public RoundRobin(int quantum, Task[] toHandle) {
        // exceptions
        if (quantum < 1) {
            throw new IllegalArgumentException();
        }
        // null toHandle & no tasks passed in
        if (toHandle == null|| toHandle.length == 0) {
            throw new IllegalArgumentException();
        }
        // assign doubly linked list to waitlist
        this.waitlist = new DoublyLinkedList<Task>();

        this.quantum = quantum;
        this.burstTime = 0;
        this.waitTime = 0;

        // handle the tasks
        for (Task task: toHandle){
            this.waitlist.add(task);
        }
        this.finished = new DoublyLinkedList<Task>();
    }

    /**
     * Handles all tasks in waitlist
     *
     * @return the String that lists all tasks completed in order
     */
    public String handleAllTasks() {
        // no tasks in wait list
        if (this.waitlist.isEmpty()) {
            return TASK_HANDLED;
        }
        // when there are tasks in the waitlist
        while (this.waitlist.size() > 0) {
            // new task obj for first task in waitlist
            Task newTask = this.waitlist.get(0);
            // schedule in order for one quantum period
            for (int i = 0; i < this.quantum; i++) {
                newTask.handleTask();
                this.burstTime += 1;
                this.waitTime += this.waitlist.size()-1;

                // "mark [task] as completed"
                if(newTask.isFinished()) {
                    this.finished.add(newTask);
                    this.waitlist.remove(0);
                    break;
                }
            }
                // return to the queue
                if(!newTask.isFinished()) {
                    this.waitlist.remove(0);
                    this.waitlist.add(newTask);
                }
            }

        // string rep
        String returnString = "All tasks are handled within " + this.burstTime + " units of " +
                              "burst time and " + this.waitTime + " units of wait time." +
                              " The tasks are finished in this order:\n" +
                              this.finished.get(0).toString();

        // adds string representations in order
        for(int j = 1; j < this.finished.size(); j++) {
            returnString += " -> " + this.finished.get(j).toString();
        }
        return returnString;

    }

    /**
     * Main method for testing.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Task[] test1 = {new Task("A", 3), new Task("B", 4),
                        new Task("C", 4), new Task("D", 12),
                        new Task("E", 6), new Task("F", 3)};
        RoundRobin rr1 = new RoundRobin(3, test1);     // Quantum: 3, ToHandle: test1
        System.out.println(rr1.handleAllTasks());   // Burst: 32, Wait: 86, Order: AFBCED
        System.out.println();
        System.out.println(rr1.handleAllTasks());   // TASK_HANDLED
        System.out.println();

        Task[] test2 = {new Task("A", 9), new Task("B", 8),
                        new Task("C", 6), new Task("D", 4),
                        new Task("E", 4), new Task("F", 3)};
        RoundRobin rr2 = new RoundRobin(test2);  // Quantum: 4, ToHandle: test2
        System.out.println(rr2.handleAllTasks());   // Burst: 34, Wait: 123, Order: DEFBCA
        System.out.println();
        System.out.println(rr2.handleAllTasks());   // TASK_HANDLED
        System.out.println();

        Task[] test3 = {new Task("A", 7), new Task("B", 5),
                        new Task("C", 3), new Task("D", 1),
                        new Task("E", 2), new Task("F", 4),
                        new Task("G", 6), new Task("H", 8)};
        RoundRobin rr3 = new RoundRobin(3, test3);     // Quantum: 3, ToHandle: test3
        System.out.println(rr3.handleAllTasks());   // Burst: 36, Wait: 148, Order: CDEBFGAH
        System.out.println();
        System.out.println(rr3.handleAllTasks());   // TASK_HANDLED
        System.out.println();
    }
}

import java.util.concurrent.Callable;


/**
 * 
 * This class is a Callable type that can have priority and return a value
 * @author Maor-Or
 * @author Raz-Saad
 *
 */
public class Task implements Callable<Object>

{
	/**
	 * class attributes:
	 *  priority - the priority of the Task, noted with a TaskType
	 *  callable - a Callable which through it we will run the call tasks inputed by the user
	 */
    private TaskType priority;
    private Callable<Object> callable;

    //constructors
    /**
     * a constructor that sets up the Callable and priority of the Task
     * @param c - a Callable to store as the Task's Callable and call later
     * @param t - priority for the Task
     */
    private Task(Callable<Object> c, TaskType t )
    {
        priority = t;
        callable = c;
    }
    /**
     * a constructor that sets up the Callable, and gets no priority, 
     * which makes the task have an automatic "OTHER" priority
     * @param c - a Callable to store as the Task's Callable and call later
     */
    private Task(Callable<Object> c)
    {
        priority = TaskType.OTHER;
        callable = c;
    }

    //Factory methods
    /**
     * this function creates and returns a Task
     * @param c - a Callable for the Task
     * @param t - a priority for the Task
     * @return - the Task which was constructed
     */
    public static Task createTask(Callable<Object> c, TaskType t)
    {
        Task task = new Task(c,t);
        return task;
    }
    /**
     * this function creates and returns a Task
     * @param c - a Callable for the Task
     * @return - the Task which was constructed
     */
    public static Task createTask(Callable<Object> c)
    {
        Task task = new Task(c);
        return task;
    }
    
    //getters and setters
    /**
     * returns the priority TaskType object
     * @return priority
     */
    public TaskType getPriority() 
    {
    	return this.priority;
    }
    
    /**
     * sets the priority in the Task
     * @param t - the priority to set up in the Task
     */
    public void setPriority(TaskType t) 
    {
    	this.priority = t;
    }
    
    /**
     * returns the Task's Callable
     * @return - the Task's Callable 
     */
    public Callable<Object> getCallable()
    {
    	return callable;
    }
    
    
    //comparing task's priorities
    /**
     * compares between this Task and another one, using their prioritie's numeric values.
     * in case this' priority's numeric value is less than the other's - then this' priority is bigger and we return 1
     * in case it is the other way around we return -1, and in case they are equal, we return 0.
     * @param other -  the other Task to compare to this Task
     * @return - 1 | -1 | 0 , depends upon the described conditions above
     */
    public int compareTo(Task other)
    {
        if (this.priority.getPriorityValue() <
                other.priority.getPriorityValue())
        {
            return 1;
        }
        else if (this.priority.getPriorityValue() >
                other.priority.getPriorityValue())
        {
            return -1;
        }
        return 0;
    }
    
    /**
     * this function calls the Task's Callable's call function and returns it's generic Object
     * can also throw an Exception that was received from the Callable's call function
     * @return - the Task's Callable's call return object
     */
	@Override
	public Object call() throws Exception {
		
		return callable.call();
	}

	/**
	 * 
	 * @return - a hash code value for the object. 
	 * This method is supported for the benefit of hash tables such as those provided by java.util.HashMap. 
	 */
	public int HashCode() 
	{
		return callable.hashCode();
	}



}

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Maor-Or
 * @author Raz-Saad
 * 
 * @param <T> - generic object to return to Future
 */
public class CustomExecutor<T>
{
	/**
	 * class attributes:
	 *  threadPool - we are using the already established java made thread pool, so we can just use it in a way that 
	 * benefits our needs
	 *  maxPriority - we track the max priority that is in the queue for the getMaxPriority function
	 *  numOfCores - calculated amount of  the machine's processors for the min\max values
	 *  corePoolSize - calculated number of minimum threads in the threadPool, considering the machine's processors
	 *  maxPoolSize - calculated number of maximum threads in the threadPool, considering the machine's processors
	 */
	private ThreadPoolExecutor threadPool;
	private int maxPriority =3;
	private int numOfCores= Runtime.getRuntime().availableProcessors();
	private int corePoolSize = numOfCores/2;
	private int maxPoolSize = numOfCores-1;

	
	/**
	 * constructor that initializes the threadpool with the required parameters
	 * and with a priority queue that uses the ObjectComparator to compare Tasks in the queue
	 */
	public CustomExecutor() 
	{
		threadPool = new ThreadPoolExecutor(
			corePoolSize, maxPoolSize, 300, TimeUnit.MILLISECONDS,
				new PriorityBlockingQueue<>(10,new ObjectComparator() ));
	}


	/**
	 * this function submits a task into the threadpool, and updates the maxPriority parameter 
	 * @param task - the Task to submit
	 * @return - the Future that gets the value that will be returned from the call method in the 
	 * Task
	 */
	public Future<T> submit(Task task) 
	{
		Future<T> res = (Future<T>) threadPool.submit(task);

		if ((threadPool.getQueue().peek())==null) {//if the queue is null
			maxPriority = task.getPriority().getPriorityValue();

		}
		else if((threadPool.getQueue().peek()) instanceof Task)//if the queue isn't null - (threadPool.getQueue().peek()) instanceof Task
		{
			//if the first in the queue is less than task.priority meaning bigger priority 
			//then update maxPriority to the first in the queue
			
			maxPriority = ((Task)threadPool.getQueue().peek()).getPriority().getPriorityValue();
		}
		return res;
	}

	/**
	 * send the maxPriority int value, needless to say it happens in O(1) time
	 * @return the maxPriority value
	 */
	public int getCurrentMax()
	{
		return maxPriority;
	}

	/**
	 * calls the previously explained submit function with a new Task argument that holds the callable parameter
	 * @param callable - the callable to send to the new Task that is being built here
	 * @return the Future value that is being returned through all the submits
	 */
	public Future<T> submit(Callable<Object> callable) 
	{
		Task task = Task.createTask(callable);
		return submit(task);
	}

	/**
	 * calls the previously explained submit function with a new Task argument that holds the callable and priority parameters
	 * @param callable - the callable to send to the new Task that is being built here
	 * @param priority - the priority to send to the new Task that is being built here
	 * @return the Future value that is being returned through all the submits
	 */
	public Future<T> submit(Callable<Object> callable,TaskType priority) 
	{
		Task task = Task.createTask(callable, priority);
		return submit(task);
	}

	/**
	 *  this function uses the threadpool's shutdown function to Initiates an orderly shutdown in which previously submitted tasks are executed,
	 *  but no new tasks will be accepted.Invocation has no additional effect if already shut down. 
	 *
	 */
	public void gracefullyTerminate()
	{
		threadPool.shutdown();
	}



}


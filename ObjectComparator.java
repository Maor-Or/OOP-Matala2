import java.util.Comparator;


/**
 * 
 * @author Maor-Or
 * @author Raz-Saad
 *
 *this class implements the Comparator Interface and compares between two Tasks
 */
public class ObjectComparator implements Comparator<Object> {
	
	
/**
 * this function handles comparison between two tasks based on their priority value
 * 
 * @param leftTask - left task in the task queue to compare
 * @param rightTask - right task in the task queue to compare
 */
	public int compare(Object leftTask, Object rightTask) 
	{
		if (leftTask instanceof Task) {
			if (((Task) leftTask).getPriority().getPriorityValue() ==
					((Task) rightTask).getPriority().getPriorityValue()) 
			{
				return 0;
			}
			else if(((Task) leftTask).getPriority().getPriorityValue() <
					((Task) rightTask).getPriority().getPriorityValue()) 
			{
				return -1;
			}
			return +1;
		}
		return 0;
	}



	
	}

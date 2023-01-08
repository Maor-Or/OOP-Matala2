
/**
 * Class that extends Thread and override his call method.
 * The call method here calculate the number of lines in a giving txt file
 */
public class Ex2_1_Thread extends Thread{
	
	/**
	 * Class attributes:
	 * File_Name - an Array that stores a file name,
	 * we are using an array because we need to use the method "getNumofLines", this methods gets an array
	 * 
	 * counter - stores the number of lines of all the files that the treads calculated 
	 */
	
	private String [] File_Name = new String[1]; 
private static volatile int counter = 0;
	
	
	/**
	*  Constructor that fill the parameter File_Name into the File_Name array
	*  @param File_Name - a String that holds a file name
	*/
	
	public Ex2_1_Thread(String File_Name)
	{
		this.File_Name[0] = File_Name;
	}
	
	/**
	* Get the counter attribute
	* @return counter
	*/
	
	public static int  Get_Add_Counter()
	{
		return counter;
	}
	
	/**
	* Override the run method, Calculating the number of lines in a giving txt file,
	*  and calling to the Add_ToCounter function in order to increment counter in a synchronized way.
	*/
	
	public void run() {
		
		int NumOfLines = Ex2_1.getNumOfLines(File_Name);
		Add_ToCounter(NumOfLines);
		
	}
	
	/**
	 * This function increments counter in a synchronized way.
	* @param NumOfLines - the number of lines that was calculated in the run method.
	*/
	public static synchronized void Add_ToCounter(int NumOfLines)
	{
		
		counter+=NumOfLines;
	}
	

}

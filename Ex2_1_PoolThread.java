import java.util.concurrent.Callable;

/**
 * @author Raz Saad
 * @author Maor Or
 * Class that implements Callable and override his call method.
 * The call method here calculate the number of lines in a giving txt file
 */

public class Ex2_1_PoolThread implements Callable<Integer>  {
	
	/**
	 * Class attributes:
	 * File_Name - an Array that stores a file name,
	 * we are using an array because we need to use the method "getNumofLines", this methods gets an array
	 */
	private String [] File_Name = new String[1]; 
	
	/**
	*  Constructor that fill the parameter File_Name into the File_Name array
	*  @param File_Name - a String that holds a file name
	*/
	public Ex2_1_PoolThread(String File_Name)
	{
		this.File_Name[0] = File_Name;
	}
	
	
	/**
	* Override the call method, Calculating the number of lines in a giving txt file
	* @return number of lines in the file
	* @exception Exception
	*/
	
	
	@Override
	public Integer call() throws Exception {
		
		return Ex2_1.getNumOfLines(File_Name);
	}

	
}

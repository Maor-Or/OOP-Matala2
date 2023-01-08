import java.io.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;

public class Ex2_1 {

	/**
	 * An assisting function to the createTextFiles function, 
	 * this function writes into a giving file a random amount of lines the string Hello World
	 * 
	 * @param fileName - a string that hold a file name
	 * 
	 * necessary parameters to generate a random number
	 * @param rand 
	 * @param bound
	 * @exception Exception
	 */
	
	
	public static void fileWrite(String fileName,Random rand,int bound) {
		try{
			FileOutputStream fos = new FileOutputStream(fileName); 
			int number_of_lines = rand.nextInt(bound);//Generate random integers
			
			for(int i=1;i <= number_of_lines;i++)
			{//Write into each file number_of_lines lines
				if(i==1)
				{
					fos.write((i+" - Hello Files!").getBytes());
				}
				else
				{
					fos.write(("\n"+i+" - Hello Files!").getBytes());
				}
			}
			fos.close(); // closing the file
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Creates n files in a new directory "Ex2_1_Files" and write a random amount of lines in each file
	 * @param n - the number of files to create
	 * @param seed - 
	 * @param bound - 
	 * @return an array of the names of the file that were created
	 * @exception IOException
	 */
	public static String[] createTextFiles(int n, int seed, int bound)
	{
		File myDir = new File("Ex2_1_Files");//makes a directory object
		myDir.mkdir();//creates a folder
		File myFile=null; 
		Random rand = new Random(seed);// create instance of Random class
		
		for(int i=1;i<=n;i++)
		{

			try {
				myFile = new File("Ex2_1_Files\\file_"+i+".txt");
				myFile.createNewFile();//creating a new file
				fileWrite(myFile.getAbsolutePath(),rand,bound); // writing a random amount of lines into each file

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return myDir.list(); // an array of the names of the file that were created
		
	}

	/**
	 * Calculating the amount of lines in each file  in a giving file name array and combining it 
	 * @param fileNames - an array of file names that we want to calculate their number of lines
	 * @return the amount of all the lines in  the files in fileNames 
	 * @exception Exception
	 */
	
	public static int getNumOfLines(String[] fileNames)
	{
		int count=0; // initialize a counter that stores the number of the lines in a file   
		for (int i = 0; i < fileNames.length; i++) {
			
			try {
			      // create a new file object
			      File file = new File("Ex2_1_Files\\"+fileNames[i]);
			      // create an object of Scanner
			      // associated with the file
			      Scanner sc = new Scanner(file);
			      // read each line and
			      // count number of lines
			      while(sc.hasNextLine()) {
			        sc.nextLine();
			        count++; 
			       
			      }
			      // close scanner
			      sc.close();
			    } catch (Exception e) {
			    	e.printStackTrace();
			    }
		}
		return count;
	}
	
	
	/**
	 * Creating an arraylist of Ex2_1_Threads and each thread is calculating the amount of lines in its specific given file
	 * and combining the results together.
	 * @param fileNames - an array of file names that we want to calculate their number of lines
	 * @return the amount of all the lines in the files in fileNames
	 * @exception InterruptedException
	 */
	public int getNumOfLinesThreads(String[] fileNames)
	{
		
		ArrayList<Ex2_1_Thread> Thread_List = new ArrayList<Ex2_1_Thread>(); 
		for (int i = 0; i < fileNames.length; i++) {
			
			Thread_List.add(new Ex2_1_Thread(fileNames[i]));
			Thread_List.get(i).start();

		}
		
		try {
			for(Ex2_1_Thread t : Thread_List)
			{
				t.join(); 
			}
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
		return Ex2_1_Thread.Get_Add_Counter();
	}

	/**
	 * Creating an arraylist of Ex2_1_PoolThreads and each thread in the pool is calculating the amount of lines in its specific given file,
	 * and combining the results together.
	 * @param fileNames - an array of file names that we want to calculate their number of lines
	 * @return the amount of all the lines in the files in fileNames
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public int getNumOfLinesThreadPool(String[] fileNames) throws InterruptedException, ExecutionException
	{
		ArrayList<Ex2_1_PoolThread> Thread_List = new ArrayList<Ex2_1_PoolThread>();
		
		for (int i = 0; i < fileNames.length; i++) {
			
			Thread_List.add(new Ex2_1_PoolThread(fileNames[i]));
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);
		List<Future<Integer>> result = executor.invokeAll(Thread_List);
		
		int counter=0;
		for(Future<Integer> future : result)
		{
			counter+= future.get();
		}
		
		executor.shutdown();
		return counter;
	}


}

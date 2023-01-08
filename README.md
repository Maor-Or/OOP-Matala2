# OOP-Matala2

## Part-A:
We have tested the three different methods of handling the calculation of the combined files's amount of lines, in a case of generating 10,000 random text files.
Here are the results: </br></br>
![Capture - SPEED TEST FOR EX2_1](https://user-images.githubusercontent.com/118377261/211208636-fa4857bd-858c-48dc-b184-3203e259cc70.PNG)
</br>
</br>
### Analysis:</br>
As we can see - the normal calculation method took the longest time to finish oparating, while both the Thread-using and the Threadpool-using methods were significantly faster, due to the nature of the thread's multi-tasking.
</br></br>The normal calculation method takes  each file one by one and calculates the amount of lines in the file, and the more files there are, the slower it gets due to it being a one by one proccess. </br></br>
The other two methods are Thread-based solutions which multi-task and thus calculating all the files "simultaneously", and then combining it all to the total amount, and due to that fact, the methods are finishing their calculations much faster.
</br></br> <ins>Note</ins> that we do see a little difference between the two thread-based methods - the second method with uses threads is slightly faster than the threadpool-based method, due to our Implementation (in order to combine all the partial sums coming from each Future type from the threads, we add an extra loop that goes through each thread in the pool for the 3rd method which adds up more time for that method to complete.)</br>

## Class Diagrams:</br>
### Part-A:</br></br>

![ClassDiagramPartA](https://user-images.githubusercontent.com/118377261/211209268-3840e896-a94d-412d-8b38-7007af2c1560.png)
</br></br></br>
### Part-B:</br></br>

![ClassDiagramPartB](https://user-images.githubusercontent.com/118377261/211209278-5c771e54-5283-43b0-b5c1-aaaffd2f4e8e.png)

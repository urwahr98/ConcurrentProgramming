/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrentprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 *
 * @author urwah
 */
public class ConcurrentProgramming {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = 5000; //number of points
        int t = 7;  // number of threads
        int m = 5;  // number of seconds to termination
        int count =0;
        double xaxis,yaxis;
        
        //To stop all the thread if any of the thread fail 20 times
        AtomicBoolean stopCriteria = new AtomicBoolean(false);
        
        //Hashmap to store the coordinates of Points
        HashMap<Double, Double> map = new HashMap<>();
        
        //Arraylist to store the Point created
        ArrayList<Point> pointArr = new ArrayList<>();
        
        //to store the number of success and failure of each thread
        HashMap<String, Integer> success = new HashMap<>();
        HashMap<String, Integer> fail = new HashMap<>();
        
        //Creating n numbers of Point
        while(count < n ){
            Random coor = new Random();
            
            //Creating a Double coordinate with 2 decimal point from range 0.00
            //to 1000.00
            xaxis = (double)coor.nextInt(1001)+((double)coor.nextInt(100)/100);
            yaxis = (double)coor.nextInt(1001)+((double)coor.nextInt(100)/100);
            
            //Checking whether the x-axis, y-axis pair has already existed
            //if exist, ignore
            //if not, insert coordinate in hashmap and create new point
            if (!Objects.equals(map.get(xaxis), yaxis)){
                map.put(xaxis, yaxis);
                pointArr.add(new Point(xaxis, yaxis));
                count++;
            }
        }
        
        //create a pool with t number of threads
        ExecutorService executorService = Executors.newFixedThreadPool(t);
        
        //submit t number of thread into the pool
        for(int i=0; i<t; i++)
            executorService.execute(new Pairing(pointArr, success, fail, stopCriteria));
        
        
        executorService.shutdown();
        // wait for m seconds before terminating
        try {
            executorService.awaitTermination(m, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            System.out.println("Termination interrupted");
        }
        
//        System.out.println(pointArr);
        System.out.println();
        System.out.println(fail);
        System.out.println(success);
        for(String j : fail.keySet()) 
        System.out.println("Thread " + j + " -> "+" number of failure: " + fail.get(j));
        System.out.println();
        for(String j : success.keySet()) 
        System.out.println("Thread " + j + " -> "+" number of Edges created: " + success.get(j));
        
    
    }
}

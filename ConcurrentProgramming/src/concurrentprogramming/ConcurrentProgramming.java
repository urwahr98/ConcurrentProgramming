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
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


/**
 *
 * @author aida
 */
public class ConcurrentProgramming {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int count =0;
        double xaxis,yaxis;
        String points, threads, seconds;
        
        points = JOptionPane.showInputDialog("Enter number of points: ");
		int n = Integer.parseInt(points); //number of points
				
		threads = JOptionPane.showInputDialog("Enter number of threads: ");
		int t = Integer.parseInt(threads); //number of threads
		
		seconds = JOptionPane.showInputDialog("Enter number of seconds to termination: ");
		int m = Integer.parseInt(seconds); //number of seconds to termination
        
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
        
        long timeNow = System.currentTimeMillis();
        executorService.shutdown();
        // wait for m seconds before terminating
        try {
            executorService.awaitTermination(m, TimeUnit.SECONDS);
        } catch (InterruptedException ex) {
            System.out.println("Termination interrupted");
        }
        long timeRunning = System.currentTimeMillis()- timeNow;

        Runnable r = new Runnable() {
            public void run() {
                LineComponent lineComponent = new LineComponent(1000,1000); //GUI
                for(String j : success.keySet()){ //Number of edges created
                    for (int i=1; i<=success.get(j); i++) {
                    lineComponent.addLine();
                }}
                JOptionPane.showMessageDialog(null, lineComponent);
            }
        };
        SwingUtilities.invokeLater(r);
        
//        System.out.println(pointArr);
        System.out.println();
        System.out.println("Thread ran for "+timeRunning+"ms\n");
//        System.out.println(fail);
//        System.out.println(success);
        for(String j : fail.keySet()) 
        System.out.println("Thread " + j + " -> "+" number of failure: " + fail.get(j));
        System.out.println();
        for(String j : success.keySet()) 
        System.out.println("Thread " + j + " -> "+" number of Edges created: " + success.get(j));
        
    
    }
}

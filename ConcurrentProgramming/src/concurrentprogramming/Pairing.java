/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrentprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author urwah
 */
public class Pairing implements Runnable{
    
    ArrayList<Point> pointArr = new ArrayList<>();
    HashMap<String, Integer> success = new HashMap<>();
    HashMap<String, Integer> fail = new HashMap<>();
    AtomicBoolean stopCriteria = new AtomicBoolean(false);
    
    public Pairing(ArrayList<Point> pointArr, HashMap<String, Integer> success, HashMap<String, Integer> fail, AtomicBoolean stopCriteria){
        this.pointArr = pointArr;
        this.success = success;
        this.fail = fail;
        this.stopCriteria = stopCriteria;
    }
    
    @Override
    public void run(){
        
        Random ran = new Random();
        
        //looping while stopCriteria has not been reached
        while(!stopCriteria.get()){
            //randomly get a point 
            int first = ran.nextInt(pointArr.size());
            //check if paired or not
            if (pointArr.get(first).isPair.get()){
                //paired -> increase thread failure number by 1
                if (fail.containsKey(String.valueOf(Thread.currentThread().getId()))){
                    //if the thread has already existed in the hashmap
                    fail.put(String.valueOf(Thread.currentThread().getId()),fail.get(String.valueOf(Thread.currentThread().getId()))+1);
                    //check if failure number reached 20
                    if(fail.get(String.valueOf(Thread.currentThread().getId())) >= 20){
                            stopCriteria.set(true);
                        }
                }else{
                    //if the thread is not in the hashmap, put it as key with value 1
                    fail.put(String.valueOf(Thread.currentThread().getId()),1);
                }
            }else {
                //set the status of the first point as pair
                pointArr.get(first).isPair.set(true);
                boolean loop = true;
                //loop to find an unpaired second point
                while(loop || !stopCriteria.get()){
                    //added sleep to give chance to other thread to work
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        System.out.println("Sleep Interrupted");
                    }
                    //randomly get a second point
                    //same algorithm as above
                    int second = ran.nextInt(pointArr.size());
                    if (pointArr.get(second).isPair.get()){                          
                        if (fail.containsKey(String.valueOf(Thread.currentThread().getId()))){
                            fail.put(String.valueOf(Thread.currentThread().getId()),fail.get(String.valueOf(Thread.currentThread().getId()))+1);
                            if(fail.get(String.valueOf(Thread.currentThread().getId())) >= 20){
                                stopCriteria.set(true);
                                loop=false;
                            }
                        }else{
                            fail.put(String.valueOf(Thread.currentThread().getId()),1);
                    }
                    }else{
                        // set the second point's status as paired
                        pointArr.get(second).isPair.set(true);
//                        System.out.println("Thread "+Thread.currentThread().getId()+" created an edge!");
                        //increase thread successful number by 1
                        //break out of the loop
                        if (success.containsKey(String.valueOf(Thread.currentThread().getId()))){
                            success.put(String.valueOf(Thread.currentThread().getId()),success.get(String.valueOf(Thread.currentThread().getId()))+1);
                            loop=false;
                        }else{
                            success.put(String.valueOf(Thread.currentThread().getId()),1);
                            loop=false;
                    }

                    }
                
            }
        }
        
    }
    
}
}

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
            int first = ran.nextInt(pointArr.size());
            //Get a random point from the arraylist
            //if paired, +1 to fail number of the thread
            //if not paired yet, change to paired
            //get another random point
            //if not paired yet, change to paired
            // +1 to succesful number of the thread
            //if paired, +1 to failure number of the thread
            //find another point to pair with
            if (pointArr.get(first).isPair.get()){
                if (fail.containsKey(String.valueOf(Thread.currentThread().getId()))){
                    fail.put(String.valueOf(Thread.currentThread().getId()),fail.get(String.valueOf(Thread.currentThread().getId()))+1);
                    if(fail.get(String.valueOf(Thread.currentThread().getId())) >= 20){
                            stopCriteria.set(true);
                        }
                }else{
                    fail.put(String.valueOf(Thread.currentThread().getId()),1);
                }
            }else {
                pointArr.get(first).isPair.set(true);
                boolean loop = true;
                while(loop){
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
                        pointArr.get(second).isPair.set(true);
                        System.out.println("Thread "+Thread.currentThread().getId()+" paired Successfully!");
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

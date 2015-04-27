/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package w14c2080asg2_5_pak_stan;

/**
 *
 * @author Stan
 */
public class Shipment<T> implements Runnable{
    BoundedQueueInterface<T> pipe;
    BoundedQueueInterface<T> pipe2;
    int iterations;
    
    public Shipment(BoundedQueueInterface pipe, BoundedQueueInterface pipe2, int iter){
        this.pipe = pipe;
        this.pipe2 = pipe2;
        this.iterations = iter;
    }
    public Shipment(){
        
    }
    public void shipIt(){
        T obj = pipe.dequeue();
        System.out.println("Packing :: Pulled from production queue and packed : " + obj.toString());
        pipe2.enqueue(obj);
    }
    public void run(){
        for(int i = 1;i <= iterations;i++){
            shipIt();
            try{Thread.sleep(150*5);}catch(InterruptedException intexc){}
        }
        
    }
}

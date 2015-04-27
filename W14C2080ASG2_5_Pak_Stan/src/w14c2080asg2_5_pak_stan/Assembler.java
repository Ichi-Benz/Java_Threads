package w14c2080asg2_5_pak_stan;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stan
 */
public class Assembler<T> implements Runnable{
    private BoundedQueueInterface<T> pipe;
    private BoundedQueueInterface<T> pipe2;
    private int iterations;
    
    public Assembler(BoundedQueueInterface pipe, BoundedQueueInterface pipe2, int iterations){
        this.pipe = pipe;
        this.pipe2 = pipe2;
        this.iterations = iterations;
    }
    public Assembler(){
        
    }
    public void assemble(){
        T obj = pipe.dequeue();
        System.out.println("Production -- Phase A :: Pulled from order queue : " + obj.toString());
        pipe2.enqueue(obj);
        System.out.println("Production -- Phase B :: Inserted into production queue : " + obj.toString());
    }
    public void run(){
        for(int i = 1;i <= iterations;i++){
            assemble();
            try{Thread.sleep(150*20);}catch(InterruptedException intexc){}
        }
    }
}

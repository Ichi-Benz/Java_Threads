/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package w14c2080asg2_5_pak_stan;
/**
 *
 * @author Stan
 */

import java.util.Random;

public class OrderGenerator<T> implements Runnable{
    private BoundedQueueInterface<T> pipe;
    private int iterations;
    static Random random = new Random();
    public OrderGenerator(BoundedQueueInterface q, int ite){
        pipe = q;
        iterations = ite;
    }
    public void GenerateOrder(){
        Product[] products = new Product[10];
        
        products[0] = new Product("dish washer", 450.00f);
        products[1] = new Product("vacuum cleaner", 85.50f);
        products[2] = new Product("electric oven", 399.99f);
        products[3] = new Product("tabletop oven", 120.00f);
        products[4] = new Product("washing machine", 499.99f);
        products[5] = new Product("microwave oven", 150.99f);
        products[6] = new Product("alarm clock", 15.99f);
        products[7] = new Product("blender", 39.99f);
        products[8] = new Product("toaster oven", 61.99f);
        products[9] = new Product("rice cooker", 34.00f);
        
        int value = random.nextInt(10);
        pipe.enqueue((T)products[value]);
        
        System.out.println("Orders :: Inserted into orders queue : " + products[value].toString());
    }
    @Override
    public void run(){
        for(int n=1;n <= iterations;n++){
            GenerateOrder();
            try{Thread.sleep(150);}catch(InterruptedException exc){}
        }
    }
    
}

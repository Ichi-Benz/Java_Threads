/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package w14c2080asg2_5_pak_stan;
import java.util.Scanner;
/**
 *
 * @author Stan
 */
public class W14C2080ASG2_5_Pak_Stan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BoundedQueueInterface<Integer> orders;
        BoundedQueueInterface<Integer> production;
        BoundedQueueInterface<Integer> shipment;
        int pipeSize = 50;
        String iterations = "";
        
        Scanner input = new Scanner(System.in);
        
        //Queues
        orders = new EncapQueue<>(pipeSize);
        production = new EncapQueue<>(pipeSize);
        shipment = new EncapQueue<>(pipeSize);
        //Prompt
        System.out.println("How many orders would you like to make? (# to break)");
        iterations = input.nextLine();
        while(!iterations.matches("^[0-9]+$") && !iterations.matches("#")){
            if(iterations.matches("^#"))
                break;
            System.out.println("!!Incorrect input, input must be ranging from 0-9!!");
            System.out.println("How many orders would you like to make? (# to break)");
            iterations = input.nextLine();
        }
        while(!iterations.matches("#")){
            //Runnable
            Runnable generate = new OrderGenerator(orders, Integer.parseInt(iterations));
            Runnable assemble = new Assembler(orders, production, Integer.parseInt(iterations));
            Runnable ship = new Shipment(production, shipment, Integer.parseInt(iterations));
            //Threads
            Thread generateT = new Thread(generate);
            Thread assembleT = new Thread(assemble);
            Thread shipT = new Thread(ship);
            //Start thread
            generateT.start();  assembleT.start();  shipT.start();
            //Run thread
            try{
                generateT.join();   assembleT.join();   shipT.join();   

                if(shipT.getState() == Thread.State.TERMINATED){
                    System.out.println("\nShipment Details :: Shipment Finished");
                    System.out.println("The following are shipped: \n");
                    for(int i =1;i <= Integer.parseInt(iterations);i++){
                        System.out.println(shipment.dequeue());
                    }
                }
                //Prompt
                System.out.println("How many orders would you like to make? (# to break)");
                iterations = input.nextLine();
                while(!iterations.matches("^[0-9]+$") && !iterations.matches("#")){
                    if(iterations.matches("#"))
                        break;
                    System.out.println("!!Incorrect input, input must be ranging from 0-9!!");
                    System.out.println("How many orders would you like to make? (# to break)");
                    iterations = input.nextLine();
                }
            }catch(InterruptedException intexc){
                System.out.println("Warning!");
                System.out.println("Thread attempted to interrupt current thread.");
            }
        }
    }
}

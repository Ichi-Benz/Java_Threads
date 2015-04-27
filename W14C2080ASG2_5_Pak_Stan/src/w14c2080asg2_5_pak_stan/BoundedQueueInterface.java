/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package w14c2080asg2_5_pak_stan;

/**
 *
 * @author Stan
 */
public interface BoundedQueueInterface<T> extends UnboundedQueueInterface<T>{
    void enqueue(T element) throws QueueOverflowException;
    
    boolean isFull();
}

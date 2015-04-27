/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package w14c2080asg2_5_pak_stan;

/**
 *
 * @author Stan
 */
public class EncapQueue<T> implements BoundedQueueInterface<T>{
    protected final int FIXCAP = 100;
    protected T[] queue;
    protected int numElements = 0;
    protected int front = 0;
    protected int rear;
    
    public EncapQueue(){
        queue = (T[])new Object[FIXCAP];
        rear = FIXCAP - 1;
    }
    public EncapQueue(int maxSize){
        queue = (T[]) new Object[maxSize];
        rear = maxSize - 1;
    }
    public synchronized void enqueue(T element){
        if(isFull())
            enlarge();
        while(isFull()){
            try{
                wait();
            }
            catch(InterruptedException inter){
                return;
            }
        }
        rear = (rear + 1) % queue.length;
        queue[rear] = element;
        numElements += 1;
        notifyAll();
    }
    public synchronized T dequeue(){
        while(isEmpty()){
            try{
                wait();
            }
            catch(InterruptedException inter){
                
            }
        }
        T toReturn = queue[front];
        queue[front] = null;
        front = (front + 1) % queue.length;
        numElements -= 1;
        notifyAll();
        return toReturn;
    }
    private void enlarge(){
        T[] expand = (T[])new Object[queue.length + FIXCAP];
        
        int currSmaller = front;
        for(int currLarger = 0;currLarger < numElements;currLarger++){
            expand[currLarger] = queue[currSmaller];
            currSmaller = (currSmaller + 1) % queue.length;
            
            queue = expand;
            front = 0;
            rear = numElements - 1;
        }
    }
    public boolean isEmpty(){
        return(numElements == 0);
    }
    public boolean isFull(){
        return (numElements == queue.length);
    }
}

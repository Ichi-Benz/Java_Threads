/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package w14c2080asg2_5_pak_stan;

/**
 *
 * @author Stan
 */
public class QueueOverflowException extends RuntimeException{
    public QueueOverflowException(){
        super();
    }
    public QueueOverflowException(String message){
        super(message);
    }
}

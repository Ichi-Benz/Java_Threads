/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package w14c2080asg2_5_pak_stan;

/**
 *
 * @author Home
 */
public class Product {
    private String name;
    private float price;
    
    public Product(String n, float p){
        name = n;
        price = p;
    }

    public Product() {
        
    }
    @Override
    public String toString(){
        return "Name : " + name + ", Price: " + price;
    }        
}

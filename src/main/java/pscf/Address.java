/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pscf;

/**
 *
 * @author Lucas Rosa at https://github.com/Zetrom18
 */
public class Address {
    private final int w;
    private final int s;
    private final int t;
    private final int r;
    
    public Address(int addr){
        this.w = addr & 0b111111;
        this.s = addr>>6;
        this.t = s & 0b11111111111;
        this.r = s>>11;
    }
    
    public int getW(){ return w; }
    
    public int getS(){ return s; }
    
    public int getT(){ return t; }
    
    public int getR(){ return r; }
    
}

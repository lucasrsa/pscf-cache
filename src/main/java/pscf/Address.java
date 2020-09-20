/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

/**
 * @author Lucas Rosa at https://github.com/Zetrom18
 */
public class Address {
    private final int w;
    private final int s;
    private final int t;
    private final int r;
    private final int cleanAddr;

    public Address(int addr) {
        this.cleanAddr = addr;
        this.w = addr & 0b111111;
        this.s = addr >> 6;
        this.r = s & 0b1111111;
        this.t = s >> 7 ;

    }

    // 2 é adicionado para suportar os campos de "tag"[0] e "modif"[1]
    public int getW() {
        return this.w + 2;
    }

    public int getS() {
        return this.s;
    }

    public int getT() {
        return this.t;
    }

    public int getR() {
        return this.r;
    }

    public int getCleanAddr(){
        return  this.cleanAddr;
    }
}

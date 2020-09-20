/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

public class Address {
    private final int w;
    private final int s;
    private final int t;
    private final int r;

    //nessa classe address fazemos as contas em bits para calcular o w, s, r, t
    public Address(int addr) {
        this.cleanAddr = addr;
        this.w = addr & 0b111111; // aqui pegamos os 6 ultimos bits
        this.s = addr >> 6; // ai o S tira todos os ultimos 6 bits do addr
        this.r = s & 0b1111111; // ai o r pega os 7 bits do final do s
        this.t = s >> 7 ; // ai o t retira os ultimos 7 bits e fica oq sobra

    }

    // + 2 é adicionado para suportar os campos de "tag"[0] e "modif"[1]
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

}

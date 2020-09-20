/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

class InvalidAddress extends Exception {
    private final int address;

    InvalidAddress() {
        super();
        this.address = -1;
    }

    InvalidAddress(int address) {
        super("invalid address");
        this.address = address;
    }

    public int getAddress() {
        return address;
    }
}

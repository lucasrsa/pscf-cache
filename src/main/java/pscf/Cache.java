/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Cache {
    private final int wordCount;
    private final int blockCount;
    private final int blockSize;
    private final RAM ram;
    private long[][] mem = null;
    private final IO io;

    public Cache(int wordCount, int blockCount, RAM ram, IO io) { // wordCount == K palavras | blockCount == m blocos
        this.wordCount = wordCount;
        this.blockCount = blockCount;
        this.blockSize = 2 + wordCount; // 2 é adicionado para suportar os campos de "tag"[0] e "modif"[1]
        this.ram = ram;
        this.mem = new long[blockCount][blockSize];

        for (int i = 0; i < blockCount; ++i) {
            this.mem[i][0] = -1;
            this.mem[i][1] = 0;
        }
        this.io = io;
    }

    public long Get(int x) throws InvalidAddress {
        Address addr = new Address(x);

        if (!this.CheckAddr(addr)) {
            this.io.Write("Cache miss...");
            this.Load(addr);
        }
        return this.mem[addr.getR()][addr.getW()];
    }

    // Seta valor na cache e flaga a linha como edita
    public void Set(int addr, int word) throws InvalidAddress {
        Address address = new Address(addr);
        //lança erro se o endreço não existir
        if (!this.CheckAddr(address)) {
            this.io.Write("Cache miss...");
            this.Load(address);
        }
        //flaga a linha como editada
        this.mem[address.getR()][1] = 1;
        this.mem[address.getR()][address.getW()] = word;
    }

    private void Load(Address addr) throws InvalidAddress {
        final int startAddr = addr.getS() * this.wordCount;

        if (this.mem[addr.getR()][1] == 1) {
            //blockSize - 2 pois temos que desconsiderar os t e a flag
           this.ram.Set((int) (this.mem[addr.getR()][0] << 7 | addr.getR())*this.wordCount, this.wordCount , this.mem[addr.getR()]);

        }
        System.arraycopy(this.ram.Get(startAddr, this.wordCount), 0, this.mem[addr.getR()], 2, this.wordCount);
        this.mem[addr.getR()][0] = addr.getT();
        this.mem[addr.getR()][1] = 0;
    }

    private boolean CheckAddr(Address addr) {
        return (this.mem[addr.getR()][0] == addr.getT());
    }
}

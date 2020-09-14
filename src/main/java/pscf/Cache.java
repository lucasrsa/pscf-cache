/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pscf;

public class Cache {
    private final int wordCount;
    private final int blockCount;
    private final int blockSize;
    private final RAM ram;
    private long [][] mem = null;
    
    public Cache(int wordCount, int blockCount, RAM ram){ // wordCount == K palavras | blockCount == m blocos
        this.wordCount = wordCount;
        this.blockCount = blockCount;
        this.blockSize = 2 + wordCount; // 2 é adicionado para suportar os campos de "tag"[0] e "modif"[1]
        this.ram = ram;
        this.mem = new long[blockCount][blockSize];
        
        for(int i = 0; i < blockCount; ++i){
            this.mem[i][0] = -1;
            this.mem[i][1] = 0;
        }
    }
    
    public long Get(int x) throws InvalidAddress {
        Address addr = new Address(x);
        
        if(!CheckAddr(addr)){
            System.out.println("Cache miss...");
            Load(addr);
        }
        return this.mem[addr.getR()][addr.getW()];
    }
    
    public void Set(int addr, int word) throws InvalidAddress {
        this.ram.Set(addr, word);
    }
    
    private void Load(Address addr) throws InvalidAddress{
        final int startAddr = addr.getR() * this.wordCount;
        System.arraycopy(this.ram.Get(startAddr, this.wordCount), 0, this.mem[addr.getR()], 2, this.wordCount);
        this.mem[addr.getR()][0] = addr.getT();
        this.mem[addr.getR()][1] = 0;
    }

    private boolean CheckAddr(Address addr){
        return (this.mem[addr.getR()][0] == addr.getT());
    }
}

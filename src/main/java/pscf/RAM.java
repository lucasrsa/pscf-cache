package pscf;

import java.util.Random;

public class RAM {
    private long [] mem = null;
    private int size = 0;

    public RAM(int size) {
        System.out.println(size);
        this.size = size;
        this.mem = new long[size];
    }

    public int Size() { return this.size; }

    public void Initialyze() {

        Random gerador = new Random();

        for(int i=0; i<this.size; ++i){
            this.mem[i] = gerador.nextLong();
        }
    }

    public long Get(int address) throws InvalidAddress {
        CheckAddress(address);
        return mem[address];
    }

    public long[] Get(int address, int size) throws InvalidAddress {
        long [] block = new long[size];
        CheckAddress(address);          // This function throws InvalidAddress
        CheckAddress(address+size-1);   // This function throws InvalidAddress
        System.arraycopy(mem, address, block, 0, size);
        return block;
    }

    public void Set(int address, int word) throws InvalidAddress {
        CheckAddress(address);
        mem[address] = word;
    }

    private void CheckAddress(int address) throws InvalidAddress {
        if (address < 0 || address >= this.size)
            throw new InvalidAddress(address);
    }
}

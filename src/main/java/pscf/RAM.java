package pscf;

public class RAM {
    private long[] mem = null;
    private int size = 0;

    public RAM(int size) {
        System.out.println(size);
        this.size = size;
        this.mem = new long[size];
    }

    public int Size() {
        return this.size;
    }

    public void Initialyze() {
        for (int i = 0; i < this.size; ++i) {
            this.mem[i] =  i;
        }
    }

    public long Get(int address) throws InvalidAddress {
        CheckAddress(address);
        return mem[address];
    }

    public long[] Get(int address, int size) throws InvalidAddress {
        long[] block = new long[size];
        CheckAddress(address);          // This function throws InvalidAddress
        CheckAddress(address + size - 1);   // This function throws InvalidAddress
        System.arraycopy(mem, address, block, 0, size);
        return block;
    }

    public void Set(int address, int word) throws InvalidAddress {
        CheckAddress(address);
        mem[address] = word;
    }

    public void Set(int address, int size, long[] block) throws InvalidAddress {
        CheckAddress(address);          // This function throws InvalidAddress
        CheckAddress(address + size - 1);   // This function throws InvalidAddress
        //Começa da pos 2 para pular os valore de t e da flag
        System.arraycopy(block, 2, mem, address, size);

    }


    private void CheckAddress(int address) throws InvalidAddress {
        if (address < 0 || address >= this.size)
            throw new InvalidAddress(address);
    }
}

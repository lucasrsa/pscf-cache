package pscf;

public class CPU {
    private Cache cache = null;
    private IO io = null;
    private int PC = 0;

    public CPU(Cache c, IO es) {
        this.cache = c;
        this.io = es;
    }

    public void Run(int x) throws InvalidAddress {
        io.Write(x + " -> " + cache.Get(x));
        // cache.Set(x, cache.Get(x) - 10);
        // io.Write(x + " <- " + cache.Get(x));
    }
}

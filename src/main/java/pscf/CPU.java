package trabalho;

import java.util.Random;

public class CPU {
    private Cache cache = null;
    private IO io = null;
    private int PC = 0;

    public CPU(Cache c, IO es) {
        this.cache = c;
        this.io = es;
    }

    public void Run(int x) throws InvalidAddress {


        Random generator = new Random();
        int aux;

        for (int i = 0; i < 120; ++i) {


            aux = generator.nextInt((int) Math.pow(2, 24));

            io.Write(aux + " -> " + cache.Get(aux));
            cache.Set(aux, -10);
            io.Write(aux + " -< " + cache.Get(aux));

            ++aux;
            io.Write(aux + " -> " + cache.Get(aux));
            ++aux;
            io.Write(aux + " -> " + cache.Get(aux));
            ++aux;
            io.Write(aux + " -> " + cache.Get(aux));
            ++aux;
            io.Write(aux + " -> " + cache.Get(aux));
            ++aux;
            io.Write(aux + " -> " + cache.Get(aux));
            ++aux;
            io.Write(aux + " -> " + cache.Get(aux));
            ++aux;
            io.Write(aux + " -> " + cache.Get(aux));
            ++aux;
            io.Write(aux + " -> " + cache.Get(aux));
            ++aux;
            io.Write(aux + " -> " + cache.Get(aux));
            ++aux;
            io.Write(aux + " -> " + cache.Get(aux));
        }


    }
}

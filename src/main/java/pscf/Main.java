package trabalho;

public class Main {

    public static void main(String[] args) throws InvalidAddress {
        IO es = new IO(System.out);

        RAM ram = new RAM((int) Math.pow(2, 24)); // 16MB == 2^4 * 2^20 == 2^24
        ram.Initialyze();

        Cache cache = new Cache(64, 2048, ram, es);

        CPU cpu = new CPU(cache, es);
        cpu.Run(120);

    }
}

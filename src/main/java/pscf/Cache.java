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
            this.mem[i][0] = -1; //comeca na cache a tag com -1
            this.mem[i][1] = 0; //comeca na cache a mod com 0
        }
        this.io = io;
    }

    public long Get(int x) throws InvalidAddress {
        Address addr = new Address(x);

        if (!this.CheckAddr(addr)) {     //checamos se nao for o endereco, se n for damos cache miss e fazemos o load da memoria principsim al
            this.io.Write("Cache miss...");
            this.Load(addr); // faz o load da memoria principal
        }
        return this.mem[addr.getR()][addr.getW()];
    }

    // Seta valor na cache e marca a flag como editado
    public void Set(int addr, int word) throws InvalidAddress {
        Address address = new Address(addr);
        //lança erro se o endreço não existir
        if (!this.CheckAddr(address)) {
            this.io.Write("Cache miss...");
            this.Load(address); // faz o load da memoria principal
        }
        //marca a flag da linha como editada
        this.mem[address.getR()][1] = 1;
        this.mem[address.getR()][address.getW()] = word; // salvando a palavra na cache
    }

    private void Load(Address addr) throws InvalidAddress { // faz o load na memoria principal
        final int startAddr = addr.getS() * this.wordCount; // acahamos o endereco de inicio do bloco que devera ser carregado

        if (this.mem[addr.getR()][1] == 1) {  // se ja foi editado, ou seja o [1] na linha da cache line mod(modificacao)
            //a parte que foi multiplicado pelo wordCount e feito para achar o S
           this.ram.Set((int) (this.mem[addr.getR()][0] << 7 | addr.getR())*this.wordCount, this.wordCount , this.mem[addr.getR()]);
           //explicando a conta: pega o t` carregado na cache (indexado de 0) referente a linha que sera substituida
        }
        //aqui estamos carregando o blloco de memoria contendo o addr levando para a cache
        System.arraycopy(this.ram.Get(startAddr, this.wordCount), 0, this.mem[addr.getR()], 2, this.wordCount);
        this.mem[addr.getR()][0] = addr.getT(); // setando as flags
        this.mem[addr.getR()][1] = 0; // setando as flags
    }

    private boolean CheckAddr(Address addr) {
        return (this.mem[addr.getR()][0] == addr.getT());
    } // checando se o load sera necessario
}

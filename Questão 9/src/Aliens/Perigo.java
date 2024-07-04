package Aliens;

import java.time.LocalDateTime;

public class Perigo extends Alien {
    public Perigo(int id, String nome, String especie, LocalDateTime dataEntrada, int periculosidade) {
        super(id, nome, especie, dataEntrada, periculosidade);
    }

    @Override
    public int avaliarPericulosidade() {
        return getPericulosidade();
    }
}
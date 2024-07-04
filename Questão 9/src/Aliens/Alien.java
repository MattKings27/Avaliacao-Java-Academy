package Aliens;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Alien {
    private int id;
    private String nome;
    private String especie;
    private LocalDateTime dataEntrada;
    private int periculosidade;

    public Alien(int id, String nome, String especie, LocalDateTime dataEntrada, int periculosidade) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.dataEntrada = dataEntrada;
        this.periculosidade = periculosidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public int getPericulosidade() {
        return periculosidade;
    }

    public String getDataEntradaFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataEntrada.format(formatter);
    }

    public abstract int avaliarPericulosidade();

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "ID: " + id +
                "\nNome: " + nome +
                "\nEspécie: " + especie +
                "\nData/Hora de Entrada: " + dataEntrada.format(formatter) +
                "\nPericulosidade: " + periculosidade;
    }
}


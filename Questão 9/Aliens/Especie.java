package Aliens;

public abstract class Especie {
    private String nome;
    private String planetaOrigem;
    private int nivelPericulosidadeBase;

    public Especie(String nome, String planetaOrigem, int nivelPericulosidadeBase) {
        this.nome = nome;
        this.planetaOrigem = planetaOrigem;
        this.nivelPericulosidadeBase = nivelPericulosidadeBase;
    }

    public String getNome() {
        return nome;
    }

    public String getPlanetaOrigem() {
        return planetaOrigem;
    }

    public int getNivelPericulosidadeBase() {
        return nivelPericulosidadeBase;
    }

    @Override
    public String toString() {
        return "Espécie: " + nome +
                "\nPlaneta de Origem: " + planetaOrigem +
                "\nNível de Periculosidade Base: " + nivelPericulosidadeBase;
    }
}

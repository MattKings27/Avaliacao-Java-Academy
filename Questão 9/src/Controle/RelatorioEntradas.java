package Controle;

import Aliens.Alien;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class RelatorioEntradas {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void gerarRelatorio(List<Alien> alienigenas, Quarentena quarentena) {
        System.out.println("\nRelatório de Entradas de Alienígenas:");

        for (Alien alien : alienigenas) {
            boolean emQuarentena = quarentena.getAlienigenasQuarentena().contains(alien);
            System.out.println("ID: " + alien.getId());
            System.out.println("Nome: " + alien.getNome());
            System.out.println("Espécie: " + alien.getEspecie());
            System.out.println("Data/Hora de Entrada: " + alien.getDataEntrada().format(formatter));
            System.out.println("Periculosidade: " + alien.avaliarPericulosidade());
            System.out.println("Mover para quarentena? " + (emQuarentena ? "Sim" : "Não"));
            System.out.println("-------------");
        }
    }
}
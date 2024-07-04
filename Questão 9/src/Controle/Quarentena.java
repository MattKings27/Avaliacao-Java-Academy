package Controle;

import Aliens.Perigo;

import java.util.ArrayList;
import java.util.List;

public class Quarentena {
    private List<Perigo> alienigenasQuarentena;

    public Quarentena() {
        this.alienigenasQuarentena = new ArrayList<>();
    }

    public void adicionarAlienQuarentena(Perigo alien) {
        alienigenasQuarentena.add(alien);
    }

    public void removerAlienQuarentena(Perigo alien) {
        alienigenasQuarentena.remove(alien);
    }

    public List<Perigo> getAlienigenasQuarentena() {
        return alienigenasQuarentena;
    }
}
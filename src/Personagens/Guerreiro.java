package Personagens;

public class Guerreiro extends Personagem {
    public Guerreiro(String n) { super(n, 40, 8, 6); }
    public Guerreiro(Guerreiro g) { super(g); }
}
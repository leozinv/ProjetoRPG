package Personagens;

public class Inimigo extends Personagem {
    public Inimigo(String n, int hp, int atk, int def) { super(n, hp, atk, def); }
    public Inimigo(Inimigo i) { super(i); }
}
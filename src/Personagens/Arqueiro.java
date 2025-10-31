package Personagens;

public class Arqueiro extends Personagem {
    public Arqueiro(String n) { super(n, 32, 9, 5); }
    public Arqueiro(Arqueiro a) { super(a); }
}
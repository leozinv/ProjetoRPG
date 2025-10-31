package Personagens;

import Itens.*;
import java.util.Random;

public abstract class Personagem {
    public String nome;
    public int hp, atk, def, nivel;
    public Inventario inv = new Inventario();
    public Random dado = new Random();

    public Personagem(String nome, int hp, int atk, int def) {
        this.nome = nome;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.nivel = 1;
    }

    public Personagem(Personagem outro) {
        this.nome = outro.nome;
        this.hp = outro.hp;
        this.atk = outro.atk;
        this.def = outro.def;
        this.nivel = outro.nivel;
        this.inv = outro.inv.cloneInv();
    }

    public boolean estaVivo() { return hp > 0; }

    public int rolarDado() { return 1 + dado.nextInt(6); }

    public boolean batalhar(Personagem inimigo) {
        System.out.println("\nCombate iniciado contra " + inimigo.nome + "!");
        boolean turnoJogador = true;
        while (estaVivo() && inimigo.estaVivo()) {
            int dAtk = rolarDado();
            int dDef = rolarDado();

            if (turnoJogador) {
                int dano = Math.max(0, (atk + dAtk) - (inimigo.def + dDef));
                inimigo.hp -= dano;
                System.out.println(nome + " causou " + dano + " de dano! (HP inimigo: " + Math.max(0, inimigo.hp) + ")");
            } else {
                int dano = Math.max(0, (inimigo.atk + dAtk) - (def + dDef));
                hp -= dano;
                System.out.println(inimigo.nome + " te acertou em " + dano + " de dano! (Seu HP: " + Math.max(0, hp) + ")");
            }
            turnoJogador = !turnoJogador;
        }
        return estaVivo();
    }

    public void aplicarEfeito(Item item) {
        switch (item.efeito) {
            case CURA -> { hp += item.poder; System.out.println(nome + " curou " + item.poder + " HP!"); }
            case BUFF_ATAQUE -> { atk += item.poder; System.out.println(nome + " ganhou +" + item.poder + " de ataque!"); }
            case BUFF_DEFESA -> { def += item.poder; System.out.println(nome + " ganhou +" + item.poder + " de defesa!"); }
            default -> {}
        }
    }
}
package Itens;

import Jogo.Util.TipoEfeito;
import java.util.*;

public class Item implements Comparable<Item> {
    public String nome, descricao;
    public TipoEfeito efeito;
    public int poder, quantidade;

    public Item(String nome, String desc, TipoEfeito efeito, int poder, int qtd) {
        this.nome = nome;
        this.descricao = desc;
        this.efeito = efeito;
        this.poder = poder;
        this.quantidade = qtd;
    }

    public Item(Item outro) {
        this(outro.nome, outro.descricao, outro.efeito, outro.poder, outro.quantidade);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Item i)) return false;
        return nome.equalsIgnoreCase(i.nome) && efeito == i.efeito && poder == i.poder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase(), efeito, poder);
    }

    @Override
    public int compareTo(Item outro) {
        return nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public String toString() {
        return nome + " (x" + quantidade + ") - " + descricao;
    }
}
package Itens;

import java.util.*;

public class Inventario {
    Map<Item, Item> itens = new HashMap<>();

    public void adicionar(Item item) {
        Item existente = itens.get(item);
        if (existente != null) {
            existente.quantidade += item.quantidade;
        } else {
            itens.put(item, item);
        }
    }

    public boolean usar(Item item) {
        Item e = itens.get(item);
        if (e == null || e.quantidade <= 0) return false;
        e.quantidade--;
        if (e.quantidade == 0) itens.remove(e);
        return true;
    }

    public boolean vazio() { return itens.isEmpty(); }

    public List<Item> listar() {
        List<Item> lista = new ArrayList<>(itens.values());
        Collections.sort(lista);
        return lista;
    }

    public Inventario cloneInv() {
        Inventario novo = new Inventario();
        for (Item i : listar()) novo.adicionar(new Item(i));
        return novo;
    }

    @Override
    public String toString() {
        if (itens.isEmpty()) return "(nenhum item)";
        StringBuilder sb = new StringBuilder();
        for (Item i : listar()) sb.append(" - ").append(i).append("\n");
        return sb.toString();
    }
}
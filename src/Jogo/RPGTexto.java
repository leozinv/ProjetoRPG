package Jogo;

import Personagens.*;
import Itens.*;
import Jogo.Util.TipoEfeito;
import java.util.*;

// Trabalho: RPG de Texto em Java (POO)
// Autor: Leonardo Vicente - 24007335 && Yago Sousa - 24015586


public class RPGTexto implements Cloneable {

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    public static void main(String[] args) {
        introducaoHistoria();

        Personagem jogador = criarPersonagem();
        inicializarItens(jogador);
        Personagem save = copiar(jogador);

        System.out.println("\nVocê desperta com determinação e parte em sua jornada...");

        while (jogador.estaVivo()) {
            System.out.println("\n--- MENU ---");
            System.out.println("1) Explorar");
            System.out.println("2) Usar item");
            System.out.println("3) Ver inventário");
            System.out.println("4) Salvar progresso");
            System.out.println("5) Carregar save");
            System.out.println("6) Fugir");
            System.out.println("0) Sair");
            System.out.print("Escolha: ");
            int op = lerInt();

            switch (op) {
                case 1 -> explorar(jogador);
                case 2 -> usarItem(jogador);
                case 3 -> mostrarInventario(jogador);
                case 4 -> { save = copiar(jogador); System.out.println("💾 Jogo salvo!"); }
                case 5 -> { jogador = copiar(save); System.out.println("📂 Save carregado!"); }
                case 6 -> tentarFugir(jogador);
                case 0 -> { System.out.println("Encerrando jogo..."); return; }
                default -> System.out.println("Opção inválida!");
            }
        }
        System.out.println("\n☠️  Você foi derrotado... o mundo ficará sem o seu herói.");
    }


    static void introducaoHistoria() {
        System.out.println("======================================");
        System.out.println("      🌄 REINOS DE ALTHERION 🌄");
        System.out.println("======================================");
        System.out.println("\nVocê desperta em meio a uma floresta silenciosa, cercado por névoa.");
        System.out.println("Rumores falam sobre a ascensão do Lorde Sombrio, que ameaça dominar o reino.");
        System.out.println("Cabe a você, aventureiro, impedir que as trevas tomem Altherion.");
        System.out.println("Mas cuidado... cada caminho esconde perigo e glória em igual medida.\n");
    }

    static Personagem criarPersonagem() {
        System.out.println("Escolha sua classe:");
        System.out.println("1) 🛡️ Guerreiro - forte e resistente");
        System.out.println("2) 🔮 Mago - domina a arte dos feitiços");
        System.out.println("3) 🏹 Arqueiro - rápido e preciso");
        int op = lerInt();
        System.out.print("Nome do personagem: ");
        String nome = sc.nextLine();
        return switch (op) {
            case 2 -> new Mago(nome);
            case 3 -> new Arqueiro(nome);
            default -> new Guerreiro(nome);
        };
    }

    static void inicializarItens(Personagem p) {
        p.inv.adicionar(new Item("Poção de Cura", "Restaura 10 HP", TipoEfeito.CURA, 10, 2));
        p.inv.adicionar(new Item("Casco de Tartaruga", "Aumenta defesa", TipoEfeito.BUFF_DEFESA, 2, 1));
        p.inv.adicionar(new Item("Dente de Lobo", "Aumenta ataque", TipoEfeito.BUFF_ATAQUE, 2, 1));
    }


    static void explorar(Personagem p) {
        System.out.println("\nVocê segue por um caminho misterioso...");
        System.out.println("1) Entrar na caverna escura");
        System.out.println("2) Caminhar até a vila abandonada");
        System.out.println("3) Seguir pela trilha no bosque");
        System.out.print("Escolha seu caminho: ");
        int escolha = lerInt();

        switch (escolha) {
            case 1 -> explorarCaverna(p);
            case 2 -> explorarVila(p);
            case 3 -> explorarBosque(p);
            default -> System.out.println("Você se perde um pouco, mas volta ao ponto inicial...");
        }
    }

    static void explorarCaverna(Personagem p) {
        System.out.println("\n🕳️ A caverna ecoa sons estranhos...");
        if (rand.nextBoolean()) {
            Inimigo inimigo = new Inimigo("Troll das Sombras", 25, 7, 5);
            inimigo.inv.adicionar(new Item("Cristal Rachado", "Usado em antigas magias", TipoEfeito.BUFF_ATAQUE, 3, 1));
            if (p.batalhar(inimigo)) {
                System.out.println("Você derrotou o Troll e encontrou um tesouro antigo!");
                p.inv.adicionar(new Item("Poção Mística", "Recupera 15 HP", TipoEfeito.CURA, 15, 1));
            }
        } else {
            System.out.println("Você tropeça em uma pedra e cai sobre um baú velho.");
            p.inv.adicionar(new Item("Poção de Cura", "Restaura 10 HP", TipoEfeito.CURA, 10, 1));
        }
    }

    static void explorarVila(Personagem p) {
        System.out.println("\n🏚️ As casas estão destruídas, o vento sopra cinzas...");
        if (rand.nextBoolean()) {
            System.out.println("Um Bandido surge das sombras!");
            Inimigo inimigo = new Inimigo("Bandido", 20, 6, 4);
            if (p.batalhar(inimigo)) {
                System.out.println("O Bandido carregava uma bolsa de suprimentos!");
                p.inv.adicionar(new Item("Casco de Tartaruga", "Aumenta defesa", TipoEfeito.BUFF_DEFESA, 2, 1));
            }
        } else {
            System.out.println("Você encontra uma taverna abandonada e descansa um pouco...");
            p.hp += 5;
            System.out.println("Recuperou 5 HP!");
        }
    }

    static void explorarBosque(Personagem p) {
        System.out.println("\n🌲 O bosque é calmo... até demais.");
        if (rand.nextBoolean()) {
            System.out.println("Um Esqueleto se levanta da terra!");
            Inimigo inimigo = new Inimigo("Esqueleto", 18, 5, 3);
            if (p.batalhar(inimigo)) {
                p.inv.adicionar(new Item("Poção de Cura", "Cura leve", TipoEfeito.CURA, 8, 1));
                System.out.println("Você coletou ossos antigos que brilham com energia estranha.");
            }
        } else {
            System.out.println("Você encontra flores azuis que exalam um aroma curativo.");
            p.hp += 4;
            System.out.println("Você recuperou 4 HP!");
        }
    }

    static void usarItem(Personagem p) {
        if (p.inv.vazio()) {
            System.out.println("Você não tem itens.");
            return;
        }
        List<Item> lista = p.inv.listar();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ") " + lista.get(i));
        }
        System.out.print("Escolha um item (0 cancela): ");
        int idx = lerInt();
        if (idx <= 0 || idx > lista.size()) return;
        Item item = lista.get(idx - 1);
        if (p.inv.usar(item)) p.aplicarEfeito(item);
    }

    static void mostrarInventario(Personagem p) {
        System.out.println("\n--- Seu Inventário ---");
        System.out.println(p.inv);
    }

    static void tentarFugir(Personagem p) {
        int dado = 1 + rand.nextInt(6);
        if (dado >= 4) {
            System.out.println("Você conseguiu escapar com sucesso!");
        } else {
            int dano = 5 + rand.nextInt(5);
            p.hp -= dano;
            System.out.println("Você tentou fugir, mas foi atingido e perdeu " + dano + " HP!");
            if (!p.estaVivo()) System.out.println("Você não sobreviveu à fuga...");
        }
    }

    static Personagem copiar(Personagem p) {
        if (p instanceof Guerreiro g) return new Guerreiro(g);
        if (p instanceof Mago m) return new Mago(m);
        if (p instanceof Arqueiro a) return new Arqueiro(a);
        if (p instanceof Inimigo i) return new Inimigo(i);
        return p;
    }

    static int lerInt() {
        while (true) {
            try { return Integer.parseInt(sc.nextLine()); }
            catch (Exception e) { System.out.print("Digite um número: "); }
        }
    }
}

// --- Arma 2: Arco Élfico ---
class ArcoElfico implements Arma {
    @Override
    public String getNome() { return "Arco Élfico"; }

    @Override
    public int getDanoBase() { return 12; }

    @Override
    public int getCustoMana() { return 15; }

    @Override
    public boolean podeEquipar(Personagem p) {
        return p.getDestreza() >= 8;
    }

    @Override
    public void usar(Personagem atacante, Personagem alvo) {
        int dano = this.getDanoBase();
        System.out.println(atacante.getNome() + " ataca " + alvo.getNome() + " com o " + getNome() + "!");

        // Efeito Especial: "Chuva de Flechas"
        // (Em uma batalha 1v1, vamos simplificar para um ataque normal)
        System.out.println("  > EFEITO: Chuva de Flechas! (Atacando o alvo principal)");
        alvo.receberDano(dano, atacante);
    }
}
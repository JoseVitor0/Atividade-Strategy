// --- Arma 3: Cajado Arcano ---
class CajadoArcano implements Arma {
    @Override
    public String getNome() { return "Cajado Arcano"; }

    @Override
    public int getDanoBase() { return 8; }

    @Override
    public int getCustoMana() { return 25; }

    @Override
    public boolean podeEquipar(Personagem p) {
        return p.getInteligencia() >= 12;
    }

    @Override
    public void usar(Personagem atacante, Personagem alvo) {
        int dano = this.getDanoBase();
        System.out.println(atacante.getNome() + " ataca " + alvo.getNome() + " com o " + getNome() + "!");

        // Efeito Especial: "Bola de Fogo"
        System.out.println("  > EFEITO: Bola de Fogo! " + alvo.getNome() + " está queimando.");
        alvo.receberDano(dano, atacante);
        alvo.adicionarEfeito(new Queimadura(2)); // 2 turnos
    }
}
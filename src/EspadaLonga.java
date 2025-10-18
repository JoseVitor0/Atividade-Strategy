import java.util.Random;

// --- Arma 1: Espada Longa ---
class EspadaLonga implements Arma {
    private Random random = new Random();

    @Override
    public String getNome() { return "Espada Longa"; }

    @Override
    public int getDanoBase() { return 15; }

    @Override
    public int getCustoMana() { return 0; }

    @Override
    public boolean podeEquipar(Personagem p) {
        return p.getForca() >= 10;
    }

    @Override
    public void usar(Personagem atacante, Personagem alvo) {
        int dano = this.getDanoBase();
        System.out.println(atacante.getNome() + " ataca " + alvo.getNome() + " com a " + getNome() + "!");

        // Aplica o dano base
        alvo.receberDano(dano, atacante);

        // Efeito Especial: "Corte Profundo" (30% de chance)
        if (random.nextDouble() < 0.30) {
            System.out.println("  > EFEITO: Corte Profundo! " + alvo.getNome() + " está sangrando.");
            alvo.adicionarEfeito(new Sangramento(3)); // 3 turnos
        }
    }
}
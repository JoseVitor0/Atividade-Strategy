import java.util.Random;

// --- Arma 4: Machado de Guerra (Extensão) ---
class MachadoDeGuerra implements Arma {
    private Random random = new Random();

    @Override
    public String getNome() { return "Machado de Guerra"; }

    @Override
    public int getDanoBase() { return 18; }

    @Override
    public int getCustoMana() { return 5; }

    @Override
    public boolean podeEquipar(Personagem p) {
        return p.getForca() >= 15;
    }

    @Override
    public void usar(Personagem atacante, Personagem alvo) {
        int dano = this.getDanoBase();
        System.out.println(atacante.getNome() + " ataca " + alvo.getNome() + " com o " + getNome() + "!");

        // Aplica o dano base
        alvo.receberDano(dano, atacante);

        // Efeito Especial: "Golpe Esmagador" (25% de chance)
        if (random.nextDouble() < 0.25) {
            System.out.println("  > EFEITO: Golpe Esmagador! " + alvo.getNome() + " está atordoado.");
            alvo.adicionarEfeito(new Atordoado(1)); // 1 turno
        }
    }
}
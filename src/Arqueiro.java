// --- Classe 2: Arqueiro ---
class Arqueiro extends Personagem {

    public Arqueiro(String nome) {
        super(nome, 90, 80, 8, 15, 7);
    }

    @Override
    public void equiparArma(Arma arma) {
        if (arma instanceof ArcoElfico) {
            if (arma.podeEquipar(this)) {
                this.armaEquipada = arma;
                System.out.println(this.nome + " equipou " + arma.getNome() + ".");
            } else {
                System.out.println(this.nome + " não tem atributos para " + arma.getNome() + ".");
            }
        } else {
            System.out.println("CLASSE INVÁLIDA: Arqueiros não podem usar " + arma.getNome() + ".");
        }
    }

    @Override
    protected int aplicarPassivaDefensiva(int dano) {
        if (this.random.nextDouble() < 0.25) {
            System.out.println("  > Passiva 'Esquiva' ativou! " + this.nome + " evitou o dano!");
            return 0; // Dano zero
        }
        return dano;
    }

    @Override
    protected void aplicarPassivaDeTurno() {
        int manaRegen = 5;
        this.manaAtual += manaRegen;
        if (this.manaAtual > this.manaMax) {
            this.manaAtual = this.manaMax;
        }
    }
}
// --- Classe 3: Mago ---
class Mago extends Personagem {

    public Mago(String nome) {
        super(nome, 70, 150, 5, 7, 18);
    }

    @Override
    public void equiparArma(Arma arma) {
        // Mago pode usar Cajados ou Adagas (Adaga não implementada)
        if (arma instanceof CajadoArcano) {
            if (arma.podeEquipar(this)) {
                this.armaEquipada = arma;
                System.out.println(this.nome + " equipou " + arma.getNome() + ".");
            } else {
                System.out.println(this.nome + " não tem atributos para " + arma.getNome() + ".");
            }
        } else {
            System.out.println("CLASSE INVÁLIDA: Magos não podem usar " + arma.getNome() + ".");
        }
    }

    @Override
    protected int aplicarPassivaDefensiva(int dano) {
        // Nenhuma passiva de defesa
        return dano;
    }

    @Override
    protected void aplicarPassivaDeTurno() {
        // Passiva: "Regeneração de Mana"
        int manaRegen = 10;
        this.manaAtual += manaRegen;
        if (this.manaAtual > this.manaMax) {
            this.manaAtual = this.manaMax;
        }
        System.out.println("  > Passiva 'Regeneração de Mana' ativou! " + this.nome + " recuperou " + manaRegen + " de mana.");
    }
}
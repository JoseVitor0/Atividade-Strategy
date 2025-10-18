// --- Classe 1: Guerreiro ---
class Guerreiro extends Personagem {

    public Guerreiro(String nome) {
        super(nome, 120, 50, 15, 8, 5);
    }

    @Override
    public void equiparArma(Arma arma) {
        if (arma instanceof EspadaLonga || arma instanceof MachadoDeGuerra) {
            if (arma.podeEquipar(this)) {
                this.armaEquipada = arma;
                System.out.println(this.nome + " equipou " + arma.getNome() + ".");
            } else {
                System.out.println(this.nome + " não tem atributos para " + arma.getNome() + ".");
            }
        } else {
            System.out.println("CLASSE INVÁLIDA: Guerreiros não podem usar " + arma.getNome() + ".");
        }
    }

    @Override
    protected int aplicarPassivaDefensiva(int dano) {
        int danoReduzido = (int)(dano * 0.80);
        System.out.println("  > Passiva 'Pele Dura' ativou! Dano reduzido.");
        return danoReduzido;
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
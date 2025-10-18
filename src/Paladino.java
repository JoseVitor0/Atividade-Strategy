// --- Classe 4: Paladino
class Paladino extends Personagem {

    public Paladino(String nome) {
        // Atributos Híbridos (Força e Inteligência)
        super(nome, 110, 80, 12, 8, 12);
    }

    @Override
    public void equiparArma(Arma arma) {
        // Paladino pode usar Espadas ou Cajados (Híbrido)
        if (arma instanceof EspadaLonga || arma instanceof CajadoArcano) {
            if (arma.podeEquipar(this)) {
                this.armaEquipada = arma;
                System.out.println(this.nome + " equipou " + arma.getNome() + ".");
            } else {
                System.out.println(this.nome + " não tem atributos para " + arma.getNome() + ".");
            }
        } else {
            System.out.println("CLASSE INVÁLIDA: Paladinos não podem usar " + arma.getNome() + ".");
        }
    }

    @Override
    protected int aplicarPassivaDefensiva(int dano) {
        // Passiva Híbrida: "Aura Sagrada" (Reduz 5 de dano fixo)
        System.out.println("  > Passiva 'Aura Sagrada' ativou! Dano reduzido em 5.");
        return Math.max(0, dano - 5); // Garante que o dano não seja negativo
    }

    @Override
    protected void aplicarPassivaDeTurno() {
        // Passiva Híbrida: "Cura Leve"
        int cura = 5;
        this.vidaAtual += cura;
        if (this.vidaAtual > this.vidaMax) {
            this.vidaAtual = this.vidaMax;
        }
        System.out.println("  > Passiva 'Cura Leve' ativou! " + this.nome + " curou " + cura + " de vida.");

        // Adiciona uma regeneração de mana base (da correção anterior)
        int manaRegen = 5;
        this.manaAtual += manaRegen;
        if (this.manaAtual > this.manaMax) {
            this.manaAtual = this.manaMax;
        }
        System.out.println("  > " + this.nome + " regenerou " + manaRegen + " de mana.");
    }
}
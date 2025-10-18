class Sangramento implements EfeitoStatus {
    private int duracao;
    private int danoPorTurno;

    public Sangramento(int duracao) {
        this.duracao = duracao;
        this.danoPorTurno = 5; // Dano de sangramento
    }

    @Override
    public String getNome() { return "Sangramento"; }

    @Override
    public int getDuracao() { return this.duracao; }

    @Override
    public boolean estaAtivo() { return this.duracao > 0; }

    @Override
    public void aplicarEfeito(Personagem alvo) {
        System.out.println("  > " + alvo.getNome() + " sofre " + this.danoPorTurno + " de dano por sangramento!");
        // O dano de status não deve ser reduzido por passivas
        alvo.reduzirVida(this.danoPorTurno);
    }

    @Override
    public void passarTurno() {
        this.duracao--;
    }
}
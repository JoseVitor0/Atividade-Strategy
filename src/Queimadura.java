class Queimadura implements EfeitoStatus {
    private int duracao;
    private int danoPorTurno;

    public Queimadura(int duracao) {
        this.duracao = duracao;
        this.danoPorTurno = 10; // Dano de queimadura
    }

    @Override
    public String getNome() { return "Queimadura"; }

    @Override
    public int getDuracao() { return this.duracao; }

    @Override
    public boolean estaAtivo() { return this.duracao > 0; }

    @Override
    public void aplicarEfeito(Personagem alvo) {
        System.out.println("  > " + alvo.getNome() + " sofre " + this.danoPorTurno + " de dano por queimadura!");
        alvo.reduzirVida(this.danoPorTurno);
    }

    @Override
    public void passarTurno() {
        this.duracao--;
    }
}
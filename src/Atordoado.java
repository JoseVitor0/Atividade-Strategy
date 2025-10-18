class Atordoado implements EfeitoStatus {
    private int duracao;

    public Atordoado(int duracao) {
        // Atordoamento de 1 turno significa que dura este turno e o próximo
        this.duracao = duracao;
    }

    @Override
    public String getNome() { return "Atordoado"; }

    @Override
    public int getDuracao() { return this.duracao; }

    @Override
    public boolean estaAtivo() { return this.duracao > 0; }

    @Override
    public void aplicarEfeito(Personagem alvo) {
        // O efeito é aplicado na lógica do turno (pular o turno)
        // Não faz nada aqui
    }

    @Override
    public void passarTurno() {
        this.duracao--;
    }
}
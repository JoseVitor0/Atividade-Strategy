/**
 * Interface para Efeitos de Status (Sangramento, Queimadura, etc.)
 */

interface EfeitoStatus {
    String getNome();
    int getDuracao();
    boolean estaAtivo();

    // Aplica o efeito no alvo (ex: causar dano)
    void aplicarEfeito(Personagem alvo);

    // Reduz a duração do efeito a cada turno
    void passarTurno();
}
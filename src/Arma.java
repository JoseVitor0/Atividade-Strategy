/**
 * Interface Strategy: Define a ação que uma arma pode tomar.
 * Cada arma concreta será uma implementação diferente dessa estratégia.
 */

interface Arma {
    String getNome();
    int getDanoBase();
    int getCustoMana();

    // Verifica se o personagem tem os atributos para usar a arma
    boolean podeEquipar(Personagem personagem);

    // O método principal da estratégia: aplicar o ataque e seu efeito
    void usar(Personagem atacante, Personagem alvo);
}
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

abstract class Personagem {
    // Atributos base
    protected String nome;
    protected int vidaMax;
    protected int manaMax;
    protected int vidaAtual;
    protected int manaAtual;

    // Atributos
    protected int forca;
    protected int destreza;
    protected int inteligencia;

    // O personagem "tem uma" estratégia de arma
    protected Arma armaEquipada;

    // Efeitos de status
    protected List<EfeitoStatus> efeitosAtivos;
    protected boolean estaAtordoado;

    // Extensão: Crítico
    protected Random random = new Random();

    public Personagem(String nome, int vida, int mana, int forca, int destreza, int inteligencia) {
        this.nome = nome;
        this.vidaMax = vida;
        this.vidaAtual = vida;
        this.manaMax = mana;
        this.manaAtual = mana;
        this.forca = forca;
        this.destreza = destreza;
        this.inteligencia = inteligencia;
        this.efeitosAtivos = new ArrayList<>();
        this.estaAtordoado = false;
    }

    // --- Getters para lógica de equipamento ---
    public String getNome() { return this.nome; }
    public int getForca() { return this.forca; }
    public int getDestreza() { return this.destreza; }
    public int getInteligencia() { return this.inteligencia; }
    public boolean estaVivo() { return this.vidaAtual > 0; }
    public boolean estaAtordoado() { return this.estaAtordoado; }

    public abstract void equiparArma(Arma arma);


    public void atacar(Personagem alvo) {
        if (this.armaEquipada == null) {
            System.out.println(this.nome + " está desarmado e não pode atacar!");
            return;
        }

        int custoMana = this.armaEquipada.getCustoMana();
        if (this.manaAtual < custoMana) {
            System.out.println(this.nome + " não tem mana suficiente para usar " + this.armaEquipada.getNome() + "!");
            return;
        }

        // Gasta a mana
        this.manaAtual -= custoMana;

        // Delega a ação para a estratégia
        this.armaEquipada.usar(this, alvo);
    }

    // Lógica de dano
    public void receberDano(int dano, Personagem atacante) {
        // Extensão: Sistema de Crítico (calculado no atacante)
        int danoComCritico = calcularDanoCritico(dano, atacante);

        // Aplica a passiva defensiva (cada classe tem a sua)
        int danoFinal = this.aplicarPassivaDefensiva(danoComCritico);

        reduzirVida(danoFinal);

        System.out.println("  > " + this.nome + " recebeu " + danoFinal + " de dano! Vida restante: " + this.vidaAtual);
    }

    // Metodo de "baixo nível" para reduzir vida (ignora passivas)
    public void reduzirVida(int dano) {
        this.vidaAtual -= dano;
        if (this.vidaAtual < 0) {
            this.vidaAtual = 0;
        }
    }

    // Extensão: Lógica do Crítico
    private int calcularDanoCritico(int dano, Personagem atacante) {
        // 15% de chance de crítico (dano dobrado)
        if (this.random.nextDouble() < 0.15) {
            System.out.println("  > ATAQUE CRÍTICO!");
            return dano * 2;
        }
        return dano;
    }

    // Métodos para Efeitos de Status
    public void adicionarEfeito(EfeitoStatus efeito) {
        // Evita duplicados (simplificado)
        for (EfeitoStatus e : efeitosAtivos) {
            if (e.getNome().equals(efeito.getNome())) {
                return; // Já tem esse efeito
            }
        }
        this.efeitosAtivos.add(efeito);
    }

    // Lógica de fim de turno (passivas, efeitos, etc.)
    public void passarTurno() {
        // 1. Aplica Habilidade Passiva de turno (ex: Mago)
        this.aplicarPassivaDeTurno();

        // 2. Processa Efeitos de Status
        this.estaAtordoado = false; // Reseta atordoamento

        // Usamos Iterator para poder remover itens da lista enquanto iteramos
        Iterator<EfeitoStatus> it = this.efeitosAtivos.iterator();
        while (it.hasNext()) {
            EfeitoStatus efeito = it.next();

            if (efeito.estaAtivo()) {
                // Se for Atordoado, marca o personagem
                if (efeito.getNome().equals("Atordoado")) {
                    this.estaAtordoado = true;
                    System.out.println("  > " + this.nome + " está atordoado e vai perder o próximo turno!");
                }

                // Aplica o efeito (ex: dano de sangramento/queimadura)
                efeito.aplicarEfeito(this);
                efeito.passarTurno();
            } else {
                // Remove o efeito se a duração acabou
                System.out.println("  > Efeito " + efeito.getNome() + " acabou em " + this.nome);
                it.remove();
            }
        }
    }

    // Métodos abstratos para as subclasses implementarem suas passivas
    protected abstract int aplicarPassivaDefensiva(int dano);
    protected abstract void aplicarPassivaDeTurno();

    public void exibirStatus() {
        System.out.println(String.format("  [%s] Vida: %d/%d | Mana: %d/%d",
                this.nome, this.vidaAtual, this.vidaMax, this.manaAtual, this.manaMax));
    }
}
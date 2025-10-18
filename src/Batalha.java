class Batalha {
    private Personagem jogador1;
    private Personagem jogador2;
    private int turno;

    public Batalha(Personagem j1, Personagem j2) {
        this.jogador1 = j1;
        this.jogador2 = j2;
        this.turno = 1;
    }

    public void iniciarBatalha() {
        System.out.println("A BATALHA COMEÇOU: " + jogador1.getNome() + " vs " + jogador2.getNome());

        while (jogador1.estaVivo() && jogador2.estaVivo()) {
            System.out.println("\n----- TURNO " + this.turno + " -----");

            // Turno do Jogador 1
            executarTurno(jogador1, jogador2);
            if (!jogador2.estaVivo()) break; // Verifica se o J2 morreu

            // Turno do Jogador 2
            executarTurno(jogador2, jogador1);
            if (!jogador1.estaVivo()) break; // Verifica se o J1 morreu

            this.turno++;
        }

        // Fim da batalha
        System.out.println("\n----- FIM DA BATALHA -----");
        if (jogador1.estaVivo()) {
            System.out.println(jogador1.getNome() + " é o vencedor!");
        } else {
            System.out.println(jogador2.getNome() + " é o vencedor!");
        }
    }

    private void executarTurno(Personagem atacante, Personagem alvo) {
        System.out.println("\nTurno de: " + atacante.getNome());
        atacante.exibirStatus();
        alvo.exibirStatus();

        // 1. Processa passivas de turno e efeitos de status
        atacante.passarTurno();

        // 2. Verifica se o personagem está atordoado (efeito de 'passarTurno')
        if (atacante.estaAtordoado()) {
            System.out.println(atacante.getNome() + " está atordoado e perdeu o turno!");
            // Consome o atordoamento (já feito no passarTurno, mas garantindo)
            return;
        }

        // 3. O personagem ataca
        // (Aqui poderia ter um menu para o jogador escolher "Atacar", "Trocar Arma", "Usar Item")
        // Para manter simples, ele sempre ataca.

        // O jogador1 (humano) pode trocar de arma (demonstração)
        if (atacante == jogador1 && atacante instanceof Guerreiro && turno == 2) {
            System.out.println(atacante.getNome() + " decide trocar de arma!");
            atacante.equiparArma(new MachadoDeGuerra());
        }

        atacante.atacar(alvo);
    }
}
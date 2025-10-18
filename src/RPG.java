public class RPG {
    public static void main(String[] args) {

        // 1. Criar as armas disponíveis no jogo
        Arma espadaBasica = new EspadaLonga();
        Arma cajadoBasico = new CajadoArcano();
        Arma arcoBasico = new ArcoElfico();
        Arma machadoGuerra = new MachadoDeGuerra();

        // 2. Criar os Personagens
        Personagem heroi = new Guerreiro("Sir Kratos");
        Personagem inimigo = new Mago("Voldemort");

        // 3. Equipar as armas iniciais
        heroi.equiparArma(espadaBasica);
        inimigo.equiparArma(cajadoBasico);

        // 4. Iniciar a Batalha
        Batalha primeiraLuta = new Batalha(heroi, inimigo);
        primeiraLuta.iniciarBatalha();


        // --- Demonstração das Extensões ---
        System.out.println("\n\n========================================");
        System.out.println("DEMONSTRAÇÃO DAS EXTENSÕES (Híbrido)");
        System.out.println("========================================");

        // 1. Criar personagem híbrido (Paladino) e Arqueiro
        Personagem paladino = new Paladino("Arthas");
        Personagem arqueiro = new Arqueiro("Legolas");

        // 2. Equipar armas (Paladino tentando usar Arco -> falha)
        paladino.equiparArma(arcoBasico); // Isso vai falhar
        paladino.equiparArma(cajadoBasico); // Isso vai funcionar

        arqueiro.equiparArma(arcoBasico);

        // 3. Iniciar a Batalha
        Batalha segundaLuta = new Batalha(paladino, arqueiro);
        segundaLuta.iniciarBatalha();
    }
}
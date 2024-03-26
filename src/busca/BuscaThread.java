package busca;

public class BuscaThread implements Runnable {
    private String nomeArquivo;
    private String buscarString;
    private BuscaService buscaService;

    public BuscaThread(String nomeArquivo, String buscarString, BuscaService buscaService) {
        this.nomeArquivo = nomeArquivo;
        this.buscarString = buscarString;
        this.buscaService = buscaService;
    }

    @Override
    public void run() {
        System.out.println("🚨 Rodando Thread! 🚨");
        buscaService.buscarNoArquivo(nomeArquivo, buscarString);
    }
}

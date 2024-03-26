package busca;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
		System.out.print("Nome para buscar: ");
		String nome = sc.nextLine();
        System.out.println();

        BuscaService buscaService = new BuscaService();
        for (int i = 0; i < 10; i++) {
            String nomeArquivo = String.format("arquivosNomes/nomescompletos-%02d.txt", i);
            Thread buscaThread = new Thread(new BuscaThread(nomeArquivo, nome, buscaService));
            buscaThread.start();
        }

        sc.close();
    }
}

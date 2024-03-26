package busca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class BuscaService {
    private static final int MAX_THREADS = 2;
    private static final Semaphore semaforo = new Semaphore(MAX_THREADS);

    public void buscarNoArquivo(String nomeArquivo, String buscarString) {
        try {
            semaforo.acquire();
            System.out.println("🚦 Semáforo fechado no arquivo \"" + nomeArquivo + "\", para a busca da String \"" + buscarString + "\" 🚦");
            buscaNoArquivo(nomeArquivo, buscarString);
            semaforo.release();
            System.out.println("🚦 Semáforo aberto no arquivo \"" + nomeArquivo + "\", para a busca da String \"" + buscarString + "\" 🚦");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void buscaNoArquivo(String nomeArquivo, String buscarString) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            List<String> ocorrencias = new ArrayList<>();
            int numeroLinha = 1;
            while ((linha = leitor.readLine()) != null) {
                if (linha.toLowerCase().contains(buscarString.toLowerCase())) {
                    ocorrencias.add("   Arquivo: \"" + nomeArquivo + "\", Linha n°: " + numeroLinha + ", Conteúdo da linha: \"" + linha + "\"");
                }
                numeroLinha++;
            }
            if (!ocorrencias.isEmpty()) {
                System.out.println("🔍 String \"" + buscarString + "\" encontrada em:");
                for (String ocorrencia : ocorrencias) {
                    System.out.println(ocorrencia);
                }
            } else {
                System.out.println("🔍 A String \"" + buscarString + "\" não foi encontrada no arquivo \"" + nomeArquivo + "\"");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

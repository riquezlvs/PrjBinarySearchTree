/*
 * Autores: João Nascimento RA: 10748243, Levi Guerra 10748088, Guilherme Leite RA: 10739054.
 * Projeto 2 - BST com Dataset NetFlix
*/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ProgramaNetFlixRepository {

    private final String caminhoArquivo;

    public ProgramaNetFlixRepository(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public BinarySearchTree<ProgramaNetFlix> carregarDados() {
        BinarySearchTree<ProgramaNetFlix> bst = new BinarySearchTree<>();
        try {
            Path caminho = Path.of(caminhoArquivo);

            if (!Files.exists(caminho)) {
                System.out.println("Erro: Arquivo não encontrado: " + caminhoArquivo);
                return bst;
            }

            List<String> linhas = Files.readAllLines(caminho);

            if (linhas.isEmpty()) {
                System.out.println("Aviso: Arquivo está vazio!");
                return bst;
            }

            System.out.println("Carregando " + (linhas.size() - 1) + " linhas do arquivo...");

            boolean primeiraLinha = true;
            for (String linha : linhas) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }
                String[] campos = separarCamposCsv(linha);
                if (campos.length < 15 || algumCampoVazio(campos)) continue;
                String id = campos[0];
                String titulo = campos[1];
                String showType = campos[2];
                String descricao = campos[3];
                int releaseYear = Integer.parseInt(campos[4].trim());
                String ageCertificate = campos[5].trim();
                int runtime = Integer.parseInt(campos[6].trim());
                List<String> generos = extrairListaDoArray(campos[7]);
                List<String> countries = extrairListaDoArray(campos[8]);
                double temporadas = Double.parseDouble(campos[9].trim());
                String imdbId = campos[10];
                double imdbScore = Double.parseDouble(campos[11].trim());
                double imdbVotes = Double.parseDouble(campos[12].trim());
                double tmdbPopularity = Double.parseDouble(campos[13].trim());
                double tmdbScore = Double.parseDouble(campos[14].trim());

                ProgramaNetFlix p = new ProgramaNetFlix(id, titulo, showType, descricao,
                        releaseYear, ageCertificate, runtime, generos, countries,
                        temporadas, imdbId, imdbScore, imdbVotes, tmdbPopularity, tmdbScore);
                bst.insert(p);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return bst;
    }

    private boolean algumCampoVazio(String[] campos) {
        for (String campo : campos) {
            if (campo.trim().isEmpty()) return true;
        }
        return false;
    }

    private String[] separarCamposCsv(String linha) {
        List<String> campos = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean dentroAspas = false;
        for (int i = 0; i < linha.length(); i++) {
            char c = linha.charAt(i);
            if (c == '"') {
                dentroAspas = !dentroAspas;
            } else if (c == ',' && !dentroAspas) {
                campos.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        campos.add(sb.toString());
        return campos.toArray(new String[0]);
    }

    private List<String> extrairListaDoArray(String campo) {
        String limpo = campo.trim().replaceAll("^\\[|\\]$", "");
        List<String> resultado = new ArrayList<>();
        for (String item : limpo.split(",")) {
            String valor = item.trim().replaceAll("^'|'$", "");
            if (!valor.isEmpty()) {
                resultado.add(valor);
            }
        }
        return resultado;
    }
}
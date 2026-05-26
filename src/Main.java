/*
 * Autores: João Nascimento RA: 10748243, Levi Guerra 10748088, Guilherme Leite RA: 10739054.
 * Projeto 2 - BST com Dataset NetFlix
*/

import java.util.List;
import java.util.Scanner;

public class Main {
    static BinarySearchTree<ProgramaNetFlix> bst = new BinarySearchTree<>();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        int opcaoSecundaria;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Ler dados de arquivo");
            System.out.println("2 - Métodos para análise de dados");
            System.out.println("3 - Inserir novo Programa");
            System.out.println("4 - Buscar Programa");
            System.out.println("5 - Remover Programa");
            System.out.println("6 - Exibir altura da árvore BST");
            System.out.println("7 - Salvar dados em arquivo");
            System.out.println("8 - Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    try {
                        System.out.print("Digite o nome do arquivo que deseja carregar (ex: titles.csv): ");
                        scanner.nextLine();
                        String nomeArquivo = scanner.nextLine().trim();

                        if (nomeArquivo.isEmpty()) {
                            System.out.println("Erro: Nome do arquivo não pode ser vazio!");
                            break;
                        }

                        ProgramaNetFlixRepository repo = new ProgramaNetFlixRepository(nomeArquivo);
                        bst = repo.carregarDados();
                        bst.emOrdem2();

                        if (!bst.isEmpty()) {
                            System.out.println("Árvore carregada com sucesso!");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao carregar arquivo: " + e.getMessage());
                    }
                }
                case 2 -> {
                    do {
                        System.out.println("\n===== Métodos para análise de dados: =====");
                        System.out.println("1 - Top 10 títulos de 'crime' (público adolescente)");
                        System.out.println("2 - Identificação de 'HIDDEN GEMS'");
                        System.out.println("3 - Análise de produção internacional por ano");
                        System.out.println("4 - Longevidade de séries (maratona)");
                        System.out.println("5 - Piores avaliações do TMDB");
                        System.out.println("0 - Voltar");
                        System.out.print("Escolha uma opcao: ");
                        opcaoSecundaria = scanner.nextInt();

                        switch (opcaoSecundaria) {
                            case 0 -> System.out.println("retornando ao menu principal...");
                            case 1 -> {
                                try {
                                    List<ProgramaNetFlix> resultado = bst.top10CrimeTv14PorTmdb();
                                    for (int i = 0; i < Math.min(resultado.size(), 11); i++) {
                                        System.out.println((i + 1) + ". " + resultado.get(i).getTitulo() +
                                                " (TMDB Popularity: " + resultado.get(i).getTmdbPopularity() + ")");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Erro na análise: " + e.getMessage());
                                }
                            }
                            case 2 -> {
                                try {
                                    List<ProgramaNetFlix> resultado = bst.topHiddenGems();
                                    for(ProgramaNetFlix p : resultado){
                                        System.out.println(p.getTitulo() + " (TMDB Score: " + p.getTmdbScore() + ", IMDb Score: " + p.getImdbScore() + ")");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Erro na análise: " + e.getMessage());
                                }
                            }
                            case 3 -> {
                                try {
                                    System.out.println("Digite o ano para análise de produção internacional: ");
                                    int anoCorrelato = scanner.nextInt();
                                    List<ProgramaNetFlix> resultado = bst.internationalProductionbyYear(anoCorrelato);
                                    for (ProgramaNetFlix p : resultado){
                                        System.out.println(p.getTitulo() + " (" + p.getReleaseYear() + ") - Países: " + String.join(", ", p.getProductionCountries()));
                                    }
                                } catch (Exception e) {
                                    System.out.println("Erro na análise: " + e.getMessage());
                                }
                            }
                            case 4 -> {
                                try {
                                    List<ProgramaNetFlix> resultado = bst.moreBiggestSeries();
                                    for (int i = 0; i < Math.min(resultado.size(), 6); i++) {
                                        System.out.println((i + 1) + ". " + resultado.get(i).getTitulo() + " (Temporadas: " + resultado.get(i).getTemporadas() + ")");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Erro na análise: " + e.getMessage());
                                }
                            }
                            case 5 -> {
                                try {
                                    List<ProgramaNetFlix> resultado = bst.worstReviewByTmdb();
                                    for (int i = 0; i < Math.min(resultado.size(), 16); i++) {
                                        System.out.println(resultado.get(i).getTitulo() + " (TMDB Score: " + resultado.get(i).getTmdbScore() + ")");
                                    }

                                } catch (Exception e) {
                                    System.out.println("Erro na análise: " + e.getMessage());
                                }
                            }
                            default -> System.out.println("Opção invalida.");
                        }
                    } while (opcaoSecundaria != 0);
                }
                case 3 -> {
                    try {
                        bst.insertProgramaNetflix();
                    } catch (Exception e) {
                        System.out.println("Erro ao inserir programa: " + e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        System.out.print("Digite o ID do programa: ");
                        scanner.nextLine();
                        String idBusca = scanner.nextLine().trim();

                        if (idBusca.isEmpty()) {
                            System.out.println("Erro: ID não pode ser vazio!");
                            break;
                        }

                        ProgramaNetFlix resultado = bst.buscarPrograma(idBusca);
                        if (resultado != null) {
                            System.out.println("\nPrograma encontrado:");
                            System.out.println(resultado.toString());
                        } else {
                            System.out.println("Programa não encontrado.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao buscar programa: " + e.getMessage());
                    }
                }
                case 5 -> {
                    try {
                        bst.removeProgramaNetflix();
                    } catch (Exception e) {
                        System.out.println("Erro ao remover programa: " + e.getMessage());
                    }
                }
                case 6 -> {
                    try {
                        int altura = bst.altura();
                        System.out.println("Altura da árvore: " + altura);
                    } catch (Exception e) {
                        System.out.println("Erro ao calcular altura: " + e.getMessage());
                    }
                }
                case 7 -> salvarPrograma();
                case 8 -> System.out.println("Encerrando...");
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 8);

        scanner.close();
    }

    public static void salvarPrograma() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Digite o nome do arquivo de gravacao (ex: titles.csv): ");
            String nomeArquivo = sc.nextLine().trim();

            if (nomeArquivo.isEmpty()) {
                System.out.println("Erro: Nome de arquivo não pode ser vazio!");
                return;
            }

            bst.salvarDadosEmArquivo(nomeArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }
}
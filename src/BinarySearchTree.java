/*
 * Autores: João Nascimento RA: 10748243, Levi Guerra 10748088, Guilherme Leite RA: 10739054.
 * Projeto 2 - BST com Dataset NetFlix
*/

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//Classe BynarySearchTree<T>: encarregada de manipular a estrutura de dados
//árvore de busca binária (ABB) genérica.
//
//Autor1: Ivan Carlos Alcântara de Oliveira.
//Data da Criação: 04/14/2026. 15h.
public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> raiz; // Nó raiz da ABB

    // Construtor da ABB
    public BinarySearchTree() {
        raiz = null;
    }

    // Verifica se a ABB está vazia
    public boolean isEmpty() {
        return (raiz == null);
    }

    // Configura a raiz da árvore
    public void setRaiz(Node<T> araiz) {
        raiz = araiz;
    }

    // Obtém o nó raiz da ABB
    public Node<T> getRaiz() {
        return raiz;
    }

    // Procura o elemento e na ABB
    public Node<T> search(T e) {
        return search(raiz, e);
    }

    // Método que procura o elemento e na ABB de raiz
    public Node<T> search(Node<T> node, T e) {
        if (node == null) // elemento não foi encontrado
            return null;
        else if (compara(e, node.getValue()) == 0) return node;
        else if (compara(e, node.getValue()) < 0) return search(node.getFilhoEsquerdo(), e);
        else return search(node.getFilhoDireito(), e);
    }

    // Método público que insere "valor" na ABB.
    // Ou seja, responsável por chamar o método que
    // insere um novo nó (contendo "valor") na ABB de "raiz"
    public T inserir(T valor) {
        try {
            Node<T> novo = new Node<>(valor);
            raiz = inserir(novo, raiz);
            return valor;
        } catch (Exception e) {
            return null;
        }
    }

    // Método que realiza a inserção de um novo nó (novo)
    // na ABB
    private Node<T> inserir(Node<T> novo, Node<T> atual) {
        if (atual == null) {
            return novo;
        }

        if (compara(novo.getValue(), atual.getValue()) < 0) {
            atual.setFilhoEsquerdo(inserir(novo, atual.getFilhoEsquerdo()));
        } else {
            atual.setFilhoDireito(inserir(novo, atual.getFilhoDireito()));
        }

        return atual;
    }

    // Encarregado de chamar o método que percorre a ABB em
    // emOrdem a partir do raiz
    public String emOrdem() {
        return emOrdem(raiz);
    }

    // Encarregado de chamar o método que percorre a ABB em
    // emOrdem2 a partir do raiz
    public void emOrdem2() {
        emOrdem2(raiz);
    }

    // Encarregado de chamar o método que percorre a ABB em
    // preOrdem a partir do raiz
    public void preOrdem() {
        preOrdem(raiz);
    }

    // Encarregado de chamar o método que percorre a ABB em
    // posOrdem a partir do raiz
    public void posOrdem() {
        posOrdem(raiz);
    }

    // Método que percorre a ABB em Ordem
    // retornando uma String com os valores
    // concatenados do nó
    public String emOrdem(Node<T> no) {
        if (no == null) return "";

        StringBuilder sb = new StringBuilder();
        sb.append(emOrdem(no.getFilhoEsquerdo()));
        sb.append(no.getValue() + " ");
        sb.append(emOrdem(no.getFilhoDireito()));

        return sb.toString();
    }

    // Método que percorre a ABB em Ordem
    // e imprime os valores dos nós
    public void emOrdem2(Node<T> no) {
        if (no != null) {
            emOrdem2(no.getFilhoEsquerdo());
            System.out.print(no.getValue() + "\n");
            emOrdem2(no.getFilhoDireito());
        }
    }

    // Método que percorre a ABB em preOrdem
    // e imprime os valores dos nós
    public void preOrdem(Node<T> no) {
        if (no != null) {
            System.out.print(no.getValue() + "   ");
            preOrdem(no.getFilhoEsquerdo());
            preOrdem(no.getFilhoDireito());
        }
    }

    // Método que percorre a ABB em posOrdem
    // e imprime os valores dos nós
    public void posOrdem(Node<T> no) {
        if (no != null) {
            posOrdem(no.getFilhoEsquerdo());
            posOrdem(no.getFilhoDireito());
            System.out.print(no.getValue() + "   ");
        }
    }

    // Método que percorre a ABB em Nível
    // e imprime os valores dos nós
    public void emNivel() {
        //Método iterativo que utiliza uma fila auxiliar
        Node<T> noAux;
        // Observe que a LinkedList está funcionando como uma Fila
        LinkedList<Node<T>> fila = new LinkedList<>();
        fila.addLast(raiz);  // Adiciona no Final
        while (!fila.isEmpty()) {
            noAux = fila.pollFirst();  // Remove do Começo
            if (noAux.getFilhoEsquerdo() != null) {
                fila.addLast(noAux.getFilhoEsquerdo());
            }
            if (noAux.getFilhoDireito() != null) {
                fila.addLast(noAux.getFilhoDireito());
            }
            System.out.print(noAux.getValue() + "   ");
        }
    }

    // Método que compara dois objetos  do tipo T genérico)
    private int compara(T ob1, T ob2) {
        return ob1.compareTo(ob2);
    }

    //Determina o menor elemento a partir de um nó
    public Node<T> getMenor(Node<T> node) {
        if (isEmpty()) {
            return null;
        }
        if (node.getFilhoEsquerdo() == null) {
            return node;
        } else {
            return getMenor(node.getFilhoEsquerdo());
        }
    }

    //Determina o maior elemento a partir de um nó
    public Node<T> getMaior(Node<T> node) {
        if (isEmpty()) {
            return null;
        }
        if (node.getFilhoDireito() == null) {
            return node;
        } else {
            return getMaior(node.getFilhoDireito());
        }
    }

    // Obtém o maior elemento a partir de um nó
    public Node<T> getMax(Node<T> raiz, Node<T> paiRaiz) {
        if (isEmpty()) {
            return null;
        }
        Node<T> aux;
        //Se não tiver mais filho direito
        if (raiz.getFilhoDireito() == null) {
            aux = raiz;
            //Se tiver um pai, ele assume o filho esquerdo
            if (paiRaiz != null) {
                if (paiRaiz.getFilhoEsquerdo() == raiz) // se é filho esquerdo
                {
                    paiRaiz.setFilhoEsquerdo(raiz.getFilhoEsquerdo());
                } else {
                    paiRaiz.setFilhoDireito(raiz.getFilhoEsquerdo());
                }
            }
            return aux;
        } else {
            return getMax(raiz.getFilhoDireito(), raiz);
        }
    }

    // Método encarregado de chamar outro método
    // que elimina o objeto e da ABB a partir da raiz
    public boolean eliminar(T e) {
        return eliminar(raiz, null, e);
    }

    //Remove um elemento da árvore e retorna true ou false
    private boolean eliminar(Node<T> node, Node<T> paiRaiz, T e) {
        Node<T> aux;
        if (node == null) {  // não achou o elemento, não existe (chegou na folha)
            return false;
        } else { // a árvore ou sub-árvore não está vazia
            if (compara(e, node.getValue()) == 0) {  // o nó a eliminar está na raiz
                aux = node;
                //Se o nó não possui filhos, basta sumir com o nó
                if (node.getFilhoEsquerdo() == null && node.getFilhoDireito() == null) {
                    //Se não tiver pai, é a raiz da árvore
                    if (paiRaiz == null) {
                        setRaiz(null);
                    } //Senão, o pai deve "deserdar" o filho
                    else {
                        //Verifica se o nó que será eliminado é o filho esquerdo ou direito  do pai:
                        if (paiRaiz.getFilhoEsquerdo() != null && compara(paiRaiz.getFilhoEsquerdo().getValue(), e) == 0) {
                            paiRaiz.setFilhoEsquerdo(null);
                        } else if (paiRaiz.getFilhoDireito() != null && compara(paiRaiz.getFilhoDireito().getValue(), e) == 0) {
                            paiRaiz.setFilhoDireito(null);
                        }
                    }
                } else if (node.getFilhoDireito() == null) {   // se só tiver o filho esquerdo
                    //Se tiver um pai, ele assume o filho esquerdo
                    if (paiRaiz != null) {
                        //Verifica se a raiz é filho esquerdo ou direito para assumir o neto
                        if (paiRaiz.getFilhoEsquerdo() != null && compara(paiRaiz.getFilhoEsquerdo().getValue(), e) == 0) {
                            paiRaiz.setFilhoEsquerdo(node.getFilhoEsquerdo());
                        } else {
                            paiRaiz.setFilhoDireito(node.getFilhoEsquerdo());
                        }
                    } //Se não tiver pai (caso da raiz), adotar seu filho esquerdo
                    else {
                        node.setValue(node.getFilhoEsquerdo().getValue());
                        node.setFilhoEsquerdo(node.getFilhoEsquerdo().getFilhoEsquerdo());
                        node.setFilhoDireito(node.getFilhoEsquerdo().getFilhoDireito());
                    }
                } else if (node.getFilhoEsquerdo() == null) {   // se só tiver o filho direito
                    //Se tiver um pai, ele assume o filho esquerdo
                    if (paiRaiz != null) {
                        //Verifica se a raiz é filho esquerdo ou direito para assumir o neto
                        if (paiRaiz.getFilhoEsquerdo() != null && compara(paiRaiz.getFilhoEsquerdo().getValue(), e) == 0) {
                            paiRaiz.setFilhoEsquerdo(node.getFilhoDireito());
                        } else {
                            paiRaiz.setFilhoDireito(node.getFilhoDireito());
                        }
                    } //Se não tiver pai (caso da raiz), adotar seu filho esquerdo
                    else {
                        node.setValue(node.getFilhoDireito().getValue());
                        node.setFilhoEsquerdo(node.getFilhoDireito().getFilhoEsquerdo());
                        node.setFilhoDireito(node.getFilhoDireito().getFilhoDireito());
                    }
                } else {   //Raiz possui os 2 filhos
                    aux = getMax(node.getFilhoEsquerdo(), node);
                    node.setValue(aux.getValue());
                }
                aux = null;
                return true;
            } else { //Se não achou o nó a eliminar na raiz, continue procurando recursively:
                //Se for menor que a raiz, continuar procurando à esquerda
                if (compara(e, node.getValue()) < 0) {
                    return eliminar(node.getFilhoEsquerdo(), node, e);
                } else { // ou à direita
                    return eliminar(node.getFilhoDireito(), node, e);
                }
            }
        }
    }

    // Algumas implementações de operações com ABBs em forma iterativa:
// Método que procura um objeto (obj) dentro da árvore
// Retornando o objeto (obj) se encontra ou null, caso contrário
    public Node<T> find(T obj) {
        Node<T> atual = raiz;

        while (atual != null) {
            int cmp = compara(obj, atual.getValue());

            if (cmp == 0) {
                return atual;
            } else if (cmp < 0) {
                atual = atual.getFilhoEsquerdo();
            } else {
                atual = atual.getFilhoDireito();
            }
        }

        return null;
    }

    // Implementação iterativa da Inserção
    public T insert(T valor) {
        try {
            Node<T> novoNodo = new Node<>(valor);

            if (isEmpty()) {
                raiz = novoNodo;
                return valor;
            }

            Node<T> atual = raiz;
            Node<T> pai = null;

            while (atual != null) {
                pai = atual;
                if (compara(valor, atual.getValue()) < 0) {
                    atual = atual.getFilhoEsquerdo();
                } else {
                    atual = atual.getFilhoDireito();
                }
            }

            if (compara(valor, pai.getValue()) < 0) {
                pai.setFilhoEsquerdo(novoNodo);
            } else {
                pai.setFilhoDireito(novoNodo);
            }

            return valor;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Retorna os programas "crime" com certificação "TV-14" ordenados
     * por TMDB score (descendente). Esta versão pública apenas encaminha
     * para a implementação recursiva que inicia no nó raiz.
     * Percurso utilizado: em-ordem (in-order).
     *
     * @return lista de programas filtrados e ordenados
     */
    public List<ProgramaNetFlix> top10CrimeTv14PorTmdb() {
        return top10CrimeTv14PorTmdb(raiz);
    }

    public List<ProgramaNetFlix> top10CrimeTv14PorTmdb(Node<T> no) {
        List<ProgramaNetFlix> lista = new ArrayList<>();
        if (no == null) return lista;
        lista.addAll(top10CrimeTv14PorTmdb(no.getFilhoEsquerdo()));
        ProgramaNetFlix p = (ProgramaNetFlix) no.getValue();
        boolean temCrime = p.getGeneros() != null && p.getGeneros().stream().anyMatch(g -> g.equalsIgnoreCase("crime"));
        boolean isTV14 = "TV-14".equalsIgnoreCase(p.getAgeCertificate());
        if (temCrime && isTV14) lista.add(p);
        lista.addAll(top10CrimeTv14PorTmdb(no.getFilhoDireito()));
        lista.sort((a, b) -> Double.compare(b.getTmdbPopularity(), a.getTmdbPopularity()));
        return lista;
    }


    /**
     * Identifica títulos com alta nota no IMDB e poucos votos (hidden gems).
     * Versão pública que encaminha para a implementação recursiva.
     * Percurso utilizado: pós-ordem (post-order).
     *
     * @return lista de possíveis "hidden gems"
     */
    public List<ProgramaNetFlix> topHiddenGems() {
        return topHiddenGems(raiz);
    }

    public List<ProgramaNetFlix> topHiddenGems(Node<T> no) {
        List<ProgramaNetFlix> lista = new ArrayList<>();
        if (no == null) return lista;
        ProgramaNetFlix p = (ProgramaNetFlix) no.getValue();
        boolean altaQualidade = p.getImdbScore() >= 8;
        boolean poucosVotos = p.getImdbVotes() <= 1000;
        if (altaQualidade && poucosVotos) lista.add(p);
        lista.addAll(topHiddenGems(no.getFilhoEsquerdo()));
        lista.addAll(topHiddenGems(no.getFilhoDireito()));
        return lista;
    }
    /**
     * Retorna programas produzidos fora dos EUA em um determinado ano.
     * A busca utiliza percurso em largura (nivel por nivel) para obter
     * resultados independentes da ordenação da ABB.
     *
     * @param ano ano a ser filtrado
     * @return lista de programas produzidos fora dos EUA no ano informado
     */
    public List<ProgramaNetFlix> internationalProductionbyYear(int ano) {
        return internationalProductionbyYear(raiz, ano);
    }

    public List<ProgramaNetFlix> internationalProductionbyYear(Node<T> no, int ano) {
        Node<T> noAux;
        LinkedList<Node<T>> fila = new LinkedList<Node<T>>();
        List<ProgramaNetFlix> filtrado = new ArrayList<>();
        fila.addLast(no);
        while (!fila.isEmpty()) {
            noAux = (Node<T>) fila.pollFirst();  // Remove do Começo
            ProgramaNetFlix p = (ProgramaNetFlix) noAux.getValue();
            if (noAux.getFilhoEsquerdo() != null) {
                fila.addLast(noAux.getFilhoEsquerdo());
            }
            if (noAux.getFilhoDireito() != null) {
                fila.addLast(noAux.getFilhoDireito());
            }
            boolean foraEua = p.getProductionCountries().stream().noneMatch(c -> c.equalsIgnoreCase("US"));
            boolean anoCorrelato = p.getReleaseYear() == ano;
            if (foraEua && anoCorrelato) filtrado.add(p);
        }
        return filtrado;
    }

    /**
     * Identifica séries (tipo SHOW) e retorna em ordem decrescente de
     * temporadas. A versão pública chama a implementação recursiva a partir
     * da raiz. Percurso utilizado: pós-ordem.
     *
     * @return lista de séries ordenadas por temporadas
     */
    public List<ProgramaNetFlix> moreBiggestSeries() {
        return moreBiggestSeries(raiz);
    }

    public List<ProgramaNetFlix> moreBiggestSeries(Node<T> no) {
        List<ProgramaNetFlix> lista = new ArrayList<>();
        if (no == null) return lista;
        ProgramaNetFlix p = (ProgramaNetFlix) no.getValue();
        boolean isShow = p.getShowType().equalsIgnoreCase("SHOW");
        if (isShow) lista.add(p);
        lista.addAll(moreBiggestSeries(no.getFilhoEsquerdo()));
        lista.addAll(moreBiggestSeries(no.getFilhoDireito()));

        lista.sort((a, b) -> Double.compare(b.getTemporadas(), a.getTemporadas()));
        return lista;
    }

    /**
     * Retorna os programas com avaliação no TMDB (> 0) ordenados por
     * tmdbScore ascendente (piores primeiro). Utiliza percurso em-ordem.
     *
     * @return lista de programas ordenada por tmdbScore crescente
     */
    public List<ProgramaNetFlix> worstReviewByTmdb() {
        return worstReviewByTmdb(raiz);
    }

    public List<ProgramaNetFlix> worstReviewByTmdb(Node<T> no) {
        List<ProgramaNetFlix> lista = new ArrayList<>();
        if (no == null) return lista;
        lista.addAll(worstReviewByTmdb(no.getFilhoEsquerdo()));
        ProgramaNetFlix p = (ProgramaNetFlix) no.getValue();
        boolean tmdbScoreMaiorQue0 = p.getTmdbScore() > 0.0;
        if (tmdbScoreMaiorQue0) lista.add(p);
        lista.addAll(worstReviewByTmdb(no.getFilhoDireito()));
        lista.sort((a, b) -> Double.compare(a.getTmdbScore(), b.getTmdbScore()));
        return lista;
    }
    /**
     * Solicita dados do novo programa ao usuário e insere um novo nó na BST.
     * Gera automaticamente um identificador seguindo o padrão do dataset
     * (tsN para SHOW, tmN para MOVIE) baseado no maior número já presente.
     */
    public void insertProgramaNetflix() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o titulo do programa: ");
        String titulo = sc.nextLine();

        System.out.print("Digite o tipo (MOVIE/SHOW): ");
        String showType = sc.nextLine();

        System.out.print("Digite o ano de lancamento: ");
        int releaseYear = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Digite a classificacao etaria (ex: TV-14, PG-13): ");
        String ageCertificate = sc.nextLine();

        System.out.print("Digite os generos separados por virgula (ex: crime, drama): ");
        List<String> generos = new ArrayList<>();
        for (String g : sc.nextLine().split(",")) {
            String v = g.trim();
            if (!v.isEmpty()) generos.add(v);
        }

        System.out.print("Digite os paises de producao separados por virgula (ex: US, BR): ");
        List<String> productionCountries = new ArrayList<>();
        for (String c : sc.nextLine().split(",")) {
            String v = c.trim();
            if (!v.isEmpty()) productionCountries.add(v);
        }

        System.out.print("Digite o numero de temporadas (0 para filmes): ");
        double temporadas = Double.parseDouble(sc.nextLine().trim());

        System.out.print("Digite a nota IMDB (0.0 a 10.0): ");
        double imdbScore = Double.parseDouble(sc.nextLine().trim());

        System.out.print("Digite o numero de votos IMDB: ");
        double imdbVotes = Double.parseDouble(sc.nextLine().trim());

        System.out.print("Digite a popularidade TMDB: ");
        double tmdbPopularity = Double.parseDouble(sc.nextLine().trim());

        System.out.print("Digite a nota TMDB (0.0 a 10.0): ");
        double tmdbScore = Double.parseDouble(sc.nextLine().trim());
        String prefixo;
        if (showType.equalsIgnoreCase("SHOW")) {
            prefixo = "ts";
        } else {
            prefixo = "tm";
        }
        int maiorNumero = 0;
        LinkedList<Node<T>> fila = new LinkedList<>();
        if (!isEmpty()) {
            fila.addLast(raiz);
            while (!fila.isEmpty()) {
                Node<T> noAux = fila.pollFirst();
                String idExistente = ((ProgramaNetFlix) noAux.getValue()).getId();
                if (idExistente != null && idExistente.startsWith(prefixo)) {
                    try {
                        int num = Integer.parseInt(idExistente.substring(prefixo.length()));
                        if (num > maiorNumero) maiorNumero = num;
                    } catch (NumberFormatException ignored) {
                    }
                }
                if (noAux.getFilhoEsquerdo() != null) fila.addLast(noAux.getFilhoEsquerdo());
                if (noAux.getFilhoDireito() != null) fila.addLast(noAux.getFilhoDireito());
            }
        }
        String id = prefixo + (maiorNumero + 1);

        ProgramaNetFlix novo = new ProgramaNetFlix(id, titulo, showType, releaseYear, ageCertificate, generos, productionCountries, temporadas, imdbScore, imdbVotes, tmdbPopularity, tmdbScore);
        insert((T) novo);
        System.out.println("Programa inserido com sucesso! ID gerado: " + id);
    }

    //Remover um programa da BST pelo ID
    public void removeProgramaNetflix() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o ID do programa a ser removido: ");
        String id = sc.nextLine().trim();

        ProgramaNetFlix chave = new ProgramaNetFlix();
        chave.setId(id);

        boolean removido = eliminar((T) chave);

        if (removido) {
            System.out.println("Programa com ID '" + id + "' removido com sucesso!");
        } else {
            System.out.println("Programa com ID '" + id + "' nao encontrado na arvore.");
        }
    }

    /**
     * Busca um programa pela sua chave (id) na ABB.
     * Retorna também estatísticas simples: tempo de execução e número de comparações.
     *
     * @param id identificador do programa a ser buscado
     * @return o ProgramaNetFlix encontrado ou null caso não exista
     */
    public ProgramaNetFlix buscarPrograma(String id) {
        long inicio = System.nanoTime();
        int comparacoes = 0;
        Node<T> atual = raiz;
        while (atual != null) {
            ProgramaNetFlix p = (ProgramaNetFlix) atual.getValue();
            int cmp = id.compareTo(p.getId());
            comparacoes++;

            if (cmp == 0) {
                System.out.printf("Tempo de execucao: %.3f ms%n", (System.nanoTime() - inicio) / 1_000_000.0);
                System.out.printf("Comparacoes realizadas: %d%n", comparacoes);
                return p;
            } else if (cmp < 0) {
                atual = atual.getFilhoEsquerdo();
            } else {
                atual = atual.getFilhoDireito();
            }
        }
        System.out.printf("Tempo de execucao: %.3f ms%n", (System.nanoTime() - inicio) / 1_000_000.0);
        System.out.printf("Comparacoes realizadas: %d%n", comparacoes);
        return null;
    }
//retorna a altura da arvore

    /**
     * Retorna a altura da ABB (profundidade máxima).
     * A altura de uma árvore vazia é -1 por convenção nesta implementação.
     *
     * @return altura da árvore
     */
    public int altura() {
        return altura(raiz);
    }

    /**
     * Calcula recursivamente a altura a partir do nó informado.
     *
     * @param no nó de início
     * @return altura do sub-árvore
     */
    private int altura(Node<T> no) {
        if (no == null) {
            return -1;
        }
        int leftHeight = altura(no.getFilhoEsquerdo());
        int rightHeight = altura(no.getFilhoDireito());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Salva os registros atualmente armazenados na ABB em um arquivo CSV.
     * Apenas os campos mantidos pelo modelo são escritos (os mesmos validados
     * durante a leitura).
     *
     * @param nomeArquivo caminho/nome do arquivo de saída
     */
    public void salvarDadosEmArquivo(String nomeArquivo) {
        List<ProgramaNetFlix> programas = new ArrayList<>();
        coletarEmOrdem(raiz, programas);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo, false))) {
            bw.write("id,title,type,release_year,age_certification,genres,production_countries,seasons,imdb_score,imdb_votes,tmdb_popularity,tmdb_score");
            bw.newLine();
            for (ProgramaNetFlix p : programas) {
                StringBuilder sb = new StringBuilder();
                // Ordem dos campos: id,title,type,release_year,age_certification,genres,
                // production_countries,seasons,imdb_score,imdb_votes,tmdb_popularity,tmdb_score
                sb.append(escaparCsv(p.getId())).append(",");
                sb.append(escaparCsv(p.getTitulo())).append(",");
                sb.append(escaparCsv(p.getShowType())).append(",");
                sb.append(p.getReleaseYear()).append(",");
                sb.append(escaparCsv(p.getAgeCertificate())).append(",");
                sb.append(listaParaArrayCsv(p.getGeneros())).append(",");
                sb.append(listaParaArrayCsv(p.getProductionCountries())).append(",");
                sb.append(p.getTemporadas()).append(",");
                sb.append(p.getImdbScore()).append(",");
                sb.append(p.getImdbVotes()).append(",");
                sb.append(p.getTmdbPopularity()).append(",");
                sb.append(p.getTmdbScore());
                bw.write(sb.toString());
                bw.newLine();
            }
            System.out.println("Dados salvos com sucesso em '" + nomeArquivo + "'. Total de registros: " + programas.size());
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    /**
     * Coleta todos os programas em-ordem (in-order) e adiciona à lista fornecida.
     * O resultado ficará ordenado de acordo com a ordem natural da chave.
     *
     * @param no nó atual para coleta
     * @param lista lista de saída onde os programas serão adicionados
     */
    private void coletarEmOrdem(Node<T> no, List<ProgramaNetFlix> lista) {
        if (no == null) return;
        coletarEmOrdem(no.getFilhoEsquerdo(), lista);
        lista.add((ProgramaNetFlix) no.getValue());
        coletarEmOrdem(no.getFilhoDireito(), lista);
    }

    /**
     * Escapa valores que contêm vírgulas, aspas ou quebras de linha para
     * que possam ser escritos corretamente em CSV.
     *
     * @param valor texto a ser escapado
     * @return valor escapado (entre aspas se necessário)
     */
    private String escaparCsv(String valor) {
        if (valor == null) return "";
        if (valor.contains(",") || valor.contains("\"") || valor.contains("\n")) {
            return "\"" + valor.replace("\"", "\"\"") + "\"";
        }
        return valor;
    }

    /**
     * Converte uma lista de strings em uma representação compatível com o
     * formato original do dataset (por exemplo: ['Drama','Crime']).
     *
     * @param lista lista de strings
     * @return representação em formato de array com aspas simples
     */
    private String listaParaArrayCsv(List<String> lista) {
        if (lista == null || lista.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < lista.size(); i++) {
            sb.append("'").append(lista.get(i)).append("'");
            if (i < lista.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
/*
 * Autores: João Nascimento RA: 10748243, Levi Guerra 10748088, Guilherme Leite RA: 10739054.
 * Projeto 2 - BST com Dataset NetFlix
*/

import java.util.List;

public class ProgramaNetFlix implements Comparable<ProgramaNetFlix> {
    private String id;
    private String titulo;
    private String showType;
    private String descricao;
    private int releaseYear;
    private String ageCertificate;
    private int runtime;
    private List<String> generos;
    private List<String> productionCountries;
    private double temporadas;
    private String imdbId;
    private double imdbScore;
    private double imdbVotes;
    private double tmdbPopularity;
    private double tmdbScore;

    public ProgramaNetFlix() {}

    public ProgramaNetFlix(String id, String titulo, String showType, String descricao,
                           int releaseYear, String ageCertificate, int runtime,
                           List<String> generos, List<String> productionCountries,
                           double temporadas, String imdbId, double imdbScore,
                           double imdbVotes, double tmdbPopularity, double tmdbScore) {
        this.id = id;
        this.titulo = titulo;
        this.showType = showType;
        this.descricao = descricao;
        this.releaseYear = releaseYear;
        this.ageCertificate = ageCertificate;
        this.runtime = runtime;
        this.generos = generos;
        this.productionCountries = productionCountries;
        this.temporadas = temporadas;
        this.imdbId = imdbId;
        this.imdbScore = imdbScore;
        this.imdbVotes = imdbVotes;
        this.tmdbPopularity = tmdbPopularity;
        this.tmdbScore = tmdbScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getAgeCertificate() {
        return ageCertificate;
    }

    public void setAgeCertificate(String ageCertificate) {
        this.ageCertificate = ageCertificate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public List<String> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<String> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public double getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(double temporadas) {
        this.temporadas = temporadas;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public double getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(double imdbScore) {
        this.imdbScore = imdbScore;
    }

    public double getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(double imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public double getTmdbPopularity() {
        return tmdbPopularity;
    }

    public void setTmdbPopularity(double tmdbPopularity) {
        this.tmdbPopularity = tmdbPopularity;
    }

    public double getTmdbScore() {
        return tmdbScore;
    }

    public void setTmdbScore(double tmdbScore) {
        this.tmdbScore = tmdbScore;
    }

    @Override
    public int compareTo(ProgramaNetFlix outro) {
        return this.id.compareTo(outro.id);
    }

    @Override
    public String toString() {
        return "ProgramaNetFlix{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", showType='" + showType + '\'' +
                ", descricao='" + descricao + '\'' +
                ", releaseYear=" + releaseYear +
                ", ageCertificate=" + ageCertificate +
                ", runtime=" + runtime +
                ", generos=" + generos +
                ", productionCountries=" + productionCountries +
                ", temporadas=" + temporadas +
                ", imdbId='" + imdbId + '\'' +
                ", imdbScore=" + imdbScore +
                ", imdbVotes=" + imdbVotes +
                ", tmdbPopularity=" + tmdbPopularity +
                ", tmdbScore=" + tmdbScore +
                '}';
    }
}

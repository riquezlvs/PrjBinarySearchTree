/**
 * Representa um registro do dataset NetFlix mapeado em memória.
 *
 * <p>Esta classe contém apenas os atributos necessários para as análises do
 * projeto (identificador, título, tipo, ano, classificação, gêneros,
 * países de produção, temporadas e métricas IMDB/TMDB). Campos do dataset
 * originais que não são utilizados pelas análises foram removidos para
 * simplificar o modelo.</p>
 *
 * Arquivo: ProgramaNetFlix.java
 * Autores: João Nascimento RA: 10748243, Levi Guerra RA: 10748088,
 *          Guilherme Leite RA: 10739054.
 */
import java.util.List;

public class ProgramaNetFlix implements Comparable<ProgramaNetFlix> {
    private String id;
    private String titulo;
    private String showType;
    private int releaseYear;
    private String ageCertificate;
    private List<String> generos;
    private List<String> productionCountries;
    private double temporadas;
    private double imdbScore;
    private double imdbVotes;
    private double tmdbScore;

    public ProgramaNetFlix() {}

    public ProgramaNetFlix(String id, String titulo, String showType,
                           int releaseYear, String ageCertificate,
                           List<String> generos, List<String> productionCountries,
                           double temporadas, double imdbScore,
                           double imdbVotes, double tmdbScore) {
        this.id = id;
        this.titulo = titulo;
        this.showType = showType;
        this.releaseYear = releaseYear;
        this.ageCertificate = ageCertificate;
        this.generos = generos;
        this.productionCountries = productionCountries;
        this.temporadas = temporadas;
        this.imdbScore = imdbScore;
        this.imdbVotes = imdbVotes;
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

    // imdbId removido

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
                ", releaseYear=" + releaseYear +
                ", ageCertificate=" + ageCertificate +
                ", generos=" + generos +
                ", productionCountries=" + productionCountries +
                ", temporadas=" + temporadas +
                ", imdbScore=" + imdbScore +
                ", imdbVotes=" + imdbVotes +
                ", tmdbScore=" + tmdbScore +
                '}';
    }
}

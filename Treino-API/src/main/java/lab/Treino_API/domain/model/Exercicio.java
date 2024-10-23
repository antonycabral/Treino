package lab.Treino_API.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_exercicio")
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int series;
    private int repeticoes;
    private int carga;
    private int descanso;
    private int energia;

    @ManyToOne
    @JoinColumn(name = "treino_id")
    private Treino treino;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;



    public Exercicio() {
    }

    public Exercicio(Long id, String nome, int series, int repeticoes, int carga, int descanso, int energia,
            Treino treino, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.series = series;
        this.repeticoes = repeticoes;
        this.carga = carga;
        this.descanso = descanso;
        this.energia = energia;
        this.treino = treino;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }

    public int getDescanso() {
        return descanso;
    }

    public void setDescanso(int descanso) {
        this.descanso = descanso;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public Treino getTreino() {
        return treino;
    }

    public void setTreino(Treino treino) {
        this.treino = treino;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    

}

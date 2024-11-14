package Gambiarra.Treino.model;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "desempenho")
public class Desempenho {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @ManyToOne
    private Usuario usuario;
    
    private LocalDateTime dataTreino;
    private Integer duracaoMinutos;
    private Integer exerciciosRealizados;

    public Desempenho() {
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public LocalDateTime getDataTreino() {
        return dataTreino;
    }
    public void setDataTreino(LocalDateTime dataTreino) {
        this.dataTreino = dataTreino;
    }
    public Integer getDuracaoMinutos() {
        return duracaoMinutos;
    }
    public void setDuracaoMinutos(Integer duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }
    public Integer getExerciciosRealizados() {
        return exerciciosRealizados;
    }
    public void setExerciciosRealizados(Integer exerciciosRealizados) {
        this.exerciciosRealizados = exerciciosRealizados;
    }
    
}
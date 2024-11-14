package Gambiarra.Treino.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "exercicio_historico")
public class ExercicioHistorico {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "exercicio_id")
    private Exercicio exercicio;
    
    private LocalDateTime dataAlteracao;
    private Integer cargaAnterior;
    private Integer cargaNova;
    private Integer repeticoesAnteriores;
    private Integer repeticoesNovas;
    private String motivoAlteracao;

    public ExercicioHistorico() {
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Exercicio getExercicio() {
        return exercicio;
    }
    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }
    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }
    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
    public Integer getCargaAnterior() {
        return cargaAnterior;
    }
    public void setCargaAnterior(Integer cargaAnterior) {
        this.cargaAnterior = cargaAnterior;
    }
    public Integer getCargaNova() {
        return cargaNova;
    }
    public void setCargaNova(Integer cargaNova) {
        this.cargaNova = cargaNova;
    }
    public Integer getRepeticoesAnteriores() {
        return repeticoesAnteriores;
    }
    public void setRepeticoesAnteriores(Integer repeticoesAnteriores) {
        this.repeticoesAnteriores = repeticoesAnteriores;
    }
    public Integer getRepeticoesNovas() {
        return repeticoesNovas;
    }
    public void setRepeticoesNovas(Integer repeticoesNovas) {
        this.repeticoesNovas = repeticoesNovas;
    }
    public String getMotivoAlteracao() {
        return motivoAlteracao;
    }
    public void setMotivoAlteracao(String motivoAlteracao) {
        this.motivoAlteracao = motivoAlteracao;
    }

    

}

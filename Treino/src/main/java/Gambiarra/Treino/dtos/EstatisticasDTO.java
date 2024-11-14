package Gambiarra.Treino.dtos;

import java.time.LocalDateTime;

public class EstatisticasDTO {
    private Integer treinosCompletos;
    private Integer tempoTotalTreino;
    private Double mediaExerciciosPorTreino;
    private LocalDateTime ultimoTreino;
    
    public Integer getTreinosCompletos() {
        return treinosCompletos;
    }
    public void setTreinosCompletos(Integer treinosCompletos) {
        this.treinosCompletos = treinosCompletos;
    }
    public Integer getTempoTotalTreino() {
        return tempoTotalTreino;
    }
    public void setTempoTotalTreino(Integer tempoTotalTreino) {
        this.tempoTotalTreino = tempoTotalTreino;
    }
    public Double getMediaExerciciosPorTreino() {
        return mediaExerciciosPorTreino;
    }
    public void setMediaExerciciosPorTreino(Double mediaExerciciosPorTreino) {
        this.mediaExerciciosPorTreino = mediaExerciciosPorTreino;
    }
    public LocalDateTime getUltimoTreino() {
        return ultimoTreino;
    }
    public void setUltimoTreino(LocalDateTime ultimoTreino) {
        this.ultimoTreino = ultimoTreino;
    }

    
}

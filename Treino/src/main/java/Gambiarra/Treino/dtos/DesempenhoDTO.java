package Gambiarra.Treino.dtos;

public class DesempenhoDTO {
    private String usuarioId;
    private Integer duracaoMinutos;
    private Integer exerciciosRealizados;
    
    public String getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
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

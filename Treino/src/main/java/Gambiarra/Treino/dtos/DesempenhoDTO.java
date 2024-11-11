package Gambiarra.Treino.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesempenhoDTO {
    private int frequenciaTotal;
    private double mediaTreinos;
    private List<Integer> treinosSemanais;
    private List<Integer> treinosMensais;
    private LocalDateTime ultimoTreino;
    private String treinoMaisFrequente;

    
    public DesempenhoDTO(int frequenciaTotal, double mediaTreinos, List<Integer> treinosSemanais,
            List<Integer> treinosMensais, LocalDateTime ultimoTreino, String treinoMaisFrequente) {
        this.frequenciaTotal = frequenciaTotal;
        this.mediaTreinos = mediaTreinos;
        this.treinosSemanais = treinosSemanais;
        this.treinosMensais = treinosMensais;
        this.ultimoTreino = ultimoTreino;
        this.treinoMaisFrequente = treinoMaisFrequente;
    }
    public DesempenhoDTO() {
    }
    public int getFrequenciaTotal() {
        return frequenciaTotal;
    }
    public void setFrequenciaTotal(int frequenciaTotal) {
        this.frequenciaTotal = frequenciaTotal;
    }
    public double getMediaTreinos() {
        return mediaTreinos;
    }
    public void setMediaTreinos(double mediaTreinos) {
        this.mediaTreinos = mediaTreinos;
    }
    public List<Integer> getTreinosSemanais() {
        return treinosSemanais;
    }
    public void setTreinosSemanais(List<Integer> treinosSemanais) {
        this.treinosSemanais = treinosSemanais;
    }
    public List<Integer> getTreinosMensais() {
        return treinosMensais;
    }
    public void setTreinosMensais(List<Integer> treinosMensais) {
        this.treinosMensais = treinosMensais;
    }
    public LocalDateTime getUltimoTreino() {
        return ultimoTreino;
    }
    public void setUltimoTreino(LocalDateTime ultimoTreino) {
        this.ultimoTreino = ultimoTreino;
    }
    public String getTreinoMaisFrequente() {
        return treinoMaisFrequente;
    }
    public void setTreinoMaisFrequente(String treinoMaisFrequente) {
        this.treinoMaisFrequente = treinoMaisFrequente;
    }

    public void preencherDiasVazios() {
        if (treinosSemanais == null) {
            treinosSemanais = new ArrayList<>(Collections.nCopies(7, 0));
        }
        if (treinosMensais == null) {
            treinosMensais = new ArrayList<>(Collections.nCopies(30, 0));
        }
    }

    
}

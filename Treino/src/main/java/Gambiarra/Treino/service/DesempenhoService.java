package Gambiarra.Treino.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Gambiarra.Treino.Repository.DesempenhoRepository;
import Gambiarra.Treino.Repository.TreinoRepository;
import Gambiarra.Treino.Repository.UsuarioRepository;
import Gambiarra.Treino.dtos.DesempenhoDTO;
import Gambiarra.Treino.dtos.EstatisticasDTO;
import Gambiarra.Treino.model.Desempenho;

@Service
public class DesempenhoService {

    @Autowired
    private DesempenhoRepository desempenhoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Desempenho registrarDesempenho(DesempenhoDTO dados) {
        Desempenho desempenho = new Desempenho();
        desempenho.setUsuario(usuarioRepository.findById(dados.getUsuarioId()).orElseThrow());
        desempenho.setDataTreino(LocalDateTime.now());
        desempenho.setDuracaoMinutos(dados.getDuracaoMinutos());
        desempenho.setExerciciosRealizados(dados.getExerciciosRealizados());
        
        return desempenhoRepository.save(desempenho);
    }

    public EstatisticasDTO obterEstatisticas(String usuarioId) {
        List<Desempenho> desempenhos = desempenhoRepository.findByUsuarioId(usuarioId);
        
        EstatisticasDTO estatisticas = new EstatisticasDTO();
        estatisticas.setTreinosCompletos(desempenhos.size());
        estatisticas.setTempoTotalTreino(desempenhos.stream()
            .mapToInt(Desempenho::getDuracaoMinutos)
            .sum());
        estatisticas.setMediaExerciciosPorTreino(desempenhos.stream()
            .mapToDouble(Desempenho::getExerciciosRealizados)
            .average()
            .orElse(0.0));
        estatisticas.setUltimoTreino(desempenhos.stream()
            .map(Desempenho::getDataTreino)
            .max(LocalDateTime::compareTo)
            .orElse(null));
            
        return estatisticas;
    }
}
package Gambiarra.Treino.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Gambiarra.Treino.Repository.DesempenhoRepository;
import Gambiarra.Treino.Repository.TreinoRepository;
import Gambiarra.Treino.dtos.DesempenhoDTO;

@Service
public class DesempenhoService {

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private DesempenhoRepository desempenhoRepository;

    public DesempenhoDTO getDesempenhoUsuario(String userId) {
        DesempenhoDTO desempenho = new DesempenhoDTO();
        
        // Calcular frequência total de treinos do usuário
        desempenho.setFrequenciaTotal(treinoRepository.countByUsuarioId(userId));
        
        // Calcular média semanal baseada nos treinos existentes
        desempenho.setMediaTreinos(calcularMediaSemanal(userId));
        
        // Obter distribuição dos treinos na semana atual
        desempenho.setTreinosSemanais(obterTreinosSemanais(userId));
        
        // Obter distribuição dos treinos no mês atual
        desempenho.setTreinosMensais(obterTreinosMensais(userId));
        
        return desempenho;
    }

    private List<Integer> obterTreinosSemanais(String userId) {
        LocalDate hoje = LocalDate.now();
        LocalDate inicioDaSemana = hoje.with(DayOfWeek.SUNDAY);
        return treinoRepository.countTreinosPorDiaSemana(userId, inicioDaSemana);
    }

    private List<Integer> obterTreinosMensais(String userId) {
        LocalDate hoje = LocalDate.now();
        LocalDate inicioDoMes = hoje.withDayOfMonth(1);
        return treinoRepository.countTreinosPorDiaMes(userId, inicioDoMes);
    }

    private double calcularMediaSemanal(String userId) {
        List<Integer> treinosSemana = obterTreinosSemanais(userId);
        if (treinosSemana.isEmpty()) return 0.0;
        
        int totalTreinos = treinosSemana.stream().mapToInt(Integer::intValue).sum();
        return (double) totalTreinos / 7;
    }

    
}

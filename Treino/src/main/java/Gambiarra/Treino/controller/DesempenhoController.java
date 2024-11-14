package Gambiarra.Treino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Gambiarra.Treino.dtos.DesempenhoDTO;
import Gambiarra.Treino.dtos.EstatisticasDTO;
import Gambiarra.Treino.model.Desempenho;
import Gambiarra.Treino.service.DesempenhoService;

@RestController
@RequestMapping("/api/desempenho")
public class DesempenhoController {

    @Autowired
    private DesempenhoService desempenhoService;

    @PostMapping("/registrar")
    public ResponseEntity<Desempenho> registrarDesempenho(@RequestBody DesempenhoDTO dados) {
        return ResponseEntity.ok(desempenhoService.registrarDesempenho(dados));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<EstatisticasDTO> obterEstatisticas(@PathVariable String id) {
        return ResponseEntity.ok(desempenhoService.obterEstatisticas(id));
    }
}
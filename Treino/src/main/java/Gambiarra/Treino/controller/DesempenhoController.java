package Gambiarra.Treino.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Gambiarra.Treino.dtos.DesempenhoDTO;
import Gambiarra.Treino.service.DesempenhoService;

@RestController
@RequestMapping("/api/desempenho")
public class DesempenhoController {

    @Autowired
    private DesempenhoService desempenhoService;

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<DesempenhoDTO> getDesempenhoUsuario(@PathVariable String userId) {
        try {
            DesempenhoDTO desempenho = desempenhoService.getDesempenhoUsuario(userId);
            return ResponseEntity.ok(desempenho);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
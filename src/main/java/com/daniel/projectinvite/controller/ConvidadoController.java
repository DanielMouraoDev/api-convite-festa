package com.daniel.projectinvite.controller;

import com.daniel.projectinvite.dto.ConvidadoRequestDTO;
import com.daniel.projectinvite.dto.ConvidadoResponseDTO;
import com.daniel.projectinvite.model.StatusPresenca;
import com.daniel.projectinvite.service.ConvidadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/convidados")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ConvidadoController {

    private final ConvidadoService service;

    @PostMapping
    public ResponseEntity<ConvidadoResponseDTO> registrar(@RequestBody @Valid ConvidadoRequestDTO dto) {
        ConvidadoResponseDTO response = service.registrarConvidado(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ConvidadoResponseDTO>> listarTodos() {
        List<ConvidadoResponseDTO> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ConvidadoResponseDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusPresenca novoStatus) {

        ConvidadoResponseDTO response = service.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(response);
    }
}
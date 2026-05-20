package com.daniel.projectinvite.service;

import com.daniel.projectinvite.dto.ConvidadoRequestDTO;
import com.daniel.projectinvite.dto.ConvidadoResponseDTO;
import com.daniel.projectinvite.model.Convidado;
import com.daniel.projectinvite.model.StatusPresenca;
import com.daniel.projectinvite.repository.ConvidadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConvidadoService {

    private final ConvidadoRepository repository;

    @Transactional
    public ConvidadoResponseDTO registrarConvidado(ConvidadoRequestDTO dto) {
        Convidado convidado = new Convidado();
        convidado.setNome(dto.nome());
        convidado.setStatusPresenca(dto.statusPresenca());

        Convidado salvo = repository.save(convidado);
        return new ConvidadoResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<ConvidadoResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(ConvidadoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ConvidadoResponseDTO atualizarStatus(Long id, StatusPresenca novoStatus) {
        Convidado convidado = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Convidado não encontrado com o ID: " + id));

        convidado.setStatusPresenca(novoStatus);
        Convidado atualizado = repository.save(convidado);
        return new ConvidadoResponseDTO(atualizado);
    }

    @Transactional
    public void deletarConvidado(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Não é possível deletar. Convidado não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }
}
package com.daniel.projectinvite.dto;

import com.daniel.projectinvite.model.Convidado;
import com.daniel.projectinvite.model.StatusPresenca;

public record ConvidadoResponseDTO(
        Long id,
        String nome,
        StatusPresenca statusPresenca
) {
    // Esse construtor extra ajuda a transformar a Entidade em DTO facilmente mais tarde
    public ConvidadoResponseDTO(Convidado convidado) {
        this(convidado.getId(), convidado.getNome(), convidado.getStatusPresenca());
    }
}

package com.daniel.projectinvite.dto;

import com.daniel.projectinvite.model.StatusPresenca;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConvidadoRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "O status de presença é obrigatório")
        StatusPresenca statusPresenca
) {}
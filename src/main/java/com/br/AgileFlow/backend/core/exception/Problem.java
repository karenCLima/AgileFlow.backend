package com.br.AgileFlow.backend.core.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Schema(name = "Problema")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    @Schema(example = "400")
    private Integer status;

    @Schema(example = "dados-inválidos")
    private String type;

    @Schema(example = "Dados inválidos")
    private String title;

    @Schema(example = "Um ou mais campos estão inválidos.")
    private String detail;

    @Schema(example = "Operação não permitida")
    private String userMessage;

    @Schema(example = "2025-01-26T12:25:50.90284131Z")
    private LocalDateTime timestamp;

    @Schema(description = "Lista de objetos ou campos que geraram o erro.")
    private List<Object> objects;

    @Getter
    @Builder
    public static class Object {
        @Schema(example = "categoria")
        private String name;

        @Schema(example = "categoria é obrigatória")
        private String userMessage;
    }
}

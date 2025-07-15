package com.glara.msvcsubscription.config;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Petición Inválida (Bad Request)",
                content = @Content(schema = @Schema(implementation = Void.class))),

        @ApiResponse(responseCode = "401", description = "No Autorizado (Unauthorized)",
                content = @Content(schema = @Schema(implementation = Void.class))),

        @ApiResponse(responseCode = "403", description = "Acceso Prohibido (Forbidden)",
                content = @Content(schema = @Schema(implementation = Void.class))),

        @ApiResponse(responseCode = "404", description = "Recurso no Encontrado (Not Found)",
                content = @Content(schema = @Schema(implementation = Void.class))),

        @ApiResponse(responseCode = "500", description = "Error Interno del Servidor",
                content = @Content(schema = @Schema(implementation = Void.class)))
})
public @interface ApiStandardErrors {
}

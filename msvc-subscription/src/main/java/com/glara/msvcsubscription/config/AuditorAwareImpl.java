package com.glara.msvcsubscription.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.lang.NonNull;
import java.util.Optional;

/*
* Se utiliza para obtener el usuario autenticado y registrarlo como auditor en las entidades JPA.
* */
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    @NonNull
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        // Verifica si el principal es un objeto Jwt
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            return Optional.of(jwt.getSubject());
        }

        // Si no es un Jwt, podría ser un UserDetails (para tests, por ejemplo)
        // Este es un fallback, pero el caso principal será el de arriba.
        if (authentication.getPrincipal() instanceof User user) {
            return Optional.of(user.getUsername());
        }

        // Si no se puede determinar el usuario, devuelve un valor por defecto o vacío
        return Optional.of("system");
    }
}

package io.cone;

import jakarta.enterprise.context.ApplicationScoped;

public record User(String username,
        String email) {
}

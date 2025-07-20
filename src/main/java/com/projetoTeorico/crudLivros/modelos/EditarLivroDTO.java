package com.projetoTeorico.crudLivros.modelos;

public record EditarLivroDTO(
        String livroNome,
        String livroAutor,
        Integer livroAnoPublicacao,
        String livroGenero) {
}

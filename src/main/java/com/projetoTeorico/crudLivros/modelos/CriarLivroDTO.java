package com.projetoTeorico.crudLivros.modelos;

public record CriarLivroDTO(
        String livroNome,
        String livroAutor,
        Integer livroAnoPublicacao,
        String livroGenero) {
}

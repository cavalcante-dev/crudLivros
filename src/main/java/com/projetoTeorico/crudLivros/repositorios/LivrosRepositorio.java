package com.projetoTeorico.crudLivros.repositorios;

import com.projetoTeorico.crudLivros.entidades.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivrosRepositorio extends JpaRepository<Livro, UUID> {
}

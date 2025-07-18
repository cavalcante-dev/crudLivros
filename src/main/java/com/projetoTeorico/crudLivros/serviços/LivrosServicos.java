package com.projetoTeorico.crudLivros.serviços;

import com.projetoTeorico.crudLivros.entidades.Livro;
import com.projetoTeorico.crudLivros.modelos.CriarLivroDTO;
import com.projetoTeorico.crudLivros.repositorios.LivrosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LivrosServicos {

    @Autowired
    private LivrosRepositorio livrosRepositorio;

    public UUID cadastrarLivro(CriarLivroDTO criarLivroDTO) {

        // Tem a função de tranformar o DTO em uma Entidade
        var novoLivroEntidade = new Livro(
                UUID.randomUUID(),
                criarLivroDTO.livroNome(),
                criarLivroDTO.livroAutor(),
                criarLivroDTO.livroAnoPublicacao(),
                criarLivroDTO.livroGenero()
        );

        var novoLivroCriado = livrosRepositorio.save(novoLivroEntidade);
        return novoLivroCriado.getLivroID();

    }

    public List<Livro> listarLivros() {
        return livrosRepositorio.findAll();
    }

    public Optional<Livro> encontrarLivroByID(String livroID) {
        var livro = livrosRepositorio.findById(UUID.fromString(livroID));
        return livro;
    }

    public void deletarLivroByID(String livroID) {
        var id = UUID.fromString(livroID);
        var livroExiste = livrosRepositorio.existsById(id);
        if (livroExiste) {
            livrosRepositorio.deleteById(id);
        }
    }

}

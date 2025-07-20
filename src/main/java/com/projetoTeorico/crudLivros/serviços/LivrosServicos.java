package com.projetoTeorico.crudLivros.serviços;

import com.projetoTeorico.crudLivros.entidades.Livro;
import com.projetoTeorico.crudLivros.modelos.CriarLivroDTO;
import com.projetoTeorico.crudLivros.modelos.EditarLivroDTO;
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

    public List<Livro> encontrarLivrosByAutor(String livroAutor) {
        return livrosRepositorio.findByLivroAutorIgnoreCase(livroAutor);
    }

    public Optional<Livro> encontrarLivroByID(String livroID) {
        return livrosRepositorio.findById(UUID.fromString(livroID));
    }

    public List<Livro> encontrarLivrosByGenero(String livroGenero) {
        return livrosRepositorio.findByLivroGeneroIgnoreCase(livroGenero);
    }

    public void deletarLivroByID(String livroID) {
        var id = UUID.fromString(livroID);
        var livroExiste = livrosRepositorio.existsById(id);
        if (livroExiste) {
            livrosRepositorio.deleteById(id);
        }
    }

    public void editarLivroByID(String livroID, EditarLivroDTO editarLivrDTO) {

        var id = UUID.fromString(livroID);
        var livroEntidade = livrosRepositorio.findById(id);

        if (livroEntidade.isPresent()) {
            var livro = livroEntidade.get();
            if (editarLivrDTO.livroNome() != null) {
                livro.setLivroNome(editarLivrDTO.livroNome());
            }
            if (editarLivrDTO.livroGenero() != null) {
                livro.setLivroGenero(editarLivrDTO.livroGenero());
            }
            if (editarLivrDTO.livroAutor() != null) {
                livro.setLivroAutor(editarLivrDTO.livroAutor());
            }
            if (editarLivrDTO.livroAnoPublicacao() != null) {
                livro.setLivroAnoPublicacao(editarLivrDTO.livroAnoPublicacao());
            }
            livrosRepositorio.save(livro);
        }

    }

}

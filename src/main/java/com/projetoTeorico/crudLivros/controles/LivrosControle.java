package com.projetoTeorico.crudLivros.controles;

import com.projetoTeorico.crudLivros.entidades.Livro;
import com.projetoTeorico.crudLivros.modelos.CriarLivroDTO;
import com.projetoTeorico.crudLivros.serviços.LivrosServicos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/livros")
public class LivrosControle {

    @Autowired
    private LivrosServicos livrosServicos;

    @PostMapping
    public ResponseEntity<Livro> cadastrarLivros(@RequestBody CriarLivroDTO criarLivroDTO) {
        var livroID = livrosServicos.cadastrarLivro(criarLivroDTO);
        return ResponseEntity.created(URI.create("/v1/livros/" + livroID.toString())).build();
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros() {
        var livros = livrosServicos.listarLivros();
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/livroAutor/{livroAutor}")
    public ResponseEntity<List<Livro>> encontrarLivrosByAutor(@PathVariable("livroAutor") String livroAutor) {
        List<Livro> livros = livrosServicos.encontrarLivrosByAutor(livroAutor);
        if (!livros.isEmpty()) {
            return ResponseEntity.ok(livros);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/livroGenero/{livroGenero}")
    public ResponseEntity<List<Livro>> encontrarLivrosByGenero(@PathVariable("livroGenero") String livroGenero) {
        List<Livro> livros = livrosServicos.encontrarLivrosByGenero(livroGenero);
        if (!livros.isEmpty()) {
            return ResponseEntity.ok(livros);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{livroID}")
    public ResponseEntity<Livro> encontrarLivroByID(@PathVariable("livroID") String livroID) {
        var livro = livrosServicos.encontrarLivroByID(livroID);
        if (livro.isPresent()) {
            return ResponseEntity.ok(livro.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{livroID}")
    public ResponseEntity<Livro> deletarLivroByID(@PathVariable("livroID") String livroID) {
        livrosServicos.deletarLivroByID(livroID);
        return ResponseEntity.noContent().build();
    }

}

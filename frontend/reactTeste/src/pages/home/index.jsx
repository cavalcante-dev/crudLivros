import { useEffect, useState, useRef, use } from 'react'
import './style.css'
import api from '../../services/api'

function Home() {

  const [livros, setLivros] = useState([])
  const [livroEditando, setLivroEditando] = useState(null)

  const inputNome = useRef()
  const inputAutor = useRef()
  const inputAno = useRef()
  const inputGenero = useRef()

  const inputNomeEdit = useRef()
  const inputAutorEdit = useRef()
  const inputAnoEdit = useRef()
  const inputGeneroEdit = useRef()

  async function getLivros() {
    const livrosFromAPI = await api.get('/v1/livros')
    setLivros(livrosFromAPI.data)
  }

  async function createLivros() {
    await api.post('/v1/livros', {
      livroNome: inputNome.current.value,
      livroAutor: inputAutor.current.value,
      livroAnoPublicacao: inputAno.current.value,
      livroGenero: inputGenero.current.value,
    })
    getLivros()
  }

  async function deleteLivro(livroID) {
    await api.delete(`/v1/livros/${livroID}`)
    getLivros()
  }

  async function editLivro(livroID) {
    await api.put(`/v1/livros/${livroID}`, {
      livroNome: inputNomeEdit.current.value,
      livroAutor: inputAutorEdit.current.value,
      livroAnoPublicacao: inputAnoEdit.current.value,
      livroGenero: inputGeneroEdit.current.value,
    })
    getLivros()
  }

  function abrirModalEditar(modalEditar, livroID) {
    setLivroEditando(livroID);
    const modal = document.getElementById(modalEditar)
    modal.showModal();
  }

  const botoesFecharModal = document.querySelectorAll('.fecharModalEditar')

  botoesFecharModal.forEach(botao => {

    botao.addEventListener('click', () => {

      const modalID = botao.getAttribute('data-modal')
      const modal = document.getElementById(modalID)
      setLivroEditando(null)

      modal.close();

    })

  });

  useEffect(() => {
    getLivros()
  }, [])


  return (

    <>
      <div className='container'>

        <div className='containerFormulario'>
          <form>
            <h1>ADICIONAR NOVO LIVRO</h1>
            <input placeholder='Nome...' type="text" ref={inputNome} />
            <input placeholder='Autor...' type="text" ref={inputAutor} />
            <input placeholder='Ano de Publicação...' type="number" ref={inputAno} />
            <input placeholder='Gênero...' type="text" ref={inputGenero} />
            <button type='button' onClick={createLivros}> CADASTRAR LIVRO </button>
          </form>
        </div>

        <div id='containerLivros'>


          <div id='gridLivros'>

            {livros.map((livro) => (
              <div className='livro'>
                <p><b>Nome:</b> <br /> {livro.livroNome}</p>
                <p><b>Autor:</b> <br /> {livro.livroAutor}</p>
                <p><b>Ano de Publicação: </b> <br /> {livro.livroAnoPublicacao}</p>
                <p><b>Gênero:</b> <br /> {livro.livroGenero}</p>
                <div>
                  <button onClick={() => deleteLivro(livro.livroID)}>Excluir</button>
                  <button onClick={() => abrirModalEditar('modalEditar', livro.livroID)}>Editar</button>
                </div>
              </div>
            ))}

          </div>

        </div>

        <dialog id='modalEditar' className='containerFormulario'>
          <form>
            <h1>EDITAR LIVRO</h1>
            <input placeholder='Nome...' type="text" ref={inputNomeEdit} />
            <input placeholder='Autor...' type="text" ref={inputAutorEdit} />
            <input placeholder='Ano de Publicação...' type="number" ref={inputAnoEdit} />
            <input placeholder='Gênero...' type="text" ref={inputGeneroEdit} />
            <button
              type='button'
              onClick={() => { editLivro(livroEditando) }}
            >
              SALVAR EDIÇÃO
            </button>
            <button type='button' className='fecharModalEditar' data-modal='modalEditar'>Fechar</button>
          </form>
        </dialog>

      </div>
    </>

  )

}

export default Home

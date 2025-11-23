package br.com.carlos.seelfapi.service;

import br.com.carlos.seelfapi.model.Conquista;
import br.com.carlos.seelfapi.model.Usuario;
import br.com.carlos.seelfapi.repository.ConquistaRepository;
import br.com.carlos.seelfapi.repository.ProdutoRepository;
import br.com.carlos.seelfapi.repository.TaskRepository;
import br.com.carlos.seelfapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamificationService {

    @Autowired
    private ConquistaRepository conquistaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private TaskRepository taskRepository;

    // Método auxiliar para pegar o "Usuário Padrão" (ID 1) para o protótipo
    private Usuario getUsuarioPadrao() {
        return usuarioRepository.findById(1L).orElse(null);
    }

    public void verificarConquistaPrimeiroProduto() {
        Usuario user = getUsuarioPadrao();
        if (user == null) return; // Se não tiver usuário cadastrado, ignora

        // Se tiver exatamente 1 produto no banco, é o primeiro!
        if (produtoRepository.count() == 1) {
            darConquista(user, "Primeiro Produto!", "Você cadastrou seu primeiro item no estoque.", "box");
        }
    }

    public void verificarConquistaPrimeiraTarefa() {
        Usuario user = getUsuarioPadrao();
        if (user == null) return;

        // Se tiver exatamente 1 tarefa concluída
        if (taskRepository.countByStatus("completed") == 1) {
            darConquista(user, "Produtividade Iniciada", "Você completou sua primeira tarefa.", "check-circle");
        }
    }

    private void darConquista(Usuario user, String titulo, String descricao, String icone) {
        // Verifica se já não ganhou essa conquista antes
        if (!conquistaRepository.existsByUsuarioIdAndTitulo(user.getId(), titulo)) {
            Conquista conquista = new Conquista();
            conquista.setUsuario(user);
            conquista.setTitulo(titulo);
            conquista.setDescricao(descricao);
            conquista.setIcone(icone);
            conquistaRepository.save(conquista);
            System.out.println("NOVA CONQUISTA DESBLOQUEADA: " + titulo);
        }
    }
}
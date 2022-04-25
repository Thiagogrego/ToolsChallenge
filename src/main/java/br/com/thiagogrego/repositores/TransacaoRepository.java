package br.com.thiagogrego.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thiagogrego.model.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, String>{

}

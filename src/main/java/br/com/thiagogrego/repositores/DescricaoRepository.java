package br.com.thiagogrego.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thiagogrego.model.Descricao;

@Repository
public interface DescricaoRepository extends JpaRepository<Descricao, String> {

}
 
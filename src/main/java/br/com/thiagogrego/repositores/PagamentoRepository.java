package br.com.thiagogrego.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thiagogrego.model.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, String> {

}

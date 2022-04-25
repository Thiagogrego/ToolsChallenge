package br.com.thiagogrego.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thiagogrego.model.Estorno;

@Repository
public interface EstornoRepository extends JpaRepository<Estorno, String> {

}

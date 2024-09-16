package spring_security.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_security.spring_security.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByLogin(String login);
}

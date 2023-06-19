package assitenciatecnica.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import assitenciatecnica.backend.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}

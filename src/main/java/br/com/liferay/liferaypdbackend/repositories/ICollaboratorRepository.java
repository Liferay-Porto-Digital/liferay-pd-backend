package br.com.liferay.liferaypdbackend.repositories;

import br.com.liferay.liferaypdbackend.models.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICollaboratorRepository extends JpaRepository<Collaborator, UUID> {
}

package br.com.liferay.liferaypdbackend.repositories;

import br.com.liferay.liferaypdbackend.models.CollaboratorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CollaboratorRepository extends JpaRepository<CollaboratorModel, UUID> {

    @Query(value = "select collaborator from CollaboratorModel collaborator where collaborator.name = :name")
    Optional<CollaboratorModel> findByName(@Param(value = "name") String name);
}

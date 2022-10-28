package br.com.liferay.liferaypdbackend.repositories;

import br.com.liferay.liferaypdbackend.models.product.FormModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<FormModel, UUID> {
    @Query(value = "select form from FormModel form order by form.dateOfCreation desc")
    List<FormModel> findAllAndOrderByNewer();

    @Query(value = "select form from FormModel form order by form.dateOfCreation")
    List<FormModel> findAllAndOrderByOlder();

    @Query(value = "select form from FormModel form where form.type = 'donation'")
    List<FormModel> findAllDonation();

    @Query(value = "select form from FormModel form where form.type = 'activity'")
    List<FormModel> findAllActivity();

    @Query(value = "select form from FormModel form where form.Institution = " +
            "(select institution.id from InstitutionModel institution where institution.name = :institutionName)")
    List<FormModel> findAllByInstitutionName(@Param(value = "institutionName") String institutionName);

    @Query(value = "select form from FormModel form where form.Collaborator = " +
            "(select collaborator.id from CollaboratorModel collaborator where collaborator.name = :collaboratorName)")
    List<FormModel> findAllByCollaboratorName(@Param(value = "collaboratorName") String collaboratorName);
}

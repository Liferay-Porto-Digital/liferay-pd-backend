package br.com.liferay.liferaypdbackend.repositories;

import br.com.liferay.liferaypdbackend.models.ReportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<ReportModel, UUID> {

    @Query(value = "select count(form) from FormModel form where form.type = 'donation'")
    Integer countAllDonations();

    @Query(value = "select count(form) from FormModel form where form.type = 'activity'")
    Integer countAllActivities();

    @Query(value = "select count(institution) from InstitutionModel institution")
    Integer countAllInstitutions();

    @Query(value = "select sum(form.value) from FormModel form where form.type = 'donation'")
    Double countDonationAmount();

    @Query(value = "select sum(form.value) from FormModel form where form.type = 'activity'")
    Double countActivityAmount();

    @Query(value = "select report from ReportModel report order by report.reportUpdateDate desc")
    List<ReportModel> findAllDescending();
}

package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.ReportModel;
import br.com.liferay.liferaypdbackend.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    //region INJECTIONS
    @Autowired
    ReportRepository reportRepository;
    //endregion

    //region METHODS
    public ReportModel save(ReportModel reportModel) {
        return reportRepository.save(reportModel);
    }

    public ReportModel saveOrGetReport() {
        ReportModel reportModel = new ReportModel (
                reportRepository.countAllDonations(),
                reportRepository.countDonationAmount(),
                reportRepository.countDonationAmount() /
                        reportRepository.countAllInstitutions(),
                reportRepository.countAllActivities(),
                reportRepository.countActivityAmount(),
                reportRepository.countActivityAmount() /
                        reportRepository.countAllInstitutions()
        );

        if (getReportWithSameFields().isPresent()) {
            return getReportWithSameFields().get();
        }

        return save(reportModel);
    }

    public List<ReportModel> findAll() {
        return reportRepository.findAllDescending();
    }

    public Optional<ReportModel> getReportWithSameFields() {
        return reportRepository.findReportWithSameFields(
                reportRepository.countAllDonations(),
                reportRepository.countDonationAmount(),
                reportRepository.countDonationAmount() /
                        reportRepository.countAllInstitutions(),
                reportRepository.countAllActivities(),
                reportRepository.countActivityAmount(),
                reportRepository.countActivityAmount() /
                        reportRepository.countAllInstitutions()
        );
    }
    //endregion
}

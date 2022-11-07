package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.ReportModel;
import br.com.liferay.liferaypdbackend.repositories.ReportRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReportService {
    //region INJECTIONS
    final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    //endregion

    //region METHODS
    @Transactional
    public ReportModel save() {
        return reportRepository.save (
                new ReportModel (
                        reportRepository.countAllDonations(),
                        reportRepository.countDonationAmount(),
                        reportRepository.countDonationAmount() /
                                reportRepository.countAllInstitutions(),
                        reportRepository.countAllActivities(),
                        reportRepository.countActivityAmount(),
                        reportRepository.countActivityAmount() /
                                reportRepository.countAllInstitutions()
                )
        );
    }

    public List<ReportModel> findAll() {
        return reportRepository.findAllDescending();
    }
    //endregion
}

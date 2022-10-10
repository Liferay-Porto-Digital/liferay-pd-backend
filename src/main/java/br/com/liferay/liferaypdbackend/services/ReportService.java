package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.ReportModel;
import br.com.liferay.liferaypdbackend.models.product.FormModel;
import br.com.liferay.liferaypdbackend.repositories.IReportRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportService {

    //region INJECTIONS
    final IReportRepository reportRepository;

    public ReportService(IReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    //endregion

    //region METHODS

    /**
     * Save report in database
     * @param reportModel
     */
    @Transactional
    @Modifying
    public void saveReport(ReportModel reportModel) {
        reportRepository.save(reportModel);
    }

    /**
     * Get all report from the database
     * @return List
     */
    public List<ReportModel> getAllReports() {
        return reportRepository.findAll();
    }
    //endregion
}

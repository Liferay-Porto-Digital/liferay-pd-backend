package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.repositories.IReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    //region INJECTIONS
    final IReportRepository reportRepository;

    public ReportService(IReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }
    //endregion
}

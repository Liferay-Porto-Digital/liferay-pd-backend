package br.com.liferay.liferaypdbackend.services;

import br.com.liferay.liferaypdbackend.models.ReportModel;
import br.com.liferay.liferaypdbackend.repositories.ReportRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportServiceTest {
    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private ReportService reportService;

    @Before
    public void initService() {
        MockitoAnnotations.openMocks(this); //inicializar as dependencias do mock
    }

    private List<ReportModel> generateRepositoryReport() {
        List<ReportModel> list = new ArrayList<>();
        ReportModel report1 = new ReportModel (
                10,
                430.00,
                100.00,
                12,
                20.00,
                2.00
        );
        ReportModel report2 = new ReportModel (
                12,
                450.00,
                110.00,
                12,
                20.00,
                2.00
        );
        list.add(report1);
        list.add(report2);
        return list;
    }

    //tesde do save()
    @Test
    public void whenSaveReportThenReturnReportModel() {
        //Execution
        when(reportRepository.save(Mockito.any())).thenReturn(generateRepositoryReport().get(0));
        ReportModel reportModel = new ReportModel();
        Assertions.assertNotNull(reportService.save(reportModel));
        Assertions.assertEquals(10,reportService.save(reportModel).getNumberOfDonations());
    }

    //teste do findAll()
    @Test
    public void whenFindAllReportThenReturnFindAllDescending() {
        //execution
        when(reportRepository.findAllDescending()).thenReturn(generateRepositoryReport());

        //condition
        Assertions.assertNotNull(reportService.findAll());
        Assertions.assertEquals(10,reportService.findAll().get(0).getNumberOfDonations());
    }
}

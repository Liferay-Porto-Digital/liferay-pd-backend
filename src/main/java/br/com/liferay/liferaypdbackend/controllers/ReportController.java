package br.com.liferay.liferaypdbackend.controllers;

import br.com.liferay.liferaypdbackend.models.ReportModel;
import br.com.liferay.liferaypdbackend.services.ReportService;
import br.com.liferay.liferaypdbackend.services.utils.ConsoleLogUtil;
import br.com.liferay.liferaypdbackend.services.utils.ExportExcelReportUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("api/v1/")
public class ReportController {
    //region INJECTIONS
    final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    //endregion

    //region ENDPOINTS
    @GetMapping("report")
    @ApiOperation(value = "Gets the most recent report values")
    public ResponseEntity<Object> getRecentReport() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(reportService.save());
        } catch (Exception e) {
            ConsoleLogUtil.log.info(Arrays.toString(e.getStackTrace()));
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("CONFLICT: Error while trying to save report. Verify fields before continuing.");
        }
    }

    @GetMapping("report/export/excel")
    @ApiOperation(value = "Download report in excel file")
    public ResponseEntity<Object> exportExcelFile(HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=report" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            List<ReportModel> reportModelList = reportService.findAll();

            ExportExcelReportUtil excelExporter = new ExportExcelReportUtil(reportModelList);

            excelExporter.export(response);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("SUCCESS: Report exported successfully");
        } catch (Exception e) {
            ConsoleLogUtil.log.info(Arrays.toString(e.getStackTrace()));
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("CONFLICT: Error while trying to export report. Contact Support ASAP");
        }
    }
    //endregion
}

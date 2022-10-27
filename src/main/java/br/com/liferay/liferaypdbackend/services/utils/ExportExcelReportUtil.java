package br.com.liferay.liferaypdbackend.services.utils;

import br.com.liferay.liferaypdbackend.models.ReportModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ExportExcelReportUtil {
    //region VARIABLES
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<ReportModel> reportModelList;
    //endregion

    //region CONSTRUCTORS
    public ExportExcelReportUtil(List<ReportModel> reportModelList) {
        this.reportModelList = reportModelList;
        workbook = new XSSFWorkbook();
    }
    //endregion

    //region METHODS
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Reports");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Report ID", style);
        createCell(row, 1, "Update Date", style);
        createCell(row, 2, "Number Donations", style);
        createCell(row, 3, "Donated Amount", style);
        createCell(row, 4, "Donations Per Institution", style);
        createCell(row, 5, "Number Activities", style);
        createCell(row, 6, "Activity Amount", style);
        createCell(row, 7, "Activities Per Institution", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (ReportModel report : reportModelList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, report.getId().toString(), style);
            createCell(row, columnCount++, report.getReportUpdateDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), style);
            createCell(row, columnCount++, report.getNumberOfDonations(), style);
            createCell(row, columnCount++, report.getAmountDonated(), style);
            createCell(row, columnCount++, report.getDonationsPerInstitution(), style);
            createCell(row, columnCount++, report.getNumberOfActivities(), style);
            createCell(row, columnCount++, report.getAmountDoneActivity(), style);
            createCell(row, columnCount++, report.getActivityPerInstitution(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
    //endregion
}

package com.example.ecomon.service.file;

import static com.example.ecomon.common.ApplicationConstants.File.HEADERS;

import com.example.ecomon.dto.pollution.AggregatedPollution;
import com.example.ecomon.dto.pollution.PollutionRequest;
import com.example.ecomon.service.pollution.PollutionService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class PollutionFileService {

    private final PollutionService pollutionService;

    @Transactional
    public void save(MultipartFile file) throws IOException {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                writeRow(row);
            }
        }
    }

    public ByteArrayOutputStream export() throws IOException {
        List<AggregatedPollution> dataToExport = pollutionService.findAll();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            addHeader(sheet);
            writeAllData(dataToExport, sheet);

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            workbook.write(result);
            return result;
        }
    }

    private static void writeAllData(List<AggregatedPollution> data, Sheet sheet) {
        int rowNum = 1;
        for (AggregatedPollution exportDto : data) {
            rowNum = writeDataRow(exportDto, sheet, rowNum);
        }
    }

    private static int writeDataRow(AggregatedPollution exportDto, Sheet sheet, int rowNum) {
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(exportDto.id());
        row.createCell(1).setCellValue(exportDto.objectName());
        row.createCell(2).setCellValue(exportDto.objectDescription());
        row.createCell(3).setCellValue(exportDto.pollutantName());
        row.createCell(4).setCellValue(exportDto.valuePollution());
        row.createCell(5).setCellValue(exportDto.pollutantMfr());
        row.createCell(6).setCellValue(exportDto.pollutantElv());
        row.createCell(7).setCellValue(exportDto.pollutantTlv());
        row.createCell(8).setCellValue(exportDto.pollutionConcentration());
        row.createCell(9).setCellValue(exportDto.cr());
        row.createCell(10).setCellValue(exportDto.hq());
        row.createCell(11).setCellValue(exportDto.year());
        return rowNum;
    }

    private static void addHeader(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < HEADERS.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(HEADERS[i]);
        }
    }

    private PollutionRequest parseRowToPollutionDto(Row row) {
        Cell objectNameCell = row.getCell(0);
        Cell pollutantNameCell = row.getCell(1);
        Cell valuePollutionCell = row.getCell(2);
        Cell yearCell = row.getCell(3);
        Cell concentrationCell = row.getCell(4);

        String objectName = objectNameCell.getStringCellValue().trim();
        String pollutantName = pollutantNameCell.getStringCellValue().trim();
        double valuePollution = parseDoubleValue(valuePollutionCell);
        int year = (int) yearCell.getNumericCellValue();
        double pollutionConcentration = parseDoubleValue(concentrationCell);

        return new PollutionRequest(objectName, "Empty",
                pollutantName, year, valuePollution, pollutionConcentration, 0, 0, 0);
    }

    private void writeRow(Row row) {
        PollutionRequest pollutionRow = parseRowToPollutionDto(row);
        pollutionService.create(pollutionRow);
    }

    private double parseDoubleValue(Cell numericCell) {
        if (numericCell == null) {
            return 0;
        }
        if (numericCell.getCellType().equals(CellType.NUMERIC)) {
            return numericCell.getNumericCellValue();
        }
        String formattedValue = numericCell.getStringCellValue().replace(",", ".");
        return Double.parseDouble(formattedValue);
    }
}

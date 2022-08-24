package mn.edu.num.lotteryProject.utils;


import mn.edu.num.lotteryProject.entity.Customer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"Id", "FirstName", "LastName", "RegistrationNumber"};
    static String SHEET = "customer";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static ByteArrayInputStream customersToExcel(List<Customer> customers) {

        try (Workbook workbook = new XSSFWorkbook()) {
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                Sheet sheet = workbook.createSheet(SHEET);
//                XSSFSheet sheet = workbook.createSheet(SHEET);

                // Header
                Row headerRow = sheet.createRow(0);

                for (int col = 0; col < HEADERs.length; col++) {
                    Cell cell = headerRow.createCell(col);
                    cell.setCellValue(HEADERs[col]);
                }

                int rowIdx = 1;
                for (Customer customer : customers) {
                    Row row = sheet.createRow(rowIdx++);

                    row.createCell(0).setCellValue(customer.getId());
                    row.createCell(1).setCellValue(customer.getFirstName());
                    row.createCell(2).setCellValue(customer.getLastName());
                    row.createCell(3).setCellValue(customer.getPhoneNumber());
                    row.createCell(4).setCellValue(customer.getRegistrationNumber());

                }

                workbook.write(out);
                return new ByteArrayInputStream(out.toByteArray());
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static List<Customer> excelToCustomers(InputStream is) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            // Read customer data form excel file customer.
            XSSFSheet sheet = workbook.getSheetAt(0);

//            Workbook workbook = new XSSFWorkbook(is);
//            Sheet sheet = workbook.getSheet(SHEET);

            Iterator<Row> rows = sheet.iterator();

            List<Customer> customers = new ArrayList<Customer>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();

                Customer customer = new Customer();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            customer.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            customer.setFirstName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            customer.setLastName(currentCell.getStringCellValue());
                            break;

                        case 3:
                            customer.setPhoneNumber(currentCell.getStringCellValue());
                            break;
                        case 4:
                            customer.setRegistrationNumber(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                customers.add(customer);
            }
            workbook.close();
            return customers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
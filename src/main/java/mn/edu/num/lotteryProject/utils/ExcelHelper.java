package mn.edu.num.lotteryProject.utils;


import mn.edu.num.lotteryProject.entity.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
    static String[] HEADERs = {"Id", "Title", "Description", "Published"};
    static String SHEET = "Tutorials";

    public static boolean hasExcelFormat(MultipartFile file) {

        return TYPE.equals(file.getContentType());
    }

    public static ByteArrayInputStream usersToExcel(List<User> users) {

        try (Workbook workbook = new XSSFWorkbook()) {
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                Sheet sheet = workbook.createSheet(SHEET);

                // Header
                Row headerRow = sheet.createRow(0);

                for (int col = 0; col < HEADERs.length; col++) {
                    Cell cell = headerRow.createCell(col);
                    cell.setCellValue(HEADERs[col]);
                }

                int rowIdx = 1;
                for (User user : users) {
                    Row row = sheet.createRow(rowIdx++);

                    row.createCell(0).setCellValue(user.getId());
                    row.createCell(1).setCellValue(user.getUserName());
                    row.createCell(2).setCellValue(user.getEmail());
                    row.createCell(3).setCellValue(user.getFirstName());
                    row.createCell(4).setCellValue(user.getLastName());
                }

                workbook.write(out);
                return new ByteArrayInputStream(out.toByteArray());
            }
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    public static List<User> excelToUsers(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<User> users = new ArrayList<User>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();

                User user = new User();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            user.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            user.setFirstName(currentCell.getStringCellValue());
                            break;

                        case 2:
                            user.setLastName(currentCell.getStringCellValue());
                            break;

                        case 3:
                            user.setEmail(currentCell.getStringCellValue());
                            break;
                        case 4:
                            user.setUserName(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                users.add(user);
            }
            workbook.close();
            return users;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}


// LocalDateTime aar hiigeed yvsan ni amar bishuu?
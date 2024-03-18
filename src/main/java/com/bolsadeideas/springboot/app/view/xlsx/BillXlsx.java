package com.bolsadeideas.springboot.app.view.xlsx;

import com.bolsadeideas.springboot.app.models.entity.Bill;
import com.bolsadeideas.springboot.app.models.entity.ItemBill;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("bill/viewDetailsBill.xlsx")
public class BillXlsx extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //Change file name
        response.setHeader("Content-Disposition", "attachment; filename=\"bill_view.xlsx\"");

        //1-Obtain bill object and do cast
        Bill bill = (Bill) model.get("bill");

        //2-To create rows and columns
        Sheet sheet = workbook.createSheet("Bill Spring");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);

        cell.setCellValue("Customer Data");
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(bill.getCustomer().getName() + " " + bill.getCustomer().getSurname());

        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue(bill.getCustomer().getEmail());

        sheet.createRow(4).createCell(0).setCellValue("Bill Data");
        sheet.createRow(5).createCell(0).setCellValue("Invoice: " + bill.getId());
        sheet.createRow(6).createCell(0).setCellValue("Description: " + bill.getDescription());
        sheet.createRow(7).createCell(0).setCellValue("Date: " + bill.getCreateAt());

        //5-Cell style
        CellStyle tHeaderStyle = workbook.createCellStyle();
        tHeaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        tHeaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        tHeaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        tHeaderStyle.setBorderBottom(BorderStyle.MEDIUM);
        tHeaderStyle.setFillForegroundColor(IndexedColors.GOLD.index);
        tHeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        CellStyle tBodyStyle = workbook.createCellStyle();
        tBodyStyle.setBorderBottom(BorderStyle.THIN);
        tBodyStyle.setBorderBottom(BorderStyle.THIN);
        tBodyStyle.setBorderBottom(BorderStyle.THIN);
        tBodyStyle.setBorderBottom(BorderStyle.THIN);

        //3-Obtain bill details
        Row header = sheet.createRow(9);
        header.createCell(0).setCellValue("Product");
        header.createCell(1).setCellValue("Price");
        header.createCell(2).setCellValue("Amount");
        header.createCell(3).setCellValue("Total");

        header.getCell(0).setCellStyle(tHeaderStyle);
        header.getCell(1).setCellStyle(tHeaderStyle);
        header.getCell(2).setCellStyle(tHeaderStyle);
        header.getCell(3).setCellStyle(tHeaderStyle);

        //4-Create counter
        int rowNum = 10;

        for (ItemBill item : bill.getItems()) {
            Row line = sheet.createRow(rowNum++);
            cell = line.createCell(0);
            //Create cell for each attribute
            line.createCell(0).setCellValue(item.getProduct().getName());
            cell.setCellStyle(tBodyStyle);

            cell = line.createCell(1);
            cell.setCellValue(item.getProduct().getPrice());
            cell.setCellStyle(tBodyStyle);

            cell = line.createCell(2);
            cell.setCellValue(item.getAmount());
            cell.setCellStyle(tBodyStyle);

            cell = line.createCell(3);
            cell.setCellValue(item.calculateAmount());
            cell.setCellStyle(tBodyStyle);
        }
        Row totalLine = sheet.createRow(rowNum);
        cell = totalLine.createCell(2);
        cell.setCellValue("GrandTotal: ");
        cell.setCellStyle(tBodyStyle);

        cell = totalLine.createCell(3);
        totalLine.createCell(3).setCellValue(bill.getTotal());
        cell.setCellStyle(tBodyStyle);
    }
}

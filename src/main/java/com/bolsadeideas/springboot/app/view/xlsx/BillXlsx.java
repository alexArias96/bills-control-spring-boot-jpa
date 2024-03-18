package com.bolsadeideas.springboot.app.view.xlsx;

import com.bolsadeideas.springboot.app.models.entity.Bill;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component("bill/viewDetailsBill.xlsx")
public class BillXlsx extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1-Obtain bill object and do cast
        Bill bill = (Bill) model.get("bill");

        //2-To create rows and columns
        Sheet sheet = workbook.createSheet("Bill Spring");

        Row row =  sheet.createRow(0);
        Cell cell = row.createCell(0);

        cell.setCellValue("Customer Data");
        row = sheet.createRow(1);
        cell = row.createCell(0);
        cell.setCellValue(bill.getCustomer().getName() + " " +  bill.getCustomer().getSurname());

        row = sheet.createRow(2);
        cell = row.createCell(0);
        cell.setCellValue(bill.getCustomer().getEmail());

        sheet.createRow(3).createCell(0).setCellValue("Bill Data");
        sheet.createRow(5).createCell(0).setCellValue("Invoice: " + bill.getId());
        sheet.createRow(6).createCell(0).setCellValue("Description: " + bill.getDescription());
        sheet.createRow(7).createCell(0).setCellValue("Date: " + bill.getCreateAt());
    }
}

package com.internet.inchirurgiademo.controller;


import com.internet.inchirurgiademo.temp.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class PageController {

    @GetMapping("/test")
    public String returnProdactPage(Model model){
        PnTable pnTable = new PnTable();
        TableRow tableRow = new TableRow();

        tableRow.addCell("TĘPE-TĘPE");
        tableRow.addCell("13-100");
        tableRow.addCell("13-102");
        tableRow.addCell("jest");
        tableRow.addCell("13-106");
        tableRow.addCell("13-108");

        pnTable.addTableRow(tableRow);
        pnTable.setDescrtiption("opisik");
//        pnTable.setHeader("");

        pnTable.addHeaderCell(new HeaderCell("80"+"px","Rozmiar"));
        pnTable.addHeaderCell(new HeaderCell("60"+"px", "12,5 cm"));
        pnTable.addHeaderCell(new HeaderCell("60"+"px","14,5 cm"));
        pnTable.addHeaderCell(new HeaderCell("60"+"px","16,5 cm"));
        pnTable.addHeaderCell(new HeaderCell("60"+"px","17,5 cm"));
        pnTable.addHeaderCell(new HeaderCell("60"+"px","20 cm"));

        List<PnTable> tableList =  new LinkedList<>();
        tableList.add(pnTable);
        tableList.add(pnTable);


        Section section = new Section();
        section.addTable(pnTable);
        section.addTable(pnTable);
        section.setSectionHeader("NOWY NAGŁÓWEK SEKCJI");
        section.setImageFile("/images/"+"13-102.jpg");
        List<Section> sectionList = new LinkedList<>();
        sectionList.add(section);
        sectionList.add(section);

        Product product = new Product();
        product.setSectionList(sectionList);
        product.setImageFile("/images/"+"13-COOPERx.jpg");
        product.setName("Nożyczki Cooper");


        model.addAttribute("product_data", product);
//        model.addAttribute("table_row", pnTable.getTableRowList());


        return "product_view";
    }

    @GetMapping("/menu")
    public String menu(){
        return "navigation";
    }
}

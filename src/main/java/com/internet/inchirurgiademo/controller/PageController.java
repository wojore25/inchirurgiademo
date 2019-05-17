package com.internet.inchirurgiademo.controller;


import com.internet.inchirurgiademo.dto.*;
import com.internet.inchirurgiademo.services.PortfolioService;
import com.internet.inchirurgiademo.services.ProductService;
import com.internet.inchirurgiademo.temp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/product")
    public String returnProdactPage(Model model){

        ProductDto product = productService.findProductDto(23l);

        List<String> categories= new LinkedList<>();
        categories.add("NOŻYCZKI");
        categories.add("PINCETY");



        model.addAttribute("product_data", product);
        model.addAttribute("categories", categories);
//        model.addAttribute("table_row", pnTable.getTableRowList());


        return "product_view";
    }

    @GetMapping("/portfolio")
    public String returnPortfolioPage(Model model){

        PortfolioDto portfolioDto = portfolioService.findPortfolioDto(325l);

        List<String> categories= new LinkedList<>();
        categories.add("NOŻYCZKI");
        categories.add("PINCETY");

        model.addAttribute("portfolio_data", portfolioDto);
        model.addAttribute("categories", categories);



        return "portfolio_view";
    }

    @GetMapping("/menu")
    public String menu(){
        return "navigation";
    }
}

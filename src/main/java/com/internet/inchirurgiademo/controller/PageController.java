package com.internet.inchirurgiademo.controller;


import com.internet.inchirurgiademo.dto.ContactDto;
import com.internet.inchirurgiademo.dto.PortfolioDto;
import com.internet.inchirurgiademo.dto.ProductDto;
import com.internet.inchirurgiademo.services.PortfolioService;
import com.internet.inchirurgiademo.services.ProductService;
import com.internet.inchirurgiademo.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private TagService tagService;

    @GetMapping("/product")
    public String returnProductPage(Model model, @ModelAttribute(name = "id") Long id) {

        ProductDto product = productService.findProductDto(id);

        List<String> categories = tagService.listCategories();

        model.addAttribute("product_data", product);
        model.addAttribute("product_categories", categories);
//        model.addAttribute("table_row", pnTable.getTableRowList());


        return "product_view";
    }


    @GetMapping("/start")
    public String returnStartPage(Model model) {

        PortfolioDto portfolioDto = portfolioService.findPortfolioDto(1l);

        setupPortfolioModel(model, portfolioDto);


        return "portfolio_view";
    }

    @GetMapping("/portfolio")
    public String returnPortfolioPage(Model model, @ModelAttribute(name = "id") Long id) {


        PortfolioDto portfolioDto = portfolioService.findPortfolioDto(id);

        setupPortfolioModel(model, portfolioDto);

        return "portfolio_view";
    }

    @GetMapping("/category")
    public String returnSearchPage(Model model, @ModelAttribute(name = "category") String category) {


        PortfolioDto portfolioDto = new PortfolioDto();
        portfolioDto.setTitle("Kategoria: " + category);
        portfolioDto.setPostDtoList(portfolioService.findPostDtoWithTag(category));

        setupPortfolioModel(model, portfolioDto);

        return "portfolio_view";
    }


    @GetMapping("/search")
    public String search(Model model, @ModelAttribute(name = "search") String search) {

        PortfolioDto portfolioDto = new PortfolioDto();
        portfolioDto.setTitle("Wyszukiwanie: " + search);
        portfolioDto.setPostDtoList(portfolioService.findSearchedPosts(search));

        setupPortfolioModel(model, portfolioDto);


        return "portfolio_view";
    }


    @GetMapping("/menu")
    public String menu() {
        return "navigation";
    }

    @GetMapping("/rodo")
    public String rodoPage(Model model){
        List<String> categories = tagService.listCategories();
        model.addAttribute("product_categories", categories);
        return "rodo";
    }

    @GetMapping("/contact")
    public String contactPage(Model model){

        ContactDto contactDto = new ContactDto();
        model.addAttribute("contact_data", contactDto);

        List<String> categories = tagService.listCategories();
        model.addAttribute("product_categories", categories);

        return "contact_page";
    }

    @RequestMapping("/processContact")
    public String processContact(@Valid @ModelAttribute("contact_data") ContactDto contactDto,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()) return "/contact_page";
        return "redirect:/start";
    }

    private void setupPortfolioModel(Model model, PortfolioDto portfolioDto) {
        model.addAttribute("portfolio_data", portfolioDto);
        List<String> categories = tagService.listCategories();
        model.addAttribute("product_categories", categories);
    }
}

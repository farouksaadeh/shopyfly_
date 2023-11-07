package ch.shopyfly.shopyfly.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "/products")
    public String getAllProducts(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products",products);
        return "product-list";
    }
}

package ch.shopyfly.shopyfly.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

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

    @GetMapping(path = "/product")
    public String getProduct(Model model, @RequestParam(value = "id", defaultValue = "1") Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            model.addAttribute("product", product.get());
        } else {
            model.addAttribute("product",new Product());
        }

        return "product-details";
    }

    @PostMapping(path = "/add-product")
    public String addProduct(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/add-product")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }




}

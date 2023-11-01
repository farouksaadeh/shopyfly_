package ch.shopyfly.shopyfly.accessingdatamysql;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

@Controller
public class WebShopController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String showProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/home")
    @ResponseBody
    public String getLandingPage() {
        try {
            Path path = new ClassPathResource("static/landingPage.html").getFile().toPath();
            return Files.readString(path);
        } catch (Exception e) {
            e.printStackTrace();
            return "Fehler beim Laden der HTML-Datei";
        }
    }

    @Service
    public static class ProductService {

        public List<Product> getAllProducts() {
            return new ArrayList<>();
        }
    }

    public static class Product {
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

package ch.shopyfly.shopyfly.accessingdatamysql;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
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

    @Service
    public static class ProductService {

        public List<Product> getAllProducts() {
            // Dies gibt eine leere Liste zurück, um einen NullPointer-Exception zu vermeiden.
            // Sie sollten dies später durch den tatsächlichen Code zum Abrufen von Produkten ersetzen.
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

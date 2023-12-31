package ch.shopyfly.shopyfly.accessingdatamysql;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String pictureURL;
    private String name;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ShoeSizeEnum size;
    private String brand;
    private int stock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ShoeSizeEnum getSize() {
        return size;
    }

    public void setSize(ShoeSizeEnum size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

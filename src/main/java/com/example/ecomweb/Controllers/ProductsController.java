package com.example.ecomweb.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ecomweb.Entity.ProductsBean;
import com.example.ecomweb.Services.ProductsService;

@Controller
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/products")
    public String products(Model model) {
        List<ProductsBean> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "Admin/products";
    }

    @PostMapping("/saveProduct")
    public String newProduct(@ModelAttribute("newProduct") ProductsBean newProduct,
            RedirectAttributes redirectAttributes, @RequestParam("image") byte[] file) {

        newProduct.setImage(file);
        newProduct.setImageType(file.toString());

        productsService.save(newProduct);

        // Add a flash attribute for the success message
        redirectAttributes.addFlashAttribute("successMessage", "Product added successfully!");
        return "redirect:/admin-panel";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productsService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        ProductsBean productsBean = productsService.findById(id);
        model.addAttribute("productBean", productsBean);
        return "Admin/edit-product";
    }

    @PostMapping("/updateProduct")
    public String editProduct(@ModelAttribute("newProduct") ProductsBean newProduct) {
        productsService.save(newProduct);
        return "redirect:/products";
    }

    // public ResponseEntity<byte[]> getImage(@PathVariable("id") Long productId) {
    // Optional<ProductsBean> productOptional =
    // productsService.OptionalfindById(productId);

    // if (productOptional.isPresent()) {
    // ProductsBean productsBean = productOptional.get();
    // byte[] image = productsBean.getImage();

    // // Set headers for image response
    // HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.IMAGE_JPEG); // Change to appropriate type
    // if not JPEG
    // return new ResponseEntity<>(image, headers, HttpStatus.OK);
    // } else {
    // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    // }
    // }

    @GetMapping("/images/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) {
        ProductsBean productsBean = productsService.findById(id);
        if (productsBean == null || productsBean.getImage() == null) {
            System.out.println("Product or image not found for ID: " + id);
            return ResponseEntity.notFound().build();
        }

        System.out.println();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, productsBean.getImageType())
                .body(productsBean.getImage());
    }

    @GetMapping("/viewProduct/{id}")
    public String viewProduct(@PathVariable("id") Long id, Model model) {
        ProductsBean productData = productsService.findById(id);
        model.addAttribute("product", productData);
        return "User/view-product";
    } 
}

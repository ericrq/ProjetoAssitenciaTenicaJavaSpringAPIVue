package assitenciatecnica.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import assitenciatecnica.backend.model.Product;
import assitenciatecnica.backend.service.ProductService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // create product
    @PostMapping
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product, BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.unprocessableEntity().body(errors);
        }

        return productService.createProduct(product);
    }

    // update product
    @PutMapping("{ProductId}")
    public ResponseEntity<?> updateProduct(@Valid @PathVariable Long ProductId, @RequestBody Product product,
            BindingResult bindResult) {
        if (bindResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.unprocessableEntity().body(errors);
        }

        product.setId(ProductId);
        productService.updateProduct(ProductId, product);
        return ResponseEntity.ok().body("Produto atualizado com sucesso!");
    }

    // select all products
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return productService.getAllProducts();
    }

    // select product by id
    @GetMapping("{ProductId}")
    public ResponseEntity<?> getProductById(@PathVariable Long ProductId) {
        return productService.getProductById(ProductId);
    }

    // delete product
    @DeleteMapping("{ProductId}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long ProductId) {
        productService.deleteProductById(ProductId);
        return ResponseEntity.ok().body("Produto deletado com sucesso!");
    }
}
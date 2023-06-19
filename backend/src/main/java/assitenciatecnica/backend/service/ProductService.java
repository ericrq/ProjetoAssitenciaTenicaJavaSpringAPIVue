package assitenciatecnica.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import assitenciatecnica.backend.model.Product;
import assitenciatecnica.backend.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // create product
    public ResponseEntity<String> createProduct(Product product) {
        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com sucesso!");
    }

    // update product
    public ResponseEntity<?> updateProduct(Long ProductId, Product product) {
        if (productRepository.findById(ProductId).isPresent()) {
            Product productToUpdate = productRepository.findById(ProductId).get();
            productToUpdate.setProduct_type(product.getProduct_type());
            productRepository.save(productToUpdate);
            return ResponseEntity.ok().body("Produto atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
    }

    // get all products
    public ResponseEntity<?> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nenhum produto encontrado!");
        }

        return ResponseEntity.ok().body(products);
    }

    // get product by id
    public ResponseEntity<?> getProductById(Long productId) {
        if (productRepository.existsById(productId)) {
            Product product = productRepository.findById(productId).get();
            return ResponseEntity.ok().body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
    }

    // delete product by id
    public ResponseEntity<?> deleteProductById(Long productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
            return ResponseEntity.ok().body("Produto deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
        }
    }

}

package io.github.wkktoria.shopping_list.product;

import java.util.List;

class ProductService {
    private final ProductRepository repository;

    ProductService() {
        this(new ProductRepository());
    }

    ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    List<Product> findAll() {
        return repository.findAll();
    }

    Product addProduct(ProductDTO productDTO) {
        return repository.addProduct(productDTO);
    }

    boolean deleteProduct(Integer id) {
        return repository.deleteProduct(id);
    }

    Product toggleBought(Integer id) {
        var product = repository.findById(id);
        return repository.updateBought(product);
    }
}

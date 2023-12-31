package io.github.wkktoria.shopping_list.product;

import java.util.ArrayList;
import java.util.List;

class MockRepository extends ProductRepository {
    private final List<Product> products;

    MockRepository() {
        this.products = new ArrayList<>();
        products.add(new Product(1, "Test 1", 1, false));
        products.add(new Product(2, "Test 2", 2, true));
        products.add(new Product(3, "Test 3", 4, false));
    }

    @Override
    List<Product> findAll() {
        return products;
    }

    @Override
    Product addProduct(ProductDTO productDTO) {
        var product = new Product(products.size(), productDTO.getName(), productDTO.getQuantity(), false);
        products.add(product);

        return product;
    }

    @Override
    boolean deleteProduct(Integer id) {
        try {
            var product = products.get(id);
            products.remove(product);

            return true;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    Product findById(Integer id) {
        return products.get(id);
    }

    @Override
    Product updateBought(Product product) {
        product.setBought(!product.getBought());
        products.get(product.getId()).setBought(product.getBought());

        return product;
    }
}

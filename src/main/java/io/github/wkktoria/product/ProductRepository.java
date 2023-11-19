package io.github.wkktoria.product;

import io.github.wkktoria.utils.HibernateUtil;

import java.util.List;

class ProductRepository {
    List<Product> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.createQuery("from Product ", Product.class).list();

        transaction.commit();
        session.close();

        return result;
    }

    Product addProduct(ProductDTO productDTO) {
        var product = new Product(productDTO.getName(), productDTO.getQuantity(), false);

        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        session.persist(product);

        transaction.commit();
        session.close();

        return product;
    }

    boolean deleteProduct(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var product = session.get(Product.class, id);

        if (product != null) {
            session.remove(product);
            transaction.commit();

            return true;
        }

        session.close();

        return false;
    }
}

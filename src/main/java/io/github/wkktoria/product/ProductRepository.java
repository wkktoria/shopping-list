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
}

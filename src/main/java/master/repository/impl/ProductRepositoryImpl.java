package master.repository.impl;


import master.entity.Product;
import master.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Product product) {
        entityManager.merge(product);
    }

    @Override
    public List<Product> findAll() {
        return entityManager
                .createQuery("FROM Product", Product.class)
                .getResultList();
    }

    @Override
    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public void delete(Long id) {
        Product product = findById(id);

        if(product != null) {
            entityManager.remove(product);
        }
    }
}
package master.repository;

import master.entity.Product;

import java.util.List;

public interface ProductRepository {
    void save(Product product);

    List<Product> findAll();

    Product findById(Long id);

    void delete(Long id);
}

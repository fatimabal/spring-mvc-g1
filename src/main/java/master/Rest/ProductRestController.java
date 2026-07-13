package master.Rest;


import master.entity.Product;
import master.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public List<Product>  getList(){
        return  this.productService.findAll();
    }

    @PostMapping
    public void save(@RequestBody Product product){
         productService.save(product);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        productService.delete(id);

        return "Delete product";
    }


    @PutMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @RequestBody Product product ){
        Product productUpd = productService.findById(id);
        productUpd.setLibelle(product.getLibelle());
        productUpd.setPrix(product.getPrix());
        productService.save(productUpd);

        return "update success";
    }

}

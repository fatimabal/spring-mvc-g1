package master.controller;


import master.entity.Product;
import master.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductConroller {

    @Autowired
    private ProductService productService;


    @GetMapping
    public String getList(Model model ){
        List<Product> list = this.productService.findAll();
        model.addAttribute("products", list);

        return "product";

    }

    @GetMapping("/new")
    public String form(Model model){
        model.addAttribute("product", new Product());
        return "form-product";
    }

    @PostMapping
    public String save(@ModelAttribute Product product){
        productService.save(product);
        return "redirect:/product";   //get
    }

    @GetMapping("/{id}")
    public String delete(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/product";   //get
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "form-product";
    }

}

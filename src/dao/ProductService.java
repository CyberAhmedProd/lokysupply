package dao;

import java.util.ArrayList;

import models.Product;



public interface ProductService {
	
	Product seekByRef(String ref);

    ArrayList<Product> getAll();

    Boolean save(Product t);

    Boolean update(Product t);

    Boolean delete(Product t);
    
    Boolean checkStock();
    
    
    String alerteStock();
    
}

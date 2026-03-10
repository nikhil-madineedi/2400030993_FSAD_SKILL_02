package com.klu.main;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.klu.entity.Product;

public class ProductApp {
  public static void main(String[] args) {
    SessionFactory sf = new Configuration().configure().buildSessionFactory();
    Session s = sf.openSession();
//    Transaction tx = s.beginTransaction();
//    Product p = new Product("Mangoes",4000);
//    s.persist(p);
    
    
   //HQL
    //1. retrieving all records with all columns
    String hql = "from Product";
    Query<Product> query = s.createQuery(hql,Product.class);// change form hql to sql and stores it into query 
    List<Product> resultList = query.getResultList();//similar types can store in collections so it is given in list type
    
    
    System.out.println("Product details using foreach loop:");
    for(Product p:resultList) {//left side from where i need to fetch the data , in right assigning the object 
//      System.out.println(p);//this will work when entity having tostring() method (displaying the object directly)
    	//below approch is usefull when there is no tostring in entity
    	System.out.println("Product Id   :"+p.getPid());
    	System.out.println("Product Name :"+p.getPname());
    	System.out.println("Product price:"+p.getPcost());
    	System.out.println("------------------------------");
    }
    System.out.println("Product details using foreach method with lambda:");
//    resultList.forEach(p -> System.out.println(p));//this will work when entity having tostring() method
  //below approch is usefull when there is no tostring in entity
    resultList.forEach(p -> System.out.println("Product Id: "+p.getPid()+" Product Name: "+p.getPname()+" Product price: "+p.getPcost()));
    s.close();
    sf.close();
  }
}
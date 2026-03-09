package com.klu.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.klu.entity.Product;

import jakarta.persistence.Tuple;


public class ProductHqlApp {
  private static List<Product> resultList3;

  public static void main(String[] args) {
    SessionFactory sf = new Configuration().configure().buildSessionFactory();
    Session s = sf.openSession();
    
    //HQL: retrieving all records with partial columns
    String hql1 = "select pname,pcost from Product";
    Query<Tuple> query = s.createQuery(hql1,Tuple.class);
    List<Tuple> resultList = query.getResultList();
    System.out.println("Product Details with partial columns:");
    for(Tuple t : resultList) {
      System.out.println(t.get(0) + "\t" + t.get(1));
    }
    s.close();
    
    
    //HQL: Named Parameters and Positional Parameters
    //HQL: retrieving all records with all columns using where clause
    String hql2 = "from Product where pcost >= :cost";
    s = sf.openSession();
    Query<Product> query2 = s.createQuery(hql2,Product.class);
    query2.setParameter("cost", 3000);
    List<Product> resultList2 = query2.getResultList();
    System.out.println("Product Details:");
    for(Product p : resultList2) {
      System.out.println(p.getPid() + "\t" + p.getPname() + "\t" + p.getPcost());
    }
    
    s.close();
    //HQL: using positional parameters(represented by ?index_value)
    //HQL: retrieving product details in which the cost is in between 3000 and 5000
    String hql3 ="from product where pcost >= ?1 and pcost <= ?2";
    String hql3 = "from Product where pcost>=?1 and pcost<=?2";
    s = sf.openSession();
    Query<Product> query3 = s.createQuery(hql3,Product.class);
    query3.setParameter(1, 2000);
    query3.setParameter(2, 5000);
    List<Product> resultList3 = query3.getResultList();
    System.out.println("Product list (partial columns)");
    for(Product p : resultList3) {
        System.out.println(p.getPid() + "\t" + p.getPname() + "\t" + p.getPcost());
      }
    s.close();
    //HQL: reterving all the data from the table in dsecending order (by taking the cost)
    //if we want all in descending we can just reteriev them 
    //or if we nedd it by specific column or list we need to specify them 
    
    String hql4="from Product order by pcost DESC";
    s=sf.openSession();
    Query<Product> query4 = s.createQuery(hql4,Product.class);
    List<Product> resultList4 = query4.getResultList();
    System.out.println("The product table in descending order:");
    for(Product p : resultList4) {
        System.out.println(p.getPid() + "\t" + p.getPname() + "\t" + p.getPcost());
      }
    s.close();
    
    
    
    
    
//    //HQl: Aggregations in  HQL
//    //we have different types of aggregate function in hql 1)count 2)avg 3)MIN 4)MAX    
    String hqlCount="Select count(*) from Product";
    String hqlSum="Select sum(pcost) from Product";
    String hqlAvg="Select avg(pcost) from Product";
    String hqlMin="Select MIN(pcost) from Product";
    String hqlMax="Select Max(pcost) from Product";
//    
    s=sf.openSession();
    Query<Long> query = s.createQuery(hqlCount,Long.class);
    Query<Double> query2 = s.createQuery(hqlSum,Double.class);
    Query<Double> query3 = s.createQuery(hqlAvg,Double.class);
    Query<Double> query5 = s.createQuery(hqlMin,Double.class);
    Query<Double> query6 = s.createQuery(hqlMax,Double.class);
    
    Long countRes = query.getSingleResult();
    System.out.println("Total Count of data:"+countRes);
    
    Double sumRes = query2.getSingleResult();
    System.out.println("Total sum of cost:"+sumRes);
    
    Double avgRes = query3.getSingleResult();
    System.out.println("Average of cost:"+avgRes);
    
    Double minRes = query5.getSingleResult();
    System.out.println("Minimum cost:"+minRes);
    
    Double maxRes = query6.getSingleResult();
    System.out.println("Maximum cost:"+maxRes);
    s.close();
    
    
    
    
  //HQL: pagination
    String hql_page = "from Product";
    s = sf.openSession();
    Query<Product> query10 = s.createQuery(hql_page,Product.class);
    query10.setFirstResult(0);
    query10.setMaxResults(2);
    List<Product> resultList5 = query10.getResultList();
    System.out.println("Product Details:");
    for(Product p : resultList5) {
      System.out.println(p.getPid() + "\t" + p.getPname() + "\t" + p.getPcost());
    }
    String hql_page="from Product";
    s=sf.openSession();
    Query<Product> query4 = s.createQuery(hql_page,Product.class);
    query4.setFirstResult(0);
    query4.setMaxResults(3);
    List<Product> rl = query4.getResultList();
    System.out.println("Product Details:");
  for(Product p : rl) {
    System.out.println(p.getPid() + "\t" + p.getPname() + "\t" + p.getPcost());
  }
    
    s.close();
    
    sf.close();
  }
}
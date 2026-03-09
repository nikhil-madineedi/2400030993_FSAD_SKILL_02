package com.klu.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.MutationQuery;

public class ProductUpdateApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sf=new Configuration().configure().buildSessionFactory();
		Session s= sf.openSession();
		Transaction Tx= s.beginTransaction();
		
		
		
//		HQL: Bulk update
	    String hql_update = "update Product set pcost = pcost + 500 where pcost = :cost";
	    MutationQuery update = s.createMutationQuery(hql_update);//convert hql update to sql update
	    update.setParameter("cost", 5000);
	    int n = update.executeUpdate();//execute sql update query
	    Tx.commit();
	    if( n > 0 )
	      System.out.println("No.of rows updated:" + n);
	    else
	      System.out.println("No rows updated");
	    
	    s.close();
	    
	    //HQL: Bulk Delete
		 String hql_delete = "delete Product where pcost = ?1";
	    s = sf.openSession();
	    MutationQuery delete = s.createMutationQuery(hql_delete);//convert hql update to sql update
	    delete.setParameter(1, 5500);
	    int n1 = delete.executeUpdate();//execute sql update query
	    Tx.commit();
	    if( n1 > 0 )
	      System.out.println("No.of rows deleted:" + n1);
	    else
	      System.out.println("No rows deleted");
	    
	    s.close();
	    sf.close();
	  }
	}
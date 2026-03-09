package com.klu.main;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.klu.entity.Product;

public class HibernateMultipeInsert {
	public static void main(String[] args) {		
			SessionFactory sf = new Configuration().configure().buildSessionFactory();
			Session s = sf.openSession();
			Transaction tx = s.beginTransaction();
			String name = null;
			double cost = 0;
			Product p = null;
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter no of records to be inserted: ");
			int n = sc.nextInt();
			for (int i = 0; i < n; i++) 
			{
				sc.nextLine();
				System.out.println("Enter Product Name:");
				name = sc.nextLine();
				System.out.println("Enter Product Cost:");
				cost = sc.nextDouble();
				//create new product object with the given details
				p = new Product(name,cost);//param.const calling
				//store object into Session
				
				
				s.persist(p);
			}
//			Store all Product objects into DB Permanently
			tx.commit();
			
		}
	}
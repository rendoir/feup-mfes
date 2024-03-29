package ConsoleInterface;

import ShopAdvizor.*;
import org.overture.codegen.runtime.*;
import java.util.*;

public class PrintFactory {
	public static void printError(String msg) {
		System.out.println("Error: " + msg);
	}
	
	public static void printUserReviews(VDMSet reviews) {
		boolean first_print = true;
		for(Object obj : reviews) {
			if(first_print) {
				System.out.print("My Reviews:");
				first_print = false;
			}
			Tuple tuple = (Tuple)obj;
			Product product = (Product)tuple.get(0);
			Product.Review review = (Product.Review)tuple.get(1);
			printProduct(product);
			System.out.println();
			System.out.println("Rating: " + review.rating + "/5");
			System.out.println("Feedback: " + review.feedback);
		}
		if(first_print)
			System.out.println("You have no reviews!");
	}
	
	public static void printProduct(Product product) {
		System.out.println("Product: " + product.m_name);
		System.out.println("Brand: " + product.m_brand.m_name);
	}
	
	public static void printLowestPrice(Tuple tuple) {
		if (tuple == null) {
			System.out.println("No retailer sells this product!");
			return;
		}
		Retailer retailer = (Retailer)tuple.get(0);
		Double price = (Double)tuple.get(1);
		System.out.println("Lowest price: " + price + "€ at " + retailer.m_name);
	}
	
	public static void printProductReviews(VDMMap reviews) {
		boolean first_print = true;
		for(Object obj : reviews.entrySet()) {
			if(first_print) {
				System.out.print("Product Reviews:");
				first_print = false;
			}
			Map.Entry<User, Product.Review> entry = (Map.Entry<User, Product.Review>)obj;
			User user = entry.getKey();
			Product.Review review = entry.getValue();
			System.out.println();
			System.out.println("User: " + user.m_username);
			System.out.println("Rating: " + review.rating);
			System.out.println("Feedback: " + review.feedback);
		}
		if(first_print)
			System.out.println("This product has no reviews!");
	}
	
	public static void printRetailer(Retailer retailer) {
		System.out.println("Retailer name: " + retailer.m_name);
		boolean initial_print = false;
		for(Object obj : retailer.m_items.entrySet()) {
			Map.Entry<Product, Retailer.ItemInfo> entry = (Map.Entry<Product, Retailer.ItemInfo>)obj;
			if(!initial_print) {
				System.out.println("Items sold :");
				initial_print = true;
			}
			System.out.println();
			System.out.println("Product name: " + entry.getKey().m_name);
			System.out.println("Product brand: " + entry.getKey().m_brand.m_name);
			System.out.println("Product price: " + entry.getValue().price + "€");
			System.out.println("Product stock: " + entry.getValue().stock);
		}
		if(!initial_print)
			System.out.println("Retailer " + retailer.m_name + " sells no products!");
	}
	
	public static void printBrand(Brand brand, ShopAdvizor shopAdvizor) {
		System.out.println("Brand name: " + brand.m_name);
		boolean initial_print = false;
		for(Object obj : shopAdvizor.m_products) {
			Product product = (Product)obj;
			if(product.m_brand.m_name == brand.m_name) {
				if(!initial_print) {
					System.out.println("Products:");
					initial_print = true;
				}
				System.out.println("Product name: " + product.m_name);
			}
		}
		if(!initial_print)
			System.out.println("Brand " + brand.m_name + " has no products!");
	}
	
	public static void printCompetitions(VDMSet competitions) {
		for(Object obj : competitions) {
			Competition competition = (Competition)obj;
			System.out.println();
			System.out.println("Competition title: " + competition.m_title);
			System.out.println("Competition brand: " + competition.m_brand.m_name);
		}
	}
	
	public static void printCompetition(Competition competition) {
		System.out.println("Title: " + competition.m_title);
		System.out.println("Description: " + competition.m_description);
		System.out.println("Brand: " + competition.m_brand.m_name);
		System.out.println("Prize: " + competition.m_prize);
		if(competition.m_has_ended) {
			System.out.println("The competition has ended!");
			if(competition.m_winner != null)
				System.out.println("The winner is " + competition.m_winner.m_username + "!");
			else System.out.println("A winner has not yet been selected!");	
		}
		else if(competition.m_has_started)
			System.out.println("The competition has started!");
		else System.out.println("The competition has not yet started!");
		boolean first_print = true;
		for(Object obj : competition.m_competitors) {
			if(first_print) {
				System.out.println("Competitors:");
				first_print = false;
			}
			User user = (User)obj;
			System.out.println(user.m_username);
		}
		if(first_print)
			System.out.println("No users competing!");
	}
	
	public static void printUserCompetitions(VDMSet competitions) {
		boolean initial_print = false;
		for(Object obj : competitions) {
			Competition competition = (Competition)obj;
			if(!initial_print) {
				System.out.println("Competitions:");
				initial_print = true;
			}
			System.out.println("Competition title: " + competition.m_title);
		}
		if(!initial_print)
			System.out.println("You are not competing in any competitions!");
	}

}

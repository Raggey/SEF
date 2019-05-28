package Class;
import java.util.Date;
import java.util.LinkedList;

/**
 * Class for representing customer.
 * 
 * @author	Gloria
 * @since	14/4/2019
 * @version	1.0
 */

/*
 * Product class needed
 */

public class Customer {
	private static final int CART_MAX = 50;
	private String id;
	private String name;
	// Record the money spent to add the points.
	private double money_spent;
	private int points;
	private int postcode;
	private int times_visited;
	// Record products.
	// Change to Array!!!!!!!!!!!!!
	private Product[] products = new Product[50];
	//private LinkedList<Product> products = new LinkedList<Product>();
	// To records card customer have.
	private LinkedList<CreditCard> creditCards = new LinkedList<CreditCard>();
	private boolean subscribe = true;

	public Customer(String id, String name, int postcode)
	{
		this.id = id;
		this.name = name;
		this.postcode = postcode;
		points = 0;
		times_visited = 0;
		money_spent = 0;
	}

	public String getID()
	{
		return id;

	}

	// Record visited time.
	public void logIn()
	{
		times_visited++;
	}

	// For database to collect info about customer.
	public int getTimeVisited()
	{
		return times_visited;
	}

	public void addProduct(Product product)
	{
		boolean exist = false;
		for(int i = 0; i < CART_MAX; i++)
		{
			if (products[i] == product)
			{
				exist = true;
			}
		}
		if (!exist)
		{
			for(int i = 0; i < 50; i++)
			{
				if (products[i] == null)
				{
					products[i] = product;
					break;
				}
			}
		}
		product.setNumberInCart(product.getNumberInCart() + 1);
	}

	public void removeProduct(Product product, int amount)
	{
		for(int i = 0; i < CART_MAX; i++)
		{
			if (products[i] == product)
			{
				product.setNumberInCart(product.getNumberInCart() - amount);

				if (product.getNumberInCart() == 0) {
					products[i] = null;
				}
			}

		}
	}
	public int getPoint()
	{
		return points;
	}

	// Be called to tell customer how much need to spent to get next point.
	public double getMoneySpent()
	{
		return money_spent;
	}

	// Add points if the customer spent more than $10.
	public void calculatePoints(double spent)
	{
		if(subscribe == true) {
			money_spent += spent;
			if (money_spent >= 10)
			{
				points += (int)Math.floor(money_spent/10);
				money_spent %= 10;
			}
		}
		else {
			money_spent += spent;
		}
	}

	// Check if the point enough to get a discount, 
	public double checkDiscount(double total, boolean confirm)
	{
		int points = this.points;
		double discount = 0;
		if(points >= 20)
		{
			discount = 5*(Math.floor(points/20));
			points %= 20;
			while (total < discount)
			{
				discount -= 5;
				points += 20;
			}
		}
		if (confirm)
		{
			this.points = points;
		}
		return discount;
	}

	// Subscribe and unsubscribe.
	public boolean subscribe()
	{
		if (subscribe)
		{
			subscribe = false;
			System.out.println("Subscription is now OFF");
		}
		else
		{
			subscribe = true;
			System.out.println("Subscription is now ON");
		}
		return subscribe;
	}

	// To record credit card info
	public boolean addCard(CreditCard newCard)
	{
		// Check if the card already exist
		if (creditCards != null)
		{
			// Card duplicate, return false, add fail.
			for (CreditCard element : creditCards)
			{
				if(newCard == element)
				{
					return false;
				}
			}
			creditCards.add(newCard);
			return true;
		}
		// If there's no cards been saved, add card. 
		else
		{
			creditCards.add(newCard);
			return true;
		}
	}

	public Product[] getCart()
	{
		return products;
	}

	public void emptyCart() {
		this.products = new Product[CART_MAX];
	}
}


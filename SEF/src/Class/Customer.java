package Class;
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
	private String id;
	private String name;
	// Record the money spent to add the points.
	private int money_spent;
	private int points;
	private int postcode;
	private int times_visited;
	// Record products.
	private LinkedList<Product> products = new LinkedList<Product>();
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
		products.put(product);
	}
	
	public int getPoint()
	{
		return points;
	}
	
	// Be called to tell customer how much need to spent to get next point.
	public int getMoneySpent()
	{
		return money_spent;
	}
	
	// Add points if the customer spent more than $10.
	public void calculatePoints(int spent)
	{
		money_spent += spent;
		if (money_spent >= 10)
		{
			points += (Math.floor(money_spent/10));
			money_spent %= 10;
		}
	}
	
	// Check if the point enough to get a discount, 
	public int checkDiscount()
	{
		if(points >= 20)
		{
			int discount = (int) (5*(Math.floor(points/20)));
			points %= 20;
			return discount;
		}
		else
		{
			return (Integer) null;
		}
	}
	
	// Subscribe and unsubscribe.
	public boolean subscribe()
	{
		if (subscribe)
		{
			subscribe = false;
		}
		else
		{
			subscribe = true;
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
}


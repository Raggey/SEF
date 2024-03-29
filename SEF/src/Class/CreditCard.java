package Class;

import java.text.SimpleDateFormat;
import java.util.Date;
	/**
	 * Class for representing credit card.
	 * 
	 * @author	Gloria
	 * @since	15/4/2019
	 * @version	1.0
	 */

/*
 * ATTENTION:
 * Add point be moved to customer class.
 */

/* test
 * QUESTIONS:
 * Is this enough?
 */

public class CreditCard {
	private int credit_card_num;
	private String holderName;
	
	public CreditCard(int credit_card_num, String holderName)
	{
		this.holderName = holderName;
		this.credit_card_num = credit_card_num;
	}
	
	public int getNumber()
	{
		return credit_card_num;
	}
	
	public String getHolderName()
	{
		return holderName;
	}
}

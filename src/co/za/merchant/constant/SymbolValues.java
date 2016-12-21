package co.za.merchant.constant;

/**
 * 
 * @author bay
 *
 */
public enum SymbolValues {
	
	I (1), 
	V (5), 
	X (10), 
	L (50),
	C (100),
	D (500),
	M (1000);
	
	private final int amount;
	
	private SymbolValues(int amount) {
		this.amount = amount;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getAmount() {
		return this.amount;
	}

}
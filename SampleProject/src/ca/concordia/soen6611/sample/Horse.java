package ca.concordia.soen6611.sample;

/**
 * 
 * @author soen6611
 *
 */
public class Horse extends Animal {
	
	/**
	 * 
	 * @param name
	 */
	public Horse(String name) {
		super(name);
	}

	/**
	 * 
	 */
	@Override
	public String sound() {
		return "neigh";
	}
}

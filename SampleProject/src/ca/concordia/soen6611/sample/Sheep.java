package ca.concordia.soen6611.sample;

/**
 * 
 * @author soen6611
 *
 */
public class Sheep extends Animal {
	
	/**
	 * 
	 * @param name
	 */
	public Sheep(String name) {
		super(name);
	}

	/**
	 * 
	 */
	@Override
	public String sound() {
		return "baaaa";
	}
}

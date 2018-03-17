package ca.concordia.soen6611.sample;

/**
 * 
 * @author soen6611
 *
 */
class Cow extends Animal {
	
	/**
	 * 
	 * @param name
	 */
	public Cow(String name) {
		super(name);
	}

	/**
	 *  
	 */
	@Override
	public String sound() {
		return "moooooo";
	}
}
package ca.concordia.soen6611.sample;

/**
 * 
 * @author soen6611
 *
 */
public abstract class Animal {

	/**
	 * 
	 */
	private String name;

	public Animal(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String speak() {
		return name + " says " + sound();
	}

	/**
	 * 
	 * @return
	 */
	public abstract String sound();
}

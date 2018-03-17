package ca.concordia.soen6611.sample;

/**
 * 
 * @author soen6611
 *
 */
public class AnimalDemo {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Animal h = new Horse("CJ");
		System.out.println(h.speak());
		Animal c = new Cow("Bessie");
		System.out.println(c.speak());
		System.out.println(new Sheep("Little Lamb").speak());
	}
}

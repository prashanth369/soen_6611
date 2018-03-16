package gr.uom.java.jdeodorant.implementedMetrics;

import gr.uom.java.ast.SystemObject;

import gr.uom.java.jdeodorant.implementedMetrics.WritingtheValues;
//Implementation for QMOOD Understandability metric 
public class UnderstandabilityMetric {
	
	public static double understandibiityMetric(SystemObject system) {
		String sk;
		
		double abstraction   = ImplementationOfMetrics.calculateAbstraction(system);
		double encapsulation   = ImplementationOfMetrics.calculateEncapsulation(system);
		double coupling   = ImplementationOfMetrics.couplingMetric(system);
		double cohesion   = ImplementationOfMetrics.cohesionMetric(system);
		double polymorphism  = ImplementationOfMetrics.calculatePolymorphism(system);
		double complexity  = ImplementationOfMetrics.calculateComplexity(system);
		double designsize = ImplementationOfMetrics.designSizeMetric(system);
		double understandibilityValue=-0.33*abstraction +0.33*encapsulation-0.33*coupling+0.33*cohesion-0.33*polymorphism-0.5*complexity+ 0.5*designsize;
		
		System.out.println("Abstraction: " + abstraction);
		System.out.println("Encapsulation: " + encapsulation);
		System.out.println("Coupling: " + coupling);
		System.out.println("Cohesion: " + cohesion);
		System.out.println("Polymorphism: " + polymorphism);
		System.out.println("Complexity: " + complexity);
		System.out.println("Design Size: " + designsize);

		sk="The Understandibility design property of the system is : " + understandibilityValue;
		WritingtheValues.WritingtheSyatemvalues(sk);
		
		return  understandibilityValue;
	}


	
	}
	
	
	
	
	
	



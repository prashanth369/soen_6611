package gr.uom.java.jdeodorant.implementedMetrics;

import gr.uom.java.ast.SystemObject;

public class FlexibilityMetric {

public static double FlexibilityCalculation(SystemObject system)	{
	
	double encapsulation   = ImplementationOfMetrics.calculateEncapsulation(system);
	double coupling   = ImplementationOfMetrics.couplingMetric(system);
	double polymorphism  = ImplementationOfMetrics.calculatePolymorphism(system);
	double composition=ImplementationOfMetrics.calculateComposition(system);
	double flexibility = 0.25*encapsulation -0.25*coupling + 0.5*composition + 0.5*polymorphism;
	return flexibility;

}




	
	
	
	
}

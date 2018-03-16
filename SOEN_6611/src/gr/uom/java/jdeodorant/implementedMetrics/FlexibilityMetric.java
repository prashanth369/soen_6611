package gr.uom.java.jdeodorant.implementedMetrics;

import gr.uom.java.ast.SystemObject;
import gr.uom.java.jdeodorant.implementedMetrics.WritingtheValues;

public class FlexibilityMetric {

public static double FlexibilityCalculation(SystemObject system)	{
	String sk;
	
	double encapsulation   = ImplementationOfMetrics.calculateEncapsulation(system);
	double coupling   = ImplementationOfMetrics.couplingMetric(system);
	double polymorphism  = ImplementationOfMetrics.calculatePolymorphism(system);
	double composition=ImplementationOfMetrics.calculateComposition(system);
	double flexibility = 0.25*encapsulation -0.25*coupling + 0.5*composition + 0.5*polymorphism;
	sk="The Flexibility design property for the system is : "+ flexibility;
	WritingtheValues.WritingtheSyatemvalues(sk);
	return flexibility;

}




	
	
	
	
}

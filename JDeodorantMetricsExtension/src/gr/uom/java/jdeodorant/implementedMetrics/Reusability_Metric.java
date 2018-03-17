package gr.uom.java.jdeodorant.implementedMetrics;


import gr.uom.java.jdeodorant.implementedMetrics.ImplementationOfMetrics;

import gr.uom.java.ast.SystemObject;
import gr.uom.java.jdeodorant.implementedMetrics.WritingtheValues;

// Implementation for QMOOD reusability metric 

public class Reusability_Metric {
	
	
	public static double reusabilityMetric(SystemObject system) {
		String sk;
		
		double coupling   = ImplementationOfMetrics.couplingMetric(system);
		double cohesion   = ImplementationOfMetrics.cohesionMetric(system);
		double messaging  = ImplementationOfMetrics.messagingMetric(system);
		double designSize = ImplementationOfMetrics.designSizeMetric(system);
		
		double reusabilityValue=-0.25*coupling +0.25*cohesion +0.5*messaging +  0.5*designSize;
		
		System.out.println("coupling: " + coupling + " " + "chosion: " + cohesion + " " + "messaging: " + messaging + " " + "design size: " + designSize);
		System.out.println("Reusability is : " + reusabilityValue);
			  
		sk="The Reusability Design property for the system is : "+ reusabilityValue;
		WritingtheValues.WritingtheSyatemvalues(sk);
		
		return  reusabilityValue;
	}

	
	
}

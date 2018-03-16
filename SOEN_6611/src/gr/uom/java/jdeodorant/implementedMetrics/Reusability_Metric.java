package gr.uom.java.jdeodorant.implementedMetrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import gr.uom.java.jdeodorant.implementedMetrics.ImplementationOfMetrics;
import gr.uom.java.ast.MethodObject;
import gr.uom.java.ast.Access;
import gr.uom.java.ast.ClassObject;
import gr.uom.java.ast.SystemObject;

// Implementation for QMOOD reusability metric 

public class Reusability_Metric {
	
	
	public static double reusabilityMetric(SystemObject system) {
		
		double coupling   = ImplementationOfMetrics.couplingMetric(system);
		double cohesion   = ImplementationOfMetrics.cohesionMetric(system);
		double messaging  = ImplementationOfMetrics.messagingMetric(system);
		double designSize = ImplementationOfMetrics.designSizeMetric(system);
		
		double reusabilityValue=-0.25*coupling +0.25*cohesion +0.5*messaging +  0.5*designSize;
		
		System.out.println("coupling: " + coupling + " " + "chosion: " + cohesion + " " + "messaging: " + messaging + " " + "design size: " + designSize);
		System.out.println("Reusability is : " + reusabilityValue);
			  
		
		return  reusabilityValue;
	}

	
	
}

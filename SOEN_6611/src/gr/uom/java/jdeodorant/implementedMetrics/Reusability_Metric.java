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
	
	public  static ImplementationOfMetrics invididualMetrics;
	
	
	
	public double reusabilityMetric(SystemObject system) {
		
		double coupling   = couplingMetric(system);
		double cohesion   = cohesionMetric(system);
		double messaging  = messagingMetric(system);
		double designSize = designSizeMetric(system);
		double reusabilityValue=-0.25*coupling +0.25*cohesion +0.5*messaging +  0.5*designSize;
		
		System.out.println("coupling: " + coupling + " " + "chosion: " + cohesion + " " + "messaging: " + messaging + " " + "design size: " + designSize);
		System.out.println("Reusability is : " + reusabilityValue);
			  
		
		return  reusabilityValue;
	}

	
	private static double cohesionMetric(SystemObject system) {
		
		invididualMetrics=new ImplementationOfMetrics();
		System.out.println("Calculating the cohesion metric values ....");
		
		Map<String, Double> cohesionOfClass = new HashMap<String, Double>();
		
		Set<ClassObject> classes = system.getClassObjects();
		
		for (ClassObject classObject : classes) {
			double classCohesion = invididualMetrics.calculateCohesion(classObject);
			cohesionOfClass.put(classObject.getName(), classCohesion);

		}

		double CohesionValue = 0.0;
		for(String key : cohesionOfClass.keySet()) {
			 CohesionValue = CohesionValue + cohesionOfClass.get(key);
        	System.out.println( key + "  " +  cohesionOfClass.get(key));
        }
		
		return CohesionValue/classes.size();
	}

	private static double messagingMetric(SystemObject system) {

		Map<String, Integer> publicMethodsMap = new HashMap<String, Integer>();

		Set<ClassObject> classes = system.getClassObjects();

		for (ClassObject classObject : classes) {
			int publicMethodsCount = invididualMetrics.countPublicMessages(classObject);
			publicMethodsMap.put(classObject.getName(), publicMethodsCount);

		}
        double nbClasses = classes.size();
        double totalPublicMethods = 0.0;
        for(String key : publicMethodsMap.keySet()) {
        	totalPublicMethods += publicMethodsMap.get(key);
        	System.out.println( key + "  " +  publicMethodsMap.get(key));
        }
        
		return totalPublicMethods/nbClasses;
	}

	
	private static double couplingMetric(SystemObject system) {
		invididualMetrics=new ImplementationOfMetrics();
		Map<String, Integer> couplingMap = new HashMap<String, Integer>();
		
		Set<ClassObject> classes = system.getClassObjects();
		List<String> classTypes  = system.getClassNames();
		
		for (ClassObject classObject : classes) {
			List<String> cls = new ArrayList<String>(classTypes);
			
			cls.remove(classObject.getName());
			int directCoupling = invididualMetrics.calculateCoupling(classObject, cls);
			couplingMap.put(classObject.getName(), directCoupling);

		}	
		double sumCoupling = 0.0;
		for(String key : couplingMap.keySet()) {
			sumCoupling += couplingMap.get(key);
        	System.out.println( key + "  " +  couplingMap.get(key));
        }
		return sumCoupling/classes.size();
		
	}
	
	
	
	
	
     private static double designSizeMetric(SystemObject system) {
		
		return system.getClassObjects().size();
	}


}

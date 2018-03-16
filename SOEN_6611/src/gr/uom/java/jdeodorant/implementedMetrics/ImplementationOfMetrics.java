package gr.uom.java.jdeodorant.implementedMetrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import gr.uom.java.ast.ASTReader;
import gr.uom.java.ast.Access;
import gr.uom.java.ast.ClassObject;
import gr.uom.java.ast.FieldInstructionObject;
import gr.uom.java.ast.FieldObject;
import gr.uom.java.ast.MethodObject;
import gr.uom.java.ast.SystemObject;
import gr.uom.java.ast.TypeObject;

public class ImplementationOfMetrics {
	
 static double cohesionMetric(SystemObject system) {
		
		
		System.out.println("Calculating the cohesion metric values ....");
		
		Map<String, Double> cohesionOfClass = new HashMap<String, Double>();
		
		Set<ClassObject> classes = system.getClassObjects();
		
		for (ClassObject classObject : classes) {
			double classCohesion = calculateCohesion(classObject);
			cohesionOfClass.put(classObject.getName(), classCohesion);

		}

		double CohesionValue = 0.0;
		for(String key : cohesionOfClass.keySet()) {
			 CohesionValue = CohesionValue + cohesionOfClass.get(key);
        	System.out.println( key + "  " +  cohesionOfClass.get(key));
        }
		
		return CohesionValue/classes.size();
	}
	
	
	
	static  double calculateCohesion(ClassObject classObject){
		
		System.out.println("Cohesion for " + classObject);
		
		List<MethodObject> methods = classObject.getMethodList();
		
		Set<String> fieldValues = new HashSet<String>();
		
		for (int i = 0; i < methods.size() - 1; i++) {
			List<FieldInstructionObject> methhodpairparameterValues = methods.get(i).getFieldInstructions();
			for (FieldInstructionObject param : methhodpairparameterValues)
				fieldValues.add(param.getType().toString());	
		}
		
		if (fieldValues.size() == 0)
			return 0;
		
		
		double sumMethodPairValues = 0.0;
		//the cohesion as QMOOD metric CAMC
		for (int i = 0; i < methods.size() - 1; i++)
		{
			List<FieldInstructionObject> methodParameters = methods.get(i).getFieldInstructions();
			for (FieldInstructionObject param : methodParameters)
				if (fieldValues.contains(param.getType().toString()))
					sumMethodPairValues++;
			
		}
		System.out.println("Methods which share attributes " + sumMethodPairValues);
		
		
		return sumMethodPairValues/(methods.size() * fieldValues.size());
	}
	
	


	static double messagingMetric(SystemObject system) {

		Map<String, Integer> publicMethodsMap = new HashMap<String, Integer>();

		Set<ClassObject> classes = system.getClassObjects();

		for (ClassObject classObject : classes) {
			int publicMethodsCount = countPublicMessages(classObject);
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

	
	static int countPublicMessages(ClassObject classObject) {
		int result = 0;
		List<MethodObject> methods = classObject.getMethodList();
		for (int i = 0; i < methods.size() - 1; i++) {
			MethodObject method = methods.get(i);
			if (method.getAccess() == Access.PUBLIC)
				result++;
		}
		return result;
	}
	
	
	
	static double couplingMetric(SystemObject system) {
		
		Map<String, Integer> couplingMap = new HashMap<String, Integer>();
		
		Set<ClassObject> classes = system.getClassObjects();
		List<String> classTypes  = system.getClassNames();
		
		for (ClassObject classObject : classes) {
			List<String> cls = new ArrayList<String>(classTypes);
			
			cls.remove(classObject.getName());
			int directCoupling = calculateCoupling(classObject, cls);
			couplingMap.put(classObject.getName(), directCoupling);

		}	
		double sumCoupling = 0.0;
		for(String key : couplingMap.keySet()) {
			sumCoupling += couplingMap.get(key);
        	System.out.println( key + "  " +  couplingMap.get(key));
        }
		return sumCoupling/classes.size();
		
	}
	        //calculate coupling
	public static  int calculateCoupling(ClassObject classObject, List<String> otherClasses){
		Set<String> directClasses = new HashSet<String>();
		ListIterator<FieldObject> fieldIterator = classObject.getFieldIterator();
		while(fieldIterator.hasNext()) {
			FieldObject field = fieldIterator.next();
			if (otherClasses.contains(field.getClassName()))
				directClasses.add(field.getClassName());
		}
			
		List<MethodObject> methods = classObject.getMethodList();
		for (int i = 0; i < methods.size() - 1; i++) {
			List<FieldInstructionObject> methodParameters = methods.get(i).getFieldInstructions();
			for (FieldInstructionObject param : methodParameters)
				if(otherClasses.contains(param.getOwnerClass()))
					directClasses.add(param.getOwnerClass());
		}
		return directClasses.size();
	}
	  
	
	
	
	
     static double designSizeMetric(SystemObject system) {
		
		return system.getClassObjects().size();
	}
            
     //calculate polymorphism
     static double calculatePolymorphism(SystemObject system) {
 		Map<String, Integer> allMethodMap = new HashMap<String, Integer>();

 		Set<ClassObject> classes = system.getClassObjects();

 		for (ClassObject classObject : classes) {
 			int allMethodCount = calculateAbstractMethod(classObject);
 			allMethodMap.put(classObject.getName(), allMethodCount);
 		}
         int totalAbstractMethods = 0;
         for(String key : allMethodMap.keySet()) {
         	totalAbstractMethods += allMethodMap.get(key);
         }        
 		return totalAbstractMethods;
 	}
 	
 	private static  int calculateAbstractMethod(ClassObject classObject) {
 		List<MethodObject> methods = classObject.getMethodList();
 		int abstractMethods = 0;
 		for (int i = 0; i < methods.size() - 1; i++) {
 			if(methods.get(i).isAbstract()) {
 				abstractMethods++;
 			}		
 		}
 		return abstractMethods;
 	}
 	
 	
 	
 	               // Calculate Complexity
 	static double calculateComplexity(SystemObject system) {
 		Map<String, Integer> allMethodMap = new HashMap<String, Integer>();

 		Set<ClassObject> classes = system.getClassObjects();

 		for (ClassObject classObject : classes) {
 			List<MethodObject> methods = classObject.getMethodList();
 			int allMethodCount = methods.size();
 			allMethodMap.put(classObject.getName(), allMethodCount);
 		}
         double nbClasses = classes.size();
         double totalAllMethods = 0.0;
         for(String key : allMethodMap.keySet()) {
         	totalAllMethods += allMethodMap.get(key);
         	
         }
         
 		return totalAllMethods/nbClasses;
 		
 		
 	}
               // calculate Encapsulation
	static double calculateEncapsulation(SystemObject system) {
	

		Set<ClassObject> classes = system.getClassObjects();
		double totalRatioSUM = 0.0;
		for (ClassObject classObject : classes) {
			List<FieldObject> fields = classObject.getFieldList();
			int allFieldSize = fields.size();
			
			int PrivateProtectedSize = countPrivateProtected(classObject);
			if(allFieldSize != 0) {
				double Ratio = ((double) PrivateProtectedSize)/allFieldSize;
				
				totalRatioSUM += Ratio;
			
			}
			
		}
	
        double nbClasses = classes.size();
        
		return totalRatioSUM/nbClasses;

	}
 	

 	private static  int countPrivateProtected(ClassObject classObject) {
 		int result = 0;
 		List<FieldObject> fields = classObject.getFieldList();
 		for (int i = 0; i < fields.size(); i++) {
 			FieldObject field = fields.get(i);
 			if (field.getAccess() == Access.PRIVATE || field.getAccess() == Access.PROTECTED) {
 				result++;
 			}				
 		}
 		return result;
 	}

 	//calculate Composition
public static double calculateComposition(SystemObject system) {
	Set<ClassObject> classes = system.getClassObjects();
	double compositionValue=0.0;
	compositionValue=couplingMetric(system)*classes.size();
    
	return compositionValue;
	
}
static double calculateAbstraction(SystemObject system) {
	
	Map<String, Integer> allFieldMap = new HashMap<String, Integer>();

	Set<ClassObject> classes = system.getClassObjects();

	for (ClassObject classObject : classes) {
		
		
		int lengthSuper = calculateDepth(classObject, 0);
		
		allFieldMap.put(classObject.getName(), lengthSuper);
	}
	double nbClasses = classes.size();
    double totalSUMofDepth = 0.0;
    for(String key : allFieldMap.keySet()) {
    	totalSUMofDepth += allFieldMap.get(key);
    }
	return totalSUMofDepth/nbClasses;
}

private static int calculateDepth(ClassObject classObject, int depth) {
	TypeObject superclass = classObject.getSuperclass();
	if(superclass == null) {
		//System.out.println("SUPERCLASS NOT FOUND");
		return depth;
	} else {
		//calculateDepth(superClassObject, depth++);
		ClassObject spObject = ASTReader.getSystemObject().getClassObject(superclass.getClassType());
		if(spObject != null) {
			//System.out.println("SUPERCLASS FOUND");
			return calculateDepth(spObject,depth+1);
		} else {
			return depth;
		}
	}
	
	
	
}
}
	
	
	
	
	
	


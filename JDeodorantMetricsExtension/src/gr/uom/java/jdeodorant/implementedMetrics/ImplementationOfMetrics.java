package gr.uom.java.jdeodorant.implementedMetrics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import gr.uom.java.jdeodorant.implementedMetrics.WritingtheValues;
import gr.uom.java.ast.ASTReader;
import gr.uom.java.ast.Access;
import gr.uom.java.ast.ClassObject;
import gr.uom.java.ast.FieldInstructionObject;
import gr.uom.java.ast.FieldObject;
import gr.uom.java.ast.MethodObject;
import gr.uom.java.ast.SystemObject;
import gr.uom.java.ast.TypeObject;

public class ImplementationOfMetrics {
	
	private String value="";
	
 static double cohesionMetric(SystemObject system) {

		//StringBuilder sw;
		String sk;
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
        //	sw.append(key + " " +cohesionOfClass.get(key) ).toString();
         	
        }
		sk="The system level cohesion value is :" + CohesionValue/classes.size();
		WritingtheValues.WritingtheSyatemvalues(sk);
		
		return CohesionValue/classes.size();
	}
	
	
	
	static  double calculateCohesion(ClassObject classObject){
		String sk;
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
		sk="cohesion of the classes is : "  + classObject  + " is "+ sumMethodPairValues/(methods.size() * fieldValues.size());
		 WritingtheValues.Writingthevalues(sk);
		return sumMethodPairValues/(methods.size() * fieldValues.size());
	}
	
	


	static double messagingMetric(SystemObject system) {
		String sk;

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
        sk="The system levek Messaging value is :" + totalPublicMethods/nbClasses;
        WritingtheValues.WritingtheSyatemvalues(sk);
		return totalPublicMethods/nbClasses;
	}

	
	static int countPublicMessages(ClassObject classObject) {
		String sk;
		int result = 0;
		List<MethodObject> methods = classObject.getMethodList();
		for (int i = 0; i < methods.size() - 1; i++) {
			MethodObject method = methods.get(i);
			if (method.getAccess() == Access.PUBLIC)
				result++;
		}
		sk="The messaging for " + classObject + " is " + result;
		 WritingtheValues.Writingthevalues(sk);
		
		return result;
	}
	
	
	
	static double couplingMetric(SystemObject system) {
		String sk;
		
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
		sk="The system level coupling vlue is : "  +sumCoupling/classes.size();
		 WritingtheValues.WritingtheSyatemvalues(sk);
		return sumCoupling/classes.size();
		
	}
	        //calculate coupling
	public static  int calculateCoupling(ClassObject classObject, List<String> otherClasses){
		String sk;
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
		sk=" The Coupling for class "+ classObject + " is " + directClasses.size();
		 WritingtheValues.Writingthevalues(sk);
		
		return directClasses.size();
	}
	  
	
	
	
	
     static double designSizeMetric(SystemObject system) {
    	 String sk="Design size for the system is: " + system.getClassObjects().size();
    	 WritingtheValues.Writingthevalues(sk);
    	 WritingtheValues.WritingtheSyatemvalues(sk);
		
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
 		String sk;
 		List<MethodObject> methods = classObject.getMethodList();
 		int abstractMethods = 0;
 		for (int i = 0; i < methods.size() - 1; i++) {
 			if(methods.get(i).isAbstract()) {
 				abstractMethods++;
 			}		
 		}
 		sk="The Polymorphism for the class " + classObject + " is :" +  abstractMethods;
 	 	 WritingtheValues.Writingthevalues(sk);
 		
 		return abstractMethods;
 	}
 	
 	
 	
 	               // Calculate Complexity
 	static double calculateComplexity(SystemObject system) {
 		String sk;
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
         sk="Complexity for the system is : " +  totalAllMethods/nbClasses;
         WritingtheValues.Writingthevalues(sk);
         
 		return totalAllMethods/nbClasses;
 		
 		
 	}
               // calculate Encapsulation
	static double calculateEncapsulation(SystemObject system) {
	String sk;

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
        sk="The Encapsulation for the system is: " + totalRatioSUM/nbClasses;
        WritingtheValues.Writingthevalues(sk);
        
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
	String sk;
	Set<ClassObject> classes = system.getClassObjects();
	double compositionValue=0.0;
	compositionValue=couplingMetric(system)*classes.size();
	sk="The composition for the system is  : " + compositionValue;
	 WritingtheValues.Writingthevalues(sk);
    
	return compositionValue;
	
}
static double calculateAbstraction(SystemObject system) {
	String sk;
	
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
    sk="The Abstraction for the system is  : " + totalSUMofDepth/nbClasses;
    WritingtheValues.Writingthevalues(sk);
	return totalSUMofDepth/nbClasses;
}

private static int calculateDepth(ClassObject classObject, int depth) {
	String sk;
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
			sk="the Depth of inheritence for the class is " + classObject + depth;
			  WritingtheValues.Writingthevalues(sk);
			return depth;
		}
	}
	
	
	
}
}

	
	
	
	
	
	


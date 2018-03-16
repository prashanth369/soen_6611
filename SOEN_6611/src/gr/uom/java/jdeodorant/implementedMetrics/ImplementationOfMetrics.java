package gr.uom.java.jdeodorant.implementedMetrics;

import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import gr.uom.java.ast.Access;
import gr.uom.java.ast.ClassObject;
import gr.uom.java.ast.FieldInstructionObject;
import gr.uom.java.ast.FieldObject;
import gr.uom.java.ast.MethodObject;

public class ImplementationOfMetrics {
	public  double calculateCohesion(ClassObject classObject){
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
		
		//for (String s : fieldValues)
		   //System.out.println("param: " + s);
		
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
	public  int calculateCoupling(ClassObject classObject, List<String> otherClasses){
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
	  int countPublicMessages(ClassObject classObject) {
		int result = 0;
		List<MethodObject> methods = classObject.getMethodList();
		for (int i = 0; i < methods.size() - 1; i++) {
			MethodObject method = methods.get(i);
			if (method.getAccess() == Access.PUBLIC)
				result++;
		}
		return result;
	}

	
	
}

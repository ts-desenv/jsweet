package org.jsweet.transpiler.extension;

import java.math.BigDecimal;

import javax.lang.model.element.Element;

import org.jsweet.transpiler.model.MethodInvocationElement;
import org.jsweet.transpiler.model.NewClassElement;

public class CustomBigDecimalAdapter extends PrinterAdapter {

    public CustomBigDecimalAdapter(PrinterAdapter parent) {
        super(parent);

        // Mapeia tipo
        addTypeMapping(BigDecimal.class.getName(), "Big");
    }

    @Override
    public boolean substituteNewClass(NewClassElement newClass) {

        String className = newClass.getTypeAsElement().toString();

        if (BigDecimal.class.getName().equals(className)) {
            print("new Big(")
                .printArgList(newClass.getArguments())
                .print(")");
            return true;
        }

        return super.substituteNewClass(newClass);
    }

    @Override
    public boolean substituteMethodInvocation(MethodInvocationElement invocation) {

        if (invocation.getTargetExpression() != null) {

            Element targetType = invocation.getTargetExpression().getTypeAsElement();

            if (BigDecimal.class.getName().equals(targetType.toString())) {

                switch (invocation.getMethodName()) {

                    case "add":
                    	printMacroName(invocation.getMethodName());
    					print(invocation.getTargetExpression()).print(".plus(")
    							.printArgList(invocation.getArguments()).print(")");
                        return true;

                    case "subtract":
                    	printMacroName(invocation.getMethodName());
    					print(invocation.getTargetExpression()).print(".minus(")
    							.printArgList(invocation.getArguments()).print(")");
                        return true;

                    case "multiply":
                    	printMacroName(invocation.getMethodName());
                    	
                    	print(invocation.getTargetExpression()).print(".times(")
						.printArgList(invocation.getArguments()).print(")");
                        return true;

                    case "divide":
                    	printMacroName(invocation.getMethodName());
                    	
                    	print(invocation.getTargetExpression()).print(".div(")
						.print(invocation.getArguments().get(0)).print(")");
                    	
                        return true;
                        
                    case "scale":
    					printMacroName(invocation.getMethodName());
    					// we assume that we always have a scale of 2, which is a
    					// good default if we deal with currencies...
    					// to be changed/implemented further
    					print("2");
    					return true;
    				case "setScale":
    					printMacroName(invocation.getMethodName());
    					print(invocation.getTargetExpression()).print(".round(").print(invocation.getArguments().get(0))
    							.print(")");
    					return true;
    				case "compareTo":
    					printMacroName(invocation.getMethodName());
    					print(invocation.getTargetExpression()).print(".cmp(").print(invocation.getArguments().get(0))
    							.print(")");
    					return true;
    				case "equals":
    					printMacroName(invocation.getMethodName());
    					print(invocation.getTargetExpression()).print(".eq(").print(invocation.getArguments().get(0))
    							.print(")");
    					return true;
    				case "signum":
    					printMacroName(invocation.getMethodName());
    					print(invocation.getTargetExpression()).print(".cmp(0)");
    					return true;
                }
            }
        }

        return super.substituteMethodInvocation(invocation);
    }
}

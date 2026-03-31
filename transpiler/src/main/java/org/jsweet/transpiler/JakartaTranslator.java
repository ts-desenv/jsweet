package org.jsweet.transpiler;

import org.jsweet.transpiler.extension.PrinterAdapter;

import standalone.com.sun.source.tree.AnnotationTree;
import standalone.com.sun.source.tree.CompilationUnitTree;
import standalone.com.sun.source.tree.ImportTree;
import standalone.com.sun.source.util.Trees;

public class JakartaTranslator extends Java2TypeScriptTranslator {

	public JakartaTranslator(PrinterAdapter adapter, TranspilationHandler logHandler, JSweetContext context,
			CompilationUnitTree compilationUnit, boolean fillSourceMap) {
		super(adapter, logHandler, context, compilationUnit, fillSourceMap);
	}
	
	private String mapJakarta(String name) {
        if (name.startsWith("jakarta.persistence")) {
            return name.replace(
                "jakarta.persistence",
                "javax.persistence");
        }
        return name;
    }
	
	@Override
	public Void visitImport(ImportTree importTree, Trees trees) {

	    String qualified = importTree.getQualifiedIdentifier().toString();

	    qualified = mapJakarta(qualified);

	    print("import " + qualified + ";");
	    
	    return null;
	}
	
	@Override
	public Void visitAnnotation(AnnotationTree annotation, Trees trees) {

		String ann = annotation.getAnnotationType().toString();

		ann = mapJakarta(ann);

		print("@" + ann);

		return null;
	}
	

}

package org.jsweet.transpiler;

import org.jsweet.transpiler.extension.PrinterAdapter;

import standalone.com.sun.source.tree.CompilationUnitTree;

public class JakartaJSweetFactory extends JSweetFactory {
	
	public Java2TypeScriptTranslator createTranslator(PrinterAdapter adapter, TranspilationHandler transpilationHandler,
			JSweetContext context, CompilationUnitTree compilationUnit, boolean fillSourceMap) {
		return new JakartaTranslator(adapter, transpilationHandler, context, compilationUnit, fillSourceMap);
	}

}

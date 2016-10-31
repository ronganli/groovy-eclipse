/*******************************************************************************
 * Copyright (c) 2000, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.core.tests.eval;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.tests.util.AbstractCompilerTest;

import junit.framework.Test;
import junit.framework.TestSuite;
/**
 * Run all tests defined in this package.
 */
public class TestAll extends EvaluationTest {
public TestAll(String name) {
	super(name);
}
public static Test suite() {
	// Disable evaluation tests on Linux
	if (System.getProperty("os.name").indexOf("Linux") == -1) {
		List<Class<? extends Test>> testClasses = new ArrayList<Class<? extends Test>>();

		testClasses.add(SanityTestEvaluationContext.class);
		testClasses.add(SanityTestEvaluationResult.class);
		testClasses.add(VariableTest.class);
		testClasses.add(CodeSnippetTest.class);
		testClasses.add(NegativeCodeSnippetTest.class);
		testClasses.add(NegativeVariableTest.class);
		testClasses.add(DebugEvaluationTest.class);

		return AbstractCompilerTest.buildAllCompliancesTestSuite(TestAll.class, DebugEvaluationSetup.class, testClasses);
	}
	return new TestSuite(TestAll.class.getName());
}
}

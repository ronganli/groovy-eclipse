/*
 * Copyright 2009-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.groovy.eclipse.test.adapters;

import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.eclipse.test.EclipseTestCase;
import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.tests.util.GroovyUtils;

/**
 * Tests the Groovy File Adapter Factory
 *
 * @author David Kerber
 */
public class GroovyFileAdapterFactoryTestCase extends EclipseTestCase {

	/**
	 * This is your happy path : )
	 *
	 * @throws Exception
	 */
	public void testFileAdapter() throws Exception {
       testProject.createGroovyTypeAndPackage( "pack1",
                "MainClass.groovy",
                "class MainClass { static void main(String[] args){}}");

        fullProjectBuild();

        final IFile script = (IFile) testProject.getProject().findMember( "src/pack1/MainClass.groovy" );

        assertNotNull(script);

        ClassNode node = script.getAdapter(ClassNode.class);

        assertEquals( "pack1.MainClass", node.getName() ) ;
        assertFalse( node.isInterface() ) ;
        assertNotNull( node.getMethods("main") ) ;
	}

	/**
	 * If there is a compile error we will still find it
	 *
	 * @throws Exception
	 */
	public void testFileAdapterCompileError() throws Exception {
        if (GroovyUtils.GROOVY_LEVEL < 18) {
            // compiler recovery not imoplemented on 1.7 and earlier
            return;
        }
        testProject.createGroovyTypeAndPackage( "pack1",
                "OtherClass.groovy",
                "class OtherClass { static void main(String[] args");

        fullProjectBuild();

        final IFile script = (IFile) testProject.getProject().findMember( "src/pack1/OtherClass.groovy" );
        ClassNode node = script.getAdapter(ClassNode.class);

        assertEquals( "pack1.OtherClass", node.getName() ) ;
        assertFalse( node.isInterface() ) ;
        assertNotNull( node.getMethods("main") ) ;
	}
	/**
	 * If there is a really bad compile error we may not find it
	 *
	 * @throws Exception
	 */
	public void testFileAdapterHorendousCompileError() throws Exception {
	    /*this one has a compile error*/
	    testProject.createGroovyTypeAndPackage( "pack1",
	            "OtherClass.groovy",
	            "class C {\n" +
	            "   abstract def foo() {}\n" +
	            "}");

	    fullProjectBuild();

	    final IFile script = (IFile) testProject.getProject().findMember( "src/pack1/OtherClass.groovy" );
	    ClassNode node = script.getAdapter(ClassNode.class);
	    assertNull(node);
	}

	/**
	 * If it is not a groovy file you wont' find it.
	 *
	 * @throws Exception
	 */
	public void testFileAdapterNotGroovyFile() throws Exception {
        /*this one is not a script file*/
        testProject.createGroovyTypeAndPackage( "pack1",
                "NotGroovy.file",
                "this is not a groovy file");

        fullProjectBuild() ;

        final IFile notScript = (IFile) testProject.getProject().findMember( "src/pack1/NotGroovy.file" );
        assertNotNull(notScript);
        assertNull( notScript.getAdapter(ClassNode.class) );
	}
}

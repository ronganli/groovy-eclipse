/*******************************************************************************
 * Copyright (c) 2009 SpringSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Andrew Eisenberg - initial API and implementation
 *******************************************************************************/

package org.codehaus.groovy.eclipse.codeassist.tests;



/**
 * @author Andrew Eisenberg
 * @created Jun 5, 2009
 *
 * Tests that type completions are working properly.  Ensures that the
 * resulting document has the correct text in it.
 */
public class TypeCompletionTests2 extends CompletionTestCase {


    private static final String HTML = "HTML";
    private static final String HTML_PROPOSAL = "HTML - javax.swing.text.html";
    public TypeCompletionTests2(String name) {
        super(name);
    }

    public void testSimpleCompletionTypesInScript1() throws Exception {
    	String contents = HTML;
        String expected = "import javax.swing.text.html.HTML;\n\nHTML";
        checkProposalApplicationType(contents, expected, getIndexOf(contents, HTML),
                HTML_PROPOSAL);
    }

    public void testSimpleCompletionTypesInScript2() throws Exception {
        String contents = "import javax.swing.plaf.ButtonUI;\n\nHTML\nButtonUI";
        String expected = "import javax.swing.plaf.ButtonUI;\nimport javax.swing.text.html.HTML;\n\nHTML\nButtonUI";
        checkProposalApplicationType(contents, expected, getIndexOf(contents, HTML),
                HTML_PROPOSAL);
    }

    public void testBrokenScript1() throws Exception {
        String contents = "def x(HTML";
        String expected = "import javax.swing.text.html.HTML;\n\ndef x(HTML";
        checkProposalApplicationType(contents, expected, getIndexOf(contents, HTML),
                HTML_PROPOSAL);
    }
    
    public void testBrokenScript2() throws Exception {
        String contents = "package f\n\ndef x(HTML";
        String expected = "package f\n\nimport javax.swing.text.html.HTML;\n\n\ndef x(HTML";
        checkProposalApplicationType(contents, expected, getIndexOf(contents, HTML),
                HTML_PROPOSAL);
    }
    
    public void testBrokenScript3() throws Exception {
        String contents = "/**some stuff*/\npackage f\n\ndef x(HTML";
        String expected = "/**some stuff*/\npackage f\n\nimport javax.swing.text.html.HTML;\n\n\ndef x(HTML";
        checkProposalApplicationType(contents, expected, getIndexOf(contents, HTML),
                HTML_PROPOSAL);
    }
    
    // Bug !!! See GRECLIPSE-1231  import statements placed on same line because ';' is not recognized as part of the import statement
    public void testBrokenScript4() throws Exception {
        String contents = "/**some stuff*/\n\nimport javax.swing.plaf.ButtonUI;\n\ndef x(HTML";
        String expected = "/**some stuff*/\n\nimport javax.swing.plaf.ButtonUI;import javax.swing.text.html.HTML;\n\n\ndef x(HTML";
        checkProposalApplicationType(contents, expected, getIndexOf(contents, HTML),
                HTML_PROPOSAL);
    }
    
    // Bug !!! See GRECLIPSE-1231  import statements placed on same line because ';' is not recognized as part of the import statement
    public void testBrokenScript5() throws Exception {
        String contents = "/**some stuff*/\npackage f\n\nimport javax.swing.plaf.ButtonUI;\n\ndef x(HTML";
        String expected = "/**some stuff*/\npackage f\n\nimport javax.swing.plaf.ButtonUI;import javax.swing.text.html.HTML;\n\n\ndef x(HTML";
        checkProposalApplicationType(contents, expected, getIndexOf(contents, HTML),
                HTML_PROPOSAL);
    }
    

    public void testBrokenScript6() throws Exception {
        String contents = 
                "/**some stuff*/\n" + 
        		"package f\n" + 
        		"HTML";
        String expected = 
                "/**some stuff*/\n" + 
        		"package f\n" +
        		"\n" +
        		"import javax.swing.text.html.HTML;\n" +
        		"\n" +
        		"HTML";
        checkProposalApplicationType(contents, expected, getIndexOf(contents, HTML),
                HTML_PROPOSAL);
    }
    public void testBrokenScript7() throws Exception {
        String contents = 
                "/**some stuff*/\n" + 
                "package f\n" + 
                "\n" + 
                "import javax.swing.plaf.ButtonUI;\n" + 
                "\n" + 
                "def x(HTML\n" + 
                "";
        String expected = 
                "/**some stuff*/\n" + 
                "package f\n" + 
                "\n" + 
                "import javax.swing.plaf.ButtonUI;import javax.swing.text.html.HTML;\n" + 
                "\n" + 
                "\n" + 
                "def x(HTML\n" + 
                "";
        checkProposalApplicationType(contents, expected, getIndexOf(contents, HTML),
                HTML_PROPOSAL);
    }
    public void testBrokenScript8() throws Exception {
        String contents = 
                "/**some stuff*/\n" + 
                "package f\n" + 
                "\n" + 
                "import javax.swing.plaf.ButtonUI\n" + 
                "\n" + 
                "def x(HTML";
        String expected = 
                "/**some stuff*/\n" + 
                "package f\n" + 
                "\n" + 
                "import javax.swing.plaf.ButtonUI\n" + 
                "import javax.swing.text.html.HTML;\n" + 
                "\n" + 
                "def x(HTML";
        checkProposalApplicationType(contents, expected, getIndexOf(contents, HTML),
                HTML_PROPOSAL);
    }

    
    public void testBrokenScript9() throws Exception {
        String contents = 
                "/**some stuff*/\n" + 
                "package f\n" + 
                "\n" + 
                "import javax.swing.plaf.ColorChooserUI;\n" + 
                "import javax.swing.plaf.ButtonUI as foo\n" + 
                "\n" + 
                "def x(HTML";
        String expected = 
                "/**some stuff*/\n" + 
                "package f\n" + 
                "\n" + 
                "import javax.swing.plaf.ColorChooserUI;\n" + 
                "import javax.swing.plaf.ButtonUI as foo\n" + 
                "import javax.swing.text.html.HTML;\n" + 
                "\n" + 
                "def x(HTML";
        checkProposalApplicationType(contents, expected, getIndexOf(contents, HTML),
                HTML_PROPOSAL);
    }

    public void testBrokenScript10() throws Exception {
        String contents = 
                "/**some stuff*/\n" + 
                "\n" + 
                "import javax.swing.plaf.ColorChooserUI;\n" + 
                "import javax.swing.plaf.ButtonUI as foo\n" + 
                "\n" + 
                "def x(HTML";
        String expected = 
                "/**some stuff*/\n" + 
                "\n" + 
                "import javax.swing.plaf.ColorChooserUI;\n" + 
                "import javax.swing.plaf.ButtonUI as foo\n" + 
                "import javax.swing.text.html.HTML;\n" + 
                "\n" + 
                "def x(HTML";
        checkProposalApplicationType(contents, expected, getIndexOf(contents, HTML),
                HTML_PROPOSAL);
    }

    public void testBrokenClass1() throws Exception {
        String contents = "/**some stuff*/\npackage f\n\nclass Y {\ndef x(HTML";
        String expected = "/**some stuff*/\npackage f\n\nimport javax.swing.text.html.HTML;\n\nclass Y {\ndef x(HTML";
        checkProposalApplicationType(contents, expected, getIndexOf(contents, HTML),
                HTML_PROPOSAL);
    }
    
    public void testBrokenClass2() throws Exception {
        String contents = "/**some stuff*/\npackage f\n\nclass Y extends HTML {\ndef x(H";
        String expected = "/**some stuff*/\npackage f\n\nimport javax.swing.text.html.HTML;\n\nclass Y extends HTML {\ndef x(H";
        checkProposalApplicationType(contents, expected, getIndexOf(contents, HTML),
                HTML_PROPOSAL);
    }
}
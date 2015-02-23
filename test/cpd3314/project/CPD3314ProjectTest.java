/*
 * Copyright 2014 Len Payne <len.payne@lambtoncollege.ca>.
 * Updated 2015 Mark Russell <mark.russell@lambtoncollege.ca>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cpd3314.project;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileSystems;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.yaml.snakeyaml.Yaml;

/**
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
public class CPD3314ProjectTest {

    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    public CPD3314ProjectTest() {
    }

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void tearDown() {
        System.setOut(null);
        System.setErr(null);
    }

    private void assertFilesEqual(File a, File b) throws IOException {
        Scanner aIn = new Scanner(a);
        Scanner bIn = new Scanner(b);
        while (aIn.hasNext() && bIn.hasNext()) {
            assertEquals(aIn.nextLine().trim(), bIn.nextLine().trim());
        }
        assertTrue("Files Not Equal Length", !aIn.hasNext() && !bIn.hasNext());
    }

    private void assertXMLFilesEqual(File a, File b) throws IOException, SAXException {
        Scanner aIn = new Scanner(a);
        Scanner bIn = new Scanner(b);
        StringBuilder aXML = new StringBuilder();
        StringBuilder bXML = new StringBuilder();
        while (aIn.hasNext() && bIn.hasNext()) {
            aXML.append(aIn.nextLine().trim());
            bXML.append(bIn.nextLine().trim());
        }
        assertTrue("Files Not Equal Length", !aIn.hasNext() && !bIn.hasNext());
        assertXMLEqual(aXML.toString(), bXML.toString());
    }

    private void assertJSONFilesEqual(File a, File b) throws IOException {
        Scanner aIn = new Scanner(a);
        Scanner bIn = new Scanner(b);
        StringBuilder aJSON = new StringBuilder();
        StringBuilder bJSON = new StringBuilder();
        while (aIn.hasNext() && bIn.hasNext()) {
            aJSON.append(aIn.nextLine().trim());
            bJSON.append(bIn.nextLine().trim());
        }
        assertTrue("Files Not Equal Length", !aIn.hasNext() && !bIn.hasNext());
        JSONObject aJ = (JSONObject) JSONValue.parse(aJSON.toString());
        JSONObject bJ = (JSONObject) JSONValue.parse(bJSON.toString());
        assertEquals(aJ.toJSONString(), bJ.toJSONString());
    }

    private void assertYAMLFilesEqual(File a, File b) throws IOException {
        Yaml yaml1 = new Yaml();
        Yaml yaml2 = new Yaml();
        FileInputStream aIS = new FileInputStream(a);
        FileInputStream bIS = new FileInputStream(b);
        Iterable<Object> aYAML = yaml1.loadAll(aIS);
        Iterable<Object> bYAML = yaml2.loadAll(bIS);
        Set<Object> aSet = new HashSet<>();
        Set<Object> bSet = new HashSet<>();
        for (Object x : aYAML) {
            aSet.add(x);
        }
        for (Object y : bYAML) {
            bSet.add(y);
        }
        assertEquals("Files Not Equal Length", aSet.size(), bSet.size());
        assertTrue("A does not contain all B", aSet.containsAll(bSet));
        assertTrue("B does not contain all A", bSet.containsAll(aSet));
    }

    @Test
    public void testNoArg() throws Exception {
        System.out.println("main");
        String[] args = {};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "noArg.xml").toFile();
        File result = new File("CPD3314.xml");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertXMLFilesEqual(expected, result);
    }

    @Test
    public void testFormatXML() throws Exception {
        System.out.println("main");
        String[] args = {"-format=XML"};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "formatXML.xml").toFile();
        File result = new File("CPD3314.xml");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertXMLFilesEqual(expected, result);
    }

    @Test
    public void testFormatJSON() throws Exception {
        System.out.println("main");
        String[] args = {"-format=JSON"};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "formatJSON.json").toFile();
        File result = new File("CPD3314.json");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertJSONFilesEqual(expected, result);
    }

    @Test
    public void testFormatYAML() throws Exception {
        System.out.println("main");
        String[] args = {"-format=YAML"};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "formatYAML.yaml").toFile();
        File result = new File("CPD3314.yaml");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertYAMLFilesEqual(expected, result);
    }
    
    @Test
    public void testFormatSQL() throws Exception {
        System.out.println("main");
        String[] args = {"-format=SQL"};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "formatSQL.sql").toFile();
        File result = new File("CPD3314.sql");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertFilesEqual(expected, result);
    }
    
    @Test
    public void testFormatHTML() throws Exception {
        System.out.println("main");
        String[] args = {"-format=HTML"};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "formatHTML.html").toFile();
        File result = new File("CPD3314.html");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertXMLFilesEqual(expected, result);
    }
    
    @Test
    public void testOutput() throws Exception {
        System.out.println("main");
        String[] args = {"-o=test"};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "noArg.xml").toFile();
        File result = new File("test.xml");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertXMLFilesEqual(expected, result);
    }

    @Test
    public void testLimitAndOutput() throws Exception {
        System.out.println("main");
        String[] args = {"-o=ten", "-limit=10"};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "limit10.xml").toFile();
        File result = new File("ten.xml");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertXMLFilesEqual(expected, result);
    }
    
    @Test
    public void testSortAAndLimitAndOutput() throws Exception {
        System.out.println("main");
        String[] args = {"-sort=A", "-o=a", "-limit=10"};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "sortA.xml").toFile();
        File result = new File("a.xml");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertXMLFilesEqual(expected, result);
    }
    
    @Test
    public void testSortIAndLimitAndOutput() throws Exception {
        System.out.println("main");
        String[] args = {"-sort=I", "-o=i", "-limit=10"};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "sortI.xml").toFile();
        File result = new File("i.xml");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertXMLFilesEqual(expected, result);
    }
    
    @Test
    public void testSortDAndLimitAndOutput() throws Exception {
        System.out.println("main");
        String[] args = {"-sort=D", "-o=d", "-limit=10"};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "sortD.xml").toFile();
        File result = new File("d.xml");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertXMLFilesEqual(expected, result);
    }
    
    @Test
    public void testGetIDAndOutput() throws Exception {
        System.out.println("main");
        String[] args = {"-getID=400", "-o=fourHundred"};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "get400.xml").toFile();
        File result = new File("fourHundred.xml");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertXMLFilesEqual(expected, result);
    }
    
    @Test
    public void testGetDateAndOutput() throws Exception {
        System.out.println("main");
        String[] args = {"-getDate=2013-02-09", "-o=byDate"};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "getDate.xml").toFile();
        File result = new File("byDate.xml");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertXMLFilesEqual(expected, result);
    }
    
    @Test
    public void testFindOneWordAndOutput() throws Exception {
        System.out.println("main");
        String[] args = {"-find=lorem", "-o=lorem"};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "findOne.xml").toFile();
        File result = new File("lorem.xml");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertXMLFilesEqual(expected, result);
    }
    
    @Test
    public void testFindMultiWordsAndOutput() throws Exception {
        System.out.println("main");
        String[] args = {"-find=dolor sit amet", "-o=dolor"};
        CPD3314Project.main(args);
        File expected = FileSystems.getDefault().getPath("testFiles", "findMulti.xml").toFile();
        File result = new File("dolor.xml");
        assertTrue("Output File Doesn't Exist", result.exists());
        assertXMLFilesEqual(expected, result);
    }
}

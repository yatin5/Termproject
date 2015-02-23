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

import java.util.HashMap;
import java.util.Map;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.yaml.snakeyaml.Yaml;

/**
 * @author Len Payne <len.payne@lambtoncollege.ca>
 */
public class ProductTest {

    public ProductTest() {
    }

    private Product sample;
    private final int sampleId = 1;
    private final String sampleName = "Wood Screw";
    private final String sampleDesc = "To screw wood.";
    private final int sampleQuantity = 10;
    private final String sampleDate = "2014-10-14";

    @Before
    public void setUp() {
        sample = new Product(sampleId, sampleName, sampleDesc, sampleQuantity, sampleDate);
    }

    /**
     * Test of getId method, of class Product.
     */
    @Test
    public void testGetId() throws Exception {
        System.out.println("getId");
        Product instance = new Product();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Product.
     */
    @Test
    public void testSetId() throws Exception {
        System.out.println("setId");
        int id = 4;
        Product instance = new Product();
        instance.setId(id);
        int result = instance.getId();
        assertEquals(id, result);
    }

    /**
     * Test of getName method, of class Product.
     */
    @Test
    public void testGetName() throws Exception {
        System.out.println("getName");
        Product instance = new Product();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Product.
     */
    @Test
    public void testSetName() throws Exception {
        System.out.println("setName");
        String name = "bob";
        Product instance = new Product();
        instance.setName(name);
        String result = instance.getName();
        assertEquals(name, result);
    }

    /**
     * Test of getDescription method, of class Product.
     */
    @Test
    public void testGetDescription() throws Exception {
        System.out.println("getDescription");
        Product instance = new Product();
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Product.
     */
    @Test
    public void testSetDescription() throws Exception {
        System.out.println("setDescription");
        String description = "bob";
        Product instance = new Product();
        instance.setDescription(description);
        String result = instance.getDescription();
        assertEquals(description, result);
    }

    /**
     * Test of getQuantity method, of class Product.
     */
    @Test
    public void testGetQuantity() throws Exception {
        System.out.println("getQuantity");
        Product instance = new Product();
        int expResult = 0;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setQuantity method, of class Product.
     */
    @Test
    public void testSetQuantity() throws Exception {
        System.out.println("setQuantity");
        int quantity = 10;
        Product instance = new Product();
        instance.setQuantity(quantity);
        int result = instance.getQuantity();
        assertEquals(quantity, result);
    }

    /**
     * Test of getDateAdded method, of class Product.
     */
    @Test
    public void testGetDateAdded() throws Exception {
        System.out.println("getDateAdded");
        Product instance = new Product();
        String expResult = "";
        String result = instance.getDateAdded();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDateAdded method, of class Product.
     */
    @Test
    public void testSetDateAdded() throws Exception {
        System.out.println("setDateAdded");
        String dateAdded = "2014-10-14";
        Product instance = new Product();
        instance.setDateAdded(dateAdded);
        String result = instance.getDateAdded();
        assertEquals(dateAdded, result);
    }

    /**
     * Test of toXML method, of class Product.
     */
    @Test
    public void testToXML() throws Exception {
        System.out.println("toXML");
        Product instance = sample;
        String result = instance.toXML().replaceAll(">[ \t\n]+<", "><");
        String expected = String.format("<product><id>%d</id><name>%s</name>"
                + "<description>%s</description><quantity>%d</quantity>"
                + "<dateAdded>%s</dateAdded></product>",
                sampleId, sampleName, sampleDesc, sampleQuantity, sampleDate);
        assertXMLEqual(result, expected);
    }

    /**
     * Test of toYAML method, of class Product.
     */
    @Test
    public void testToYAML() throws Exception {
        System.out.println("toYAML");
        Product instance = sample;
        String result = instance.toYAML();
        Yaml yaml = new Yaml();
        Map<String, Object> resultSet = (Map<String, Object>) yaml.load(result);
        Map<String, Object> expectedSet = new HashMap<>();
        expectedSet.put("id", sampleId);
        expectedSet.put("name", sampleName);
        expectedSet.put("description", sampleDesc);
        expectedSet.put("quantity", sampleQuantity);
        expectedSet.put("dateAdded", sampleDate);
        for (String k : expectedSet.keySet()) {
            assertTrue(resultSet.containsKey(k));
            assertTrue(resultSet.get(k).equals(expectedSet.get(k)));
        }
    }

    /**
     * Test of toJSON method, of class Product.
     */
    @Test
    public void testToJSON() throws Exception {
        System.out.println("toJSON");
        Product instance = sample;
        JSONObject expected = new JSONObject();
        expected.put("name", sampleName);
        expected.put("id", sampleId);
        expected.put("description", sampleDesc);
        expected.put("quantity", sampleQuantity);
        expected.put("dateAdded", sampleDate);
        String result = instance.toJSON();
        JSONObject resultJSON = (JSONObject) JSONValue.parse(result);
        assertEquals(expected.toJSONString(), resultJSON.toJSONString());
    }

    /**
     * Test of toSQL method, of class Product.
     */
    @Test
    public void testToSQL() throws Exception {
        System.out.println("toSQL");
        Product instance = sample;
        String expResult = String.format("INSERT INTO Products VALUES (%d, \"%s\", \"%s\", %d, \"%s\");",
                sampleId, sampleName, sampleDesc, sampleQuantity, sampleDate);
        String result = instance.toSQL();
        assertEquals(expResult, result);
    }

    /**
     * Test of toHTML method, of class Product.
     */
    @Test
    public void testToHTML() throws Exception {
        System.out.println("toHTML");
        Product instance = sample;
        String[] expResult = {"<div class=\"product\">",
            "<h1>Wood Screw</h1>",
            "<p>ID: 1</p>",
            "<p>To screw wood.</p>",
            "<p>Quantity: 10</p>",
            "<p>Added: 2014-10-14</p>",
            "</div>"};
        String[] result = instance.toHTML().replaceAll(">[ \t\n]+", ">!!").split("!!");
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i], expResult[i]);
        }
    }

}

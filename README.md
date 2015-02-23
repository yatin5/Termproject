# CPD3314 Final Project – Data API

Build the Following Project and Submit to Dropbox on or before Apr. 16th, 2015

Your employers have a set of product records that are stored as an XML-formatted dataset. They have been given to you as ORIGINALS.xml. In a flash of foresight, your employer realized that many modern tools prefer other data formats including JSON and YAML. In addition, they would like a tool that formats all of the data into SQL statements, and another tool that formats all of the data into HTML blocks.
There are many ways to implement this, but your lead developer pointed you in the direction of the following tools:

* [snakeyaml](https://code.google.com/p/snakeyaml/)
* [JSON.Simple](https://code.google.com/p/json-simple/)
* [Simple XML Serialization](http://simple.sourceforge.net/)

The following specific behaviours are expected:

## Command Line Arguments

When built and run on the command line (eg- java CPD3314-Project ... ) the project should accept the following command-line arguments:

Command Argument                   | Result
-----------------------------------|---------------------------
`-format=<XML/JSON/YAML/SQL/HTML>` | Formats the output as appropriate: XML, JSON, YAML, SQL, or HTML. If excluded, default is XML.
`-sort=<A/I/D>`                              | Sorts the output (A)lphabetically by Name, by (I)D, or by (D)ate. If excluded, default is to preserve the source ordering.
`-limit=<#>`                       | Limits the output to a certain number of lines. Performed last (ie- after sorting and filtering).
`-getID=<ID>`                      | Retrieves a single result by its ID.
`-getDate=<DATE>`                  | Retrieves all results that match a given date.
`-find=<CONTENTS>`                 | Retrieves all results that include the given case-insensitive content in their name or description.
`-o=<filename>`                    | Outputs to a specific filename. Appropriate file extension is added by the program. If filename is excluded, default is CPD3314.<ext> where <ext> matches the appropriate output format.

## Command Line Examples

`java CPD3314-Project`

Produces an XML file named CPD3314.xml of all products, with no particular sorting

---

`java CPD3314-Project –format=XML`

Produces an XML file named CPD3314.xml of all products, with no particular sorting

---

`java CPD3314-Project –format=YAML`

Produces a YAML file named CPD3314.yaml of all products, with no particular sorting

---

`java CPD3314-Project –format=JSON`

Produces a JSON file named CPD3314.json of all products, with no particular sorting

---

`java CPD3314-Project –format=SQL`

Produces an SQL file named CPD3314.sql of all products, with no particular sorting

---

`java CPD3314-Project –format=HTML`

Produces an HTML file named CPD3314.html of all products, with no particular sorting

---

`java CPD3314-Project –o=test`

Produces an XML file named test.xml of all products, with no particular sorting

---

`java CPD3314-Project –o=ten –limit=10`

Produces an XML file named ten.xml of the first ten products, with no particular sorting

---

`java CPD3314-Project –sort=A –o=test –limit=10`

Produces an XML file named test.xml of the first ten products, sorted by name ascending

---

`java CPD3314-Project –sort=I –o=test –limit=10`

Produces an XML file named test.xml of the first ten products, sorted by ID ascending

---

`java CPD3314-Project –sort=D –o=test –limit=10`

Produces an XML file named test.xml of the first ten products, sorted by date ascending

---

`java CPD3314-Project –getID=400 –o=test`

Produces an XML file named test.xml that only contains the product with ID 400

---

`java CPD3314-Project –getDate=2015-06-14 –o=test`

Produces an XML file named test.xml that only contains the products added on Jun. 14, 2015

---

`java CPD3314-Project –find=Desk –o=test`

Produces an XML file named test.xml that only contains the products with “Desk” in their name or description

---

`java CPD3314-Project –find=”Rich Mahogany Desk” –o=test`

Produces an XML file named test.xml that only contains the products with “Rich Mahogany Desk” in their name or description

---

### Further Instructions in [Attached PDF](https://github.com/ProfRussell/CPD3314-Project/blob/master/CPD3314%20Data%20Project%20Instructions.pdf)

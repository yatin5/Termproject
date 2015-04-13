package cpd3314.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * Final Term Project for CPD-3314 Java Development I
 *
 * @author YATIN PATEL
 */
public class CPD3314Project {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        Serializer serializer = new Persister();
        File newfile = new File("ORIGINALS.xml");
        Products product = serializer.read(Products.class, newfile);
        List<Product> ProductList = product.getProductList();

        int length = args.length;
        if (length == 0) {
            xml(ProductList);
        }
        if (length == 1) {
            String command = args[0];

            String splitcommand[] = command.split("=");

            if (splitcommand[0].equals("-format")) {
                String formatto = splitcommand[1];
                switch (formatto) {
                    case "XML":
                        xml(ProductList);
                        /* function to convert to xml */
                        break;
                    case "YAML":
                        yaml(ProductList);
                        /*function to convert to yaml */
                        break;
                    case "JSON":
                        json(ProductList);
                        /* function to convert to JSON */
                        break;
                    case "SQL":
                        sql(ProductList);
                        /* function to convert to SQL */
                        break;
                    case "HTML":
                        html(ProductList);
                        /* function to convert to html */
                        break;
                    default:
                    /* nothing happens command needs to be reentered */
                }
            } else if (splitcommand[0].equals("-o")) {
                if (splitcommand[1].contains(".")) {
                    String s[] = splitcommand[1].split("\\.");
                    String fileextension = s[1].toLowerCase();
                    String filename = s[0];
                    switch (fileextension) {
                        case "xml":
                            xml(ProductList, filename);
                            break;
                        case "yaml":
                            yaml(ProductList, filename);
                            break;
                        case "json":
                            json(ProductList, filename);
                            break;
                        case "sql":
                            sql(ProductList, filename);
                            break;
                        case "html":
                            html(ProductList, filename);
                            break;
                        default:
                            System.out.println("command is incorrect");
                    }
                } else {
                    String filename = splitcommand[1];
                    xml(ProductList, filename);
                    /* function which creates a file name filename with all xml data in it without any sorting */
                }

            }
        } else if (length == 2) {
            String command = args[0];
            String splitcommand[] = command.split("=");
            if (splitcommand[0].equals("-o")) {
                String filename = splitcommand[1];
                String limitcommand = args[1];
                String splitlimit[] = limitcommand.split("=");
                String numberofrecords = splitlimit[1];
                int records = Integer.parseInt(numberofrecords);
                limitrecords(ProductList, filename, records);
                /* a function which accepts the filename and records as arguments and create a file with 
                 records in it named filename*/
            } else if (splitcommand[0].equals("-getID")) {
                String recordvalue = splitcommand[1];
                int record = Integer.parseInt(recordvalue);
                String filename = args[1];
                String splitfilename[] = filename.split("=");
                String filetobecreated = splitfilename[1];
                selectedrecord(ProductList, filetobecreated, record);
                /* a function which accets the record int value and file name and create a file with only that record init */
            } else if (splitcommand[0].equals("-getDate")) {
                String datestring = splitcommand[1];
                String filename = args[1];
                String splitfilename[] = filename.split("=");
                String filetobecreated = splitfilename[1];
                selectedrecordondate(ProductList, filetobecreated, datestring);
            } else if (splitcommand[0].equals("-find")) {
                String find = splitcommand[1];
                find = find.replace("", "");
                String filename = args[1];
                String splitfilename[] = filename.split("=");
                String filetobecreated = splitfilename[1];
                selectedrecordondata(ProductList, filetobecreated, find);
                /* function which eccept a string find and find it in the discription or name and make a new file named file*/
            }
        } else if (length == 3) {
            String command = args[0];
            String splitcommand[] = command.split("=");
            if (splitcommand[1].equals("A")) {
                String arg1 = args[1];
                //String limitcommand=args[1];
                String splitcommandargs[] = arg1.split("=");
                //String splitlimit[]=limitcommand.split("=");
                String filename = splitcommandargs[1];
                String arg2 = args[2];
                //String limitcommand=args[1];
                String splitcommandargs2[] = arg2.split("=");
                //String splitlimit[]=limitcommand.split("=");
                String numberofrecords = splitcommandargs2[1];
                int records = Integer.parseInt(numberofrecords);

                Collections.sort(ProductList, Product.getByName());
                sortbyname(ProductList, filename, records);
                /* a function which accepts the filename and records as arguments and create a file with 
                 records in it named filename*/
            } else if (splitcommand[1].equals("I")) {

                String arg1 = args[1];
                //String limitcommand=args[1];
                String splitcommandargs[] = arg1.split("=");
                //String splitlimit[]=limitcommand.split("=");
                String filename = splitcommandargs[1];
                String arg2 = args[2];
                //String limitcommand=args[1];
                String splitcommandargs2[] = arg2.split("=");
                //String splitlimit[]=limitcommand.split("=");
                String numberofrecords = splitcommandargs2[1];
                int records = Integer.parseInt(numberofrecords);

                Collections.sort(ProductList, Product.getById());
                sortbyid(ProductList, filename, records);
                /* a function which accepts the filename and records as arguments and create a file with 
                 records in it named filename*/
            } else if (splitcommand[1].equals("D")) {

                String arg1 = args[1];
                //String limitcommand=args[1];
                String splitcommandargs[] = arg1.split("=");
                //String splitlimit[]=limitcommand.split("=");
                String filename = splitcommandargs[1];
                String arg2 = args[2];
                //String limitcommand=args[1];
                String splitcommandargs2[] = arg2.split("=");
                //String splitlimit[]=limitcommand.split("=");
                String numberofrecords = splitcommandargs2[1];
                int records = Integer.parseInt(numberofrecords);

                Collections.sort(ProductList, Product.getByDate());
                sortbydate(ProductList, filename, records);
                /* a function which accepts the filename and records as arguments and create a file with 
                 records in it named filename*/
            }
        }

    }

    /**
     * xml() method take two arguments and write into the file with extension
     * dot XML
     * @param ProductList
     * @param filename
     * @throws FileNotFoundException
     */
    public static void xml(List<Product> ProductList, String... filename) throws FileNotFoundException {
        if (filename.length != 0) {
            PrintWriter file = new PrintWriter(filename[0] + ".XML");
            file.println("<products>");
            for (int i = 0; i < ProductList.size(); i++) {
                String pl = ProductList.get(i).toXML();
                file.println(pl);
            }
            file.println("</products>");
            file.close();
        }
        PrintWriter file;
        file = new PrintWriter("CPD3314.XML");
        file.println("<products>");
        for (int i = 0; i < ProductList.size(); i++) {
            String pl = ProductList.get(i).toXML();
            file.println(pl);
        }
        file.println("</products>");
        file.close();
    }

    /**
     * html() method take two arguments and write into the file with extension
     * dot HTML
     * @param ProductList
     * @param filename
     * @throws FileNotFoundException
     */
    public static void html(List<Product> ProductList, String... filename) throws FileNotFoundException {
        if (filename.length != 0) {
            PrintWriter file = new PrintWriter(filename[0] + ".html");
            file.println("<!DOCTYPE html>");
            file.println("<html>");
            file.println("<head>");
            file.println("</head>");
            file.println("<body>");
            for (int i = 0; i < ProductList.size(); i++) {
                String pl = ProductList.get(i).toHTML();
                file.println(pl);
            }
            file.println("</body>");
            file.println("</html>");
            file.close();
        }
        PrintWriter file;
        file = new PrintWriter("CPD3314.html");
        file.println("<!DOCTYPE html>");
        file.println("<html>");
        file.println("<head>");
        file.println("</head>");
        file.println("<body>");
        for (int i = 0; i < ProductList.size(); i++) {
            String pl = ProductList.get(i).toHTML();
            file.println(pl);
        }
        file.println("</body>");
        file.println("</html>");
        file.close();

    }

    /**
     * json() method take two arguments and write into the file with extension
     * dot JSON
     * @param ProductList
     * @param filename
     * @throws FileNotFoundException
     */
    public static void json(List<Product> ProductList, String... filename) throws FileNotFoundException {
        if (filename.length != 0) {
            PrintWriter file = new PrintWriter(filename[0] + ".json");
            file.print("{\"product\" : [ ");

            for (int i = 0; i < ProductList.size() - 1; i++) {
                String pl = ProductList.get(i).toJSON();
                file.print(pl + ", ");
            }
            String pl1 = ProductList.get(ProductList.size() - 1).toJSON();
            file.print(pl1);
            file.print(" ] }");
            file.close();
        }
        PrintWriter file;
        file = new PrintWriter("CPD3314.json");
        file.print("{\"products\" : [ ");
        for (int i = 0; i < ProductList.size() - 1; i++) {
            String pl = ProductList.get(i).toJSON();
            file.print(pl + ", ");
        }
        String pl1 = ProductList.get(ProductList.size() - 1).toJSON();
        file.print(pl1);
        file.print(" ] }");
        file.close();
    }

    /**
     * yaml() method take two arguments and write into the file with extension
     * dot YML
     *
     * @param ProductList
     * @param filename
     * @throws FileNotFoundException
     */
    public static void yaml(List<Product> ProductList, String... filename) throws FileNotFoundException {
        if (filename.length != 0) {
            PrintWriter file = new PrintWriter(filename[0] + ".yaml");
            for (int i = 0; i < ProductList.size(); i++) {
                file.println("---");
                String pl = ProductList.get(i).toYAML();
                file.println(pl);
            }
            file.println("---");
            file.close();
        }
        PrintWriter file;
        file = new PrintWriter("CPD3314.yaml");
        for (int i = 0; i < ProductList.size(); i++) {
            file.println("---");
            String pl = ProductList.get(i).toYAML();
            file.println(pl);
        }
        file.println("---");
        file.close();
    }

    /**
     * sql() method take two arguments and write into the file with extension
     * dot SQL
     * @param ProductList
     * @param filename
     * @throws FileNotFoundException
     */
    public static void sql(List<Product> ProductList, String... filename) throws FileNotFoundException {
        if (filename.length != 0) {

            PrintWriter file = new PrintWriter(filename[0] + ".sql");
            file.println("CREATE TABLE Products (id INT, name VARCHAR(50), description TEXT, quantity INT, dateAdded DATE);");
            for (int i = 0; i < ProductList.size(); i++) {
                String pl = ProductList.get(i).toSQL();
                file.println(pl);
            }
            file.close();

        }
        PrintWriter file;
        file = new PrintWriter("CPD3314.sql");
        file.println("CREATE TABLE Products (id INT, name VARCHAR(50), description TEXT, quantity INT, dateAdded DATE);");
        for (int i = 0; i < ProductList.size(); i++) {
            String pl = ProductList.get(i).toSQL();
            file.println(pl);
        }
        file.close();
    }

    /**
     *
     * @param ProductList
     * @param file
     * @param record
     * @throws FileNotFoundException
     */
    public static void limitrecords(List<Product> ProductList, String file, int record) throws FileNotFoundException {
        PrintWriter newfile;
        newfile = new PrintWriter(file + ".xml");
        newfile.println("<products>");
        for (int i = 0; i < record; i++) {
            String pl = ProductList.get(i).toXML();
            newfile.println(pl);
        }
        newfile.println("</products>");
        newfile.close();
    }

    /**
     *
     * @param ProductList
     * @param file
     * @param record
     * @throws FileNotFoundException
     */
    public static void selectedrecord(List<Product> ProductList, String file, int record) throws FileNotFoundException {
        PrintWriter newfile;
        newfile = new PrintWriter(file + ".xml");
        newfile.println("<products>");
        for (int i = 0; i < ProductList.size(); i++) {
            if (ProductList.get(i).getId() == record) {
                String pl = ProductList.get(i).toXML();
                newfile.println(pl);
            }
        }
        newfile.println("</products>");
        newfile.close();
    }

    /**
     *
     * @param ProductList
     * @param file
     * @param date
     * @throws FileNotFoundException
     */
    public static void selectedrecordondate(List<Product> ProductList, String file, String date) throws FileNotFoundException {
        PrintWriter newfile;
        newfile = new PrintWriter(file + ".xml");
        newfile.println("<products>");
        for (int i = 0; i < ProductList.size(); i++) {
            if (ProductList.get(i).getDateAdded().equals(date)) {
                String pl = ProductList.get(i).toXML();
                newfile.println(pl);
            }
        }
        newfile.println("</products>");
        newfile.close();
    }

    /**
     *
     * @param ProductList
     * @param file
     * @param find
     * @throws FileNotFoundException
     */
    public static void selectedrecordondata(List<Product> ProductList, String file, String find) throws FileNotFoundException {
        PrintWriter newfile;
        newfile = new PrintWriter(file + ".xml");
        newfile.println("<products>");
        for (int i = 0; i < ProductList.size(); i++) {
            if (ProductList.get(i).getDescription().contains(find.toLowerCase()) || ProductList.get(i).getName().contains(find.toLowerCase())) {
                String pl = ProductList.get(i).toXML();
                newfile.println(pl);
            }
        }
        newfile.println("</products>");
        newfile.close();
    }

    /**
     *
     * @param ProductList
     * @param file
     * @param record
     * @throws FileNotFoundException
     */
    public static void sortbyname(List<Product> ProductList, String file, int record) throws FileNotFoundException {
        PrintWriter newfile;
        newfile = new PrintWriter(file + ".xml");
        newfile.println("<products>");
        for (int i = 0; i < record; i++) {
            String pl = ProductList.get(i).toXML();
            newfile.println(pl);
        }
        newfile.println("</products>");
        newfile.close();
    }

    /**
     *
     * @param ProductList
     * @param file
     * @param record
     * @throws FileNotFoundException
     */
    public static void sortbyid(List<Product> ProductList, String file, int record) throws FileNotFoundException {
        PrintWriter newfile;
        newfile = new PrintWriter(file + ".xml");
        newfile.println("<products>");
        for (int i = 0; i < record; i++) {
            String pl = ProductList.get(i).toXML();
            newfile.println(pl);
        }
        newfile.println("</products>");
        newfile.close();
    }

    /**
     *
     * @param ProductList
     * @param file
     * @param record
     * @throws FileNotFoundException
     */
    public static void sortbydate(List<Product> ProductList, String file, int record) throws FileNotFoundException {
        PrintWriter newfile;
        newfile = new PrintWriter(file + ".xml");
        newfile.println("<products>");
        for (int i = 0; i < record; i++) {
            String pl = ProductList.get(i).toXML();
            newfile.println(pl);
        }
        newfile.println("</products>");
        newfile.close();
    }

}

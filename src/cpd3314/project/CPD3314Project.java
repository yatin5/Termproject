package cpd3314.project;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
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
     */
    public static void main(String[] args) throws FileNotFoundException, Exception {
        
        Serializer serializer= new Persister();
        File newfile= new File("ORIGINALS.xml");
        Products product= serializer.read(Products.class, newfile);
        List<Product> ProductList=  product.getProductList();
        
        int length=args.length;
        if(length==1)
        {
            String command=args[0];
            
            String splitcommand[]=command.split("=");
           
            if(splitcommand[0].equals("-format"))
            {
                String formatto=splitcommand[1];
                switch(formatto)
                {
                    case "XML" :
                        xml(ProductList);
                       // Product p=new Product();
                        //p.readxml();
                        /* function to convert to xml */
                        break;
                    case "YAML" :
                        /*function to convert to yaml */
                        break;
                    case "JSON" :
                        /* function to convert to JSON */
                        break;
                    case "SQL" :
                        sql(ProductList);
                        /* function to convert to SQL */
                        break;
                    case "HTML" :
                        html(ProductList);
                        /* function to convert to html */
                    default: 
                        /* nothing happens command needs to be reentered */
                        
                            
                }
            }
            else if(splitcommand[0].equals("-o"))
            {
               String filename=splitcommand[1];
               /* function which creates a file name filename with all xml data in it without any sorting */
               
            }
            
            
        }
        else if(length==2)
        {
            String command=args[0];
            String splitcommand[]=command.split("=");
            if(splitcommand[0].equals("-o"))
            {
                String filename=splitcommand[1];
                String limitcommand=args[1];
                String splitlimit[]=limitcommand.split("=");
                String numberofrecords=splitlimit[1];
                int records=Integer.parseInt(numberofrecords);
                limitrecords(ProductList, filename, records);
                /* a function which accepts the filename and records as arguments and create a file with 
               records in it named filename*/
                
                
            }
            else if(splitcommand[0].equals("-getID"))
                    {
                        //System.out.println("dsfsdffdsfsfsd");
                         String recordvalue=splitcommand[1];
                         int record=Integer.parseInt(recordvalue);
                String filename=args[1];
                String splitfilename[]=filename.split("=");
                String filetobecreated=splitfilename[1];
                selectedrecord(ProductList, filetobecreated, record);
                /* a function which accets the record int value and file name and create a file with only that record init */
                
                
                        
                
                     }
            else if(splitcommand[0].equals("-getDate"))
            {
                String datestring=splitcommand[1];
                //DateFormat format = new SimpleDateFormat("yyyy-mmmm-d", Locale.ENGLISH);
                //Date dateofrecord=format.parse(datestring);
                String filename=args[1];
                String splitfilename[]=filename.split("=");
                String filetobecreated=splitfilename[1];
                selectedrecordondate(ProductList, filetobecreated, datestring);
                
               
            }
            else if(splitcommand[0].equals("-find"))
            {
                String find=splitcommand[1];
                find=find.replace("","");
                String filename=args[1];
                String splitfilename[]=filename.split("=");
                String filetobecreated=splitfilename[1];
                selectedrecordondata(ProductList, filetobecreated, find);
                /* function which eccept a string find and find it in the discription or name and make a new file named file*/
                
            }
        }
        
        else if(length==3)
        {
            String command=args[0];
            String splitcommand[]=command.split("=");
            if(splitcommand[1].equals("A"))
            {
                String arg1=args[1];
                //String limitcommand=args[1];
                String splitcommandargs[]=arg1.split("=");
                //String splitlimit[]=limitcommand.split("=");
                String filename=splitcommandargs[1];
                 String arg2=args[2];
                //String limitcommand=args[1];
                String splitcommandargs2[]=arg2.split("=");
                //String splitlimit[]=limitcommand.split("=");
                String numberofrecords=splitcommandargs2[1];
                int records=Integer.parseInt(numberofrecords);
               
                Collections.sort(ProductList, Product.getByName());
                sortbyname(ProductList, filename, records);
                /* a function which accepts the filename and records as arguments and create a file with 
               records in it named filename*/
                
                
            }
        }

    }
    
    public static void xml(List<Product>ProductList) throws FileNotFoundException
    {
        PrintWriter file;
        file = new PrintWriter("CPD3314.XML");
        file.println("<products>");
        for(int i=0;i<ProductList.size();i++)
        {
            String pl=ProductList.get(i).toXML();
            file.println(pl);
        }
        file.println("</products>");
        file.close();
    }
    
    public static void html(List<Product>ProductList) throws FileNotFoundException
    {
        PrintWriter file;
        file = new PrintWriter("CPD3314.html");
        for(int i=0;i<ProductList.size();i++)
        {
            String pl=ProductList.get(i).toHTML();
            file.println(pl);
        }
        file.close();
    }
    public static void json(List<Product>ProductList) throws FileNotFoundException
    {
        PrintWriter file;
        file = new PrintWriter("CPD3314.json");
        for(int i=0;i<ProductList.size();i++)
        {
            String pl=ProductList.get(i).toJSON();
            file.println(pl);
        }
        file.close();
    }
    public static void yaml(List<Product>ProductList) throws FileNotFoundException
    {
        PrintWriter file;
        file = new PrintWriter("CPD3314.yaml");
        for(int i=0;i<ProductList.size();i++)
        {
            String pl=ProductList.get(i).toYAML();
            file.println(pl);
        }
        file.close();
    }
    public static void sql(List<Product>ProductList) throws FileNotFoundException
    {
        PrintWriter file;
        file = new PrintWriter("CPD3314.sql");
        for(int i=0;i<ProductList.size();i++)
        {
            String pl=ProductList.get(i).toSQL();
            file.println(pl);
        }
        file.close();
    }
    public static void limitedrecords(List<Product> ProductList, String file, int record) throws FileNotFoundException
    {
         PrintWriter newfile;
        newfile = new PrintWriter(file+".xml");
        newfile.println("<products>");
        for(int i=0;i<record;i++)
        {
            String pl=ProductList.get(i).toXML();
            newfile.println(pl);
        }
        newfile.println("</products>");
        newfile.close();
    }
    public static void selectedrecord(List<Product> ProductList, String file, int record) throws FileNotFoundException
    {
         PrintWriter newfile;
        newfile = new PrintWriter(file+".xml");
        newfile.println("<products>");
        for(int i=0;i<ProductList.size();i++)
        {
            if(ProductList.get(i).getid()==record)
            {
                String pl=ProductList.get(i).toXML();
                newfile.println(pl);
                break;
            }
        }
        newfile.println("</products>");
        newfile.close();
    }
    public static void selectedrecordondate(List<Product> ProductList, String file, String date) throws FileNotFoundException
    {
         PrintWriter newfile;
        newfile = new PrintWriter(file+".xml");
        newfile.println("<products>");
        for(int i=0;i<ProductList.size();i++)
        {
            if(ProductList.get(i).getdateAdded().equals(date))
            {
                String pl=ProductList.get(i).toXML();
                newfile.println(pl);
                break;
            }
        }
        newfile.println("</products>");
        newfile.close();
    }
    public static void selectedrecordondata(List<Product> ProductList, String file, String find) throws FileNotFoundException
    {
         PrintWriter newfile;
        newfile = new PrintWriter(file+".xml");
        newfile.println("<products>");
        for(int i=0;i<ProductList.size();i++)
        {
            if(ProductList.get(i).getdescription().toLowerCase().indexOf(find.toLowerCase())!=-1 || ProductList.get(i).getname().toLowerCase().indexOf(find.toLowerCase())!=-1 )
            {
                String pl=ProductList.get(i).toXML();
                newfile.println(pl);
                break;
            }
        }
        newfile.println("</products>");
        newfile.close();
    }
     public static void sortbyname(List<Product> ProductList, String file, int record) throws FileNotFoundException
    {
         PrintWriter newfile;
        newfile = new PrintWriter(file+".xml");
        newfile.println("<products>");
        for(int i=0;i<record;i++)
        {
                String pl=ProductList.get(i).toXML();
                newfile.println(pl);
                break;
        }
        newfile.println("</products>");
        newfile.close();
    }

}

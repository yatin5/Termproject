package cpd3314.project;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONValue;
import org.simpleframework.xml.Element;
import org.yaml.snakeyaml.Yaml;


/**
 * 
 * @author YATIN PATEL
 */
public class Product {
    @Element
    private int id;
    
    @Element
    private String name;
    
    @Element
    private String description;

    @Element
    private int Quantity;
    
    @Element
    private String dateAdded;
    
    
    public Product(int id, String name, String description, int Quantity, String dateAdded)
    {
        this.id= id;
        this.name= name;
        this.description= description;
        this.Quantity= Quantity;
        this.dateAdded= dateAdded;
    }
    public Product()
    {}
    
    public int getid()
    {
        return id;
    }
    public String getname()
    {
        return name;
    }
    public String getdescription()
    {
        return description;
    }
    public int getQuantity()
    {
        return Quantity;
    }
    public String getdateAdded()
    {
        return dateAdded;
    }
    public void setid(int id)
    {
        this.id = id;
    }
    public void setname(String name)
    {
        this.name=name;
    }
    public void setdescription(String description)
    {
        this.description= description;
    }
    public void setquantitity(int quatity)
    {
        this.Quantity=quatity;
    }
    public void setdateAdded(String dateAdded)
    {
        this.dateAdded=dateAdded;
    }
    
    public String toXML()
    {
        String xml="<product>\n" 
                    + "<id>" +getid()+"</id>\n" +"<name>"+getname()+"</nmae>\n"+"<description>"+getdescription()+"</description>\n"+"<Quantity>"+getQuantity()+"</Quantity>\n"+"<dateAdded>"+getdateAdded()+"</dateAdded>\n"
                   +"</product>";
        return xml;        
    }
    public String toSQL()
    {
      String sql="INSERT INTO Products VALUES("+getid()+", \""+getname()+", \""+getdescription()+", \""+getQuantity()+", \""+getdateAdded()+"\");";
      return sql;
    }
    public String toHTML()
    {
        String html= "<div class=\"product\">\n"
                    +"<h1>"+getname()+"</h1>\n"
                    +"<p>"+getid()+"</p>\n"
                    +"<p>"+getdescription()+"</p>\n"
                    +"<p>"+getQuantity()+"</p>\n"
                    +"<p>"+getdateAdded()+"</p>\n"
                    +"</div>";
        return html;
    }
    public String toJSON()
    {
        Map jobj = (Map) new LinkedHasMap();
            jobj.put("Quantity", getQuantity());
            jobj.put("name", getname());
            jobj.put("description", getdescription());
            jobj.put("id", getid());
            jobj.put("dateAdded", getdateAdded());
        
            String json=JSONValue.toJSONString(jobj);
         return json;
    }
    public String toYAML() {
        Map<String, Object> yamlmap=new HashMap<String, Object>();
            yamlmap.put("dateAdded", getdateAdded());
            yamlmap.put("description", getdescription());
            yamlmap.put("id", getid());
            yamlmap.put("name", getname());
            yamlmap.put("quantity", getQuantity());
            
        Yaml obj= new Yaml();
        
     String yaml=obj.dump(yamlmap);
     return yaml;
    }
    
private static Comparator<Product> ZToAByName() 
{
 Comparator comp = new Comparator<Product>()
 {
@Override
public int compare(Product s1, Product s2)
{
 return (s2.getname().compareTo(s1.getname()));
}
 }; 
    return comp;
}
}

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
    
    public int getId()
    {
        return id;
    }
    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }
    public int getQuantity()
    {
        return Quantity;
    }
    public String getDateAdded()
    {
        return dateAdded;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setDescription(String description)
    {
        this.description= description;
    }
    public void setQuantity(int quatity)
    {
        this.Quantity=quatity;
    }
    public void setDateAdded(String dateAdded)
    {
        this.dateAdded=dateAdded;
    }
    
    public String toXML()
    {
        String xml="<product>\n" 
                    + "<id>" +getId()+"</id>\n" +"<name>"+getName()+"</nmae>\n"+"<description>"+getDescription()+"</description>\n"+"<Quantity>"+getQuantity()+"</Quantity>\n"+"<dateAdded>"+getDateAdded()+"</dateAdded>\n"
                   +"</product>";
        return xml;        
    }
    public String toSQL()
    {
      String sql="INSERT INTO Products VALUES("+getId()+", \""+getName()+", \""+getDescription()+", \""+getQuantity()+", \""+getDateAdded()+"\");";
      return sql;
    }
    public String toHTML()
    {
        String html= "<div class=\"product\">\n"
                    +"<h1>"+getName()+"</h1>\n"
                    +"<p>"+getId()+"</p>\n"
                    +"<p>"+getDescription()+"</p>\n"
                    +"<p>"+getQuantity()+"</p>\n"
                    +"<p>"+getDateAdded()+"</p>\n"
                    +"</div>";
        return html;
    }
    public String toJSON()
    {
        Map jobj = (Map) new LinkedHasMap();
            jobj.put("Quantity", getQuantity());
            jobj.put("name", getName());
            jobj.put("description", getDescription());
            jobj.put("id", getId());
            jobj.put("dateAdded", getDateAdded());
        
            String json=JSONValue.toJSONString(jobj);
         return json;
    }
    public String toYAML() {
        Map<String, Object> yamlmap=new HashMap<String, Object>();
            yamlmap.put("dateAdded", getDateAdded());
            yamlmap.put("description", getDescription());
            yamlmap.put("id", getId());
            yamlmap.put("name", getName());
            yamlmap.put("quantity", getQuantity());
            
        Yaml obj= new Yaml();
        
     String yaml=obj.dump(yamlmap);
     return yaml;
    }
    
static Comparator<Product> getByName() 
{
 Comparator comp = new Comparator<Product>()
 {
@Override
public int compare(Product s1, Product s2)
{
 return (s2.getName().compareTo(s1.getName()));
}
 }; 
    return comp;
}

    private static class LinkedHasMap {

        public LinkedHasMap() {
        }
    }
}

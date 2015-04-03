package cpd3314.project;

import org.simpleframework.xml.Element;

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
    private int quantity;
    
    @Element
    private String dateadded;
    
    
    public Product(int id, String name, String description, int quantity, String dateadded)
    {
        this.id= id;
        this.name= name;
        this.description= description;
        this.quantity= quantity;
        this.dateadded= dateadded;
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
    public int getquantity()
    {
        return quantity;
    }
    public String getdateadded()
    {
        return dateadded;
    }
    public void setid(int id)
    {
        this.id = id;
    }
}

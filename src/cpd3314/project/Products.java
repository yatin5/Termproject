/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cpd3314.project;

import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author YATIN PATEL
 */
@Root
class Products {
    @ElementList(inline=true, entry="product")
    private List<Product> productlist;
    
    public void setProductlist(List<Product> productlist)
    {
        this.productlist = productlist;
    }
    public List getProductList()
    {
        return this.productlist;
    }
    
}

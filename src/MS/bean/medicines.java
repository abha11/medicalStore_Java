 //id, m_name , m_company , m_category, quantity int, price double
package MS.bean;

public class medicines {
    private String id;
    private String medName;
    private String medCompany;
    private String medCategory;
    private int quantity;
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedCompany() {
        return medCompany;
    }

    public void setMedCompany(String medCompany) {
        this.medCompany = medCompany;
    }

    public String getmedCategory() {
        return medCategory;
    }

    public void setmedCategory(String imedCategory) {
        this.medCategory = imedCategory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
}

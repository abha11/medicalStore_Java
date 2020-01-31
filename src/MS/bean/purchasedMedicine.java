package MS.bean;

//s_no ,m_id, med_name , med_category, quant_purchased int, price_unit double, price_total double
public class purchasedMedicine {
    private String sNo;
    private String mId;
    private String MedName;
    private String MedCategory;
    private int quantPurchased;
    private double pricePerUnit;
    private double totalPrice;
    
    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getMedName() {
        return MedName;
    }

    public void setMedName(String MedName) {
        this.MedName = MedName;
    }

    public String getMedCategory() {
        return MedCategory;
    }

    public void setMedCategory(String MedCategory) {
        this.MedCategory = MedCategory;
    }

   

    public int getQuantPurchased() {
        return quantPurchased;
    }

    public void setQuantPurchased(int quantPurchased) {
        this.quantPurchased = quantPurchased;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
    
}

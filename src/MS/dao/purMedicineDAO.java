
package MS.dao;

import ECUtils.BaseDAO;
import static ECUtils.BaseDAO.closeCon;
import static ECUtils.BaseDAO.getCon;
import static ECUtils.ECConst.DB_HOST;
import static ECUtils.ECConst.DB_NAME;
import static ECUtils.ECConst.DB_PASS;
import static ECUtils.ECConst.DB_USER;
import ECUtils.MyUtils;
import MS.bean.medicines;
import MS.bean.purchasedMedicine;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class purMedicineDAO extends BaseDAO{
    public static void insert(purchasedMedicine p1){
        Connection con = null;
        try {
            con = getCon();
            String sql = "insert into purchased_medicine (m_id, med_name ,med_category ,quant_purchased, price_unit, price_total) values (?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            int i = 1;
            st.setString(i++, p1.getmId());
            st.setString(i++, p1.getMedName());
            st.setString(i++, p1.getMedCategory());
            st.setInt(i++, p1.getQuantPurchased());
            st.setDouble(i++, p1.getPricePerUnit());
            st.setDouble(i++, p1.getTotalPrice());
            st.executeUpdate();
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            closeCon(con);
        }
    }
    
     public static purchasedMedicine searchByIdForSale(String id){
       purchasedMedicine res = new purchasedMedicine();
               
       Connection con = null;
       try {
           con = getCon();
           String sql = "select * from medicines where id = ?" ;
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, id);
           ResultSet rs = st.executeQuery();
           if(rs.next()){
               purchasedMedicine m1 = new purchasedMedicine();
               m1.setmId(rs.getString("id"));
               m1.setMedName(rs.getString("m_name"));
               m1.setMedCategory(rs.getString("m_category"));
               m1.setPricePerUnit(rs.getDouble("price"));
               res = m1;         
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       finally{
           closeCon(con);
       }
       return res;
   }
    
    public static void delete(String id, int q, String mId){
        Connection con = null;
        try {
            con = getCon();
            String sql = "delete from purchased_medicine where s_no = ?";
            PreparedStatement st = con.prepareStatement(sql);
            int i = 1;
            st.setString(i++, id);
            st.executeUpdate();
            medicines m1 = medicineDAO.searchById(mId);
            m1.setQuantity((q + m1.getQuantity()));
            medicineDAO.update(m1);
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            closeCon(con);
        }
    }
    
    public static void deleteExistingData(){
        Connection con = null;
        try {
            con = getCon();
            String sql = "TRUNCATE TABLE purchased_medicine";
            PreparedStatement st = con.prepareStatement(sql);
            st.executeUpdate();
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            closeCon(con);
        }
    }
    public static LinkedList<purchasedMedicine> search(){
       LinkedList<purchasedMedicine> res = new LinkedList<>();
       Connection con = null;
       try {
           con = getCon();
           String sql = "select * from purchased_medicine" ;
           PreparedStatement st = con.prepareStatement(sql);
           ResultSet rs = st.executeQuery();
           while(rs.next()){
               purchasedMedicine m1 = new purchasedMedicine();
               m1.setsNo(rs.getString("s_no"));
               m1.setmId(rs.getString("m_id"));
               m1.setMedName(rs.getString("med_name"));
               m1.setMedCategory(rs.getString("med_category"));
               m1.setPricePerUnit(MyUtils.getDouble(rs.getString("price_unit")));
               m1.setTotalPrice(MyUtils.getDouble(rs.getString("price_total")));
               m1.setQuantPurchased(MyUtils.getInt(rs.getString("quant_purchased")));
               res.add(m1);           
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       finally{
           closeCon(con);
       }
       return res;
   }
    
     public static double getTotalBill(){
        double d1 = 0.0;
        Connection con =null;
        try {
            con = getCon();
            String sql = "SELECT sum(price_total) as total FROM purchased_medicine ";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
              d1 = rs.getDouble("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            closeCon(con);
        }
        return d1;                
    }
     
}
//id, m_name , m_company , m_category, quantity int, price double

package MS.dao;

import ECUtils.BaseDAO;
import static ECUtils.BaseDAO.getCon;
import MS.bean.medicines;
import MS.bean.myUser;
import MS.bean.purchasedMedicine;
import java.sql.*;
import java.util.*;

public class medicineDAO extends BaseDAO{
     public static void insert(medicines m1){
       Connection con = null;
       try {
           con = getCon();
           String sql = "insert into medicines (m_name , m_company , m_category, quantity , price ) values (?, ?, ?, ?, ?)";
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, m1.getMedName());
           st.setString(i++, m1.getMedCompany());
           st.setString(i++, m1.getmedCategory());
           st.setInt(i++, m1.getQuantity());
           st.setDouble(i++, m1.getPrice());
           st.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
       }
       finally{
           closeCon(con);
       }
   }
    
    public static void delete(String id){
       Connection con = null;
       try {
           con = getCon();
           String sql = "delete from medicines where id = ?";
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, id);
           st.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
       }
       finally{
           closeCon(con);
       }
   }
    public static void update(medicines m1){
       Connection con = null;
       try {
           con = getCon();
           String sql = "update medicines set  m_name = ? , m_company = ? , m_category = ?, quantity = ?, price = ? where id = ? ";
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, m1.getMedName());
           st.setString(i++, m1.getMedCompany());
           st.setString(i++, m1.getmedCategory());
           st.setInt(i++, m1.getQuantity());
           st.setDouble(i++, m1.getPrice());
           st.setString(i++, m1.getId());
           st.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
       }
       finally{
           closeCon(con);
       }
   }
    
     public static LinkedList<medicines> search(String sc , String si){
       LinkedList<medicines> res = new LinkedList<>();
       Connection con = null;
       try {
           con = getCon();
           String sql = "select * from medicines where "+sc+" like ?" ;
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, "%" + si + "%");
           ResultSet rs = st.executeQuery();
           while(rs.next()){
               medicines m1 = new medicines();
               m1.setId(rs.getString("id"));
               m1.setMedName(rs.getString("m_name"));
               m1.setMedCompany(rs.getString("m_company"));
               m1.setmedCategory(rs.getString("m_category"));
               m1.setQuantity(rs.getInt("quantity"));
               m1.setPrice(rs.getDouble("price"));
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
    
     
   public static medicines searchById(String id){
       medicines res = new medicines();
               
       Connection con = null;
       try {
           con = getCon();
           String sql = "select * from medicines where id = ?" ;
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, id);
           ResultSet rs = st.executeQuery();
           if(rs.next()){
               medicines m1 = new medicines();
               m1.setId(rs.getString("id"));
               m1.setMedName(rs.getString("m_name"));
               m1.setMedCompany(rs.getString("m_company"));
               m1.setmedCategory(rs.getString("m_category"));
               m1.setQuantity(rs.getInt("quantity"));
               m1.setPrice(rs.getDouble("price"));
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
   
   
    
   public static LinkedList getCompanyNames(){
       LinkedList res = new LinkedList();
       Connection con = null ;
       try {
           con = getCon();
           String sql = "select com_name from company";
           PreparedStatement st = con.prepareStatement(sql);
           ResultSet rs = st.executeQuery();
           while(rs.next()){
               res.add(rs.getString("com_name"));
           }
           
       } catch (Exception e) {
       }
       finally{
           closeCon(con);
       }
       return res;
   }
}

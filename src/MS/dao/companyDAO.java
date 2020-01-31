package MS.dao;

import ECUtils.BaseDAO;
import static ECUtils.BaseDAO.closeCon;
import static ECUtils.BaseDAO.getCon;
import MS.bean.Company;
import MS.bean.medicines;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class companyDAO extends BaseDAO{
     public static void insert(Company c1){
       Connection con = null;
       try {
           con = getCon();
           String sql = "insert into company (com_name, com_country, com_email, com_addr, com_phone) values (?, ?, ?, ?, ?)";
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, c1.getCompanyName());
           st.setString(i++, c1.getCompanyCountry());
           st.setString(i++, c1.getCompanyEmail());
           st.setString(i++, c1.getCompanyAddress());
           st.setString(i++, c1.getCompanyPhone());
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
           String sql = "delete from company where id = ?";
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
     public static void update(Company c1){
       Connection con = null;
       try {
           con = getCon();
           String sql = "update company set com_name = ?, com_country = ?, com_email = ?, com_addr = ?, com_phone = ? where id = ? ";
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, c1.getCompanyName());
           st.setString(i++, c1.getCompanyCountry());
           st.setString(i++, c1.getCompanyEmail());
           st.setString(i++, c1.getCompanyAddress());
           st.setString(i++, c1.getCompanyPhone());
           st.setString(i++, c1.getId());
           st.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
       }
       finally{
           closeCon(con);
       }
   }
    
     public static LinkedList<Company> search(String sc , String si){
       LinkedList<Company> res = new LinkedList<>();
       Connection con = null;
       try {
           con = getCon();
           String sql = "select * from company where "+sc+" like ?" ;
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, "%" + si + "%");
           ResultSet rs = st.executeQuery();
           while(rs.next()){
               Company c1 = new Company();
               c1.setId(rs.getString("id"));
               c1.setCompanyName(rs.getString("com_name"));
               c1.setCompanyCountry(rs.getString("com_country"));
               c1.setCompanyEmail(rs.getString("com_email"));
               c1.setCompanyAddress(rs.getString("com_addr"));
               c1.setCompanyPhone(rs.getString("com_phone"));
               res.add(c1);           
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       finally{
           closeCon(con);
       }
       return res;
   }
    
   public static Company searchById(String id){
       Company res = new Company();
               
       Connection con = null;
       try {
           con = getCon();
           String sql = "select * from company where id = ?" ;
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, id);
           ResultSet rs = st.executeQuery();
           if(rs.next()){
              Company c1 = new Company();
               c1.setId(rs.getString("id"));
               c1.setCompanyName(rs.getString("com_name"));
               c1.setCompanyCountry(rs.getString("com_country"));
               c1.setCompanyEmail(rs.getString("com_email"));
               c1.setCompanyAddress(rs.getString("com_addr"));
               c1.setCompanyPhone(rs.getString("com_phone"));
               res = c1;         
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       finally{
           closeCon(con);
       }
       return res;
   }
    
}

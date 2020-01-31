
package MS.dao;

import ECUtils.BaseDAO;
import static ECUtils.BaseDAO.closeCon;
import static ECUtils.BaseDAO.getCon;
import MS.bean.medicines;
import MS.bean.myCustomer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

public class customerDAO extends BaseDAO{
    public static int insert(myCustomer m1){
       Connection con = null;
       ResultSet rs = null;
       PreparedStatement st = null;
       int pKey = 0;
       try {
           //id, c_name, phone_no,S_date datetime, total_bill double

           con = getCon();
           String sql = "insert into customer ( c_name, phone_no, S_date, total_bill ) values (?, ?, ?, ?)";
           st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
           int i = 1;
           st.setString(i++, m1.getCustomerName());
           st.setString(i++, m1.getPhoneNumber());
           st.setDate(i++, m1.getPurchaseDate());
           st.setDouble(i++, m1.getTotalBill());
           st.executeUpdate();
           rs = st.getGeneratedKeys();
           if(rs != null && rs.next()){
               pKey = rs.getInt(1);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       finally{
           closeCon(con);
       }
       return pKey;
   }
    
    public static LinkedList<myCustomer> search(String sc , String si){
       LinkedList<myCustomer> res = new LinkedList<>();
       Connection con = null;
       try {
           con = getCon();
           String sql = "select * from customer where "+sc+" like ?" ;
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, "%" + si + "%");
           ResultSet rs = st.executeQuery();
           //id, c_name, phone_no,S_date datetime, total_bill double
           while(rs.next()){
               myCustomer m1 = new myCustomer();
               m1.setId(rs.getString("id"));
               m1.setCustomerName(rs.getString("c_name"));
               m1.setPhoneNumber(rs.getString("phone_no"));
               m1.setPurchaseDate(rs.getDate("S_date"));
               m1.setTotalBill(rs.getDouble("total_bill"));
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
   
     public static myCustomer searchById(String id){
       myCustomer res = new myCustomer();
       //id, c_name, phone_no,S_date datetime, total_bill double

       Connection con = null;
       try {
           con = getCon();
           String sql = "select * from customer where id = ?" ;
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, id);
           ResultSet rs = st.executeQuery();
           if(rs.next()){
               myCustomer m1 = new myCustomer();
               m1.setId(rs.getString("id"));
               m1.setCustomerName(rs.getString("c_name"));
               m1.setPhoneNumber(rs.getString("phone_no"));
               m1.setPurchaseDate(rs.getDate("S_date"));
               m1.setTotalBill(rs.getDouble("total_bill"));
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
}

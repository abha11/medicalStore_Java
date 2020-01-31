package MS.dao;

//id, user_name , pass , phone_no , u_category

import ECUtils.BaseDAO;
import static ECUtils.BaseDAO.closeCon;
import static ECUtils.BaseDAO.getCon;
import MS.bean.medicines;
import MS.bean.myUser;
import java.sql.*;
import java.util.LinkedList;

public class MyuserDAO extends BaseDAO{
    
    public static myUser validate_login(String uname, String passw){
        myUser res = null;
        Connection con = null;
        try {
            con = getCon();
            String sql = "select * from app_users where user_name = ? and pass = ?";
            PreparedStatement st = con.prepareStatement(sql);
            int i = 1;
            st.setString(i++, uname);
            st.setString(i++, passw);
           ResultSet rs =  st.executeQuery();
           while(rs.next()){
               myUser m1 = new myUser();
               m1.setId(rs.getString("id"));
               m1.setUserName(rs.getString("user_name"));
               m1.setPassWord(rs.getString("pass"));
               m1.setPhoneNo(rs.getString("phone_no"));
               m1.setUserCategory(rs.getString("u_category"));
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
    
     public static void insert(myUser m1){
       Connection con = null;
       try {
           con = getCon();
           String sql = "insert into app_users (user_name , pass , phone_no , u_category) values (?, ?, ?, ?)";
           PreparedStatement st = con.prepareStatement(sql);
           //id, user_name , pass , phone_no , u_category

           int i = 1;
           st.setString(i++, m1.getUserName());
           st.setString(i++, m1.getPassWord());
           st.setString(i++, m1.getPhoneNo());
           st.setString(i++, m1.getUserCategory());
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
           String sql = "delete from app_users where id = ?";
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
    public static void update(myUser m1){
       Connection con = null;
       try {
           con = getCon();
           String sql = "update app_users set user_name = ?, pass = ? , phone_no = ?, u_category = ? where id = ? ";
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, m1.getUserName());
           st.setString(i++, m1.getPassWord());
           st.setString(i++, m1.getPhoneNo());
           st.setString(i++, m1.getUserCategory());
           st.setString(i++, m1.getId());
           st.executeUpdate();
       } catch (Exception e) {
           e.printStackTrace();
       }
       finally{
           closeCon(con);
       }
   }
    
     public static LinkedList<myUser> search(String sc , String si){
       LinkedList<myUser> res = new LinkedList<>();
       Connection con = null;
       try {
           con = getCon();
           String sql = "select * from app_users where "+sc+" like ?" ;
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, "%" + si + "%");
           ResultSet rs = st.executeQuery();
           while(rs.next()){
               myUser m1 = new myUser();
               m1.setId(rs.getString("id"));
               m1.setUserName(rs.getString("user_name"));
               m1.setPassWord(rs.getString("pass"));
               m1.setPhoneNo(rs.getString("phone_no"));
               m1.setUserCategory(rs.getString("u_category"));
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
     
     public static myUser searchById(String id){
       myUser res = new myUser();
               
       Connection con = null;
       try {
           con = getCon();
           String sql = "select * from app_users where id = ?" ;
           PreparedStatement st = con.prepareStatement(sql);
           int i = 1;
           st.setString(i++, id);
           ResultSet rs = st.executeQuery();
           if(rs.next()){
               myUser m1 = new myUser();
               m1.setId(rs.getString("id"));
               m1.setUserName(rs.getString("user_name"));
               m1.setPassWord(rs.getString("pass"));
               m1.setPhoneNo(rs.getString("phone_no"));
               m1.setUserCategory(rs.getString("u_category"));
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

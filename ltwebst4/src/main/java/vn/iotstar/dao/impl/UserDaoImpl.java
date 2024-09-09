package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.DBConnectMySQL;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao{
	public Connection conn=null;
	public PreparedStatement ps=null;
	public ResultSet rs=null;
	@Override
	public List<UserModel> findAll() {
		String sql ="select * from users";
		
		List<UserModel> list = new ArrayList<>();
		try {
			conn = super.getDatabaseConnection();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while (rs.next() /*Next từng DÒNG tới cuối cùng */) {
				list.add(
						new UserModel(
								rs.getInt("id"), 
								rs.getString("username"), 
								rs.getString("email"),
								rs.getString("password"), 
								rs.getString("fullname"),
								rs.getString("images")));
				
			}
			return list;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO users (id, username, password, email,  images, fullname) VALUES (?, ?, ?, ?, ?, ?)"; 
		try {
			conn = super.getDatabaseConnection(); //kết nối database
			ps = conn.prepareStatement(sql);//ném câu sql vào cho thực thi
			ps.setInt(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getEmail()); 
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getImages()); 
			ps.setString(6, user.getFullname());
			
			ps.executeUpdate();
			} catch (Exception e) 
		{
				e.printStackTrace();
			}
			
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		
		UserDaoImpl userDao=new UserDaoImpl();
		//System.out.println.(userDao.findOne(1));
	//	List<UserModel> list = userDao.findAll();		
		userDao.insert(new UserModel(2,"abc","abc@gmail.com", "123","","abcdef"));
		List<UserModel> list = userDao.findAll();
		for (UserModel user:list) {
			System.out.println(user);
		}
	}

}

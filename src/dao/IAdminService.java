package dao;
import entity.Admin;
import exception.AdminNotFoundException;
public interface IAdminService {

	Admin getAdminById(int adminId) throws AdminNotFoundException;

	Admin getAdminByUsername(String username) throws AdminNotFoundException;

	boolean registerAdmin(Admin admin);

	//boolean updateAdmin(String adminData);

	boolean deleteAdmin(int adminId);
}

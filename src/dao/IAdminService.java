package dao;
import entity.Admin;

public interface IAdminService {

	Admin getAdminById(int adminId);

	Admin getAdminByUsername(String username);

	boolean registerAdmin(Admin admin);

	Admin updateAdmin(String adminData);

	boolean deleteAdmin(int adminId);
}

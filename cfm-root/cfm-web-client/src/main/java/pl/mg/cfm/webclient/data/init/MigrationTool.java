package pl.mg.cfm.webclient.data.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.mg.cfm.webclient.data.repository.EmployeeRoleRepository;

/**
 * Tool initializing database and insert sample data.
 * 
 * @author Maciej Gzik
 *
 */
public class MigrationTool {

	@Autowired
	private EmployeeRoleRepository roleRepository;

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContextPersistence.xml",
				"applicationContext.xml");
		MigrationTool tool = new MigrationTool();
		tool.roleRepository = context.getBean(EmployeeRoleRepository.class);

		tool.insertData();
	}

	private void createTables() {
		createIndexTable();
		createEmployeeTable();
		createCarTable();
	}

	private void insertData() {
		insertRolesData();
	}

	private void createIndexTable() {
		// TODO Auto-generated method stub
	}

	private void createEmployeeTable() {

	}

	private void createCarTable() {
		// TODO Auto-generated method stub
	}

	private void insertRolesData() {
		this.roleRepository.addRole(1,"ADMIN");
	}

}

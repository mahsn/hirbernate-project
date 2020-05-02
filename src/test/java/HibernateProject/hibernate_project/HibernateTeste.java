package HibernateProject.hibernate_project;

import java.util.List;

import org.junit.Test;

import ProjectDao.StudentDao;
import model.Students;
import model.TelephoneUser;

public class HibernateTeste {

	@Test
	public void testHibertaneUtil() {
		Students students = new Students();

		StudentDao<Students> studentDao = new StudentDao<Students>();
		// students.setId(2L);
		students.setIdentity(212121);
		students.setStudentName("Mahsn");
		students.setStudentSurname("Texeira");
		students.setStudentEmail("alex@texeira.com");
		students.setStudentLogin("atextexeira");
		students.setStudentPassword("11222");
		studentDao.save(students);
	}

	@Test
	public void testSearch() {
		StudentDao<Students> studentDao = new StudentDao<>();
		Students students = studentDao.search(4L, Students.class);
		System.out.println(students.toString());
	}

	@Test
	public void testUpdate() {
		StudentDao<Students> studentDao = new StudentDao<>();
		Students students = studentDao.search(2L, Students.class);
		students.setIdentity(1211111);
		students.setStudentName("Mehmet");
		students.setStudentSurname("Updated");
		students.setStudentEmail("mehmet@gmail.com");
		studentDao.updateMerge(students);
	}

	@Test
	public void testDelete() {
		StudentDao<Students> studentDao = new StudentDao<>();
		Students students = studentDao.search(2L, Students.class);
		studentDao.deleteById(students);
	}

	@Test
	public void testConsult() {
		StudentDao<Students> studentDao = new StudentDao<>();
		List<Students> lStudents = studentDao.listData(Students.class);
		for (Students students : lStudents) {
			System.out.println(students);
			System.out.println("-------------------");
		}
	}
	
	@Test
	public void testQueryList() {
		StudentDao<Students> studentDao = new StudentDao<Students>();
		List<Students> list = studentDao.getEntityManager()
				.createQuery(" from Students order by name")
				.setMaxResults(3)
				.getResultList();
		
		for (Students students : list) {
			System.out.println(students);
		}
	}
	
	@Test
	public void testQueryListParameter() {
		StudentDao<Students> studentDao = new StudentDao<Students>();
		List<Students> list = studentDao.getEntityManager()
				.createQuery(" from Students where studentName = :studentName")
				.setParameter("studentName", "Mahsn")
				.getResultList();
		
		for (Students students : list) {
			System.out.println(students);
		}
	}
	
	@Test
	public void testQuerySumIdentity() {
		StudentDao<Students> studentDao = new StudentDao<Students>();
		Long sumIdentity = (Long) studentDao.getEntityManager()
				.createQuery("select sum(u.identity) from Students u").getSingleResult();
		System.out.println("Sum of the all identities --> " + sumIdentity);
	}
	
	@Test
	public void testNamedQuery() {
		StudentDao<Students> studentDao = new StudentDao<Students>();
		List<Students> list = studentDao.getEntityManager()
				.createNamedQuery("Students.findAll")
				.getResultList();
		for (Students students : list) {
			System.out.println(students);
		}
		
	}
	
	@Test
	public void testSearchNameByNamedQuery() {
		StudentDao<Students> studentDao = new StudentDao<Students>();
		List<Students> list = studentDao.getEntityManager()
				.createNamedQuery("Students.searchByName")
				.setParameter("studentName", "Mahsn")
				.getResultList();
		
		for (Students students : list) {
			System.out.println(students);
		}
		
	}
	
	@Test
	public void testSaveTelephone() {
		StudentDao studentDao = new StudentDao();
		
		Students students = (Students)studentDao.search(4L, Students.class);
		TelephoneUser telephoneUser = new TelephoneUser();
		telephoneUser.setTelephoneType("mobile phone");
		telephoneUser.setTelephoneNumber("111133455");
		telephoneUser.setStudents(students);
		studentDao.save(telephoneUser);
		
	}
	
	@Test
	public void testConsultTelephone() {
		StudentDao studentDao = new StudentDao();
		
		Students students = (Students)studentDao.search(4L, Students.class);
		for (TelephoneUser telephoneUser : students.getTelephoneUserList()) {
			System.out.println(telephoneUser.getTelephoneType());
			System.out.println(telephoneUser.getTelephoneNumber());
			System.out.println(telephoneUser.getStudents());
		}
		
	}
}

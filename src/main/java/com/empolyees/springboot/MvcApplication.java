package com.empolyees.springboot;

import com.empolyees.springboot.hibernatedemo.dao.AppDAO;
import com.empolyees.springboot.hibernatedemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcApplication.class, args);
	}

	//this method is Bean method so no need to autowired AppDAO
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//createInstructor(appDAO);
			//findByInstructor(appDAO);
			//findByInstructorDetail(appDAO);
			//deleteInstructor(appDAO);
			//deleteInstructorDetail(appDAO);

			//createInstructorWithCourses(appDAO);
			//findInstructorsWithCourses(appDAO);
			//findCoursesForInstructor(appDAO);
			//findInstructorsWithJoinFetch(appDAO);
            //updateInstructor(appDAO);
			//updateCourse(appDAO);
			//deleteInstructor(appDAO);
			//deleteCourse(appDAO);

			//createCourseAndReviews(appDAO);
			//retriveCourseAndReviews(appDAO);
			//deleteCourseAndReviews(appDAO);

		    //createCourseAndStudent(appDAO);
		    //findCourseAndStudents(appDAO);
		    //findStudnetAndCourses(appDAO);
			//updateStudent(appDAO);
			//deleteCourse(appDAO);
			//deleteStudent(appDAO);
			System.out.println("Testing....");
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting Student id : " + theId);
        appDAO.deleteStudnetById(theId);
	}

	private void updateStudent(AppDAO appDAO) {
		int theId =3;
		Student theStudent = appDAO.findStudentAndCourseByStudentId(theId);
		Course tempCourse1 = new Course("Rubik's Club - How to speed Cube");
		Course tempCourse2 = new Course("Game Development");

		theStudent.addCourse(tempCourse1);
		theStudent.addCourse(tempCourse2);

		System.out.println("Updating student : " + theStudent);
		System.out.println("Associated Courses : " + theStudent.getCourses());

		appDAO.update(theStudent);
		System.out.println("Done!");
	}

	private void findStudnetAndCourses(AppDAO appDAO) {
		int theId= 3;
		Student theStudent = appDAO.findStudentAndCourseByStudentId(theId);
		System.out.println ("Student : " + theStudent);
		System.out.println("Courses : " + theStudent.getCourses());
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId = 11;
		Course theCourse = appDAO.findCourseAndStudentByCourseId(theId);
		System.out.println ("Course : " + theCourse);
		System.out.println("Student : " + theCourse.getStudents());
	}

	private void createCourseAndStudent(AppDAO appDAO) {
		 Course tempCourse = new Course("PacMan - How to Score one Million Points");
		 Student tempStudent1 = new Student("John", "Doe", "john@gmail.com");
		 Student tempStudent2 = new Student("Mary", "Doe", "Mary@gmail.com");
		 tempCourse.addStudents(tempStudent1);
		 tempCourse.addStudents(tempStudent2);
		 System.out.println("Saving the course");
		 appDAO.save(tempCourse);
		 System.out.println("Done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;
		System.out.println("Deleting course id :" + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void retriveCourseAndReviews(AppDAO appDAO) {
		int theId =10;
		Course theCourse = appDAO.findCourseAndReviewsByCourseId(theId);
		System.out.println("Course : " + theCourse);
		System.out.println("Reviews : " + theCourse.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		Course course = new Course("PacMan - How to Score one Million Points");
		course.addReview(new Review("Great course .. loved it"));
		course.addReview(new Review("Cool course, job well done."));
		course.addReview(new Review("What a dumb course, you are an idiot"));
		System.out.println("Saving the course");
		appDAO.save(course);
		System.out.println("Done");
	}

	private void deleteCourse(AppDAO appDAO) {
		int theId = 11;
		System.out.println("Deleting course id : " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done !" );
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;
		Course course = appDAO.findCourseById(theId);
		course.setTitle("Enjoy the simple things..");
		appDAO.update(course);
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting instructor id: " + theId );
		appDAO.deleteInstructorById(theId);
		System.out.println("Done! ");
	}
	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;
		Instructor instructor = appDAO.findByInstructorId(theId);
		instructor.setLastName("TESTER");
		appDAO.update(instructor);
		System.out.println("done");
	}

	private void findInstructorsWithJoinFetch(AppDAO appDAO) {
		int theId = 1;
		//find the instructor
		Instructor instructor = appDAO.findInstructorByJoinFetch(theId);
		System.out.println("Instructor :" + instructor);
		System.out.println("Associated Instructor Details for the Instructor :" + instructor.getInstructorDetail());
		System.out.println("Associated courses for the Instructor :" + instructor.getCourses());
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		Instructor instructor = appDAO.findByInstructorId(theId);
		//List<Course> courseList = instructor.getCourses();
		System.out.println("Instructor : " + instructor);
		// find courses for instructor
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);
		//Associate the objects
		instructor.setCourses(courses);
		System.out.println("The associated courses : " + instructor.getCourses());
	}

	private void findInstructorsWithCourses(AppDAO appDAO) {
		int theId = 1;
		Instructor instructor = appDAO.findByInstructorId(theId);
		//List<Course> courseList = instructor.getCourses();
		System.out.println("Instructor : " + instructor);
		System.out.println("Associated courses of the instructor : " + instructor.getCourses());
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor = new Instructor("Susan", "Public", "susan.public@gmail.com");
		//create Instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("http://www.Video.com", "Video games");
		instructor.setInstructorDetail(instructorDetail);

		//create some courses
		Course tempCourse1 = new Course("Air Guitar - he Ultimate Guide");
		Course tempCourse2 = new Course("The Pinball Masterclass");

		instructor.add(tempCourse1);
		instructor.add(tempCourse2);

		//save the instructor
		System.out.println("Saving instructor :" + instructor);
		System.out.println("The Courses :  " + instructor.getCourses());
		appDAO.save(instructor);
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor instructor = new Instructor("Madhu", "Patel", "Madhu@gmail.com");
		//create Instructor detail
		InstructorDetail instructorDetail = new InstructorDetail("http://www.xyz.com", "Madhu");
		instructor.setInstructorDetail(instructorDetail);

	    //save the instructor
		appDAO.save(instructor);
   }

	private void findByInstructor(AppDAO appDAO) {
		int theId = 5;
		Instructor instructor = appDAO.findByInstructorId(theId);
		System.out.println("Instructor : " + instructor);
		System.out.println("Associated Instructor Details : " + instructor.getInstructorDetail());
	}

	private void findByInstructorDetail(AppDAO appDAO) {
		int theId = 4;
		InstructorDetail instructorDetail = appDAO.findByInstructorDetailId(theId);
		System.out.println("Instructor Details : " + instructorDetail);
		System.out.println("Associated Instructor : " + instructorDetail.getInstructor());
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 12;
		System.out.println("Deleting instructor detail id: " + theId );
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("Done! ");
	}

}

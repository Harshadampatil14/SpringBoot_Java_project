package com.empolyees.springboot.hibernatedemo.dao;

import com.empolyees.springboot.hibernatedemo.entity.InstructorDetail;
import com.empolyees.springboot.hibernatedemo.entity.Course;
import com.empolyees.springboot.hibernatedemo.entity.Instructor;
import com.empolyees.springboot.hibernatedemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements  AppDAO{
    //Define field for entity Manager
    private EntityManager entityManager;

    //Inject entiry manager using constructor injection

    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findByInstructorId(int theId) {
        return entityManager.find(Instructor.class, theId);
    }
    @Transactional
    @Override
    public void deleteInstructorById(int theId) {
        // retrieve the instructor
        Instructor instructor = entityManager.find(Instructor.class, theId);
        //get the courses
        List<Course> courses = instructor.getCourses();
        // break association of all course for the instructors
        for(Course course : courses) {
            course.setInstructor(null);
        }

        //delete the instructor
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findByInstructorDetailId(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }
    @Transactional
    @Override
    public void deleteInstructorDetailById(int theId) {
        // retrieve instructor details by id
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class,theId);
        //remove the associated object reference
        //break bi-directional link. Should not delete associated Instructor
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        List<Course> courses = query.setParameter("data", theId).getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i "
                + "JOIN FETCH i.courses "
                + "JOIN FETCH i.instructorDetail "
                + "where i.id = :data", Instructor.class);
        query.setParameter("data",theId);
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int theId) {
        Course course = entityManager.find(Course.class, theId);
        return course;
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course course = entityManager.find(Course.class,theId);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery query = entityManager.createQuery("select c from Course c "
        +"JOIN FETCH c.reviews "
        + "where c.id = :data ", Course.class);
        query.setParameter("data", theId);
        Course theCourse = (Course) query.getSingleResult();
        return  theCourse;
    }

    @Override
    public Course findCourseAndStudentByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c "
                +"JOIN FETCH c.students "
                + "where c.id = :data ", Course.class);
        query.setParameter("data", theId);
        Course theCourse = (Course) query.getSingleResult();
        return  theCourse;
    }

    @Override
    public Student findStudentAndCourseByStudentId(int theId) {

        TypedQuery<Student> query = entityManager.createQuery("select s from Student s "
                +"JOIN FETCH s.courses "
                + "where s.id = :data ", Student.class);
        query.setParameter("data", theId);
       //Student theStudent = (Student) query.getSingleResult();
        return  (Student) query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudnetById(int theId) {
        Student theStudent = entityManager.find(Student.class, theId);
        entityManager.remove(theStudent);
    }
}

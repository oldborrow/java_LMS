import com.example.demo.dao.UserRepository;
import com.example.demo.domain.Course;
import com.example.demo.domain.User;
import com.example.demo.service.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseServiceTests {
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void assignUserToCourse() {
        courseService.assignUserToCourse(1L, 2L);
        Optional<User> optionalUser = userRepository.findById(2L);
        assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        Set<Course> courses = user.getCourses();
        assertNotNull(courses);
        assertEquals(2, courses.size());
        assertTrue(courses.stream().anyMatch(course -> course.getId().equals(1L)));
    }

    @Test
    @Transactional
    void dismissUserFromCourse() {
        courseService.dismissUserFromCourse(1L, 2L);
        Optional<User> optionalUser = userRepository.findById(2L);
        assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        Set<Course> courses = user.getCourses();
        assertNotNull(courses);
        assertTrue(courses.isEmpty());
    }
}

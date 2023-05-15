package nextstep.courses.domain;


import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class CourseTest {

    @Test
    void 여래개_강의_가짐() {
        Course course = new Course("title1", 1L);
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1L);
        String coverImageUrl = "http://test.png";
        Session session = new Session(1L, startDate, endDate, coverImageUrl,
            PaymentPlan.FREE, RecruitmentStatus.RECRUITING, 1, 0);

        course.addSession(session);

        assertThat(course.hasSession(session)).isTrue();
    }

}

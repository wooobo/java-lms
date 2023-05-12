package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class SessionTest {

    @Test
    void 강의_시작종료일_존재() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1L);
        String coverImageUrl = "http://test.png";

        Session session = new Session(1L, startDate, endDate, coverImageUrl, PaymentPlan.FREE,
            RecruitmentStatus.PREPARING, 1, 0);

        assertAll(
            () -> assertThat(session.isEnd(LocalDateTime.now().plusDays(999))).isTrue(),
            () -> assertThat(session.isEnd(LocalDateTime.now().minusDays(999))).isFalse(),
            () -> assertThat(session.isStart(LocalDateTime.now().plusDays(1))).isTrue()
        );
    }

    @Test
    void 강의_커버이미지_존재() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1L);
        String coverImageUrl = "http://test.png";

        Session session = new Session(1L, startDate, endDate, coverImageUrl, PaymentPlan.FREE,
            RecruitmentStatus.PREPARING, 1, 0);
    }

    @Test
    void 강의_비용타입_존재() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1L);
        String coverImageUrl = "http://test.png";

        Session session = new Session(1L, startDate, endDate, coverImageUrl, PaymentPlan.FREE,
            RecruitmentStatus.PREPARING, 1, 0);

        assertAll(
            () -> assertThat(session.isFree()).isTrue()
        );
    }

    @Test
    void 강의_진행타입_존재() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1L);
        String coverImageUrl = "http://test.png";

        Session session = Session.ofPreparing(startDate, endDate, coverImageUrl,
            PaymentPlan.FREE, 1);

        assertThat(session.isPreParing()).isTrue();
    }

    @Test
    void 강의_수강신청_가능_강의상태_존재() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1L);
        String coverImageUrl = "http://test.png";
        Session session = Session.ofPreparing( startDate, endDate, coverImageUrl,
            PaymentPlan.FREE, 1);

        session.open();

        assertThat(session.isOpen()).isTrue();
    }

    @Test
    void 강의_최대수강생인원_존재() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1L);
        String coverImageUrl = "http://test.png";
        Session session = new Session(1L, startDate, endDate, coverImageUrl,
            PaymentPlan.FREE, RecruitmentStatus.RECRUITING, 1, 0);

        assertThat(session.canRegister()).isTrue();
    }

    @Test
    void 강의_최대수강생_신청완료됨() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = LocalDateTime.now().plusDays(1L);
        String coverImageUrl = "http://test.png";
        Session session = new Session(1L, startDate, endDate, coverImageUrl,
            PaymentPlan.FREE, RecruitmentStatus.RECRUITING, 1, 0);

        session.incrementRegisteredStudents();

        assertThat(session.canRegister()).isFalse();
    }
}

package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Session {

    private final Long id;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final String coverImageUrl;
    private final PaymentPlan paymentPlan;
    private RecruitmentStatus recruitmentStatus;
    private final int capacity;
    private int registeredStudents;

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate,
        String coverImageUrl, PaymentPlan paymentPlan, RecruitmentStatus recruitmentStatus,
        int capacity, int registeredStudents) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.coverImageUrl = coverImageUrl;
        this.paymentPlan = paymentPlan;
        this.recruitmentStatus = recruitmentStatus;
        this.capacity = capacity;
        this.registeredStudents = registeredStudents;
    }

    public static Session ofPreparing(LocalDateTime startDate, LocalDateTime endDate,
        String coverImageUrl, PaymentPlan paymentPlan, int capacity) {
        return new Session(0L, startDate, endDate, coverImageUrl, paymentPlan,
            RecruitmentStatus.PREPARING, capacity, 0);
    }

    public boolean isEnd(LocalDateTime value) {
        return endDate.isBefore(value);
    }

    public boolean isStart(LocalDateTime value) {
        return startDate.isBefore(value);
    }

    public boolean isFree() {
        return paymentPlan.isFree();
    }

    public boolean isPreParing() {
        return recruitmentStatus.isPreParing();
    }

    public void open() {
        recruitmentStatus = RecruitmentStatus.RECRUITING;
    }

    public boolean isOpen() {
        return recruitmentStatus.isOpen();
    }

    public boolean canRegister() {
        return capacity > registeredStudents;
    }

    public void incrementRegisteredStudents() {
        registeredStudents++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Session session = (Session) o;
        return id.equals(session.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, coverImageUrl, paymentPlan, recruitmentStatus,
            capacity,
            registeredStudents);
    }
}

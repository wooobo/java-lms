package nextstep.courses.domain;

public enum RecruitmentStatus {
    PREPARING, RECRUITING, END;

    public boolean isPreParing() {
        return this.equals(RecruitmentStatus.PREPARING);
    }

    public boolean isOpen() {
        return this.equals(RecruitmentStatus.RECRUITING);
    }
}

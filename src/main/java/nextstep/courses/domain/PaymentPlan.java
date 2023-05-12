package nextstep.courses.domain;

public enum PaymentPlan {
    FREE,
    PAID;

    public boolean isFree() {
        return this.equals(PaymentPlan.FREE);
    }
}

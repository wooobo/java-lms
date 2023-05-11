package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeleteHistory {
    private Long id;

    private ContentType contentType;

    private Long contentId;

    private NsUser deletedBy;

    private LocalDateTime createdDate = LocalDateTime.now();

    private DeleteHistory() {
    }

    public DeleteHistory(ContentType contentType, Long contentId, NsUser deletedBy, LocalDateTime createdDate) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedBy = deletedBy;
        this.createdDate = createdDate;
    }

    public static DeleteHistory OfQuestion(Long contentId, NsUser deletedBy) {
        return new DeleteHistory(ContentType.QUESTION, contentId, deletedBy, LocalDateTime.now());
    }

    public static DeleteHistory OfAnswer(Long contentId, NsUser deletedBy) {
        return new DeleteHistory(ContentType.ANSWER, contentId, deletedBy, LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

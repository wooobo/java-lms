package nextstep.qna.domain;

import java.util.Objects;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer {

    private Long id;

    private NsUser writer;

    private Question question;

    private String contents;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    private Answer() {
    }

    public Answer(NsUser writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, NsUser writer, Question question, String contents) {
        validate(writer, question);

        this.id = id;
        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    private static void validate(NsUser writer, Question question) {
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }
    }

    @Deprecated
    public Long getId() {
        return id;
    }

    @Deprecated
    public Answer setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
    }

    @Deprecated
    public NsUser getWriter() {
        return writer;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public void delete() {
        deleted = true;
    }

    public DeleteHistory delete(NsUser user) {
        if (!isOwner(user)) {
             throw new IllegalArgumentException("답변 작성자만 삭제 가능합니다.");
        }

        delete();

        return DeleteHistory.OfAnswer(id, user);
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (id == null) {
            return false;
        }

        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

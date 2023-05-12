package nextstep.qna.domain;

import java.util.Objects;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Answer {

    private Long id;

    private NsUser writer;

    private String contents;

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    private Answer() {
    }

    public Answer(NsUser writer, String contents) {
        this(null, writer,  contents);
    }

    public Answer(Long id, NsUser writer, String contents) {
        validate(writer);

        this.id = id;
        this.writer = writer;
        this.contents = contents;
    }

    private static void validate(NsUser writer) {
        if (writer == null) {
            throw new UnAuthorizedException();
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(NsUser writer) {
        return this.writer.equals(writer);
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

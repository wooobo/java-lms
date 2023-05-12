package nextstep.qna.domain;

import java.util.Objects;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class Question {

    private Long id;

    private QuestionBody questionBody;

    private NsUser writer;

    private Answers answers = Answers.empty();

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    private Question() {
    }

    public Question(Long id, NsUser writer, QuestionBody questionBody) {
        this.id = id;
        this.writer = writer;
        this.questionBody = questionBody;
    }

    public Question(NsUser writer, QuestionBody questionBody) {
        this(0L, writer, questionBody);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean containAnswer(Answer answer) {
        return answers.contains(answer);
    }

    public List<DeleteHistory> delete(NsUser user) {
        validateIsOwner(user);

        List<DeleteHistory> deleteHistories = answers.delete(user);
        deleteHistories.add(deleteToHistory(user));

        return deleteHistories;
    }

    private DeleteHistory deleteToHistory(NsUser user) {
        deleted = true;

        return DeleteHistory.OfQuestion(id, user);
    }

    private void validateIsOwner(NsUser user) {
        if (isOwner(user)) {
            return;
        }

        throw new CannotDeleteException("질문 작성자만 삭제 가능합니다.");
    }

    public long getId() {
        return id;
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

        Question question = (Question) o;
        return Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

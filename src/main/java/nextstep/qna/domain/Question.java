package nextstep.qna.domain;

import java.util.Objects;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {

    private Long id;

    private String title;

    private String contents;

    private NsUser writer;

    private List<Answer> answers = new ArrayList<>();

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    private Question() {
    }

    public Question(Long id, NsUser writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Question(NsUser writer, String title, String contents) {
        this(0L, writer, title, contents);
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(NsUser loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Deprecated
    public List<Answer> getAnswers() {
        return answers;
    }

    @Deprecated
    public Long getId() {
        return id;
    }

    @Deprecated
    public NsUser getWriter() {
        return writer;
    }

    @Deprecated
    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean containAnswer(Answer answer) {
        return answers.contains(answer);
    }
    
    public Question delete(NsUser user) {
        validateIsOwner(user);
        validWrittenByQuestionWriter(user);
        answers.forEach(it -> it.delete(user));
        deleted = true;

        return this;
    }

    private void validWrittenByQuestionWriter(NsUser questionWriter) {
        long answersByQuestionWriterCount = answers.stream()
            .filter(answer -> answer.isOwner(questionWriter))
            .count();

        if (answersByQuestionWriterCount != answers.size()) {
            throw new IllegalArgumentException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private void validateIsOwner(NsUser user) {
        if (isOwner(user)) {
            return;
        }
        
        throw new IllegalArgumentException("질문 작성자만 삭제 가능합니다.");
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents
            + ", writer=" + writer + "]";
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

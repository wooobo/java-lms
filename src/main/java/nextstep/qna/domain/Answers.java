package nextstep.qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nextstep.users.domain.NsUser;

public class Answers {

    List<Answer> values;

    public Answers(List<Answer> values) {
        this.values = values;
    }

    public static Answers empty() {
        return new Answers(new ArrayList<>());
    }

    public void add(Answer answer) {
        values.add(answer);
    }

    public boolean contains(Answer answer) {
        return values.contains(answer);
    }

    public List<DeleteHistory> delete(NsUser user) {
        validWrittenByQuestionWriter(user);

        return values.stream()
            .map(it -> it.delete(user))
            .collect(Collectors.toList());
    }

    private void validWrittenByQuestionWriter(NsUser questionWriter) {
        long answersByQuestionWriterCount = values.stream()
            .filter(answer -> answer.isOwner(questionWriter))
            .count();

        if (answersByQuestionWriterCount != values.size()) {
            throw new IllegalArgumentException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}

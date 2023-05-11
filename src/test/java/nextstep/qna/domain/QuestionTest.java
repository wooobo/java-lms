package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 질문에_답변_추가_가능() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        question.addAnswer(answer);

        assertThat(question.containAnswer(answer)).isTrue();
    }

    @Test
    void 자신의_질문인지_확인가능() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        assertAll(
            () -> assertThat(question.isOwner(NsUserTest.JAVAJIGI)).isTrue(),
            () -> assertThat(question.isOwner(NsUserTest.SANJIGI)).isFalse()
        );
    }

    @Test
    void 질문_삭제_상태_변경할수있다() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        Question actual = question.delete(NsUserTest.JAVAJIGI);

        assertThat(actual.isDeleted()).isTrue();
    }

    @Test
    void 질문_작성자가_아니면_삭제불가() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            question.delete(NsUserTest.SANJIGI);
        });
    }

    @Test
    void 질문_답변_작성자_모두같으면_삭제가능() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);

        Question actual = question.delete(NsUserTest.JAVAJIGI);

        assertThat(actual.isDeleted()).isTrue();
    }

    @Test
    void 다른사람_답변_존재시_답변_삭제_불가() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(1L, NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            question.delete(NsUserTest.JAVAJIGI);
        });
    }

    @Test
    void 질문_삭제시_답변같이_삭제됨() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);

        question.delete(NsUserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
    }
}

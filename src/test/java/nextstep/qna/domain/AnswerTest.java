package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, "Answers Contents2");


    @Test
    void 삭제요청_사용자와_질문작성자_같으면_삭제_가능() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, "Answers Contents1");

        answer.delete(NsUserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    void 삭제요청_사용자와_질문작성자_다르면_삭제안됨() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, "Answers Contents1");

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            answer.delete(NsUserTest.SANJIGI);
        });
    }


    @Test
    void 질문_작성자_일치_검증할수있다() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI,"Answers Contents1");

        assertAll(
            () -> assertThat(answer.isOwner(NsUserTest.JAVAJIGI)).isTrue(),
            () -> assertThat(answer.isOwner(NsUserTest.SANJIGI)).isFalse()
        );
    }

    @Test
    void 작성자_필수이다() {
        assertThatExceptionOfType(UnAuthorizedException.class).isThrownBy(() -> {
            new Answer(null,"Answers Contents1");
        });
    }
}

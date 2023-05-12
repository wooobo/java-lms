package nextstep.qna.domain;

import java.util.Objects;

public class QuestionBody {
    private final String title;

    private final String contents;

    private QuestionBody(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public static QuestionBody of(String title, String contents) {
        return new QuestionBody(title, contents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionBody that = (QuestionBody) o;
        return Objects.equals(title, that.title) && Objects.equals(contents,
            that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, contents);
    }
}

package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;

public class Sessions {

    private final List<Session> values;

    public Sessions(List<Session> values) {
        this.values = values;
    }

    public static Sessions empty() {
        return new Sessions(new ArrayList<>());
    }

    public void add(Session value) {
        values.add(value);
    }

    public boolean contains(Session value) {
        return values.contains(value);
    }
    
}

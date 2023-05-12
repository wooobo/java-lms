package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Course {

    private Long id;

    private String title;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
    private Sessions sessions;

    private Course() {
    }

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null, Sessions.empty());
    }

    @Deprecated
    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sessions = Sessions.empty();
    }

    public Course(Long id, String title, Long creatorId, LocalDateTime createdAt,
        LocalDateTime updatedAt, Sessions sessions) {
        this.id = id;
        this.title = title;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sessions = sessions;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public boolean hasSession(Session session) {
        return sessions.contains(session);
    }

    @Deprecated
    public String getTitle() {
        return title;
    }

    @Deprecated
    public Long getCreatorId() {
        return creatorId;
    }

    @Deprecated
    public LocalDateTime getCreatedAt() {
        return createdAt;
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
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, creatorId, createdAt, updatedAt, sessions);
    }
}

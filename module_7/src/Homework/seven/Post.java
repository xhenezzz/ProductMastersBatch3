package Homework.seven;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Post {
    private static final AtomicLong ID_GENERATOR = new AtomicLong(0);
    private final long id;
    private final User author;
    private final String content;
    private final LocalDateTime creationDate;
    private int likes;
    private int reposts;

    public Post(User author, String content) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.author = author;
        this.content = content.length() > 280 ? content.substring(0, 280) : content;
        this.creationDate = LocalDateTime.now();
        this.likes = 0;
        this.reposts = 0;
    }

    private Post(User author, String originalContent, boolean isRepost) {
        this.id = ID_GENERATOR.incrementAndGet();
        this.author = author;
        this.content = "REPOST: " + originalContent;
        this.creationDate = LocalDateTime.now();
        this.likes = 0;
        this.reposts = 0;
    }

    public void like() {
        this.likes++;
    }

    public <T extends Post> T repost(User newAuthor) {
        this.reposts++;
        return (T) new Post(newAuthor, this.content, true);
    }


    public long getId() { return id; }
    public User getAuthor() { return author; }
    public String getContent() { return content; }
    public int getLikes() { return likes; }
    public int getReposts() { return reposts; }
    public LocalDateTime getCreationDate() { return creationDate; }

    @Override
    public String toString() {
        return String.format("Post{id=%d, author=%s, content='%s', likes=%d, reposts=%d}",
                id, author, content, likes, reposts);
    }
}

package Homework.seven;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TwitterService {
    private final List<Post> posts;

    public TwitterService() {
        this.posts = new ArrayList<>();
        loadInitialPosts();
    }

    public void createPost(User author, String content) {
        posts.add(new Post(author, content));
    }

    public boolean likePost(long postId) {
        Optional<Post> postOptional = posts.stream()
                .filter(p -> p.getId() == postId)
                .findFirst();

        if (postOptional.isPresent()) {
            postOptional.get().like();
            return true;
        }
        return false;
    }

    public boolean repostPost(long postId, User reposter) {
        Optional<Post> postOptional = posts.stream()
                .filter(p -> p.getId() == postId)
                .findFirst();

        if (postOptional.isPresent()) {
            Post originalPost = postOptional.get();
            Post newRepost = originalPost.repost(reposter);
            posts.add(newRepost);
            return true;
        }
        return false;
    }

    public List<Post> getAllPosts() {
        return posts.stream()
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                .collect(Collectors.toList());
    }

    public List<Post> getPopularPosts(int limit) {
        return posts.stream()
                .sorted(Comparator.comparing(Post::getLikes).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<Post> getUserPosts(User user) {
        return posts.stream()
                .filter(p -> p.getAuthor().equals(user))
                .sorted(Comparator.comparing(Post::getCreationDate).reversed())
                .collect(Collectors.toList());
    }

    private void loadInitialPosts() {
        User alice = new User("Alice");
        User bob = new User("Bob");
        User charlie = new User("Charlie");

        posts.add(new Post(alice, "Привет, мир!"));
        posts.add(new Post(bob, "Сегодня отличный день для кодинга! "));
        posts.add(new Post(charlie, "Люблю программировать на Java."));

        posts.get(0).like();
        posts.get(0).like();
        posts.get(1).like();
        posts.get(1).repost(alice);
    }
}
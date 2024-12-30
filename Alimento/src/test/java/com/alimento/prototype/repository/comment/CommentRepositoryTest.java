package com.alimento.prototype.repository.comment;

import com.alimento.prototype.entities.blog.BlogPost;
import com.alimento.prototype.entities.blog.ContentBlock;
import com.alimento.prototype.entities.comment.Comment;
import com.alimento.prototype.entities.user.User;
import com.alimento.prototype.repositories.blog.BlogPostRepository;
import com.alimento.prototype.repositories.comment.CommentRepository;
import com.alimento.prototype.repositories.user.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@Transactional
public class CommentRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    private User user;
    private BlogPost blogPost;

    @BeforeEach
    public void setup() {
        // Create and save a BlogPost
        List<ContentBlock> blocks = new ArrayList<>();
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle("Test Blog Post");
        blogPost.setBlocks(blocks);
        blogPost.setSlug("test-blog-post");
        blogPostRepository.save(blogPost);
        //System.out.println(blogPostRepository.getBlogPostBySlug("test-blog-post"));
        this.blogPost = blogPostRepository.getBlogPostBySlug("test-blog-post");

        // Create and save a User
        User user = new User();
        user.setUsername("testUser");
        user.setEmail("testuser@example.com");
        userRepository.save(user);
        //System.out.println(userRepository.getUserByUsernameForTest("testUser").get);
        this.user = userRepository.getUserByUsernameForTest("testUser");
    }

//    @BeforeEach
//    public void enableForeignKeys() {
//        entityManager.createNativeQuery("PRAGMA foreign_keys = ON").executeUpdate();
//    }

    @Test
    public void testSaveComment_success() {
        // Arrange
        String commentContent = "This is a test comment.";
        LocalDate commentDate = LocalDate.now();

        // Create Comment object
        Comment comment = new Comment();
        comment.setCommentContent(commentContent);
        comment.setBlogPost(this.blogPost);
        comment.setUser(this.user);
        comment.setCommentDate(commentDate);

        // Act
        Comment savedComment = commentRepository.save(comment);

        // Assert
        assertThat(savedComment).isNotNull();
        assertThat(savedComment.getCommentId()).isGreaterThan(0);
        assertThat(savedComment.getCommentContent()).isEqualTo(commentContent);
        assertThat(savedComment.getBlogPost()).isEqualTo(blogPost);
        assertThat(savedComment.getUser()).isEqualTo(user);
        assertThat(savedComment.getCommentDate()).isEqualTo(commentDate);
    }

}

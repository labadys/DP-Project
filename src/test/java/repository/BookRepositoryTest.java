package repository;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BookRepository bookRepository;

    @Test
    void findByTitleContainingIgnoreCase_WhenTitleExists_ShouldReturnBooks() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setPublicationYear(2024);
        book.setIsbn("1234567890");
        entityManager.persist(book);
        entityManager.flush();

        List<Book> foundBooks = bookRepository.findByTitleContainingIgnoreCase("test");

        assertFalse(foundBooks.isEmpty());
        assertEquals("Test Book", foundBooks.get(0).getTitle());
    }

    @Test
    void saveBook_ShouldPersistBook() {
        Book book = new Book();
        book.setTitle("New Book");
        book.setPublicationYear(2024);
        book.setIsbn("0987654321");

        Book savedBook = bookRepository.save(book);

        assertNotNull(savedBook.getId());
        assertEquals("New Book", savedBook.getTitle());
    }

    @Test
    void findById_WhenBookExists_ShouldReturnBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        entityManager.persist(book);
        entityManager.flush();

        Optional<Book> foundBook = bookRepository.findById(book.getId());

        assertTrue(foundBook.isPresent());
        assertEquals("Test Book", foundBook.get().getTitle());
    }
}

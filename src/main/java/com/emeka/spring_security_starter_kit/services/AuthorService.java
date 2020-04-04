package com.emeka.spring_security_starter_kit.services;

import com.emeka.blogspringboot.models.Author;
import com.emeka.blogspringboot.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public String createAuthor(Author author) {
        author.setRole("USER");
        authorRepository.save(author);
        return "Author created successfully";
    }

    public String updateAuthor(Author author, int id) {
       Optional<Author> theAuthor = authorRepository.findById(id);
       theAuthor.ifPresent(mainAuthor -> {
           mainAuthor.setSurname(author.getSurname());
           mainAuthor.setFirstName(author.getFirstName());
           mainAuthor.setEmail(author.getEmail());
           mainAuthor.setPassword(author.getPassword());

           authorRepository.save(mainAuthor);

       });
       return "Author updated successfully";
    }

    public String deleteAuthor(int id) {
        authorRepository.deleteById(id);
        return "Author deleted successfully";
    }
}

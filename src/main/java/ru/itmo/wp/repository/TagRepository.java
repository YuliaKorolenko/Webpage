package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.domain.User;

public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query(value = "SELECT * FROM tag WHERE name=?", nativeQuery = true)
    Tag findByName(String name);
}

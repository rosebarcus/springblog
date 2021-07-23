package com.codeup.springblog.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad findAdByID(long id);

    @Query("From Ad a WHERE a.title LIKE %:terms%")
    Ad findFirstByTitle(String term);
}

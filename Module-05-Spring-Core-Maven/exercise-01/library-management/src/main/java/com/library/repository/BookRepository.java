package com.library.repository;

import java.util.Arrays;
import java.util.List;

public class BookRepository {
    public List<String> findAll() {
        return Arrays.asList("Percy Jackson and the lightening thief", "The Norton Anthology of English Literature", "To Kill a Mockingbird");
    }
}
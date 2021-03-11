package io.haufe.beercatalogueservice.controller;


import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface Controller<T> {
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    ResponseEntity<Collection<T>> findAll();

    @GetMapping("{id}")
    ResponseEntity<T> findById(@PathVariable Long id);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    ResponseEntity<T> add(@RequestBody T t);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    ResponseEntity<T> update(@RequestBody T t);

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    ResponseEntity<String> deleteById(@PathVariable Long id);

    @GetMapping("/invalid")
    ResponseEntity<String> invalid();
}
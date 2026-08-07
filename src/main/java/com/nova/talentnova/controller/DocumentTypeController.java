package com.nova.talentnova.controller;

import com.nova.talentnova.model.DocumentType;
import com.nova.talentnova.repository.IDocumentTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/document-type")
@CrossOrigin("*")
@RequiredArgsConstructor
public class DocumentTypeController {

    private final IDocumentTypeRepository repo;

    @GetMapping
    public List<DocumentType> getAllDocumentTypes() {
        return repo.findAll();
    }
}

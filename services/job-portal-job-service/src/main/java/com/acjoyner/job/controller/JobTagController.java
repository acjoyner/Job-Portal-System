package com.acjoyner.job.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acjoyner.job.repository.JobTagRepository;
import com.acjoyner.job.service.JobTagService;
import com.acjoyner.job.payload.JobTagRequest;

import jakarta.validation.Valid;

import com.acjoyner.job.dto.ApiResponse;
import com.acjoyner.job.dto.JobTagResponse;
import com.acjoyner.job.payload.JobTagRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-tags")
public class JobTagController {
    private final JobTagRepository jobTagRepository;
    private final JobTagService jobTagService;


    @PostMapping
    public ResponseEntity<JobTagResponse> createJobTag(
        @RequestBody @Valid JobTagRequest jobTagRequest) throws Exception{
            return ResponseEntity.status(HttpStatus.CREATED)
        .body(jobTagService.createTag(jobTagRequest));
    }

    @GetMapping
    public ResponseEntity<List<JobTagResponse>> getAllTags() {
        return ResponseEntity.ok(jobTagService.getAllTags());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobTagResponse> getMethodName(
        @PathVariable Long id) throws Exception{
        return ResponseEntity.ok(jobTagService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobTagResponse> putMethodName(
        @PathVariable Long id, 
        @RequestBody @Valid JobTagRequest req) throws Exception {
        
        
        return ResponseEntity.ok(jobTagService.updateTag(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTags(
        @PathVariable Long id) throws Exception {
        jobTagService.deleteTag(id);
            return  ResponseEntity.ok(new ApiResponse("Tag deleted successfully.", true));
            
        }
    
    
}

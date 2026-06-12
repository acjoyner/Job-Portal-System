package com.acjoyner.job.service.impl;

import com.acjoyner.job.repository.JobTagRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.acjoyner.job.dto.JobTagResponse;
import com.acjoyner.job.mapper.JobTagMapper;
import com.acjoyner.job.model.JobTag;
import com.acjoyner.job.payload.JobTagRequest;
import com.acjoyner.job.service.JobTagService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class JobTagServiceImpl  implements JobTagService{

    private final JobTagRepository jobTagRepository;


    @Override
    public JobTagResponse createTag(JobTagRequest req) throws Exception {
        if(jobTagRepository.existsByName(req.getName())){
            throw new Exception("tag name already exist");
        }

        String slug = generateUniqueSlug(req.getName());

        JobTag jobTag=JobTag.builder()
            .name(req.getName())
            .slug(slug)
            .build();

        JobTag saved = jobTagRepository.save(jobTag);

        return JobTagMapper.toTagResponse(saved);
    }

    @Override
    public List<JobTagResponse> getAllTags() {
        
        return jobTagRepository.findAll()
            .stream().map(JobTagMapper::toTagResponse)
            .toList();
    }

    @Override
    public JobTagResponse getById(Long id) throws Exception {
        JobTag jobTag = getTagEntityById(id);
        return JobTagMapper.toTagResponse(jobTag);
    }

    @Override
    public JobTagResponse updateTag(Long id, JobTagRequest req) throws Exception {
        JobTag jobTag = getTagEntityById(id);

        if(!jobTag.getName().equals(req.getName()) 
        && jobTagRepository.existsByName(req.getName())){
            throw new Exception("Tag name already exists");
        }
        jobTag.setName(req.getName());
        return JobTagMapper.toTagResponse(jobTagRepository.save(jobTag));
    }

    @Override
    public void deleteTag(Long id) {
        jobTagRepository.deleteById(id);
    }

    @Override
    public JobTag getTagEntityById(Long id) throws Exception {
        return jobTagRepository.findById(id).orElseThrow(
            ()-> new Exception("job tag not found")
        );
    }

      private String generateUniqueSlug(String name) {
        String base = name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("[\\s-]+", "-");
        if (!jobTagRepository.existsBySlug(base)) {
            return base;
        }
        int counter = 1;
        while (jobTagRepository.existsBySlug(base + "-" + counter)) {
            counter++;
        }

        return base + "-" + counter;
    }

      @Override
      public Set<JobTag> getTagsByIds(Set<Long> ids) throws Exception {
        List<JobTag> tags = jobTagRepository.findAllById(ids);
        return new HashSet<>(tags);
      }

    
}

package com.internet.inchirurgiademo.services;

import com.internet.inchirurgiademo.dto.TagDto;
import com.internet.inchirurgiademo.entities.Tag;
import com.internet.inchirurgiademo.entities.TagTaxonomy;
import com.internet.inchirurgiademo.repositories.TagRepository;
import com.internet.inchirurgiademo.repositories.TagTaxonomyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private Function<Tag, TagDto> tagDtoFunction;

    @Autowired
    private TagTaxonomyRepository taxonomyRepository;

    @Override
    public List<String> listCategories() {
        List<TagTaxonomy> tagTaxonomyList = taxonomyRepository.findAllByTagType("category");
        List<String> result = null;
        if (tagTaxonomyList == null) return null;
        result = new LinkedList<>();
        List<Tag> tagList = null;
        for (TagTaxonomy taxonomy : tagTaxonomyList) {

            tagList = tagRepository.findAllByType(taxonomy);
            if (tagList != null && tagList.size() > 0) {
                for (Tag tag : tagList) {
                    result.add(tag.getName());
                }
            }
        }
        result.sort(String::compareTo);
        return result;
    }

}

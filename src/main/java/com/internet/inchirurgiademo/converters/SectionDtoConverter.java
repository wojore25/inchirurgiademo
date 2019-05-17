package com.internet.inchirurgiademo.converters;

import com.internet.inchirurgiademo.dto.PnTableDto;
import com.internet.inchirurgiademo.dto.SectionDto;
import com.internet.inchirurgiademo.entities.PartTable;
import com.internet.inchirurgiademo.entities.PostSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

@Component
public class SectionDtoConverter implements Function<PostSection, SectionDto> {

    @Autowired
    private Function<PartTable, PnTableDto> PnTableDtoConvertor;

    @Override
    public SectionDto apply(PostSection postSection) {
        SectionDto sectionDto = null;
        if (postSection != null) {
            sectionDto = new SectionDto();

            if (postSection.getId() != null) sectionDto.setId(postSection.getId());
            if (postSection.getImageFileName() != null) sectionDto.setImageFile("/images/" + postSection.getImageFileName());
            if (postSection.getSectionPosition() != null) sectionDto.setPosition(postSection.getSectionPosition());
            if (postSection.getPartTableList() != null) {
                List<PnTableDto> pnTableDtoList = new LinkedList<>();
                PnTableDto pnTableDto = null;
                for (PartTable partTable : postSection.getPartTableList()) {
                    pnTableDto = PnTableDtoConvertor.apply(partTable);
                    pnTableDtoList.add(pnTableDto);

                }
                sectionDto.setTableList(pnTableDtoList);
            }
        }
        return sectionDto;
    }
}

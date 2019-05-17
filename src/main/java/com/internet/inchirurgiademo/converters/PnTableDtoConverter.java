package com.internet.inchirurgiademo.converters;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.internet.inchirurgiademo.dto.HeaderCell;
import com.internet.inchirurgiademo.dto.PnTableDto;
import com.internet.inchirurgiademo.dto.TableHeaderObject;
import com.internet.inchirurgiademo.dto.TableRow;
import com.internet.inchirurgiademo.entities.PartTable;
import com.internet.inchirurgiademo.repositories.PartTableRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class PnTableDtoConverter implements Function<PartTable, PnTableDto> {

    private PartTableRepository partTableRepository;

    @Override
    public PnTableDto apply(PartTable partTable) {

        PnTableDto pnTableDto = null;

        if (partTable != null) {

            pnTableDto = new PnTableDto();
            if (partTable.getId() != null) pnTableDto.setId(partTable.getId());
            if (partTable.getHeader() != null) pnTableDto.setHeader(partTable.getHeader());
            if (partTable.getTableDescription() != null) pnTableDto.setDescrtiption(partTable.getTableDescription());
            if (partTable.getTablePosition() != null) pnTableDto.setPosition(partTable.getTablePosition());

            if (partTable.getTableHeader() != null) pnTableDto.setTableHeader(getHeaderList(partTable));

            if (partTable.getTableContent() != null) pnTableDto.setTableRowList(getTableRows(partTable, pnTableDto));

        }
        return pnTableDto;
    }


    private static List<HeaderCell> getHeaderList(PartTable partTable) {
        List<HeaderCell> headerCellList = new LinkedList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<TableHeaderObject> headerObjects =
                    objectMapper.readValue(partTable.getTableHeader(),
                            new TypeReference<List<TableHeaderObject>>() {
                            });

            for (TableHeaderObject cell : headerObjects) {
                headerCellList.add(new HeaderCell(cell.getName(), cell.getWidth(), cell.getTitle()));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return headerCellList;
    }

    private static List<TableRow> getTableRows(PartTable partTable, PnTableDto pnTableDto) {

        List<TableRow> tableRowList = null;

        if (pnTableDto.getTableHeader() != null & pnTableDto.getTableHeader().size() > 0) {

            tableRowList = new LinkedList<>();
            ObjectMapper objectMapper = new ObjectMapper();
            Pattern pattern = Pattern.compile("(\\{.+?\\})");
            Matcher matcher = pattern.matcher(partTable.getTableContent());

            TableRow tableRow = null;

            List<String> rowStrings = new LinkedList<>();
            while (matcher.find()){
                rowStrings.add(matcher.group());
            }
            for (String row : rowStrings) {
                tableRow = new TableRow();
                for (HeaderCell cell: pnTableDto.getTableHeader()){
                    JsonNode jsonNode = null;
                    try {
                        jsonNode = objectMapper.readTree(row);
                        tableRow.addCell(jsonNode.get(cell.getName()).asText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (tableRow != null) tableRowList.add(tableRow);
            }

        }
        return tableRowList;
    }

}



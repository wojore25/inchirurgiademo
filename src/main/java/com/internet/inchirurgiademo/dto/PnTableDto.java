package com.internet.inchirurgiademo.dto;

import java.util.LinkedList;
import java.util.List;

public class PnTableDto {
    private Long id;
    private List<HeaderCell> tableHeader;
    private List<TableRow> tableRowList;
    private String descrtiption;
    private String header;
    private Integer position;

    public PnTableDto() {
    }

    public void addHeaderCell(HeaderCell headerCell){
        if (this.tableHeader == null){
            this.tableHeader = new LinkedList<>();
        }
        this.tableHeader.add(headerCell);
    }

    public void setTableHeader(List<HeaderCell> tableHeader) {
        this.tableHeader = tableHeader;
    }

    public void addTableRow(TableRow tableRow){
        if (this.tableRowList == null){
            this.tableRowList = new LinkedList<>();
        }
        this.tableRowList.add(tableRow);
    }

    public void setTableRowList(List<TableRow> tableRowList) {
        this.tableRowList = tableRowList;
    }

    public List<HeaderCell> getTableHeader() {
        return tableHeader;
    }

    public List<List<String>> getTableRowList() {
        List<List<String>> result = new LinkedList<>();
        for (TableRow element: tableRowList) {
            result.add(element.getCellList());
        }
        return result;
    }

    public String getDescrtiption() {
        return descrtiption;
    }

    public void setDescrtiption(String descrtiption) {
        this.descrtiption = descrtiption;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Long getId() {
        return id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setId(Long id) {
        this.id = id;


    }
}

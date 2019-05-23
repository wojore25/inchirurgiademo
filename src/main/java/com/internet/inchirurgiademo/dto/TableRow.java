package com.internet.inchirurgiademo.dto;

import java.util.LinkedList;
import java.util.List;

public class TableRow {
    List<String> cellList;

    public TableRow() {
    }

    public void addCell(String cell){
        if (this.cellList == null){
            cellList = new LinkedList<>();
        }
        this.cellList.add(cell);
    }

    public List<String> getCellList(){
        if (this.cellList == null){
            this.cellList = new LinkedList<>();
        }
        return this.cellList;
    }
}

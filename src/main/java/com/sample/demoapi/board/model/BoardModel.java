package com.sample.demoapi.board.model;

import java.util.Date;
import com.sample.demoapi.board.entity.Board;
import lombok.Getter;
import lombok.Builder;

@Getter
public class BoardModel {
    private Long num;
    private String title;
    private String writeName;
    private String contents;
    private Date writeDate;
    private Date modifyDate;

    public Board toEntity() {
        Board board = Board.builder().id(this.num).title(this.title).author(this.writeName)
                .content(this.contents).build();

        return board;
    }

    @Builder
    public BoardModel(Long id, String title, String author, String content, Date createdDate,
            Date modifiedDate) {
        this.num = id;
        this.title = title;
        this.writeName = author;
        this.contents = content;
        this.writeDate = createdDate;
        this.modifyDate = modifiedDate;
    }
}

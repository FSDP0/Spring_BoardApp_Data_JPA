package com.sample.demoapi.board.entity;

import lombok.*;
import jakarta.persistence.*;
import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board {

    // Identification Column (Primary Key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id", nullable = false)
    private Long boardId;

    // Title Column
    @Column(name = "board_title", length = 50, nullable = false)
    private String boardTitle;

    // Author Column
    @Column(name = "board_author", length = 30, nullable = false)
    private String boardAuthor;

    // Content Column
    @Column(name = "board_content", length = 200, nullable = true)
    private String boardContent;

    // Board Created Date Column
    @CreatedDate
    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    // Board Modified Date Column
    @LastModifiedDate
    @Column(name = "modified_date", nullable = true)
    private Date modifiedDate;

    @Builder
    public Board(Long id, String title, String author, String content, Date createdDate,
            Date modifiedDate) {
        this.boardId = id;
        this.boardTitle = title;
        this.boardAuthor = author;
        this.boardContent = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}

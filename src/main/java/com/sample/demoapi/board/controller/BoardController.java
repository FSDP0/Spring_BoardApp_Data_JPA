package com.sample.demoapi.board.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.sample.demoapi.board.model.BoardModel;
import com.sample.demoapi.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Board API", description = "Board Application CRUD Guide")
@RestController // Define REST Controller
@RequestMapping("boards") // This Controller Url Route Mapping based on ../boards
public class BoardController {

    // Constructor Injection
    private final BoardService boardService;

    private BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    //

    // Get All Boards List
    /**
     * @return List<BoardModel>
     */
    @Operation(summary = "Find all post", description = "등록된 모든 게시글 조회")
    @GetMapping
    public List<BoardModel> findBoardList() {
        return this.boardService.getAllBoards();
    }

    // Get Board Info
    /**
     * @param Long id
     * 
     * @return BoardModel
     */
    @Operation(summary = "Find specific post", description = "등록된 특정 게시글 조회")
    @GetMapping("/:{id}")
    public BoardModel findByBoardId(@PathVariable Long id) {
        return this.boardService.getBoard(id);
    }

    // Board Post
    /**
     * @body BoardModel
     * 
     * @return String
     */
    @Operation(summary = "Post enroll", description = "게시글 등록")
    @PostMapping
    public String createBoardPost(@RequestBody BoardModel boardModel) {
        if (this.boardService.createBoard(boardModel)) {
            return "OK";
        } else {
            return "Not OK";
        }
    }

    // Modify Post
    /**
     * @param Long id
     * 
     * @body BoardModel
     * 
     * @return String
     */
    @Operation(summary = "Post modify", description = "게시글 수정")
    @PutMapping("/:{id}")
    public String editBoardPost(@PathVariable Long id, @RequestBody BoardModel boardDto) {
        this.boardService.editBoard(id, boardDto);

        return "OK";
    }

    // Delete Post
    /**
     * @param Long id
     * 
     * @return String
     */
    @Operation(summary = "Post delete", description = "게시글 삭제")
    @DeleteMapping("/:{id}")
    public void deleteBoardPost(@PathVariable Long id) {
        this.boardService.deleteBoard(id);
    }
}

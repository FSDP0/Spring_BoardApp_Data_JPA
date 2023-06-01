package com.sample.demoapi.board.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sample.demoapi.board.model.BoardModel;
import com.sample.demoapi.board.service.BoardService;

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
    @DeleteMapping("/:{id}")
    public void deleteBoardPost(@PathVariable Long id) {
        this.boardService.deleteBoard(id);
    }
}

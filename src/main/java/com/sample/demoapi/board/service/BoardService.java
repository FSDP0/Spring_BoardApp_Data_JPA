package com.sample.demoapi.board.service;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import com.sample.demoapi.board.entity.Board;
import com.sample.demoapi.board.model.BoardModel;
import com.sample.demoapi.board.repository.BoardRepository;

@Service
public class BoardService {

    // Constructor Injection
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    //

    // Get All Boards
    public List<BoardModel> getAllBoards() {
        List<Board> boardList = this.boardRepository.findAll();

        if (boardList.isEmpty()) {
            // return new ResponseStatusException(HttpStatus.NOT_FOUND, "no data");
            return null;
        }

        List<BoardModel> boardModelList = new ArrayList<BoardModel>();

        for (Board board : boardList) {
            BoardModel boardModel = BoardModel.builder().id(board.getBoardId())
                    .title(board.getBoardTitle()).author(board.getBoardAuthor())
                    .content(board.getBoardContent()).createdDate(board.getCreatedDate())
                    .modifiedDate(board.getModifiedDate()).build();

            boardModelList.add(boardModel);
        }

        return boardModelList;
    }

    // Get Board
    public BoardModel getBoard(Long boardId) {
        Board board = this.boardRepository.findById(boardId).get();

        if (board == null) {
            return null;
        }

        BoardModel boardModel = BoardModel.builder().id(board.getBoardId())
                .title(board.getBoardTitle()).author(board.getBoardAuthor())
                .content(board.getBoardContent()).createdDate(board.getCreatedDate())
                .modifiedDate(board.getModifiedDate()).build();

        return boardModel;
    }

    // Post Board
    @Transactional
    public Boolean createBoard(BoardModel boardModel) {
        if (!isExistPost(boardModel.getNum())) {
            this.boardRepository.save(boardModel.toEntity());

            return true;
        } else {
            return false;
        }
    }

    // Edit Board
    @Transactional
    public Boolean editBoard(Long boardId, BoardModel boardModel) {
        if (isExistPost(boardId)) {
            Date createdDate = this.boardRepository.findById(boardId).get().getCreatedDate();

            Board board = Board.builder().id(boardId).title(boardModel.getTitle())
                    .author(boardModel.getWriteName()).content(boardModel.getContents())
                    .createdDate(createdDate).build();

            // Update
            // this.boardRepository.save(boardModel.toEntity());
            this.boardRepository.save(board);
        }

        return false;
    }

    // Delete Board
    @Transactional
    public Boolean deleteBoard(Long boardId) {
        if (isExistPost(boardId)) {
            this.boardRepository.deleteById(boardId);

            return true;
        } else {
            return false;
        }
    }

    Boolean isExistPost(Long id) {
        if (!this.boardRepository.findByBoardId(id).isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}

package com.sample.demoapi.board.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sample.demoapi.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByBoardId(Long id);
}

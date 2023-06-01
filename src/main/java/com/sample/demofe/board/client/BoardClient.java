package com.sample.demofe.board.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import com.sample.demofe.board.model.Board;

@PropertySource("classpath:application.yml")
@FeignClient(name = "BOARD-CLIENT", url = "${api.url}")
public interface BoardClient {

    @GetMapping
    public List<Board> getAllBoards();

    @GetMapping("/:{id}")
    public Board getBoardById(@PathVariable Long id);

    @PostMapping
    public String createBoardPost(@RequestBody Board dto);

    @PutMapping("/:{id}")
    public String editBoardPost(@PathVariable Long id, @RequestBody Board dto);

    @DeleteMapping("/:{id}")
    public String deleteBoardPost(@PathVariable Long id);
}

package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        // RequestDto -> entity
        Memo memo = new Memo(requestDto);

        // Memo Max ID Check
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId);
        // save to DB
        memoList.put(memo.getId(), memo);

        //entity > ResponseDto
        MemoResponseDto responseDto = new MemoResponseDto(memo);

        return responseDto;
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        // Map to List
        List<MemoResponseDto> responseList = memoList.values().stream().map(MemoResponseDto::new).toList();

        return responseList;
    }

    @PutMapping("/memos/{id}")
    public long updateMemo(@PathVariable long id, @RequestBody MemoRequestDto requestDto) {
        // check this memo is exist in DB
        if (memoList.containsKey(id)) {
            Memo memo = memoList.get(id);

            // update memo
            memo.update(requestDto);
            return memo.getId();

        } else {
            throw new IllegalArgumentException("select memo are don't exist.");
        }

    }

    @DeleteMapping("memos/{id}")
    public long deleteMemo(@PathVariable long id) {
        // check this memo is exist in DB
        if (memoList.containsKey(id)) {
            memoList.remove(id);

            return id;

        } else {
            throw new IllegalArgumentException("select memo are don't exist.");
        }
    }

}

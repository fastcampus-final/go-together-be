package com.example.gotogether.board.dto;

import com.example.gotogether.board.entity.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;

public class BoardDTO {

    @ApiModel(value = "게시판 목록 조회 응답")
    @NoArgsConstructor
    @Getter
    @Setter
    public static class BoardListResDTO {

        @ApiModelProperty(value = "여행후기 아이디")
        private Long boardId;
        @ApiModelProperty(value = "작성자 이름")
        private String userName;
        @ApiModelProperty(value = "게시판 타입")
        private String boardType;
        @ApiModelProperty(value = "게시판 제목")
        private String boardTitle;
        @ApiModelProperty(value = "게시판 생성일자")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate createdDate;
        @ApiModelProperty(value = "게시판 마지막 수정일자")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate updatedDate;

        public BoardListResDTO(Board board) {
            this.boardId = board.getBoardId();
            this.userName = board.getUser().getName();
            this.boardType = board.getType();
            this.boardTitle = board.getTitle();
            this.createdDate = board.getCreatedDate().toLocalDate();
            this.updatedDate = board.getUpdatedDate().toLocalDate();
        }
    }

}

package com.example.gotogether.board.dto;

import com.example.gotogether.auth.entity.User;
import com.example.gotogether.board.entity.Board;
import com.example.gotogether.board.entity.BoardType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

public class BoardDTO {

    @ApiModel(value = "게시글 목록 조회 응답")
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ListResDTO {

        @ApiModelProperty(value = "게시글 아이디")
        private Long boardId;
        @ApiModelProperty(value = "작성자 이름")
        private String userName;
        @ApiModelProperty(value = "게시글 타입")
        private String boardType;
        @ApiModelProperty(value = "게시글 썸네일")
        private String boardThumbnail;
        @ApiModelProperty(value = "게시글 제목")
        private String boardTitle;
        @ApiModelProperty(value = "게시글 생성일자")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate createdDate;

        public ListResDTO(Board board) {
            this.boardId = board.getBoardId();
            this.userName = board.getUser().getName();
            this.boardType = board.getType().getValue();
            this.boardThumbnail = board.getThumbnail();
            this.boardTitle = board.getTitle();
            this.createdDate = board.getCreatedDate().toLocalDate();
        }
    }

    @ApiModel(value = "게시글 상세 정보 조회 응답")
    @NoArgsConstructor
    @Getter
    @Setter
    public static class DetailResDTO {

        @ApiModelProperty(value = "게시글 아이디")
        private Long boardId;
        @ApiModelProperty(value = "작성자 이름")
        private String userName;
        @ApiModelProperty(value = "게시글 타입")
        private String boardType;
        @ApiModelProperty(value = "게시글 제목")
        private String boardTitle;
        @ApiModelProperty(value = "게시글 내용")
        private String boardContent;
        @ApiModelProperty(value = "게시글 생성일자")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate createdDate;
        @ApiModelProperty(value = "게시글 마지막 수정일자")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate updatedDate;

        public DetailResDTO(Board board) {
            this.boardId = board.getBoardId();
            this.userName = board.getUser().getName();
            this.boardType = board.getType().getValue();
            this.boardTitle = board.getTitle();
            this.boardContent = board.getContent();
            this.createdDate = board.getCreatedDate().toLocalDate();
            this.updatedDate = board.getUpdatedDate().toLocalDate();
        }

    }

    @ApiModel(value = "게시글 추가 요청")
    @NoArgsConstructor
    @Getter
    @Setter
    public static class AddReqDTO {

        @ApiModelProperty(value = "게시글 타입")
        private BoardType boardType;
        @ApiModelProperty(value = "게시글 썸네일")
        private String boardThumbnail;
        @ApiModelProperty(value = "게시글 제목")
        private String boardTitle;
        @ApiModelProperty(value = "게시글 내용")
        private String boardContent;

        public Board toEntity(User user) {

            return Board.builder()
                    .user(user)
                    .type(boardType)
                    .thumbnail(boardThumbnail)
                    .title(boardTitle)
                    .content(boardContent)
                    .role(user.getRole())
                    .build();
        }
    }

    @ApiModel(value = "게시글 수정 요청")
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ModifyReqDTO {

        @ApiModelProperty(value = "게시글 썸네일")
        private String boardThumbnail;
        @ApiModelProperty(value = "게시글 제목")
        private String boardTitle;
        @ApiModelProperty(value = "게시글 내용")
        private String boardContent;
    }

    @ApiModel(value = "회원 여행후기 목록 조회 요청")
    @NoArgsConstructor
    @Getter
    @Setter
    public static class ReviewListResDTO {

        @ApiModelProperty(value = "게시글 아이디")
        private Long boardId;
        @ApiModelProperty(value = "게시글 썸네일")
        private String boardThumbnail;
        @ApiModelProperty(value = "게시글 제목")
        private String boardTitle;
        @ApiModelProperty(value = "게시글 생성일자")
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate createdDate;

        public ReviewListResDTO(Board board) {
            this.boardId = board.getBoardId();
            this.boardThumbnail = board.getThumbnail();
            this.boardTitle = board.getTitle();
            this.createdDate = board.getCreatedDate().toLocalDate();
        }
    }
}

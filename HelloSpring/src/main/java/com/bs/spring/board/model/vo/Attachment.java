package com.bs.spring.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attachment {
	private int attachmentNo;
	private Board board;
	private String originalFilename;
	private String renamedFilename;
	private Date uploadDate;
	private int downloadCount;
}

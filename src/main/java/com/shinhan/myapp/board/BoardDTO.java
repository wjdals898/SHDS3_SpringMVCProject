package com.shinhan.myapp.board;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter@ToString
public class BoardDTO {
	int bno;
	String title;
	String content;
	String writer;
	String pic;
	Date create_date;
}

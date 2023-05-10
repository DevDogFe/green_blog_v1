package com.tenco.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, length = 100)
	private String title;
	@Lob // 대용량 데이터
	private String content;
	@ColumnDefault("0")
	private Integer count;
	@ManyToOne // Board와 User 관계 -> N:1
	@JoinColumn(name = "user_id")
	private User user;
	
	/**
	 * Board에는 Reply에 대한 정보가 없음
	 * Board 정보를 가지고 올 때 댓글 정보도 가지고 와야 한다면?
	 * Board와 Reply의 관계는 1:N 
	 */
	/**
	 *  board테이블에 reply_id 컬럼이 필요없다면 mappedBy 설정
	 *  object가 생성될 때(데이터 가져올 때) 알아서 join처리해서 데이터만 가져옴
	 */
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY) // fetch -> LAZY가 기본값
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createdDate;
}

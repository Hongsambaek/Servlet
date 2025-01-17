package kr.or.ddit.comm.service;

import java.util.Collection;

import javax.servlet.http.Part;

import kr.or.ddit.comm.vo.AtchFileDetailVO;
import kr.or.ddit.comm.vo.AtchFileVO;

public interface IAtchFileService {
	
	
	/**
	 * 첨부파일목록을 저장하기 위한 메소드
	 * @param parts Part 객체를 담은 컬렉션
	 * @return atchFileId를 담은 AtchFileVO 객체
	 */
	public AtchFileVO saveAtchFileList(Collection<Part> parts);
	
	
	/**
	 * 첨부파일 목록을 조회하기 위한 메소드
	 * @param fileVO atchFileId를 담은 AtchFileVO객체
	 * @return 첨부파일 목록을 담은 AtchFileVO객체
	 */
	public AtchFileVO getAtchFile(AtchFileVO fileVO);
	
	
	/**
	 * 첨부파일 상세정보를 조회하기 위한 메소드(다운르도 처리시 사용)
	 * @param atchFileDetailVO atchFileId 및 fileSn을 담은 객체
	 * @return 상세정보를 담은 AtchFileDetailVO 객체
	 */
	public AtchFileDetailVO getAtchFileDetail(AtchFileDetailVO atchFileDetailVO);
	
	
	
}

package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

public interface IMemberService {

	/**
	 * membervo에 담겨진 회원정보를 등록하기 위한 메서드
	 * 
	 * @param mv 회원정보를 담은 membervo 객체
	 * @return 작업이 성공하면 1, 실패하면 0 반환됨
	 */
	public int registerMember(MemberVO mv);

	/**
	 * membervo에 담겨진 회원정보를 수정하기 위한 메서드
	 * 
	 * @param mv 회원정보를 담은 membervo 객체
	 * @return 작업이 성공하면 1, 실패하면 0 반환됨
	 */
	public int modifyMember(MemberVO mv);



	public boolean checkMember(String memId);
	
	
	
	/**
	 * 해당 회원의 상세정보를 조회하기 위한 메소드
	 * @param mv 회원 ID, memId
	 * @return 해당 회원의 상세정보를 담은 MemberVO객체 리턴함
	 */
	public MemberVO getMember(String memId);



	public int removeMember(String memId);

	/**
	 * 모든 회원정보를 가져오는 위한 메서드
	 * 
	 * @return 모든 회원 정보를 담은 list 객체
	 * 
	 */
	public List<MemberVO> getTotalMember();


	public List<MemberVO> searchMember(MemberVO mv);

}

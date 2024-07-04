package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/* 
    실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해
   서비스에 전달하기 위한 DAO Interface
*/

public interface IMemberDao {
	
	/**
	 * membervo에 담겨진 회원정보를 db에 insert하기 위한 메서드
	 * @param mv 회원정보를 담은 membervo 객체
	 * @return db작업이 성공하면 1, 실패하면 0 반환됨
	 */
   public int insertMember(MemberVO mv);
   
   /**
	 * membervo에 담겨진 회원정보를 db에 update하기 위한 메서드
	 * @param mv 회원정보를 담은 membervo 객체
	 * @return db작업이 성공하면 1, 실패하면 0 반환됨
	 */
   public int updateMember(MemberVO mv);
   

   public boolean checkMember(String memId);
   
   

   /**
	 * 해당 회원의 상세정보를 조회하기 위한 메소드
	 * @param mv 회원 ID, memId
	 * @return 해당 회원의 상세정보를 담은 MemberVO객체 리턴함
	 */
   public MemberVO getMember(String memId);

   
   public int deleteMember(String memId);
   

    public List<MemberVO> getAllMember();
    
    

    public List<MemberVO> searchMember(MemberVO mv);
   
}
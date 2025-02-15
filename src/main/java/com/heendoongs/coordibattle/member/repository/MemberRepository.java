package com.heendoongs.coordibattle.member.repository;

import com.heendoongs.coordibattle.coordi.domain.Coordi;
import com.heendoongs.coordibattle.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 멤버 레포지토리
 * @author 조희정
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.27  	조희정       최초 생성
 * 2024.07.27  	조희정       existsByLoginId, existsByNickname 메소드 추가
 * 2024.07.28  	조희정       findByLoginId 메소드 추가
 * 2024.07.31   조희정       findMyCoordiByLikesDesc 메소드 추가
 * </pre>
 */

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 삭제되지 않은 회원 중 이미 존재하는 아이디인지 확인
     * @param loginId
     * @return
     */
    @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.loginId = :loginId AND m.deleted = 'N'")
    Boolean existsByLoginId(String loginId);

    /**
     * 삭제되지 않은 회원 중 이미 존재하는 닉네임인지 확인
     * @param nickname
     * @return
     */
    @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.nickname = :nickname AND m.deleted = 'N'")
    Boolean existsByNickname(String nickname);

    /**
     * 삭제되지 않은 회원 중 로그인 아이디로 회원 검색
     * @param loginId
     * @return
     */
    Member findByLoginIdAndDeleted(String loginId, Character deleted);

    /**
     * 내 코디 리스트 조회
     * @param pageable
     * @return
     */
    @Query("SELECT c " +
            "FROM Coordi c " +
            "LEFT JOIN c.member m " +
            "WHERE m.id = :memberId " +
            "ORDER BY c.createDate DESC")
    Page<Coordi> findMyCoordiByLikesDesc(Pageable pageable, Long memberId);

}

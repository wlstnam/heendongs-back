package com.heendoongs.coordibattle.member.exception;

import com.heendoongs.coordibattle.global.exception.BaseExceptionType;
import org.springframework.http.HttpStatus;

/**
 * 멤버 예외처리 타입
 * @author 조희정
 * @since 2024.07.27
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.07.27  	조희정       최초 생성
 * </pre>
 */
public enum MemberExceptionType implements BaseExceptionType {
    // 회원가입 에러코드
    ALREADY_EXIST_LOGIN_ID(600, HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),
    ALREADY_EXIST_NICKNAME(600, HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다.");
//    WRONG_PASSWORD(601,HttpStatus.BAD_REQUEST, "비밀번호가 잘못되었습니다."),
//    NOT_FOUND_MEMBER(602, HttpStatus.NOT_FOUND, "회원 정보가 없습니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    MemberExceptionType(int errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}

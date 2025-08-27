package com.mycompany.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class StudentValidator2 implements Validator {

	
	@Override
	// 검증 할 객체(StudentDto)의 클래스 타입 정보 가져오기
	public boolean supports(Class<?> clazz) {
		return StudentDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StudentDto studentDto = (StudentDto) target;
		String id = studentDto.getId();
		String pw = studentDto.getPw();
		String name = studentDto.getName();
		int age = studentDto.getAge();
		if (id == null || id.strip().isEmpty()) {
			System.out.println("에러가 발생된 아이디 : " + id);
			errors.rejectValue("id", "id가 공란입니다."); // 1. 에러가 발생한 필드 변수 2. 에러코드
		}

		//		if (pw == null || pw.strip().isEmpty()) {
//			System.out.println("에러가 발생된 비밀번호 : " + pw);
//			errors.rejectValue("pw", "pw가 공란입니다."); // 1. 에러가 발생한 필드 변수 2. 에러코드
//		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pw", "ValidationUtils로 pw 유효성 체크");
		

		if (name == null || name.strip().isEmpty()) {
			System.out.println("에러가 발생된 이름 : " + name);
			errors.rejectValue("name", "name가 공란입니다."); // 1. 에러가 발생한 필드 변수 2. 에러코드
		}
		if (age > 19 || age < 0) {
			System.out.println("에러된 발생된 나이 : " + age);
			errors.rejectValue("age", "19세 초과 가입 불가"); // 1. 에러가 발생한 필드 변수 2. 에러코드
		}
	}

}

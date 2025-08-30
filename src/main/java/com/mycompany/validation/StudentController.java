package com.mycompany.validation;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {
	
	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}
	@RequestMapping(value = "/joinOk")	
	public String joinOk(StudentDto studentDto, Model model, BindingResult result) {
        // BindingResult : Validation을 수행할 때 발생하는 오류를 보관하는 객체
        // bindingResult.hasErrors()가 true이면 bindingResult.getFieldErrors()를 통해 필드 오류 목록을 가져와 모델에 담거나, 사용자에게 에러 메시지를 표시
		// error 조건 : null 값, 나이 19세 초과
		
		StudentValidator validator = new StudentValidator();
		validator.validate(studentDto, result); // 타겟을 dto로 받고 결과는 result
		if (result.hasErrors()) { // result의 결과 유무 true, false
			// 에러 발생 수
			System.out.println("에러 발생 개수: " + result.getFieldErrorCount());
			
			// 해당 필드의 에러 내용 가져오기 (예시)
			//FieldError fieldError = result.getFieldError("id");
			//System.out.println(fieldError.getCode());
			
			System.out.println("----------------------------------------------");
			
			// 모든 에러를 리스트로 반환
			String errorMsg = null;
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError error : fieldErrors) {
				System.out.println("에러 발생한 항목 : " + error.getField());
				System.out.println("에러 발생 코드명 : " + error.getCode());
				errorMsg = error.getCode();
			}
			model.addAttribute("error", errorMsg); // errorMsg 하나만 담아짐 (해결책 생각해보기)
			return "join";
		} else {
			model.addAttribute("studentDto", studentDto);
			return "joinOk";
		}
		
	}
}

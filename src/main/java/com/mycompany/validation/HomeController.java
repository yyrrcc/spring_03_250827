package com.mycompany.validation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}
	@RequestMapping(value = "/joinOk") // 유저가 입력한 값이 유효한 값인지 validation 유효성 체크
	public String joinOk(StudentDto studentDto, Model model, BindingResult result) {
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
			model.addAttribute("error", errorMsg);
			return "join";
		} else {
			model.addAttribute("studentDto", studentDto);
			return "joinOk";
		}
		
	}
	
	// 의존성에 유효성 추가
	@RequestMapping(value = "/join2")
	public String join2() {
		return "join2";
	}
	@RequestMapping(value = "/joinOk")
	public String joinOk2(@Valid StudentDto studentDto, Model model, BindingResult result) {
		if (result.hasErrors()) {
			return "join";
		} else {
			model.addAttribute("studentDto", studentDto);
			return "joinOk";
		}
	}
	
	@InitBinder // 직접 호출하는 대신 애노테이션으로 대체
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new StudentValidator());
	}
}

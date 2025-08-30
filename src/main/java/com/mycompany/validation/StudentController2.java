package com.mycompany.validation;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController2 {
	
	@InitBinder // 직접 호출하는 대신 애노테이션으로 대체
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new StudentValidator());
	}
	
	@RequestMapping(value = "/join2")
	public String join2() {
		return "join2";
	}
	
	@RequestMapping(value = "/joinOk2")
	// @Valid 또는 @Validated : Validator(기본 또는 커스텀)를 실제로 실행하도록 트리거하는 역할
	public String joinOk2(@Validated StudentDto studentDto, BindingResult result, Model model) {
        // 커스텀 Validator가 자동 실행됨
        if (result.hasErrors()) {
        	System.out.println("에러 발생 개수: " + result.getFieldErrorCount());
        	
        	String errorMsg = null;
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError error : fieldErrors) {
				System.out.println("에러 발생한 항목 : " + error.getField());
				System.out.println("에러 발생 코드명 : " + error.getCode());
				errorMsg = error.getCode();
			}
			model.addAttribute("error", errorMsg); // errorMsg 하나만 담아짐 (해결책 생각해보기)
            return "join2";
        }
        model.addAttribute("studentDto", studentDto);
        return "joinOk";
	}
}
package com.sohu.controller;

import com.sohu.dto.StudentDTO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Controller层
 */
@Controller
@RequestMapping(value = "/v3")
public class StudentController {
    private Validator validator;

    public StudentController() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     * http://localhost:8080/lesson03/v3/student/validate
     *
     * @param studentDTO
     * @return
     */
    @RequestMapping(value = "/student/validate", method = RequestMethod.POST)
    @ResponseBody
    public String queryEmployee(@RequestBody StudentDTO studentDTO, BindingResult result){
        System.out.println(studentDTO);
        Set<ConstraintViolation<StudentDTO>> violations = validator.validate(studentDTO);
        for (ConstraintViolation<StudentDTO> violation : violations)
        {
            String propertyPath = violation.getPropertyPath().toString();
            System.out.println(propertyPath);
            String message = violation.getMessage();
            System.out.println(message);
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError
            result.addError(
                    new FieldError("StudentDTO",
                    propertyPath,
                    "Invalid "+ propertyPath + "(" + message + ")"));
        }

        if(result.hasErrors()){
            return result.toString();
        }
        return "success";
    }
}

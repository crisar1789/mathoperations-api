package com.appgate.mathoperations.api.rest;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appgate.mathoperations.api.business.MathOperationsInt;
import com.appgate.mathoperations.api.rq.AddOperandRq;
import com.appgate.mathoperations.api.rq.CalculateRq;
import com.appgate.mathoperations.api.rq.SessionRq;
import com.appgate.mathoperations.api.rs.AddOperandRs;
import com.appgate.mathoperations.api.rs.CalculateRs;
import com.appgate.mathoperations.api.rs.Response;
import com.appgate.mathoperations.api.rs.SessionRs;
import com.appgate.mathoperations.api.util.ConstantsUtil;
import com.appgate.mathoperations.api.util.ValidatorUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "MathOperations", produces = "application/json")
public class MathOperationsRest {

	private final MathOperationsInt mathOperations;
	
	@Autowired
	public MathOperationsRest(MathOperationsInt mathOperations) {
		this.mathOperations = mathOperations;
	}
	
	@ApiOperation(value = "Generate Session Id", tags = "session")
	@PostMapping(value = "generate-session-id")
	@ResponseBody
	public Response<SessionRs> generateSessionId(@Context HttpServletRequest request,
				@ApiParam(value = "Object with user data") @RequestBody SessionRq sessionRq) {
		
		try {
			ValidatorUtil.validateRequest(sessionRq);
			
			return mathOperations.generateSessionId(sessionRq);
		} catch (ConstraintViolationException e) {
			return new Response<>(400, ConstantsUtil.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Add operand", tags = "operand")
	@PostMapping(value = "add-operand")
	@ResponseBody
	public Response<AddOperandRs> addOperand(@Context HttpServletRequest request,
				@ApiParam(value = "Object with data") @RequestBody AddOperandRq addOperandRq) {
		
		try {
			ValidatorUtil.validateRequest(addOperandRq);
			
			return mathOperations.addOperand(addOperandRq);
		} catch (ConstraintViolationException e) {
			return new Response<>(400, ConstantsUtil.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "calclate value", tags = "calculate")
	@PostMapping(value = "calculate")
	@ResponseBody
	public Response<CalculateRs> calculate(@Context HttpServletRequest request,
				@ApiParam(value = "Object with data") @RequestBody CalculateRq calculateRq) {
		
		try {
			ValidatorUtil.validateRequest(calculateRq);
			
			return mathOperations.calculate(calculateRq);
		} catch (ConstraintViolationException e) {
			return new Response<>(400, ConstantsUtil.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "Hola", tags = "MathOperations")
	@GetMapping(value = "hola")
	@ResponseBody
	public Response<String> hola() {
		
		try {
			ClassPathResource path = new ClassPathResource("data.json", this.getClass().getClassLoader());
			File f = path.getFile();
			return new Response<>(200, "Success Transaction", "hola");
		} catch (Exception e) {
			return new Response<>(400, "Bad Request");
		}
	}
}

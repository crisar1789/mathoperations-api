package com.appgate.mathoperations.api.util;

import java.util.Optional;

import com.appgate.mathoperations.api.model.User;
import com.appgate.mathoperations.api.model.UserRepository;
import com.appgate.mathoperations.api.security.Security;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

public class Utility {

	/**
	 * Método encargado de generar la información para la App
	 * @param apiName Nombre de la App
	 * @return Información de la App para documentación Swagger
	 */
	public static ApiInfo apiInfo(String apiName) {
		Contact contact  = new Contact(ConstantsUtil.COMPANY_NAME, 
				ConstantsUtil.COMPANY_WEB_SITE, 
				ConstantsUtil.COMPANY_EMAIL);
		
		return new ApiInfoBuilder().title(ConstantsUtil.COMPANY_NAME)
				.description(apiName + " Api Rest")
				.contact(contact)
				.build();
	}
	
	/**
	 * Método encargado de validar si dos sesiones son iguales o no
	 * @param sessionRequest Sesión que llega desde una parte externa
	 * @param sessionUser Sesión guardada en base de datos
	 * @return true, si las sesiones son iguales
	 * 		   false, si las sesiones no son iguales
	 */
	public static boolean validarSession(String sessionRequest, String sessionUser) {
		if (sessionRequest.equals(sessionUser)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	/**
	 * Método encargado de consultar un usuario en base de datos
	 * @param sessionId Sesión que llega desde una parte externa
	 * @param key Llave para desencriptar sessionId
	 * @param userRepository Repository para búsqueda de usuario
	 * @return Objeto con la información del usuario
	 * @throws Exception, Si ocurre un error durante el proceso
	 */
	public static User consultarUsuario(String sessionId, String key, UserRepository userRepository) throws Exception {
		String decryptSessionId = Security.desencriptar(sessionId, key);
		String[] datos = decryptSessionId.split("\\.");
		
		Optional<User> optional = userRepository.findById(Long.valueOf(datos[1]));
		return optional.get();
	}
}

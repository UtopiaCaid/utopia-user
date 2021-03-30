package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
			value = HttpStatus.CONFLICT,
			reason = "ERROR: Record already exists"
		)

public class RecordAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8394529715051838184L;

	
}

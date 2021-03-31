package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
			value = HttpStatus.BAD_REQUEST,
			reason = "ERROR: Bad Update Request"
		)

public class RecordUpdateException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2804584265142912975L;


}

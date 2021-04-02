package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
			value = HttpStatus.UNPROCESSABLE_ENTITY,
			reason = "Could not find requested records"
)

public class RecordHasDependenciesException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7385672627657687120L;


	
}

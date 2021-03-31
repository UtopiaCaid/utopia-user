package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
			value = HttpStatus.NOT_FOUND,
			reason = "Could not created requested record"
		)

public class RecordCreationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1254562355546506759L;


	
}

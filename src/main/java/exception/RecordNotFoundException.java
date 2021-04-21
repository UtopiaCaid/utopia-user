package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
			value = HttpStatus.NOT_FOUND,
			reason = "Could not find requested records"
)

public class RecordNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7463132903894299287L;
	
}

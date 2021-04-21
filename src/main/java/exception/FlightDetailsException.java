package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
		value = HttpStatus.BAD_REQUEST,
		reason = "Error when updating flight"
	)

public class FlightDetailsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8657390405931861036L;

}

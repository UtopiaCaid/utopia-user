package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
		value = HttpStatus.BAD_REQUEST,
		reason = "Flight id does not exist, could not be deleted"
	)

public class FlightDeletionException extends RuntimeException{

}

package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
			value = HttpStatus.NOT_FOUND,
			reason = "Flights was returned with no data entries"
		)

public class RecordNotFoundException extends RuntimeException{
	
}

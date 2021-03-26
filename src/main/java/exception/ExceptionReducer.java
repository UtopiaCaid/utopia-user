package exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ExceptionReducer {

	public static ResponseEntity<Object> handleException(Exception ex) throws Exception {
		HttpHeaders headers = new HttpHeaders();
		if (ex instanceof RecordNotFoundException) {
			HttpStatus status = HttpStatus.FOUND;
			headers.add("Custom Record Not Found Exception", "No Records Found");
			return new ResponseEntity<>("No Records Found", headers, status);
		}
		if (ex instanceof RecordCreationException) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			headers.add("Record Creation Exception", "Record Could Not Be Created");
			return new ResponseEntity<>("BAD_REQUEST:Record Could Not Be Created", headers, status);
		}
		if (ex instanceof RecordAlreadyExistsException) {
			HttpStatus status = HttpStatus.CONFLICT;
			headers.add("Custom Record Not Found Exception", "No Records Found");
			return new ResponseEntity<>("CONFLICT: Record already exists", headers, status);
		}
		if (ex instanceof RecordForeignKeyConstraintException) {
			HttpStatus status = HttpStatus.FAILED_DEPENDENCY;
			headers.add("Foreign Key Error", "No Records Found");
			return new ResponseEntity<>("FAILED_DEPENDENCY: Invalid Associated Aircraft Type", 
					headers, status);
		}
		if (ex instanceof RecordUpdateException) {
			HttpStatus status = HttpStatus.BAD_REQUEST;
			headers.add("Unknown Key", "No Records Found");
			return new ResponseEntity<>("BAD_REQUEST: Unknown Primary Key", 
					headers, status);
		}
		throw ex;
	}
}

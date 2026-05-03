package tn.enicarthage.gestionpfa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Intercepter les erreurs de validation (@Valid) que nous venons d'activer
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage); // Ex: {"email": "l'email est invalide"}
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // 2. Intercepter les RuntimeException (utilisées dans vos Services pour les règles métier)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erreur de règle métier ou introuvable",
                ex.getMessage() // Ex: "Le PFA doit être terminé avant d'être noté."
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // 3. Intercepter toutes les autres erreurs imprévues (pour éviter de planter le serveur)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erreur Interne du Serveur",
                "Une erreur inattendue s'est produite. Veuillez contacter l'administrateur."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
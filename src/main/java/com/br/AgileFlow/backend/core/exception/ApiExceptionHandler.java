package com.br.AgileFlow.backend.core.exception;

import org.hibernate.TransientPropertyValueException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String msgUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String msgDev = ex.getMostSpecificCause().toString();

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .build();

        return handleExceptionInternal(ex, problem, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Problem problem = createProblems(ex.getBindingResult());
        return handleExceptionInternal(ex, problem, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ EmptyResultDataAccessException.class })
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
                                                                       WebRequest request) {
        String msgUser = messageSource.getMessage("recurso.nao-encontrado", null,
                LocaleContextHolder.getLocale());
        String msgDev = ex.getMostSpecificCause().toString();

        Problem problem = Problem.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                        WebRequest request) {
        String msgUser = messageSource.getMessage("recurso.operacao-nao-permitida", null,
                LocaleContextHolder.getLocale());
        String msgDev = ex.getMostSpecificCause().toString(); // Indicação de outro aluno

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ DateTimeParseException.class })
    public ResponseEntity<Object> handleInvalidDataAccessResourceUsageException(DateTimeParseException ex,
                                                                                WebRequest request) {
        String msgUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String msgDev = ex.getMessage().toString();

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ InvalidDataAccessResourceUsageException.class })
    public ResponseEntity<Object> handleInvalidDataAccessResourceUsageException(
            InvalidDataAccessResourceUsageException ex, WebRequest request) {
        String msgUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String msgDev = ex.getMostSpecificCause().toString(); // Indicação de outro aluno

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ GenericException.class })
    public ResponseEntity<Object> handleGenericException(
            GenericException ex, WebRequest request) {
        String msgUser = messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale());
        String msgDev = ex.getMessage(); // Indicação de outro aluno

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ JpaObjectRetrievalFailureException.class })
    public ResponseEntity<Object> handleJpaObjectRetrievalFailureException(JpaObjectRetrievalFailureException ex,
                                                                           WebRequest request) {
        String msgUser = messageSource.getMessage("recurso.operacao-nao-permitida", null,
                LocaleContextHolder.getLocale());
        String msgDev = ex.getMostSpecificCause().toString(); // Indicação de outro aluno

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ IllegalStateException.class })
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        String msgUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String msgDev = ex.getLocalizedMessage().toString(); // Indicação de outro aluno

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        String msgUser = messageSource.getMessage("recurso.operacao-nao-permitida", null,
                LocaleContextHolder.getLocale());
        String msgDev = ex.getLocalizedMessage().toString();

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ TransientPropertyValueException.class })
    public ResponseEntity<Object> handleTransientPropertyValueException(TransientPropertyValueException ex,
                                                                        WebRequest request) {
        String msgUser = messageSource.getMessage("recurso.operacao-nao-permitida", null,
                LocaleContextHolder.getLocale());
        String msgDev = ex.getLocalizedMessage().toString(); // Indicação de outro aluno

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(AuthCredentialException.class)
    public ResponseEntity<Object> handleCredentialException(AuthCredentialException ex, WebRequest request) {
        String msgUser = messageSource.getMessage("recurso.nao-autorizado", null,
                LocaleContextHolder.getLocale());
        String msgDev = ex.getLocalizedMessage();

        Problem problem = Problem.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        String msgUser = messageSource.getMessage("recurso.nao-encontrado", null,
                LocaleContextHolder.getLocale());
        String msgDev = ex.getLocalizedMessage();

        Problem problem = Problem.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<Object> handleMultipartException(MultipartException ex, WebRequest request) {
        String msgUser = messageSource.getMessage("recurso.operacao-nao-permitida", null,
                LocaleContextHolder.getLocale());
        String msgDev = ex.getLocalizedMessage();

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalException(Exception ex, WebRequest request) {
        String msgUser = messageSource.getMessage("recurso.erro-interno", null,
                LocaleContextHolder.getLocale());
        String msgDev = ex.getLocalizedMessage();

        Problem problem = Problem.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatusCode status, WebRequest request) {
        String msgUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String msgDev = ex.getMostSpecificCause().toString();

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
                                                               HttpStatusCode status, WebRequest request) {
        String msgUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String msgDev = ex.toString();

        Problem problem = Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private Problem createProblems(BindingResult bindingResult) {
        String msgUser = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String msgDev = bindingResult.toString();

        List<Problem.Object> objects = new ArrayList<>();
        for (FieldError fieldProblemr : bindingResult.getFieldErrors()) {
            String name = fieldProblemr.getField();
            String description = messageSource.getMessage(fieldProblemr, LocaleContextHolder.getLocale());
            var obj = Problem.Object
                    .builder()
                    .name(name)
                    .userMessage(name + " " + description)
                    .build();

            objects.add(obj);
        }

        return Problem.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .objects(objects)
                .build();
    }

    @ExceptionHandler({AuthorizationDeniedException.class})
    public ResponseEntity<Object> handleAuthorizationDeniedException(AuthorizationDeniedException ex, WebRequest request) {
        String msgUser = messageSource.getMessage("recurso.acesso-negado", null,
                LocaleContextHolder.getLocale());
        String msgDev = ex.getLocalizedMessage();

        Problem problem = Problem.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .userMessage(msgUser)
                .detail(msgDev)
                .timestamp(LocalDateTime.now())
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

}

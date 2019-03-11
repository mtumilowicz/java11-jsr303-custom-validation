[![Build Status](https://travis-ci.com/mtumilowicz/java11-jsr303-custom-validation.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-jsr303-custom-validation)
# java11-jsr303-custom-validation

# preface
* https://github.com/mtumilowicz/java-bean-validation2
* JSR303 - https://beanvalidation.org/2.0/spec/
* validating data is a common task that occurs throughout 
    an application, from the presentation layer to the persistence 
    layer
* often the same validation logic is implemented in each layer, 
    proving to be time consuming and error-prone
* JSR defines a metadata model and API for JavaBean validation
* default metadata source is annotations, with the ability to override and extend the metadata through the use 
    of XML validation descriptors
* **declaring these constraints does not 
    automatically cause their validation when the concerned 
    methods are invoked** - it’s the responsibility of 
    an integration layer (Spring, Hibernate...) to trigger 
    the validation of the constraints using a method interceptor, dynamic proxy 
    or similar
* **bean Validation itself doesn’t trigger the evaluation 
    of method constraints**
* just annotating any methods or constructors with 
    parameter or return value constraints doesn’t automatically 
    enforce these constraints, just as annotating any fields or 
    properties with bean constraints doesn’t enforce these either
* `@Valid` - tells Spring to pass the object to a 
    `Validator` before doing anything else
* if the input class contains a field with another complex 
    type that should be validated, this field, needs to be 
    annotated with `@Valid`

# project description
* we have `User`
    ```
    public class User {
        String name;
    }
    ```
    and we want to validate if a name is a proper word
* regex for a word character (`[a-zA-Z_0-9]`): `[\w]`
* JSR303
    * annotation
        ```
        @Constraint(validatedBy = WordValidator.class)
        @Target(ElementType.FIELD)
        @Retention(RetentionPolicy.RUNTIME)
        public @interface Word {
            String message() default "is not a proper word!";
            Class<?>[] groups() default {};
            Class<? extends Payload>[] payload() default {};
        }
        ```
        * we need `groups()` and `payload()`, otherwise Internal Server Error, 500 - 
            `"HV000074: com.example.validation.app.domain.patterns.Word contains Constraint annotation, but does not contain 
            a groups parameter."`
    * validator
        ```
        class WordValidator implements ConstraintValidator<Word, String> {
        
            private static final Predicate<String> PATTERN = Pattern.compile("[\\w]+").asMatchPredicate();
        
            @Override
            public void initialize(Word word) {
            }
        
            @Override
            public boolean isValid(String word,
                                   ConstraintValidatorContext cxt) {
                return isNull(word) || PATTERN.test(word);
            }
        
        }
        ```
* endpoint
    ```
    @RestController
    public class UserController {
        
        @PostMapping("/")
        public ResponseEntity<User> register(@RequestBody @Valid User user) {
            return ResponseEntity.ok(user);
        }
    }
    ```
* final version of User
    ```
    @Value
    @Builder
    public class User {
    
        @NotBlank
        @Word
        String name;
    
        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public User(@JsonProperty("name") String name) {
            this.name = name;
        }
    }
    ```
    * `@JsonCreator` and dedicated constructor are to prevent
        _JSON parse error: cannot deserialize from Object value_,
        note that `User` is immutable
* swagger: http://localhost:8080/swagger-ui.html#
    * `users/register`, response for a user with `%%%` as name:
        ```
        {
          "timestamp": "2019-03-11T09:03:53.983+0000",
          "status": 400,
          "error": "Bad Request",
          "errors": [
            {
              "codes": [
                "Word.user.name",
                "Word.name",
                "Word.java.lang.String",
                "Word"
              ],
              "arguments": [
                {
                  "codes": [
                    "user.name",
                    "name"
                  ],
                  "arguments": null,
                  "defaultMessage": "name",
                  "code": "name"
                }
              ],
              "defaultMessage": "is not a proper word!",
              "objectName": "user",
              "field": "name",
              "rejectedValue": "%%%",
              "bindingFailure": false,
              "code": "Word"
            }
          ],
          "message": "Validation failed for object='user'. Error count: 1",
          "path": "/users/register"
        }
        ```
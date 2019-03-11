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
* note that declaring these constraints does not 
automatically cause their validation when the concerned 
methods are invoked
* it’s the responsibility of an integration layer to 
trigger the validation of the constraints using a 
method interceptor, dynamic proxy or similar
* bean Validation itself doesn’t trigger the evaluation 
of method constraints
* just annotating any methods or constructors with 
parameter or return value constraints doesn’t automatically 
enforce these constraints, just as annotating any fields or 
properties with bean constraints doesn’t enforce these either

# project description

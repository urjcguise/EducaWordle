Documentación API
=================

API and SDK Documentation
-------------------------

Version: 1.0.0

* * *

Documentación de la API del Wordle educativo

AuthController
==============

login
=====

Inicio de sesión de la aplicación

Introduciendo el usuario comprueba si existe en la aplicación e inicia sesión

  

    /auth/login

### Usage and SDK Samples

*   [Curl](#examples-AuthController-login-0-curl)
*   [Java](#examples-AuthController-login-0-java)
*   [Android](#examples-AuthController-login-0-android)
*   [Obj-C](#examples-AuthController-login-0-objc)
*   [JavaScript](#examples-AuthController-login-0-javascript)
*   [C#](#examples-AuthController-login-0-csharp)
*   [PHP](#examples-AuthController-login-0-php)
*   [Perl](#examples-AuthController-login-0-perl)
*   [Python](#examples-AuthController-login-0-python)

    curl -X POST\
    -H "Accept: application/json"\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/auth/login"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.AuthControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class AuthControllerApiExample {
    
        public static void main(String[] args) {
            
            AuthControllerApi apiInstance = new AuthControllerApi();
            LoginUser body = ; // LoginUser | Datos del usuario para iniciar sesión
            try {
                array[JwtDto] result = apiInstance.login(body);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling AuthControllerApi#login");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.AuthControllerApi;
    
    public class AuthControllerApiExample {
    
        public static void main(String[] args) {
            AuthControllerApi apiInstance = new AuthControllerApi();
            LoginUser body = ; // LoginUser | Datos del usuario para iniciar sesión
            try {
                array[JwtDto] result = apiInstance.login(body);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling AuthControllerApi#login");
                e.printStackTrace();
            }
        }
    }

    LoginUser *body = ; // Datos del usuario para iniciar sesión (optional)
    
    AuthControllerApi *apiInstance = [[AuthControllerApi alloc] init];
    
    // Inicio de sesión de la aplicación
    [apiInstance loginWith:body
                  completionHandler: ^(array[JwtDto] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.AuthControllerApi()
    var opts = { 
      'body':  // {{LoginUser}} Datos del usuario para iniciar sesión
    };
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.login(opts, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class loginExample
        {
            public void main()
            {
    
                var apiInstance = new AuthControllerApi();
                var body = new LoginUser(); // LoginUser | Datos del usuario para iniciar sesión (optional) 
    
                try
                {
                    // Inicio de sesión de la aplicación
                    array[JwtDto] result = apiInstance.login(body);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling AuthControllerApi.login: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiAuthControllerApi();
    $body = ; // LoginUser | Datos del usuario para iniciar sesión
    
    try {
        $result = $api_instance->login($body);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling AuthControllerApi->login: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::AuthControllerApi;
    
    my $api_instance = WWW::SwaggerClient::AuthControllerApi->new();
    my $body = WWW::SwaggerClient::Object::LoginUser->new(); # LoginUser | Datos del usuario para iniciar sesión
    
    eval { 
        my $result = $api_instance->login(body => $body);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling AuthControllerApi->login: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.AuthControllerApi()
    body =  # LoginUser | Datos del usuario para iniciar sesión (optional)
    
    try: 
        # Inicio de sesión de la aplicación
        api_response = api_instance.login(body=body)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling AuthControllerApi->login: %s\n" % e)

Parameters
----------

Body parameters

Name

Description

body

$(document).ready(function() { var schemaWrapper = { "description" : "Datos del usuario para iniciar sesión", "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/LoginUser" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_login\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 200 - Inicio de sesión satisfactorio

*   [Schema](#responses-login-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Inicio de sesión satisfactorio", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/JwtDto" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-login-200-schema-data').val(stringify(schema)); var result = $('#responses-login-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 400 - Campos mal introducidos

* * *

register
========

Registro de la aplicación

Adds an item to the system

  

    /auth/newUser

### Usage and SDK Samples

*   [Curl](#examples-AuthController-register-0-curl)
*   [Java](#examples-AuthController-register-0-java)
*   [Android](#examples-AuthController-register-0-android)
*   [Obj-C](#examples-AuthController-register-0-objc)
*   [JavaScript](#examples-AuthController-register-0-javascript)
*   [C#](#examples-AuthController-register-0-csharp)
*   [PHP](#examples-AuthController-register-0-php)
*   [Perl](#examples-AuthController-register-0-perl)
*   [Python](#examples-AuthController-register-0-python)

    curl -X POST\
    -H "Accept: application/json"\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/auth/newUser"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.AuthControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class AuthControllerApiExample {
    
        public static void main(String[] args) {
            
            AuthControllerApi apiInstance = new AuthControllerApi();
            NewUser body = ; // NewUser | 
            try {
                'Integer' result = apiInstance.register(body);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling AuthControllerApi#register");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.AuthControllerApi;
    
    public class AuthControllerApiExample {
    
        public static void main(String[] args) {
            AuthControllerApi apiInstance = new AuthControllerApi();
            NewUser body = ; // NewUser | 
            try {
                'Integer' result = apiInstance.register(body);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling AuthControllerApi#register");
                e.printStackTrace();
            }
        }
    }

    NewUser *body = ; //  (optional)
    
    AuthControllerApi *apiInstance = [[AuthControllerApi alloc] init];
    
    // Registro de la aplicación
    [apiInstance registerWith:body
                  completionHandler: ^('Integer' output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.AuthControllerApi()
    var opts = { 
      'body':  // {{NewUser}} 
    };
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.register(opts, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class registerExample
        {
            public void main()
            {
    
                var apiInstance = new AuthControllerApi();
                var body = new NewUser(); // NewUser |  (optional) 
    
                try
                {
                    // Registro de la aplicación
                    'Integer' result = apiInstance.register(body);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling AuthControllerApi.register: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiAuthControllerApi();
    $body = ; // NewUser | 
    
    try {
        $result = $api_instance->register($body);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling AuthControllerApi->register: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::AuthControllerApi;
    
    my $api_instance = WWW::SwaggerClient::AuthControllerApi->new();
    my $body = WWW::SwaggerClient::Object::NewUser->new(); # NewUser | 
    
    eval { 
        my $result = $api_instance->register(body => $body);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling AuthControllerApi->register: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.AuthControllerApi()
    body =  # NewUser |  (optional)
    
    try: 
        # Registro de la aplicación
        api_response = api_instance.register(body=body)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling AuthControllerApi->register: %s\n" % e)

Parameters
----------

Body parameters

Name

Description

body

$(document).ready(function() { var schemaWrapper = { "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/NewUser" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_register\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 201 - Registro de usuario satisfactorio

*   [Schema](#responses-register-201-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Registro de usuario satisfactorio", "content" : { "application/json" : { "schema" : { "type" : "integer", "example" : 1, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-register-201-schema-data').val(stringify(schema)); var result = $('#responses-register-201-schema-201'); result.empty(); result.append(view.render()); });

### Status: 400 - Campos mal introducidos o usuario ya existente en el sistema

* * *

CompetitionController
=====================

apiCompetitionsAddStudentsByExcelCompetitionIdPost
==================================================

Añade estudiantes a una competición mediante un archivo Excel.

Añade estudiantes a una competición específica utilizando un archivo Excel. Requiere rol de Administrador o de Profesor.

  

    /api/competitions/addStudentsByExcel/{competitionId}

### Usage and SDK Samples

*   [Curl](#examples-CompetitionController-apiCompetitionsAddStudentsByExcelCompetitionIdPost-0-curl)
*   [Java](#examples-CompetitionController-apiCompetitionsAddStudentsByExcelCompetitionIdPost-0-java)
*   [Android](#examples-CompetitionController-apiCompetitionsAddStudentsByExcelCompetitionIdPost-0-android)
*   [Obj-C](#examples-CompetitionController-apiCompetitionsAddStudentsByExcelCompetitionIdPost-0-objc)
*   [JavaScript](#examples-CompetitionController-apiCompetitionsAddStudentsByExcelCompetitionIdPost-0-javascript)
*   [C#](#examples-CompetitionController-apiCompetitionsAddStudentsByExcelCompetitionIdPost-0-csharp)
*   [PHP](#examples-CompetitionController-apiCompetitionsAddStudentsByExcelCompetitionIdPost-0-php)
*   [Perl](#examples-CompetitionController-apiCompetitionsAddStudentsByExcelCompetitionIdPost-0-perl)
*   [Python](#examples-CompetitionController-apiCompetitionsAddStudentsByExcelCompetitionIdPost-0-python)

    curl -X POST\
    -H "Content-Type: multipart/form-data"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/competitions/addStudentsByExcel/{competitionId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.CompetitionControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class CompetitionControllerApiExample {
    
        public static void main(String[] args) {
            
            CompetitionControllerApi apiInstance = new CompetitionControllerApi();
            byte[] file = file_example; // byte[] | 
            Long competitionId = 789; // Long | ID de la competición.
            try {
                apiInstance.apiCompetitionsAddStudentsByExcelCompetitionIdPost(file, competitionId);
            } catch (ApiException e) {
                System.err.println("Exception when calling CompetitionControllerApi#apiCompetitionsAddStudentsByExcelCompetitionIdPost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.CompetitionControllerApi;
    
    public class CompetitionControllerApiExample {
    
        public static void main(String[] args) {
            CompetitionControllerApi apiInstance = new CompetitionControllerApi();
            byte[] file = file_example; // byte[] | 
            Long competitionId = 789; // Long | ID de la competición.
            try {
                apiInstance.apiCompetitionsAddStudentsByExcelCompetitionIdPost(file, competitionId);
            } catch (ApiException e) {
                System.err.println("Exception when calling CompetitionControllerApi#apiCompetitionsAddStudentsByExcelCompetitionIdPost");
                e.printStackTrace();
            }
        }
    }

    byte[] *file = file_example; // 
    Long *competitionId = 789; // ID de la competición.
    
    CompetitionControllerApi *apiInstance = [[CompetitionControllerApi alloc] init];
    
    // Añade estudiantes a una competición mediante un archivo Excel.
    [apiInstance apiCompetitionsAddStudentsByExcelCompetitionIdPostWith:file
        competitionId:competitionId
                  completionHandler: ^(NSError* error) {
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.CompetitionControllerApi()
    var file = file_example; // {{byte[]}} 
    var competitionId = 789; // {{Long}} ID de la competición.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully.');
      }
    };
    api.apiCompetitionsAddStudentsByExcelCompetitionIdPost(filecompetitionId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiCompetitionsAddStudentsByExcelCompetitionIdPostExample
        {
            public void main()
            {
    
                var apiInstance = new CompetitionControllerApi();
                var file = file_example;  // byte[] | 
                var competitionId = 789;  // Long | ID de la competición.
    
                try
                {
                    // Añade estudiantes a una competición mediante un archivo Excel.
                    apiInstance.apiCompetitionsAddStudentsByExcelCompetitionIdPost(file, competitionId);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling CompetitionControllerApi.apiCompetitionsAddStudentsByExcelCompetitionIdPost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiCompetitionControllerApi();
    $file = file_example; // byte[] | 
    $competitionId = 789; // Long | ID de la competición.
    
    try {
        $api_instance->apiCompetitionsAddStudentsByExcelCompetitionIdPost($file, $competitionId);
    } catch (Exception $e) {
        echo 'Exception when calling CompetitionControllerApi->apiCompetitionsAddStudentsByExcelCompetitionIdPost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::CompetitionControllerApi;
    
    my $api_instance = WWW::SwaggerClient::CompetitionControllerApi->new();
    my $file = file_example; # byte[] | 
    my $competitionId = 789; # Long | ID de la competición.
    
    eval { 
        $api_instance->apiCompetitionsAddStudentsByExcelCompetitionIdPost(file => $file, competitionId => $competitionId);
    };
    if ($@) {
        warn "Exception when calling CompetitionControllerApi->apiCompetitionsAddStudentsByExcelCompetitionIdPost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.CompetitionControllerApi()
    file = file_example # byte[] | 
    competitionId = 789 # Long | ID de la competición.
    
    try: 
        # Añade estudiantes a una competición mediante un archivo Excel.
        api_instance.api_competitions_add_students_by_excel_competition_id_post(file, competitionId)
    except ApiException as e:
        print("Exception when calling CompetitionControllerApi->apiCompetitionsAddStudentsByExcelCompetitionIdPost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

competitionId\*

Long (int64)

ID de la competición.

Required

Form parameters

Name

Description

file\*

byte\[\] (binary)

Required

Responses
---------

### Status: 200 - Archivo procesado correctamente.

### Status: 400 - Solicitud incorrecta o error al procesar el archivo.

### Status: 404 - Competición no encontrada.

* * *

apiCompetitionsDeleteCompetitionCompetitionIdDelete
===================================================

Elimina una competición por su ID.

Elimina una competición específica utilizando su ID. Requiere el rol de Administrador o de Profesor

  

    /api/competitions/deleteCompetition/{competitionId}

### Usage and SDK Samples

*   [Curl](#examples-CompetitionController-apiCompetitionsDeleteCompetitionCompetitionIdDelete-0-curl)
*   [Java](#examples-CompetitionController-apiCompetitionsDeleteCompetitionCompetitionIdDelete-0-java)
*   [Android](#examples-CompetitionController-apiCompetitionsDeleteCompetitionCompetitionIdDelete-0-android)
*   [Obj-C](#examples-CompetitionController-apiCompetitionsDeleteCompetitionCompetitionIdDelete-0-objc)
*   [JavaScript](#examples-CompetitionController-apiCompetitionsDeleteCompetitionCompetitionIdDelete-0-javascript)
*   [C#](#examples-CompetitionController-apiCompetitionsDeleteCompetitionCompetitionIdDelete-0-csharp)
*   [PHP](#examples-CompetitionController-apiCompetitionsDeleteCompetitionCompetitionIdDelete-0-php)
*   [Perl](#examples-CompetitionController-apiCompetitionsDeleteCompetitionCompetitionIdDelete-0-perl)
*   [Python](#examples-CompetitionController-apiCompetitionsDeleteCompetitionCompetitionIdDelete-0-python)

    curl -X DELETE\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/competitions/deleteCompetition/{competitionId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.CompetitionControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class CompetitionControllerApiExample {
    
        public static void main(String[] args) {
            
            CompetitionControllerApi apiInstance = new CompetitionControllerApi();
            Long competitionId = 789; // Long | ID de la competición a eliminar.
            try {
                apiInstance.apiCompetitionsDeleteCompetitionCompetitionIdDelete(competitionId);
            } catch (ApiException e) {
                System.err.println("Exception when calling CompetitionControllerApi#apiCompetitionsDeleteCompetitionCompetitionIdDelete");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.CompetitionControllerApi;
    
    public class CompetitionControllerApiExample {
    
        public static void main(String[] args) {
            CompetitionControllerApi apiInstance = new CompetitionControllerApi();
            Long competitionId = 789; // Long | ID de la competición a eliminar.
            try {
                apiInstance.apiCompetitionsDeleteCompetitionCompetitionIdDelete(competitionId);
            } catch (ApiException e) {
                System.err.println("Exception when calling CompetitionControllerApi#apiCompetitionsDeleteCompetitionCompetitionIdDelete");
                e.printStackTrace();
            }
        }
    }

    Long *competitionId = 789; // ID de la competición a eliminar.
    
    CompetitionControllerApi *apiInstance = [[CompetitionControllerApi alloc] init];
    
    // Elimina una competición por su ID.
    [apiInstance apiCompetitionsDeleteCompetitionCompetitionIdDeleteWith:competitionId
                  completionHandler: ^(NSError* error) {
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.CompetitionControllerApi()
    var competitionId = 789; // {{Long}} ID de la competición a eliminar.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully.');
      }
    };
    api.apiCompetitionsDeleteCompetitionCompetitionIdDelete(competitionId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiCompetitionsDeleteCompetitionCompetitionIdDeleteExample
        {
            public void main()
            {
    
                var apiInstance = new CompetitionControllerApi();
                var competitionId = 789;  // Long | ID de la competición a eliminar.
    
                try
                {
                    // Elimina una competición por su ID.
                    apiInstance.apiCompetitionsDeleteCompetitionCompetitionIdDelete(competitionId);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling CompetitionControllerApi.apiCompetitionsDeleteCompetitionCompetitionIdDelete: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiCompetitionControllerApi();
    $competitionId = 789; // Long | ID de la competición a eliminar.
    
    try {
        $api_instance->apiCompetitionsDeleteCompetitionCompetitionIdDelete($competitionId);
    } catch (Exception $e) {
        echo 'Exception when calling CompetitionControllerApi->apiCompetitionsDeleteCompetitionCompetitionIdDelete: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::CompetitionControllerApi;
    
    my $api_instance = WWW::SwaggerClient::CompetitionControllerApi->new();
    my $competitionId = 789; # Long | ID de la competición a eliminar.
    
    eval { 
        $api_instance->apiCompetitionsDeleteCompetitionCompetitionIdDelete(competitionId => $competitionId);
    };
    if ($@) {
        warn "Exception when calling CompetitionControllerApi->apiCompetitionsDeleteCompetitionCompetitionIdDelete: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.CompetitionControllerApi()
    competitionId = 789 # Long | ID de la competición a eliminar.
    
    try: 
        # Elimina una competición por su ID.
        api_instance.api_competitions_delete_competition_competition_id_delete(competitionId)
    except ApiException as e:
        print("Exception when calling CompetitionControllerApi->apiCompetitionsDeleteCompetitionCompetitionIdDelete: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

competitionId\*

Long (int64)

ID de la competición a eliminar.

Required

Responses
---------

### Status: 200 - Competición eliminada con éxito.

### Status: 401 - No autorizado.

### Status: 404 - Competición no encontrada.

* * *

apiCompetitionsGetCompetitionsProfessorNameGet
==============================================

Obtiene las competiciones creadas por un profesor.

Obtiene la lista de competiciones creadas por un profesor utilizando su nombre de usuario. Requiere el rol de Administrador o de Profesor

  

    /api/competitions/getCompetitions/{professorName}

### Usage and SDK Samples

*   [Curl](#examples-CompetitionController-apiCompetitionsGetCompetitionsProfessorNameGet-0-curl)
*   [Java](#examples-CompetitionController-apiCompetitionsGetCompetitionsProfessorNameGet-0-java)
*   [Android](#examples-CompetitionController-apiCompetitionsGetCompetitionsProfessorNameGet-0-android)
*   [Obj-C](#examples-CompetitionController-apiCompetitionsGetCompetitionsProfessorNameGet-0-objc)
*   [JavaScript](#examples-CompetitionController-apiCompetitionsGetCompetitionsProfessorNameGet-0-javascript)
*   [C#](#examples-CompetitionController-apiCompetitionsGetCompetitionsProfessorNameGet-0-csharp)
*   [PHP](#examples-CompetitionController-apiCompetitionsGetCompetitionsProfessorNameGet-0-php)
*   [Perl](#examples-CompetitionController-apiCompetitionsGetCompetitionsProfessorNameGet-0-perl)
*   [Python](#examples-CompetitionController-apiCompetitionsGetCompetitionsProfessorNameGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/competitions/getCompetitions/{professorName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.CompetitionControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class CompetitionControllerApiExample {
    
        public static void main(String[] args) {
            
            CompetitionControllerApi apiInstance = new CompetitionControllerApi();
            String professorName = professorName_example; // String | Nombre de usuario del profesor para obtener sus competiciones.
            try {
                array[Competition] result = apiInstance.apiCompetitionsGetCompetitionsProfessorNameGet(professorName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling CompetitionControllerApi#apiCompetitionsGetCompetitionsProfessorNameGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.CompetitionControllerApi;
    
    public class CompetitionControllerApiExample {
    
        public static void main(String[] args) {
            CompetitionControllerApi apiInstance = new CompetitionControllerApi();
            String professorName = professorName_example; // String | Nombre de usuario del profesor para obtener sus competiciones.
            try {
                array[Competition] result = apiInstance.apiCompetitionsGetCompetitionsProfessorNameGet(professorName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling CompetitionControllerApi#apiCompetitionsGetCompetitionsProfessorNameGet");
                e.printStackTrace();
            }
        }
    }

    String *professorName = professorName_example; // Nombre de usuario del profesor para obtener sus competiciones.
    
    CompetitionControllerApi *apiInstance = [[CompetitionControllerApi alloc] init];
    
    // Obtiene las competiciones creadas por un profesor.
    [apiInstance apiCompetitionsGetCompetitionsProfessorNameGetWith:professorName
                  completionHandler: ^(array[Competition] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.CompetitionControllerApi()
    var professorName = professorName_example; // {{String}} Nombre de usuario del profesor para obtener sus competiciones.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiCompetitionsGetCompetitionsProfessorNameGet(professorName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiCompetitionsGetCompetitionsProfessorNameGetExample
        {
            public void main()
            {
    
                var apiInstance = new CompetitionControllerApi();
                var professorName = professorName_example;  // String | Nombre de usuario del profesor para obtener sus competiciones.
    
                try
                {
                    // Obtiene las competiciones creadas por un profesor.
                    array[Competition] result = apiInstance.apiCompetitionsGetCompetitionsProfessorNameGet(professorName);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling CompetitionControllerApi.apiCompetitionsGetCompetitionsProfessorNameGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiCompetitionControllerApi();
    $professorName = professorName_example; // String | Nombre de usuario del profesor para obtener sus competiciones.
    
    try {
        $result = $api_instance->apiCompetitionsGetCompetitionsProfessorNameGet($professorName);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling CompetitionControllerApi->apiCompetitionsGetCompetitionsProfessorNameGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::CompetitionControllerApi;
    
    my $api_instance = WWW::SwaggerClient::CompetitionControllerApi->new();
    my $professorName = professorName_example; # String | Nombre de usuario del profesor para obtener sus competiciones.
    
    eval { 
        my $result = $api_instance->apiCompetitionsGetCompetitionsProfessorNameGet(professorName => $professorName);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling CompetitionControllerApi->apiCompetitionsGetCompetitionsProfessorNameGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.CompetitionControllerApi()
    professorName = professorName_example # String | Nombre de usuario del profesor para obtener sus competiciones.
    
    try: 
        # Obtiene las competiciones creadas por un profesor.
        api_response = api_instance.api_competitions_get_competitions_professor_name_get(professorName)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling CompetitionControllerApi->apiCompetitionsGetCompetitionsProfessorNameGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

professorName\*

String

Nombre de usuario del profesor para obtener sus competiciones.

Required

Responses
---------

### Status: 200 - Lista de competiciones obtenida con éxito.

*   [Schema](#responses-apiCompetitionsGetCompetitionsProfessorNameGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de competiciones obtenida con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/Competition" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiCompetitionsGetCompetitionsProfessorNameGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiCompetitionsGetCompetitionsProfessorNameGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 401 - No autorizado.

### Status: 404 - Profesor no encontrado.

* * *

apiCompetitionsGetStudentsCompetitionIdGet
==========================================

Obtiene los estudiantes inscritos en una competición.

Obtiene la lista de estudiantes inscritos en una competición específica, utilizando su ID.

  

    /api/competitions/getStudents/{competitionId}

### Usage and SDK Samples

*   [Curl](#examples-CompetitionController-apiCompetitionsGetStudentsCompetitionIdGet-0-curl)
*   [Java](#examples-CompetitionController-apiCompetitionsGetStudentsCompetitionIdGet-0-java)
*   [Android](#examples-CompetitionController-apiCompetitionsGetStudentsCompetitionIdGet-0-android)
*   [Obj-C](#examples-CompetitionController-apiCompetitionsGetStudentsCompetitionIdGet-0-objc)
*   [JavaScript](#examples-CompetitionController-apiCompetitionsGetStudentsCompetitionIdGet-0-javascript)
*   [C#](#examples-CompetitionController-apiCompetitionsGetStudentsCompetitionIdGet-0-csharp)
*   [PHP](#examples-CompetitionController-apiCompetitionsGetStudentsCompetitionIdGet-0-php)
*   [Perl](#examples-CompetitionController-apiCompetitionsGetStudentsCompetitionIdGet-0-perl)
*   [Python](#examples-CompetitionController-apiCompetitionsGetStudentsCompetitionIdGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/competitions/getStudents/{competitionId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.CompetitionControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class CompetitionControllerApiExample {
    
        public static void main(String[] args) {
            
            CompetitionControllerApi apiInstance = new CompetitionControllerApi();
            Long competitionId = 789; // Long | ID de la competición para obtener los estudiantes.
            try {
                array[UserStudent] result = apiInstance.apiCompetitionsGetStudentsCompetitionIdGet(competitionId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling CompetitionControllerApi#apiCompetitionsGetStudentsCompetitionIdGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.CompetitionControllerApi;
    
    public class CompetitionControllerApiExample {
    
        public static void main(String[] args) {
            CompetitionControllerApi apiInstance = new CompetitionControllerApi();
            Long competitionId = 789; // Long | ID de la competición para obtener los estudiantes.
            try {
                array[UserStudent] result = apiInstance.apiCompetitionsGetStudentsCompetitionIdGet(competitionId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling CompetitionControllerApi#apiCompetitionsGetStudentsCompetitionIdGet");
                e.printStackTrace();
            }
        }
    }

    Long *competitionId = 789; // ID de la competición para obtener los estudiantes.
    
    CompetitionControllerApi *apiInstance = [[CompetitionControllerApi alloc] init];
    
    // Obtiene los estudiantes inscritos en una competición.
    [apiInstance apiCompetitionsGetStudentsCompetitionIdGetWith:competitionId
                  completionHandler: ^(array[UserStudent] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.CompetitionControllerApi()
    var competitionId = 789; // {{Long}} ID de la competición para obtener los estudiantes.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiCompetitionsGetStudentsCompetitionIdGet(competitionId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiCompetitionsGetStudentsCompetitionIdGetExample
        {
            public void main()
            {
    
                var apiInstance = new CompetitionControllerApi();
                var competitionId = 789;  // Long | ID de la competición para obtener los estudiantes.
    
                try
                {
                    // Obtiene los estudiantes inscritos en una competición.
                    array[UserStudent] result = apiInstance.apiCompetitionsGetStudentsCompetitionIdGet(competitionId);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling CompetitionControllerApi.apiCompetitionsGetStudentsCompetitionIdGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiCompetitionControllerApi();
    $competitionId = 789; // Long | ID de la competición para obtener los estudiantes.
    
    try {
        $result = $api_instance->apiCompetitionsGetStudentsCompetitionIdGet($competitionId);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling CompetitionControllerApi->apiCompetitionsGetStudentsCompetitionIdGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::CompetitionControllerApi;
    
    my $api_instance = WWW::SwaggerClient::CompetitionControllerApi->new();
    my $competitionId = 789; # Long | ID de la competición para obtener los estudiantes.
    
    eval { 
        my $result = $api_instance->apiCompetitionsGetStudentsCompetitionIdGet(competitionId => $competitionId);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling CompetitionControllerApi->apiCompetitionsGetStudentsCompetitionIdGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.CompetitionControllerApi()
    competitionId = 789 # Long | ID de la competición para obtener los estudiantes.
    
    try: 
        # Obtiene los estudiantes inscritos en una competición.
        api_response = api_instance.api_competitions_get_students_competition_id_get(competitionId)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling CompetitionControllerApi->apiCompetitionsGetStudentsCompetitionIdGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

competitionId\*

Long (int64)

ID de la competición para obtener los estudiantes.

Required

Responses
---------

### Status: 200 - Lista de estudiantes obtenida con éxito.

*   [Schema](#responses-apiCompetitionsGetStudentsCompetitionIdGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de estudiantes obtenida con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/UserStudent" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiCompetitionsGetStudentsCompetitionIdGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiCompetitionsGetStudentsCompetitionIdGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Competición no encontrada.

* * *

apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost
==============================================================

Asigna un estudiante a una competición.

Asigna un estudiante a una competición específica utilizando sus IDs. Requiere el rol de Administrador o de Profesor

  

    /api/competitions/linkStudentToCompetition/{competitionId}/{userId}

### Usage and SDK Samples

*   [Curl](#examples-CompetitionController-apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost-0-curl)
*   [Java](#examples-CompetitionController-apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost-0-java)
*   [Android](#examples-CompetitionController-apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost-0-android)
*   [Obj-C](#examples-CompetitionController-apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost-0-objc)
*   [JavaScript](#examples-CompetitionController-apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost-0-javascript)
*   [C#](#examples-CompetitionController-apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost-0-csharp)
*   [PHP](#examples-CompetitionController-apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost-0-php)
*   [Perl](#examples-CompetitionController-apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost-0-perl)
*   [Python](#examples-CompetitionController-apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost-0-python)

    curl -X POST\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/competitions/linkStudentToCompetition/{competitionId}/{userId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.CompetitionControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class CompetitionControllerApiExample {
    
        public static void main(String[] args) {
            
            CompetitionControllerApi apiInstance = new CompetitionControllerApi();
            Long competitionId = 789; // Long | ID de la competición.
            Long userId = 789; // Long | ID del estudiante.
            try {
                apiInstance.apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost(competitionId, userId);
            } catch (ApiException e) {
                System.err.println("Exception when calling CompetitionControllerApi#apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.CompetitionControllerApi;
    
    public class CompetitionControllerApiExample {
    
        public static void main(String[] args) {
            CompetitionControllerApi apiInstance = new CompetitionControllerApi();
            Long competitionId = 789; // Long | ID de la competición.
            Long userId = 789; // Long | ID del estudiante.
            try {
                apiInstance.apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost(competitionId, userId);
            } catch (ApiException e) {
                System.err.println("Exception when calling CompetitionControllerApi#apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost");
                e.printStackTrace();
            }
        }
    }

    Long *competitionId = 789; // ID de la competición.
    Long *userId = 789; // ID del estudiante.
    
    CompetitionControllerApi *apiInstance = [[CompetitionControllerApi alloc] init];
    
    // Asigna un estudiante a una competición.
    [apiInstance apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPostWith:competitionId
        userId:userId
                  completionHandler: ^(NSError* error) {
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.CompetitionControllerApi()
    var competitionId = 789; // {{Long}} ID de la competición.
    var userId = 789; // {{Long}} ID del estudiante.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully.');
      }
    };
    api.apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost(competitionId, userId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPostExample
        {
            public void main()
            {
    
                var apiInstance = new CompetitionControllerApi();
                var competitionId = 789;  // Long | ID de la competición.
                var userId = 789;  // Long | ID del estudiante.
    
                try
                {
                    // Asigna un estudiante a una competición.
                    apiInstance.apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost(competitionId, userId);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling CompetitionControllerApi.apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiCompetitionControllerApi();
    $competitionId = 789; // Long | ID de la competición.
    $userId = 789; // Long | ID del estudiante.
    
    try {
        $api_instance->apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost($competitionId, $userId);
    } catch (Exception $e) {
        echo 'Exception when calling CompetitionControllerApi->apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::CompetitionControllerApi;
    
    my $api_instance = WWW::SwaggerClient::CompetitionControllerApi->new();
    my $competitionId = 789; # Long | ID de la competición.
    my $userId = 789; # Long | ID del estudiante.
    
    eval { 
        $api_instance->apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost(competitionId => $competitionId, userId => $userId);
    };
    if ($@) {
        warn "Exception when calling CompetitionControllerApi->apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.CompetitionControllerApi()
    competitionId = 789 # Long | ID de la competición.
    userId = 789 # Long | ID del estudiante.
    
    try: 
        # Asigna un estudiante a una competición.
        api_instance.api_competitions_link_student_to_competition_competition_id_user_id_post(competitionId, userId)
    except ApiException as e:
        print("Exception when calling CompetitionControllerApi->apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

competitionId\*

Long (int64)

ID de la competición.

Required

userId\*

Long (int64)

ID del estudiante.

Required

Responses
---------

### Status: 200 - Estudiante asignado correctamente.

### Status: 404 - Competición o usuario no encontrado.

* * *

apiCompetitionsNewCompetitionProfessorNamePost
==============================================

Crea una nueva competición.

Crea una nueva competición asociada a un profesor por su nombre de usuario. Requiere el rol de Administrador o de Profesor.

  

    /api/competitions/newCompetition/{professorName}

### Usage and SDK Samples

*   [Curl](#examples-CompetitionController-apiCompetitionsNewCompetitionProfessorNamePost-0-curl)
*   [Java](#examples-CompetitionController-apiCompetitionsNewCompetitionProfessorNamePost-0-java)
*   [Android](#examples-CompetitionController-apiCompetitionsNewCompetitionProfessorNamePost-0-android)
*   [Obj-C](#examples-CompetitionController-apiCompetitionsNewCompetitionProfessorNamePost-0-objc)
*   [JavaScript](#examples-CompetitionController-apiCompetitionsNewCompetitionProfessorNamePost-0-javascript)
*   [C#](#examples-CompetitionController-apiCompetitionsNewCompetitionProfessorNamePost-0-csharp)
*   [PHP](#examples-CompetitionController-apiCompetitionsNewCompetitionProfessorNamePost-0-php)
*   [Perl](#examples-CompetitionController-apiCompetitionsNewCompetitionProfessorNamePost-0-perl)
*   [Python](#examples-CompetitionController-apiCompetitionsNewCompetitionProfessorNamePost-0-python)

    curl -X POST\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/competitions/newCompetition/{professorName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.CompetitionControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class CompetitionControllerApiExample {
    
        public static void main(String[] args) {
            
            CompetitionControllerApi apiInstance = new CompetitionControllerApi();
            CompetitionInput body = ; // CompetitionInput | 
            String professorName = professorName_example; // String | Nombre de usuario del profesor que crea la competición.
            try {
                apiInstance.apiCompetitionsNewCompetitionProfessorNamePost(body, professorName);
            } catch (ApiException e) {
                System.err.println("Exception when calling CompetitionControllerApi#apiCompetitionsNewCompetitionProfessorNamePost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.CompetitionControllerApi;
    
    public class CompetitionControllerApiExample {
    
        public static void main(String[] args) {
            CompetitionControllerApi apiInstance = new CompetitionControllerApi();
            CompetitionInput body = ; // CompetitionInput | 
            String professorName = professorName_example; // String | Nombre de usuario del profesor que crea la competición.
            try {
                apiInstance.apiCompetitionsNewCompetitionProfessorNamePost(body, professorName);
            } catch (ApiException e) {
                System.err.println("Exception when calling CompetitionControllerApi#apiCompetitionsNewCompetitionProfessorNamePost");
                e.printStackTrace();
            }
        }
    }

    CompetitionInput *body = ; // 
    String *professorName = professorName_example; // Nombre de usuario del profesor que crea la competición.
    
    CompetitionControllerApi *apiInstance = [[CompetitionControllerApi alloc] init];
    
    // Crea una nueva competición.
    [apiInstance apiCompetitionsNewCompetitionProfessorNamePostWith:body
        professorName:professorName
                  completionHandler: ^(NSError* error) {
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.CompetitionControllerApi()
    var body = ; // {{CompetitionInput}} 
    var professorName = professorName_example; // {{String}} Nombre de usuario del profesor que crea la competición.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully.');
      }
    };
    api.apiCompetitionsNewCompetitionProfessorNamePost(bodyprofessorName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiCompetitionsNewCompetitionProfessorNamePostExample
        {
            public void main()
            {
    
                var apiInstance = new CompetitionControllerApi();
                var body = new CompetitionInput(); // CompetitionInput | 
                var professorName = professorName_example;  // String | Nombre de usuario del profesor que crea la competición.
    
                try
                {
                    // Crea una nueva competición.
                    apiInstance.apiCompetitionsNewCompetitionProfessorNamePost(body, professorName);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling CompetitionControllerApi.apiCompetitionsNewCompetitionProfessorNamePost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiCompetitionControllerApi();
    $body = ; // CompetitionInput | 
    $professorName = professorName_example; // String | Nombre de usuario del profesor que crea la competición.
    
    try {
        $api_instance->apiCompetitionsNewCompetitionProfessorNamePost($body, $professorName);
    } catch (Exception $e) {
        echo 'Exception when calling CompetitionControllerApi->apiCompetitionsNewCompetitionProfessorNamePost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::CompetitionControllerApi;
    
    my $api_instance = WWW::SwaggerClient::CompetitionControllerApi->new();
    my $body = WWW::SwaggerClient::Object::CompetitionInput->new(); # CompetitionInput | 
    my $professorName = professorName_example; # String | Nombre de usuario del profesor que crea la competición.
    
    eval { 
        $api_instance->apiCompetitionsNewCompetitionProfessorNamePost(body => $body, professorName => $professorName);
    };
    if ($@) {
        warn "Exception when calling CompetitionControllerApi->apiCompetitionsNewCompetitionProfessorNamePost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.CompetitionControllerApi()
    body =  # CompetitionInput | 
    professorName = professorName_example # String | Nombre de usuario del profesor que crea la competición.
    
    try: 
        # Crea una nueva competición.
        api_instance.api_competitions_new_competition_professor_name_post(body, professorName)
    except ApiException as e:
        print("Exception when calling CompetitionControllerApi->apiCompetitionsNewCompetitionProfessorNamePost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

professorName\*

String

Nombre de usuario del profesor que crea la competición.

Required

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/CompetitionInput" } } }, "required" : true }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiCompetitionsNewCompetitionProfessorNamePost\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 201 - Competición creada con éxito.

### Status: 401 - No autorizado.

### Status: 404 - Profesor no encontrado.

### Status: 409 - Nombre de competición ya utilizado.

* * *

ContestController
=================

apiContestsCompetitionNameContestsGet
=====================================

Obtiene los concursos de una competición por su nombre.

Obtiene la lista de concursos asociados a una competición específica utilizando su nombre.

  

    /api/contests/{competitionName}/contests

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsCompetitionNameContestsGet-0-curl)
*   [Java](#examples-ContestController-apiContestsCompetitionNameContestsGet-0-java)
*   [Android](#examples-ContestController-apiContestsCompetitionNameContestsGet-0-android)
*   [Obj-C](#examples-ContestController-apiContestsCompetitionNameContestsGet-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsCompetitionNameContestsGet-0-javascript)
*   [C#](#examples-ContestController-apiContestsCompetitionNameContestsGet-0-csharp)
*   [PHP](#examples-ContestController-apiContestsCompetitionNameContestsGet-0-php)
*   [Perl](#examples-ContestController-apiContestsCompetitionNameContestsGet-0-perl)
*   [Python](#examples-ContestController-apiContestsCompetitionNameContestsGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/{competitionName}/contests"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            String competitionName = competitionName_example; // String | Nombre de la competición.
            try {
                array[Contest] result = apiInstance.apiContestsCompetitionNameContestsGet(competitionName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsCompetitionNameContestsGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            String competitionName = competitionName_example; // String | Nombre de la competición.
            try {
                array[Contest] result = apiInstance.apiContestsCompetitionNameContestsGet(competitionName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsCompetitionNameContestsGet");
                e.printStackTrace();
            }
        }
    }

    String *competitionName = competitionName_example; // Nombre de la competición.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Obtiene los concursos de una competición por su nombre.
    [apiInstance apiContestsCompetitionNameContestsGetWith:competitionName
                  completionHandler: ^(array[Contest] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var competitionName = competitionName_example; // {{String}} Nombre de la competición.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiContestsCompetitionNameContestsGet(competitionName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsCompetitionNameContestsGetExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var competitionName = competitionName_example;  // String | Nombre de la competición.
    
                try
                {
                    // Obtiene los concursos de una competición por su nombre.
                    array[Contest] result = apiInstance.apiContestsCompetitionNameContestsGet(competitionName);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsCompetitionNameContestsGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $competitionName = competitionName_example; // String | Nombre de la competición.
    
    try {
        $result = $api_instance->apiContestsCompetitionNameContestsGet($competitionName);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsCompetitionNameContestsGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $competitionName = competitionName_example; # String | Nombre de la competición.
    
    eval { 
        my $result = $api_instance->apiContestsCompetitionNameContestsGet(competitionName => $competitionName);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsCompetitionNameContestsGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    competitionName = competitionName_example # String | Nombre de la competición.
    
    try: 
        # Obtiene los concursos de una competición por su nombre.
        api_response = api_instance.api_contests_competition_name_contests_get(competitionName)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsCompetitionNameContestsGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

competitionName\*

String

Nombre de la competición.

Required

Responses
---------

### Status: 200 - Lista de concursos obtenida con éxito.

*   [Schema](#responses-apiContestsCompetitionNameContestsGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de concursos obtenida con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/Contest" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiContestsCompetitionNameContestsGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiContestsCompetitionNameContestsGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Competición no encontrada.

* * *

apiContestsContestIdContestGet
==============================

Obtiene un concurso por su ID.

Obtiene la información de un concurso específico utilizando su ID.

  

    /api/contests/{contestId}/contest

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsContestIdContestGet-0-curl)
*   [Java](#examples-ContestController-apiContestsContestIdContestGet-0-java)
*   [Android](#examples-ContestController-apiContestsContestIdContestGet-0-android)
*   [Obj-C](#examples-ContestController-apiContestsContestIdContestGet-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsContestIdContestGet-0-javascript)
*   [C#](#examples-ContestController-apiContestsContestIdContestGet-0-csharp)
*   [PHP](#examples-ContestController-apiContestsContestIdContestGet-0-php)
*   [Perl](#examples-ContestController-apiContestsContestIdContestGet-0-perl)
*   [Python](#examples-ContestController-apiContestsContestIdContestGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/{contestId}/contest"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            try {
                Contest result = apiInstance.apiContestsContestIdContestGet(contestId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsContestIdContestGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            try {
                Contest result = apiInstance.apiContestsContestIdContestGet(contestId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsContestIdContestGet");
                e.printStackTrace();
            }
        }
    }

    Long *contestId = 789; // ID del concurso.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Obtiene un concurso por su ID.
    [apiInstance apiContestsContestIdContestGetWith:contestId
                  completionHandler: ^(Contest output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var contestId = 789; // {{Long}} ID del concurso.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiContestsContestIdContestGet(contestId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsContestIdContestGetExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var contestId = 789;  // Long | ID del concurso.
    
                try
                {
                    // Obtiene un concurso por su ID.
                    Contest result = apiInstance.apiContestsContestIdContestGet(contestId);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsContestIdContestGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $contestId = 789; // Long | ID del concurso.
    
    try {
        $result = $api_instance->apiContestsContestIdContestGet($contestId);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsContestIdContestGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $contestId = 789; # Long | ID del concurso.
    
    eval { 
        my $result = $api_instance->apiContestsContestIdContestGet(contestId => $contestId);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsContestIdContestGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    contestId = 789 # Long | ID del concurso.
    
    try: 
        # Obtiene un concurso por su ID.
        api_response = api_instance.api_contests_contest_id_contest_get(contestId)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsContestIdContestGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso.

Required

Responses
---------

### Status: 200 - Concurso obtenido con éxito.

*   [Schema](#responses-apiContestsContestIdContestGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Concurso obtenido con éxito.", "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/Contest" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiContestsContestIdContestGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiContestsContestIdContestGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Concurso no encontrado.

* * *

apiContestsCopyContestOldContestIdPost
======================================

Copia un concurso existente.

Crea una copia de un concurso existente utilizando su ID. Requiere el rol de Administrador o de Profesor

  

    /api/contests/copyContest/{oldContestId}

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsCopyContestOldContestIdPost-0-curl)
*   [Java](#examples-ContestController-apiContestsCopyContestOldContestIdPost-0-java)
*   [Android](#examples-ContestController-apiContestsCopyContestOldContestIdPost-0-android)
*   [Obj-C](#examples-ContestController-apiContestsCopyContestOldContestIdPost-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsCopyContestOldContestIdPost-0-javascript)
*   [C#](#examples-ContestController-apiContestsCopyContestOldContestIdPost-0-csharp)
*   [PHP](#examples-ContestController-apiContestsCopyContestOldContestIdPost-0-php)
*   [Perl](#examples-ContestController-apiContestsCopyContestOldContestIdPost-0-perl)
*   [Python](#examples-ContestController-apiContestsCopyContestOldContestIdPost-0-python)

    curl -X POST\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/copyContest/{oldContestId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long oldContestId = 789; // Long | ID del concurso a copiar.
            try {
                apiInstance.apiContestsCopyContestOldContestIdPost(oldContestId);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsCopyContestOldContestIdPost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long oldContestId = 789; // Long | ID del concurso a copiar.
            try {
                apiInstance.apiContestsCopyContestOldContestIdPost(oldContestId);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsCopyContestOldContestIdPost");
                e.printStackTrace();
            }
        }
    }

    Long *oldContestId = 789; // ID del concurso a copiar.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Copia un concurso existente.
    [apiInstance apiContestsCopyContestOldContestIdPostWith:oldContestId
                  completionHandler: ^(NSError* error) {
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var oldContestId = 789; // {{Long}} ID del concurso a copiar.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully.');
      }
    };
    api.apiContestsCopyContestOldContestIdPost(oldContestId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsCopyContestOldContestIdPostExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var oldContestId = 789;  // Long | ID del concurso a copiar.
    
                try
                {
                    // Copia un concurso existente.
                    apiInstance.apiContestsCopyContestOldContestIdPost(oldContestId);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsCopyContestOldContestIdPost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $oldContestId = 789; // Long | ID del concurso a copiar.
    
    try {
        $api_instance->apiContestsCopyContestOldContestIdPost($oldContestId);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsCopyContestOldContestIdPost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $oldContestId = 789; # Long | ID del concurso a copiar.
    
    eval { 
        $api_instance->apiContestsCopyContestOldContestIdPost(oldContestId => $oldContestId);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsCopyContestOldContestIdPost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    oldContestId = 789 # Long | ID del concurso a copiar.
    
    try: 
        # Copia un concurso existente.
        api_instance.api_contests_copy_contest_old_contest_id_post(oldContestId)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsCopyContestOldContestIdPost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

oldContestId\*

Long (int64)

ID del concurso a copiar.

Required

Responses
---------

### Status: 201 - Concurso copiado con éxito.

### Status: 404 - Concurso no encontrado.

* * *

apiContestsCreateContestLogContestIdWordlePositionUserNamePost
==============================================================

Crea un nuevo registro de estado de concurso.

Crea un nuevo registro de estado de concurso para un usuario específico en una posición de wordle dada. Requiere el rol de Alumno.

  

    /api/contests/createContestLog/{contestId}/{wordlePosition}/{userName}

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsCreateContestLogContestIdWordlePositionUserNamePost-0-curl)
*   [Java](#examples-ContestController-apiContestsCreateContestLogContestIdWordlePositionUserNamePost-0-java)
*   [Android](#examples-ContestController-apiContestsCreateContestLogContestIdWordlePositionUserNamePost-0-android)
*   [Obj-C](#examples-ContestController-apiContestsCreateContestLogContestIdWordlePositionUserNamePost-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsCreateContestLogContestIdWordlePositionUserNamePost-0-javascript)
*   [C#](#examples-ContestController-apiContestsCreateContestLogContestIdWordlePositionUserNamePost-0-csharp)
*   [PHP](#examples-ContestController-apiContestsCreateContestLogContestIdWordlePositionUserNamePost-0-php)
*   [Perl](#examples-ContestController-apiContestsCreateContestLogContestIdWordlePositionUserNamePost-0-perl)
*   [Python](#examples-ContestController-apiContestsCreateContestLogContestIdWordlePositionUserNamePost-0-python)

    curl -X POST\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/createContestLog/{contestId}/{wordlePosition}/{userName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            WordleStateLog body = ; // WordleStateLog | 
            Long contestId = 789; // Long | ID del concurso.
            Integer wordlePosition = 56; // Integer | Posición del wordle.
            String userName = userName_example; // String | Nombre de usuario del usuario.
            try {
                apiInstance.apiContestsCreateContestLogContestIdWordlePositionUserNamePost(body, contestId, wordlePosition, userName);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsCreateContestLogContestIdWordlePositionUserNamePost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            WordleStateLog body = ; // WordleStateLog | 
            Long contestId = 789; // Long | ID del concurso.
            Integer wordlePosition = 56; // Integer | Posición del wordle.
            String userName = userName_example; // String | Nombre de usuario del usuario.
            try {
                apiInstance.apiContestsCreateContestLogContestIdWordlePositionUserNamePost(body, contestId, wordlePosition, userName);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsCreateContestLogContestIdWordlePositionUserNamePost");
                e.printStackTrace();
            }
        }
    }

    WordleStateLog *body = ; // 
    Long *contestId = 789; // ID del concurso.
    Integer *wordlePosition = 56; // Posición del wordle.
    String *userName = userName_example; // Nombre de usuario del usuario.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Crea un nuevo registro de estado de concurso.
    [apiInstance apiContestsCreateContestLogContestIdWordlePositionUserNamePostWith:body
        contestId:contestId
        wordlePosition:wordlePosition
        userName:userName
                  completionHandler: ^(NSError* error) {
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var body = ; // {{WordleStateLog}} 
    var contestId = 789; // {{Long}} ID del concurso.
    var wordlePosition = 56; // {{Integer}} Posición del wordle.
    var userName = userName_example; // {{String}} Nombre de usuario del usuario.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully.');
      }
    };
    api.apiContestsCreateContestLogContestIdWordlePositionUserNamePost(bodycontestIdwordlePositionuserName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsCreateContestLogContestIdWordlePositionUserNamePostExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var body = new WordleStateLog(); // WordleStateLog | 
                var contestId = 789;  // Long | ID del concurso.
                var wordlePosition = 56;  // Integer | Posición del wordle.
                var userName = userName_example;  // String | Nombre de usuario del usuario.
    
                try
                {
                    // Crea un nuevo registro de estado de concurso.
                    apiInstance.apiContestsCreateContestLogContestIdWordlePositionUserNamePost(body, contestId, wordlePosition, userName);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsCreateContestLogContestIdWordlePositionUserNamePost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $body = ; // WordleStateLog | 
    $contestId = 789; // Long | ID del concurso.
    $wordlePosition = 56; // Integer | Posición del wordle.
    $userName = userName_example; // String | Nombre de usuario del usuario.
    
    try {
        $api_instance->apiContestsCreateContestLogContestIdWordlePositionUserNamePost($body, $contestId, $wordlePosition, $userName);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsCreateContestLogContestIdWordlePositionUserNamePost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $body = WWW::SwaggerClient::Object::WordleStateLog->new(); # WordleStateLog | 
    my $contestId = 789; # Long | ID del concurso.
    my $wordlePosition = 56; # Integer | Posición del wordle.
    my $userName = userName_example; # String | Nombre de usuario del usuario.
    
    eval { 
        $api_instance->apiContestsCreateContestLogContestIdWordlePositionUserNamePost(body => $body, contestId => $contestId, wordlePosition => $wordlePosition, userName => $userName);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsCreateContestLogContestIdWordlePositionUserNamePost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    body =  # WordleStateLog | 
    contestId = 789 # Long | ID del concurso.
    wordlePosition = 56 # Integer | Posición del wordle.
    userName = userName_example # String | Nombre de usuario del usuario.
    
    try: 
        # Crea un nuevo registro de estado de concurso.
        api_instance.api_contests_create_contest_log_contest_id_wordle_position_user_name_post(body, contestId, wordlePosition, userName)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsCreateContestLogContestIdWordlePositionUserNamePost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso.

Required

wordlePosition\*

Integer

Posición del wordle.

Required

userName\*

String

Nombre de usuario del usuario.

Required

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/WordleStateLog" } } }, "required" : true }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiContestsCreateContestLogContestIdWordlePositionUserNamePost\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 201 - Registro de estado de concurso creado con éxito.

### Status: 400 - Error al procesar los datos.

### Status: 404 - Concurso o usuario no encontrado.

* * *

apiContestsDeleteContestContestIdDelete
=======================================

Elimina un concurso por su ID.

Elimina un concurso específico utilizando su ID. Requiere el rol de Administrador o de Profesor.

  

    /api/contests/deleteContest/{contestId}

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsDeleteContestContestIdDelete-0-curl)
*   [Java](#examples-ContestController-apiContestsDeleteContestContestIdDelete-0-java)
*   [Android](#examples-ContestController-apiContestsDeleteContestContestIdDelete-0-android)
*   [Obj-C](#examples-ContestController-apiContestsDeleteContestContestIdDelete-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsDeleteContestContestIdDelete-0-javascript)
*   [C#](#examples-ContestController-apiContestsDeleteContestContestIdDelete-0-csharp)
*   [PHP](#examples-ContestController-apiContestsDeleteContestContestIdDelete-0-php)
*   [Perl](#examples-ContestController-apiContestsDeleteContestContestIdDelete-0-perl)
*   [Python](#examples-ContestController-apiContestsDeleteContestContestIdDelete-0-python)

    curl -X DELETE\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/deleteContest/{contestId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso a eliminar.
            try {
                apiInstance.apiContestsDeleteContestContestIdDelete(contestId);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsDeleteContestContestIdDelete");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso a eliminar.
            try {
                apiInstance.apiContestsDeleteContestContestIdDelete(contestId);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsDeleteContestContestIdDelete");
                e.printStackTrace();
            }
        }
    }

    Long *contestId = 789; // ID del concurso a eliminar.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Elimina un concurso por su ID.
    [apiInstance apiContestsDeleteContestContestIdDeleteWith:contestId
                  completionHandler: ^(NSError* error) {
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var contestId = 789; // {{Long}} ID del concurso a eliminar.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully.');
      }
    };
    api.apiContestsDeleteContestContestIdDelete(contestId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsDeleteContestContestIdDeleteExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var contestId = 789;  // Long | ID del concurso a eliminar.
    
                try
                {
                    // Elimina un concurso por su ID.
                    apiInstance.apiContestsDeleteContestContestIdDelete(contestId);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsDeleteContestContestIdDelete: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $contestId = 789; // Long | ID del concurso a eliminar.
    
    try {
        $api_instance->apiContestsDeleteContestContestIdDelete($contestId);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsDeleteContestContestIdDelete: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $contestId = 789; # Long | ID del concurso a eliminar.
    
    eval { 
        $api_instance->apiContestsDeleteContestContestIdDelete(contestId => $contestId);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsDeleteContestContestIdDelete: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    contestId = 789 # Long | ID del concurso a eliminar.
    
    try: 
        # Elimina un concurso por su ID.
        api_instance.api_contests_delete_contest_contest_id_delete(contestId)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsDeleteContestContestIdDelete: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso a eliminar.

Required

Responses
---------

### Status: 200 - Concurso eliminado con éxito.

### Status: 404 - Concurso no encontrado.

* * *

apiContestsEditContestPost
==========================

Edita un concurso existente.

Edita un concurso existente utilizando un DTO con la información actualizada. Requiere el rol de Administrador o de Profesor.

  

    /api/contests/editContest

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsEditContestPost-0-curl)
*   [Java](#examples-ContestController-apiContestsEditContestPost-0-java)
*   [Android](#examples-ContestController-apiContestsEditContestPost-0-android)
*   [Obj-C](#examples-ContestController-apiContestsEditContestPost-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsEditContestPost-0-javascript)
*   [C#](#examples-ContestController-apiContestsEditContestPost-0-csharp)
*   [PHP](#examples-ContestController-apiContestsEditContestPost-0-php)
*   [Perl](#examples-ContestController-apiContestsEditContestPost-0-perl)
*   [Python](#examples-ContestController-apiContestsEditContestPost-0-python)

    curl -X POST\
    -H "Accept: application/json"\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/editContest"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            EditContestDTO body = ; // EditContestDTO | 
            try {
                Contest result = apiInstance.apiContestsEditContestPost(body);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsEditContestPost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            EditContestDTO body = ; // EditContestDTO | 
            try {
                Contest result = apiInstance.apiContestsEditContestPost(body);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsEditContestPost");
                e.printStackTrace();
            }
        }
    }

    EditContestDTO *body = ; // 
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Edita un concurso existente.
    [apiInstance apiContestsEditContestPostWith:body
                  completionHandler: ^(Contest output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var body = ; // {{EditContestDTO}} 
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiContestsEditContestPost(body, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsEditContestPostExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var body = new EditContestDTO(); // EditContestDTO | 
    
                try
                {
                    // Edita un concurso existente.
                    Contest result = apiInstance.apiContestsEditContestPost(body);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsEditContestPost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $body = ; // EditContestDTO | 
    
    try {
        $result = $api_instance->apiContestsEditContestPost($body);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsEditContestPost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $body = WWW::SwaggerClient::Object::EditContestDTO->new(); # EditContestDTO | 
    
    eval { 
        my $result = $api_instance->apiContestsEditContestPost(body => $body);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsEditContestPost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    body =  # EditContestDTO | 
    
    try: 
        # Edita un concurso existente.
        api_response = api_instance.api_contests_edit_contest_post(body)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsEditContestPost: %s\n" % e)

Parameters
----------

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/EditContestDTO" } } }, "required" : true }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiContestsEditContestPost\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 200 - Concurso editado con éxito.

*   [Schema](#responses-apiContestsEditContestPost-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Concurso editado con éxito.", "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/Contest" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiContestsEditContestPost-200-schema-data').val(stringify(schema)); var result = $('#responses-apiContestsEditContestPost-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Concurso no encontrado.

* * *

apiContestsExistsInDictionaryWordGet
====================================

Verifica si una palabra existe en el diccionario global.

Verifica si una palabra dada existe en el diccionario global. Requiere el rol de Alumno.

  

    /api/contests/existsInDictionary/{word}

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsExistsInDictionaryWordGet-0-curl)
*   [Java](#examples-ContestController-apiContestsExistsInDictionaryWordGet-0-java)
*   [Android](#examples-ContestController-apiContestsExistsInDictionaryWordGet-0-android)
*   [Obj-C](#examples-ContestController-apiContestsExistsInDictionaryWordGet-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsExistsInDictionaryWordGet-0-javascript)
*   [C#](#examples-ContestController-apiContestsExistsInDictionaryWordGet-0-csharp)
*   [PHP](#examples-ContestController-apiContestsExistsInDictionaryWordGet-0-php)
*   [Perl](#examples-ContestController-apiContestsExistsInDictionaryWordGet-0-perl)
*   [Python](#examples-ContestController-apiContestsExistsInDictionaryWordGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/existsInDictionary/{word}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            String word = word_example; // String | Palabra a verificar.
            try {
                'Boolean' result = apiInstance.apiContestsExistsInDictionaryWordGet(word);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsExistsInDictionaryWordGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            String word = word_example; // String | Palabra a verificar.
            try {
                'Boolean' result = apiInstance.apiContestsExistsInDictionaryWordGet(word);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsExistsInDictionaryWordGet");
                e.printStackTrace();
            }
        }
    }

    String *word = word_example; // Palabra a verificar.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Verifica si una palabra existe en el diccionario global.
    [apiInstance apiContestsExistsInDictionaryWordGetWith:word
                  completionHandler: ^('Boolean' output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var word = word_example; // {{String}} Palabra a verificar.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiContestsExistsInDictionaryWordGet(word, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsExistsInDictionaryWordGetExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var word = word_example;  // String | Palabra a verificar.
    
                try
                {
                    // Verifica si una palabra existe en el diccionario global.
                    'Boolean' result = apiInstance.apiContestsExistsInDictionaryWordGet(word);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsExistsInDictionaryWordGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $word = word_example; // String | Palabra a verificar.
    
    try {
        $result = $api_instance->apiContestsExistsInDictionaryWordGet($word);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsExistsInDictionaryWordGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $word = word_example; # String | Palabra a verificar.
    
    eval { 
        my $result = $api_instance->apiContestsExistsInDictionaryWordGet(word => $word);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsExistsInDictionaryWordGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    word = word_example # String | Palabra a verificar.
    
    try: 
        # Verifica si una palabra existe en el diccionario global.
        api_response = api_instance.api_contests_exists_in_dictionary_word_get(word)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsExistsInDictionaryWordGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

word\*

String

Palabra a verificar.

Required

Responses
---------

### Status: 200 - Resultado de la verificación.

*   [Schema](#responses-apiContestsExistsInDictionaryWordGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Resultado de la verificación.", "content" : { "application/json" : { "schema" : { "type" : "boolean", "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiContestsExistsInDictionaryWordGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiContestsExistsInDictionaryWordGet-200-schema-200'); result.empty(); result.append(view.render()); });

* * *

apiContestsExistsInExternalDictionaryContestIdWordleGet
=======================================================

Verifica si una palabra existe en el diccionario externo de un concurso.

Verifica si una palabra dada existe en el diccionario externo de un concurso específico. Requiere el rol de Alumno

  

    /api/contests/existsInExternalDictionary/{contestId}/{wordle}

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsExistsInExternalDictionaryContestIdWordleGet-0-curl)
*   [Java](#examples-ContestController-apiContestsExistsInExternalDictionaryContestIdWordleGet-0-java)
*   [Android](#examples-ContestController-apiContestsExistsInExternalDictionaryContestIdWordleGet-0-android)
*   [Obj-C](#examples-ContestController-apiContestsExistsInExternalDictionaryContestIdWordleGet-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsExistsInExternalDictionaryContestIdWordleGet-0-javascript)
*   [C#](#examples-ContestController-apiContestsExistsInExternalDictionaryContestIdWordleGet-0-csharp)
*   [PHP](#examples-ContestController-apiContestsExistsInExternalDictionaryContestIdWordleGet-0-php)
*   [Perl](#examples-ContestController-apiContestsExistsInExternalDictionaryContestIdWordleGet-0-perl)
*   [Python](#examples-ContestController-apiContestsExistsInExternalDictionaryContestIdWordleGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/existsInExternalDictionary/{contestId}/{wordle}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            String wordle = wordle_example; // String | Palabra a verificar.
            try {
                'Boolean' result = apiInstance.apiContestsExistsInExternalDictionaryContestIdWordleGet(contestId, wordle);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsExistsInExternalDictionaryContestIdWordleGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            String wordle = wordle_example; // String | Palabra a verificar.
            try {
                'Boolean' result = apiInstance.apiContestsExistsInExternalDictionaryContestIdWordleGet(contestId, wordle);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsExistsInExternalDictionaryContestIdWordleGet");
                e.printStackTrace();
            }
        }
    }

    Long *contestId = 789; // ID del concurso.
    String *wordle = wordle_example; // Palabra a verificar.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Verifica si una palabra existe en el diccionario externo de un concurso.
    [apiInstance apiContestsExistsInExternalDictionaryContestIdWordleGetWith:contestId
        wordle:wordle
                  completionHandler: ^('Boolean' output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var contestId = 789; // {{Long}} ID del concurso.
    var wordle = wordle_example; // {{String}} Palabra a verificar.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiContestsExistsInExternalDictionaryContestIdWordleGet(contestId, wordle, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsExistsInExternalDictionaryContestIdWordleGetExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var contestId = 789;  // Long | ID del concurso.
                var wordle = wordle_example;  // String | Palabra a verificar.
    
                try
                {
                    // Verifica si una palabra existe en el diccionario externo de un concurso.
                    'Boolean' result = apiInstance.apiContestsExistsInExternalDictionaryContestIdWordleGet(contestId, wordle);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsExistsInExternalDictionaryContestIdWordleGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $contestId = 789; // Long | ID del concurso.
    $wordle = wordle_example; // String | Palabra a verificar.
    
    try {
        $result = $api_instance->apiContestsExistsInExternalDictionaryContestIdWordleGet($contestId, $wordle);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsExistsInExternalDictionaryContestIdWordleGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $contestId = 789; # Long | ID del concurso.
    my $wordle = wordle_example; # String | Palabra a verificar.
    
    eval { 
        my $result = $api_instance->apiContestsExistsInExternalDictionaryContestIdWordleGet(contestId => $contestId, wordle => $wordle);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsExistsInExternalDictionaryContestIdWordleGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    contestId = 789 # Long | ID del concurso.
    wordle = wordle_example # String | Palabra a verificar.
    
    try: 
        # Verifica si una palabra existe en el diccionario externo de un concurso.
        api_response = api_instance.api_contests_exists_in_external_dictionary_contest_id_wordle_get(contestId, wordle)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsExistsInExternalDictionaryContestIdWordleGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso.

Required

wordle\*

String

Palabra a verificar.

Required

Responses
---------

### Status: 200 - Resultado de la verificación.

*   [Schema](#responses-apiContestsExistsInExternalDictionaryContestIdWordleGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Resultado de la verificación.", "content" : { "application/json" : { "schema" : { "type" : "boolean", "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiContestsExistsInExternalDictionaryContestIdWordleGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiContestsExistsInExternalDictionaryContestIdWordleGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Concurso no encontrado.

* * *

apiContestsExportLogsInExcelContestIdGet
========================================

Exporta los registros de un concurso a un archivo Excel.

Exporta todos los registros de estado de concurso de un concurso específico a un archivo Excel. Requiere el rol de Profesor o de Administrador.

  

    /api/contests/exportLogsInExcel/{contestId}

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsExportLogsInExcelContestIdGet-0-curl)
*   [Java](#examples-ContestController-apiContestsExportLogsInExcelContestIdGet-0-java)
*   [Android](#examples-ContestController-apiContestsExportLogsInExcelContestIdGet-0-android)
*   [Obj-C](#examples-ContestController-apiContestsExportLogsInExcelContestIdGet-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsExportLogsInExcelContestIdGet-0-javascript)
*   [C#](#examples-ContestController-apiContestsExportLogsInExcelContestIdGet-0-csharp)
*   [PHP](#examples-ContestController-apiContestsExportLogsInExcelContestIdGet-0-php)
*   [Perl](#examples-ContestController-apiContestsExportLogsInExcelContestIdGet-0-perl)
*   [Python](#examples-ContestController-apiContestsExportLogsInExcelContestIdGet-0-python)

    curl -X GET\
    -H "Accept: application/octet-stream"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/exportLogsInExcel/{contestId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            try {
                Object result = apiInstance.apiContestsExportLogsInExcelContestIdGet(contestId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsExportLogsInExcelContestIdGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            try {
                Object result = apiInstance.apiContestsExportLogsInExcelContestIdGet(contestId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsExportLogsInExcelContestIdGet");
                e.printStackTrace();
            }
        }
    }

    Long *contestId = 789; // ID del concurso.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Exporta los registros de un concurso a un archivo Excel.
    [apiInstance apiContestsExportLogsInExcelContestIdGetWith:contestId
                  completionHandler: ^(Object output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var contestId = 789; // {{Long}} ID del concurso.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiContestsExportLogsInExcelContestIdGet(contestId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsExportLogsInExcelContestIdGetExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var contestId = 789;  // Long | ID del concurso.
    
                try
                {
                    // Exporta los registros de un concurso a un archivo Excel.
                    Object result = apiInstance.apiContestsExportLogsInExcelContestIdGet(contestId);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsExportLogsInExcelContestIdGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $contestId = 789; // Long | ID del concurso.
    
    try {
        $result = $api_instance->apiContestsExportLogsInExcelContestIdGet($contestId);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsExportLogsInExcelContestIdGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $contestId = 789; # Long | ID del concurso.
    
    eval { 
        my $result = $api_instance->apiContestsExportLogsInExcelContestIdGet(contestId => $contestId);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsExportLogsInExcelContestIdGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    contestId = 789 # Long | ID del concurso.
    
    try: 
        # Exporta los registros de un concurso a un archivo Excel.
        api_response = api_instance.api_contests_export_logs_in_excel_contest_id_get(contestId)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsExportLogsInExcelContestIdGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso.

Required

Responses
---------

### Status: 200 - Archivo Excel con los registros de concurso exportado con éxito.

*   [Schema](#responses-apiContestsExportLogsInExcelContestIdGet-200-schema)
*   [Headers](#responses-apiContestsExportLogsInExcelContestIdGet-200-headers)

$(document).ready(function() { var schemaWrapper = { "description" : "Archivo Excel con los registros de concurso exportado con éxito.", "headers" : { "Content-Disposition" : { "description" : "logs.xlsx", "style" : "simple", "explode" : false, "schema" : { "type" : "string" } }, "Content-Type" : { "description" : "Archivo Excel.", "style" : "simple", "explode" : false, "schema" : { "type" : "string" } } }, "content" : { "application/octet-stream" : { "schema" : { "type" : "object", "x-content-type" : "application/octet-stream" } } } }; var schema = schemaWrapper.content\["application/octet-stream"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiContestsExportLogsInExcelContestIdGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiContestsExportLogsInExcelContestIdGet-200-schema-200'); result.empty(); result.append(view.render()); });

Name

Type

Format

Description

Content-Disposition

String

Content-Type

String

### Status: 404 - Concurso no encontrado.

* * *

apiContestsGetAllContestStateContestIdGet
=========================================

Obtiene todos los estados de concurso para un concurso dado.

Obtiene una lista de todos los estados de concurso para un concurso específico.

  

    /api/contests/getAllContestState/{contestId}

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsGetAllContestStateContestIdGet-0-curl)
*   [Java](#examples-ContestController-apiContestsGetAllContestStateContestIdGet-0-java)
*   [Android](#examples-ContestController-apiContestsGetAllContestStateContestIdGet-0-android)
*   [Obj-C](#examples-ContestController-apiContestsGetAllContestStateContestIdGet-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsGetAllContestStateContestIdGet-0-javascript)
*   [C#](#examples-ContestController-apiContestsGetAllContestStateContestIdGet-0-csharp)
*   [PHP](#examples-ContestController-apiContestsGetAllContestStateContestIdGet-0-php)
*   [Perl](#examples-ContestController-apiContestsGetAllContestStateContestIdGet-0-perl)
*   [Python](#examples-ContestController-apiContestsGetAllContestStateContestIdGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/getAllContestState/{contestId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            try {
                array[UserState] result = apiInstance.apiContestsGetAllContestStateContestIdGet(contestId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsGetAllContestStateContestIdGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            try {
                array[UserState] result = apiInstance.apiContestsGetAllContestStateContestIdGet(contestId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsGetAllContestStateContestIdGet");
                e.printStackTrace();
            }
        }
    }

    Long *contestId = 789; // ID del concurso.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Obtiene todos los estados de concurso para un concurso dado.
    [apiInstance apiContestsGetAllContestStateContestIdGetWith:contestId
                  completionHandler: ^(array[UserState] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var contestId = 789; // {{Long}} ID del concurso.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiContestsGetAllContestStateContestIdGet(contestId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsGetAllContestStateContestIdGetExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var contestId = 789;  // Long | ID del concurso.
    
                try
                {
                    // Obtiene todos los estados de concurso para un concurso dado.
                    array[UserState] result = apiInstance.apiContestsGetAllContestStateContestIdGet(contestId);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsGetAllContestStateContestIdGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $contestId = 789; // Long | ID del concurso.
    
    try {
        $result = $api_instance->apiContestsGetAllContestStateContestIdGet($contestId);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsGetAllContestStateContestIdGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $contestId = 789; # Long | ID del concurso.
    
    eval { 
        my $result = $api_instance->apiContestsGetAllContestStateContestIdGet(contestId => $contestId);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsGetAllContestStateContestIdGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    contestId = 789 # Long | ID del concurso.
    
    try: 
        # Obtiene todos los estados de concurso para un concurso dado.
        api_response = api_instance.api_contests_get_all_contest_state_contest_id_get(contestId)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsGetAllContestStateContestIdGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso.

Required

Responses
---------

### Status: 200 - Lista de estados de concurso obtenida con éxito.

*   [Schema](#responses-apiContestsGetAllContestStateContestIdGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de estados de concurso obtenida con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/UserState" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiContestsGetAllContestStateContestIdGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiContestsGetAllContestStateContestIdGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Concurso no encontrado.

* * *

apiContestsGetAllContestStateLogsContestIdGet
=============================================

Obtiene todos los registros de estado de concurso de un concurso.

Obtiene una lista de todos los registros de estado de concurso de un concurso dado. Requiere el rol de Administrador o de Profesor

  

    /api/contests/getAllContestStateLogs/{contestId}

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsGetAllContestStateLogsContestIdGet-0-curl)
*   [Java](#examples-ContestController-apiContestsGetAllContestStateLogsContestIdGet-0-java)
*   [Android](#examples-ContestController-apiContestsGetAllContestStateLogsContestIdGet-0-android)
*   [Obj-C](#examples-ContestController-apiContestsGetAllContestStateLogsContestIdGet-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsGetAllContestStateLogsContestIdGet-0-javascript)
*   [C#](#examples-ContestController-apiContestsGetAllContestStateLogsContestIdGet-0-csharp)
*   [PHP](#examples-ContestController-apiContestsGetAllContestStateLogsContestIdGet-0-php)
*   [Perl](#examples-ContestController-apiContestsGetAllContestStateLogsContestIdGet-0-perl)
*   [Python](#examples-ContestController-apiContestsGetAllContestStateLogsContestIdGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/getAllContestStateLogs/{contestId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            try {
                array[WordleStateLog] result = apiInstance.apiContestsGetAllContestStateLogsContestIdGet(contestId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsGetAllContestStateLogsContestIdGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            try {
                array[WordleStateLog] result = apiInstance.apiContestsGetAllContestStateLogsContestIdGet(contestId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsGetAllContestStateLogsContestIdGet");
                e.printStackTrace();
            }
        }
    }

    Long *contestId = 789; // ID del concurso.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Obtiene todos los registros de estado de concurso de un concurso.
    [apiInstance apiContestsGetAllContestStateLogsContestIdGetWith:contestId
                  completionHandler: ^(array[WordleStateLog] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var contestId = 789; // {{Long}} ID del concurso.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiContestsGetAllContestStateLogsContestIdGet(contestId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsGetAllContestStateLogsContestIdGetExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var contestId = 789;  // Long | ID del concurso.
    
                try
                {
                    // Obtiene todos los registros de estado de concurso de un concurso.
                    array[WordleStateLog] result = apiInstance.apiContestsGetAllContestStateLogsContestIdGet(contestId);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsGetAllContestStateLogsContestIdGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $contestId = 789; // Long | ID del concurso.
    
    try {
        $result = $api_instance->apiContestsGetAllContestStateLogsContestIdGet($contestId);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsGetAllContestStateLogsContestIdGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $contestId = 789; # Long | ID del concurso.
    
    eval { 
        my $result = $api_instance->apiContestsGetAllContestStateLogsContestIdGet(contestId => $contestId);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsGetAllContestStateLogsContestIdGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    contestId = 789 # Long | ID del concurso.
    
    try: 
        # Obtiene todos los registros de estado de concurso de un concurso.
        api_response = api_instance.api_contests_get_all_contest_state_logs_contest_id_get(contestId)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsGetAllContestStateLogsContestIdGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso.

Required

Responses
---------

### Status: 200 - Lista de registros de estado de concurso obtenida con éxito.

*   [Schema](#responses-apiContestsGetAllContestStateLogsContestIdGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de registros de estado de concurso obtenida con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/WordleStateLog" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiContestsGetAllContestStateLogsContestIdGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiContestsGetAllContestStateLogsContestIdGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Concurso o registros de estado de concurso no encontrado.

* * *

apiContestsGetAllUserContestStateLogsContestIdUserNameGet
=========================================================

Obtiene todos los registros de estado de concurso de un usuario en un concurso.

Obtiene una lista de todos los registros de estado de concurso de un usuario específico en un concurso dado. Requiere el rol de ALumno.

  

    /api/contests/getAllUserContestStateLogs/{contestId}/{userName}

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsGetAllUserContestStateLogsContestIdUserNameGet-0-curl)
*   [Java](#examples-ContestController-apiContestsGetAllUserContestStateLogsContestIdUserNameGet-0-java)
*   [Android](#examples-ContestController-apiContestsGetAllUserContestStateLogsContestIdUserNameGet-0-android)
*   [Obj-C](#examples-ContestController-apiContestsGetAllUserContestStateLogsContestIdUserNameGet-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsGetAllUserContestStateLogsContestIdUserNameGet-0-javascript)
*   [C#](#examples-ContestController-apiContestsGetAllUserContestStateLogsContestIdUserNameGet-0-csharp)
*   [PHP](#examples-ContestController-apiContestsGetAllUserContestStateLogsContestIdUserNameGet-0-php)
*   [Perl](#examples-ContestController-apiContestsGetAllUserContestStateLogsContestIdUserNameGet-0-perl)
*   [Python](#examples-ContestController-apiContestsGetAllUserContestStateLogsContestIdUserNameGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/getAllUserContestStateLogs/{contestId}/{userName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            String userName = userName_example; // String | Nombre de usuario del usuario.
            try {
                array[WordleStateLog] result = apiInstance.apiContestsGetAllUserContestStateLogsContestIdUserNameGet(contestId, userName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsGetAllUserContestStateLogsContestIdUserNameGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            String userName = userName_example; // String | Nombre de usuario del usuario.
            try {
                array[WordleStateLog] result = apiInstance.apiContestsGetAllUserContestStateLogsContestIdUserNameGet(contestId, userName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsGetAllUserContestStateLogsContestIdUserNameGet");
                e.printStackTrace();
            }
        }
    }

    Long *contestId = 789; // ID del concurso.
    String *userName = userName_example; // Nombre de usuario del usuario.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Obtiene todos los registros de estado de concurso de un usuario en un concurso.
    [apiInstance apiContestsGetAllUserContestStateLogsContestIdUserNameGetWith:contestId
        userName:userName
                  completionHandler: ^(array[WordleStateLog] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var contestId = 789; // {{Long}} ID del concurso.
    var userName = userName_example; // {{String}} Nombre de usuario del usuario.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiContestsGetAllUserContestStateLogsContestIdUserNameGet(contestId, userName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsGetAllUserContestStateLogsContestIdUserNameGetExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var contestId = 789;  // Long | ID del concurso.
                var userName = userName_example;  // String | Nombre de usuario del usuario.
    
                try
                {
                    // Obtiene todos los registros de estado de concurso de un usuario en un concurso.
                    array[WordleStateLog] result = apiInstance.apiContestsGetAllUserContestStateLogsContestIdUserNameGet(contestId, userName);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsGetAllUserContestStateLogsContestIdUserNameGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $contestId = 789; // Long | ID del concurso.
    $userName = userName_example; // String | Nombre de usuario del usuario.
    
    try {
        $result = $api_instance->apiContestsGetAllUserContestStateLogsContestIdUserNameGet($contestId, $userName);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsGetAllUserContestStateLogsContestIdUserNameGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $contestId = 789; # Long | ID del concurso.
    my $userName = userName_example; # String | Nombre de usuario del usuario.
    
    eval { 
        my $result = $api_instance->apiContestsGetAllUserContestStateLogsContestIdUserNameGet(contestId => $contestId, userName => $userName);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsGetAllUserContestStateLogsContestIdUserNameGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    contestId = 789 # Long | ID del concurso.
    userName = userName_example # String | Nombre de usuario del usuario.
    
    try: 
        # Obtiene todos los registros de estado de concurso de un usuario en un concurso.
        api_response = api_instance.api_contests_get_all_user_contest_state_logs_contest_id_user_name_get(contestId, userName)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsGetAllUserContestStateLogsContestIdUserNameGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso.

Required

userName\*

String

Nombre de usuario del usuario.

Required

Responses
---------

### Status: 200 - Lista de registros de estado de concurso obtenida con éxito.

*   [Schema](#responses-apiContestsGetAllUserContestStateLogsContestIdUserNameGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de registros de estado de concurso obtenida con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/WordleStateLog" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiContestsGetAllUserContestStateLogsContestIdUserNameGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiContestsGetAllUserContestStateLogsContestIdUserNameGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Concurso, usuario o registros de estado de concurso no encontrado.

* * *

apiContestsGetContestStateContestIdUserNameGet
==============================================

Obtiene el estado de un concurso para un usuario.

Obtiene el estado de un concurso específico para un usuario dado. Requiere el rol de Alumno.

  

    /api/contests/getContestState/{contestId}/{userName}

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsGetContestStateContestIdUserNameGet-0-curl)
*   [Java](#examples-ContestController-apiContestsGetContestStateContestIdUserNameGet-0-java)
*   [Android](#examples-ContestController-apiContestsGetContestStateContestIdUserNameGet-0-android)
*   [Obj-C](#examples-ContestController-apiContestsGetContestStateContestIdUserNameGet-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsGetContestStateContestIdUserNameGet-0-javascript)
*   [C#](#examples-ContestController-apiContestsGetContestStateContestIdUserNameGet-0-csharp)
*   [PHP](#examples-ContestController-apiContestsGetContestStateContestIdUserNameGet-0-php)
*   [Perl](#examples-ContestController-apiContestsGetContestStateContestIdUserNameGet-0-perl)
*   [Python](#examples-ContestController-apiContestsGetContestStateContestIdUserNameGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/getContestState/{contestId}/{userName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            String userName = userName_example; // String | Nombre de usuario del usuario.
            try {
                WordleState result = apiInstance.apiContestsGetContestStateContestIdUserNameGet(contestId, userName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsGetContestStateContestIdUserNameGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            String userName = userName_example; // String | Nombre de usuario del usuario.
            try {
                WordleState result = apiInstance.apiContestsGetContestStateContestIdUserNameGet(contestId, userName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsGetContestStateContestIdUserNameGet");
                e.printStackTrace();
            }
        }
    }

    Long *contestId = 789; // ID del concurso.
    String *userName = userName_example; // Nombre de usuario del usuario.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Obtiene el estado de un concurso para un usuario.
    [apiInstance apiContestsGetContestStateContestIdUserNameGetWith:contestId
        userName:userName
                  completionHandler: ^(WordleState output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var contestId = 789; // {{Long}} ID del concurso.
    var userName = userName_example; // {{String}} Nombre de usuario del usuario.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiContestsGetContestStateContestIdUserNameGet(contestId, userName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsGetContestStateContestIdUserNameGetExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var contestId = 789;  // Long | ID del concurso.
                var userName = userName_example;  // String | Nombre de usuario del usuario.
    
                try
                {
                    // Obtiene el estado de un concurso para un usuario.
                    WordleState result = apiInstance.apiContestsGetContestStateContestIdUserNameGet(contestId, userName);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsGetContestStateContestIdUserNameGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $contestId = 789; // Long | ID del concurso.
    $userName = userName_example; // String | Nombre de usuario del usuario.
    
    try {
        $result = $api_instance->apiContestsGetContestStateContestIdUserNameGet($contestId, $userName);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsGetContestStateContestIdUserNameGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $contestId = 789; # Long | ID del concurso.
    my $userName = userName_example; # String | Nombre de usuario del usuario.
    
    eval { 
        my $result = $api_instance->apiContestsGetContestStateContestIdUserNameGet(contestId => $contestId, userName => $userName);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsGetContestStateContestIdUserNameGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    contestId = 789 # Long | ID del concurso.
    userName = userName_example # String | Nombre de usuario del usuario.
    
    try: 
        # Obtiene el estado de un concurso para un usuario.
        api_response = api_instance.api_contests_get_contest_state_contest_id_user_name_get(contestId, userName)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsGetContestStateContestIdUserNameGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso.

Required

userName\*

String

Nombre de usuario del usuario.

Required

Responses
---------

### Status: 200 - Estado de concurso obtenido con éxito.

*   [Schema](#responses-apiContestsGetContestStateContestIdUserNameGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Estado de concurso obtenido con éxito.", "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/WordleState" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiContestsGetContestStateContestIdUserNameGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiContestsGetContestStateContestIdUserNameGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Concurso, usuario o estado de concurso no encontrado.

* * *

apiContestsNewContestCompetitionIdPost
======================================

Crea un nuevo concurso en una competición.

Crea un nuevo concurso en una competición específica utilizando su ID. Requiere el rol de Administrador o de Profesor.

  

    /api/contests/newContest/{competitionId}

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsNewContestCompetitionIdPost-0-curl)
*   [Java](#examples-ContestController-apiContestsNewContestCompetitionIdPost-0-java)
*   [Android](#examples-ContestController-apiContestsNewContestCompetitionIdPost-0-android)
*   [Obj-C](#examples-ContestController-apiContestsNewContestCompetitionIdPost-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsNewContestCompetitionIdPost-0-javascript)
*   [C#](#examples-ContestController-apiContestsNewContestCompetitionIdPost-0-csharp)
*   [PHP](#examples-ContestController-apiContestsNewContestCompetitionIdPost-0-php)
*   [Perl](#examples-ContestController-apiContestsNewContestCompetitionIdPost-0-perl)
*   [Python](#examples-ContestController-apiContestsNewContestCompetitionIdPost-0-python)

    curl -X POST\
    -H "Accept: application/json"\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/newContest/{competitionId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            Contest body = ; // Contest | 
            Long competitionId = 789; // Long | ID de la competición.
            try {
                Contest result = apiInstance.apiContestsNewContestCompetitionIdPost(body, competitionId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsNewContestCompetitionIdPost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            Contest body = ; // Contest | 
            Long competitionId = 789; // Long | ID de la competición.
            try {
                Contest result = apiInstance.apiContestsNewContestCompetitionIdPost(body, competitionId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsNewContestCompetitionIdPost");
                e.printStackTrace();
            }
        }
    }

    Contest *body = ; // 
    Long *competitionId = 789; // ID de la competición.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Crea un nuevo concurso en una competición.
    [apiInstance apiContestsNewContestCompetitionIdPostWith:body
        competitionId:competitionId
                  completionHandler: ^(Contest output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var body = ; // {{Contest}} 
    var competitionId = 789; // {{Long}} ID de la competición.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiContestsNewContestCompetitionIdPost(bodycompetitionId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsNewContestCompetitionIdPostExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var body = new Contest(); // Contest | 
                var competitionId = 789;  // Long | ID de la competición.
    
                try
                {
                    // Crea un nuevo concurso en una competición.
                    Contest result = apiInstance.apiContestsNewContestCompetitionIdPost(body, competitionId);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsNewContestCompetitionIdPost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $body = ; // Contest | 
    $competitionId = 789; // Long | ID de la competición.
    
    try {
        $result = $api_instance->apiContestsNewContestCompetitionIdPost($body, $competitionId);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsNewContestCompetitionIdPost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $body = WWW::SwaggerClient::Object::Contest->new(); # Contest | 
    my $competitionId = 789; # Long | ID de la competición.
    
    eval { 
        my $result = $api_instance->apiContestsNewContestCompetitionIdPost(body => $body, competitionId => $competitionId);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsNewContestCompetitionIdPost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    body =  # Contest | 
    competitionId = 789 # Long | ID de la competición.
    
    try: 
        # Crea un nuevo concurso en una competición.
        api_response = api_instance.api_contests_new_contest_competition_id_post(body, competitionId)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsNewContestCompetitionIdPost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

competitionId\*

Long (int64)

ID de la competición.

Required

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/Contest" } } }, "required" : true }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiContestsNewContestCompetitionIdPost\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 201 - Concurso creado con éxito.

*   [Schema](#responses-apiContestsNewContestCompetitionIdPost-201-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Concurso creado con éxito.", "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/Contest" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiContestsNewContestCompetitionIdPost-201-schema-data').val(stringify(schema)); var result = $('#responses-apiContestsNewContestCompetitionIdPost-201-schema-201'); result.empty(); result.append(view.render()); });

### Status: 404 - Competición no encontrada.

### Status: 409 - Concurso ya existe en la competición.

* * *

apiContestsNewContestStateContestIdUserNamePost
===============================================

Crea un nuevo estado de concurso para un usuario.

Crea un nuevo estado de concurso para un usuario específico en un concurso dado. Requiere el rol de Alumno.

  

    /api/contests/newContestState/{contestId}/{userName}

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsNewContestStateContestIdUserNamePost-0-curl)
*   [Java](#examples-ContestController-apiContestsNewContestStateContestIdUserNamePost-0-java)
*   [Android](#examples-ContestController-apiContestsNewContestStateContestIdUserNamePost-0-android)
*   [Obj-C](#examples-ContestController-apiContestsNewContestStateContestIdUserNamePost-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsNewContestStateContestIdUserNamePost-0-javascript)
*   [C#](#examples-ContestController-apiContestsNewContestStateContestIdUserNamePost-0-csharp)
*   [PHP](#examples-ContestController-apiContestsNewContestStateContestIdUserNamePost-0-php)
*   [Perl](#examples-ContestController-apiContestsNewContestStateContestIdUserNamePost-0-perl)
*   [Python](#examples-ContestController-apiContestsNewContestStateContestIdUserNamePost-0-python)

    curl -X POST\
    -H "Accept: application/json"\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/newContestState/{contestId}/{userName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            WordleState body = ; // WordleState | 
            Long contestId = 789; // Long | ID del concurso.
            String userName = userName_example; // String | Nombre de usuario del usuario.
            try {
                ContestState result = apiInstance.apiContestsNewContestStateContestIdUserNamePost(body, contestId, userName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsNewContestStateContestIdUserNamePost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            WordleState body = ; // WordleState | 
            Long contestId = 789; // Long | ID del concurso.
            String userName = userName_example; // String | Nombre de usuario del usuario.
            try {
                ContestState result = apiInstance.apiContestsNewContestStateContestIdUserNamePost(body, contestId, userName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsNewContestStateContestIdUserNamePost");
                e.printStackTrace();
            }
        }
    }

    WordleState *body = ; // 
    Long *contestId = 789; // ID del concurso.
    String *userName = userName_example; // Nombre de usuario del usuario.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Crea un nuevo estado de concurso para un usuario.
    [apiInstance apiContestsNewContestStateContestIdUserNamePostWith:body
        contestId:contestId
        userName:userName
                  completionHandler: ^(ContestState output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var body = ; // {{WordleState}} 
    var contestId = 789; // {{Long}} ID del concurso.
    var userName = userName_example; // {{String}} Nombre de usuario del usuario.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiContestsNewContestStateContestIdUserNamePost(bodycontestIduserName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsNewContestStateContestIdUserNamePostExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var body = new WordleState(); // WordleState | 
                var contestId = 789;  // Long | ID del concurso.
                var userName = userName_example;  // String | Nombre de usuario del usuario.
    
                try
                {
                    // Crea un nuevo estado de concurso para un usuario.
                    ContestState result = apiInstance.apiContestsNewContestStateContestIdUserNamePost(body, contestId, userName);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsNewContestStateContestIdUserNamePost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $body = ; // WordleState | 
    $contestId = 789; // Long | ID del concurso.
    $userName = userName_example; // String | Nombre de usuario del usuario.
    
    try {
        $result = $api_instance->apiContestsNewContestStateContestIdUserNamePost($body, $contestId, $userName);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsNewContestStateContestIdUserNamePost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $body = WWW::SwaggerClient::Object::WordleState->new(); # WordleState | 
    my $contestId = 789; # Long | ID del concurso.
    my $userName = userName_example; # String | Nombre de usuario del usuario.
    
    eval { 
        my $result = $api_instance->apiContestsNewContestStateContestIdUserNamePost(body => $body, contestId => $contestId, userName => $userName);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsNewContestStateContestIdUserNamePost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    body =  # WordleState | 
    contestId = 789 # Long | ID del concurso.
    userName = userName_example # String | Nombre de usuario del usuario.
    
    try: 
        # Crea un nuevo estado de concurso para un usuario.
        api_response = api_instance.api_contests_new_contest_state_contest_id_user_name_post(body, contestId, userName)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsNewContestStateContestIdUserNamePost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso.

Required

userName\*

String

Nombre de usuario del usuario.

Required

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/WordleState" } } }, "required" : true }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiContestsNewContestStateContestIdUserNamePost\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 201 - Estado de concurso creado con éxito.

*   [Schema](#responses-apiContestsNewContestStateContestIdUserNamePost-201-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Estado de concurso creado con éxito.", "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/ContestState" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiContestsNewContestStateContestIdUserNamePost-201-schema-data').val(stringify(schema)); var result = $('#responses-apiContestsNewContestStateContestIdUserNamePost-201-schema-201'); result.empty(); result.append(view.render()); });

### Status: 400 - Error al procesar los datos.

### Status: 404 - Concurso o usuario no encontrado.

### Status: 409 - Estado de concurso ya creado.

* * *

apiContestsSaveExternalDictionaryContestIdPost
==============================================

Guarda un diccionario externo para un concurso.

Guarda una lista de palabras como diccionario externo para un concurso específico. Requiere el rol de Administrador o de Profesor.

  

    /api/contests/saveExternalDictionary/{contestId}

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsSaveExternalDictionaryContestIdPost-0-curl)
*   [Java](#examples-ContestController-apiContestsSaveExternalDictionaryContestIdPost-0-java)
*   [Android](#examples-ContestController-apiContestsSaveExternalDictionaryContestIdPost-0-android)
*   [Obj-C](#examples-ContestController-apiContestsSaveExternalDictionaryContestIdPost-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsSaveExternalDictionaryContestIdPost-0-javascript)
*   [C#](#examples-ContestController-apiContestsSaveExternalDictionaryContestIdPost-0-csharp)
*   [PHP](#examples-ContestController-apiContestsSaveExternalDictionaryContestIdPost-0-php)
*   [Perl](#examples-ContestController-apiContestsSaveExternalDictionaryContestIdPost-0-perl)
*   [Python](#examples-ContestController-apiContestsSaveExternalDictionaryContestIdPost-0-python)

    curl -X POST\
    -H "Accept: application/json"\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/saveExternalDictionary/{contestId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            array[String] body = ; // array[String] | 
            Long contestId = 789; // Long | ID del concurso.
            try {
                array[DictionaryExternalSaved] result = apiInstance.apiContestsSaveExternalDictionaryContestIdPost(body, contestId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsSaveExternalDictionaryContestIdPost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            array[String] body = ; // array[String] | 
            Long contestId = 789; // Long | ID del concurso.
            try {
                array[DictionaryExternalSaved] result = apiInstance.apiContestsSaveExternalDictionaryContestIdPost(body, contestId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsSaveExternalDictionaryContestIdPost");
                e.printStackTrace();
            }
        }
    }

    array[String] *body = ; // 
    Long *contestId = 789; // ID del concurso.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Guarda un diccionario externo para un concurso.
    [apiInstance apiContestsSaveExternalDictionaryContestIdPostWith:body
        contestId:contestId
                  completionHandler: ^(array[DictionaryExternalSaved] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var body = ; // {{array[String]}} 
    var contestId = 789; // {{Long}} ID del concurso.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiContestsSaveExternalDictionaryContestIdPost(bodycontestId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsSaveExternalDictionaryContestIdPostExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var body = new array[String](); // array[String] | 
                var contestId = 789;  // Long | ID del concurso.
    
                try
                {
                    // Guarda un diccionario externo para un concurso.
                    array[DictionaryExternalSaved] result = apiInstance.apiContestsSaveExternalDictionaryContestIdPost(body, contestId);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsSaveExternalDictionaryContestIdPost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $body = ; // array[String] | 
    $contestId = 789; // Long | ID del concurso.
    
    try {
        $result = $api_instance->apiContestsSaveExternalDictionaryContestIdPost($body, $contestId);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsSaveExternalDictionaryContestIdPost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $body = [WWW::SwaggerClient::Object::array[String]->new()]; # array[String] | 
    my $contestId = 789; # Long | ID del concurso.
    
    eval { 
        my $result = $api_instance->apiContestsSaveExternalDictionaryContestIdPost(body => $body, contestId => $contestId);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsSaveExternalDictionaryContestIdPost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    body =  # array[String] | 
    contestId = 789 # Long | ID del concurso.
    
    try: 
        # Guarda un diccionario externo para un concurso.
        api_response = api_instance.api_contests_save_external_dictionary_contest_id_post(body, contestId)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsSaveExternalDictionaryContestIdPost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso.

Required

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "application/json" : { "schema" : { "type" : "array", "description" : "Lista de palabras a guardar.", "items" : { "type" : "string", "example" : "\[\\"Wordle1\\",\\"Wordle2\\"\]" } } } }, "required" : true }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiContestsSaveExternalDictionaryContestIdPost\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 200 - Diccionario externo guardado con éxito.

*   [Schema](#responses-apiContestsSaveExternalDictionaryContestIdPost-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Diccionario externo guardado con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/DictionaryExternalSaved" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiContestsSaveExternalDictionaryContestIdPost-200-schema-data').val(stringify(schema)); var result = $('#responses-apiContestsSaveExternalDictionaryContestIdPost-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Concurso no encontrado.

* * *

apiContestsUpdateContestStateContestIdUserNamePost
==================================================

Actualiza el estado de un concurso para un usuario.

Actualiza el estado de un concurso específico para un usuario dado. Requiere el rol de ALumno

  

    /api/contests/updateContestState/{contestId}/{userName}

### Usage and SDK Samples

*   [Curl](#examples-ContestController-apiContestsUpdateContestStateContestIdUserNamePost-0-curl)
*   [Java](#examples-ContestController-apiContestsUpdateContestStateContestIdUserNamePost-0-java)
*   [Android](#examples-ContestController-apiContestsUpdateContestStateContestIdUserNamePost-0-android)
*   [Obj-C](#examples-ContestController-apiContestsUpdateContestStateContestIdUserNamePost-0-objc)
*   [JavaScript](#examples-ContestController-apiContestsUpdateContestStateContestIdUserNamePost-0-javascript)
*   [C#](#examples-ContestController-apiContestsUpdateContestStateContestIdUserNamePost-0-csharp)
*   [PHP](#examples-ContestController-apiContestsUpdateContestStateContestIdUserNamePost-0-php)
*   [Perl](#examples-ContestController-apiContestsUpdateContestStateContestIdUserNamePost-0-perl)
*   [Python](#examples-ContestController-apiContestsUpdateContestStateContestIdUserNamePost-0-python)

    curl -X POST\
    -H "Accept: application/json"\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/contests/updateContestState/{contestId}/{userName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.ContestControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            
            ContestControllerApi apiInstance = new ContestControllerApi();
            WordleState body = ; // WordleState | 
            Long contestId = 789; // Long | ID del concurso.
            String userName = userName_example; // String | Nombre de usuario del usuario.
            try {
                ContestState result = apiInstance.apiContestsUpdateContestStateContestIdUserNamePost(body, contestId, userName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsUpdateContestStateContestIdUserNamePost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.ContestControllerApi;
    
    public class ContestControllerApiExample {
    
        public static void main(String[] args) {
            ContestControllerApi apiInstance = new ContestControllerApi();
            WordleState body = ; // WordleState | 
            Long contestId = 789; // Long | ID del concurso.
            String userName = userName_example; // String | Nombre de usuario del usuario.
            try {
                ContestState result = apiInstance.apiContestsUpdateContestStateContestIdUserNamePost(body, contestId, userName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling ContestControllerApi#apiContestsUpdateContestStateContestIdUserNamePost");
                e.printStackTrace();
            }
        }
    }

    WordleState *body = ; // 
    Long *contestId = 789; // ID del concurso.
    String *userName = userName_example; // Nombre de usuario del usuario.
    
    ContestControllerApi *apiInstance = [[ContestControllerApi alloc] init];
    
    // Actualiza el estado de un concurso para un usuario.
    [apiInstance apiContestsUpdateContestStateContestIdUserNamePostWith:body
        contestId:contestId
        userName:userName
                  completionHandler: ^(ContestState output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.ContestControllerApi()
    var body = ; // {{WordleState}} 
    var contestId = 789; // {{Long}} ID del concurso.
    var userName = userName_example; // {{String}} Nombre de usuario del usuario.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiContestsUpdateContestStateContestIdUserNamePost(bodycontestIduserName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiContestsUpdateContestStateContestIdUserNamePostExample
        {
            public void main()
            {
    
                var apiInstance = new ContestControllerApi();
                var body = new WordleState(); // WordleState | 
                var contestId = 789;  // Long | ID del concurso.
                var userName = userName_example;  // String | Nombre de usuario del usuario.
    
                try
                {
                    // Actualiza el estado de un concurso para un usuario.
                    ContestState result = apiInstance.apiContestsUpdateContestStateContestIdUserNamePost(body, contestId, userName);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling ContestControllerApi.apiContestsUpdateContestStateContestIdUserNamePost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiContestControllerApi();
    $body = ; // WordleState | 
    $contestId = 789; // Long | ID del concurso.
    $userName = userName_example; // String | Nombre de usuario del usuario.
    
    try {
        $result = $api_instance->apiContestsUpdateContestStateContestIdUserNamePost($body, $contestId, $userName);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling ContestControllerApi->apiContestsUpdateContestStateContestIdUserNamePost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::ContestControllerApi;
    
    my $api_instance = WWW::SwaggerClient::ContestControllerApi->new();
    my $body = WWW::SwaggerClient::Object::WordleState->new(); # WordleState | 
    my $contestId = 789; # Long | ID del concurso.
    my $userName = userName_example; # String | Nombre de usuario del usuario.
    
    eval { 
        my $result = $api_instance->apiContestsUpdateContestStateContestIdUserNamePost(body => $body, contestId => $contestId, userName => $userName);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling ContestControllerApi->apiContestsUpdateContestStateContestIdUserNamePost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.ContestControllerApi()
    body =  # WordleState | 
    contestId = 789 # Long | ID del concurso.
    userName = userName_example # String | Nombre de usuario del usuario.
    
    try: 
        # Actualiza el estado de un concurso para un usuario.
        api_response = api_instance.api_contests_update_contest_state_contest_id_user_name_post(body, contestId, userName)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling ContestControllerApi->apiContestsUpdateContestStateContestIdUserNamePost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso.

Required

userName\*

String

Nombre de usuario del usuario.

Required

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/WordleState" } } }, "required" : true }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiContestsUpdateContestStateContestIdUserNamePost\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 200 - Estado de concurso actualizado con éxito.

*   [Schema](#responses-apiContestsUpdateContestStateContestIdUserNamePost-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Estado de concurso actualizado con éxito.", "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/ContestState" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiContestsUpdateContestStateContestIdUserNamePost-200-schema-data').val(stringify(schema)); var result = $('#responses-apiContestsUpdateContestStateContestIdUserNamePost-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 400 - Error al procesar los datos.

### Status: 404 - Concurso o usuario no encontrado.

* * *

UserController
==============

apiUsersDeleteUserByNameUserNameDelete
======================================

Elimina un usuario por su nombre de usuario.

Elimina un usuario específico utilizando su nombre de usuario. Requiere el rol de Administrador.

  

    /api/users/deleteUserByName/{userName}

### Usage and SDK Samples

*   [Curl](#examples-UserController-apiUsersDeleteUserByNameUserNameDelete-0-curl)
*   [Java](#examples-UserController-apiUsersDeleteUserByNameUserNameDelete-0-java)
*   [Android](#examples-UserController-apiUsersDeleteUserByNameUserNameDelete-0-android)
*   [Obj-C](#examples-UserController-apiUsersDeleteUserByNameUserNameDelete-0-objc)
*   [JavaScript](#examples-UserController-apiUsersDeleteUserByNameUserNameDelete-0-javascript)
*   [C#](#examples-UserController-apiUsersDeleteUserByNameUserNameDelete-0-csharp)
*   [PHP](#examples-UserController-apiUsersDeleteUserByNameUserNameDelete-0-php)
*   [Perl](#examples-UserController-apiUsersDeleteUserByNameUserNameDelete-0-perl)
*   [Python](#examples-UserController-apiUsersDeleteUserByNameUserNameDelete-0-python)

    curl -X DELETE\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/users/deleteUserByName/{userName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.UserControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class UserControllerApiExample {
    
        public static void main(String[] args) {
            
            UserControllerApi apiInstance = new UserControllerApi();
            String userName = userName_example; // String | Nombre de usuario del usuario a eliminar.
            try {
                apiInstance.apiUsersDeleteUserByNameUserNameDelete(userName);
            } catch (ApiException e) {
                System.err.println("Exception when calling UserControllerApi#apiUsersDeleteUserByNameUserNameDelete");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.UserControllerApi;
    
    public class UserControllerApiExample {
    
        public static void main(String[] args) {
            UserControllerApi apiInstance = new UserControllerApi();
            String userName = userName_example; // String | Nombre de usuario del usuario a eliminar.
            try {
                apiInstance.apiUsersDeleteUserByNameUserNameDelete(userName);
            } catch (ApiException e) {
                System.err.println("Exception when calling UserControllerApi#apiUsersDeleteUserByNameUserNameDelete");
                e.printStackTrace();
            }
        }
    }

    String *userName = userName_example; // Nombre de usuario del usuario a eliminar.
    
    UserControllerApi *apiInstance = [[UserControllerApi alloc] init];
    
    // Elimina un usuario por su nombre de usuario.
    [apiInstance apiUsersDeleteUserByNameUserNameDeleteWith:userName
                  completionHandler: ^(NSError* error) {
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.UserControllerApi()
    var userName = userName_example; // {{String}} Nombre de usuario del usuario a eliminar.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully.');
      }
    };
    api.apiUsersDeleteUserByNameUserNameDelete(userName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiUsersDeleteUserByNameUserNameDeleteExample
        {
            public void main()
            {
    
                var apiInstance = new UserControllerApi();
                var userName = userName_example;  // String | Nombre de usuario del usuario a eliminar.
    
                try
                {
                    // Elimina un usuario por su nombre de usuario.
                    apiInstance.apiUsersDeleteUserByNameUserNameDelete(userName);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling UserControllerApi.apiUsersDeleteUserByNameUserNameDelete: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiUserControllerApi();
    $userName = userName_example; // String | Nombre de usuario del usuario a eliminar.
    
    try {
        $api_instance->apiUsersDeleteUserByNameUserNameDelete($userName);
    } catch (Exception $e) {
        echo 'Exception when calling UserControllerApi->apiUsersDeleteUserByNameUserNameDelete: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::UserControllerApi;
    
    my $api_instance = WWW::SwaggerClient::UserControllerApi->new();
    my $userName = userName_example; # String | Nombre de usuario del usuario a eliminar.
    
    eval { 
        $api_instance->apiUsersDeleteUserByNameUserNameDelete(userName => $userName);
    };
    if ($@) {
        warn "Exception when calling UserControllerApi->apiUsersDeleteUserByNameUserNameDelete: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.UserControllerApi()
    userName = userName_example # String | Nombre de usuario del usuario a eliminar.
    
    try: 
        # Elimina un usuario por su nombre de usuario.
        api_instance.api_users_delete_user_by_name_user_name_delete(userName)
    except ApiException as e:
        print("Exception when calling UserControllerApi->apiUsersDeleteUserByNameUserNameDelete: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

userName\*

String

Nombre de usuario del usuario a eliminar.

Required

Responses
---------

### Status: 200 - Usuario eliminado con éxito.

### Status: 401 - No autorizado.

### Status: 404 - Usuario no encontrado.

* * *

apiUsersGetAllStudentsGet
=========================

Obtiene todos los usuarios con el rol de estudiante

Obtiene una lista de todos los usuarios que tienen el rol de estudiante. Requiere el rol de Administrador.

  

    /api/users/getAllStudents

### Usage and SDK Samples

*   [Curl](#examples-UserController-apiUsersGetAllStudentsGet-0-curl)
*   [Java](#examples-UserController-apiUsersGetAllStudentsGet-0-java)
*   [Android](#examples-UserController-apiUsersGetAllStudentsGet-0-android)
*   [Obj-C](#examples-UserController-apiUsersGetAllStudentsGet-0-objc)
*   [JavaScript](#examples-UserController-apiUsersGetAllStudentsGet-0-javascript)
*   [C#](#examples-UserController-apiUsersGetAllStudentsGet-0-csharp)
*   [PHP](#examples-UserController-apiUsersGetAllStudentsGet-0-php)
*   [Perl](#examples-UserController-apiUsersGetAllStudentsGet-0-perl)
*   [Python](#examples-UserController-apiUsersGetAllStudentsGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/users/getAllStudents"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.UserControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class UserControllerApiExample {
    
        public static void main(String[] args) {
            
            UserControllerApi apiInstance = new UserControllerApi();
            try {
                array[UserStudent] result = apiInstance.apiUsersGetAllStudentsGet();
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling UserControllerApi#apiUsersGetAllStudentsGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.UserControllerApi;
    
    public class UserControllerApiExample {
    
        public static void main(String[] args) {
            UserControllerApi apiInstance = new UserControllerApi();
            try {
                array[UserStudent] result = apiInstance.apiUsersGetAllStudentsGet();
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling UserControllerApi#apiUsersGetAllStudentsGet");
                e.printStackTrace();
            }
        }
    }

    
    UserControllerApi *apiInstance = [[UserControllerApi alloc] init];
    
    // Obtiene todos los usuarios con el rol de estudiante
    [apiInstance apiUsersGetAllStudentsGetWithCompletionHandler: 
                  ^(array[UserStudent] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.UserControllerApi()
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiUsersGetAllStudentsGet(callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiUsersGetAllStudentsGetExample
        {
            public void main()
            {
    
                var apiInstance = new UserControllerApi();
    
                try
                {
                    // Obtiene todos los usuarios con el rol de estudiante
                    array[UserStudent] result = apiInstance.apiUsersGetAllStudentsGet();
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling UserControllerApi.apiUsersGetAllStudentsGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiUserControllerApi();
    
    try {
        $result = $api_instance->apiUsersGetAllStudentsGet();
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling UserControllerApi->apiUsersGetAllStudentsGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::UserControllerApi;
    
    my $api_instance = WWW::SwaggerClient::UserControllerApi->new();
    
    eval { 
        my $result = $api_instance->apiUsersGetAllStudentsGet();
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling UserControllerApi->apiUsersGetAllStudentsGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.UserControllerApi()
    
    try: 
        # Obtiene todos los usuarios con el rol de estudiante
        api_response = api_instance.api_users_get_all_students_get()
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling UserControllerApi->apiUsersGetAllStudentsGet: %s\n" % e)

Parameters
----------

Responses
---------

### Status: 200 - Lista de estudiantes obtenida con éxito.

*   [Schema](#responses-apiUsersGetAllStudentsGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de estudiantes obtenida con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/UserStudent" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiUsersGetAllStudentsGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiUsersGetAllStudentsGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 401 - No autorizado

* * *

apiUsersGetCompetitionsUserNameGet
==================================

Obtiene las competiciones en las que participa un usuario.

Obtiene la lista de competiciones en las que un usuario participa, utilizando su nombre de usuario. Requiere el rol de Alumno.

  

    /api/users/getCompetitions/{userName}

### Usage and SDK Samples

*   [Curl](#examples-UserController-apiUsersGetCompetitionsUserNameGet-0-curl)
*   [Java](#examples-UserController-apiUsersGetCompetitionsUserNameGet-0-java)
*   [Android](#examples-UserController-apiUsersGetCompetitionsUserNameGet-0-android)
*   [Obj-C](#examples-UserController-apiUsersGetCompetitionsUserNameGet-0-objc)
*   [JavaScript](#examples-UserController-apiUsersGetCompetitionsUserNameGet-0-javascript)
*   [C#](#examples-UserController-apiUsersGetCompetitionsUserNameGet-0-csharp)
*   [PHP](#examples-UserController-apiUsersGetCompetitionsUserNameGet-0-php)
*   [Perl](#examples-UserController-apiUsersGetCompetitionsUserNameGet-0-perl)
*   [Python](#examples-UserController-apiUsersGetCompetitionsUserNameGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/users/getCompetitions/{userName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.UserControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class UserControllerApiExample {
    
        public static void main(String[] args) {
            
            UserControllerApi apiInstance = new UserControllerApi();
            String userName = userName_example; // String | Nombre de usuario del usuario para obtener sus competiciones.
            try {
                array[Competition] result = apiInstance.apiUsersGetCompetitionsUserNameGet(userName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling UserControllerApi#apiUsersGetCompetitionsUserNameGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.UserControllerApi;
    
    public class UserControllerApiExample {
    
        public static void main(String[] args) {
            UserControllerApi apiInstance = new UserControllerApi();
            String userName = userName_example; // String | Nombre de usuario del usuario para obtener sus competiciones.
            try {
                array[Competition] result = apiInstance.apiUsersGetCompetitionsUserNameGet(userName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling UserControllerApi#apiUsersGetCompetitionsUserNameGet");
                e.printStackTrace();
            }
        }
    }

    String *userName = userName_example; // Nombre de usuario del usuario para obtener sus competiciones.
    
    UserControllerApi *apiInstance = [[UserControllerApi alloc] init];
    
    // Obtiene las competiciones en las que participa un usuario.
    [apiInstance apiUsersGetCompetitionsUserNameGetWith:userName
                  completionHandler: ^(array[Competition] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.UserControllerApi()
    var userName = userName_example; // {{String}} Nombre de usuario del usuario para obtener sus competiciones.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiUsersGetCompetitionsUserNameGet(userName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiUsersGetCompetitionsUserNameGetExample
        {
            public void main()
            {
    
                var apiInstance = new UserControllerApi();
                var userName = userName_example;  // String | Nombre de usuario del usuario para obtener sus competiciones.
    
                try
                {
                    // Obtiene las competiciones en las que participa un usuario.
                    array[Competition] result = apiInstance.apiUsersGetCompetitionsUserNameGet(userName);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling UserControllerApi.apiUsersGetCompetitionsUserNameGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiUserControllerApi();
    $userName = userName_example; // String | Nombre de usuario del usuario para obtener sus competiciones.
    
    try {
        $result = $api_instance->apiUsersGetCompetitionsUserNameGet($userName);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling UserControllerApi->apiUsersGetCompetitionsUserNameGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::UserControllerApi;
    
    my $api_instance = WWW::SwaggerClient::UserControllerApi->new();
    my $userName = userName_example; # String | Nombre de usuario del usuario para obtener sus competiciones.
    
    eval { 
        my $result = $api_instance->apiUsersGetCompetitionsUserNameGet(userName => $userName);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling UserControllerApi->apiUsersGetCompetitionsUserNameGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.UserControllerApi()
    userName = userName_example # String | Nombre de usuario del usuario para obtener sus competiciones.
    
    try: 
        # Obtiene las competiciones en las que participa un usuario.
        api_response = api_instance.api_users_get_competitions_user_name_get(userName)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling UserControllerApi->apiUsersGetCompetitionsUserNameGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

userName\*

String

Nombre de usuario del usuario para obtener sus competiciones.

Required

Responses
---------

### Status: 200 - Lista de competiciones obtenida con éxito.

*   [Schema](#responses-apiUsersGetCompetitionsUserNameGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de competiciones obtenida con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/Competition" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiUsersGetCompetitionsUserNameGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiUsersGetCompetitionsUserNameGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 401 - No autorizado.

### Status: 404 - Usuario no encontrado.

* * *

apiUsersGetUserEmailUserNameGet
===============================

Obtiene el correo electrónico de un usuario por su nombre de usuario.

Obtiene el correo electrónico de un usuario específico utilizando su nombre de usuario. Requiere el rol de Alumno.

  

    /api/users/getUserEmail/{userName}

### Usage and SDK Samples

*   [Curl](#examples-UserController-apiUsersGetUserEmailUserNameGet-0-curl)
*   [Java](#examples-UserController-apiUsersGetUserEmailUserNameGet-0-java)
*   [Android](#examples-UserController-apiUsersGetUserEmailUserNameGet-0-android)
*   [Obj-C](#examples-UserController-apiUsersGetUserEmailUserNameGet-0-objc)
*   [JavaScript](#examples-UserController-apiUsersGetUserEmailUserNameGet-0-javascript)
*   [C#](#examples-UserController-apiUsersGetUserEmailUserNameGet-0-csharp)
*   [PHP](#examples-UserController-apiUsersGetUserEmailUserNameGet-0-php)
*   [Perl](#examples-UserController-apiUsersGetUserEmailUserNameGet-0-perl)
*   [Python](#examples-UserController-apiUsersGetUserEmailUserNameGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/users/getUserEmail/{userName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.UserControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class UserControllerApiExample {
    
        public static void main(String[] args) {
            
            UserControllerApi apiInstance = new UserControllerApi();
            String userName = userName_example; // String | Nombre de usuario del usuario para obtener su correo electrónico.
            try {
                'String' result = apiInstance.apiUsersGetUserEmailUserNameGet(userName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling UserControllerApi#apiUsersGetUserEmailUserNameGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.UserControllerApi;
    
    public class UserControllerApiExample {
    
        public static void main(String[] args) {
            UserControllerApi apiInstance = new UserControllerApi();
            String userName = userName_example; // String | Nombre de usuario del usuario para obtener su correo electrónico.
            try {
                'String' result = apiInstance.apiUsersGetUserEmailUserNameGet(userName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling UserControllerApi#apiUsersGetUserEmailUserNameGet");
                e.printStackTrace();
            }
        }
    }

    String *userName = userName_example; // Nombre de usuario del usuario para obtener su correo electrónico.
    
    UserControllerApi *apiInstance = [[UserControllerApi alloc] init];
    
    // Obtiene el correo electrónico de un usuario por su nombre de usuario.
    [apiInstance apiUsersGetUserEmailUserNameGetWith:userName
                  completionHandler: ^('String' output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.UserControllerApi()
    var userName = userName_example; // {{String}} Nombre de usuario del usuario para obtener su correo electrónico.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiUsersGetUserEmailUserNameGet(userName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiUsersGetUserEmailUserNameGetExample
        {
            public void main()
            {
    
                var apiInstance = new UserControllerApi();
                var userName = userName_example;  // String | Nombre de usuario del usuario para obtener su correo electrónico.
    
                try
                {
                    // Obtiene el correo electrónico de un usuario por su nombre de usuario.
                    'String' result = apiInstance.apiUsersGetUserEmailUserNameGet(userName);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling UserControllerApi.apiUsersGetUserEmailUserNameGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiUserControllerApi();
    $userName = userName_example; // String | Nombre de usuario del usuario para obtener su correo electrónico.
    
    try {
        $result = $api_instance->apiUsersGetUserEmailUserNameGet($userName);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling UserControllerApi->apiUsersGetUserEmailUserNameGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::UserControllerApi;
    
    my $api_instance = WWW::SwaggerClient::UserControllerApi->new();
    my $userName = userName_example; # String | Nombre de usuario del usuario para obtener su correo electrónico.
    
    eval { 
        my $result = $api_instance->apiUsersGetUserEmailUserNameGet(userName => $userName);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling UserControllerApi->apiUsersGetUserEmailUserNameGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.UserControllerApi()
    userName = userName_example # String | Nombre de usuario del usuario para obtener su correo electrónico.
    
    try: 
        # Obtiene el correo electrónico de un usuario por su nombre de usuario.
        api_response = api_instance.api_users_get_user_email_user_name_get(userName)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling UserControllerApi->apiUsersGetUserEmailUserNameGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

userName\*

String

Nombre de usuario del usuario para obtener su correo electrónico.

Required

Responses
---------

### Status: 200 - Correo electrónico del usuario obtenido con éxito.

*   [Schema](#responses-apiUsersGetUserEmailUserNameGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Correo electrónico del usuario obtenido con éxito.", "content" : { "application/json" : { "schema" : { "type" : "string", "example" : "usuario@gmail.com", "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiUsersGetUserEmailUserNameGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiUsersGetUserEmailUserNameGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 401 - No autorizado.

### Status: 404 - Usuario no encontrado.

* * *

apiUsersUpdateUserOldUserNamePost
=================================

Actualiza la información de un usuario existente.

Actualiza el nombre de usuario, correo electrónico y contraseña de un usuario existente. Requiere el rol de Administrador.

  

    /api/users/updateUser/{oldUserName}

### Usage and SDK Samples

*   [Curl](#examples-UserController-apiUsersUpdateUserOldUserNamePost-0-curl)
*   [Java](#examples-UserController-apiUsersUpdateUserOldUserNamePost-0-java)
*   [Android](#examples-UserController-apiUsersUpdateUserOldUserNamePost-0-android)
*   [Obj-C](#examples-UserController-apiUsersUpdateUserOldUserNamePost-0-objc)
*   [JavaScript](#examples-UserController-apiUsersUpdateUserOldUserNamePost-0-javascript)
*   [C#](#examples-UserController-apiUsersUpdateUserOldUserNamePost-0-csharp)
*   [PHP](#examples-UserController-apiUsersUpdateUserOldUserNamePost-0-php)
*   [Perl](#examples-UserController-apiUsersUpdateUserOldUserNamePost-0-perl)
*   [Python](#examples-UserController-apiUsersUpdateUserOldUserNamePost-0-python)

    curl -X POST\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/users/updateUser/{oldUserName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.UserControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class UserControllerApiExample {
    
        public static void main(String[] args) {
            
            UserControllerApi apiInstance = new UserControllerApi();
            NewUser body = ; // NewUser | 
            String oldUserName = oldUserName_example; // String | Nombre de usuario actual del usuario a actualizar.
            try {
                apiInstance.apiUsersUpdateUserOldUserNamePost(body, oldUserName);
            } catch (ApiException e) {
                System.err.println("Exception when calling UserControllerApi#apiUsersUpdateUserOldUserNamePost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.UserControllerApi;
    
    public class UserControllerApiExample {
    
        public static void main(String[] args) {
            UserControllerApi apiInstance = new UserControllerApi();
            NewUser body = ; // NewUser | 
            String oldUserName = oldUserName_example; // String | Nombre de usuario actual del usuario a actualizar.
            try {
                apiInstance.apiUsersUpdateUserOldUserNamePost(body, oldUserName);
            } catch (ApiException e) {
                System.err.println("Exception when calling UserControllerApi#apiUsersUpdateUserOldUserNamePost");
                e.printStackTrace();
            }
        }
    }

    NewUser *body = ; // 
    String *oldUserName = oldUserName_example; // Nombre de usuario actual del usuario a actualizar.
    
    UserControllerApi *apiInstance = [[UserControllerApi alloc] init];
    
    // Actualiza la información de un usuario existente.
    [apiInstance apiUsersUpdateUserOldUserNamePostWith:body
        oldUserName:oldUserName
                  completionHandler: ^(NSError* error) {
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.UserControllerApi()
    var body = ; // {{NewUser}} 
    var oldUserName = oldUserName_example; // {{String}} Nombre de usuario actual del usuario a actualizar.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully.');
      }
    };
    api.apiUsersUpdateUserOldUserNamePost(bodyoldUserName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiUsersUpdateUserOldUserNamePostExample
        {
            public void main()
            {
    
                var apiInstance = new UserControllerApi();
                var body = new NewUser(); // NewUser | 
                var oldUserName = oldUserName_example;  // String | Nombre de usuario actual del usuario a actualizar.
    
                try
                {
                    // Actualiza la información de un usuario existente.
                    apiInstance.apiUsersUpdateUserOldUserNamePost(body, oldUserName);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling UserControllerApi.apiUsersUpdateUserOldUserNamePost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiUserControllerApi();
    $body = ; // NewUser | 
    $oldUserName = oldUserName_example; // String | Nombre de usuario actual del usuario a actualizar.
    
    try {
        $api_instance->apiUsersUpdateUserOldUserNamePost($body, $oldUserName);
    } catch (Exception $e) {
        echo 'Exception when calling UserControllerApi->apiUsersUpdateUserOldUserNamePost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::UserControllerApi;
    
    my $api_instance = WWW::SwaggerClient::UserControllerApi->new();
    my $body = WWW::SwaggerClient::Object::NewUser->new(); # NewUser | 
    my $oldUserName = oldUserName_example; # String | Nombre de usuario actual del usuario a actualizar.
    
    eval { 
        $api_instance->apiUsersUpdateUserOldUserNamePost(body => $body, oldUserName => $oldUserName);
    };
    if ($@) {
        warn "Exception when calling UserControllerApi->apiUsersUpdateUserOldUserNamePost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.UserControllerApi()
    body =  # NewUser | 
    oldUserName = oldUserName_example # String | Nombre de usuario actual del usuario a actualizar.
    
    try: 
        # Actualiza la información de un usuario existente.
        api_instance.api_users_update_user_old_user_name_post(body, oldUserName)
    except ApiException as e:
        print("Exception when calling UserControllerApi->apiUsersUpdateUserOldUserNamePost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

oldUserName\*

String

Nombre de usuario actual del usuario a actualizar.

Required

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/NewUser" } } }, "required" : true }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiUsersUpdateUserOldUserNamePost\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 200 - Usuario actualizado con éxito.

### Status: 401 - No autorizado.

### Status: 404 - Usuario no encontrado.

* * *

apiUsersUserNameGet
===================

Obtiene los datos de un usuario por su nombre de usuario

Obtiene la información de un usuario específico utilizando su nombre de usuario. Requiere el rol de Administrador.

  

    /api/users/{userName}

### Usage and SDK Samples

*   [Curl](#examples-UserController-apiUsersUserNameGet-0-curl)
*   [Java](#examples-UserController-apiUsersUserNameGet-0-java)
*   [Android](#examples-UserController-apiUsersUserNameGet-0-android)
*   [Obj-C](#examples-UserController-apiUsersUserNameGet-0-objc)
*   [JavaScript](#examples-UserController-apiUsersUserNameGet-0-javascript)
*   [C#](#examples-UserController-apiUsersUserNameGet-0-csharp)
*   [PHP](#examples-UserController-apiUsersUserNameGet-0-php)
*   [Perl](#examples-UserController-apiUsersUserNameGet-0-perl)
*   [Python](#examples-UserController-apiUsersUserNameGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/users/{userName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.UserControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class UserControllerApiExample {
    
        public static void main(String[] args) {
            
            UserControllerApi apiInstance = new UserControllerApi();
            String userName = userName_example; // String | Nombre de usuario del usuario a obtener.
            try {
                User result = apiInstance.apiUsersUserNameGet(userName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling UserControllerApi#apiUsersUserNameGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.UserControllerApi;
    
    public class UserControllerApiExample {
    
        public static void main(String[] args) {
            UserControllerApi apiInstance = new UserControllerApi();
            String userName = userName_example; // String | Nombre de usuario del usuario a obtener.
            try {
                User result = apiInstance.apiUsersUserNameGet(userName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling UserControllerApi#apiUsersUserNameGet");
                e.printStackTrace();
            }
        }
    }

    String *userName = userName_example; // Nombre de usuario del usuario a obtener.
    
    UserControllerApi *apiInstance = [[UserControllerApi alloc] init];
    
    // Obtiene los datos de un usuario por su nombre de usuario
    [apiInstance apiUsersUserNameGetWith:userName
                  completionHandler: ^(User output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.UserControllerApi()
    var userName = userName_example; // {{String}} Nombre de usuario del usuario a obtener.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiUsersUserNameGet(userName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiUsersUserNameGetExample
        {
            public void main()
            {
    
                var apiInstance = new UserControllerApi();
                var userName = userName_example;  // String | Nombre de usuario del usuario a obtener.
    
                try
                {
                    // Obtiene los datos de un usuario por su nombre de usuario
                    User result = apiInstance.apiUsersUserNameGet(userName);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling UserControllerApi.apiUsersUserNameGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiUserControllerApi();
    $userName = userName_example; // String | Nombre de usuario del usuario a obtener.
    
    try {
        $result = $api_instance->apiUsersUserNameGet($userName);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling UserControllerApi->apiUsersUserNameGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::UserControllerApi;
    
    my $api_instance = WWW::SwaggerClient::UserControllerApi->new();
    my $userName = userName_example; # String | Nombre de usuario del usuario a obtener.
    
    eval { 
        my $result = $api_instance->apiUsersUserNameGet(userName => $userName);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling UserControllerApi->apiUsersUserNameGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.UserControllerApi()
    userName = userName_example # String | Nombre de usuario del usuario a obtener.
    
    try: 
        # Obtiene los datos de un usuario por su nombre de usuario
        api_response = api_instance.api_users_user_name_get(userName)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling UserControllerApi->apiUsersUserNameGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

userName\*

String

Nombre de usuario del usuario a obtener.

Required

Responses
---------

### Status: 200 - Usuario encontrado y devuelto con éxito.

*   [Schema](#responses-apiUsersUserNameGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Usuario encontrado y devuelto con éxito.", "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/User" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiUsersUserNameGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiUsersUserNameGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 401 - No autorizado.

### Status: 404 - Usuario no encontrado.

* * *

getAllProfessors
================

Obtiene todos los usuarios con el rol de profesor

Devuelve una lista con todos los profesores que se encuentran en la aplicación. Requiere el rol de Administrador.

  

    /api/users/getAllProfessors

### Usage and SDK Samples

*   [Curl](#examples-UserController-getAllProfessors-0-curl)
*   [Java](#examples-UserController-getAllProfessors-0-java)
*   [Android](#examples-UserController-getAllProfessors-0-android)
*   [Obj-C](#examples-UserController-getAllProfessors-0-objc)
*   [JavaScript](#examples-UserController-getAllProfessors-0-javascript)
*   [C#](#examples-UserController-getAllProfessors-0-csharp)
*   [PHP](#examples-UserController-getAllProfessors-0-php)
*   [Perl](#examples-UserController-getAllProfessors-0-perl)
*   [Python](#examples-UserController-getAllProfessors-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/users/getAllProfessors"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.UserControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class UserControllerApiExample {
    
        public static void main(String[] args) {
            
            UserControllerApi apiInstance = new UserControllerApi();
            try {
                array[UserProfessor] result = apiInstance.getAllProfessors();
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling UserControllerApi#getAllProfessors");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.UserControllerApi;
    
    public class UserControllerApiExample {
    
        public static void main(String[] args) {
            UserControllerApi apiInstance = new UserControllerApi();
            try {
                array[UserProfessor] result = apiInstance.getAllProfessors();
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling UserControllerApi#getAllProfessors");
                e.printStackTrace();
            }
        }
    }

    
    UserControllerApi *apiInstance = [[UserControllerApi alloc] init];
    
    // Obtiene todos los usuarios con el rol de profesor
    [apiInstance getAllProfessorsWithCompletionHandler: 
                  ^(array[UserProfessor] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.UserControllerApi()
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.getAllProfessors(callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class getAllProfessorsExample
        {
            public void main()
            {
    
                var apiInstance = new UserControllerApi();
    
                try
                {
                    // Obtiene todos los usuarios con el rol de profesor
                    array[UserProfessor] result = apiInstance.getAllProfessors();
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling UserControllerApi.getAllProfessors: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiUserControllerApi();
    
    try {
        $result = $api_instance->getAllProfessors();
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling UserControllerApi->getAllProfessors: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::UserControllerApi;
    
    my $api_instance = WWW::SwaggerClient::UserControllerApi->new();
    
    eval { 
        my $result = $api_instance->getAllProfessors();
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling UserControllerApi->getAllProfessors: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.UserControllerApi()
    
    try: 
        # Obtiene todos los usuarios con el rol de profesor
        api_response = api_instance.get_all_professors()
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling UserControllerApi->getAllProfessors: %s\n" % e)

Parameters
----------

Responses
---------

### Status: 200 - Lista con los profesores obtenida con éxito

*   [Schema](#responses-getAllProfessors-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista con los profesores obtenida con éxito", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/UserProfessor" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-getAllProfessors-200-schema-data').val(stringify(schema)); var result = $('#responses-getAllProfessors-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 401 - No autorizado

* * *

WordleController
================

apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet
===============================================================

Verifica un intento de palabra Wordle.

Verifica un intento de palabra Wordle para un usuario en un concurso específico y devuelve una lista de resultados. Requiere el rol de Alumno.

  

    /api/wordle/checkWordleAttempt/{contestId}/{wordleIndex}/{word}/{userEmail}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet-0-curl)
*   [Java](#examples-WordleController-apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet-0-java)
*   [Android](#examples-WordleController-apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet-0-android)
*   [Obj-C](#examples-WordleController-apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet-0-javascript)
*   [C#](#examples-WordleController-apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet-0-csharp)
*   [PHP](#examples-WordleController-apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet-0-php)
*   [Perl](#examples-WordleController-apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet-0-perl)
*   [Python](#examples-WordleController-apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/checkWordleAttempt/{contestId}/{wordleIndex}/{word}/{userEmail}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            Integer wordleIndex = 56; // Integer | Índice de la palabra Wordle en el concurso.
            String word = word_example; // String | Palabra Wordle intentada.
            String userEmail = userEmail_example; // String | Correo electrónico del usuario.
            try {
                array['Integer'] result = apiInstance.apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet(contestId, wordleIndex, word, userEmail);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            Integer wordleIndex = 56; // Integer | Índice de la palabra Wordle en el concurso.
            String word = word_example; // String | Palabra Wordle intentada.
            String userEmail = userEmail_example; // String | Correo electrónico del usuario.
            try {
                array['Integer'] result = apiInstance.apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet(contestId, wordleIndex, word, userEmail);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet");
                e.printStackTrace();
            }
        }
    }

    Long *contestId = 789; // ID del concurso.
    Integer *wordleIndex = 56; // Índice de la palabra Wordle en el concurso.
    String *word = word_example; // Palabra Wordle intentada.
    String *userEmail = userEmail_example; // Correo electrónico del usuario.
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Verifica un intento de palabra Wordle.
    [apiInstance apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGetWith:contestId
        wordleIndex:wordleIndex
        word:word
        userEmail:userEmail
                  completionHandler: ^(array['Integer'] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var contestId = 789; // {{Long}} ID del concurso.
    var wordleIndex = 56; // {{Integer}} Índice de la palabra Wordle en el concurso.
    var word = word_example; // {{String}} Palabra Wordle intentada.
    var userEmail = userEmail_example; // {{String}} Correo electrónico del usuario.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet(contestId, wordleIndex, word, userEmail, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGetExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var contestId = 789;  // Long | ID del concurso.
                var wordleIndex = 56;  // Integer | Índice de la palabra Wordle en el concurso.
                var word = word_example;  // String | Palabra Wordle intentada.
                var userEmail = userEmail_example;  // String | Correo electrónico del usuario.
    
                try
                {
                    // Verifica un intento de palabra Wordle.
                    array['Integer'] result = apiInstance.apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet(contestId, wordleIndex, word, userEmail);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $contestId = 789; // Long | ID del concurso.
    $wordleIndex = 56; // Integer | Índice de la palabra Wordle en el concurso.
    $word = word_example; // String | Palabra Wordle intentada.
    $userEmail = userEmail_example; // String | Correo electrónico del usuario.
    
    try {
        $result = $api_instance->apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet($contestId, $wordleIndex, $word, $userEmail);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $contestId = 789; # Long | ID del concurso.
    my $wordleIndex = 56; # Integer | Índice de la palabra Wordle en el concurso.
    my $word = word_example; # String | Palabra Wordle intentada.
    my $userEmail = userEmail_example; # String | Correo electrónico del usuario.
    
    eval { 
        my $result = $api_instance->apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet(contestId => $contestId, wordleIndex => $wordleIndex, word => $word, userEmail => $userEmail);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    contestId = 789 # Long | ID del concurso.
    wordleIndex = 56 # Integer | Índice de la palabra Wordle en el concurso.
    word = word_example # String | Palabra Wordle intentada.
    userEmail = userEmail_example # String | Correo electrónico del usuario.
    
    try: 
        # Verifica un intento de palabra Wordle.
        api_response = api_instance.api_wordle_check_wordle_attempt_contest_id_wordle_index_word_user_email_get(contestId, wordleIndex, word, userEmail)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso.

Required

wordleIndex\*

Integer

Índice de la palabra Wordle en el concurso.

Required

word\*

String

Palabra Wordle intentada.

Required

userEmail\*

String

Correo electrónico del usuario.

Required

Responses
---------

### Status: 200 - Lista de resultados de la verificación del intento de Wordle.

*   [Schema](#responses-apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de resultados de la verificación del intento de Wordle.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "type" : "integer" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Concurso o usuario no encontrado.

* * *

apiWordleDeleteFoldersDelete
============================

Elimina una lista de carpetas.

Elimina una lista de carpetas utilizando sus IDs. Requiere el rol de Administrador o Profesor.

  

    /api/wordle/deleteFolders

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleDeleteFoldersDelete-0-curl)
*   [Java](#examples-WordleController-apiWordleDeleteFoldersDelete-0-java)
*   [Android](#examples-WordleController-apiWordleDeleteFoldersDelete-0-android)
*   [Obj-C](#examples-WordleController-apiWordleDeleteFoldersDelete-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleDeleteFoldersDelete-0-javascript)
*   [C#](#examples-WordleController-apiWordleDeleteFoldersDelete-0-csharp)
*   [PHP](#examples-WordleController-apiWordleDeleteFoldersDelete-0-php)
*   [Perl](#examples-WordleController-apiWordleDeleteFoldersDelete-0-perl)
*   [Python](#examples-WordleController-apiWordleDeleteFoldersDelete-0-python)

    curl -X DELETE\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/deleteFolders"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            array[Long] body = ; // array[Long] | 
            try {
                apiInstance.apiWordleDeleteFoldersDelete(body);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleDeleteFoldersDelete");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            array[Long] body = ; // array[Long] | 
            try {
                apiInstance.apiWordleDeleteFoldersDelete(body);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleDeleteFoldersDelete");
                e.printStackTrace();
            }
        }
    }

    array[Long] *body = ; // 
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Elimina una lista de carpetas.
    [apiInstance apiWordleDeleteFoldersDeleteWith:body
                  completionHandler: ^(NSError* error) {
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var body = ; // {{array[Long]}} 
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully.');
      }
    };
    api.apiWordleDeleteFoldersDelete(body, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleDeleteFoldersDeleteExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var body = new array[Long](); // array[Long] | 
    
                try
                {
                    // Elimina una lista de carpetas.
                    apiInstance.apiWordleDeleteFoldersDelete(body);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleDeleteFoldersDelete: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $body = ; // array[Long] | 
    
    try {
        $api_instance->apiWordleDeleteFoldersDelete($body);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleDeleteFoldersDelete: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $body = [WWW::SwaggerClient::Object::array[Long]->new()]; # array[Long] | 
    
    eval { 
        $api_instance->apiWordleDeleteFoldersDelete(body => $body);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleDeleteFoldersDelete: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    body =  # array[Long] | 
    
    try: 
        # Elimina una lista de carpetas.
        api_instance.api_wordle_delete_folders_delete(body)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleDeleteFoldersDelete: %s\n" % e)

Parameters
----------

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "type" : "integer", "format" : "int64" } } } }, "required" : true }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiWordleDeleteFoldersDelete\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 200 - Carpetas eliminadas con éxito.

### Status: 404 - Carpeta no encontrada.

* * *

apiWordleDeleteWordlesDelete
============================

Elimina una lista de palabras Wordle.

Elimina una lista de palabras Wordle proporcionadas en el cuerpo de la solicitud. Requiere el rol de Profesor o de Administrador

  

    /api/wordle/deleteWordles

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleDeleteWordlesDelete-0-curl)
*   [Java](#examples-WordleController-apiWordleDeleteWordlesDelete-0-java)
*   [Android](#examples-WordleController-apiWordleDeleteWordlesDelete-0-android)
*   [Obj-C](#examples-WordleController-apiWordleDeleteWordlesDelete-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleDeleteWordlesDelete-0-javascript)
*   [C#](#examples-WordleController-apiWordleDeleteWordlesDelete-0-csharp)
*   [PHP](#examples-WordleController-apiWordleDeleteWordlesDelete-0-php)
*   [Perl](#examples-WordleController-apiWordleDeleteWordlesDelete-0-perl)
*   [Python](#examples-WordleController-apiWordleDeleteWordlesDelete-0-python)

    curl -X DELETE\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/deleteWordles"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            array[Wordle] body = ; // array[Wordle] | 
            try {
                apiInstance.apiWordleDeleteWordlesDelete(body);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleDeleteWordlesDelete");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            array[Wordle] body = ; // array[Wordle] | 
            try {
                apiInstance.apiWordleDeleteWordlesDelete(body);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleDeleteWordlesDelete");
                e.printStackTrace();
            }
        }
    }

    array[Wordle] *body = ; // 
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Elimina una lista de palabras Wordle.
    [apiInstance apiWordleDeleteWordlesDeleteWith:body
                  completionHandler: ^(NSError* error) {
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var body = ; // {{array[Wordle]}} 
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully.');
      }
    };
    api.apiWordleDeleteWordlesDelete(body, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleDeleteWordlesDeleteExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var body = new array[Wordle](); // array[Wordle] | 
    
                try
                {
                    // Elimina una lista de palabras Wordle.
                    apiInstance.apiWordleDeleteWordlesDelete(body);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleDeleteWordlesDelete: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $body = ; // array[Wordle] | 
    
    try {
        $api_instance->apiWordleDeleteWordlesDelete($body);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleDeleteWordlesDelete: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $body = [WWW::SwaggerClient::Object::array[Wordle]->new()]; # array[Wordle] | 
    
    eval { 
        $api_instance->apiWordleDeleteWordlesDelete(body => $body);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleDeleteWordlesDelete: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    body =  # array[Wordle] | 
    
    try: 
        # Elimina una lista de palabras Wordle.
        api_instance.api_wordle_delete_wordles_delete(body)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleDeleteWordlesDelete: %s\n" % e)

Parameters
----------

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "application/json" : { "schema" : { "type" : "array", "description" : "Lista de palabras Wordle a eliminar.", "items" : { "$ref" : "#/components/schemas/Wordle" } } } }, "required" : true }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiWordleDeleteWordlesDelete\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 200 - Palabras Wordle eliminadas con éxito.

### Status: 404 - Palabra Wordle no encontrada.

* * *

apiWordleEditFolderOldFolderIdPost
==================================

Edita el nombre de una carpeta.

Edita el nombre de una carpeta existente utilizando su ID. Requiere el rol de Administrador o Profesor.

  

    /api/wordle/editFolder/{oldFolderId}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleEditFolderOldFolderIdPost-0-curl)
*   [Java](#examples-WordleController-apiWordleEditFolderOldFolderIdPost-0-java)
*   [Android](#examples-WordleController-apiWordleEditFolderOldFolderIdPost-0-android)
*   [Obj-C](#examples-WordleController-apiWordleEditFolderOldFolderIdPost-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleEditFolderOldFolderIdPost-0-javascript)
*   [C#](#examples-WordleController-apiWordleEditFolderOldFolderIdPost-0-csharp)
*   [PHP](#examples-WordleController-apiWordleEditFolderOldFolderIdPost-0-php)
*   [Perl](#examples-WordleController-apiWordleEditFolderOldFolderIdPost-0-perl)
*   [Python](#examples-WordleController-apiWordleEditFolderOldFolderIdPost-0-python)

    curl -X POST\
    -H "Content-Type: text/plain"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/editFolder/{oldFolderId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            String body = ; // String | 
            Long oldFolderId = 789; // Long | ID de la carpeta a editar.
            try {
                apiInstance.apiWordleEditFolderOldFolderIdPost(body, oldFolderId);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleEditFolderOldFolderIdPost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            String body = ; // String | 
            Long oldFolderId = 789; // Long | ID de la carpeta a editar.
            try {
                apiInstance.apiWordleEditFolderOldFolderIdPost(body, oldFolderId);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleEditFolderOldFolderIdPost");
                e.printStackTrace();
            }
        }
    }

    String *body = ; // 
    Long *oldFolderId = 789; // ID de la carpeta a editar.
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Edita el nombre de una carpeta.
    [apiInstance apiWordleEditFolderOldFolderIdPostWith:body
        oldFolderId:oldFolderId
                  completionHandler: ^(NSError* error) {
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var body = ; // {{String}} 
    var oldFolderId = 789; // {{Long}} ID de la carpeta a editar.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully.');
      }
    };
    api.apiWordleEditFolderOldFolderIdPost(bodyoldFolderId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleEditFolderOldFolderIdPostExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var body = new String(); // String | 
                var oldFolderId = 789;  // Long | ID de la carpeta a editar.
    
                try
                {
                    // Edita el nombre de una carpeta.
                    apiInstance.apiWordleEditFolderOldFolderIdPost(body, oldFolderId);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleEditFolderOldFolderIdPost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $body = ; // String | 
    $oldFolderId = 789; // Long | ID de la carpeta a editar.
    
    try {
        $api_instance->apiWordleEditFolderOldFolderIdPost($body, $oldFolderId);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleEditFolderOldFolderIdPost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $body = WWW::SwaggerClient::Object::String->new(); # String | 
    my $oldFolderId = 789; # Long | ID de la carpeta a editar.
    
    eval { 
        $api_instance->apiWordleEditFolderOldFolderIdPost(body => $body, oldFolderId => $oldFolderId);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleEditFolderOldFolderIdPost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    body =  # String | 
    oldFolderId = 789 # Long | ID de la carpeta a editar.
    
    try: 
        # Edita el nombre de una carpeta.
        api_instance.api_wordle_edit_folder_old_folder_id_post(body, oldFolderId)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleEditFolderOldFolderIdPost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

oldFolderId\*

Long (int64)

ID de la carpeta a editar.

Required

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "text/plain" : { "schema" : { "type" : "string", "example" : "Carpeta" } } }, "required" : true }; var schema = schemaWrapper.content\["text/plain"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiWordleEditFolderOldFolderIdPost\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 200 - Carpeta editada con éxito.

### Status: 404 - Carpeta no encontrada.

* * *

apiWordleGetContestsWhereIsUsedWordGet
======================================

Obtiene la lista de concursos donde se utiliza una palabra Wordle.

Obtiene la lista de concursos donde se utiliza una palabra Wordle específica. Requiere el rol de Administrador o Profesor.

  

    /api/wordle/getContestsWhereIsUsed/{word}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleGetContestsWhereIsUsedWordGet-0-curl)
*   [Java](#examples-WordleController-apiWordleGetContestsWhereIsUsedWordGet-0-java)
*   [Android](#examples-WordleController-apiWordleGetContestsWhereIsUsedWordGet-0-android)
*   [Obj-C](#examples-WordleController-apiWordleGetContestsWhereIsUsedWordGet-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleGetContestsWhereIsUsedWordGet-0-javascript)
*   [C#](#examples-WordleController-apiWordleGetContestsWhereIsUsedWordGet-0-csharp)
*   [PHP](#examples-WordleController-apiWordleGetContestsWhereIsUsedWordGet-0-php)
*   [Perl](#examples-WordleController-apiWordleGetContestsWhereIsUsedWordGet-0-perl)
*   [Python](#examples-WordleController-apiWordleGetContestsWhereIsUsedWordGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/getContestsWhereIsUsed/{word}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            String word = word_example; // String | Palabra Wordle a buscar.
            try {
                array[Contest] result = apiInstance.apiWordleGetContestsWhereIsUsedWordGet(word);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetContestsWhereIsUsedWordGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            String word = word_example; // String | Palabra Wordle a buscar.
            try {
                array[Contest] result = apiInstance.apiWordleGetContestsWhereIsUsedWordGet(word);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetContestsWhereIsUsedWordGet");
                e.printStackTrace();
            }
        }
    }

    String *word = word_example; // Palabra Wordle a buscar.
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Obtiene la lista de concursos donde se utiliza una palabra Wordle.
    [apiInstance apiWordleGetContestsWhereIsUsedWordGetWith:word
                  completionHandler: ^(array[Contest] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var word = word_example; // {{String}} Palabra Wordle a buscar.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiWordleGetContestsWhereIsUsedWordGet(word, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleGetContestsWhereIsUsedWordGetExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var word = word_example;  // String | Palabra Wordle a buscar.
    
                try
                {
                    // Obtiene la lista de concursos donde se utiliza una palabra Wordle.
                    array[Contest] result = apiInstance.apiWordleGetContestsWhereIsUsedWordGet(word);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleGetContestsWhereIsUsedWordGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $word = word_example; // String | Palabra Wordle a buscar.
    
    try {
        $result = $api_instance->apiWordleGetContestsWhereIsUsedWordGet($word);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleGetContestsWhereIsUsedWordGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $word = word_example; # String | Palabra Wordle a buscar.
    
    eval { 
        my $result = $api_instance->apiWordleGetContestsWhereIsUsedWordGet(word => $word);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleGetContestsWhereIsUsedWordGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    word = word_example # String | Palabra Wordle a buscar.
    
    try: 
        # Obtiene la lista de concursos donde se utiliza una palabra Wordle.
        api_response = api_instance.api_wordle_get_contests_where_is_used_word_get(word)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleGetContestsWhereIsUsedWordGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

word\*

String

Palabra Wordle a buscar.

Required

Responses
---------

### Status: 200 - Lista de concursos obtenida con éxito.

*   [Schema](#responses-apiWordleGetContestsWhereIsUsedWordGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de concursos obtenida con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/Contest" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiWordleGetContestsWhereIsUsedWordGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiWordleGetContestsWhereIsUsedWordGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Palabra Wordle no encontrada.

* * *

apiWordleGetFolderFolderIdGet
=============================

Obtiene una carpeta por su ID.

Obtiene una carpeta específica utilizando su ID. Requiere el rol de Profesor o Administrador.

  

    /api/wordle/getFolder/{folderId}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleGetFolderFolderIdGet-0-curl)
*   [Java](#examples-WordleController-apiWordleGetFolderFolderIdGet-0-java)
*   [Android](#examples-WordleController-apiWordleGetFolderFolderIdGet-0-android)
*   [Obj-C](#examples-WordleController-apiWordleGetFolderFolderIdGet-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleGetFolderFolderIdGet-0-javascript)
*   [C#](#examples-WordleController-apiWordleGetFolderFolderIdGet-0-csharp)
*   [PHP](#examples-WordleController-apiWordleGetFolderFolderIdGet-0-php)
*   [Perl](#examples-WordleController-apiWordleGetFolderFolderIdGet-0-perl)
*   [Python](#examples-WordleController-apiWordleGetFolderFolderIdGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/getFolder/{folderId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            Long folderId = 789; // Long | ID de la carpeta a obtener.
            try {
                FolderDTO result = apiInstance.apiWordleGetFolderFolderIdGet(folderId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetFolderFolderIdGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            Long folderId = 789; // Long | ID de la carpeta a obtener.
            try {
                FolderDTO result = apiInstance.apiWordleGetFolderFolderIdGet(folderId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetFolderFolderIdGet");
                e.printStackTrace();
            }
        }
    }

    Long *folderId = 789; // ID de la carpeta a obtener.
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Obtiene una carpeta por su ID.
    [apiInstance apiWordleGetFolderFolderIdGetWith:folderId
                  completionHandler: ^(FolderDTO output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var folderId = 789; // {{Long}} ID de la carpeta a obtener.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiWordleGetFolderFolderIdGet(folderId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleGetFolderFolderIdGetExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var folderId = 789;  // Long | ID de la carpeta a obtener.
    
                try
                {
                    // Obtiene una carpeta por su ID.
                    FolderDTO result = apiInstance.apiWordleGetFolderFolderIdGet(folderId);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleGetFolderFolderIdGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $folderId = 789; // Long | ID de la carpeta a obtener.
    
    try {
        $result = $api_instance->apiWordleGetFolderFolderIdGet($folderId);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleGetFolderFolderIdGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $folderId = 789; # Long | ID de la carpeta a obtener.
    
    eval { 
        my $result = $api_instance->apiWordleGetFolderFolderIdGet(folderId => $folderId);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleGetFolderFolderIdGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    folderId = 789 # Long | ID de la carpeta a obtener.
    
    try: 
        # Obtiene una carpeta por su ID.
        api_response = api_instance.api_wordle_get_folder_folder_id_get(folderId)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleGetFolderFolderIdGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

folderId\*

Long (int64)

ID de la carpeta a obtener.

Required

Responses
---------

### Status: 200 - Carpeta obtenida con éxito.

*   [Schema](#responses-apiWordleGetFolderFolderIdGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Carpeta obtenida con éxito.", "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/FolderDTO" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiWordleGetFolderFolderIdGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiWordleGetFolderFolderIdGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Carpeta no encontrada.

* * *

apiWordleGetFoldersByProfessorProfessorNameGet
==============================================

Obtiene la lista de carpetas de un profesor.

Obtiene la lista de carpetas creadas por un profesor específico, excluyendo las carpetas anidadas. Requiere el rol de Administrador o Profesor.

  

    /api/wordle/getFoldersByProfessor/{professorName}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleGetFoldersByProfessorProfessorNameGet-0-curl)
*   [Java](#examples-WordleController-apiWordleGetFoldersByProfessorProfessorNameGet-0-java)
*   [Android](#examples-WordleController-apiWordleGetFoldersByProfessorProfessorNameGet-0-android)
*   [Obj-C](#examples-WordleController-apiWordleGetFoldersByProfessorProfessorNameGet-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleGetFoldersByProfessorProfessorNameGet-0-javascript)
*   [C#](#examples-WordleController-apiWordleGetFoldersByProfessorProfessorNameGet-0-csharp)
*   [PHP](#examples-WordleController-apiWordleGetFoldersByProfessorProfessorNameGet-0-php)
*   [Perl](#examples-WordleController-apiWordleGetFoldersByProfessorProfessorNameGet-0-perl)
*   [Python](#examples-WordleController-apiWordleGetFoldersByProfessorProfessorNameGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/getFoldersByProfessor/{professorName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            String professorName = professorName_example; // String | Nombre del profesor.
            try {
                array[FolderDTO] result = apiInstance.apiWordleGetFoldersByProfessorProfessorNameGet(professorName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetFoldersByProfessorProfessorNameGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            String professorName = professorName_example; // String | Nombre del profesor.
            try {
                array[FolderDTO] result = apiInstance.apiWordleGetFoldersByProfessorProfessorNameGet(professorName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetFoldersByProfessorProfessorNameGet");
                e.printStackTrace();
            }
        }
    }

    String *professorName = professorName_example; // Nombre del profesor.
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Obtiene la lista de carpetas de un profesor.
    [apiInstance apiWordleGetFoldersByProfessorProfessorNameGetWith:professorName
                  completionHandler: ^(array[FolderDTO] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var professorName = professorName_example; // {{String}} Nombre del profesor.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiWordleGetFoldersByProfessorProfessorNameGet(professorName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleGetFoldersByProfessorProfessorNameGetExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var professorName = professorName_example;  // String | Nombre del profesor.
    
                try
                {
                    // Obtiene la lista de carpetas de un profesor.
                    array[FolderDTO] result = apiInstance.apiWordleGetFoldersByProfessorProfessorNameGet(professorName);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleGetFoldersByProfessorProfessorNameGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $professorName = professorName_example; // String | Nombre del profesor.
    
    try {
        $result = $api_instance->apiWordleGetFoldersByProfessorProfessorNameGet($professorName);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleGetFoldersByProfessorProfessorNameGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $professorName = professorName_example; # String | Nombre del profesor.
    
    eval { 
        my $result = $api_instance->apiWordleGetFoldersByProfessorProfessorNameGet(professorName => $professorName);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleGetFoldersByProfessorProfessorNameGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    professorName = professorName_example # String | Nombre del profesor.
    
    try: 
        # Obtiene la lista de carpetas de un profesor.
        api_response = api_instance.api_wordle_get_folders_by_professor_professor_name_get(professorName)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleGetFoldersByProfessorProfessorNameGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

professorName\*

String

Nombre del profesor.

Required

Responses
---------

### Status: 200 - Lista de carpetas obtenida con éxito.

*   [Schema](#responses-apiWordleGetFoldersByProfessorProfessorNameGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de carpetas obtenida con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/FolderDTO" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiWordleGetFoldersByProfessorProfessorNameGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiWordleGetFoldersByProfessorProfessorNameGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Profesor no encontrado.

* * *

apiWordleGetFoldersInsideFolderFolderIdGet
==========================================

Obtiene la lista de carpetas dentro de una carpeta.

Obtiene la lista de carpetas que se encuentran dentro de la carpeta especificada por su ID. Requiere el rol de Profesor o Administrador.

  

    /api/wordle/getFoldersInsideFolder/{folderId}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleGetFoldersInsideFolderFolderIdGet-0-curl)
*   [Java](#examples-WordleController-apiWordleGetFoldersInsideFolderFolderIdGet-0-java)
*   [Android](#examples-WordleController-apiWordleGetFoldersInsideFolderFolderIdGet-0-android)
*   [Obj-C](#examples-WordleController-apiWordleGetFoldersInsideFolderFolderIdGet-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleGetFoldersInsideFolderFolderIdGet-0-javascript)
*   [C#](#examples-WordleController-apiWordleGetFoldersInsideFolderFolderIdGet-0-csharp)
*   [PHP](#examples-WordleController-apiWordleGetFoldersInsideFolderFolderIdGet-0-php)
*   [Perl](#examples-WordleController-apiWordleGetFoldersInsideFolderFolderIdGet-0-perl)
*   [Python](#examples-WordleController-apiWordleGetFoldersInsideFolderFolderIdGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/getFoldersInsideFolder/{folderId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            Long folderId = 789; // Long | ID de la carpeta padre.
            try {
                array[Folder] result = apiInstance.apiWordleGetFoldersInsideFolderFolderIdGet(folderId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetFoldersInsideFolderFolderIdGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            Long folderId = 789; // Long | ID de la carpeta padre.
            try {
                array[Folder] result = apiInstance.apiWordleGetFoldersInsideFolderFolderIdGet(folderId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetFoldersInsideFolderFolderIdGet");
                e.printStackTrace();
            }
        }
    }

    Long *folderId = 789; // ID de la carpeta padre.
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Obtiene la lista de carpetas dentro de una carpeta.
    [apiInstance apiWordleGetFoldersInsideFolderFolderIdGetWith:folderId
                  completionHandler: ^(array[Folder] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var folderId = 789; // {{Long}} ID de la carpeta padre.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiWordleGetFoldersInsideFolderFolderIdGet(folderId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleGetFoldersInsideFolderFolderIdGetExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var folderId = 789;  // Long | ID de la carpeta padre.
    
                try
                {
                    // Obtiene la lista de carpetas dentro de una carpeta.
                    array[Folder] result = apiInstance.apiWordleGetFoldersInsideFolderFolderIdGet(folderId);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleGetFoldersInsideFolderFolderIdGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $folderId = 789; // Long | ID de la carpeta padre.
    
    try {
        $result = $api_instance->apiWordleGetFoldersInsideFolderFolderIdGet($folderId);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleGetFoldersInsideFolderFolderIdGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $folderId = 789; # Long | ID de la carpeta padre.
    
    eval { 
        my $result = $api_instance->apiWordleGetFoldersInsideFolderFolderIdGet(folderId => $folderId);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleGetFoldersInsideFolderFolderIdGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    folderId = 789 # Long | ID de la carpeta padre.
    
    try: 
        # Obtiene la lista de carpetas dentro de una carpeta.
        api_response = api_instance.api_wordle_get_folders_inside_folder_folder_id_get(folderId)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleGetFoldersInsideFolderFolderIdGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

folderId\*

Long (int64)

ID de la carpeta padre.

Required

Responses
---------

### Status: 200 - Lista de carpetas obtenida con éxito.

*   [Schema](#responses-apiWordleGetFoldersInsideFolderFolderIdGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de carpetas obtenida con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/Folder" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiWordleGetFoldersInsideFolderFolderIdGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiWordleGetFoldersInsideFolderFolderIdGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Carpeta padre no encontrada.

* * *

apiWordleGetWordleInContestContestIdWordleIndexGet
==================================================

Obtiene una palabra Wordle específica de un concurso.

Obtiene una palabra Wordle específica de un concurso, verificando que el juego esté terminado para el usuario autenticado. Requiere el rol de Alumno.

  

    /api/wordle/getWordleInContest/{contestId}/{wordleIndex}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleGetWordleInContestContestIdWordleIndexGet-0-curl)
*   [Java](#examples-WordleController-apiWordleGetWordleInContestContestIdWordleIndexGet-0-java)
*   [Android](#examples-WordleController-apiWordleGetWordleInContestContestIdWordleIndexGet-0-android)
*   [Obj-C](#examples-WordleController-apiWordleGetWordleInContestContestIdWordleIndexGet-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleGetWordleInContestContestIdWordleIndexGet-0-javascript)
*   [C#](#examples-WordleController-apiWordleGetWordleInContestContestIdWordleIndexGet-0-csharp)
*   [PHP](#examples-WordleController-apiWordleGetWordleInContestContestIdWordleIndexGet-0-php)
*   [Perl](#examples-WordleController-apiWordleGetWordleInContestContestIdWordleIndexGet-0-perl)
*   [Python](#examples-WordleController-apiWordleGetWordleInContestContestIdWordleIndexGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/getWordleInContest/{contestId}/{wordleIndex}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            Integer wordleIndex = 56; // Integer | Índice de la palabra Wordle en el concurso.
            try {
                Wordle result = apiInstance.apiWordleGetWordleInContestContestIdWordleIndexGet(contestId, wordleIndex);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetWordleInContestContestIdWordleIndexGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            Integer wordleIndex = 56; // Integer | Índice de la palabra Wordle en el concurso.
            try {
                Wordle result = apiInstance.apiWordleGetWordleInContestContestIdWordleIndexGet(contestId, wordleIndex);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetWordleInContestContestIdWordleIndexGet");
                e.printStackTrace();
            }
        }
    }

    Long *contestId = 789; // ID del concurso.
    Integer *wordleIndex = 56; // Índice de la palabra Wordle en el concurso.
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Obtiene una palabra Wordle específica de un concurso.
    [apiInstance apiWordleGetWordleInContestContestIdWordleIndexGetWith:contestId
        wordleIndex:wordleIndex
                  completionHandler: ^(Wordle output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var contestId = 789; // {{Long}} ID del concurso.
    var wordleIndex = 56; // {{Integer}} Índice de la palabra Wordle en el concurso.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiWordleGetWordleInContestContestIdWordleIndexGet(contestId, wordleIndex, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleGetWordleInContestContestIdWordleIndexGetExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var contestId = 789;  // Long | ID del concurso.
                var wordleIndex = 56;  // Integer | Índice de la palabra Wordle en el concurso.
    
                try
                {
                    // Obtiene una palabra Wordle específica de un concurso.
                    Wordle result = apiInstance.apiWordleGetWordleInContestContestIdWordleIndexGet(contestId, wordleIndex);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleGetWordleInContestContestIdWordleIndexGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $contestId = 789; // Long | ID del concurso.
    $wordleIndex = 56; // Integer | Índice de la palabra Wordle en el concurso.
    
    try {
        $result = $api_instance->apiWordleGetWordleInContestContestIdWordleIndexGet($contestId, $wordleIndex);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleGetWordleInContestContestIdWordleIndexGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $contestId = 789; # Long | ID del concurso.
    my $wordleIndex = 56; # Integer | Índice de la palabra Wordle en el concurso.
    
    eval { 
        my $result = $api_instance->apiWordleGetWordleInContestContestIdWordleIndexGet(contestId => $contestId, wordleIndex => $wordleIndex);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleGetWordleInContestContestIdWordleIndexGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    contestId = 789 # Long | ID del concurso.
    wordleIndex = 56 # Integer | Índice de la palabra Wordle en el concurso.
    
    try: 
        # Obtiene una palabra Wordle específica de un concurso.
        api_response = api_instance.api_wordle_get_wordle_in_contest_contest_id_wordle_index_get(contestId, wordleIndex)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleGetWordleInContestContestIdWordleIndexGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso.

Required

wordleIndex\*

Integer

Índice de la palabra Wordle en el concurso.

Required

Responses
---------

### Status: 200 - Palabra Wordle obtenida con éxito.

*   [Schema](#responses-apiWordleGetWordleInContestContestIdWordleIndexGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Palabra Wordle obtenida con éxito.", "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/Wordle" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiWordleGetWordleInContestContestIdWordleIndexGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiWordleGetWordleInContestContestIdWordleIndexGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 401 - Juego no terminado para el usuario autenticado.

### Status: 404 - Concurso no encontrado.

* * *

apiWordleGetWordlesByContestContestIdGet
========================================

Obtiene la lista de palabras Wordle asociadas a un concurso.

Obtiene la lista de palabras Wordle asociadas a un concurso específico. Para estudiantes, verifica si todos los juegos están terminados antes de retornar la lista.

  

    /api/wordle/getWordlesByContest/{contestId}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleGetWordlesByContestContestIdGet-0-curl)
*   [Java](#examples-WordleController-apiWordleGetWordlesByContestContestIdGet-0-java)
*   [Android](#examples-WordleController-apiWordleGetWordlesByContestContestIdGet-0-android)
*   [Obj-C](#examples-WordleController-apiWordleGetWordlesByContestContestIdGet-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleGetWordlesByContestContestIdGet-0-javascript)
*   [C#](#examples-WordleController-apiWordleGetWordlesByContestContestIdGet-0-csharp)
*   [PHP](#examples-WordleController-apiWordleGetWordlesByContestContestIdGet-0-php)
*   [Perl](#examples-WordleController-apiWordleGetWordlesByContestContestIdGet-0-perl)
*   [Python](#examples-WordleController-apiWordleGetWordlesByContestContestIdGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/getWordlesByContest/{contestId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            try {
                array[Wordle] result = apiInstance.apiWordleGetWordlesByContestContestIdGet(contestId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetWordlesByContestContestIdGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            Long contestId = 789; // Long | ID del concurso.
            try {
                array[Wordle] result = apiInstance.apiWordleGetWordlesByContestContestIdGet(contestId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetWordlesByContestContestIdGet");
                e.printStackTrace();
            }
        }
    }

    Long *contestId = 789; // ID del concurso.
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Obtiene la lista de palabras Wordle asociadas a un concurso.
    [apiInstance apiWordleGetWordlesByContestContestIdGetWith:contestId
                  completionHandler: ^(array[Wordle] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var contestId = 789; // {{Long}} ID del concurso.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiWordleGetWordlesByContestContestIdGet(contestId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleGetWordlesByContestContestIdGetExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var contestId = 789;  // Long | ID del concurso.
    
                try
                {
                    // Obtiene la lista de palabras Wordle asociadas a un concurso.
                    array[Wordle] result = apiInstance.apiWordleGetWordlesByContestContestIdGet(contestId);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleGetWordlesByContestContestIdGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $contestId = 789; // Long | ID del concurso.
    
    try {
        $result = $api_instance->apiWordleGetWordlesByContestContestIdGet($contestId);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleGetWordlesByContestContestIdGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $contestId = 789; # Long | ID del concurso.
    
    eval { 
        my $result = $api_instance->apiWordleGetWordlesByContestContestIdGet(contestId => $contestId);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleGetWordlesByContestContestIdGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    contestId = 789 # Long | ID del concurso.
    
    try: 
        # Obtiene la lista de palabras Wordle asociadas a un concurso.
        api_response = api_instance.api_wordle_get_wordles_by_contest_contest_id_get(contestId)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleGetWordlesByContestContestIdGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso.

Required

Responses
---------

### Status: 200 - Lista de palabras Wordle obtenida con éxito.

*   [Schema](#responses-apiWordleGetWordlesByContestContestIdGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de palabras Wordle obtenida con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/Wordle" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiWordleGetWordlesByContestContestIdGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiWordleGetWordlesByContestContestIdGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 400 - Juegos no terminados (solo para estudiantes).

### Status: 404 - Concurso no encontrado.

* * *

apiWordleGetWordlesByProfessorProfessorNameGet
==============================================

Obtiene la lista de palabras Wordle creadas por un profesor.

Obtiene la lista de Wordles creados por un profesor específico, excluyendo las que están en carpetas. Requiere el rol de Administrador o de Profesor.

  

    /api/wordle/getWordlesByProfessor/{professorName}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleGetWordlesByProfessorProfessorNameGet-0-curl)
*   [Java](#examples-WordleController-apiWordleGetWordlesByProfessorProfessorNameGet-0-java)
*   [Android](#examples-WordleController-apiWordleGetWordlesByProfessorProfessorNameGet-0-android)
*   [Obj-C](#examples-WordleController-apiWordleGetWordlesByProfessorProfessorNameGet-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleGetWordlesByProfessorProfessorNameGet-0-javascript)
*   [C#](#examples-WordleController-apiWordleGetWordlesByProfessorProfessorNameGet-0-csharp)
*   [PHP](#examples-WordleController-apiWordleGetWordlesByProfessorProfessorNameGet-0-php)
*   [Perl](#examples-WordleController-apiWordleGetWordlesByProfessorProfessorNameGet-0-perl)
*   [Python](#examples-WordleController-apiWordleGetWordlesByProfessorProfessorNameGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/getWordlesByProfessor/{professorName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            String professorName = professorName_example; // String | Nombre del profesor.
            try {
                array[WordleDTO] result = apiInstance.apiWordleGetWordlesByProfessorProfessorNameGet(professorName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetWordlesByProfessorProfessorNameGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            String professorName = professorName_example; // String | Nombre del profesor.
            try {
                array[WordleDTO] result = apiInstance.apiWordleGetWordlesByProfessorProfessorNameGet(professorName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetWordlesByProfessorProfessorNameGet");
                e.printStackTrace();
            }
        }
    }

    String *professorName = professorName_example; // Nombre del profesor.
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Obtiene la lista de palabras Wordle creadas por un profesor.
    [apiInstance apiWordleGetWordlesByProfessorProfessorNameGetWith:professorName
                  completionHandler: ^(array[WordleDTO] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var professorName = professorName_example; // {{String}} Nombre del profesor.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiWordleGetWordlesByProfessorProfessorNameGet(professorName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleGetWordlesByProfessorProfessorNameGetExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var professorName = professorName_example;  // String | Nombre del profesor.
    
                try
                {
                    // Obtiene la lista de palabras Wordle creadas por un profesor.
                    array[WordleDTO] result = apiInstance.apiWordleGetWordlesByProfessorProfessorNameGet(professorName);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleGetWordlesByProfessorProfessorNameGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $professorName = professorName_example; // String | Nombre del profesor.
    
    try {
        $result = $api_instance->apiWordleGetWordlesByProfessorProfessorNameGet($professorName);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleGetWordlesByProfessorProfessorNameGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $professorName = professorName_example; # String | Nombre del profesor.
    
    eval { 
        my $result = $api_instance->apiWordleGetWordlesByProfessorProfessorNameGet(professorName => $professorName);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleGetWordlesByProfessorProfessorNameGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    professorName = professorName_example # String | Nombre del profesor.
    
    try: 
        # Obtiene la lista de palabras Wordle creadas por un profesor.
        api_response = api_instance.api_wordle_get_wordles_by_professor_professor_name_get(professorName)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleGetWordlesByProfessorProfessorNameGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

professorName\*

String

Nombre del profesor.

Required

Responses
---------

### Status: 200 - Lista de palabras Wordle obtenida con éxito.

*   [Schema](#responses-apiWordleGetWordlesByProfessorProfessorNameGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de palabras Wordle obtenida con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/WordleDTO" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiWordleGetWordlesByProfessorProfessorNameGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiWordleGetWordlesByProfessorProfessorNameGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Profesor no encontrado.

* * *

apiWordleGetWordlesInsideFolderFolderIdGet
==========================================

Obtiene la lista de palabras Wordle dentro de una carpeta.

Obtiene la lista de palabras Wordle que se encuentran dentro de la carpeta especificada por su ID. Requiere el rol de Profesor o Administrador.

  

    /api/wordle/getWordlesInsideFolder/{folderId}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleGetWordlesInsideFolderFolderIdGet-0-curl)
*   [Java](#examples-WordleController-apiWordleGetWordlesInsideFolderFolderIdGet-0-java)
*   [Android](#examples-WordleController-apiWordleGetWordlesInsideFolderFolderIdGet-0-android)
*   [Obj-C](#examples-WordleController-apiWordleGetWordlesInsideFolderFolderIdGet-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleGetWordlesInsideFolderFolderIdGet-0-javascript)
*   [C#](#examples-WordleController-apiWordleGetWordlesInsideFolderFolderIdGet-0-csharp)
*   [PHP](#examples-WordleController-apiWordleGetWordlesInsideFolderFolderIdGet-0-php)
*   [Perl](#examples-WordleController-apiWordleGetWordlesInsideFolderFolderIdGet-0-perl)
*   [Python](#examples-WordleController-apiWordleGetWordlesInsideFolderFolderIdGet-0-python)

    curl -X GET\
    -H "Accept: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/getWordlesInsideFolder/{folderId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            Long folderId = 789; // Long | ID de la carpeta.
            try {
                array[Wordle] result = apiInstance.apiWordleGetWordlesInsideFolderFolderIdGet(folderId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetWordlesInsideFolderFolderIdGet");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            Long folderId = 789; // Long | ID de la carpeta.
            try {
                array[Wordle] result = apiInstance.apiWordleGetWordlesInsideFolderFolderIdGet(folderId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleGetWordlesInsideFolderFolderIdGet");
                e.printStackTrace();
            }
        }
    }

    Long *folderId = 789; // ID de la carpeta.
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Obtiene la lista de palabras Wordle dentro de una carpeta.
    [apiInstance apiWordleGetWordlesInsideFolderFolderIdGetWith:folderId
                  completionHandler: ^(array[Wordle] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var folderId = 789; // {{Long}} ID de la carpeta.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiWordleGetWordlesInsideFolderFolderIdGet(folderId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleGetWordlesInsideFolderFolderIdGetExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var folderId = 789;  // Long | ID de la carpeta.
    
                try
                {
                    // Obtiene la lista de palabras Wordle dentro de una carpeta.
                    array[Wordle] result = apiInstance.apiWordleGetWordlesInsideFolderFolderIdGet(folderId);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleGetWordlesInsideFolderFolderIdGet: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $folderId = 789; // Long | ID de la carpeta.
    
    try {
        $result = $api_instance->apiWordleGetWordlesInsideFolderFolderIdGet($folderId);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleGetWordlesInsideFolderFolderIdGet: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $folderId = 789; # Long | ID de la carpeta.
    
    eval { 
        my $result = $api_instance->apiWordleGetWordlesInsideFolderFolderIdGet(folderId => $folderId);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleGetWordlesInsideFolderFolderIdGet: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    folderId = 789 # Long | ID de la carpeta.
    
    try: 
        # Obtiene la lista de palabras Wordle dentro de una carpeta.
        api_response = api_instance.api_wordle_get_wordles_inside_folder_folder_id_get(folderId)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleGetWordlesInsideFolderFolderIdGet: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

folderId\*

Long (int64)

ID de la carpeta.

Required

Responses
---------

### Status: 200 - Lista de palabras Wordle obtenida con éxito.

*   [Schema](#responses-apiWordleGetWordlesInsideFolderFolderIdGet-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Lista de palabras Wordle obtenida con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/Wordle" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiWordleGetWordlesInsideFolderFolderIdGet-200-schema-data').val(stringify(schema)); var result = $('#responses-apiWordleGetWordlesInsideFolderFolderIdGet-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Carpeta no encontrada.

* * *

apiWordleMoveToFolderFolderIdPost
=================================

Mueve una lista de palabras Wordle a una carpeta.

Mueve una lista de palabras Wordle a la carpeta especificada por su ID. Si folderId es 0, mueve las palabras fuera de cualquier carpeta. Requiere el rol de Profesor o de Administrador.

  

    /api/wordle/moveToFolder/{folderId}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleMoveToFolderFolderIdPost-0-curl)
*   [Java](#examples-WordleController-apiWordleMoveToFolderFolderIdPost-0-java)
*   [Android](#examples-WordleController-apiWordleMoveToFolderFolderIdPost-0-android)
*   [Obj-C](#examples-WordleController-apiWordleMoveToFolderFolderIdPost-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleMoveToFolderFolderIdPost-0-javascript)
*   [C#](#examples-WordleController-apiWordleMoveToFolderFolderIdPost-0-csharp)
*   [PHP](#examples-WordleController-apiWordleMoveToFolderFolderIdPost-0-php)
*   [Perl](#examples-WordleController-apiWordleMoveToFolderFolderIdPost-0-perl)
*   [Python](#examples-WordleController-apiWordleMoveToFolderFolderIdPost-0-python)

    curl -X POST\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/moveToFolder/{folderId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            array[String] body = ; // array[String] | 
            Long folderId = 789; // Long | ID de la carpeta destino (0 para mover fuera de carpetas).
            try {
                apiInstance.apiWordleMoveToFolderFolderIdPost(body, folderId);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleMoveToFolderFolderIdPost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            array[String] body = ; // array[String] | 
            Long folderId = 789; // Long | ID de la carpeta destino (0 para mover fuera de carpetas).
            try {
                apiInstance.apiWordleMoveToFolderFolderIdPost(body, folderId);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleMoveToFolderFolderIdPost");
                e.printStackTrace();
            }
        }
    }

    array[String] *body = ; // 
    Long *folderId = 789; // ID de la carpeta destino (0 para mover fuera de carpetas).
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Mueve una lista de palabras Wordle a una carpeta.
    [apiInstance apiWordleMoveToFolderFolderIdPostWith:body
        folderId:folderId
                  completionHandler: ^(NSError* error) {
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var body = ; // {{array[String]}} 
    var folderId = 789; // {{Long}} ID de la carpeta destino (0 para mover fuera de carpetas).
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully.');
      }
    };
    api.apiWordleMoveToFolderFolderIdPost(bodyfolderId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleMoveToFolderFolderIdPostExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var body = new array[String](); // array[String] | 
                var folderId = 789;  // Long | ID de la carpeta destino (0 para mover fuera de carpetas).
    
                try
                {
                    // Mueve una lista de palabras Wordle a una carpeta.
                    apiInstance.apiWordleMoveToFolderFolderIdPost(body, folderId);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleMoveToFolderFolderIdPost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $body = ; // array[String] | 
    $folderId = 789; // Long | ID de la carpeta destino (0 para mover fuera de carpetas).
    
    try {
        $api_instance->apiWordleMoveToFolderFolderIdPost($body, $folderId);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleMoveToFolderFolderIdPost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $body = [WWW::SwaggerClient::Object::array[String]->new()]; # array[String] | 
    my $folderId = 789; # Long | ID de la carpeta destino (0 para mover fuera de carpetas).
    
    eval { 
        $api_instance->apiWordleMoveToFolderFolderIdPost(body => $body, folderId => $folderId);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleMoveToFolderFolderIdPost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    body =  # array[String] | 
    folderId = 789 # Long | ID de la carpeta destino (0 para mover fuera de carpetas).
    
    try: 
        # Mueve una lista de palabras Wordle a una carpeta.
        api_instance.api_wordle_move_to_folder_folder_id_post(body, folderId)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleMoveToFolderFolderIdPost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

folderId\*

Long (int64)

ID de la carpeta destino (0 para mover fuera de carpetas).

Required

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "application/json" : { "schema" : { "type" : "array", "description" : "Lista de palabras Wordle a mover.", "items" : { "type" : "string" } } } }, "required" : true }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiWordleMoveToFolderFolderIdPost\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 200 - Palabras Wordle movidas con éxito.

### Status: 404 - Carpeta o palabra Wordle no encontrada.

* * *

apiWordleNewFolderFolderNamePost
================================

Crea una nueva carpeta.

Crea una nueva carpeta con el nombre y el profesor especificados. Requiere el rol de Administrador o Profesor.

  

    /api/wordle/newFolder/{folderName}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleNewFolderFolderNamePost-0-curl)
*   [Java](#examples-WordleController-apiWordleNewFolderFolderNamePost-0-java)
*   [Android](#examples-WordleController-apiWordleNewFolderFolderNamePost-0-android)
*   [Obj-C](#examples-WordleController-apiWordleNewFolderFolderNamePost-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleNewFolderFolderNamePost-0-javascript)
*   [C#](#examples-WordleController-apiWordleNewFolderFolderNamePost-0-csharp)
*   [PHP](#examples-WordleController-apiWordleNewFolderFolderNamePost-0-php)
*   [Perl](#examples-WordleController-apiWordleNewFolderFolderNamePost-0-perl)
*   [Python](#examples-WordleController-apiWordleNewFolderFolderNamePost-0-python)

    curl -X POST\
    -H "Accept: application/json"\
    -H "Content-Type: text/plain"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/newFolder/{folderName}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            String body = ; // String | 
            String folderName = folderName_example; // String | Nombre de la carpeta a crear.
            try {
                Folder result = apiInstance.apiWordleNewFolderFolderNamePost(body, folderName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleNewFolderFolderNamePost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            String body = ; // String | 
            String folderName = folderName_example; // String | Nombre de la carpeta a crear.
            try {
                Folder result = apiInstance.apiWordleNewFolderFolderNamePost(body, folderName);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleNewFolderFolderNamePost");
                e.printStackTrace();
            }
        }
    }

    String *body = ; // 
    String *folderName = folderName_example; // Nombre de la carpeta a crear.
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Crea una nueva carpeta.
    [apiInstance apiWordleNewFolderFolderNamePostWith:body
        folderName:folderName
                  completionHandler: ^(Folder output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var body = ; // {{String}} 
    var folderName = folderName_example; // {{String}} Nombre de la carpeta a crear.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiWordleNewFolderFolderNamePost(bodyfolderName, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleNewFolderFolderNamePostExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var body = new String(); // String | 
                var folderName = folderName_example;  // String | Nombre de la carpeta a crear.
    
                try
                {
                    // Crea una nueva carpeta.
                    Folder result = apiInstance.apiWordleNewFolderFolderNamePost(body, folderName);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleNewFolderFolderNamePost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $body = ; // String | 
    $folderName = folderName_example; // String | Nombre de la carpeta a crear.
    
    try {
        $result = $api_instance->apiWordleNewFolderFolderNamePost($body, $folderName);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleNewFolderFolderNamePost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $body = WWW::SwaggerClient::Object::String->new(); # String | 
    my $folderName = folderName_example; # String | Nombre de la carpeta a crear.
    
    eval { 
        my $result = $api_instance->apiWordleNewFolderFolderNamePost(body => $body, folderName => $folderName);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleNewFolderFolderNamePost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    body =  # String | 
    folderName = folderName_example # String | Nombre de la carpeta a crear.
    
    try: 
        # Crea una nueva carpeta.
        api_response = api_instance.api_wordle_new_folder_folder_name_post(body, folderName)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleNewFolderFolderNamePost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

folderName\*

String

Nombre de la carpeta a crear.

Required

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "text/plain" : { "schema" : { "type" : "string", "example" : "Profesor" } } }, "required" : true }; var schema = schemaWrapper.content\["text/plain"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiWordleNewFolderFolderNamePost\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 200 - Carpeta creada con éxito.

*   [Schema](#responses-apiWordleNewFolderFolderNamePost-200-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Carpeta creada con éxito.", "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/Folder" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiWordleNewFolderFolderNamePost-200-schema-data').val(stringify(schema)); var result = $('#responses-apiWordleNewFolderFolderNamePost-200-schema-200'); result.empty(); result.append(view.render()); });

### Status: 404 - Profesor no encontrado.

### Status: 409 - La carpeta ya existe.

* * *

apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost
=======================================================

Crea una nueva carpeta dentro de otra carpeta.

Crea una nueva carpeta con el nombre especificado dentro de la carpeta con el ID especificado. Requiere el rol de Profesor o Administrador.

  

    /api/wordle/newFolderInsideFolder/{newFolderName}/{folderId}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost-0-curl)
*   [Java](#examples-WordleController-apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost-0-java)
*   [Android](#examples-WordleController-apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost-0-android)
*   [Obj-C](#examples-WordleController-apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost-0-javascript)
*   [C#](#examples-WordleController-apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost-0-csharp)
*   [PHP](#examples-WordleController-apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost-0-php)
*   [Perl](#examples-WordleController-apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost-0-perl)
*   [Python](#examples-WordleController-apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost-0-python)

    curl -X POST\
    -H "Accept: application/json"\
    -H "Content-Type: text/plain"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/newFolderInsideFolder/{newFolderName}/{folderId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            String body = ; // String | 
            String newFolderName = newFolderName_example; // String | Nombre de la nueva carpeta a crear.
            Long folderId = 789; // Long | ID de la carpeta padre.
            try {
                Folder result = apiInstance.apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost(body, newFolderName, folderId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            String body = ; // String | 
            String newFolderName = newFolderName_example; // String | Nombre de la nueva carpeta a crear.
            Long folderId = 789; // Long | ID de la carpeta padre.
            try {
                Folder result = apiInstance.apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost(body, newFolderName, folderId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost");
                e.printStackTrace();
            }
        }
    }

    String *body = ; // 
    String *newFolderName = newFolderName_example; // Nombre de la nueva carpeta a crear.
    Long *folderId = 789; // ID de la carpeta padre.
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Crea una nueva carpeta dentro de otra carpeta.
    [apiInstance apiWordleNewFolderInsideFolderNewFolderNameFolderIdPostWith:body
        newFolderName:newFolderName
        folderId:folderId
                  completionHandler: ^(Folder output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var body = ; // {{String}} 
    var newFolderName = newFolderName_example; // {{String}} Nombre de la nueva carpeta a crear.
    var folderId = 789; // {{Long}} ID de la carpeta padre.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost(bodynewFolderNamefolderId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleNewFolderInsideFolderNewFolderNameFolderIdPostExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var body = new String(); // String | 
                var newFolderName = newFolderName_example;  // String | Nombre de la nueva carpeta a crear.
                var folderId = 789;  // Long | ID de la carpeta padre.
    
                try
                {
                    // Crea una nueva carpeta dentro de otra carpeta.
                    Folder result = apiInstance.apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost(body, newFolderName, folderId);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $body = ; // String | 
    $newFolderName = newFolderName_example; // String | Nombre de la nueva carpeta a crear.
    $folderId = 789; // Long | ID de la carpeta padre.
    
    try {
        $result = $api_instance->apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost($body, $newFolderName, $folderId);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $body = WWW::SwaggerClient::Object::String->new(); # String | 
    my $newFolderName = newFolderName_example; # String | Nombre de la nueva carpeta a crear.
    my $folderId = 789; # Long | ID de la carpeta padre.
    
    eval { 
        my $result = $api_instance->apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost(body => $body, newFolderName => $newFolderName, folderId => $folderId);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    body =  # String | 
    newFolderName = newFolderName_example # String | Nombre de la nueva carpeta a crear.
    folderId = 789 # Long | ID de la carpeta padre.
    
    try: 
        # Crea una nueva carpeta dentro de otra carpeta.
        api_response = api_instance.api_wordle_new_folder_inside_folder_new_folder_name_folder_id_post(body, newFolderName, folderId)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

newFolderName\*

String

Nombre de la nueva carpeta a crear.

Required

folderId\*

Long (int64)

ID de la carpeta padre.

Required

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "text/plain" : { "schema" : { "type" : "string", "example" : "Profesor" } } }, "required" : true }; var schema = schemaWrapper.content\["text/plain"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 201 - Carpeta creada con éxito dentro de la carpeta padre.

*   [Schema](#responses-apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost-201-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Carpeta creada con éxito dentro de la carpeta padre.", "content" : { "application/json" : { "schema" : { "$ref" : "#/components/schemas/Folder" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost-201-schema-data').val(stringify(schema)); var result = $('#responses-apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost-201-schema-201'); result.empty(); result.append(view.render()); });

### Status: 404 - Carpeta padre o profesor no encontrado.

### Status: 409 - La carpeta ya existe dentro de la carpeta padre.

* * *

apiWordleUpdateWordleWordInitialWordUpdatedPost
===============================================

Actualiza una palabra Wordle y sus concursos asociados.

Actualiza una palabra Wordle existente y sus asociaciones con concursos. Requiere el rol de Administrador o Profesor.

  

    /api/wordle/updateWordle/{wordInitial}/{wordUpdated}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apiWordleUpdateWordleWordInitialWordUpdatedPost-0-curl)
*   [Java](#examples-WordleController-apiWordleUpdateWordleWordInitialWordUpdatedPost-0-java)
*   [Android](#examples-WordleController-apiWordleUpdateWordleWordInitialWordUpdatedPost-0-android)
*   [Obj-C](#examples-WordleController-apiWordleUpdateWordleWordInitialWordUpdatedPost-0-objc)
*   [JavaScript](#examples-WordleController-apiWordleUpdateWordleWordInitialWordUpdatedPost-0-javascript)
*   [C#](#examples-WordleController-apiWordleUpdateWordleWordInitialWordUpdatedPost-0-csharp)
*   [PHP](#examples-WordleController-apiWordleUpdateWordleWordInitialWordUpdatedPost-0-php)
*   [Perl](#examples-WordleController-apiWordleUpdateWordleWordInitialWordUpdatedPost-0-perl)
*   [Python](#examples-WordleController-apiWordleUpdateWordleWordInitialWordUpdatedPost-0-python)

    curl -X POST\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/api/wordle/updateWordle/{wordInitial}/{wordUpdated}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            array[Contest] body = ; // array[Contest] | 
            String wordInitial = wordInitial_example; // String | Palabra Wordle inicial a actualizar.
            String wordUpdated = wordUpdated_example; // String | Nueva palabra Wordle actualizada.
            try {
                apiInstance.apiWordleUpdateWordleWordInitialWordUpdatedPost(body, wordInitial, wordUpdated);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleUpdateWordleWordInitialWordUpdatedPost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            array[Contest] body = ; // array[Contest] | 
            String wordInitial = wordInitial_example; // String | Palabra Wordle inicial a actualizar.
            String wordUpdated = wordUpdated_example; // String | Nueva palabra Wordle actualizada.
            try {
                apiInstance.apiWordleUpdateWordleWordInitialWordUpdatedPost(body, wordInitial, wordUpdated);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apiWordleUpdateWordleWordInitialWordUpdatedPost");
                e.printStackTrace();
            }
        }
    }

    array[Contest] *body = ; // 
    String *wordInitial = wordInitial_example; // Palabra Wordle inicial a actualizar.
    String *wordUpdated = wordUpdated_example; // Nueva palabra Wordle actualizada.
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Actualiza una palabra Wordle y sus concursos asociados.
    [apiInstance apiWordleUpdateWordleWordInitialWordUpdatedPostWith:body
        wordInitial:wordInitial
        wordUpdated:wordUpdated
                  completionHandler: ^(NSError* error) {
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var body = ; // {{array[Contest]}} 
    var wordInitial = wordInitial_example; // {{String}} Palabra Wordle inicial a actualizar.
    var wordUpdated = wordUpdated_example; // {{String}} Nueva palabra Wordle actualizada.
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully.');
      }
    };
    api.apiWordleUpdateWordleWordInitialWordUpdatedPost(bodywordInitialwordUpdated, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apiWordleUpdateWordleWordInitialWordUpdatedPostExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var body = new array[Contest](); // array[Contest] | 
                var wordInitial = wordInitial_example;  // String | Palabra Wordle inicial a actualizar.
                var wordUpdated = wordUpdated_example;  // String | Nueva palabra Wordle actualizada.
    
                try
                {
                    // Actualiza una palabra Wordle y sus concursos asociados.
                    apiInstance.apiWordleUpdateWordleWordInitialWordUpdatedPost(body, wordInitial, wordUpdated);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apiWordleUpdateWordleWordInitialWordUpdatedPost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $body = ; // array[Contest] | 
    $wordInitial = wordInitial_example; // String | Palabra Wordle inicial a actualizar.
    $wordUpdated = wordUpdated_example; // String | Nueva palabra Wordle actualizada.
    
    try {
        $api_instance->apiWordleUpdateWordleWordInitialWordUpdatedPost($body, $wordInitial, $wordUpdated);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apiWordleUpdateWordleWordInitialWordUpdatedPost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $body = [WWW::SwaggerClient::Object::array[Contest]->new()]; # array[Contest] | 
    my $wordInitial = wordInitial_example; # String | Palabra Wordle inicial a actualizar.
    my $wordUpdated = wordUpdated_example; # String | Nueva palabra Wordle actualizada.
    
    eval { 
        $api_instance->apiWordleUpdateWordleWordInitialWordUpdatedPost(body => $body, wordInitial => $wordInitial, wordUpdated => $wordUpdated);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apiWordleUpdateWordleWordInitialWordUpdatedPost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    body =  # array[Contest] | 
    wordInitial = wordInitial_example # String | Palabra Wordle inicial a actualizar.
    wordUpdated = wordUpdated_example # String | Nueva palabra Wordle actualizada.
    
    try: 
        # Actualiza una palabra Wordle y sus concursos asociados.
        api_instance.api_wordle_update_wordle_word_initial_word_updated_post(body, wordInitial, wordUpdated)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apiWordleUpdateWordleWordInitialWordUpdatedPost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

wordInitial\*

String

Palabra Wordle inicial a actualizar.

Required

wordUpdated\*

String

Nueva palabra Wordle actualizada.

Required

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "application/json" : { "schema" : { "type" : "array", "description" : "Lista de concursos asociados a la palabra Wordle actualizada.", "items" : { "$ref" : "#/components/schemas/Contest" } } } }, "required" : true }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apiWordleUpdateWordleWordInitialWordUpdatedPost\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 200 - Palabra Wordle actualizada con éxito.

### Status: 404 - Palabra Wordle inicial o concurso no encontrado.

* * *

apoWordleNewWordlesContestIdProfessorNameFolderIdPost
=====================================================

Crea nuevas palabras Wordle y las asocia a un concurso y/o carpeta.

Crea una lista de nuevas palabras Wordle y las asocia opcionalmente a un concurso y/o carpeta. Requiere rol de Profesor o Administrador.

  

    /apo/wordle/newWordles/{contestId}/{professorName}/{folderId}

### Usage and SDK Samples

*   [Curl](#examples-WordleController-apoWordleNewWordlesContestIdProfessorNameFolderIdPost-0-curl)
*   [Java](#examples-WordleController-apoWordleNewWordlesContestIdProfessorNameFolderIdPost-0-java)
*   [Android](#examples-WordleController-apoWordleNewWordlesContestIdProfessorNameFolderIdPost-0-android)
*   [Obj-C](#examples-WordleController-apoWordleNewWordlesContestIdProfessorNameFolderIdPost-0-objc)
*   [JavaScript](#examples-WordleController-apoWordleNewWordlesContestIdProfessorNameFolderIdPost-0-javascript)
*   [C#](#examples-WordleController-apoWordleNewWordlesContestIdProfessorNameFolderIdPost-0-csharp)
*   [PHP](#examples-WordleController-apoWordleNewWordlesContestIdProfessorNameFolderIdPost-0-php)
*   [Perl](#examples-WordleController-apoWordleNewWordlesContestIdProfessorNameFolderIdPost-0-perl)
*   [Python](#examples-WordleController-apoWordleNewWordlesContestIdProfessorNameFolderIdPost-0-python)

    curl -X POST\
    -H "Accept: application/json"\
    -H "Content-Type: application/json"\
    "https://virtserver.swaggerhub.com/MiguelRegato/WordleAPI/1.0.0/apo/wordle/newWordles/{contestId}/{professorName}/{folderId}"

    import io.swagger.client.*;
    import io.swagger.client.auth.*;
    import io.swagger.client.model.*;
    import io.swagger.client.api.WordleControllerApi;
    
    import java.io.File;
    import java.util.*;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            
            WordleControllerApi apiInstance = new WordleControllerApi();
            array[String] body = ; // array[String] | 
            Long contestId = 789; // Long | ID del concurso (0 si no se asocia a un concurso).
            String professorName = professorName_example; // String | Nombre del profesor que crea las palabras.
            Long folderId = 789; // Long | ID de la carpeta (0 si no se asocia a una carpeta).
            try {
                array[Wordle] result = apiInstance.apoWordleNewWordlesContestIdProfessorNameFolderIdPost(body, contestId, professorName, folderId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apoWordleNewWordlesContestIdProfessorNameFolderIdPost");
                e.printStackTrace();
            }
        }
    }

    import io.swagger.client.api.WordleControllerApi;
    
    public class WordleControllerApiExample {
    
        public static void main(String[] args) {
            WordleControllerApi apiInstance = new WordleControllerApi();
            array[String] body = ; // array[String] | 
            Long contestId = 789; // Long | ID del concurso (0 si no se asocia a un concurso).
            String professorName = professorName_example; // String | Nombre del profesor que crea las palabras.
            Long folderId = 789; // Long | ID de la carpeta (0 si no se asocia a una carpeta).
            try {
                array[Wordle] result = apiInstance.apoWordleNewWordlesContestIdProfessorNameFolderIdPost(body, contestId, professorName, folderId);
                System.out.println(result);
            } catch (ApiException e) {
                System.err.println("Exception when calling WordleControllerApi#apoWordleNewWordlesContestIdProfessorNameFolderIdPost");
                e.printStackTrace();
            }
        }
    }

    array[String] *body = ; // 
    Long *contestId = 789; // ID del concurso (0 si no se asocia a un concurso).
    String *professorName = professorName_example; // Nombre del profesor que crea las palabras.
    Long *folderId = 789; // ID de la carpeta (0 si no se asocia a una carpeta).
    
    WordleControllerApi *apiInstance = [[WordleControllerApi alloc] init];
    
    // Crea nuevas palabras Wordle y las asocia a un concurso y/o carpeta.
    [apiInstance apoWordleNewWordlesContestIdProfessorNameFolderIdPostWith:body
        contestId:contestId
        professorName:professorName
        folderId:folderId
                  completionHandler: ^(array[Wordle] output, NSError* error) {
                                if (output) {
                                    NSLog(@"%@", output);
                                }
                                if (error) {
                                    NSLog(@"Error: %@", error);
                                }
                            }];
    

    var DocumentacinApi = require('documentacin_api');
    
    var api = new DocumentacinApi.WordleControllerApi()
    var body = ; // {{array[String]}} 
    var contestId = 789; // {{Long}} ID del concurso (0 si no se asocia a un concurso).
    var professorName = professorName_example; // {{String}} Nombre del profesor que crea las palabras.
    var folderId = 789; // {{Long}} ID de la carpeta (0 si no se asocia a una carpeta).
    
    var callback = function(error, data, response) {
      if (error) {
        console.error(error);
      } else {
        console.log('API called successfully. Returned data: ' + data);
      }
    };
    api.apoWordleNewWordlesContestIdProfessorNameFolderIdPost(bodycontestIdprofessorNamefolderId, callback);
    

    using System;
    using System.Diagnostics;
    using IO.Swagger.Api;
    using IO.Swagger.Client;
    using IO.Swagger.Model;
    
    namespace Example
    {
        public class apoWordleNewWordlesContestIdProfessorNameFolderIdPostExample
        {
            public void main()
            {
    
                var apiInstance = new WordleControllerApi();
                var body = new array[String](); // array[String] | 
                var contestId = 789;  // Long | ID del concurso (0 si no se asocia a un concurso).
                var professorName = professorName_example;  // String | Nombre del profesor que crea las palabras.
                var folderId = 789;  // Long | ID de la carpeta (0 si no se asocia a una carpeta).
    
                try
                {
                    // Crea nuevas palabras Wordle y las asocia a un concurso y/o carpeta.
                    array[Wordle] result = apiInstance.apoWordleNewWordlesContestIdProfessorNameFolderIdPost(body, contestId, professorName, folderId);
                    Debug.WriteLine(result);
                }
                catch (Exception e)
                {
                    Debug.Print("Exception when calling WordleControllerApi.apoWordleNewWordlesContestIdProfessorNameFolderIdPost: " + e.Message );
                }
            }
        }
    }
    

    <?php
    require_once(__DIR__ . '/vendor/autoload.php');
    
    $api_instance = new Swagger\Client\ApiWordleControllerApi();
    $body = ; // array[String] | 
    $contestId = 789; // Long | ID del concurso (0 si no se asocia a un concurso).
    $professorName = professorName_example; // String | Nombre del profesor que crea las palabras.
    $folderId = 789; // Long | ID de la carpeta (0 si no se asocia a una carpeta).
    
    try {
        $result = $api_instance->apoWordleNewWordlesContestIdProfessorNameFolderIdPost($body, $contestId, $professorName, $folderId);
        print_r($result);
    } catch (Exception $e) {
        echo 'Exception when calling WordleControllerApi->apoWordleNewWordlesContestIdProfessorNameFolderIdPost: ', $e->getMessage(), PHP_EOL;
    }
    ?>

    use Data::Dumper;
    use WWW::SwaggerClient::Configuration;
    use WWW::SwaggerClient::WordleControllerApi;
    
    my $api_instance = WWW::SwaggerClient::WordleControllerApi->new();
    my $body = [WWW::SwaggerClient::Object::array[String]->new()]; # array[String] | 
    my $contestId = 789; # Long | ID del concurso (0 si no se asocia a un concurso).
    my $professorName = professorName_example; # String | Nombre del profesor que crea las palabras.
    my $folderId = 789; # Long | ID de la carpeta (0 si no se asocia a una carpeta).
    
    eval { 
        my $result = $api_instance->apoWordleNewWordlesContestIdProfessorNameFolderIdPost(body => $body, contestId => $contestId, professorName => $professorName, folderId => $folderId);
        print Dumper($result);
    };
    if ($@) {
        warn "Exception when calling WordleControllerApi->apoWordleNewWordlesContestIdProfessorNameFolderIdPost: $@\n";
    }

    from __future__ import print_statement
    import time
    import swagger_client
    from swagger_client.rest import ApiException
    from pprint import pprint
    
    # create an instance of the API class
    api_instance = swagger_client.WordleControllerApi()
    body =  # array[String] | 
    contestId = 789 # Long | ID del concurso (0 si no se asocia a un concurso).
    professorName = professorName_example # String | Nombre del profesor que crea las palabras.
    folderId = 789 # Long | ID de la carpeta (0 si no se asocia a una carpeta).
    
    try: 
        # Crea nuevas palabras Wordle y las asocia a un concurso y/o carpeta.
        api_response = api_instance.apo_wordle_new_wordles_contest_id_professor_name_folder_id_post(body, contestId, professorName, folderId)
        pprint(api_response)
    except ApiException as e:
        print("Exception when calling WordleControllerApi->apoWordleNewWordlesContestIdProfessorNameFolderIdPost: %s\n" % e)

Parameters
----------

Path parameters

Name

Description

contestId\*

Long (int64)

ID del concurso (0 si no se asocia a un concurso).

Required

professorName\*

String

Nombre del profesor que crea las palabras.

Required

folderId\*

Long (int64)

ID de la carpeta (0 si no se asocia a una carpeta).

Required

Body parameters

Name

Description

body \*

$(document).ready(function() { var schemaWrapper = { "content" : { "application/json" : { "schema" : { "type" : "array", "description" : "Lista de palabras Wordle a crear.", "items" : { "type" : "string", "example" : "\[\\"Wordle1\\",\\"Wordle2\\"\]" } } } }, "required" : true }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } var view = new JSONSchemaView(schema,2,{isBodyParam: true}); var result = $('#d2e199\_apoWordleNewWordlesContestIdProfessorNameFolderIdPost\_body'); result.empty(); result.append(view.render()); });

Responses
---------

### Status: 201 - Palabras Wordle creadas con éxito.

*   [Schema](#responses-apoWordleNewWordlesContestIdProfessorNameFolderIdPost-201-schema)

$(document).ready(function() { var schemaWrapper = { "description" : "Palabras Wordle creadas con éxito.", "content" : { "application/json" : { "schema" : { "type" : "array", "items" : { "$ref" : "#/components/schemas/Wordle" }, "x-content-type" : "application/json" } } } }; var schema = schemaWrapper.content\["application/json"\].schema; if (schema.$ref != null) { schema = defsParser.$refs.get(schema.$ref); } else { schemaWrapper.components = {}; schemaWrapper.components.schemas = Object.assign({}, defs); $RefParser.dereference(schemaWrapper).catch(function(err) { console.log(err); }); } //console.log(JSON.stringify(schema)); var view = new JSONSchemaView(schema, 3); $('#responses-apoWordleNewWordlesContestIdProfessorNameFolderIdPost-201-schema-data').val(stringify(schema)); var result = $('#responses-apoWordleNewWordlesContestIdProfessorNameFolderIdPost-201-schema-201'); result.empty(); result.append(view.render()); });

### Status: 404 - Concurso, carpeta o profesor no encontrado.

* * *

Suggestions, contact, support and error reporting;

Information URL: [https://helloreverb.com](https://helloreverb.com)

Contact Info: [m.regato.2019@alumnos.urjc.es](m.regato.2019@alumnos.urjc.es)

Apache 2.0

http://www.apache.org/licenses/LICENSE-2.0.html

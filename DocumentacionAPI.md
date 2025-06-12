# **Documentación API**
Documentación de la API del Wordle educativo

More information: <https://helloreverb.com>

Contact Info: <m.regato.2019@alumnos.urjc.es>

Version: 1.0.0

BasePath:/wordle/WordleEducativoAPI/1.0.0

Apache 2.0

http://www.apache.org/licenses/LICENSE-2.0.html
## **Access**
## <a name="__methods"></a>**Methods**
[ Jump to [Models](#__models) ] 
### **Table of Contents** 
#### [**AuthController**](#authcontroller)
- [POST /auth/login](#login)
- [POST /auth/newUser](#register)
#### [**CompetitionController**](#competitioncontroller)
- [POST /api/competitions/addStudentsByExcel/{competitionId}](#apicompetitionsaddstudentsbyexcelcompetitionidpost)
- [DELETE /api/competitions/deleteCompetition/{competitionId}](#apicompetitionsdeletecompetitioncompetitioniddelete)
- [POST /api/competitions/editCompetition/{competitionId}](#apicompetitionseditcompetitioncompetitionidpost)
- [GET /api/competitions/getCompetitions/{professorName}](#apicompetitionsgetcompetitionsprofessornameget)
- [GET /api/competitions/getStudents/{competitionId}](#apicompetitionsgetstudentscompetitionidget)
- [POST /api/competitions/linkStudentToCompetition/{competitionId}/{userId}](#apicompetitionslinkstudenttocompetitioncompetitioniduseridpost)
- [POST /api/competitions/newCompetition/{professorName}](#apicompetitionsnewcompetitionprofessornamepost)
#### [**ContestController**](#contestcontroller)
- [POST /api/contests/addWordlesToContest/{contestId}](#apicontestsaddwordlestocontestcontestidpost)
- [POST /api/contests/changeWordlesPosition/{contestId}](#apicontestschangewordlespositioncontestidpost)
- [GET /api/contests/{competitionId}/contests](#apicontestscompetitionidcontestsget)
- [GET /api/contests/{contestId}/contest](#apicontestscontestidcontestget)
- [POST /api/contests/copyContest/{oldContestId}](#apicontestscopycontestoldcontestidpost)
- [POST /api/contests/createContestLog/{contestId}/{wordlePosition}/{userName}](#apicontestscreatecontestlogcontestidwordlepositionusernamepost)
- [POST /api/contests/deleteAllProfessorState/{contestId}/{professorName}](#apicontestsdeleteallprofessorstatecontestidprofessornamepost)
- [DELETE /api/contests/deleteContest/{contestId}](#apicontestsdeletecontestcontestiddelete)
- [POST /api/contests/deleteWordlesInContest/{contestId}](#apicontestsdeletewordlesincontestcontestidpost)
- [POST /api/contests/editAccentMode/{contestId}](#apicontestseditaccentmodecontestidpost)
- [POST /api/contests/editContest](#apicontestseditcontestpost)
- [POST /api/contests/editRandomMode/{contestId}](#apicontestseditrandommodecontestidpost)
- [GET /api/contests/existsInDictionary/{word}](#apicontestsexistsindictionarywordget)
- [GET /api/contests/existsInExternalDictionary/{contestId}/{wordle}](#apicontestsexistsinexternaldictionarycontestidwordleget)
- [GET /api/contests/exportLogsInExcel/{contestId}](#apicontestsexportlogsinexcelcontestidget)
- [GET /api/contests/getAllContestState/{contestId}](#apicontestsgetallconteststatecontestidget)
- [GET /api/contests/getAllContestStateLogs/{contestId}](#apicontestsgetallconteststatelogscontestidget)
- [GET /api/contests/getAllUserContestStateLogs/{contestId}/{userName}](#apicontestsgetalluserconteststatelogscontestidusernameget)
- [GET /api/contests/getContestState/{contestId}/{userName}](#apicontestsgetconteststatecontestidusernameget)
- [POST /api/contests/newContest/{competitionId}](#apicontestsnewcontestcompetitionidpost)
- [POST /api/contests/newContestState/{contestId}/{userName}](#apicontestsnewconteststatecontestidusernamepost)
- [GET /api/contests/resumeContest/{contestId}/{userName}](#apicontestsresumecontestcontestidusernameget)
- [POST /api/contests/saveExternalDictionary/{contestId}](#apicontestssaveexternaldictionarycontestidpost)
- [POST /api/contests/updateContestState/{contestId}/{userName}](#apicontestsupdateconteststatecontestidusernamepost)
#### [**UserController**](#usercontroller)
- [DELETE /api/users/deleteUserByName/{userName}](#apiusersdeleteuserbynameusernamedelete)
- [GET /api/users/getAllStudents](#apiusersgetallstudentsget)
- [GET /api/users/getCompetitions/{userName}](#apiusersgetcompetitionsusernameget)
- [GET /api/users/getUserEmail/{userName}](#apiusersgetuseremailusernameget)
- [POST /api/users/updateUser/{oldUserName}](#apiusersupdateuseroldusernamepost)
- [GET /api/users/{userName}](#apiusersusernameget)
- [GET /apu/users/getAllWordles/{userName}](#apuusersgetallwordlesusernameget)
- [GET /api/users/getAllProfessors](#getallprofessors)
#### [**WordleController**](#wordlecontroller)
- [GET /api/wordle/checkWordleAttempt/{contestId}/{wordleIndex}/{word}/{userEmail}](#apiwordlecheckwordleattemptcontestidwordleindexworduseremailget)
- [DELETE /api/wordle/deleteFolders](#apiwordledeletefoldersdelete)
- [DELETE /api/wordle/deleteWordles](#apiwordledeletewordlesdelete)
- [POST /api/wordle/editFolder/{oldFolderId}](#apiwordleeditfolderoldfolderidpost)
- [POST /api/wordle/editWordle/{wordle}](#apiwordleeditwordlewordlepost)
- [GET /api/wordle/getContestsWhereIsUsed/{word}](#apiwordlegetcontestswhereisusedwordget)
- [GET /api/wordle/getFolder/{folderId}](#apiwordlegetfolderfolderidget)
- [GET /api/wordle/getFoldersByProfessor/{professorName}](#apiwordlegetfoldersbyprofessorprofessornameget)
- [GET /api/wordle/getFoldersInsideFolder/{folderId}](#apiwordlegetfoldersinsidefolderfolderidget)
- [GET /api/wordle/getWordleInContest/{contestId}/{wordleIndex}](#apiwordlegetwordleincontestcontestidwordleindexget)
- [GET /api/wordle/getWordlesByContest/{contestId}](#apiwordlegetwordlesbycontestcontestidget)
- [GET /api/wordle/getWordlesByProfessor/{professorName}](#apiwordlegetwordlesbyprofessorprofessornameget)
- [GET /api/wordle/getWordlesInsideFolder/{folderId}](#apiwordlegetwordlesinsidefolderfolderidget)
- [POST /api/wordle/moveToFolder/{folderId}](#apiwordlemovetofolderfolderidpost)
- [POST /api/wordle/newFolder/{folderName}](#apiwordlenewfolderfoldernamepost)
- [POST /api/wordle/newFolderInsideFolder/{newFolderName}/{folderId}](#apiwordlenewfolderinsidefoldernewfoldernamefolderidpost)
- [POST /api/wordle/updateWordle/{wordInitial}/{wordUpdated}](#apiwordleupdatewordlewordinitialwordupdatedpost)
- [POST /apo/wordle/newWordles/{contestId}/{professorName}/{folderId}](#apowordlenewwordlescontestidprofessornamefolderidpost)
# <a name="authcontroller"></a>**AuthController**
<a name="login"></a>[Up](#__methods) 

POST /auth/login

Inicio de sesión de la aplicación (**login**)

Introduciendo el usuario comprueba si existe en la aplicación e inicia sesión

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [LoginUser](#loginuser) (optional)**

*Body Parameter* — Datos del usuario para iniciar sesión 

**Return type**

array[[JwtDto](#jwtdto)] 

**Example data**

Content-Type: application/json

[ {

`  `"bearer" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91",

`  `"userName" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91",

`  `"authorities" : [ "[{\"authority\":\"ROLE\_PROFESSOR\"}]", "[{\"authority\":\"ROLE\_PROFESSOR\"}]" ],

`  `"token" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"

}, {

`  `"bearer" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91",

`  `"userName" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91",

`  `"authorities" : [ "[{\"authority\":\"ROLE\_PROFESSOR\"}]", "[{\"authority\":\"ROLE\_PROFESSOR\"}]" ],

`  `"token" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Inicio de sesión satisfactorio 

**400**

Campos mal introducidos []()

-----
<a name="register"></a>[Up](#__methods) 

POST /auth/newUser

Registro de la aplicación (**register**)

Adds an item to the system

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [NewUser](#newuser) (optional)**

*Body Parameter* — 

**Return type**

Integer 

**Example data**

Content-Type: application/json

1

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**201**

Registro de usuario satisfactorio [Integer](#integer) 

**400**

Campos mal introducidos o usuario ya existente en el sistema []()

-----
# <a name="competitioncontroller"></a>**CompetitionController**
<a name="apicompetitionsaddstudentsbyexcelcompetitionidpost"></a>[Up](#__methods) 

POST /api/competitions/addStudentsByExcel/{competitionId}

Añade estudiantes a una competición mediante un archivo Excel. (**apiCompetitionsAddStudentsByExcelCompetitionIdPost**)

Añade estudiantes a una competición específica utilizando un archivo Excel. Requiere rol de Administrador o de Profesor.

**Path parameters**

**competitionId (required)**

*Path Parameter* — ID de la competición. format: int64

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- multipart/form-data

**Form parameters**

**file (required)**

*Form Parameter* — format: binary

**Responses**

**200**

Archivo procesado correctamente. []()

**400**

Solicitud incorrecta o error al procesar el archivo. []()

**404**

Competición no encontrada. []()

-----
<a name="apicompetitionsdeletecompetitioncompetitioniddelete"></a>[Up](#__methods) 

DELETE /api/competitions/deleteCompetition/{competitionId}

Elimina una competición por su ID. (**apiCompetitionsDeleteCompetitionCompetitionIdDelete**)

Elimina una competición específica utilizando su ID. Requiere el rol de Administrador o de Profesor

**Path parameters**

**competitionId (required)**

*Path Parameter* — ID de la competición a eliminar. format: int64

**Responses**

**200**

Competición eliminada con éxito. []()

**401**

No autorizado. []()

**404**

Competición no encontrada. []()

-----
<a name="apicompetitionseditcompetitioncompetitionidpost"></a>[Up](#__methods) 

POST /api/competitions/editCompetition/{competitionId}

Edita el nombre de una competición. (**apiCompetitionsEditCompetitionCompetitionIdPost**)

Modifica el nombre de una competición existente. Requiere rol de Profesor o de Administrador.

**Path parameters**

**competitionId (required)**

*Path Parameter* — ID de la competición a editar. format: int64

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- text/plain

**Request body**

**body [string](#string) (required)**

*Body Parameter* — 

**Responses**

**200**

Nombre de la competición actualizado correctamente. []()

**404**

Competición no encontrada. []()

-----
<a name="apicompetitionsgetcompetitionsprofessornameget"></a>[Up](#__methods) 

GET /api/competitions/getCompetitions/{professorName}

Obtiene las competiciones creadas por un profesor. (**apiCompetitionsGetCompetitionsProfessorNameGet**)

Obtiene la lista de competiciones creadas por un profesor utilizando su nombre de usuario. Requiere el rol de Administrador o de Profesor

**Path parameters**

**professorName (required)**

*Path Parameter* — Nombre de usuario del profesor para obtener sus competiciones. 

**Return type**

array[[Competition](#competition)] 

**Example data**

Content-Type: application/json

[ {

`  `"contests" : [ {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `}, {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `} ],

`  `"professor" : {

`    `"participations" : [ {

`      `"id" : 1

`    `}, {

`      `"id" : 1

`    `} ],

`    `"roles" : [ {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `}, {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `} ],

`    `"id" : 1,

`    `"email" : "profesor@gmail.com",

`    `"username" : "Profesor"

`  `},

`  `"participations" : [ {

`    `"id" : 1

`  `}, {

`    `"id" : 1

`  `} ],

`  `"competitionName" : "Competición",

`  `"id" : 1

}, {

`  `"contests" : [ {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `}, {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `} ],

`  `"professor" : {

`    `"participations" : [ {

`      `"id" : 1

`    `}, {

`      `"id" : 1

`    `} ],

`    `"roles" : [ {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `}, {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `} ],

`    `"id" : 1,

`    `"email" : "profesor@gmail.com",

`    `"username" : "Profesor"

`  `},

`  `"participations" : [ {

`    `"id" : 1

`  `}, {

`    `"id" : 1

`  `} ],

`  `"competitionName" : "Competición",

`  `"id" : 1

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de competiciones obtenida con éxito. 

**401**

No autorizado. []()

**404**

Profesor no encontrado. []()

-----
<a name="apicompetitionsgetstudentscompetitionidget"></a>[Up](#__methods) 

GET /api/competitions/getStudents/{competitionId}

Obtiene los estudiantes inscritos en una competición. (**apiCompetitionsGetStudentsCompetitionIdGet**)

Obtiene la lista de estudiantes inscritos en una competición específica, utilizando su ID.

**Path parameters**

**competitionId (required)**

*Path Parameter* — ID de la competición para obtener los estudiantes. format: int64

**Return type**

array[[UserStudent](#userstudent)] 

**Example data**

Content-Type: application/json

[ {

`  `"participations" : [ [ ], [ ] ],

`  `"roles" : [ {

`    `"id" : 0,

`    `"rolName" : "ROLE\_STUDENT"

`  `}, {

`    `"id" : 0,

`    `"rolName" : "ROLE\_STUDENT"

`  `} ],

`  `"id" : 1,

`  `"email" : "alumno@gmail.com",

`  `"username" : "Alumno"

}, {

`  `"participations" : [ [ ], [ ] ],

`  `"roles" : [ {

`    `"id" : 0,

`    `"rolName" : "ROLE\_STUDENT"

`  `}, {

`    `"id" : 0,

`    `"rolName" : "ROLE\_STUDENT"

`  `} ],

`  `"id" : 1,

`  `"email" : "alumno@gmail.com",

`  `"username" : "Alumno"

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de estudiantes obtenida con éxito. 

**404**

Competición no encontrada. []()

-----
<a name="apicompetitionslinkstudenttocompetitioncompetitioniduseridpost"></a>[Up](#__methods) 

POST /api/competitions/linkStudentToCompetition/{competitionId}/{userId}

Asigna un estudiante a una competición. (**apiCompetitionsLinkStudentToCompetitionCompetitionIdUserIdPost**)

Asigna un estudiante a una competición específica utilizando sus IDs. Requiere el rol de Administrador o de Profesor

**Path parameters**

**competitionId (required)**

*Path Parameter* — ID de la competición. format: int64

**userId (required)**

*Path Parameter* — ID del estudiante. format: int64

**Responses**

**200**

Estudiante asignado correctamente. []()

**404**

Competición o usuario no encontrado. []()

-----
<a name="apicompetitionsnewcompetitionprofessornamepost"></a>[Up](#__methods) 

POST /api/competitions/newCompetition/{professorName}

Crea una nueva competición. (**apiCompetitionsNewCompetitionProfessorNamePost**)

Crea una nueva competición asociada a un profesor por su nombre de usuario. Requiere el rol de Administrador o de Profesor.

**Path parameters**

**professorName (required)**

*Path Parameter* — Nombre de usuario del profesor que crea la competición. 

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [CompetitionInput](#competitioninput) (required)**

*Body Parameter* — 

**Responses**

**201**

Competición creada con éxito. []()

**401**

No autorizado. []()

**404**

Profesor no encontrado. []()

**409**

Nombre de competición ya utilizado. []()

-----
# <a name="contestcontroller"></a>**ContestController**
<a name="apicontestsaddwordlestocontestcontestidpost"></a>[Up](#__methods) 

POST /api/contests/addWordlesToContest/{contestId}

Añade Wordles a un concurso. (**apiContestsAddWordlesToContestContestIdPost**)

Asocia una lista de palabras Wordle a un concurso. Requiere rol de Profesor o Administrador.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. 

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [string](#string) (required)**

*Body Parameter* — 

**Responses**

**200**

Wordles añadidas correctamente al concurso. []()

**404**

Concurso o Wordle no encontrado. []()

**409**

Wordle ya existente en el concurso. []()

-----
<a name="apicontestschangewordlespositioncontestidpost"></a>[Up](#__methods) 

POST /api/contests/changeWordlesPosition/{contestId}

Cambia el orden de los Wordles en un concurso. (**apiContestsChangeWordlesPositionContestIdPost**)

Reordena los Wordles asociados a un concurso en el orden especificado. Requiere rol de Profesor o Administrador.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. 

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [string](#string) (required)**

*Body Parameter* — 

**Responses**

**200**

Orden de los Wordles actualizado con éxito. []()

**404**

Concurso o Wordle no encontrado. []()

-----
<a name="apicontestscompetitionidcontestsget"></a>[Up](#__methods) 

GET /api/contests/{competitionId}/contests

Obtiene los concursos asociados a una competición. (**apiContestsCompetitionIdContestsGet**)

Devuelve una lista de concursos (contests) que pertenecen a una competición específica. Requiere rol de Profesor o de Administrador.

**Path parameters**

**competitionId (required)**

*Path Parameter* — ID de la competición. format: int64

**Return type**

array[[Contest](#contest)] 

**Example data**

Content-Type: application/json

[ {

`  `"endDate" : "2000-01-23T04:56:07.000+00:00",

`  `"wordles" : [ [ ], [ ] ],

`  `"id" : 1,

`  `"contestName" : "Concurso",

`  `"numTries" : 6,

`  `"useDictionary" : true,

`  `"useExternalFile" : true,

`  `"wordlesLength" : [ [ ], [ ] ],

`  `"startDate" : "2000-01-23T04:56:07.000+00:00"

}, {

`  `"endDate" : "2000-01-23T04:56:07.000+00:00",

`  `"wordles" : [ [ ], [ ] ],

`  `"id" : 1,

`  `"contestName" : "Concurso",

`  `"numTries" : 6,

`  `"useDictionary" : true,

`  `"useExternalFile" : true,

`  `"wordlesLength" : [ [ ], [ ] ],

`  `"startDate" : "2000-01-23T04:56:07.000+00:00"

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de concursos devuelta con éxito. 

**404**

Competición no encontrada. []()

-----
<a name="apicontestscontestidcontestget"></a>[Up](#__methods) 

GET /api/contests/{contestId}/contest

Obtiene un concurso por su ID. (**apiContestsContestIdContestGet**)

Obtiene la información de un concurso específico utilizando su ID.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**Return type**

[Contest](#contest)

**Example data**

Content-Type: application/json

{

`  `"endDate" : "2000-01-23T04:56:07.000+00:00",

`  `"wordles" : [ [ ], [ ] ],

`  `"id" : 1,

`  `"contestName" : "Concurso",

`  `"numTries" : 6,

`  `"useDictionary" : true,

`  `"useExternalFile" : true,

`  `"wordlesLength" : [ [ ], [ ] ],

`  `"startDate" : "2000-01-23T04:56:07.000+00:00"

}

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Concurso obtenido con éxito. [Contest](#contest) 

**404**

Concurso no encontrado. []()

-----
<a name="apicontestscopycontestoldcontestidpost"></a>[Up](#__methods) 

POST /api/contests/copyContest/{oldContestId}

Copia un concurso existente. (**apiContestsCopyContestOldContestIdPost**)

Crea una copia de un concurso existente utilizando su ID. Requiere el rol de Administrador o de Profesor

**Path parameters**

**oldContestId (required)**

*Path Parameter* — ID del concurso a copiar. format: int64

**Responses**

**201**

Concurso copiado con éxito. []()

**404**

Concurso no encontrado. []()

-----
<a name="apicontestscreatecontestlogcontestidwordlepositionusernamepost"></a>[Up](#__methods) 

POST /api/contests/createContestLog/{contestId}/{wordlePosition}/{userName}

Crea un nuevo registro de estado de concurso. (**apiContestsCreateContestLogContestIdWordlePositionUserNamePost**)

Crea un nuevo registro de estado de concurso para un usuario específico en una posición de wordle dada.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**wordlePosition (required)**

*Path Parameter* — Posición del wordle. 

**userName (required)**

*Path Parameter* — Nombre de usuario del usuario. 

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [WordleStateLog](#wordlestatelog) (required)**

*Body Parameter* — 

**Responses**

**201**

Registro de estado de concurso creado con éxito. []()

**400**

Error al procesar los datos. []()

**404**

Concurso o usuario no encontrado. []()

-----
<a name="apicontestsdeleteallprofessorstatecontestidprofessornamepost"></a>[Up](#__methods) 

POST /api/contests/deleteAllProfessorState/{contestId}/{professorName}

Elimina todo el estado de un profesor en un concurso. (**apiContestsDeleteAllProfessorStateContestIdProfessorNamePost**)

Elimina el estado del concurso y todos los registros asociados al profesor para el concurso especificado. Requiere rol de Profesor o Administrador.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso del cual eliminar el estado del profesor. format: int64

**professorName (required)**

*Path Parameter* — Nombre de usuario del profesor. 

**Responses**

**200**

Estado y registros del profesor eliminados correctamente. []()

**404**

Concurso o profesor no encontrado. []()

-----
<a name="apicontestsdeletecontestcontestiddelete"></a>[Up](#__methods) 

DELETE /api/contests/deleteContest/{contestId}

Elimina un concurso por su ID. (**apiContestsDeleteContestContestIdDelete**)

Elimina un concurso específico utilizando su ID. Requiere el rol de Administrador o de Profesor.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso a eliminar. format: int64

**Responses**

**200**

Concurso eliminado con éxito. []()

**404**

Concurso no encontrado. []()

-----
<a name="apicontestsdeletewordlesincontestcontestidpost"></a>[Up](#__methods) 

POST /api/contests/deleteWordlesInContest/{contestId}

Elimina Wordles de un concurso. (**apiContestsDeleteWordlesInContestContestIdPost**)

Elimina una lista de palabras Wordle asociadas a un concurso. Requiere rol de Profesor o Administrador.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. 

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [string](#string) (required)**

*Body Parameter* — 

**Responses**

**200**

Wordles eliminadas del concurso. []()

**404**

Concurso o Wordle no encontrado. []()

-----
<a name="apicontestseditaccentmodecontestidpost"></a>[Up](#__methods) 

POST /api/contests/editAccentMode/{contestId}

Edita el modo de acentos de un concurso. (**apiContestsEditAccentModeContestIdPost**)

Cambia el estado del modo de acentos de un concurso. Requiere rol de Profesor o Administrador.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [boolean](#boolean) (required)**

*Body Parameter* — 

**Return type**

Boolean 

**Example data**

Content-Type: application/json

true

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Modo de acentos actualizado. [Boolean](#boolean) 

**404**

Concurso no encontrado. []()

-----
<a name="apicontestseditcontestpost"></a>[Up](#__methods) 

POST /api/contests/editContest

Edita un concurso existente. (**apiContestsEditContestPost**)

Edita un concurso existente utilizando un DTO con la información actualizada. Requiere el rol de Administrador o de Profesor.

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [EditContestDTO](#editcontestdto) (required)**

*Body Parameter* — 

**Return type**

[Contest](#contest)

**Example data**

Content-Type: application/json

{

`  `"endDate" : "2000-01-23T04:56:07.000+00:00",

`  `"wordles" : [ [ ], [ ] ],

`  `"id" : 1,

`  `"contestName" : "Concurso",

`  `"numTries" : 6,

`  `"useDictionary" : true,

`  `"useExternalFile" : true,

`  `"wordlesLength" : [ [ ], [ ] ],

`  `"startDate" : "2000-01-23T04:56:07.000+00:00"

}

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Concurso editado con éxito. [Contest](#contest) 

**404**

Concurso no encontrado. []()

-----
<a name="apicontestseditrandommodecontestidpost"></a>[Up](#__methods) 

POST /api/contests/editRandomMode/{contestId}

Edita el modo aleatorio de un concurso. (**apiContestsEditRandomModeContestIdPost**)

Cambia el estado del modo aleatorio de un concurso. Requiere rol de Profesor o Administrador.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [boolean](#boolean) (required)**

*Body Parameter* — 

**Return type**

Boolean 

**Example data**

Content-Type: application/json

true

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Modo aleatorio actualizado. [Boolean](#boolean) 

**404**

Concurso no encontrado. []()

-----
<a name="apicontestsexistsindictionarywordget"></a>[Up](#__methods) 

GET /api/contests/existsInDictionary/{word}

Verifica si una palabra existe en el diccionario global. (**apiContestsExistsInDictionaryWordGet**)

Verifica si una palabra dada existe en el diccionario global.

**Path parameters**

**word (required)**

*Path Parameter* — Palabra a verificar. 

**Return type**

Boolean 

**Example data**

Content-Type: application/json

true

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Resultado de la verificación. [Boolean](#boolean)

-----
<a name="apicontestsexistsinexternaldictionarycontestidwordleget"></a>[Up](#__methods) 

GET /api/contests/existsInExternalDictionary/{contestId}/{wordle}

Verifica si una palabra existe en el diccionario externo de un concurso. (**apiContestsExistsInExternalDictionaryContestIdWordleGet**)

Verifica si una palabra dada existe en el diccionario externo de un concurso específico.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**wordle (required)**

*Path Parameter* — Palabra a verificar. 

**Return type**

Boolean 

**Example data**

Content-Type: application/json

true

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Resultado de la verificación. [Boolean](#boolean) 

**404**

Concurso no encontrado. []()

-----
<a name="apicontestsexportlogsinexcelcontestidget"></a>[Up](#__methods) 

GET /api/contests/exportLogsInExcel/{contestId}

Exporta los registros de un concurso a un archivo Excel. (**apiContestsExportLogsInExcelContestIdGet**)

Exporta todos los registros de estado de concurso de un concurso específico a un archivo Excel. Requiere el rol de Profesor o de Administrador.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**Return type**

Object 

**Example data**

Content-Type: application/json

{ }

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/octet-stream

**Responses**

**200**

Archivo Excel con los registros de concurso exportado con éxito. [Object](#object) 

**404**

Concurso no encontrado. []()

-----
<a name="apicontestsgetallconteststatecontestidget"></a>[Up](#__methods) 

GET /api/contests/getAllContestState/{contestId}

Obtiene todos los estados de concurso para un concurso dado. (**apiContestsGetAllContestStateContestIdGet**)

Obtiene una lista de todos los estados de concurso para un concurso específico.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**Return type**

array[[UserState](#userstate)] 

**Example data**

Content-Type: application/json

[ {

`  `"state" : "numberOfWordle:1, games:[{finished:false, won:false, tryCount:0, startTime:, finishTime:, timeNeeded:0, state:{good:0,halfGood:0,wrong:0}, lastWordle:, timeGuess:}]",

`  `"email" : "alumno@gmail.com",

`  `"username" : "Alumno"

}, {

`  `"state" : "numberOfWordle:1, games:[{finished:false, won:false, tryCount:0, startTime:, finishTime:, timeNeeded:0, state:{good:0,halfGood:0,wrong:0}, lastWordle:, timeGuess:}]",

`  `"email" : "alumno@gmail.com",

`  `"username" : "Alumno"

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de estados de concurso obtenida con éxito. 

**404**

Concurso no encontrado. []()

-----
<a name="apicontestsgetallconteststatelogscontestidget"></a>[Up](#__methods) 

GET /api/contests/getAllContestStateLogs/{contestId}

Obtiene todos los registros de estado de concurso de un concurso. (**apiContestsGetAllContestStateLogsContestIdGet**)

Obtiene una lista de todos los registros de estado de concurso de un concurso dado.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**Return type**

array[[WordleStateLog](#wordlestatelog)] 

**Example data**

Content-Type: application/json

[ {

`  `"dateLog" : "2025-03-11T12:20:12.000Z",

`  `"wordleInserted" : "Wordle",

`  `"correct" : 0,

`  `"wrongPosition" : 0,

`  `"wordleToGuess" : "Wordle",

`  `"state" : false,

`  `"userName" : "Alumno",

`  `"numTry" : 1,

`  `"email" : "alumno@gmail.com",

`  `"wordlePosition" : 1,

`  `"wrong" : 0

}, {

`  `"dateLog" : "2025-03-11T12:20:12.000Z",

`  `"wordleInserted" : "Wordle",

`  `"correct" : 0,

`  `"wrongPosition" : 0,

`  `"wordleToGuess" : "Wordle",

`  `"state" : false,

`  `"userName" : "Alumno",

`  `"numTry" : 1,

`  `"email" : "alumno@gmail.com",

`  `"wordlePosition" : 1,

`  `"wrong" : 0

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de registros de estado de concurso obtenida con éxito. 

**404**

Concurso o registros de estado de concurso no encontrado. []()

-----
<a name="apicontestsgetalluserconteststatelogscontestidusernameget"></a>[Up](#__methods) 

GET /api/contests/getAllUserContestStateLogs/{contestId}/{userName}

Obtiene todos los registros de estado de concurso de un usuario en un concurso. (**apiContestsGetAllUserContestStateLogsContestIdUserNameGet**)

Obtiene una lista de todos los registros de estado de concurso de un usuario específico en un concurso dado.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**userName (required)**

*Path Parameter* — Nombre de usuario del usuario. 

**Return type**

array[[WordleStateLog](#wordlestatelog)] 

**Example data**

Content-Type: application/json

[ {

`  `"dateLog" : "2025-03-11T12:20:12.000Z",

`  `"wordleInserted" : "Wordle",

`  `"correct" : 0,

`  `"wrongPosition" : 0,

`  `"wordleToGuess" : "Wordle",

`  `"state" : false,

`  `"userName" : "Alumno",

`  `"numTry" : 1,

`  `"email" : "alumno@gmail.com",

`  `"wordlePosition" : 1,

`  `"wrong" : 0

}, {

`  `"dateLog" : "2025-03-11T12:20:12.000Z",

`  `"wordleInserted" : "Wordle",

`  `"correct" : 0,

`  `"wrongPosition" : 0,

`  `"wordleToGuess" : "Wordle",

`  `"state" : false,

`  `"userName" : "Alumno",

`  `"numTry" : 1,

`  `"email" : "alumno@gmail.com",

`  `"wordlePosition" : 1,

`  `"wrong" : 0

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de registros de estado de concurso obtenida con éxito. 

**404**

Concurso, usuario o registros de estado de concurso no encontrado. []()

-----
<a name="apicontestsgetconteststatecontestidusernameget"></a>[Up](#__methods) 

GET /api/contests/getContestState/{contestId}/{userName}

Obtiene el estado de un concurso para un usuario. (**apiContestsGetContestStateContestIdUserNameGet**)

Obtiene el estado de un concurso específico para un usuario dado.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**userName (required)**

*Path Parameter* — Nombre de usuario del usuario. 

**Return type**

[WordleState](#wordlestate)

**Example data**

Content-Type: application/json

{

`  `"numberOfWordle" : 1,

`  `"games" : [ {

`    `"finishTime" : "2000-01-23T04:56:07.000+00:00",

`    `"lastWordle" : "",

`    `"timeNeeded" : 7,

`    `"won" : false,

`    `"timeGuess" : 0,

`    `"tryCount" : 2,

`    `"finished" : false,

`    `"startTime" : "2000-01-23T04:56:07.000+00:00",

`    `"state" : {

`      `"halfGood" : 3,

`      `"good" : 9,

`      `"wrong" : 2

`    `}

`  `}, {

`    `"finishTime" : "2000-01-23T04:56:07.000+00:00",

`    `"lastWordle" : "",

`    `"timeNeeded" : 7,

`    `"won" : false,

`    `"timeGuess" : 0,

`    `"tryCount" : 2,

`    `"finished" : false,

`    `"startTime" : "2000-01-23T04:56:07.000+00:00",

`    `"state" : {

`      `"halfGood" : 3,

`      `"good" : 9,

`      `"wrong" : 2

`    `}

`  `} ]

}

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Estado de concurso obtenido con éxito. [WordleState](#wordlestate) 

**404**

Concurso, usuario o estado de concurso no encontrado. []()

-----
<a name="apicontestsnewcontestcompetitionidpost"></a>[Up](#__methods) 

POST /api/contests/newContest/{competitionId}

Crea un nuevo concurso en una competición. (**apiContestsNewContestCompetitionIdPost**)

Crea un nuevo concurso en una competición específica utilizando su ID. Requiere el rol de Administrador o de Profesor.

**Path parameters**

**competitionId (required)**

*Path Parameter* — ID de la competición. format: int64

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [Contest](#contest) (required)**

*Body Parameter* — 

**Return type**

[Contest](#contest)

**Example data**

Content-Type: application/json

{

`  `"endDate" : "2000-01-23T04:56:07.000+00:00",

`  `"wordles" : [ [ ], [ ] ],

`  `"id" : 1,

`  `"contestName" : "Concurso",

`  `"numTries" : 6,

`  `"useDictionary" : true,

`  `"useExternalFile" : true,

`  `"wordlesLength" : [ [ ], [ ] ],

`  `"startDate" : "2000-01-23T04:56:07.000+00:00"

}

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**201**

Concurso creado con éxito. [Contest](#contest) 

**404**

Competición no encontrada. []()

**409**

Concurso ya existe en la competición. []()

-----
<a name="apicontestsnewconteststatecontestidusernamepost"></a>[Up](#__methods) 

POST /api/contests/newContestState/{contestId}/{userName}

Crea un nuevo estado de concurso para un usuario. (**apiContestsNewContestStateContestIdUserNamePost**)

Crea un nuevo estado de concurso para un usuario específico en un concurso dado.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**userName (required)**

*Path Parameter* — Nombre de usuario del usuario. 

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [WordleState](#wordlestate) (required)**

*Body Parameter* — 

**Return type**

[ContestState](#conteststate)

**Example data**

Content-Type: application/json

{

`  `"contest" : {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `},

`  `"letterCountsList" : [ [ ], [ ] ],

`  `"id" : 0,

`  `"state" : "numberOfWordle:1, games:[{finished:false, won:false, tryCount:0, startTime:, finishTime:, timeNeeded:0, state:{good:0,halfGood:0,wrong:0}, lastWordle:, timeGuess:}]",

`  `"user" : {

`    `"participations" : [ [ ], [ ] ],

`    `"roles" : [ {

`      `"id" : 0,

`      `"rolName" : "ROLE\_STUDENT"

`    `}, {

`      `"id" : 0,

`      `"rolName" : "ROLE\_STUDENT"

`    `} ],

`    `"id" : 1,

`    `"email" : "alumno@gmail.com",

`    `"username" : "Alumno"

`  `}

}

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**201**

Estado de concurso creado con éxito. [ContestState](#conteststate) 

**400**

Error al procesar los datos. []()

**404**

Concurso o usuario no encontrado. []()

**409**

Estado de concurso ya creado. []()

-----
<a name="apicontestsresumecontestcontestidusernameget"></a>[Up](#__methods) 

GET /api/contests/resumeContest/{contestId}/{userName}

Reanuda el estado del concurso para un usuario. (**apiContestsResumeContestContestIdUserNameGet**)

Devuelve el estado actual del concurso para un usuario, incluyendo la posición del Wordle actual, las letras ingresadas y su estado. Requiere rol de Profesor o Administrador.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**userName (required)**

*Path Parameter* — Nombre de usuario del participante. 

**Return type**

[ResumeContestDTO](#resumecontestdto)

**Example data**

Content-Type: application/json

{

`  `"tries" : [ {

`    `"letters" : [ {

`      `"letter" : "letter",

`      `"state" : 5

`    `}, {

`      `"letter" : "letter",

`      `"state" : 5

`    `} ]

`  `}, {

`    `"letters" : [ {

`      `"letter" : "letter",

`      `"state" : 5

`    `}, {

`      `"letter" : "letter",

`      `"state" : 5

`    `} ]

`  `} ],

`  `"wordleOrder" : [ 5, 5 ],

`  `"wordleState" : {

`    `"numberOfWordle" : 1,

`    `"games" : [ {

`      `"finishTime" : "2000-01-23T04:56:07.000+00:00",

`      `"lastWordle" : "",

`      `"timeNeeded" : 7,

`      `"won" : false,

`      `"timeGuess" : 0,

`      `"tryCount" : 2,

`      `"finished" : false,

`      `"startTime" : "2000-01-23T04:56:07.000+00:00",

`      `"state" : {

`        `"halfGood" : 3,

`        `"good" : 9,

`        `"wrong" : 2

`      `}

`    `}, {

`      `"finishTime" : "2000-01-23T04:56:07.000+00:00",

`      `"lastWordle" : "",

`      `"timeNeeded" : 7,

`      `"won" : false,

`      `"timeGuess" : 0,

`      `"tryCount" : 2,

`      `"finished" : false,

`      `"startTime" : "2000-01-23T04:56:07.000+00:00",

`      `"state" : {

`        `"halfGood" : 3,

`        `"good" : 9,

`        `"wrong" : 2

`      `}

`    `} ]

`  `},

`  `"charPosition" : 1,

`  `"wordlePosition" : 0,

`  `"tryPosition" : 6

}

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Estado del concurso retornado con éxito. [ResumeContestDTO](#resumecontestdto) 

**404**

Concurso o usuario o estado no encontrado. []()

-----
<a name="apicontestssaveexternaldictionarycontestidpost"></a>[Up](#__methods) 

POST /api/contests/saveExternalDictionary/{contestId}

Guarda un diccionario externo para un concurso. (**apiContestsSaveExternalDictionaryContestIdPost**)

Guarda una lista de palabras como diccionario externo para un concurso específico. Requiere el rol de Administrador o de Profesor.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [string](#string) (required)**

*Body Parameter* — 

**Return type**

array[[DictionaryExternalSaved](#dictionaryexternalsaved)] 

**Example data**

Content-Type: application/json

[ {

`  `"wordle" : "Wordle",

`  `"contest" : {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `},

`  `"id" : 1

}, {

`  `"wordle" : "Wordle",

`  `"contest" : {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `},

`  `"id" : 1

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Diccionario externo guardado con éxito. 

**404**

Concurso no encontrado. []()

-----
<a name="apicontestsupdateconteststatecontestidusernamepost"></a>[Up](#__methods) 

POST /api/contests/updateContestState/{contestId}/{userName}

Actualiza el estado de un concurso para un usuario. (**apiContestsUpdateContestStateContestIdUserNamePost**)

Actualiza el estado de un concurso específico para un usuario dado.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**userName (required)**

*Path Parameter* — Nombre de usuario del usuario. 

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [WordleState](#wordlestate) (required)**

*Body Parameter* — 

**Return type**

[ContestState](#conteststate)

**Example data**

Content-Type: application/json

{

`  `"contest" : {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `},

`  `"letterCountsList" : [ [ ], [ ] ],

`  `"id" : 0,

`  `"state" : "numberOfWordle:1, games:[{finished:false, won:false, tryCount:0, startTime:, finishTime:, timeNeeded:0, state:{good:0,halfGood:0,wrong:0}, lastWordle:, timeGuess:}]",

`  `"user" : {

`    `"participations" : [ [ ], [ ] ],

`    `"roles" : [ {

`      `"id" : 0,

`      `"rolName" : "ROLE\_STUDENT"

`    `}, {

`      `"id" : 0,

`      `"rolName" : "ROLE\_STUDENT"

`    `} ],

`    `"id" : 1,

`    `"email" : "alumno@gmail.com",

`    `"username" : "Alumno"

`  `}

}

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Estado de concurso actualizado con éxito. [ContestState](#conteststate) 

**400**

Error al procesar los datos. []()

**404**

Concurso o usuario no encontrado. []()

-----
# <a name="usercontroller"></a>**UserController**
<a name="apiusersdeleteuserbynameusernamedelete"></a>[Up](#__methods) 

DELETE /api/users/deleteUserByName/{userName}

Elimina un usuario por su nombre de usuario. (**apiUsersDeleteUserByNameUserNameDelete**)

Elimina un usuario específico utilizando su nombre de usuario. Requiere el rol de Administrador.

**Path parameters**

**userName (required)**

*Path Parameter* — Nombre de usuario del usuario a eliminar. 

**Responses**

**200**

Usuario eliminado con éxito. []()

**401**

No autorizado. []()

**404**

Usuario no encontrado. []()

-----
<a name="apiusersgetallstudentsget"></a>[Up](#__methods) 

GET /api/users/getAllStudents

Obtiene todos los usuarios con el rol de estudiante (**apiUsersGetAllStudentsGet**)

Obtiene una lista de todos los usuarios que tienen el rol de estudiante. Requiere el rol de Administrador.

**Return type**

array[[UserStudent](#userstudent)] 

**Example data**

Content-Type: application/json

[ {

`  `"participations" : [ [ ], [ ] ],

`  `"roles" : [ {

`    `"id" : 0,

`    `"rolName" : "ROLE\_STUDENT"

`  `}, {

`    `"id" : 0,

`    `"rolName" : "ROLE\_STUDENT"

`  `} ],

`  `"id" : 1,

`  `"email" : "alumno@gmail.com",

`  `"username" : "Alumno"

}, {

`  `"participations" : [ [ ], [ ] ],

`  `"roles" : [ {

`    `"id" : 0,

`    `"rolName" : "ROLE\_STUDENT"

`  `}, {

`    `"id" : 0,

`    `"rolName" : "ROLE\_STUDENT"

`  `} ],

`  `"id" : 1,

`  `"email" : "alumno@gmail.com",

`  `"username" : "Alumno"

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de estudiantes obtenida con éxito. 

**401**

No autorizado []()

-----
<a name="apiusersgetcompetitionsusernameget"></a>[Up](#__methods) 

GET /api/users/getCompetitions/{userName}

Obtiene las competiciones en las que participa un usuario. (**apiUsersGetCompetitionsUserNameGet**)

Obtiene la lista de competiciones en las que un usuario participa, utilizando su nombre de usuario. Requiere el rol de Alumno.

**Path parameters**

**userName (required)**

*Path Parameter* — Nombre de usuario del usuario para obtener sus competiciones. 

**Return type**

array[[Competition](#competition)] 

**Example data**

Content-Type: application/json

[ {

`  `"contests" : [ {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `}, {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `} ],

`  `"professor" : {

`    `"participations" : [ {

`      `"id" : 1

`    `}, {

`      `"id" : 1

`    `} ],

`    `"roles" : [ {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `}, {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `} ],

`    `"id" : 1,

`    `"email" : "profesor@gmail.com",

`    `"username" : "Profesor"

`  `},

`  `"participations" : [ {

`    `"id" : 1

`  `}, {

`    `"id" : 1

`  `} ],

`  `"competitionName" : "Competición",

`  `"id" : 1

}, {

`  `"contests" : [ {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `}, {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `} ],

`  `"professor" : {

`    `"participations" : [ {

`      `"id" : 1

`    `}, {

`      `"id" : 1

`    `} ],

`    `"roles" : [ {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `}, {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `} ],

`    `"id" : 1,

`    `"email" : "profesor@gmail.com",

`    `"username" : "Profesor"

`  `},

`  `"participations" : [ {

`    `"id" : 1

`  `}, {

`    `"id" : 1

`  `} ],

`  `"competitionName" : "Competición",

`  `"id" : 1

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de competiciones obtenida con éxito. 

**401**

No autorizado. []()

**404**

Usuario no encontrado. []()

-----
<a name="apiusersgetuseremailusernameget"></a>[Up](#__methods) 

GET /api/users/getUserEmail/{userName}

Obtiene el correo electrónico de un usuario por su nombre de usuario. (**apiUsersGetUserEmailUserNameGet**)

Obtiene el correo electrónico de un usuario específico utilizando su nombre de usuario. Requiere el rol de Alumno.

**Path parameters**

**userName (required)**

*Path Parameter* — Nombre de usuario del usuario para obtener su correo electrónico. 

**Return type**

String 

**Example data**

Content-Type: application/json

"usuario@gmail.com"

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Correo electrónico del usuario obtenido con éxito. [String](#string) 

**401**

No autorizado. []()

**404**

Usuario no encontrado. []()

-----
<a name="apiusersupdateuseroldusernamepost"></a>[Up](#__methods) 

POST /api/users/updateUser/{oldUserName}

Actualiza la información de un usuario existente. (**apiUsersUpdateUserOldUserNamePost**)

Actualiza el nombre de usuario, correo electrónico y contraseña de un usuario existente. Requiere el rol de Administrador.

**Path parameters**

**oldUserName (required)**

*Path Parameter* — Nombre de usuario actual del usuario a actualizar. 

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [NewUser](#newuser) (required)**

*Body Parameter* — 

**Responses**

**200**

Usuario actualizado con éxito. []()

**401**

No autorizado. []()

**404**

Usuario no encontrado. []()

-----
<a name="apiusersusernameget"></a>[Up](#__methods) 

GET /api/users/{userName}

Obtiene los datos de un usuario por su nombre de usuario (**apiUsersUserNameGet**)

Obtiene la información de un usuario específico utilizando su nombre de usuario. Requiere el rol de Administrador.

**Path parameters**

**userName (required)**

*Path Parameter* — Nombre de usuario del usuario a obtener. 

**Return type**

[User](#user)

**Example data**

Content-Type: application/json

{

`  `"password" : "password",

`  `"participations" : [ {

`    `"id" : 1

`  `}, {

`    `"id" : 1

`  `} ],

`  `"roles" : [ {

`    `"id" : 1,

`    `"rolName" : "ROLE\_ADMIN"

`  `}, {

`    `"id" : 1,

`    `"rolName" : "ROLE\_ADMIN"

`  `} ],

`  `"id" : 1,

`  `"email" : "usuario@gmail.com",

`  `"username" : "Usuario"

}

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Usuario encontrado y devuelto con éxito. [User](#user) 

**401**

No autorizado. []()

**404**

Usuario no encontrado. []()

-----
<a name="apuusersgetallwordlesusernameget"></a>[Up](#__methods) 

GET /apu/users/getAllWordles/{userName}

Obtiene os wordles de los concursos que ya han terminado. (**apuUsersGetAllWordlesUserNameGet**)

Obtiene la lista de competiciones con los wordles que tengan en sus concursos. Requiere el rol de Alumno.

**Path parameters**

**userName (required)**

*Path Parameter* — Nombre de usuario del usuario para obtener sus competiciones. 

**Return type**

array[[WordlesStudentDTO](#wordlesstudentdto)] 

**Example data**

Content-Type: application/json

[ {

`  `"contestInfo" : {

`    `"contest" : {

`      `"endDate" : "2000-01-23T04:56:07.000+00:00",

`      `"wordles" : [ [ ], [ ] ],

`      `"id" : 1,

`      `"contestName" : "Concurso",

`      `"numTries" : 6,

`      `"useDictionary" : true,

`      `"useExternalFile" : true,

`      `"wordlesLength" : [ [ ], [ ] ],

`      `"startDate" : "2000-01-23T04:56:07.000+00:00"

`    `},

`    `"wordles" : [ {

`      `"contests" : [ null, null ],

`      `"folder" : {

`        `"parentFolder" : { },

`        `"folders" : [ "[]", "[]" ],

`        `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`        `"name" : "Carpeta",

`        `"id" : 1

`      `},

`      `"id" : 1,

`      `"word" : "Wordle",

`      `"user" : {

`        `"participations" : [ {

`          `"id" : 1

`        `}, {

`          `"id" : 1

`        `} ],

`        `"roles" : [ {

`          `"id" : 0,

`          `"rolName" : "ROLE\_PROFESSOR"

`        `}, {

`          `"id" : 0,

`          `"rolName" : "ROLE\_PROFESSOR"

`        `} ],

`        `"id" : 1,

`        `"email" : "profesor@gmail.com",

`        `"username" : "Profesor"

`      `}

`    `}, {

`      `"contests" : [ null, null ],

`      `"folder" : {

`        `"parentFolder" : { },

`        `"folders" : [ "[]", "[]" ],

`        `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`        `"name" : "Carpeta",

`        `"id" : 1

`      `},

`      `"id" : 1,

`      `"word" : "Wordle",

`      `"user" : {

`        `"participations" : [ {

`          `"id" : 1

`        `}, {

`          `"id" : 1

`        `} ],

`        `"roles" : [ {

`          `"id" : 0,

`          `"rolName" : "ROLE\_PROFESSOR"

`        `}, {

`          `"id" : 0,

`          `"rolName" : "ROLE\_PROFESSOR"

`        `} ],

`        `"id" : 1,

`        `"email" : "profesor@gmail.com",

`        `"username" : "Profesor"

`      `}

`    `} ]

`  `},

`  `"competition" : {

`    `"contests" : [ {

`      `"endDate" : "2000-01-23T04:56:07.000+00:00",

`      `"wordles" : [ [ ], [ ] ],

`      `"id" : 1,

`      `"contestName" : "Concurso",

`      `"numTries" : 6,

`      `"useDictionary" : true,

`      `"useExternalFile" : true,

`      `"wordlesLength" : [ [ ], [ ] ],

`      `"startDate" : "2000-01-23T04:56:07.000+00:00"

`    `}, {

`      `"endDate" : "2000-01-23T04:56:07.000+00:00",

`      `"wordles" : [ [ ], [ ] ],

`      `"id" : 1,

`      `"contestName" : "Concurso",

`      `"numTries" : 6,

`      `"useDictionary" : true,

`      `"useExternalFile" : true,

`      `"wordlesLength" : [ [ ], [ ] ],

`      `"startDate" : "2000-01-23T04:56:07.000+00:00"

`    `} ],

`    `"professor" : {

`      `"participations" : [ {

`        `"id" : 1

`      `}, {

`        `"id" : 1

`      `} ],

`      `"roles" : [ {

`        `"id" : 0,

`        `"rolName" : "ROLE\_PROFESSOR"

`      `}, {

`        `"id" : 0,

`        `"rolName" : "ROLE\_PROFESSOR"

`      `} ],

`      `"id" : 1,

`      `"email" : "profesor@gmail.com",

`      `"username" : "Profesor"

`    `},

`    `"participations" : [ {

`      `"id" : 1

`    `}, {

`      `"id" : 1

`    `} ],

`    `"competitionName" : "Competición",

`    `"id" : 1

`  `}

}, {

`  `"contestInfo" : {

`    `"contest" : {

`      `"endDate" : "2000-01-23T04:56:07.000+00:00",

`      `"wordles" : [ [ ], [ ] ],

`      `"id" : 1,

`      `"contestName" : "Concurso",

`      `"numTries" : 6,

`      `"useDictionary" : true,

`      `"useExternalFile" : true,

`      `"wordlesLength" : [ [ ], [ ] ],

`      `"startDate" : "2000-01-23T04:56:07.000+00:00"

`    `},

`    `"wordles" : [ {

`      `"contests" : [ null, null ],

`      `"folder" : {

`        `"parentFolder" : { },

`        `"folders" : [ "[]", "[]" ],

`        `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`        `"name" : "Carpeta",

`        `"id" : 1

`      `},

`      `"id" : 1,

`      `"word" : "Wordle",

`      `"user" : {

`        `"participations" : [ {

`          `"id" : 1

`        `}, {

`          `"id" : 1

`        `} ],

`        `"roles" : [ {

`          `"id" : 0,

`          `"rolName" : "ROLE\_PROFESSOR"

`        `}, {

`          `"id" : 0,

`          `"rolName" : "ROLE\_PROFESSOR"

`        `} ],

`        `"id" : 1,

`        `"email" : "profesor@gmail.com",

`        `"username" : "Profesor"

`      `}

`    `}, {

`      `"contests" : [ null, null ],

`      `"folder" : {

`        `"parentFolder" : { },

`        `"folders" : [ "[]", "[]" ],

`        `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`        `"name" : "Carpeta",

`        `"id" : 1

`      `},

`      `"id" : 1,

`      `"word" : "Wordle",

`      `"user" : {

`        `"participations" : [ {

`          `"id" : 1

`        `}, {

`          `"id" : 1

`        `} ],

`        `"roles" : [ {

`          `"id" : 0,

`          `"rolName" : "ROLE\_PROFESSOR"

`        `}, {

`          `"id" : 0,

`          `"rolName" : "ROLE\_PROFESSOR"

`        `} ],

`        `"id" : 1,

`        `"email" : "profesor@gmail.com",

`        `"username" : "Profesor"

`      `}

`    `} ]

`  `},

`  `"competition" : {

`    `"contests" : [ {

`      `"endDate" : "2000-01-23T04:56:07.000+00:00",

`      `"wordles" : [ [ ], [ ] ],

`      `"id" : 1,

`      `"contestName" : "Concurso",

`      `"numTries" : 6,

`      `"useDictionary" : true,

`      `"useExternalFile" : true,

`      `"wordlesLength" : [ [ ], [ ] ],

`      `"startDate" : "2000-01-23T04:56:07.000+00:00"

`    `}, {

`      `"endDate" : "2000-01-23T04:56:07.000+00:00",

`      `"wordles" : [ [ ], [ ] ],

`      `"id" : 1,

`      `"contestName" : "Concurso",

`      `"numTries" : 6,

`      `"useDictionary" : true,

`      `"useExternalFile" : true,

`      `"wordlesLength" : [ [ ], [ ] ],

`      `"startDate" : "2000-01-23T04:56:07.000+00:00"

`    `} ],

`    `"professor" : {

`      `"participations" : [ {

`        `"id" : 1

`      `}, {

`        `"id" : 1

`      `} ],

`      `"roles" : [ {

`        `"id" : 0,

`        `"rolName" : "ROLE\_PROFESSOR"

`      `}, {

`        `"id" : 0,

`        `"rolName" : "ROLE\_PROFESSOR"

`      `} ],

`      `"id" : 1,

`      `"email" : "profesor@gmail.com",

`      `"username" : "Profesor"

`    `},

`    `"participations" : [ {

`      `"id" : 1

`    `}, {

`      `"id" : 1

`    `} ],

`    `"competitionName" : "Competición",

`    `"id" : 1

`  `}

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de wordles obtenida con éxito. 

**401**

No autorizado. []()

**404**

Usuario no encontrado. []()

-----
<a name="getallprofessors"></a>[Up](#__methods) 

GET /api/users/getAllProfessors

Obtiene todos los usuarios con el rol de profesor (**getAllProfessors**)

Devuelve una lista con todos los profesores que se encuentran en la aplicación. Requiere el rol de Administrador.

**Return type**

array[[UserProfessor](#userprofessor)] 

**Example data**

Content-Type: application/json

[ {

`  `"participations" : [ {

`    `"id" : 1

`  `}, {

`    `"id" : 1

`  `} ],

`  `"roles" : [ {

`    `"id" : 0,

`    `"rolName" : "ROLE\_PROFESSOR"

`  `}, {

`    `"id" : 0,

`    `"rolName" : "ROLE\_PROFESSOR"

`  `} ],

`  `"id" : 1,

`  `"email" : "profesor@gmail.com",

`  `"username" : "Profesor"

}, {

`  `"participations" : [ {

`    `"id" : 1

`  `}, {

`    `"id" : 1

`  `} ],

`  `"roles" : [ {

`    `"id" : 0,

`    `"rolName" : "ROLE\_PROFESSOR"

`  `}, {

`    `"id" : 0,

`    `"rolName" : "ROLE\_PROFESSOR"

`  `} ],

`  `"id" : 1,

`  `"email" : "profesor@gmail.com",

`  `"username" : "Profesor"

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista con los profesores obtenida con éxito 

**401**

No autorizado []()

-----
# <a name="wordlecontroller"></a>**WordleController**
<a name="apiwordlecheckwordleattemptcontestidwordleindexworduseremailget"></a>[Up](#__methods) 

GET /api/wordle/checkWordleAttempt/{contestId}/{wordleIndex}/{word}/{userEmail}

Verifica un intento de palabra Wordle. (**apiWordleCheckWordleAttemptContestIdWordleIndexWordUserEmailGet**)

Verifica un intento de palabra Wordle para un usuario en un concurso específico y devuelve una lista de resultados.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**wordleIndex (required)**

*Path Parameter* — Índice de la palabra Wordle en el concurso. 

**word (required)**

*Path Parameter* — Palabra Wordle intentada. 

**userEmail (required)**

*Path Parameter* — Correo electrónico del usuario. 

**Return type**

array[Integer] 

**Example data**

Content-Type: application/json

[ 0, 0 ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de resultados de la verificación del intento de Wordle. 

**404**

Concurso o usuario no encontrado. []()

-----
<a name="apiwordledeletefoldersdelete"></a>[Up](#__methods) 

DELETE /api/wordle/deleteFolders

Elimina una lista de carpetas. (**apiWordleDeleteFoldersDelete**)

Elimina una lista de carpetas utilizando sus IDs. Requiere el rol de Administrador o Profesor.

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [long](#long) (required)**

*Body Parameter* — 

**Responses**

**200**

Carpetas eliminadas con éxito. []()

**404**

Carpeta no encontrada. []()

-----
<a name="apiwordledeletewordlesdelete"></a>[Up](#__methods) 

DELETE /api/wordle/deleteWordles

Elimina una lista de palabras Wordle. (**apiWordleDeleteWordlesDelete**)

Elimina una lista de palabras Wordle proporcionadas en el cuerpo de la solicitud. Requiere el rol de Profesor o de Administrador

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [Wordle](#wordle) (required)**

*Body Parameter* — 

**Responses**

**200**

Palabras Wordle eliminadas con éxito. []()

**404**

Palabra Wordle no encontrada. []()

-----
<a name="apiwordleeditfolderoldfolderidpost"></a>[Up](#__methods) 

POST /api/wordle/editFolder/{oldFolderId}

Edita el nombre de una carpeta. (**apiWordleEditFolderOldFolderIdPost**)

Edita el nombre de una carpeta existente utilizando su ID. Requiere el rol de Administrador o Profesor.

**Path parameters**

**oldFolderId (required)**

*Path Parameter* — ID de la carpeta a editar. format: int64

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- text/plain

**Request body**

**body [string](#string) (required)**

*Body Parameter* — 

**Responses**

**200**

Carpeta editada con éxito. []()

**404**

Carpeta no encontrada. []()

-----
<a name="apiwordleeditwordlewordlepost"></a>[Up](#__methods) 

POST /api/wordle/editWordle/{wordle}

Edita una palabra Wordle existente. (**apiWordleEditWordleWordlePost**)

Modifica una palabra Wordle específica. Requiere rol de Profesor o Administrador.

**Path parameters**

**wordle (required)**

*Path Parameter* — Palabra original que se desea editar. 

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [string](#string) (required)**

*Body Parameter* — 

*example: "NUEVA"*

**Responses**

**200**

Palabra Wordle editada con éxito. []()

**404**

La palabra Wordle original no fue encontrada. []()

-----
<a name="apiwordlegetcontestswhereisusedwordget"></a>[Up](#__methods) 

GET /api/wordle/getContestsWhereIsUsed/{word}

Obtiene la lista de concursos donde se utiliza una palabra Wordle. (**apiWordleGetContestsWhereIsUsedWordGet**)

Obtiene la lista de concursos donde se utiliza una palabra Wordle específica. Requiere el rol de Administrador o Profesor.

**Path parameters**

**word (required)**

*Path Parameter* — Palabra Wordle a buscar. 

**Return type**

array[[Contest](#contest)] 

**Example data**

Content-Type: application/json

[ {

`  `"endDate" : "2000-01-23T04:56:07.000+00:00",

`  `"wordles" : [ [ ], [ ] ],

`  `"id" : 1,

`  `"contestName" : "Concurso",

`  `"numTries" : 6,

`  `"useDictionary" : true,

`  `"useExternalFile" : true,

`  `"wordlesLength" : [ [ ], [ ] ],

`  `"startDate" : "2000-01-23T04:56:07.000+00:00"

}, {

`  `"endDate" : "2000-01-23T04:56:07.000+00:00",

`  `"wordles" : [ [ ], [ ] ],

`  `"id" : 1,

`  `"contestName" : "Concurso",

`  `"numTries" : 6,

`  `"useDictionary" : true,

`  `"useExternalFile" : true,

`  `"wordlesLength" : [ [ ], [ ] ],

`  `"startDate" : "2000-01-23T04:56:07.000+00:00"

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de concursos obtenida con éxito. 

**404**

Palabra Wordle no encontrada. []()

-----
<a name="apiwordlegetfolderfolderidget"></a>[Up](#__methods) 

GET /api/wordle/getFolder/{folderId}

Obtiene una carpeta por su ID. (**apiWordleGetFolderFolderIdGet**)

Obtiene una carpeta específica utilizando su ID. Requiere el rol de Profesor o Administrador.

**Path parameters**

**folderId (required)**

*Path Parameter* — ID de la carpeta a obtener. format: int64

**Return type**

[FolderDTO](#folderdto)

**Example data**

Content-Type: application/json

{

`  `"parentFolder" : "",

`  `"folders" : [ "[]", "[]" ],

`  `"wordles" : [ "[]", "[]" ],

`  `"name" : "Carpeta",

`  `"id" : 1

}

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Carpeta obtenida con éxito. [FolderDTO](#folderdto) 

**404**

Carpeta no encontrada. []()

-----
<a name="apiwordlegetfoldersbyprofessorprofessornameget"></a>[Up](#__methods) 

GET /api/wordle/getFoldersByProfessor/{professorName}

Obtiene la lista de carpetas de un profesor. (**apiWordleGetFoldersByProfessorProfessorNameGet**)

Obtiene la lista de carpetas creadas por un profesor específico, excluyendo las carpetas anidadas. Requiere el rol de Administrador o Profesor.

**Path parameters**

**professorName (required)**

*Path Parameter* — Nombre del profesor. 

**Return type**

array[[FolderDTO](#folderdto)] 

**Example data**

Content-Type: application/json

[ {

`  `"parentFolder" : "",

`  `"folders" : [ "[]", "[]" ],

`  `"wordles" : [ "[]", "[]" ],

`  `"name" : "Carpeta",

`  `"id" : 1

}, {

`  `"parentFolder" : "",

`  `"folders" : [ "[]", "[]" ],

`  `"wordles" : [ "[]", "[]" ],

`  `"name" : "Carpeta",

`  `"id" : 1

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de carpetas obtenida con éxito. 

**404**

Profesor no encontrado. []()

-----
<a name="apiwordlegetfoldersinsidefolderfolderidget"></a>[Up](#__methods) 

GET /api/wordle/getFoldersInsideFolder/{folderId}

Obtiene la lista de carpetas dentro de una carpeta. (**apiWordleGetFoldersInsideFolderFolderIdGet**)

Obtiene la lista de carpetas que se encuentran dentro de la carpeta especificada por su ID. Requiere el rol de Profesor o Administrador.

**Path parameters**

**folderId (required)**

*Path Parameter* — ID de la carpeta padre. format: int64

**Return type**

array[[Folder](#folder)] 

**Example data**

Content-Type: application/json

[ {

`  `"parentFolder" : { },

`  `"folders" : [ "[]", "[]" ],

`  `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`  `"name" : "Carpeta",

`  `"id" : 1

}, {

`  `"parentFolder" : { },

`  `"folders" : [ "[]", "[]" ],

`  `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`  `"name" : "Carpeta",

`  `"id" : 1

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de carpetas obtenida con éxito. 

**404**

Carpeta padre no encontrada. []()

-----
<a name="apiwordlegetwordleincontestcontestidwordleindexget"></a>[Up](#__methods) 

GET /api/wordle/getWordleInContest/{contestId}/{wordleIndex}

Obtiene una palabra Wordle específica de un concurso. (**apiWordleGetWordleInContestContestIdWordleIndexGet**)

Obtiene una palabra Wordle específica de un concurso, verificando que el juego esté terminado para el usuario autenticado.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**wordleIndex (required)**

*Path Parameter* — Índice de la palabra Wordle en el concurso. 

**Return type**

[Wordle](#wordle)

**Example data**

Content-Type: application/json

{

`  `"contests" : [ null, null ],

`  `"folder" : {

`    `"parentFolder" : { },

`    `"folders" : [ "[]", "[]" ],

`    `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`    `"name" : "Carpeta",

`    `"id" : 1

`  `},

`  `"id" : 1,

`  `"word" : "Wordle",

`  `"user" : {

`    `"participations" : [ {

`      `"id" : 1

`    `}, {

`      `"id" : 1

`    `} ],

`    `"roles" : [ {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `}, {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `} ],

`    `"id" : 1,

`    `"email" : "profesor@gmail.com",

`    `"username" : "Profesor"

`  `}

}

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Palabra Wordle obtenida con éxito. [Wordle](#wordle) 

**401**

Juego no terminado para el usuario autenticado. []()

**404**

Concurso no encontrado. []()

-----
<a name="apiwordlegetwordlesbycontestcontestidget"></a>[Up](#__methods) 

GET /api/wordle/getWordlesByContest/{contestId}

Obtiene la lista de palabras Wordle asociadas a un concurso. (**apiWordleGetWordlesByContestContestIdGet**)

Obtiene la lista de palabras Wordle asociadas a un concurso específico. Para estudiantes, verifica si todos los juegos están terminados antes de retornar la lista.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso. format: int64

**Return type**

array[[Wordle](#wordle)] 

**Example data**

Content-Type: application/json

[ {

`  `"contests" : [ null, null ],

`  `"folder" : {

`    `"parentFolder" : { },

`    `"folders" : [ "[]", "[]" ],

`    `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`    `"name" : "Carpeta",

`    `"id" : 1

`  `},

`  `"id" : 1,

`  `"word" : "Wordle",

`  `"user" : {

`    `"participations" : [ {

`      `"id" : 1

`    `}, {

`      `"id" : 1

`    `} ],

`    `"roles" : [ {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `}, {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `} ],

`    `"id" : 1,

`    `"email" : "profesor@gmail.com",

`    `"username" : "Profesor"

`  `}

}, {

`  `"contests" : [ null, null ],

`  `"folder" : {

`    `"parentFolder" : { },

`    `"folders" : [ "[]", "[]" ],

`    `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`    `"name" : "Carpeta",

`    `"id" : 1

`  `},

`  `"id" : 1,

`  `"word" : "Wordle",

`  `"user" : {

`    `"participations" : [ {

`      `"id" : 1

`    `}, {

`      `"id" : 1

`    `} ],

`    `"roles" : [ {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `}, {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `} ],

`    `"id" : 1,

`    `"email" : "profesor@gmail.com",

`    `"username" : "Profesor"

`  `}

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de palabras Wordle obtenida con éxito. 

**400**

Juegos no terminados (solo para estudiantes). []()

**404**

Concurso no encontrado. []()

-----
<a name="apiwordlegetwordlesbyprofessorprofessornameget"></a>[Up](#__methods) 

GET /api/wordle/getWordlesByProfessor/{professorName}

Obtiene la lista de palabras Wordle creadas por un profesor. (**apiWordleGetWordlesByProfessorProfessorNameGet**)

Obtiene la lista de Wordles creados por un profesor específico, excluyendo las que están en carpetas. Requiere el rol de Administrador o de Profesor.

**Path parameters**

**professorName (required)**

*Path Parameter* — Nombre del profesor. 

**Return type**

array[[WordleDTO](#wordledto)] 

**Example data**

Content-Type: application/json

[ {

`  `"contests" : [ {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `}, {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `} ],

`  `"id" : 1,

`  `"word" : "Wordle"

}, {

`  `"contests" : [ {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `}, {

`    `"endDate" : "2000-01-23T04:56:07.000+00:00",

`    `"wordles" : [ [ ], [ ] ],

`    `"id" : 1,

`    `"contestName" : "Concurso",

`    `"numTries" : 6,

`    `"useDictionary" : true,

`    `"useExternalFile" : true,

`    `"wordlesLength" : [ [ ], [ ] ],

`    `"startDate" : "2000-01-23T04:56:07.000+00:00"

`  `} ],

`  `"id" : 1,

`  `"word" : "Wordle"

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de palabras Wordle obtenida con éxito. 

**404**

Profesor no encontrado. []()

-----
<a name="apiwordlegetwordlesinsidefolderfolderidget"></a>[Up](#__methods) 

GET /api/wordle/getWordlesInsideFolder/{folderId}

Obtiene la lista de palabras Wordle dentro de una carpeta. (**apiWordleGetWordlesInsideFolderFolderIdGet**)

Obtiene la lista de palabras Wordle que se encuentran dentro de la carpeta especificada por su ID. Requiere el rol de Profesor o Administrador.

**Path parameters**

**folderId (required)**

*Path Parameter* — ID de la carpeta. format: int64

**Return type**

array[[Wordle](#wordle)] 

**Example data**

Content-Type: application/json

[ {

`  `"contests" : [ null, null ],

`  `"folder" : {

`    `"parentFolder" : { },

`    `"folders" : [ "[]", "[]" ],

`    `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`    `"name" : "Carpeta",

`    `"id" : 1

`  `},

`  `"id" : 1,

`  `"word" : "Wordle",

`  `"user" : {

`    `"participations" : [ {

`      `"id" : 1

`    `}, {

`      `"id" : 1

`    `} ],

`    `"roles" : [ {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `}, {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `} ],

`    `"id" : 1,

`    `"email" : "profesor@gmail.com",

`    `"username" : "Profesor"

`  `}

}, {

`  `"contests" : [ null, null ],

`  `"folder" : {

`    `"parentFolder" : { },

`    `"folders" : [ "[]", "[]" ],

`    `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`    `"name" : "Carpeta",

`    `"id" : 1

`  `},

`  `"id" : 1,

`  `"word" : "Wordle",

`  `"user" : {

`    `"participations" : [ {

`      `"id" : 1

`    `}, {

`      `"id" : 1

`    `} ],

`    `"roles" : [ {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `}, {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `} ],

`    `"id" : 1,

`    `"email" : "profesor@gmail.com",

`    `"username" : "Profesor"

`  `}

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Lista de palabras Wordle obtenida con éxito. 

**404**

Carpeta no encontrada. []()

-----
<a name="apiwordlemovetofolderfolderidpost"></a>[Up](#__methods) 

POST /api/wordle/moveToFolder/{folderId}

Mueve una lista de palabras Wordle a una carpeta. (**apiWordleMoveToFolderFolderIdPost**)

Mueve una lista de palabras Wordle a la carpeta especificada por su ID. Si folderId es 0, mueve las palabras fuera de cualquier carpeta. Requiere el rol de Profesor o de Administrador.

**Path parameters**

**folderId (required)**

*Path Parameter* — ID de la carpeta destino (0 para mover fuera de carpetas). format: int64

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [string](#string) (required)**

*Body Parameter* — 

**Responses**

**200**

Palabras Wordle movidas con éxito. []()

**404**

Carpeta o palabra Wordle no encontrada. []()

-----
<a name="apiwordlenewfolderfoldernamepost"></a>[Up](#__methods) 

POST /api/wordle/newFolder/{folderName}

Crea una nueva carpeta. (**apiWordleNewFolderFolderNamePost**)

Crea una nueva carpeta con el nombre y el profesor especificados. Requiere el rol de Administrador o Profesor.

**Path parameters**

**folderName (required)**

*Path Parameter* — Nombre de la carpeta a crear. 

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- text/plain

**Request body**

**body [string](#string) (required)**

*Body Parameter* — 

**Return type**

[Folder](#folder)

**Example data**

Content-Type: application/json

{

`  `"parentFolder" : { },

`  `"folders" : [ "[]", "[]" ],

`  `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`  `"name" : "Carpeta",

`  `"id" : 1

}

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**200**

Carpeta creada con éxito. [Folder](#folder) 

**404**

Profesor no encontrado. []()

**409**

La carpeta ya existe. []()

-----
<a name="apiwordlenewfolderinsidefoldernewfoldernamefolderidpost"></a>[Up](#__methods) 

POST /api/wordle/newFolderInsideFolder/{newFolderName}/{folderId}

Crea una nueva carpeta dentro de otra carpeta. (**apiWordleNewFolderInsideFolderNewFolderNameFolderIdPost**)

Crea una nueva carpeta con el nombre especificado dentro de la carpeta con el ID especificado. Requiere el rol de Profesor o Administrador.

**Path parameters**

**newFolderName (required)**

*Path Parameter* — Nombre de la nueva carpeta a crear. 

**folderId (required)**

*Path Parameter* — ID de la carpeta padre. format: int64

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- text/plain

**Request body**

**body [string](#string) (required)**

*Body Parameter* — 

**Return type**

[Folder](#folder)

**Example data**

Content-Type: application/json

{

`  `"parentFolder" : { },

`  `"folders" : [ "[]", "[]" ],

`  `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`  `"name" : "Carpeta",

`  `"id" : 1

}

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**201**

Carpeta creada con éxito dentro de la carpeta padre. [Folder](#folder) 

**404**

Carpeta padre o profesor no encontrado. []()

**409**

La carpeta ya existe dentro de la carpeta padre. []()

-----
<a name="apiwordleupdatewordlewordinitialwordupdatedpost"></a>[Up](#__methods) 

POST /api/wordle/updateWordle/{wordInitial}/{wordUpdated}

Actualiza una palabra Wordle y sus concursos asociados. (**apiWordleUpdateWordleWordInitialWordUpdatedPost**)

Actualiza una palabra Wordle existente y sus asociaciones con concursos. Requiere el rol de Administrador o Profesor.

**Path parameters**

**wordInitial (required)**

*Path Parameter* — Palabra Wordle inicial a actualizar. 

**wordUpdated (required)**

*Path Parameter* — Nueva palabra Wordle actualizada. 

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [Contest](#contest) (required)**

*Body Parameter* — 

**Responses**

**200**

Palabra Wordle actualizada con éxito. []()

**404**

Palabra Wordle inicial o concurso no encontrado. []()

-----
<a name="apowordlenewwordlescontestidprofessornamefolderidpost"></a>[Up](#__methods) 

POST /apo/wordle/newWordles/{contestId}/{professorName}/{folderId}

Crea nuevas palabras Wordle y las asocia a un concurso y/o carpeta. (**apoWordleNewWordlesContestIdProfessorNameFolderIdPost**)

Crea una lista de nuevas palabras Wordle y las asocia opcionalmente a un concurso y/o carpeta. Requiere rol de Profesor o Administrador.

**Path parameters**

**contestId (required)**

*Path Parameter* — ID del concurso (0 si no se asocia a un concurso). format: int64

**professorName (required)**

*Path Parameter* — Nombre del profesor que crea las palabras. 

**folderId (required)**

*Path Parameter* — ID de la carpeta (0 si no se asocia a una carpeta). format: int64

**Consumes**

This API call consumes the following media types via the Content-Type request header: 

- application/json

**Request body**

**body [string](#string) (required)**

*Body Parameter* — 

**Return type**

array[[Wordle](#wordle)] 

**Example data**

Content-Type: application/json

[ {

`  `"contests" : [ null, null ],

`  `"folder" : {

`    `"parentFolder" : { },

`    `"folders" : [ "[]", "[]" ],

`    `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`    `"name" : "Carpeta",

`    `"id" : 1

`  `},

`  `"id" : 1,

`  `"word" : "Wordle",

`  `"user" : {

`    `"participations" : [ {

`      `"id" : 1

`    `}, {

`      `"id" : 1

`    `} ],

`    `"roles" : [ {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `}, {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `} ],

`    `"id" : 1,

`    `"email" : "profesor@gmail.com",

`    `"username" : "Profesor"

`  `}

}, {

`  `"contests" : [ null, null ],

`  `"folder" : {

`    `"parentFolder" : { },

`    `"folders" : [ "[]", "[]" ],

`    `"wordles" : [ "[\"Wordle\"]", "[\"Wordle\"]" ],

`    `"name" : "Carpeta",

`    `"id" : 1

`  `},

`  `"id" : 1,

`  `"word" : "Wordle",

`  `"user" : {

`    `"participations" : [ {

`      `"id" : 1

`    `}, {

`      `"id" : 1

`    `} ],

`    `"roles" : [ {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `}, {

`      `"id" : 0,

`      `"rolName" : "ROLE\_PROFESSOR"

`    `} ],

`    `"id" : 1,

`    `"email" : "profesor@gmail.com",

`    `"username" : "Profesor"

`  `}

} ]

**Produces**

This API call produces the following media types according to the Accept request header; the media type will be conveyed by the Content-Type response header. 

- application/json

**Responses**

**201**

Palabras Wordle creadas con éxito. 

**404**

Concurso, carpeta o profesor no encontrado. []()

-----
## <a name="__models"></a>**Models**
[ Jump to [Methods](#__methods) ] 
### **Table of Contents**
1. [Competition](#competition)
1. [CompetitionInput](#competitioninput)
1. [Contest](#contest)
1. [ContestInfo](#contestinfo)
1. [ContestInput](#contestinput)
1. [ContestState](#conteststate)
1. [DictionaryExternalSaved](#dictionaryexternalsaved)
1. [EditContestDTO](#editcontestdto)
1. [Folder](#folder)
1. [FolderDTO](#folderdto)
1. [Game](#game)
1. [JwtDto](#jwtdto)
1. [JwtDto_authorities](#jwtdto_authorities)
1. [Letter](#letter)
1. [LoginUser](#loginuser)
1. [NewUser](#newuser)
1. [Participation](#participation)
1. [ResumeContestDTO](#resumecontestdto)
1. [Rol](#rol)
1. [RolName](#rolname)
1. [RolProfessor](#rolprofessor)
1. [RolStudent](#rolstudent)
1. [State](#state)
1. [Try](#try)
1. [User](#user)
1. [UserProfessor](#userprofessor)
1. [UserState](#userstate)
1. [UserStudent](#userstudent)
1. [Wordle](#wordle)
1. [WordleDTO](#wordledto)
1. [WordleInput](#wordleinput)
1. [WordleState](#wordlestate)
1. [WordleStateLog](#wordlestatelog)
1. [WordlesStudentDTO](#wordlesstudentdto)
1. [addStudentsByExcel_competitionId_body](#addstudentsbyexcel_competitionid_body)
### <a name="competition"></a>**Competition [Up**](#__models)**
**id (optional)**

[*Long*](#long) format: int64

*example: 1*

**competitionName (optional)**

[*String*](#string)

*example: Competición*

**professor (optional)**

[*UserProfessor*](#userprofessor)

**participations (optional)**

[*array\[Participation\]*](#participation)

**contests (optional)**

[*array\[Contest\]*](#contest)
### <a name="competitioninput"></a>**CompetitionInput [Up**](#__models)**
**competitionName (optional)**

[*String*](#string)

*example: Competición*

**participations (optional)**

[*array\[null\]*]()

**contests (optional)**

[*array\[null\]*]()

**professor (optional)**

[*UserProfessor*](#userprofessor)
### <a name="contest"></a>**Contest [Up**](#__models)**
**id (optional)**

[*Long*](#long) format: int64

*example: 1*

**contestName (optional)**

[*String*](#string)

*example: Concurso*

**startDate (optional)**

[*Date*](#datetime) format: date-time

**endDate (optional)**

[*Date*](#datetime) format: date-time

**numTries (optional)**

[*Integer*](#integer)

*example: 6*

**useDictionary (optional)**

[*Boolean*](#boolean)

**useExternalFile (optional)**

[*Boolean*](#boolean)

**wordles (optional)**

[*array\[null\]*]()

**wordlesLength (optional)**

[*array\[null\]*]()
### <a name="contestinfo"></a>**ContestInfo [Up**](#__models)**
**contest (optional)**

[*Contest*](#contest)

**wordles (optional)**

[*array\[Wordle\]*](#wordle)
### <a name="contestinput"></a>**ContestInput [Up**](#__models)**
**id (optional)**

[*Long*](#long) format: int64

*example: 1*

**contestName (optional)**

[*String*](#string)

*example: Concurso*

**startDate (optional)**

[*Date*](#datetime) format: date-time

**endDate (optional)**

[*Date*](#datetime) format: date-time

**wordles (optional)**

[*array\[null\]*]()

**wordlesLength (optional)**

[*array\[null\]*]()
### <a name="conteststate"></a>**ContestState [Up**](#__models)**
**id (optional)**

[*Long*](#long) format: int64

**contest (optional)**

[*Contest*](#contest)

**user (optional)**

[*UserStudent*](#userstudent)

**state (optional)**

[*String*](#string)

*example: numberOfWordle:1, games:[{finished:false, won:false, tryCount:0, startTime:, finishTime:, timeNeeded:0, state:{good:0,halfGood:0,wrong:0}, lastWordle:, timeGuess:}]*

**letterCountsList (optional)**

[*array\[Object\]*](#object)
### <a name="dictionaryexternalsaved"></a>**DictionaryExternalSaved [Up**](#__models)**
**id (optional)**

[*Integer*](#integer)

*example: 1*

**wordle (optional)**

[*String*](#string)

*example: Wordle*

**contest (optional)**

[*Contest*](#contest)
### <a name="editcontestdto"></a>**EditContestDTO [Up**](#__models)**
**contest (optional)**

[*ContestInput*](#contestinput)

**wordles (optional)**

[*array\[WordleInput\]*](#wordleinput)
### <a name="folder"></a>**Folder [Up**](#__models)**
**id (optional)**

[*Long*](#long) format: int64

*example: 1*

**name (optional)**

[*String*](#string)

*example: Carpeta*

**professor (optional)**

[*UserProfessor*](#userprofessor)

**wordles (optional)**

[*array\[String\]*](#string)

**folders (optional)**

[*array\[String\]*](#string)

**parentFolder (optional)**

[*Object*](#object)
### <a name="folderdto"></a>**FolderDTO [Up**](#__models)**
**id (optional)**

[*Long*](#long) format: int64

*example: 1*

**name (optional)**

[*String*](#string)

*example: Carpeta*

**wordles (optional)**

[*array\[String\]*](#string)

**folders (optional)**

[*array\[String\]*](#string)

**parentFolder (optional)**

[*String*](#string)
### <a name="game"></a>**Game [Up**](#__models)**
**finished (optional)**

[*Boolean*](#boolean)

*example: false*

**won (optional)**

[*Boolean*](#boolean)

*example: false*

**tryCount (optional)**

[*Integer*](#integer)

**startTime (optional)**

[*Date*](#datetime) format: date-time

**finishTime (optional)**

[*Date*](#datetime) format: date-time

**timeNeeded (optional)**

[*Integer*](#integer)

**state (optional)**

[*State*](#state)

**lastWordle (optional)**

[*String*](#string)

**timeGuess (optional)**

[*Integer*](#integer)

*example: 0*
### <a name="jwtdto"></a>**JwtDto [Up**](#__models)**
**token** 

[*UUID*](#uuid) format: uuid

**bearer** 

[*UUID*](#uuid) format: uuid

**userName** 

[*UUID*](#uuid) format: uuid

**authorities** 

[*array\[JwtDto_authorities\]*](#jwtdto_authorities)
### <a name="jwtdto_authorities"></a>**JwtDto\_authorities [Up**](#__models)**
**authority (optional)**

[*String*](#string)

*example: ROLE\_PROFESSOR*
### <a name="letter"></a>**Letter [Up**](#__models)**
**letter** 

[*String*](#string) Letra ingresada en el intento. 

**state** 

[*Integer*](#integer) <p>Estado de la letra:</p> <ul> <li>0: Incorrecta</li> <li>1: Correcta pero en posición incorrecta</li> <li>2: Correcta y en posición correcta.</li> </ul> 

**Enum:**

*0*

*1*

*2*
### <a name="loginuser"></a>**LoginUser [Up**](#__models)**
**userName** 

[*UUID*](#uuid) format: uuid

**password** 

[*UUID*](#uuid) format: uuid
### <a name="newuser"></a>**NewUser [Up**](#__models)**
**name** 

[*String*](#string)

*example: Name*

**email** 

[*String*](#string)

*example: name@gmail.com*

**password** 

[*String*](#string)

*example: password*

**roles** 

[*array\[null\]*]()
### <a name="participation"></a>**Participation [Up**](#__models)**
**id (optional)**

[*Long*](#long) format: int64

*example: 1*
### <a name="resumecontestdto"></a>**ResumeContestDTO [Up**](#__models)**
**wordlePosition (optional)**

[*Integer*](#integer) Índice del Wordle actual que se está resolviendo. 

**tryPosition (optional)**

[*Integer*](#integer) Número de intentos realizados en el Wordle actual. 

**charPosition (optional)**

[*Integer*](#integer) Número total de caracteres introducidos hasta ahora. 

**tries (optional)**

[*array\[Try\]*](#try) Lista de intentos realizados. 

**wordleOrder (optional)**

[*array\[Integer\]*](#integer) Orden de los Wordles asignados al usuario (en modo aleatorio). 

**wordleState (optional)**

[*WordleState*](#wordlestate)
### <a name="rol"></a>**Rol [Up**](#__models)**
**id (optional)**

[*Integer*](#integer)

*example: 1*

**rolName (optional)**

[*RolName*](#rolname)
### <a name="rolname"></a>**RolName [Up**](#__models)**
### <a name="rolprofessor"></a>**RolProfessor [Up**](#__models)**
**id (optional)**

[*Long*](#long) format: int64

**Enum:**

*2*

**rolName (optional)**

[*String*](#string)

**Enum:**

*ROLE\_PROFESSOR*
### <a name="rolstudent"></a>**RolStudent [Up**](#__models)**
**id (optional)**

[*Long*](#long) format: int64

**Enum:**

*3*

**rolName (optional)**

[*String*](#string)

**Enum:**

*ROLE\_STUDENT*
### <a name="state"></a>**State [Up**](#__models)**
**good (optional)**

[*Integer*](#integer)

**halfGood (optional)**

[*Integer*](#integer)

**wrong (optional)**

[*Integer*](#integer)
### <a name="try"></a>**Try [Up**](#__models)**
**letters (optional)**

[*array\[Letter\]*](#letter) Lista de letras ingresadas en un intento. 
### <a name="user"></a>**User [Up**](#__models)**
**id (optional)**

[*Long*](#long) format: int64

*example: 1*

**username (optional)**

[*String*](#string)

*example: Usuario*

**email (optional)**

[*String*](#string)

*example: usuario@gmail.com*

**password (optional)**

[*String*](#string)

**roles (optional)**

[*array\[Rol\]*](#rol)

**participations (optional)**

[*array\[Participation\]*](#participation)
### <a name="userprofessor"></a>**UserProfessor [Up**](#__models)**
**id (optional)**

[*Integer*](#integer)

*example: 1*

**username (optional)**

[*String*](#string)

*example: Profesor*

**email (optional)**

[*String*](#string)

*example: profesor@gmail.com*

**roles (optional)**

[*array\[RolProfessor\]*](#rolprofessor)

**participations (optional)**

[*array\[Participation\]*](#participation)
### <a name="userstate"></a>**UserState [Up**](#__models)**
**username (optional)**

[*String*](#string)

*example: Alumno*

**email (optional)**

[*String*](#string)

*example: alumno@gmail.com*

**state (optional)**

[*String*](#string)

*example: numberOfWordle:1, games:[{finished:false, won:false, tryCount:0, startTime:, finishTime:, timeNeeded:0, state:{good:0,halfGood:0,wrong:0}, lastWordle:, timeGuess:}]*
### <a name="userstudent"></a>**UserStudent [Up**](#__models)**
**id (optional)**

[*Long*](#long) format: int64

*example: 1*

**username (optional)**

[*String*](#string)

*example: Alumno*

**email (optional)**

[*String*](#string)

*example: alumno@gmail.com*

**roles (optional)**

[*array\[RolStudent\]*](#rolstudent)

**participations (optional)**

[*array\[null\]*]()
### <a name="wordle"></a>**Wordle [Up**](#__models)**
**id (optional)**

[*Long*](#long) format: int64

*example: 1*

**word (optional)**

[*String*](#string)

*example: Wordle*

**contests (optional)**

[*array\[Contest\]*](#contest)

**user (optional)**

[*UserProfessor*](#userprofessor)

**folder (optional)**

[*Folder*](#folder)
### <a name="wordledto"></a>**WordleDTO [Up**](#__models)**
**id (optional)**

[*Long*](#long) format: int64

*example: 1*

**word (optional)**

[*String*](#string)

*example: Wordle*

**contests (optional)**

[*array\[Contest\]*](#contest)
### <a name="wordleinput"></a>**WordleInput [Up**](#__models)**
### <a name="wordlestate"></a>**WordleState [Up**](#__models)**
**numberOfWordle (optional)**

[*Integer*](#integer)

*example: 1*

**games (optional)**

[*array\[Game\]*](#game)
### <a name="wordlestatelog"></a>**WordleStateLog [Up**](#__models)**
**userName (optional)**

[*String*](#string)

*example: Alumno*

**email (optional)**

[*String*](#string)

*example: alumno@gmail.com*

**dateLog (optional)**

[*String*](#string)

*example: 2025-03-11T12:20:12.000Z*

**wordleToGuess (optional)**

[*String*](#string)

*example: Wordle*

**wordleInserted (optional)**

[*String*](#string)

*example: Wordle*

**numTry (optional)**

[*Integer*](#integer)

*example: 1*

**wordlePosition (optional)**

[*Integer*](#integer)

*example: 1*

**correct (optional)**

[*Integer*](#integer)

*example: 0*

**wrongPosition (optional)**

[*Integer*](#integer)

*example: 0*

**wrong (optional)**

[*Integer*](#integer)

*example: 0*

**state (optional)**

[*Boolean*](#boolean)

*example: false*
### <a name="wordlesstudentdto"></a>**WordlesStudentDTO [Up**](#__models)**
**competition (optional)**

[*Competition*](#competition)

**contestInfo (optional)**

[*ContestInfo*](#contestinfo)
### <a name="addstudentsbyexcel_competitionid_body"></a>**addStudentsByExcel\_competitionId\_body [Up**](#__models)**
**file (optional)**

[*byte\[\]*](#binary) Archivo Excel con la información de los estudiantes. format: binary

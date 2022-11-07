package seg3x02.booksrestapi.representation

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel

@JsonInclude(JsonInclude.Include.NON_NULL)
class AuthorNameRepresentation: RepresentationModel<AuthorNameRepresentation>() {
    var firstName: String = ""
    var lastName: String = ""
}

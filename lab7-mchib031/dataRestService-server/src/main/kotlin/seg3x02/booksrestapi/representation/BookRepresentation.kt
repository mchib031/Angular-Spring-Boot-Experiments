package seg3x02.booksrestapi.representation

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation

@Relation(collectionRelation = "books")
@JsonInclude(JsonInclude.Include.NON_NULL)
class BookRepresentation: RepresentationModel<BookRepresentation>() {
    var id: Long = 0
    var title: String = ""
    var category: String = ""
    var isbn: String = ""
    var cost: Double = 0.0
    var year: Int = 0
    var description: String = ""
    var authors: List<AuthorNameRepresentation> = ArrayList<AuthorNameRepresentation>()
}

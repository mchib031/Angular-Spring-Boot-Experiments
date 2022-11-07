import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import org.springframework.hateoas.server.core.Relation

@Relation(collectionRelation = "orders")
@JsonInclude(JsonInclude.Include.NON_NULL)
class OrderRepresentation: RepresentationModel<OrderRepresentation>() {
    var id: Long = 0
    var quantity: Int = 0
}

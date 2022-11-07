package seg3x02.booksrestapi.assemblers

import OrderRepresentation
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component
import seg3x02.booksrestapi.controller.ApiController
import seg3x02.booksrestapi.entities.Order

@Component
class OrderModelAssembler: RepresentationModelAssemblerSupport<Order,
        OrderRepresentation>(ApiController::class.java, OrderRepresentation::class.java) {
    override fun toModel(entity: Order): OrderRepresentation {
        val orderRepresentation = instantiateModel(entity)
        orderRepresentation.add(
            WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ApiController::class.java)
                .getOrderById(entity.id))
            .withSelfRel())
        orderRepresentation.id = entity.id
        orderRepresentation.quantity = entity.quantity
        return orderRepresentation
    }

    fun toCollectionModel(entities: Set<Order>): CollectionModel<OrderRepresentation> {
        val orderRepresentations = super.toCollectionModel(entities)
        orderRepresentations.add(WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(
                ApiController::class.java)).withRel("orders"))
        return orderRepresentations
    }
}

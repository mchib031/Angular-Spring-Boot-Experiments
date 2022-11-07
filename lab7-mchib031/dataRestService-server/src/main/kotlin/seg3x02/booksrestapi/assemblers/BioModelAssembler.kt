package seg3x02.booksrestapi.assemblers

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Component
import seg3x02.booksrestapi.controller.ApiController
import seg3x02.booksrestapi.entities.Bio
import seg3x02.booksrestapi.representation.BioRepresentation

@Component
class BioModelAssembler : RepresentationModelAssemblerSupport<Bio,
        BioRepresentation>(ApiController::class.java, BioRepresentation::class.java) {
    override fun toModel(entity: Bio): BioRepresentation {
        val bioRepresentation = instantiateModel(entity)
        bioRepresentation.add(
            WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ApiController::class.java)
                .getBioById(entity.id))
            .withSelfRel())
        bioRepresentation.id = entity.id
        bioRepresentation.biodata = entity.biodata
        return bioRepresentation
    }
}

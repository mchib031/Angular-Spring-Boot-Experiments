package seg3x02.booksrestapi.assemblers

import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Component
import seg3x02.booksrestapi.controller.ApiController
import seg3x02.booksrestapi.entities.Author
import seg3x02.booksrestapi.entities.Book
import seg3x02.booksrestapi.representation.AuthorRepresentation
import seg3x02.booksrestapi.representation.BookTitleRepresentation
import java.util.*

@Component
class AuthorModelAssembler: RepresentationModelAssemblerSupport<Author,
        AuthorRepresentation>(ApiController::class.java, AuthorRepresentation::class.java) {
    override fun toModel(entity: Author): AuthorRepresentation {
        val authorRepresentation = instantiateModel(entity)
        authorRepresentation.add(linkTo(
            WebMvcLinkBuilder.methodOn(ApiController::class.java)
                .getAuthorById(entity.id))
            .withSelfRel())
        authorRepresentation.books = toBooksRepresentation(entity.books)
        authorRepresentation.add(linkTo(
            WebMvcLinkBuilder.methodOn(ApiController::class.java)
                .getAuthorBioById(entity.id))
            .withRel("bio"))
        authorRepresentation.id = entity.id

        authorRepresentation.firstName = entity.firstName
        authorRepresentation.lastName = entity.lastName
        return authorRepresentation
    }

    override fun toCollectionModel(entities: Iterable<Author>): CollectionModel<AuthorRepresentation> {
        val authorRepresentations = super.toCollectionModel(entities)
        authorRepresentations.add(linkTo(
            WebMvcLinkBuilder.methodOn(
                ApiController::class.java).allAuthors()).withSelfRel())
        return authorRepresentations
    }

    fun toBooksRepresentation(books: List<Book>): List<BookTitleRepresentation> {
        return if (books.isEmpty()) Collections.emptyList() else books
            .map{
                bookRepresentation(it)
            }
    }

    private fun bookRepresentation(book: Book): BookTitleRepresentation {
        val representation = BookTitleRepresentation()
        representation.title = book.title
        return representation.add(linkTo(
            WebMvcLinkBuilder.methodOn(ApiController::class.java)
                .getBookById(book.id))
            .withSelfRel())
    }
}

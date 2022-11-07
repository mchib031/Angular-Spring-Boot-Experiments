package seg3x02.booksrestapi.controller

import OrderRepresentation
import io.swagger.v3.oas.annotations.Operation
import org.springframework.hateoas.CollectionModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import seg3x02.booksrestapi.assemblers.AuthorModelAssembler
import seg3x02.booksrestapi.assemblers.BioModelAssembler
import seg3x02.booksrestapi.assemblers.BookModelAssembler
import seg3x02.booksrestapi.assemblers.OrderModelAssembler
import seg3x02.booksrestapi.entities.Author
import seg3x02.booksrestapi.entities.Bio
import seg3x02.booksrestapi.entities.Book
import seg3x02.booksrestapi.entities.Order
import seg3x02.booksrestapi.repository.AuthorRepository
import seg3x02.booksrestapi.repository.BioRepository
import seg3x02.booksrestapi.repository.BookRepository
import seg3x02.booksrestapi.repository.OrderRepository
import seg3x02.booksrestapi.representation.*
import java.net.URI

@RestController
@CrossOrigin(origins = ["http://localhost:4200"])
@RequestMapping("books-api", produces = ["application/hal+json"])
class ApiController(val authorRepository: AuthorRepository,
                    val bookRepository: BookRepository,
                    val bioRepository: BioRepository,
                    val orderRepository: OrderRepository,
                    val authorAssembler: AuthorModelAssembler,
                    val bookAssembler: BookModelAssembler,
                    val orderAssembler: OrderModelAssembler,
                    val bioAssembler: BioModelAssembler
) {
    @Operation(summary = "Get all authors")
    @GetMapping("/authors")
    fun allAuthors(): ResponseEntity<CollectionModel<AuthorRepresentation>> {
        val authors = authorRepository.findAll()
        return ResponseEntity(
            authorAssembler.toCollectionModel(authors),
            HttpStatus.OK)
    }

    @Operation(summary = "Get all authors by firstName and lastName")
    @GetMapping("/authors", params = ["firstName", "lastName"])
    fun getAuthorsByName(@RequestParam("firstName") firstName: String,
                         @RequestParam("lastName") lastName: String):
            ResponseEntity<CollectionModel<AuthorRepresentation>> {
        val authors = authorRepository.findAuthorsByName(firstName, lastName)
        return ResponseEntity(
            authorAssembler.toCollectionModel(authors),
            HttpStatus.OK)
    }

    @Operation(summary = "Get an authors by id")
    @GetMapping("/authors/{id}")
    fun getAuthorById(@PathVariable("id") id: Long): ResponseEntity<AuthorRepresentation> {
        return authorRepository.findById(id)
            .map { entity: Author -> authorAssembler.toModel(entity) }
            .map { body: AuthorRepresentation -> ResponseEntity.ok(body) }
            .orElse(ResponseEntity.notFound().build())
    }

    @Operation(summary = "Get all the books of and author")
    @GetMapping("/authors/{id}/books")
    fun getAuthorBooksById(@PathVariable("id") id: Long): ResponseEntity<List<BookTitleRepresentation>> {
        return authorRepository.findById(id)
            .map { author: Author -> authorAssembler.toBooksRepresentation(author.books) }
            .map { body: List<BookTitleRepresentation> -> ResponseEntity.ok(body) }
            .orElse(ResponseEntity.notFound().build())
    }

    @Operation(summary = "Get an author's biographical information")
    @GetMapping("/authors/{id}/bio")
    fun getAuthorBioById(@PathVariable("id") id: Long): ResponseEntity<BioRepresentation> {
        return authorRepository.findById(id)
            .map { author: Author ->  bioAssembler.toModel(author.bio)}
            .map { body: BioRepresentation -> ResponseEntity.ok(body) }
            .orElse(ResponseEntity.notFound().build())
    }

    @Operation(summary = "Get all books")
    @GetMapping("/books")
    fun allBooks(): ResponseEntity<CollectionModel<BookRepresentation>> {
        val books = bookRepository.findAll()
        return ResponseEntity(
            bookAssembler.toCollectionModel(books),
            HttpStatus.OK)
    }

    @Operation(summary = "Get a book by id")
    @GetMapping("/books/{id}")
    fun getBookById(@PathVariable("id") id: Long): ResponseEntity<BookRepresentation> {
        return bookRepository.findById(id)
            .map { entity: Book -> bookAssembler.toModel(entity) }
            .map { body: BookRepresentation -> ResponseEntity.ok(body) }
            .orElse(ResponseEntity.notFound().build())
    }

    @Operation(summary = "Get all authors of a book")
    @GetMapping("/books/{id}/authors")
    fun getBookAuthorsById(@PathVariable("id") id: Long): ResponseEntity<List<AuthorNameRepresentation>> {
        return bookRepository.findById(id)
            .map { book: Book -> bookAssembler.toAuthorsRepresentation(book.authors) }
            .map { body: List<AuthorNameRepresentation> -> ResponseEntity.ok(body) }
            .orElse(ResponseEntity.notFound().build())
    }

    @Operation(summary = "Get all orders of a book")
    @GetMapping("/books/{id}/orders")
    fun getBookOrdersById(@PathVariable("id") id: Long): ResponseEntity<CollectionModel<OrderRepresentation>> {
        return bookRepository.findById(id)
            .map { book: Book -> orderAssembler.toCollectionModel(book.orders)}
            .map { body: CollectionModel<OrderRepresentation> -> ResponseEntity.ok(body) }
            .orElse(ResponseEntity.notFound().build())
    }

    @Operation(summary = "Get a bio by id")
    @GetMapping("/bios/{id}")
    fun getBioById(@PathVariable("id") id: Long): ResponseEntity<BioRepresentation> {
        return bioRepository.findById(id)
            .map { entity: Bio -> bioAssembler.toModel(entity)}
            .map { body: BioRepresentation -> ResponseEntity.ok(body) }
            .orElse(ResponseEntity.notFound().build())
    }

    @Operation(summary = "Get an order by id")
    @GetMapping("/orders/{id}")
    fun getOrderById(@PathVariable("id") id: Long): ResponseEntity<OrderRepresentation> {
        return orderRepository.findById(id)
            .map { order: Order ->  orderAssembler.toModel(order)}
            .map { body: OrderRepresentation -> ResponseEntity.ok(body) }
            .orElse(ResponseEntity.notFound().build())
    }

    @Operation(summary = "Add a new book")
    @PostMapping("/books")
    fun addBook(@RequestBody book: Book): ResponseEntity<Any> {
        return try {
            val newBook = this.bookRepository.save(book)
            val location: URI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newBook.id)
                .toUri()
            ResponseEntity.created(location).body(bookAssembler.toModel(newBook))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @Operation(summary = "Add an order to a book")
    @PostMapping("/books/{id}/orders")
    fun addOrderToBook(@PathVariable("id") id: Long, @RequestBody order: Order): ResponseEntity<Any> {
        return try {
            val book = bookRepository.findById(id).get()
            book.orders.add(order)
            val newOrder = orderRepository.save(order)
            val location: URI = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/books-api")
                .path("/orders")
                .path("/{id}")
                .buildAndExpand(newOrder.id)
                .toUri()
            ResponseEntity.created(location).body(orderAssembler.toModel(newOrder))
        } catch (e: NoSuchElementException) {
            ResponseEntity.badRequest().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @Operation(summary = "Add a new author to a book")
    @PostMapping("/books/{id}/authors")
    fun addAuthorToBook(@PathVariable("id") id: Long, @RequestBody author: Author): ResponseEntity<Any> {
        return try {
            val book = bookRepository.findById(id).get()
            author.books.add(book)
            val newAuthor = authorRepository.save(author)
            val location: URI = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/books-api")
                .path("/authors")
                .path("/{id}")
                .buildAndExpand(newAuthor.id)
                .toUri()
            ResponseEntity.created(location).body(authorAssembler.toModel(newAuthor))
        } catch (e: NoSuchElementException) {
            ResponseEntity.badRequest().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @Operation(summary = "Add a new author")
    @PostMapping("/authors")
    fun addAuthor(@RequestBody author: Author): ResponseEntity<Any> {
        return try {
            val newAuthor = this.authorRepository.save(author)
            val location: URI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newAuthor.id)
                .toUri()
            ResponseEntity.created(location).body(authorAssembler.toModel(newAuthor))
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @Operation(summary = "Add a new book to an author")
    @PostMapping("/authors/{id}/books")
    fun addBookToAuthor(@PathVariable("id") id: Long, @RequestBody book: Book): ResponseEntity<Any> {
        return try {
            val author = authorRepository.findById(id).get()
            book.authors.add(author)
            author.books.add(book)
            val newBook = bookRepository.save(book)
            val location: URI = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/books-api")
                .path("/books")
                .path("/{id}")
                .buildAndExpand(newBook.id)
                .toUri()
            ResponseEntity.created(location).body(bookAssembler.toModel(newBook))
        } catch (e: NoSuchElementException) {
            ResponseEntity.badRequest().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @Operation(summary = "Add an existing author to an existing book")
    @PatchMapping("/books/{bid}/authors/{aid}")
    fun updateBookAuthors(@PathVariable("bid") bid: Long,
                          @PathVariable("aid") aid: Long): ResponseEntity<Any> {
        return try {
            val book = bookRepository.findById(bid).get()
            val author = authorRepository.findById(aid).get()
            if (!book.authors.contains(author)) {
                book.authors.add(author)
                author.books.add(book)
                bookRepository.save(book)
            }
            ResponseEntity.noContent().build<Any>()
        } catch (e: NoSuchElementException) {
            ResponseEntity.badRequest().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @Operation(summary = "Update the firstName and lastName of an author")
    @PutMapping("/authors/{id}")
    fun updateAuthor(@PathVariable("id") id: Long, @RequestBody author: Author): ResponseEntity<Any> {
        return try {
            val currAuthor = authorRepository.findById(id).get()
            currAuthor.firstName = author.firstName
            currAuthor.lastName = author.lastName
            authorRepository.save(currAuthor)
            ResponseEntity.noContent().build<Any>()
        } catch (e: NoSuchElementException) {
            ResponseEntity.badRequest().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @Operation(summary = "Update the information of a book")
    @PutMapping("/books/{id}")
    fun updateBook(@PathVariable("id") id: Long, @RequestBody book: Book): ResponseEntity<Any> {
        return try {
            val currBook = bookRepository.findById(id).get()
            currBook.title = book.title
            currBook.isbn = book.isbn
            currBook.cost = book.cost
            currBook.category = book.category
            currBook.description = book.description
            currBook.year = book.year
            bookRepository.save(currBook)
            ResponseEntity.noContent().build<Any>()
        } catch (e: NoSuchElementException) {
            ResponseEntity.badRequest().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @Operation(summary = "Update an order")
    @PutMapping("/orders/{id}")
    fun updateOrder(@PathVariable("id") id: Long, @RequestBody order: Order): ResponseEntity<Any> {
        return try {
            val currOrder = orderRepository.findById(id).get()
            currOrder.quantity = order.quantity
            orderRepository.save(currOrder)
            ResponseEntity.noContent().build<Any>()
        } catch (e: NoSuchElementException) {
            ResponseEntity.badRequest().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @Operation(summary = "Update a biographical information")
    @PutMapping("/bios/{id}")
    fun updateBio(@PathVariable("id") id: Long, @RequestBody bio: Bio): ResponseEntity<Any> {
        return try {
            val currBio = bioRepository.findById(id).get()
            currBio.biodata = bio.biodata
            bioRepository.save(currBio)
            ResponseEntity.noContent().build<Any>()
        } catch (e: NoSuchElementException) {
            ResponseEntity.badRequest().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @Operation(summary = "Remove an author")
    @DeleteMapping("/authors/{id}")
    fun deleteAuthor(@PathVariable("id") id: Long): ResponseEntity<Any> {
        return try {
            this.authorRepository.deleteById(id)
            ResponseEntity.noContent().build<Any>()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @Operation(summary = "Remove an book")
    @DeleteMapping("/books/{id}")
    fun deleteBook(@PathVariable("id") id: Long): ResponseEntity<Any> {
        return try {
            this.bookRepository.deleteById(id)
            ResponseEntity.noContent().build<Any>()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }

    @Operation(summary = "Remove an order from a book")
    @DeleteMapping("/books/{bkId}/orders/{ordId}")
    fun deleteAuthorBio(@PathVariable("bkId") bkId: Long,
                        @PathVariable("ordId") ordId: Long): ResponseEntity<Any> {
        return try {
            val book = this.bookRepository.findById(bkId).get()
            val order = book.orders.find {  it.id == ordId }
            book.orders.remove(order)
            this.bookRepository.save(book)
            this.orderRepository.deleteById(ordId)
            ResponseEntity.noContent().build<Any>()
        } catch (e: NoSuchElementException) {
            ResponseEntity.badRequest().build()
        }catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().build()
        }
    }
}

package seg3x02.booksrestapi.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import seg3x02.booksrestapi.entities.Author

interface AuthorRepository: CrudRepository<Author, Long> {
    @Query(value="select aut from Author aut where aut.firstName = :firstName and aut.lastName = :lastName")
    fun findAuthorsByName(firstName: String, lastName: String): List<Author>
}

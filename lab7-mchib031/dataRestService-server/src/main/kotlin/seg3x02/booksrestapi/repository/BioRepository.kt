package seg3x02.booksrestapi.repository

import org.springframework.data.repository.CrudRepository
import seg3x02.booksrestapi.entities.Bio

interface BioRepository: CrudRepository<Bio, Long>

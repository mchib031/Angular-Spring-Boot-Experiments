package seg3x02.graphqlcontactlistapi.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import seg3x02.graphqlcontactlistapi.entity.User

@Repository
interface UserRepository: MongoRepository<User, String>
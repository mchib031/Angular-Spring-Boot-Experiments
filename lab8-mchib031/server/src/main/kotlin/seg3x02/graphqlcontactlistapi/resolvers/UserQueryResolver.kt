package seg3x02.graphqlcontactlistapi.resolvers

import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.stereotype.Component
import seg3x02.graphqlcontactlistapi.entity.User
import seg3x02.graphqlcontactlistapi.repository.UserRepository


@Component
class UserQueryResolver(val userRepository:UserRepository, private val mongoOperation:MongoOperations): GraphQLQueryResolver{
   fun users():List<User> {
      val list = userRepository.findAll()
      return list
   }
   fun userById(userId:String) :User?{
      val user=userRepository.findById(userId)
      return if(user.isPresent){
         user.get()
      }
      else{
         null
      }
   }
}

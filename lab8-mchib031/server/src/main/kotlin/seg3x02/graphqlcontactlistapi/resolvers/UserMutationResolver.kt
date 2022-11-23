package seg3x02.graphqlcontactlistapi.resolvers

import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.stereotype.Component
import seg3x02.graphqlcontactlistapi.entity.User
import seg3x02.graphqlcontactlistapi.repository.UserRepository

@Component
class UserMutationResolver(private val userRepository:UserRepository):GraphQLMutationResolver{
    fun newUser(
        firstName:String,
        lastName:String,
        phoneNumber:String?,
        email:String?
    ):User{
        val user = User(firstName,lastName,phoneNumber,email)
        user.userId= java.util.UUID.randomUUID().toString()
        userRepository.save(user)
        return user
    }
    fun deleteUser(userId:String):Boolean{
        userRepository.deleteById(userId)
        return true
    }

    fun updateUser(
        userId:String,
        firstName:String?,
        lastName:String?,
        phoneNumber:String?,
        email:String?
    ):User{
        val user=userRepository.findById(userId)
        user.ifPresent{
            if(firstName!=null){
                it.firstName=firstName
            }
            if(lastName!=null){
                it.lastName=lastName
            }
            if(phoneNumber!=null){
                it.phoneNumber=phoneNumber
            }
            if(email!=null){
                it.email=email
            }
            userRepository.save(it)
        }
        return user.get()
    }
}
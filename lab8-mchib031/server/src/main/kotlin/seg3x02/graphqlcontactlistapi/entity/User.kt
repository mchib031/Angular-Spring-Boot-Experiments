package seg3x02.graphqlcontactlistapi.entity

import org.springframework.data.annotation.Id
 import org.springframework.data.mongodb.core.mapping.Document

 @Document(collection = "user")
 data class User(var firstName:String,
                 var lastName:String,
                 var phoneNumber:String?,
                 var email:String?
                 ){
     @Id
     var userId:String=""
 }
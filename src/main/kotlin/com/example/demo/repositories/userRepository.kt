package com.example.demo.repositories

import com.example.demo.models.what.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {
    fun findOneById(id: String): User
    override fun deleteAll()

}
package com.example.demo.repositories

import com.example.demo.models.what.Rn
import org.springframework.data.mongodb.repository.MongoRepository

interface RnsRepository : MongoRepository<Rn, String> {
    fun findByRngsa(rngsa: String): Rn

}
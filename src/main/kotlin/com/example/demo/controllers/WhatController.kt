package com.example.demo.controllers

import com.example.demo.models.what.Place
import com.example.demo.models.what.Rn
import com.example.demo.models.what.User
import com.example.demo.models.what.WhatResponse
import com.example.demo.repositories.WebRepository
import com.example.demo.repositories.RnsRepository
import com.google.gson.Gson
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.io.IOException
import java.io.InputStream


@RestController
class WhatController(val webRepository: WebRepository,
                      val rnsRepository: RnsRepository
) {

    @PostMapping("/what")
    fun what(@RequestBody request: WhatRequest): ResponseEntity<WhatResponse> {
        val data = read()
        println(data)
        rnsRepository.findByRngsa("12.00528/S")

        val html =webRepository.request("15","00047","S");
        val pair=webRepository.parse(html.orEmpty())
        return ResponseEntity.ok(WhatResponse(user = User(id="id"), rn= Rn(_id="",rngsa = pair.first,product_data = pair.second, isFavourite = false, places = emptyList())))
    }

    fun read(): WhatResponse? {
        return try {
            Gson().fromJson(readJSONFromAsset(), WhatResponse::class.java)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun readJSONFromAsset(): String? {
        return try {
            val fileName = "./src/main/resources/what_response.json"
            val myFile = File(fileName)

            val inputStream: InputStream = myFile.inputStream()
            inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            null
        }
    }

}

class WhatRequest(val user: User, val rn: String, val place: Place?)


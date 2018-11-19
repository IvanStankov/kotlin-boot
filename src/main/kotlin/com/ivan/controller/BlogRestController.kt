package com.ivan.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class Blog(val id: Long, val name: String, val description: String)

@RestController
@RequestMapping("/blog")
class BlogRestController {

    companion object {
        val db: MutableMap<Long, Blog> = mutableMapOf(1L to Blog(1, "Blog1", "Blog1 description"),
            2L to Blog(2, "Blog2", "Blog2 description"))
    }

    @GetMapping
    fun getBlogList(): List<Blog> {
        return db.valuesAsList()
    }

    @GetMapping("/{id}")
    fun getBlog(@PathVariable id: Long): Blog {
        return db[id]!!
    }

}
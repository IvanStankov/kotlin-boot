package com.ivan.service

import com.ivan.util.valuesAsList
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException

data class Blog(val id: Long, val name: String, val description: String)

interface BlogService {
    fun findBlogs(): List<Blog>
    fun findBlog(id: Long): Blog?
}

@Component
class MapBasedBlogService : BlogService {

    companion object {
        val db: MutableMap<Long, Blog> = mutableMapOf(
            1L to Blog(1, "Cars", "Blog about cars"),
            2L to Blog(2, "Books", "Like reading books? Check it out"),
            3L to Blog(3, "Programming", "Wanna become (already) a geek? Welcome"))
    }

    override fun findBlogs() = db.valuesAsList()

    override fun findBlog(id: Long): Blog {
        return db[id] ?: throw IllegalArgumentException("No blog with id = $id")
    }
}


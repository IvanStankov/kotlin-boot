package com.ivan.service

import com.ivan.util.isAfterOrEqual
import com.ivan.util.isBeforeOrEqual
import com.ivan.util.randomDateTime
import com.ivan.util.valuesAsList
import org.springframework.stereotype.Component

interface BlogService {
    fun findBlogs(filter: BlogFilter): List<Blog>
    fun findBlog(blogId: BlogId): Blog
}

@Component
class MapBasedBlogService : BlogService {

    val db: MutableMap<Long, Blog> = mutableMapOf(
        1L to Blog(1, "Cars", "Blog about cars", randomDateTime()),
        2L to Blog(2, "Books", "Like reading books? Check it out", randomDateTime()),
        3L to Blog(3, "Programming", "Wanna become (already) a geek? Welcome", randomDateTime())
    )

    override fun findBlogs(filter: BlogFilter) =
        db.valuesAsList().asSequence()
            .filter { blog ->
                if (filter.title != null) blog.name.contains(filter.title) else true
            }
            .filter { blog ->
                if (filter.creationDate?.from != null) blog.creationDate.isAfterOrEqual(filter.creationDate.from) else true
            }
            .filter { blog ->
                if (filter.creationDate?.to != null) blog.creationDate.isBeforeOrEqual(filter.creationDate.to) else true
            }
            .toList()

    override fun findBlog(blogId: BlogId): Blog {
        return db[blogId.id] ?: throw IllegalArgumentException("No blog with id = $blogId")
    }
}
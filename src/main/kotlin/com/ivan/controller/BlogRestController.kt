package com.ivan.controller

import com.ivan.DateRange
import com.ivan.service.Blog
import com.ivan.service.BlogFilter
import com.ivan.service.BlogId
import com.ivan.service.BlogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/blogs")
class BlogRestController {

    @Autowired
    lateinit var blogService: BlogService

    @GetMapping
    fun getBlogList(
        @RequestParam(required = false) title: String?, @RequestParam(required = false) creationDateFrom: LocalDateTime?,
        @RequestParam(required = false) creationDateTo: LocalDateTime?
    ): List<Blog> {
        val blogFilter = BlogFilter(title, DateRange(creationDateFrom, creationDateTo))
        return blogService.findBlogs(blogFilter)
    }

    @GetMapping("/{id}")
    fun getBlog(@PathVariable id: Long): Blog {
        return blogService.findBlog(BlogId(id))
    }

}
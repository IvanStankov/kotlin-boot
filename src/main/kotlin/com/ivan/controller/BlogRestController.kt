package com.ivan.controller

import com.ivan.service.Blog
import com.ivan.service.BlogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blog")
class BlogRestController {

    @Autowired
    lateinit var blogService: BlogService

    @GetMapping
    fun getBlogList(): List<Blog> {
        return blogService.findBlogs()
    }

    @GetMapping("/{id}")
    fun getBlog(@PathVariable id: Long): Blog {
        return blogService.findBlog(id)!!
    }

}
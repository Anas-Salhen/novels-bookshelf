package com.example.novelsbookshelf.fake

import com.example.novelsbookshelf.model.ImageLinks
import com.example.novelsbookshelf.model.Info
import com.example.novelsbookshelf.model.Item
import com.example.novelsbookshelf.model.NestedResponse
import com.example.novelsbookshelf.model.Novel

object FakeDataSource {

    private const val idOne = "novelOne"
    private const val idTwo = "novelTwo"
    private const val idThree = "novelThree"
    private val infoOne = Info(
        title = "titleOne",
        imageLinks = ImageLinks(
            thumbnailUrl = "thumbnailOne"
        )
    )
    private val infoTwo = Info(
        title = "titleTwo",
        imageLinks = ImageLinks(
            thumbnailUrl = "thumbnailTwo"
        )
    )
    private val infoThree = Info(
        title = "titleThree",
        imageLinks = ImageLinks(
            thumbnailUrl = "thumbnailThree"
        )
    )
    private val items = mutableListOf(
        Item(
            id = idOne,
            volumeInfo = infoOne
        ),
        Item(
            id = idTwo,
            volumeInfo = infoTwo
        ),
        Item(
            id = idThree,
            volumeInfo = infoThree
        )
    )
    val nestedResponse = NestedResponse(items)
    val novels = listOf(
        Novel(
            id = idOne,
            title = infoOne.title,
            thumbnailUrl = infoOne.imageLinks.thumbnailUrl
        ),
        Novel(
            id = idTwo,
            title = infoTwo.title,
            thumbnailUrl = infoTwo.imageLinks.thumbnailUrl
        ),
        Novel(
            id = idThree,
            title = infoThree.title,
            thumbnailUrl = infoThree.imageLinks.thumbnailUrl
        )
    )
}
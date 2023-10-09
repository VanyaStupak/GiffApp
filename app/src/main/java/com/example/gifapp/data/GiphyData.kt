package com.example.gifapp.data

data class GifItem(
    val id: String,
    val images: Images,
    val title: String
)

data class Images(
    val original: OriginalImage
)

data class OriginalImage(
    val url: String
)

data class GifData(
    val data: List<GifItem>
)
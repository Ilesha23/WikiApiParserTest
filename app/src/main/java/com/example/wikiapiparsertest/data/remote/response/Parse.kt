package com.example.wikiapiparsertest.data.remote.response

data class Parse(
    val categories: List<Category>,
    val displaytitle: String,
    val externallinks: List<String>,
    val images: List<String>,
    val iwlinks: List<Iwlink>,
    val langlinks: List<Langlink>,
    val links: List<Link>,
    val pageid: Int,
    val parsewarnings: List<Any>,
    val properties: List<Property>,
    val revid: Int,
    val sections: List<Section>,
    val showtoc: String,
    val templates: List<Template>,
    val text: Text,
    val title: String
)
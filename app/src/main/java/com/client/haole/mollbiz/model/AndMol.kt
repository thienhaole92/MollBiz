package com.client.haole.mollbiz.model

data class AndMol(
        val _id: String,
        val createdAt: String,
        val desc: String,
        val images: Array<String>,
        val publishedAt: String,
        val source: String,
        val type: String,
        val url: String,
        val used: Boolean,
        val who: String


) {
    fun hasImg(): Boolean {
        return images != null
    }
}
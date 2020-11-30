package com.example.HurixTest.repository.model.news

data class ResponseX(
        val docs: List<Doc>,
        val maxScore: Double,
        val numFound: Int,
        val start: Int
)
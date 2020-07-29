package ru.skillbranch.devintensive.models

data class Profile(
    var firstName: String,
    var lastName: String,
    var about: String,
    var repository: String,
    var rating: Int = 0,
    var respect: Int = 0
) {
    var nickName: String = "Daniil Firsov" //TODO Fix me
    var rank: String = "Junior Android Developer"


    fun toMap(): Map<String, Any> = mapOf(
        "nickName" to nickName,
        "rank" to rank,
        "firstName" to firstName,
        "lastName" to lastName,
        "about" to about,
        "repository" to repository,
        "rating" to rating,
        "respect" to respect
    )
}
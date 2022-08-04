package com.example.chat_application

class User {
    var email : String? = null
    var name : String? = null
    var password : String? = null

    constructor(){}

    constructor(email : String?,name : String?,password : String?){
        this.name = name
        this.email = email
        this.password = password
    }
}